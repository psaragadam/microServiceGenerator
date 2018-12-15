package com.micro.microServiceGenerator.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.micro.microServiceGenerator.helper.ApplicationPropertiesGenerateHelper;
import com.micro.microServiceGenerator.helper.ControllerGenerateHelper;
import com.micro.microServiceGenerator.helper.EntityCreationHelper;
import com.micro.microServiceGenerator.helper.JPAGenerateHelper;
import com.micro.microServiceGenerator.helper.MainAppGeneratorHelper;
import com.micro.microServiceGenerator.helper.ModelCreationHelper;
import com.micro.microServiceGenerator.helper.PomGeneratorHelper;
import com.micro.microServiceGenerator.helper.ProjectFolderGenerateHelper;
import com.micro.microServiceGenerator.helper.ServiceGenerateHelper;
import com.micro.microServiceGenerator.helper.SwaggerGenerateHelper;
import com.micro.microServiceGenerator.helper.ZipDirectoryHelper;
import com.micro.microServiceGenerator.model.AutoGenerateRequest;
import com.micro.microServiceGenerator.model.ModelDetailsRequest;


@Service
public class AutoGeneratorService {

	public void generateService(AutoGenerateRequest autoGenerateRequest, HttpServletResponse response) {
		String projectName = autoGenerateRequest.getProjectDetails().getProjectName();
		String packageName = autoGenerateRequest.getProjectDetails().getPackageName();

		// build project folder project structure and mainApp
		buildMainServices(projectName, packageName,  autoGenerateRequest );

		// Micro service main model, service, controller generation
		buildModuleServices(autoGenerateRequest, projectName, packageName);
				
		// Micro service JPA and other integration details
		buildIntegrationServices(projectName, packageName, autoGenerateRequest);

		generateZip(response, autoGenerateRequest, projectName, packageName);
	}

	private static void buildMainServices(String projectName, String packageName, AutoGenerateRequest autoGenerateRequest) {
		// project directory generation
		ProjectFolderGenerateHelper.generateRootFolders(projectName, packageName);
		// pom.xml generation
		PomGeneratorHelper.generatePomFile(projectName, packageName);
		// Micro service main App
		MainAppGeneratorHelper.generateMainApplication(projectName, packageName);
		//Application properties file 
		ApplicationPropertiesGenerateHelper.generateApplicationProperties(projectName,autoGenerateRequest.getJpaProperties(), autoGenerateRequest.getIntegrationDetails().isHasJPA());
	}

	private static void buildModuleServices(AutoGenerateRequest autoGenerateRequest, String projectName,
			String packageName) {
		boolean hasJPA = autoGenerateRequest.getIntegrationDetails().isHasJPA();
		// Micro service main model, service, controller generation
		for (ModelDetailsRequest model : autoGenerateRequest.getModels()) {
			ModelCreationHelper.generateModels(projectName, packageName, model.getModelName(), model.getFields());
			// Controller generator
			ControllerGenerateHelper.generateController(projectName, packageName, model.getModelName(), hasJPA);
			// Service generator
			ServiceGenerateHelper.generateService(projectName, packageName, model.getModelName(), hasJPA);
		}
		//Entity generator
		if (hasJPA) {
			EntityCreationHelper.generateModels(autoGenerateRequest);
		}
	}

	private static void buildIntegrationServices(String projectName, String packageName,AutoGenerateRequest autoGenerateRequest) {
		// SWAGGER config generator changes
		SwaggerGenerateHelper.generateSwagger(projectName, packageName);
		// JPA
		if (autoGenerateRequest.getIntegrationDetails().isHasJPA()) {
			JPAGenerateHelper.generateRespository(projectName, packageName, autoGenerateRequest);
		}
	}

	private static void generateZip(HttpServletResponse response, AutoGenerateRequest autoGenerateRequest,
			String projectName, String packageName) {
		// Zip file generator
		try {
			String fileName = "./target/" + autoGenerateRequest.getProjectDetails().getProjectName();
			ZipDirectoryHelper.generateZip(fileName);
			new File(fileName).deleteOnExit();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String filePath = "./target/" + autoGenerateRequest.getProjectDetails().getProjectName();
		Path file = Paths.get("./target/", autoGenerateRequest.getProjectDetails().getProjectName() + ".zip");
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
