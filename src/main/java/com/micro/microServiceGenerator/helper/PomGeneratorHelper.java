package com.micro.microServiceGenerator.helper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.micro.microServiceGenerator.model.AutoGenerateRequest;

public class PomGeneratorHelper {

	public static void generatePomFile(AutoGenerateRequest autoGenerateRequest) {
		String projectName = autoGenerateRequest.getProjectDetails().getProjectName();
		String packageName = autoGenerateRequest.getProjectDetails().getPackageName();
		try {
			packageName = "com." + packageName;
			String pomXml = Files.lines(Paths.get("./src/main/resources/pom.xml")).collect(Collectors.joining("\n"));
			pomXml = MessageFormat.format(pomXml, packageName, projectName,"jar", projectName, buildDependencies(autoGenerateRequest));
			Path file = Paths.get("./target/" + projectName + "/"+ projectName + "/pom.xml");
			Files.write(file, Arrays.asList(pomXml), Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String buildDependencies(AutoGenerateRequest autoGenerateRequest) {
		boolean hasJPA = autoGenerateRequest.getIntegrationDetails().isHasJPA();
		boolean hasRest = autoGenerateRequest.getIntegrationDetails().isHasRestTemplate();
		StringBuilder builder=new StringBuilder();
		if(hasJPA) {
			builder.append("<dependency>\n<groupId>org.springframework.boot</groupId>\n<artifactId>spring-boot-starter-data-jpa</artifactId>\n</dependency>\n");
			builder.append("<dependency>\n<groupId>mysql</groupId>\n<artifactId>mysql-connector-java</artifactId>\n<scope>runtime</scope>\n</dependency>\n");	
		}
		if (hasRest) {
			builder.append("<dependency>\n<groupId>com.fasterxml.jackson.core</groupId>\n<artifactId>jackson-databind</artifactId>\n</dependency>\n");
		}
		return builder.toString();
	}
	

	
}
