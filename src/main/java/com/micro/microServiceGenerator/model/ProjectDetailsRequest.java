package com.micro.microServiceGenerator.model;

public class ProjectDetailsRequest {

	private String projectName;
	private String packageName;
	private String buildToolName;
	private String buildType;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getBuildToolName() {
		return buildToolName;
	}

	public void setBuildToolName(String buildToolName) {
		this.buildToolName = buildToolName;
	}

	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

}
