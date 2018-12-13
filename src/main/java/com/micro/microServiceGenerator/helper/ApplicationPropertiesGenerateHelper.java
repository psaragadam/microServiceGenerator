package com.micro.microServiceGenerator.helper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.micro.microServiceGenerator.model.DBPropertiesRequest;

public class ApplicationPropertiesGenerateHelper {


	public static void generateApplicationProperties(String projectName, DBPropertiesRequest properties) {
		try {
			
			StringBuilder builder=new StringBuilder();
			builder.append("# properties file for ");
			builder.append("\n");
			builder.append("server.port = 8888 \n");
			builder.append("spring.application.name = " + projectName + "\n");
			builder.append("spring.datasource.url = " + properties.getUrl() + "\n");
			builder.append("spring.datasource.username = " + properties.getUserName() + "\n");
			builder.append("spring.datasource.password = " + properties.getPassword() + "\n");
			builder.append("spring.jpa.properties.hibernate.dialect = " + properties.getDialect() + "\n");
			builder.append("spring.jpa.hibernate.ddl-auto = " + properties.getDdl_Auto() + "\n");
			List<String> lines = Arrays.asList(builder.toString());
			Path file = Paths.get("./target/"+projectName+"/"+projectName+"/src/main/resources/application.properties");
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

}
