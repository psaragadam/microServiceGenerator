package com.micro.microServiceGenerator.model;


public class UserOptionsRequest {
	private String notificationType;
	private String messageType;
	private String account;
	private boolean optInOut;
	public String getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public boolean isOptInOut() {
		return optInOut;
	}
	public void setOptInOut(boolean optInOut) {
		this.optInOut = optInOut;
	}
	
	
}
