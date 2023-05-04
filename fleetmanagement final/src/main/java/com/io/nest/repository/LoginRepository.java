package com.io.nest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.io.nest.model.User;

@Repository
public interface LoginRepository extends JpaRepository<User, Long>{

	User findByEmailAndPassword(String email, String password);
	
	@Query(value = "Select u from User u where u.email=:uemail")
	public User getUserByEmail(@Param(value = "uemail") String uemail);

}
