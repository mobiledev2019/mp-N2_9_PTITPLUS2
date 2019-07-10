package com.tvtien.app.ws.io.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tvtien.app.ws.io.entity.AddressEntity;
import com.tvtien.app.ws.io.entity.UserEntity;


@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Serializable> {
	List<AddressEntity> findAllByUserDetails(UserEntity userEntity);
	AddressEntity findByAddressId(String addressId);
}
