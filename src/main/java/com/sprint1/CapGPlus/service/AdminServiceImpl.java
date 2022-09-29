package com.sprint1.CapGPlus.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.CapGPlus.dto.outer.CommunityDTOOuter;
import com.sprint1.CapGPlus.entity.Admin;
import com.sprint1.CapGPlus.entity.Community;
import com.sprint1.CapGPlus.entity.Post;
import com.sprint1.CapGPlus.entity.User;
import com.sprint1.CapGPlus.exception.CommunityAlreadyExistsException;
import com.sprint1.CapGPlus.exception.CommunityNotFoundException;
import com.sprint1.CapGPlus.exception.InvalidCredentialsException;
import com.sprint1.CapGPlus.exception.PasswordMatchException;
import com.sprint1.CapGPlus.repository.AdminRepository;
import com.sprint1.CapGPlus.repository.CommunityRepository;
import com.sprint1.CapGPlus.repository.UserRepository;
import com.sprint1.CapGPlus.service.dto.CommunityDTOService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepository adRepo;

	@Autowired
	private CommunityRepository communityRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommunityDTOService communityDTOService;

	// Admin Auth starts here
	private void addAdmin() {
		Admin a = new Admin();
		a.setFirstName("admin");
		a.setLastName("admin");
		a.setPassword(BCrypt.hashpw("admin", BCrypt.gensalt()));
		adRepo.save(a);
	}

	@Override
	public String adminLogin(Admin pass) throws InvalidCredentialsException {
		Admin a;
		try {
			a = adRepo.findAll().get(0);
		} catch (Exception e) {
			addAdmin();
		}
		a = adRepo.findAll().get(0);
		if (!BCrypt.checkpw(pass.getPassword(), a.getPassword())) {
			throw new InvalidCredentialsException();
		}
		return "Admin Login Successful";
	}

	@Override
	public String updatePassword(Admin a) throws InvalidCredentialsException, PasswordMatchException {
		Admin admin = adRepo.findAll().get(0);
		// Check if new password is same as old password
		// If same, throw exception
		if (BCrypt.checkpw(a.getPassword(), admin.getPassword())) {
			throw new PasswordMatchException();
		}
		if (!admin.getFirstName().equals(a.getFirstName()) || !admin.getLastName().equals(a.getLastName())) {
			throw new InvalidCredentialsException();
		}
		admin.setPassword(BCrypt.hashpw(a.getPassword(), BCrypt.gensalt()));
		adRepo.save(admin);
		return "Password Updated";
	}
	// Admin Auth ends here

	// Admin Community starts

	@Override
	public List<CommunityDTOOuter> getAllCommunities() {
		return communityRepository.findAll().stream().map(communityDTOService::convertToOuterDTO)
				.collect(Collectors.toList());
	}

	@Override
	public CommunityDTOOuter getCommunityById(int communityId) throws CommunityNotFoundException {
		if (!communityRepository.existsById(communityId))
			throw new CommunityNotFoundException();
		return communityDTOService.convertToOuterDTO(communityRepository.findById(communityId).get());
	}

	@Override
	public Community addCommunity(Community community) throws CommunityAlreadyExistsException {
		if (communityRepository.existsById(community.getId()))
			throw new CommunityAlreadyExistsException();

		// Check if community with that name already exists
		if (communityRepository.findByCommunityName(community.getName()) != null)
			throw new CommunityAlreadyExistsException();

		return communityRepository.save(community);
	}
	
	@Override
	public Community editCommunityDetails(int communityId, Community community)
			throws CommunityNotFoundException, CommunityAlreadyExistsException {
		if (!communityRepository.existsById(communityId))
			throw new CommunityNotFoundException();

		Community oldCommunity = communityRepository.findById(communityId).get();

		// Check if community with that name already exists (if name has changed)
		if (!community.getName().equals(oldCommunity.getName())
				&& communityRepository.findByCommunityName(community.getName()) != null)
			throw new CommunityAlreadyExistsException();

		oldCommunity.setName(community.getName());
		oldCommunity.setDescription(community.getDescription());
		return communityRepository.save(oldCommunity);
	}

	@Override
	public String deleteCommunitybyCommunityId(int communityId) throws CommunityNotFoundException {
		if (!communityRepository.existsById(communityId))
			throw new CommunityNotFoundException();		
		
		Community community = communityRepository.findById(communityId).get();
		Set<User> users = community.getUsers();
		String name = community.getName();
		for (User user : users) {
			user.getCommunities().remove(community);
			userRepository.save(user);
		}
		communityRepository.deleteById(communityId);
		return name + " community is successfully deleted";
	}

	// Admin Community ends

}
