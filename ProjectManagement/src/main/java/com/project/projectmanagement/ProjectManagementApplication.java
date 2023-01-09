package com.project.projectmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.projectmanagement.dao.LoginRepo;
import com.project.projectmanagement.dao.PersonRepo;
import com.project.projectmanagement.dao.ProjectRepo;
import com.project.projectmanagement.dao.TaskRepo;
import com.project.projectmanagement.entity.Login;
import com.project.projectmanagement.entity.Person;
import com.project.projectmanagement.entity.Project;
import com.project.projectmanagement.entity.Task;

@SpringBootApplication
public class ProjectManagementApplication implements CommandLineRunner {

	@Autowired
	private PersonRepo personRepo;
	@Autowired
	private LoginRepo loginRepo;
	@Autowired
	private ProjectRepo projectRepo;
	@Autowired
	private TaskRepo taskRepo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Person person1 = new Person("Liya", "Mike", "liya.m@email.com", "9874563789"); //1
//		person1 = personRepo.save(person1);
//		Integer pId = person1.getPersonId();
//		
//		Login login1 = new Login(pId, "liyaMike", "liya@123", "Liya Mike", "normaluser");
//		login1 = loginRepo.save(login1);
//		Integer lId = login1.getLoginId();
//		
//		//'Pro', 'Ruth Fox', '01-JAN-2015', '10-MAY-2023'
//		Project project1 = new Project("DCF", "Liya Mike", "23-FEB-2019", "10-JAN-2025");
//		project1 = projectRepo.save(project1);
//		Integer prId = project1.getProjectId();
//		
//		//NULL,2,'Registration', 'In Progress);
//		Task task1 = new Task(prId,"Navigation" ,"Open");
//		task1=taskRepo.save(task1);
		
	}

}
