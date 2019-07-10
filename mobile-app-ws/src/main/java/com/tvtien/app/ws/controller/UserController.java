//creat usercontroller
package com.tvtien.app.ws.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tvtien.app.ws.exceptions.UserServiceException;
import com.tvtien.app.ws.service.AddressesService;
import com.tvtien.app.ws.service.UserService;
import com.tvtien.app.ws.shared.dto.AddressDTO;
import com.tvtien.app.ws.shared.dto.UserDto;
import com.tvtien.app.ws.ui.model.request.PasswordResetModel;
import com.tvtien.app.ws.ui.model.request.PasswordResetRequestModel;
import com.tvtien.app.ws.ui.model.request.UserDetailsRequestModel;
import com.tvtien.app.ws.ui.model.response.AddressesRest;
import com.tvtien.app.ws.ui.model.response.ErrorMessages;
import com.tvtien.app.ws.ui.model.response.OperationStatusModel;
import com.tvtien.app.ws.ui.model.response.RequestOperationName;
import com.tvtien.app.ws.ui.model.response.RequestOperationStatus;
import com.tvtien.app.ws.ui.model.response.UserRest;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;;

@RestController
@RequestMapping("/users") // http://localhost:8080/users
//@CrossOrigin(origins= {"http://localhost:8083","http://localhost:8084"})
public class UserController {
	// add method POST ,PUT ,DELETE ,GET HTTP

	@Autowired
	UserService userService;

	@Autowired
	AddressesService addressesService;
	
	@ApiOperation(value = "The get userDatail Web service Endpoint",
			note = " this ")
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest getUser(@PathVariable String id) {
		// get user by userid after login
		UserRest returnvalue = new UserRest();

		UserDto userDto = userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnvalue);

		return returnvalue;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest creatUser(@RequestBody UserDetailsRequestModel userDetail) throws Exception {

		UserRest returnvalue = new UserRest();

		if (userDetail.getEmail().isEmpty())
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		// convert du lieu nhan ve
		// UserDto userDto = new UserDto();
		// BeanUtils.copyProperties(userDetail, userDto);
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userDetail, UserDto.class);

		// chuyen va csdl dua ra ket qua
		UserDto creatUser = userService.createUser(userDto);
		// BeanUtils.copyProperties(creatUser, returnvalue);
		returnvalue = modelMapper.map(creatUser, UserRest.class);

		// tra lai ket qua
		return returnvalue;
	}

	// update users
	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetail) {
		UserRest returnValue = new UserRest();

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetail, userDto);

		UserDto updateUser = userService.updateUser(id, userDto);
		BeanUtils.copyProperties(updateUser, returnValue);

		return returnValue;
	}

	// delete user
	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deleteUser(@PathVariable String id) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());

		userService.deleteUser(id);

		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return returnValue;
	}

	// get list user //page and limit
	@ApiImplicitParams({
		@ApiImplicitParam(name = "authorization" ,value ="${userController.authorizationHeader.description}",paramType="header")
	})
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "25") int limit) {
		List<UserRest> returnValue = new ArrayList<>();

		List<UserDto> users = userService.getUsers(page, limit);

		for (UserDto userDto : users) {
			UserRest userRest = new UserRest();
			BeanUtils.copyProperties(userDto, userRest);
			returnValue.add(userRest);
		}

		return returnValue;
	}

	// http:localhost:8080/users/id/addresses
	@GetMapping(path = "/{id}/addresses", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE, "application/hal+json" })
	public Resources<AddressesRest> getUserAddresses(@PathVariable String id) {
		// get address by userid after login
		List<AddressesRest> addressesListRestModel = new ArrayList<>();

		List<AddressDTO> addressDTOs = addressesService.getAddresses(id);

		if (addressDTOs != null && !addressDTOs.isEmpty()) {
			Type listType = new org.modelmapper.TypeToken<List<AddressesRest>>() {
			}.getType();
			addressesListRestModel = new ModelMapper().map(addressDTOs, listType);

			for (AddressesRest addressRest : addressesListRestModel) {
				Link addressLink = linkTo(
						methodOn(UserController.class).getUserAddresse(id, addressRest.getAddressId())).withSelfRel();
				addressRest.add(addressLink);

				Link userLink = linkTo(methodOn(UserController.class).getUser(id)).withRel("user");
				addressRest.add(userLink);
			}
		}

		return new Resources<>(addressesListRestModel);
	}

	@GetMapping(path = "/{userId}/addresses/{addressId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE, "application/hal+json" })
	public Resource<AddressesRest> getUserAddresse(@PathVariable String userId, @PathVariable String addressId) {
		AddressDTO addressDTO = addressesService.getAddress(addressId);

		ModelMapper modelMapper = new ModelMapper();
		Link addressLink = linkTo(methodOn(UserController.class).getUserAddresse(userId, addressId)).withSelfRel();
		Link userLink = linkTo(UserController.class).slash(userId).withRel("user");
		Link addressesLink = linkTo(methodOn(UserController.class).getUserAddresses(userId)).withRel("addresses");

		AddressesRest addressesRestModel = modelMapper.map(addressDTO, AddressesRest.class);

		addressesRestModel.add(addressLink);
		addressesRestModel.add(userLink);
		addressesRestModel.add(addressesLink);

		return new Resource<>(addressesRestModel);
	}

	// users/email-verification?token=sdfsdf

	@GetMapping(path = "/email-verification", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })

	public OperationStatusModel verifyEmailToken(@RequestParam(value = "token") String token) {

		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.VERIFY_EMAIL.name());

		boolean isVerify = userService.verifyEmailToken(token);

		if (isVerify) {
			returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		} else {
			returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
		}
		return returnValue;
	}

	// reset password user
	@PostMapping(path = "/password-reset-request", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel requestReset(@RequestBody PasswordResetRequestModel passwordResetRequest)
			throws Exception {

		OperationStatusModel returnValue = new OperationStatusModel();

		boolean operationResult = userService.requestPasswordReset(passwordResetRequest.getEmail());

		returnValue.setOperationName(RequestOperationName.REQUEST_PASSWORD_RESET.name());
		returnValue.setOperationResult(RequestOperationStatus.ERROR.name());

		if (operationResult) {
			returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		}
		return returnValue;
	}
	
	// reset password user
		@PostMapping(path = "/password-reset", 
				consumes = { MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE })
		public OperationStatusModel requestPassword(@RequestBody PasswordResetModel passwordResetModel)
				throws Exception {

			OperationStatusModel returnValue = new OperationStatusModel();

			boolean operationResult = userService.resetPasswod(
					passwordResetModel.getToken(),
					passwordResetModel.getPassword());
			
			returnValue.setOperationName(RequestOperationName.PASSWORD_RESET.name());
			returnValue.setOperationResult(RequestOperationStatus.ERROR.name());

			if (operationResult) {
				returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
			}
			
			return returnValue;
		}


}
