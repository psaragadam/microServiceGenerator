package com.micro.microServiceGenerator.helper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ServiceGenerateHelper {

	public static void generateService(String projectName, String packageName, String modelName) {
		try {
			String className = modelName.substring(0, 1).toUpperCase() + modelName.substring(1);
			String packageNameValue = "package com." + packageName + ".service;";
			StringBuilder imports = buildImports(packageName, className);
			StringBuilder classDef = buildClassDef(packageName, className, modelName);

			List<String> lines = Arrays.asList(packageNameValue, imports.toString(), classDef.toString());

			Path file = Paths.get("./target/" + projectName + "/" + projectName + "/src/main/java/com/" + packageName
					+ "/service/" + className + "Service.java");

			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String generateControllerDisplay(String projectName, String packageName, String modelName) {
		String className = modelName.substring(0, 1).toUpperCase() + modelName.substring(1);
		String packageNameValue = "package com." + packageName + ".service;";
		StringBuilder imports = buildImports(packageName, className);
		StringBuilder classDef = buildClassDef(packageName, className, modelName);
		return packageNameValue + " \n" + imports.toString() + "\n" + classDef.toString();
	}

	private static StringBuilder buildImports(String packageName, String className) {
		StringBuilder build = new StringBuilder();
		String repo = className + "Repository";
		build.append("\nimport com." + packageName + ".entity." + className + ";\r\n");
		build.append("import com." + packageName + ".repository." + repo + ";\r\n");
		build.append("import org.springframework.stereotype.Service;\r\n");
		build.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
		return build;
	}

	private static StringBuilder buildClassDef(String packageName, String className, String modelName) {
		String serviceName = className + "Service";
		String repo = className + "Repository";
		StringBuilder build = new StringBuilder();
		build.append("@Service\r\n");
		build.append("public class " + serviceName + " {\n\n");
		build.append("\n\t @Autowired");
		build.append("\n\t private " + repo);
		build.append("  ");
		build.append(modelName + "Repository;\n\n\n");
		build.append(buildCreateMethod(packageName, className, modelName));
		build.append(buildGetMethod(packageName, className, modelName));
		build.append(buildUpdateMethod(packageName, className, modelName));
		build.append("\n}\n");
		return build;
	}

	private static StringBuilder buildCreateMethod(String packageName, String className, String modelName) {
		String methodName = "create" + className;
		String repo = modelName + "Repository";
		StringBuilder build = new StringBuilder();
		build.append("\t public " + className + " " + methodName + "(" + className + " " + modelName + ") {\n\t\t");
		build.append(repo + ".save(" + modelName + ");\n\t\t");
		build.append("return " + modelName + ";\n");
		build.append("\t}\n\n");
		return build;
	}
	
	private static StringBuilder buildGetMethod(String packageName, String className, String modelName) {
		String methodName= "find"+ className;
		String repo= modelName+"Repository";
		String field="id";
		String filedType = "Long";
		StringBuilder build = new StringBuilder();
		build.append("\t public " + className  +" "+ methodName +"("+ filedType + " " + field+") {\n");
		build.append("\t\t return ");
		build.append(repo+".findById(" + field + ").get();\n");
		build.append("\t}\n\n");
		return build;
	}
	

	private static StringBuilder buildUpdateMethod(String packageName, String className, String modelName) {
		String methodName = "update" + className;
		String repo = modelName + "Repository";
		StringBuilder build = new StringBuilder();
		build.append("\t public " + className + " " + methodName + "(" + className + " " + modelName + ") {\n");
		build.append("\t\t " + repo + ".save(" + modelName + ");\n");
		build.append("\t\t return " + modelName + ";\n");
		build.append("\t}\n\n");
		return build;
	}

	public static void main(String[] args) {
		System.out.println(generateControllerDisplay("Town", "town", "person"));
	}

}
