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

public class MainAppGeneratorHelper {

	public static void generateMainApplication(String projectName, String packageName, String location) {
		generateModels(projectName, packageName, location);
	}

	public static void generateModels(String projectName, String packageName, String location) {
		try {
			String className = projectName.substring(0, 1).toUpperCase() + projectName.substring(1) +"MainApplication";
			StringBuilder builder=new StringBuilder();
			builder.append("package com." + packageName + ";");
			builder.append("\n\nimport org.springframework.boot.SpringApplication;\r\n"
					+ "import org.springframework.boot.autoconfigure.SpringBootApplication;\n\n");
			builder.append("@SpringBootApplication\n");
			
			builder.append("public class " + className + " {\n\n");
			builder.append(bodyGenerator(className));
			builder.append("\n}");
			
			File file=new File(location+projectName+"/"+projectName+"/src/main/java/com/"+ packageName +"/" + className + ".java");
			FileUtils.writeStringToFile(file, builder.toString());
			
			//Path file = Paths.get(URI.create(location+projectName+"/"+projectName+"/src/main/java/com/"+ packageName +"/" + className + ".java"));
			//Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String bodyGenerator(String projectName) {
		StringBuilder build = new StringBuilder();
		build.append("\t public static void main(String[] args) {\r\n");
		build.append("\t\t SpringApplication.run(" + projectName + ".class, args);\r\n");
		build.append("\t }");
		return build.toString();
	}

}
