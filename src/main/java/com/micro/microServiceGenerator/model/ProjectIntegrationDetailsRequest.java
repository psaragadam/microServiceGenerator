package com.micro.microServiceGenerator.model;

public class ProjectIntegrationDetailsRequest {
	private boolean hasSwagger;
	private boolean hasJunit;
	private boolean hasJPA;
	private boolean hasRestTemplate;
	private boolean hasProfiling;

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

	public boolean isHasRestTemplate() {
		return hasRestTemplate;
	}

	public void setHasRestTemplate(boolean hasRestTemplate) {
		this.hasRestTemplate = hasRestTemplate;
	}

	public boolean isHasProfiling() {
		return hasProfiling;
	}

	public void setHasProfiling(boolean hasProfiling) {
		this.hasProfiling = hasProfiling;
	}

}
