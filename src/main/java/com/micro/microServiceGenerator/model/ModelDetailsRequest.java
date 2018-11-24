package com.micro.microServiceGenerator.model;

import java.util.ArrayList;
import java.util.List;

public class ModelDetailsRequest {

	private String modelName;
	private List<Field> fields = new ArrayList<>();
	private boolean serialize;
	private boolean jsonFormat;
	private boolean entity;

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
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

	public boolean isEntity() {
		return entity;
	}

	public void setEntity(boolean entity) {
		this.entity = entity;
	}

}