package com.micro.microServiceGenerator.model;

public class ProjectDownloadRequest {
	private boolean zipDownload;
	private boolean jarDownload;
	private boolean vcsDownload;

	public boolean isZipDownload() {
		return zipDownload;
	}

	public void setZipDownload(boolean zipDownload) {
		this.zipDownload = zipDownload;
	}

	public boolean isJarDownload() {
		return jarDownload;
	}

	public void setJarDownload(boolean jarDownload) {
		this.jarDownload = jarDownload;
	}

	public boolean isVcsDownload() {
		return vcsDownload;
	}

	public void setVcsDownload(boolean vcsDownload) {
		this.vcsDownload = vcsDownload;
	}

}
