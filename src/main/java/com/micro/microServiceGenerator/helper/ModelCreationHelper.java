package com.micro.microServiceGenerator.helper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.micro.microServiceGenerator.model.Field;



public class ModelCreationHelper {

	public static void generateModels(String projectName, String packageName, String modelName, List<Field> property) {
		try {
			String className = modelName.substring(0, 1).toUpperCase()+ modelName.substring(1);
			List<String> lines = Arrays.asList("package com."+packageName+".domain;", "\n\n", "public class " + className + " {\n\n",
					propertyGenerator(property, false), "\n}");
			Path file = Paths.get("./target/"+projectName+"/"+projectName+"/src/main/java/com/"+ packageName +"/domain/" + className + ".java"); 
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String propertyGenerator(List<Field> properties, boolean required) {
		StringBuilder build = new StringBuilder();
		for (Field field : properties) {
			String type = field.getFieldType().substring(0, 1).toUpperCase() + field.getFieldType().substring(1);
			build.append("private  " + type + " " + field.getFieldName() + ";\n \n");
			build.append("public void set" + field.getFieldName().substring(0, 1).toUpperCase()
					+ field.getFieldName().substring(1) + "( " + type + " " + field.getFieldName() + "){ " + " \n this."
					+ field.getFieldName() + "=" + field.getFieldName() + "; \n } \n\n");
			build.append("public " + type + "  get" + field.getFieldName().substring(0, 1).toUpperCase()
					+ field.getFieldName().substring(1) + "(){ \n return " + field.getFieldName() + "; \n } \n\n");
		}
		return build.toString();
	}

}
