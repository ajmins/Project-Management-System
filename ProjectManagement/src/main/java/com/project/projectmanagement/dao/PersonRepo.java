package com.project.projectmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.projectmanagement.entity.Person;

public interface PersonRepo extends JpaRepository<Person, Integer> {

	Person findByFirstName(String newUser);

}
