package com.micro.microServiceGenerator.helper;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import com.micro.microServiceGenerator.model.AutoGenerateRequest;

public class PomGeneratorHelper {

	public void generatePomFile(AutoGenerateRequest autoGenerateRequest, String location) {
		String projectName = autoGenerateRequest.getProjectDetails().getProjectName();
		String packageName = autoGenerateRequest.getProjectDetails().getPackageName();
		try {
			packageName = "com." + packageName;
			//String pomXml = Files.lines(Paths.get("./src/main/resources/pom.xml")).collect(Collectors.joining("\n"));
			File pomFile = ResourceUtils.getFile(this.getClass().getResource("/pom.xml"));
			String pomXml = FileUtils.readFileToString(pomFile);
			pomXml = MessageFormat.format(pomXml, packageName, projectName, "jar", projectName,
					buildDependencies(autoGenerateRequest), buildDockerPlugIn(autoGenerateRequest));
			File file=new File(location + projectName + "/"+ projectName + "/pom.xml");
			FileUtils.writeStringToFile(file, pomXml);
			//Path file = Paths.get(URI.create(location + projectName + "\\"+ projectName + "\\pom.xml"));
			//Files.write(file, Arrays.asList(pomXml), Charset.forName("UTF-8"));
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
	
	private static String buildDockerPlugIn(AutoGenerateRequest autoGenerateRequest) {
		StringBuilder builder = new StringBuilder();
		if (autoGenerateRequest.getIntegrationDetails().isHasDocker()) {
			builder.append("<plugin>\r\n");
			builder.append("<groupId>com.spotify</groupId>\r\n");
			builder.append("<artifactId>docker-maven-plugin</artifactId>\r\n");
			builder.append("<version>0.4.11</version>\r\n");
			builder.append("<configuration>\r\n");
			builder.append("<skipDockerBuild>false</skipDockerBuild>\r\n");
			builder.append("<imageName>twilio-sms</imageName>\r\n");
			builder.append("<newName>docker.io/${project.artifactId}:${project.version}</newName>\r\n");
			builder.append("<!-- not needed when you enable executions -->\r\n");
			builder.append("<dockerDirectory>src/main/docker</dockerDirectory>\r\n");
			builder.append("<serverId>docker-hub</serverId><!-- not needed when you enable executions -->\r\n");
			builder.append("<resources>\r\n");
			builder.append("<resource>\r\n");
			builder.append("<targetPath>/</targetPath>\r\n");
			builder.append("<directory>${project.build.directory}</directory>\r\n");
			builder.append("<include>${project.build.finalName}.jar</include>\r\n");
			builder.append("</resource>\r\n");
			builder.append("</resources>\r\n");
			builder.append("</configuration>\r\n");
			builder.append("</plugin>");
		}
		return builder.toString();
	}
	
}
