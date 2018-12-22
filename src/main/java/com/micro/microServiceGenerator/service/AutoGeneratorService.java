package com.micro.microServiceGenerator.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.micro.microServiceGenerator.helper.ApplicationConfigGenerateHelper;
import com.micro.microServiceGenerator.helper.ApplicationPropertiesGenerateHelper;
import com.micro.microServiceGenerator.helper.ControllerGenerateHelper;
import com.micro.microServiceGenerator.helper.EntityCreationHelper;
import com.micro.microServiceGenerator.helper.JPAGenerateHelper;
import com.micro.microServiceGenerator.helper.MainAppGeneratorHelper;
import com.micro.microServiceGenerator.helper.ModelCreationHelper;
import com.micro.microServiceGenerator.helper.PomGeneratorHelper;
import com.micro.microServiceGenerator.helper.ProjectFolderGenerateHelper;
import com.micro.microServiceGenerator.helper.RestServiceGeneratorHelper;
import com.micro.microServiceGenerator.helper.ServiceGenerateHelper;
import com.micro.microServiceGenerator.helper.SwaggerGenerateHelper;
import com.micro.microServiceGenerator.helper.ZipDirectoryHelper;
import com.micro.microServiceGenerator.model.AutoGenerateRequest;
import com.micro.microServiceGenerator.model.ModelDetailsRequest;

@Service
public class AutoGeneratorService {
	
	private String location="./target/";

	public void generateService(AutoGenerateRequest autoGenerateRequest, HttpServletResponse response) {
		String projectName = autoGenerateRequest.getProjectDetails().getProjectName();
		String packageName = autoGenerateRequest.getProjectDetails().getPackageName();

		// build project folder project structure and mainApp
		buildMainServices(projectName, packageName, autoGenerateRequest);

		// Micro service main model, service, controller generation
		buildModuleServices(autoGenerateRequest, projectName, packageName);

		// Micro service JPA and other integration details
		buildIntegrationServices(autoGenerateRequest);

		generateZip(response, autoGenerateRequest, projectName, packageName);
	}

	private void buildMainServices(String projectName, String packageName,
			AutoGenerateRequest autoGenerateRequest) {
		// project directory generation
		ProjectFolderGenerateHelper.generateRootFolders(projectName, packageName, "target\\");
		// pom.xml generation
		PomGeneratorHelper.generatePomFile(autoGenerateRequest, location);
		// Micro service main App
		MainAppGeneratorHelper.generateMainApplication(projectName, packageName, location);
		// Application properties file
		ApplicationPropertiesGenerateHelper.generateApplicationProperties(projectName,
				autoGenerateRequest.getJpaProperties(), autoGenerateRequest.getIntegrationDetails().isHasJPA(),
				location);
	}

	private void buildModuleServices(AutoGenerateRequest autoGenerateRequest, String projectName,
			String packageName) {
		boolean hasJPA = autoGenerateRequest.getIntegrationDetails().isHasJPA();
		// Micro service main model, service, controller generation
		for (ModelDetailsRequest model : autoGenerateRequest.getModels()) {
			ModelCreationHelper.generateModels(projectName, packageName, model.getModelName(), model.getFields(),
					location);
			// Controller generator
			ControllerGenerateHelper.generateController(projectName, packageName, model.getModelName(), hasJPA,
					location);
			// Service generator
			ServiceGenerateHelper.generateService(projectName, packageName, model.getModelName(), hasJPA, location);
		}
		// Entity generator
		if (hasJPA) {
			EntityCreationHelper.generateModels(autoGenerateRequest, location);
		}
	}

	private void buildIntegrationServices(AutoGenerateRequest autoGenerateRequest) {
		// JPA
		if (autoGenerateRequest.getIntegrationDetails().isHasJPA()) {
			JPAGenerateHelper.generateRespository(autoGenerateRequest, location);
		}
		// SWAGGER config generator changes
		if (autoGenerateRequest.getIntegrationDetails().isHasSwagger()) {
			SwaggerGenerateHelper.generateSwagger(autoGenerateRequest.getProjectDetails().getProjectName(),
					autoGenerateRequest.getProjectDetails().getPackageName(), location);
		}
		ApplicationConfigGenerateHelper.generateConfig(autoGenerateRequest, location);
		if (autoGenerateRequest.getIntegrationDetails().isHasRestTemplate()) {
			RestServiceGeneratorHelper.generateRestService(autoGenerateRequest.getProjectDetails().getProjectName(),
					autoGenerateRequest.getProjectDetails().getPackageName(), location);
		}
	}

	private void generateZip(HttpServletResponse response, AutoGenerateRequest autoGenerateRequest,
			String projectName, String packageName) {
		// Zip file generator
		try {
			String fileName = location + autoGenerateRequest.getProjectDetails().getProjectName();
			ZipDirectoryHelper.generateZip(fileName, location);
			new File(fileName).deleteOnExit();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String filePath = location + autoGenerateRequest.getProjectDetails().getProjectName();
		Path file = Paths.get(location, autoGenerateRequest.getProjectDetails().getProjectName() + ".zip");
		if (Files.exists(file)) {
			response.setHeader("Content-Type", "application/zip");
			response.addHeader("Content-Disposition",
					"attachment; filename=" + autoGenerateRequest.getProjectDetails().getProjectName() + ".zip");
			try {
				Files.copy(file, response.getOutputStream());
				response.getOutputStream().flush();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			System.out.println("file doesn't exist!");
		}
		ZipDirectoryHelper.deleteDirectory(filePath);
		ZipDirectoryHelper.deleteFile(filePath + ".zip");
	}

}
