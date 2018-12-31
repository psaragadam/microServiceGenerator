package com.micro.microServiceGenerator.model;

public class Field {
	private String fieldName;
	private String fieldType;
	private boolean required;
	private boolean havingRelation;
	private String relationType;
	private String joinColumn;

	public Field() {

	}

	public Field(String fieldName, String fieldType, boolean required) {
		super();
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.required = required;
	}

	public Field(String fieldName, String fieldType, boolean required, boolean havingRelation, String relationType,
			String joinColumn) {
		super();
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.required = required;
		this.havingRelation = havingRelation;
		this.relationType = relationType;
		this.joinColumn = joinColumn;
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

	public boolean isHavingRelation() {
		return havingRelation;
	}

	public void setHavingRelation(boolean havingRelation) {
		this.havingRelation = havingRelation;
	}

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	public String getJoinColumn() {
		return joinColumn;
	}

	public void setJoinColumn(String joinColumn) {
		this.joinColumn = joinColumn;
	}

}
