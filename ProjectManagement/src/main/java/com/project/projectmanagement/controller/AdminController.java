package com.project.projectmanagement.controller;


import java.util.Enumeration;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.projectmanagement.dao.LoginRepo;
import com.project.projectmanagement.dao.PersonRepo;
import com.project.projectmanagement.dao.ProjectRepo;
import com.project.projectmanagement.dao.TaskRepo;
import com.project.projectmanagement.entity.Login;
import com.project.projectmanagement.entity.Person;
import com.project.projectmanagement.entity.Project;
import com.project.projectmanagement.entity.Task;

@Controller
public class AdminController {
	
	@Autowired
	private PersonRepo personRepo;
	@Autowired
	private LoginRepo loginRepo;
	@Autowired
	private ProjectRepo projectRepo;
	@Autowired
	private TaskRepo taskRepo;
	
	@GetMapping("/persons")
	public String listPersons(Login user,Model model, HttpServletResponse response, HttpServletRequest request,HttpSession session) {
		//String fullName = user.getFullName();
		session.setAttribute("fullName", session.getAttribute("fullName"));
		model.addAttribute("personsList",personRepo.findAll());
		//request.setAttribute(fullName, fullName);
		return "persons"; //return a view named students
		}
	
	@GetMapping("/savePersonPage")
	public String savePersonPage(Model model) {
//		ModelAndView mav = new ModelAndView();
		Person person = new Person();
		Login login = new Login();
		model.addAttribute("persons", person);
		model.addAttribute("logins", login);
		return "addPerson";
	}
	@PostMapping("/savePerson")
	//@ModelAttribute is like  
	public String  savePerson(@ModelAttribute("persons")Person person,@ModelAttribute("login")Login login ) { 
		personRepo.save(person);
		if(person.getPersonId()!=null) {
			Integer pid = person.getPersonId();
			login.setLoginId(null);
			if(login.getLoginId()==null) {
				login.setPersonId(pid);
				login.setUserName(person.getFirstName());
				login.setPassword(person.getFirstName()+"*"+"123");
				login.setFullName(person.getFirstName() +" "+ person.getLastName());
				login.setUserType("normaluser");	
			}
		}
		loginRepo.save(login);
		return "redirect:/adminHomePage"; //redirect to home page
		
	}
	@GetMapping("/deletePersonPage/{id}")
	public String deletePerson(@PathVariable("id")int id, Login login) {
		int tempID = id;
		System.out.println(tempID);
		login = loginRepo.findByPersonId(tempID);
		if (login != null) {
			int loginID = login.getLoginId();
			loginRepo.deleteById(loginID);
		}
		personRepo.deleteById(id);		
		return "redirect:/adminHomePage";
	}
	
	@GetMapping("/projects")
	public String listProject(Model model, HttpSession session) {
		System.out.println("View Projects list");
		model.addAttribute("projectsList",projectRepo.findAll());
		session.setAttribute("projects", projectRepo.findAll());
		return "projects"; //return a view named students
		}
	
	@GetMapping("/saveProjectPage")
	public String saveProjectPage(Model model) {
		Project project = new Project();
		model.addAttribute("projects", project);
		return "addProject";
	}
	@PostMapping("/saveProject")
	public String  saveProject(@ModelAttribute("projects")Project project ) { 
//		project.setStartDate(new SimpleDateFormat("dd/MM/yyyy").format(project.getStartDate()));
//		project.setEndDate(new SimpleDateFormat("dd/MM/yyyy").format(project.getEndDate()));
		projectRepo.save(project);
		return "redirect:/adminHomePage"; //redirect to home page
		
	}

	@GetMapping("/editAdminProjectPage/{projectId}")
	public String editAdminProjectPage(@PathVariable("projectId")int projectId,HttpServletRequest request, RedirectAttributes redirectAttributes, Model model,HttpSession session) {
		//check id found in db
		Optional<Project> tempId = projectRepo.findById(projectId);
		Project project = tempId.get();
		model.addAttribute("projects", project);
		model.addAttribute( "id", tempId );
		session.setAttribute("tempId", projectId);
		redirectAttributes.addFlashAttribute("id", tempId);
		
		return "redirect:/updateProject";
	}
	@RequestMapping(value = "/updateProject", method = { RequestMethod.GET, RequestMethod.POST })
	//@PostMapping("/updateProject")
	public String updateProject(Model model, @ModelAttribute("projects")Project project, HttpSession session, HttpServletRequest request) {
		//projectRepo.save(project);
		Project pro = new Project();
		Enumeration<String> attributes = (request.getSession().getAttributeNames());
		while (attributes.hasMoreElements()) {
		    String attribute = (String) attributes.nextElement();
		    if (attribute.equalsIgnoreCase("tempId")) {
//				user.setFullName(request.getSession().getAttribute(attribute).toString());
//				newUser= request.getSession().getAttribute(attribute).toString(); //Isabel
		    	int tempId = (int) request.getSession().getAttribute(attribute);
		    	model.addAttribute("projects",projectRepo.findById(tempId));
		    }
		}
		return "updateProject";
	}
	@GetMapping("/deleteProjectPage/{id}")
	public String deleteProject(@PathVariable("id")int id, Task userProjectTasks) {
		int tempID = id;
		System.out.println(tempID);
		userProjectTasks = taskRepo.findByprojectId(tempID);
		if (userProjectTasks != null) {
			int taskID = userProjectTasks.getTaskId();
			loginRepo.deleteById(taskID);
		}
		projectRepo.deleteById(id);		
		return "redirect:/adminHomePage";
	}
	@GetMapping("/addTaskPage/{id}")
	public String addTask(@ModelAttribute("taskList")Task task, @PathVariable("id")int id,Model model,HttpSession session ,Task userProjectTasks, HttpServletRequest request) {
		int tempID = id;
		session.setAttribute("tempId", id);
		System.out.println(tempID);
		Enumeration<String> attributes = (request.getSession().getAttributeNames());
		while (attributes.hasMoreElements()) {
		    String attribute = (String) attributes.nextElement();
		    if (attribute.equalsIgnoreCase("tempId")) {
		    	int tempId = (int) request.getSession().getAttribute(attribute);
		    	model.addAttribute("projectTask",projectRepo.findById(tempId));
		    }
		}
		
		return "addTask";
	}
	
	@PostMapping("/saveTask")
	public String saveTask(@ModelAttribute("taskList")Task task,Integer tempId,Login user, HttpSession session, HttpServletRequest request) { 
		task.setTaskId(null);
		Enumeration<String> attributes = (request.getSession().getAttributeNames());
		while (attributes.hasMoreElements()) {
		    String attribute = (String) attributes.nextElement();
		    if (attribute.equalsIgnoreCase("tempId")) {
		    	tempId = (Integer) request.getSession().getAttribute(attribute);
		    	System.out.println(tempId);
		    	break;
		    }
		}
		
		task.setProjectId(tempId);
		taskRepo.save(task);
		return "redirect:/projects";
	
}
}
