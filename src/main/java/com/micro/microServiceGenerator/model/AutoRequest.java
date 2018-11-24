package com.micro.microServiceGenerator.model;

import java.util.ArrayList;
import java.util.List;

public class AutoRequest {
	private String projectName;
	private String packageName;
	private String buildTool;
	private String buildFile;
	private String modelName;
	private List<String> fieldName = new ArrayList<>();
	private List<String> fieldType = new ArrayList<>();
	private List<String> requiredFirld = new ArrayList<>();
	// JPA Properties
	private String jpaProperties = "## Spring DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties) \n spring.datasource.url = jdbc:mysql://localhost:3306/test?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false \nspring.datasource.username = root \n spring.datasource.password = 123456\n\n\n## Hibernate Properties \n\n # The SQL dialect makes Hibernate generate better SQL for the chosen database\n spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect \n # Hibernate ddl auto (create, create-drop, validate, update) \n spring.jpa.hibernate.ddl-auto = update";

	private boolean serialize;
	private boolean jsonFormat;
	private boolean junit;
	private boolean jpa;
	private boolean swagger;
	private boolean restTemplate;
	private boolean zip;
	private boolean vcsEnable;
	private boolean buildFileType;

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

	public String getBuildTool() {
		return buildTool;
	}

	public void setBuildTool(String buildTool) {
		this.buildTool = buildTool;
	}

	public String getBuildFile() {
		return buildFile;
	}

	public void setBuildFile(String buildFile) {
		this.buildFile = buildFile;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public List<String> getFieldName() {
		return fieldName;
	}

	public void setFieldName(List<String> fieldName) {
		this.fieldName = fieldName;
	}

	public List<String> getFieldType() {
		return fieldType;
	}

	public void setFieldType(List<String> fieldType) {
		this.fieldType = fieldType;
	}

	public List<String> getRequiredFirld() {
		return requiredFirld;
	}

	public void setRequiredFirld(List<String> requiredFirld) {
		this.requiredFirld = requiredFirld;
	}

	public boolean isSerialize() {
		return serialize;
	}

	public void setSerialize(boolean serialize) {
		this.serialize = serialize;
	}

	public boolean isJsonFormat() {
		return jsonFormat;
	}

	public void setJsonFormat(boolean jsonFormat) {
		this.jsonFormat = jsonFormat;
	}

	public boolean isJunit() {
		return junit;
	}

	public void setJunit(boolean junit) {
		this.junit = junit;
	}

	public boolean isJpa() {
		return jpa;
	}

	public void setJpa(boolean jpa) {
		this.jpa = jpa;
	}

	public boolean isRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(boolean restTemplate) {
		this.restTemplate = restTemplate;
	}

	public boolean isZip() {
		return zip;
	}

	public void setZip(boolean zip) {
		this.zip = zip;
	}

	public boolean isVcsEnable() {
		return vcsEnable;
	}

	public void setVcsEnable(boolean vcsEnable) {
		this.vcsEnable = vcsEnable;
	}

	@Override
	public String toString() {
		return "AutoRequest [projectName=" + projectName + ", packageName=" + packageName + ", buildTool=" + buildTool
				+ ", buildFile=" + buildFile + ", modelName=" + modelName + ", fieldName=" + fieldName + ", fieldType="
				+ fieldType + ", requiredFirld=" + requiredFirld + ", serialize=" + serialize + ", jsonFormat="
				+ jsonFormat + ", junit=" + junit + ", jpa=" + jpa + ", restTemplate=" + restTemplate + ", zip=" + zip
				+ ", vcsEnable=" + vcsEnable + " , jpaProperties=" + jpaProperties + " , swagger=" + swagger
				+ " , buildFileType=" + buildFileType + "]";
	}

	public String getJpaProperties() {
		return jpaProperties;
	}

	public void setJpaProperties(String jpaProperties) {
		this.jpaProperties = jpaProperties;
	}

	public boolean isSwagger() {
		return swagger;
	}

	public void setSwagger(boolean swagger) {
		this.swagger = swagger;
	}

	public boolean isBuildFileType() {
		return buildFileType;
	}

	public void setBuildFileType(boolean buildFileType) {
		this.buildFileType = buildFileType;
	}

}
