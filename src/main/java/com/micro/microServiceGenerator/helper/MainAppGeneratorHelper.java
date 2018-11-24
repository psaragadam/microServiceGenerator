package com.micro.microServiceGenerator.helper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class MainAppGeneratorHelper {

	public static void generateMainApplication(String projectName, String packageName) {
		generateModels(projectName, packageName);
	}

	public static void generateModels(String projectName, String packageName) {
		try {
			String className = projectName.substring(0, 1).toUpperCase() + projectName.substring(1) +"MainApplication";
			List<String> lines = Arrays.asList("package com." + packageName + ";",
					"\n\n import org.springframework.boot.SpringApplication;\r\n"
							+ "import org.springframework.boot.autoconfigure.SpringBootApplication;\n\n",
					"@SpringBootApplication\n", "public class " + className + " {\n\n", bodyGenerator(className),
					"\n}");
			Path file = Paths.get("./target/"+projectName+"/"+projectName+"/src/main/java/com/"+ packageName +"/" + className + ".java");

			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String bodyGenerator(String projectName) {
		StringBuilder build = new StringBuilder();
		build.append("public static void main(String[] args) {\r\n");
		build.append("\tSpringApplication.run(" + projectName + ".class, args);\r\n");
		build.append("	}");
		return build.toString();
	}

}
