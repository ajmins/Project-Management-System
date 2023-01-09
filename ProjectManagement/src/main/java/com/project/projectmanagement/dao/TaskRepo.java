package com.project.projectmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.projectmanagement.entity.Task;

public interface TaskRepo extends JpaRepository<Task, Integer>{

	Task findByprojectId(int projectID);

}
