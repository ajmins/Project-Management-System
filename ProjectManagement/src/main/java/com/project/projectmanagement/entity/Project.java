package com.project.projectmanagement.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PROJECT")
public class Project {

	@Id // to define an identifier
	@Column(name = "project_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer projectId;

//	// foreign key
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "login_login_id" , referencedColumnName = "login_id" ,insertable = false,updatable = false)
//	private Login login;
//
//	@Column(name = "login_login_id", nullable = false)
//	private Integer loginId;

	@Column(name = "project_name", nullable = false)
	private String projectName;
	@Column(name = "assignee_name", nullable = false)
	private String assigneeName;
	@Column(name = "start_date", nullable = false)
	private Date startDate;
	@Column(name = "end_date", nullable = false)
	private Date endDate;
	
	//foreign key - primary key
	@JsonIgnore
	@OneToOne(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Task task;
	
	//getters and setters
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
//	public Login getLogin() {
//		return login;
//	}
//	public void setLogin(Login login) {
//		this.login = login;
//	}
//	public Integer getLoginId() {
//		return loginId;
//	}
//	public void setLoginId(Integer loginId) {
//		this.loginId = loginId;
//	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getAssigneeName() {
		return assigneeName;
	}
	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	
	//constructors
	public Project() {
	}
	public Project( String projectName, String assigneeName, Date startDate, Date endDate) {
		super();
		this.projectName = projectName;
		this.assigneeName = assigneeName;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ",  projectName="
				+ projectName + ", assigneeName=" + assigneeName + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", task=" + task + "]";
	}

	
	
}
