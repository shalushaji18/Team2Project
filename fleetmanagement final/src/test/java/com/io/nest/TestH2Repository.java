package com.io.nest;

import org.springframework.data.jpa.repository.JpaRepository;

import com.io.nest.model.User;

public interface TestH2Repository extends JpaRepository<User,Long>{

}
