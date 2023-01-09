package com.project.projectmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.projectmanagement.entity.Login;

public interface LoginRepo extends JpaRepository<Login, Integer>{

	Login findByUserName(String userName);

	Login findByPersonId(int tempID);

}
