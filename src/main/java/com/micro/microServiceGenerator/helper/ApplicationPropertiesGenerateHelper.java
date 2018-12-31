package com.micro.microServiceGenerator.helper;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.micro.microServiceGenerator.model.AutoGenerateRequest;
import com.micro.microServiceGenerator.model.DBPropertiesRequest;

public class ApplicationPropertiesGenerateHelper {

	public static void generateApplicationProperties(String projectName, AutoGenerateRequest autoGenerateRequest,
			String location) {
		try {
			DBPropertiesRequest properties = autoGenerateRequest.getJpaProperties();
			boolean hasJPA = autoGenerateRequest.getIntegrationDetails().isHasJPA();
			boolean hasProfiling = autoGenerateRequest.getIntegrationDetails().isHasProfiling();
			StringBuilder builder = new StringBuilder();
			builder.append("# properties file for ");
			builder.append("\n");
			builder.append("server.port = 8888 \n");
			builder.append("spring.application.name = " + projectName + "\n");
			if (hasProfiling) {
				builder.append("spring.profiles.active = " + autoGenerateRequest.getSpringProfile());
			} else {
				if (hasJPA) {
					builder.append("spring.datasource.url = " + properties.getUrl() + "\n");
					builder.append("spring.datasource.username = " + properties.getUserName() + "\n");
					builder.append("spring.datasource.password = " + properties.getPassword() + "\n");
					builder.append("spring.jpa.properties.hibernate.dialect = " + properties.getDialect() + "\n");
					builder.append("spring.jpa.hibernate.ddl-auto = " + properties.getDdl_Auto() + "\n");
				}
			}
			//List<String> lines = Arrays.asList(builder.toString());
			//Path file = Paths
			//		.get(URI.create(location + projectName + "/" + projectName + "/src/main/resources/application.properties"));
			//Files.write(file, lines, Charset.forName("UTF-8"));
			
			File file=new File(location + projectName + "/" + projectName + "/src/main/resources/application.properties");
			FileUtils.writeStringToFile(file, builder.toString());


			if (hasProfiling) {
				for (String profile : autoGenerateRequest.getProfilesList()) {
					builder = new StringBuilder();
					if (hasJPA) {
						builder.append("spring.datasource.url = " + properties.getUrl() + "\n");
						builder.append("spring.datasource.username = " + properties.getUserName() + "\n");
						builder.append("spring.datasource.password = " + properties.getPassword() + "\n");
						builder.append("spring.jpa.properties.hibernate.dialect = " + properties.getDialect() + "\n");
						builder.append("spring.jpa.hibernate.ddl-auto = " + properties.getDdl_Auto() + "\n");
					}
					//lines = Arrays.asList(builder.toString());
					//file = Paths.get(location + projectName + "/" + projectName + "/src/main/resources/application-"
					//		+ profile + ".properties");
					//Files.write(file, lines, Charset.forName("UTF-8"));
					file=new File(location + projectName + "/" + projectName + "/src/main/resources/application-"+ profile + ".properties");
					FileUtils.writeStringToFile(file, builder.toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
