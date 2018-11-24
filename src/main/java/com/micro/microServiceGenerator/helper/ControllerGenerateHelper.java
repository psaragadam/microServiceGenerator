package com.micro.microServiceGenerator.helper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ControllerGenerateHelper {
	
	public static void generateController(String projectName, String packageName, String modelName) {
		try {
			String className = modelName.substring(0, 1).toUpperCase() + modelName.substring(1);
			String packageNameValue = "package com." + packageName + ".controller;";
			StringBuilder imports = buildImports(packageName, className);
			StringBuilder classDef = buildClassDef(packageName, className,modelName );
			List<String> lines = Arrays.asList(packageNameValue, imports.toString(), classDef.toString());

			Path file = Paths.get("./target/" + projectName + "/" + projectName + "/src/main/java/com/" + packageName
					+ "/controller/" + className + "Controller.java");

			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String generateControllerDisplay(String projectName, String packageName, String modelName) {
			String className = modelName.substring(0, 1).toUpperCase() + modelName.substring(1);
			String packageNameValue = "package com." + packageName + ".controller;";
			StringBuilder imports = buildImports(packageName, className);
			StringBuilder classDef = buildClassDef(packageName, className,modelName );
			return  packageNameValue + " \n" + imports.toString() + "\n"+ classDef.toString();
	}
	
	
	private static StringBuilder buildImports(String packageName, String className) {
		StringBuilder build = new StringBuilder();
		build.append("\n\nimport com." + packageName+".entity."+ className+";\r\n");
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
	
	
	private static StringBuilder buildClassDef(String packageName, String className, String modelName) {
		String serviceName= className +"Service";
		String serviceInstanceName= modelName +"Service";
		StringBuilder build = new StringBuilder();
		build.append("@RestController\r\n");
		build.append("@RequestMapping(\"/api/" + modelName + " \")\r\n");
		build.append("public class " + className + "Controller {\n\n");
		build.append("@Autowired\n");
		build.append("private "+serviceName + " "+ serviceInstanceName +";\n\n");
		build.append(buildCreateMethod(packageName,  className,  modelName));
		build.append(buildGetMethod(packageName,  className,  modelName));
		build.append(buildUpdateMethod(packageName,  className,  modelName));
		build.append("\n}\n");
		return build;
	}
	
	
	private static StringBuilder buildCreateMethod(String packageName, String className, String modelName) {
		String methodName= " create"+ className;
		String serviceInstanceName= modelName +"Service";
		StringBuilder build = new StringBuilder();
		build.append("@RequestMapping(value=\"/create\", method=RequestMethod.POST) \r\n");
		build.append("public  " + className  +" "+ methodName +"(@RequestBody "+ className +" "+ modelName+"){\n\n");
		build.append("return ");
		build.append(serviceInstanceName);
		build.append(".");
		build.append(methodName);
		build.append("("+ modelName+");\n");
		build.append("}\n\n");
		return build;
	}
	
	private static StringBuilder buildUpdateMethod(String packageName, String className, String modelName) {
		String methodName= " update"+ className;
		String serviceInstanceName= modelName +"Service";
		StringBuilder build = new StringBuilder();
		build.append("@RequestMapping(value=\"/update\", method=RequestMethod.PUT) \r\n");
		build.append("public  " + className  +" "+ methodName +"(@RequestBody "+ className +" "+ modelName+"){\n\n");
		build.append("return ");
		build.append(serviceInstanceName);
		build.append(".");
		build.append(methodName);
		build.append("("+ modelName+");\n");
		build.append("}\n\n");
		return build;
	}
	
	
	
	private static StringBuilder buildGetMethod(String packageName, String className, String modelName) {
		String methodName= " find"+ className;
		String serviceInstanceName= modelName +"Service";
		String filed="field";
		String filedType = "String";
		StringBuilder build = new StringBuilder();
		build.append("@RequestMapping(value=\"/get\", method=RequestMethod.GET) \r\n");
		build.append("public " + className  +" "+ methodName +"("+ filedType + "  " + filed+"){\n\n");
		build.append("return ");
		build.append(serviceInstanceName);
		build.append(".");
		build.append(methodName);
		build.append("("+ filed+");\n");
		build.append("}\n\n");
		return build;
	}
	
	
   public static void main(String[] args) {
	  System.out.println(generateControllerDisplay("Town", "town", "person"));
   }
	

}
