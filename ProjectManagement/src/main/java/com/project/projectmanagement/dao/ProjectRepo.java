package com.project.projectmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.projectmanagement.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, Integer> {

	Project findByAssigneeName(String newUser);

}
