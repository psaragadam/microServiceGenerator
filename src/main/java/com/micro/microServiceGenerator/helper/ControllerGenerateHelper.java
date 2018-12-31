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

public class ControllerGenerateHelper {
	
	public static void generateController(String projectName, String packageName, String modelName, boolean hasJPA, String location) {
		try {
			String className = modelName.substring(0, 1).toUpperCase() + modelName.substring(1);
			String packageNameValue = "package com." + packageName + ".controller;";
			StringBuilder imports = buildImports(packageName, className, hasJPA);
			StringBuilder classDef = buildClassDef(packageName, className, modelName, hasJPA);
			List<String> lines = Arrays.asList(packageNameValue, imports.toString(), classDef.toString());

			//Path file = Paths.get(URI.create(location + projectName + "/" + projectName + "/src/main/java/com/" + packageName
			//		+ "/controller/" + className + "Controller.java"));
			//Files.write(file, lines, Charset.forName("UTF-8"));
			StringBuilder builder=new StringBuilder();
			builder.append(packageNameValue);
			builder.append(imports.toString());
			builder.append(classDef.toString());
			File file=new File(location + projectName + "/" + projectName + "/src/main/java/com/" + packageName
					+ "/controller/" + className + "Controller.java");
			FileUtils.writeStringToFile(file, builder.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String generateControllerDisplay(String projectName, String packageName, String modelName, boolean hasJPA) {
			String className = modelName.substring(0, 1).toUpperCase() + modelName.substring(1);
			String packageNameValue = "package com." + packageName + ".controller;";
			StringBuilder imports = buildImports(packageName, className, hasJPA);
			StringBuilder classDef = buildClassDef(packageName, className,modelName, hasJPA);
			return  packageNameValue + " \n" + imports.toString() + "\n"+ classDef.toString();
	}
	
	
	private static StringBuilder buildImports(String packageName, String className, boolean hasJPA) {
		StringBuilder build = new StringBuilder();
		if (hasJPA) {
			build.append("\n\nimport com." + packageName+".entity."+ className+";\r\n");
		} else {
			build.append("\n\nimport com." + packageName+".domain."+ className+";\r\n");
		}
		build.append("import com." + packageName+".service."+ className+"Service;\r\n");
		build.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
		build.append("import org.springframework.http.MediaType;\r\n");
		//build.append("import org.springframework.stereotype.Controller;\r\n");
		build.append("import org.springframework.web.bind.annotation.RestController;\n");
		build.append("import org.springframework.web.bind.annotation.RequestBody;\r\n");
		build.append("import org.springframework.web.bind.annotation.RequestMapping;\r\n");
		build.append("import org.springframework.web.bind.annotation.RequestMethod;\r\n");
		build.append("import org.springframework.web.bind.annotation.ResponseBody;\r\n");
		return build;
	}
	
	
	private static StringBuilder buildClassDef(String packageName, String className, String modelName, boolean hasJPA) {
		String serviceName= className +"Service";
		String serviceInstanceName= modelName +"Service";
		StringBuilder build = new StringBuilder();
		build.append("@RestController\r\n");
		build.append("@RequestMapping(\"/api/" + modelName + "\")\r\n");
		build.append("public class " + className + "Controller {\n\n");
		build.append("\t @Autowired\n");
		build.append("\t private "+serviceName + " "+ serviceInstanceName +";\n\n");
		build.append(buildCreateMethod(packageName,  className,  modelName));
		if (hasJPA) {
			build.append(buildGetMethod(packageName,  className,  modelName));
			build.append(buildUpdateMethod(packageName,  className,  modelName));
			build.append(buildDeleteMethod(packageName,  className,  modelName));
		}
		build.append("\n}\n");
		return build;
	}
	
	
	private static StringBuilder buildCreateMethod(String packageName, String className, String modelName) {
		String methodName= "create"+ className;
		String serviceInstanceName= modelName +"Service";
		StringBuilder build = new StringBuilder();
		build.append("\t @RequestMapping(value=\"/create\", method=RequestMethod.POST) \r\n");
		build.append("\t public " + className  +" "+ methodName +"(@RequestBody "+ className +" "+ modelName+") {\n");
		build.append("\t\t return ");
		build.append(serviceInstanceName);
		build.append(".");
		build.append(methodName);
		build.append("("+ modelName+");\n");
		build.append("\t}\n\n");
		return build;
	}
	
	private static StringBuilder buildUpdateMethod(String packageName, String className, String modelName) {
		String methodName= "update"+ className;
		String serviceInstanceName= modelName +"Service";
		StringBuilder build = new StringBuilder();
		build.append("\t @RequestMapping(value=\"/update\", method=RequestMethod.PUT) \r\n");
		build.append("\t public " + className  +" "+ methodName +"(@RequestBody "+ className +" "+ modelName+") {\n");
		build.append("\t\t return ");
		build.append(serviceInstanceName);
		build.append(".");
		build.append(methodName);
		build.append("("+ modelName+");\n");
		build.append("\t}\n\n");
		return build;
	}
	
	
	
	private static StringBuilder buildGetMethod(String packageName, String className, String modelName) {
		String methodName = "find" + className;
		String serviceInstanceName = modelName + "Service";
		String field = "id";
		String filedType = "String";
		StringBuilder build = new StringBuilder();
		build.append("\t @RequestMapping(value=\"/get\", method=RequestMethod.GET) \r\n");
		build.append("\t public " + className  +" "+ methodName +"("+ filedType + " " + field+") {\n");
		build.append("\t\t Long uid = Long.valueOf(" + field + ");\n");
		build.append("\t\t return ");
		build.append(serviceInstanceName);
		build.append(".");
		build.append(methodName);
		build.append("(uid);\n");
		build.append("\t}\n\n");
		return build;
	}
	
	private static StringBuilder buildDeleteMethod(String packageName, String className, String modelName) {
		String methodName = "delete" + className;
		String serviceInstanceName = modelName + "Service";
		String field = "id";
		String filedType = "String";
		StringBuilder build = new StringBuilder();
		build.append("\t @RequestMapping(value=\"/delete\", method=RequestMethod.DELETE) \r\n");
		build.append("\t public void "+ methodName +"("+ filedType + " " + field+") {\n");
		build.append("\t\t Long uid = Long.valueOf(" + field + ");\n");
		build.append("\t\t ");
		build.append(serviceInstanceName);
		build.append(".");
		build.append(methodName);
		build.append("(uid);\n");
		build.append("\t}\n\n");
		return build;
	}
	
	
   public static void main(String[] args) {
	  System.out.println(generateControllerDisplay("Town", "town", "person", true));
   }
	

}
