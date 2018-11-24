package com.micro.microServiceGenerator.model;

public class ProjectIntegrationDetailsRequest {
	private boolean hasSwagger;
	private boolean hasJunit;
	private boolean hasJPA;
	private boolean restTemplate;

	public boolean isHasSwagger() {
		return hasSwagger;
	}

	public void setHasSwagger(boolean hasSwagger) {
		this.hasSwagger = hasSwagger;
	}

	public boolean isHasJunit() {
		return hasJunit;
	}

	public void setHasJunit(boolean hasJunit) {
		this.hasJunit = hasJunit;
	}

	public boolean isHasJPA() {
		return hasJPA;
	}

	public void setHasJPA(boolean hasJPA) {
		this.hasJPA = hasJPA;
	}

	public boolean isRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(boolean restTemplate) {
		this.restTemplate = restTemplate;
	}

}
