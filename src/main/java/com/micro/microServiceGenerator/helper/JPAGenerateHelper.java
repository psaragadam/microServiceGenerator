package com.micro.microServiceGenerator.helper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.micro.microServiceGenerator.model.AutoGenerateRequest;
import com.micro.microServiceGenerator.model.ModelDetailsRequest;



public class JPAGenerateHelper {
	

	public static void generateRespository(AutoGenerateRequest autoGenerateRequest, String location) {
		String projectName = autoGenerateRequest.getProjectDetails().getProjectName();
		String packageName = autoGenerateRequest.getProjectDetails().getPackageName();
		for (ModelDetailsRequest model : autoGenerateRequest.getModels()) {
			StringBuilder builder = new StringBuilder();
			String className = model.getModelName().substring(0, 1).toUpperCase() + model.getModelName().substring(1);
			String repoClassName = className + "Repository";
			builder.append("package com." + packageName + ".repository;\n\n");
			builder.append("import org.springframework.data.repository.CrudRepository;\r\n");
			builder.append("import org.springframework.stereotype.Repository;\r\n");
			builder.append("import com." + packageName + ".entity." + className + ";\n\n");
			builder.append("@Repository\r\n");
			builder.append("public interface ");
			builder.append(repoClassName);
			builder.append(" extends CrudRepository<");
			builder.append(className);
			builder.append(", Long>");
			builder.append(" { \r\n\n\n");
			builder.append("}\r\n");
			try {
				List<String> lines = Arrays.asList(builder.toString());

				Path file = Paths.get(location + projectName + "/" + projectName + "/src/main/java/com/"
						+ packageName + "/repository/" + repoClassName + ".java");

				Files.write(file, lines, Charset.forName("UTF-8"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
