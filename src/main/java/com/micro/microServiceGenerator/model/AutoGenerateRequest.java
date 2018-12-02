package com.micro.microServiceGenerator.model;

import java.util.ArrayList;
import java.util.List;

public class AutoGenerateRequest {
	// project details
	private ProjectDetailsRequest projectDetails;
	// model details
	private List<ModelDetailsRequest> models = new ArrayList<ModelDetailsRequest>();
	// project integration details
	private ProjectIntegrationDetailsRequest integrationDetails;
	// project download details
	private ProjectDownloadRequest downloadDetails;
	//JPA Properties
	private DBPropertiesRequest jpaProperties;
	
	public ProjectDetailsRequest getProjectDetails() {
		return projectDetails;
	}

	public void setProjectDetails(ProjectDetailsRequest projectDetails) {
		this.projectDetails = projectDetails;
	}

	public List<ModelDetailsRequest> getModels() {
		return models;
	}

	public void setModels(List<ModelDetailsRequest> models) {
		this.models = models;
	}

	public ProjectIntegrationDetailsRequest getIntegrationDetails() {
		return integrationDetails;
	}

	public void setIntegrationDetails(ProjectIntegrationDetailsRequest integrationDetails) {
		this.integrationDetails = integrationDetails;
	}

	public ProjectDownloadRequest getDownloadDetails() {
		return downloadDetails;
	}

	public void setDownloadDetails(ProjectDownloadRequest downloadDetails) {
		this.downloadDetails = downloadDetails;
	}

	public DBPropertiesRequest getJpaProperties() {
		return jpaProperties;
	}

	public void setJpaProperties(DBPropertiesRequest jpaProperties) {
		this.jpaProperties = jpaProperties;
	}


}
