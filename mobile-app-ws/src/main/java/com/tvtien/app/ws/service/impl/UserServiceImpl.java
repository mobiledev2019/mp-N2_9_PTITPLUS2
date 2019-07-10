package com.tvtien.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tvtien.app.ws.exceptions.UserServiceException;
import com.tvtien.app.ws.io.entity.PasswordResetTokenEntity;
import com.tvtien.app.ws.io.entity.UserEntity;
import com.tvtien.app.ws.io.repository.UserRepository;
import com.tvtien.app.ws.io.repository.passwordResetTokenRepository;
import com.tvtien.app.ws.service.UserService;
import com.tvtien.app.ws.shared.AmazonSES;
import com.tvtien.app.ws.shared.dto.AddressDTO;
import com.tvtien.app.ws.shared.dto.UserDto;
import com.tvtien.app.ws.shared.dto.Utils;
import com.tvtien.app.ws.ui.model.response.ErrorMessages;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	passwordResetTokenRepository passwordResetTokenRepository;

	@Autowired
	Utils utils;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) {
		// TODO Auto-generated method stub
		// xac dinh email co ton tai hay khong
		UserEntity storedUserDetail = userRepository.findByEmail(user.getEmail());

		if (storedUserDetail != null)
			throw new RuntimeException("Record already exits");

		for (int i = 0; i < user.getAddresses().size(); i++) {
			AddressDTO address = user.getAddresses().get(i);
			address.setUserDetails(user);
			address.setAddressId(utils.generateAddressesId(30));
			user.getAddresses().set(i, address);
		}

		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
		// BeanUtils.copyProperties(user, userEntity);

		// tao id user
		String publicUserId = utils.generateUserId(30);
		userEntity.setUserId(publicUserId);

		// ma hoa mat khau
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setEmailVerificationToken(utils.generatEmailVerificationToken(publicUserId));
		userEntity.setEmailVerificationStatus(false);

		// save to database
		UserEntity storedUserDeatail = userRepository.save(userEntity);

		// BeanUtils.copyProperties(storedUserDeatail, returnvalue);
		UserDto returnvalue = modelMapper.map(storedUserDeatail, UserDto.class);

		// send mail verification to user sigup
		new AmazonSES().verifyEmail(returnvalue);

		return returnvalue;
	}

	// phuong thc dung de login
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		// dang nhap bang email : tim kiem theo email
		UserEntity userEntity = userRepository.findByEmail(email);
		// neu khong ton tai tra lai ket qua
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		// neu co dua ra tai khoan
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(),
				userEntity.getEmailVerificationStatus(), true, true, true, new ArrayList<>());
		// return new User(userEntity.getEmail(), userEntity.getEncryptedPassword() ,
		// new ArrayList<>());

	}

	// login
	@Override
	public UserDto getUser(String email) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findByEmail(email);

		// neu khong ton tai tra lai ket qua
		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		UserDto returnvalue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnvalue);

		return returnvalue;
	}

	// service get user by userid after login get userid
	@Override
	public UserDto getUserByUserId(String userId) {
		// TODO Auto-generated method stub
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null)
			throw new UsernameNotFoundException("User whit id : " + userId + "  not found");

		BeanUtils.copyProperties(userEntity, returnValue);

		return returnValue;
	}

	// update user after login
	@Override
	public UserDto updateUser(String userId, UserDto userDto) {

		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());

		UserEntity updateUserDeatail = userRepository.save(userEntity);
		BeanUtils.copyProperties(updateUserDeatail, returnValue);

		return returnValue;
	}

	// delete user
	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		userRepository.delete(userEntity);

	}

	// get user for list ( pagr and limit )
	@Override
	public List<UserDto> getUsers(int page, int limit) {
		List<UserDto> returnValue = new ArrayList<>();

		if (page > 0)
			page = page - 1;

		Pageable pageableRequest = new PageRequest(page, limit);

		Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);

		List<UserEntity> users = usersPage.getContent();

		for (UserEntity userEntity : users) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userEntity, userDto);
			returnValue.add(userDto);
		}
		return returnValue;
	}

	// erify email
	@Override
	public boolean verifyEmailToken(String token) {
		boolean returnValue = false;

		UserEntity userEntity = userRepository.findUserByEmailVerificationToken(token);

		if (userEntity != null) {
			boolean hastokenExpired = Utils.hasTokenExprired(token);
			if (!hastokenExpired) {
				userEntity.setEmailVerificationToken(null);
				userEntity.setEmailVerificationStatus(Boolean.TRUE);
				userRepository.save(userEntity);
				returnValue = true;
			}
		}

		return returnValue;
	}

	// resuet reset password
	@Override
	public boolean requestPasswordReset(String email) {

		boolean returnValue = false;

		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null) {
			return returnValue;
		}

		String token = new Utils().generatePasswordResetToken(userEntity.getUserId());
		PasswordResetTokenEntity passwordResetTokenEntity = new PasswordResetTokenEntity();
		passwordResetTokenEntity.setToken(token);
		passwordResetTokenEntity.setUserDetails(userEntity);
		passwordResetTokenRepository.save(passwordResetTokenEntity);

		returnValue = new AmazonSES().sendPasswordResetRequest(userEntity.getFirstName(), userEntity.getEmail(), token);

		return returnValue;
	}

	// reset password
	@Override
	public boolean resetPasswod(String token, String password) {
		boolean returnValue = false;

		if (Utils.hasTokenExprired(token)) {
			return returnValue;
		}

		PasswordResetTokenEntity passwordResetTokenEntity = passwordResetTokenRepository.findByToken(token);

		if (passwordResetTokenEntity == null) {
			return returnValue;
		}

		// Prepare new password
		String encodedPassword = bCryptPasswordEncoder.encode(password);

		// Update User password in database
		UserEntity userEntity = passwordResetTokenEntity.getUserDetails();
		userEntity.setEncryptedPassword(encodedPassword);
		UserEntity savedUserEntity = userRepository.save(userEntity);

		// Verify if password was saved successfully
		if (savedUserEntity != null && savedUserEntity.getEncryptedPassword().equalsIgnoreCase(encodedPassword)) {
			returnValue = true;
		}

		// Remove Password Reset token from database
		passwordResetTokenRepository.delete(passwordResetTokenEntity);

		return returnValue;

	}

}
