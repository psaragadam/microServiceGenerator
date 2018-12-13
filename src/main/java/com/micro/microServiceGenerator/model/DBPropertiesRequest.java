package com.micro.microServiceGenerator.model;

public class DBPropertiesRequest {

	private String url;
	private String userName;
	private String password;
	private String dialect;
	private String ddl_Auto;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	public String getDdl_Auto() {
		return ddl_Auto;
	}

	public void setDdl_Auto(String ddl_Auto) {
		this.ddl_Auto = ddl_Auto;
	}

}
