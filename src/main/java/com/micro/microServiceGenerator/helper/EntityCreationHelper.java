package com.micro.microServiceGenerator.helper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.micro.microServiceGenerator.model.AutoGenerateRequest;
import com.micro.microServiceGenerator.model.Field;
import com.micro.microServiceGenerator.model.ModelDetailsRequest;



public class EntityCreationHelper {

	private static String importGenerator() {
		StringBuilder build = new StringBuilder();
		build.append("\n\nimport javax.persistence.Column;\n");
		build.append("import javax.persistence.Entity;\n");
		build.append("import javax.persistence.GeneratedValue;\n");
		build.append("import javax.persistence.GenerationType;\n");
		build.append("import javax.persistence.Id;\n");
		build.append("import javax.persistence.Table;\n\n");
		return build.toString();
	}

	private static String propertyGenerator(List<Field> properties, boolean required) {
		StringBuilder build = new StringBuilder();
		build.append("@Id \n\n");
		build.append("@GeneratedValue(strategy = GenerationType.AUTO) \n\n");
		build.append("private Long uid; \n\n");
		build.append("public void setUid(Long uid){\n this.uid=uid; \n}\n\n ");
		build.append("public Long getUid(){ return this.uid; \n}\n\n");
		
		for (Field field : properties) {
			String type = field.getFieldType().substring(0, 1).toUpperCase() + field.getFieldType().substring(1);
			build.append("@Column \n\n");
			build.append("private  " + type + " " + field.getFieldName() + ";\n \n");
			build.append("public void set" + field.getFieldName().substring(0, 1).toUpperCase()
					+ field.getFieldName().substring(1) + "( " + type + " " + field.getFieldName() + "){ " + " \n this."
					+ field.getFieldName() + "=" + field.getFieldName() + "; \n } \n\n");
			build.append("public " + type + "  get" + field.getFieldName().substring(0, 1).toUpperCase()
					+ field.getFieldName().substring(1) + "(){ \n return " + field.getFieldName() + "; \n } \n\n");
		}
		return build.toString();
	}

	public static void generateModels(AutoGenerateRequest autoGenerateRequest) {
		String projectName=autoGenerateRequest.getProjectDetails().getProjectName();
		String packageName=autoGenerateRequest.getProjectDetails().getPackageName();
		for (ModelDetailsRequest model : autoGenerateRequest.getModels()) {
			String modelName=model.getModelName();
			 List<Field> property=model.getFields();
			if(model.isEntity()) {
				try {
					String className = modelName.substring(0, 1).toUpperCase()+ modelName.substring(1);
					String entity="@Entity\n@Table(name=\" "+ modelName+"\")\n";
					
					
					List<String> lines = Arrays.asList("package com."+packageName+".entity;\n", importGenerator() , entity+ "public class " + className + " {\n\n",
							propertyGenerator(property, false), "\n}");
					Path file = Paths.get("./target/"+projectName+"/"+projectName+"/src/main/java/com/"+ packageName +"/entity/" + className + ".java"); 
					Files.write(file, lines, Charset.forName("UTF-8"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
