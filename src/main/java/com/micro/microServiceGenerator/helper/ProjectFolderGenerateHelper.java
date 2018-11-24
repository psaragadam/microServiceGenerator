package com.micro.microServiceGenerator.helper;

import java.io.File;

public class ProjectFolderGenerateHelper {

	public static void generateRootFolders(String projectName, String packageName) {
		String storageLocation = "target\\"+ projectName +"\\";
		boolean controller = new File(
				storageLocation + projectName + "\\src\\main\\java\\com\\" + packageName + "\\controller").mkdirs();
		boolean domain = new File(storageLocation + projectName + "\\src\\main\\java\\com\\" + packageName + "\\domain")
				.mkdirs();
		boolean entity = new File(storageLocation + projectName + "\\src\\main\\java\\com\\" + packageName + "\\entity")
				.mkdirs();
		boolean service = new File(
				storageLocation + projectName + "\\src\\main\\java\\com\\" + packageName + "\\service").mkdirs();
		boolean repository = new File(
				storageLocation + projectName + "\\src\\main\\java\\com\\" + packageName + "\\repository").mkdirs();
		boolean config = new File(storageLocation + projectName + "\\src\\main\\java\\com\\" + packageName + "\\config")
				.mkdirs();
		boolean properties = new File(storageLocation + projectName + "\\src\\main\\resources")
				.mkdirs();
	}

}
