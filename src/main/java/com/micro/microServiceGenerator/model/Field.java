package com.micro.microServiceGenerator.model;

public class Field {
	private String fieldName;
	private String fieldType;
	private boolean required;

	public Field(){
		
	}
	public Field(String fieldName, String fieldType, boolean required) {
		super();
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.required = required;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

}

