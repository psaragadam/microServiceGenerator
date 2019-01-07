package com.micro.microServiceGenerator.helper;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.micro.microServiceGenerator.model.AutoGenerateRequest;
import com.micro.microServiceGenerator.model.DBPropertiesRequest;

public class DockerGenerateHelper {

	public static void generateDockerGenerateHelper(String projectName, AutoGenerateRequest autoGenerateRequest,
			String location) {
		try {
			DBPropertiesRequest properties = autoGenerateRequest.getJpaProperties();
			boolean hasJPA = autoGenerateRequest.getIntegrationDetails().isHasJPA();
			boolean hasProfiling = autoGenerateRequest.getIntegrationDetails().isHasProfiling();
			StringBuilder builder = new StringBuilder();
			builder.append("FROM frolvlad/alpine-oraclejdk8:slim\n");
			builder.append("VOLUME /tmp\n");
			builder.append("ADD "+ projectName+"-0.0.1.jar "+projectName+".jar \n");
			builder.append("RUN sh -c 'touch /"+projectName+".jar' \n");
			builder.append("ENV JAVA_OPTS=\"\" \n");
			builder.append("ENTRYPOINT [ \"sh\", \"-c\", \"java $JAVA_OPTS -Dspring.profiles.active=dev -Djava.security.egd=file:/dev/./urandom -jar /"+projectName+".jar\" ] \n");
			File file=new File(location + projectName + "/" + projectName + "/src/main/docker/DockerFile");
			FileUtils.writeStringToFile(file, builder.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
