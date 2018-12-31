package com.micro.microServiceGenerator.helper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.micro.microServiceGenerator.model.AutoGenerateRequest;
import com.micro.microServiceGenerator.model.Field;
import com.micro.microServiceGenerator.model.ModelDetailsRequest;



public class EntityCreationHelper {

	private static String importGenerator() {
		StringBuilder build = new StringBuilder();
		build.append("\n\nimport java.util.*;\n");
		build.append("import javax.persistence.*;\n\n");
		/*build.append("\n\nimport javax.persistence.Column;\n");
		build.append("import javax.persistence.Entity;\n");
		build.append("import javax.persistence.GeneratedValue;\n");
		build.append("import javax.persistence.GenerationType;\n");
		build.append("import javax.persistence.Id;\n");
		build.append("import javax.persistence.Table;\n\n");*/
		return build.toString();
	}

	private static String propertyGenerator(List<Field> properties, boolean required) {
		StringBuilder build = new StringBuilder();
		build.append("\t @Id \n");
		build.append("\t @GeneratedValue(strategy = GenerationType.AUTO) \n");
		build.append("\t private Long uid; \n");
		List<Field> fieldsForMethods = new ArrayList<Field>();
		for (Field field : properties) {
			String type = field.getFieldType().substring(0, 1).toUpperCase() + field.getFieldType().substring(1);
			build.append("\t @Column");
			if (field.isHavingRelation()) {
				build.delete(build.lastIndexOf("@"), build.lastIndexOf("n") + 1);
				build.append("@" + field.getRelationType() + "\n");
				build.append("\t @JoinColumn(name=\"" + field.getJoinColumn() + "\")");
			}
			build.append("\n\t private " + type + " " + field.getFieldName() + ";\n");
			fieldsForMethods.add(field);
		}
		build.append("\n\n");
		build.append("\t public void setUid(Long uid) {\n\t\t this.uid = uid; \n\t }\n\n ");
		build.append("\t public Long getUid() {\n\t\t return this.uid; \n\t }\n\n");
		for (Field field : fieldsForMethods) {
			String type = field.getFieldType().substring(0, 1).toUpperCase() + field.getFieldType().substring(1);
			build.append("\t public void set" + field.getFieldName().substring(0, 1).toUpperCase()
					+ field.getFieldName().substring(1) + "(" + type + " " + field.getFieldName() + ") { " + " \n\t\t this."
					+ field.getFieldName() + "=" + field.getFieldName() + "; \n\t } \n\n");
			build.append("\t public " + type + " get" + field.getFieldName().substring(0, 1).toUpperCase()
					+ field.getFieldName().substring(1) + "() { \n\t\t return " + field.getFieldName() + "; \n\t } \n\n ");
		}
		return build.toString();
	}

	public static void generateModels(AutoGenerateRequest autoGenerateRequest, String location) {
		String projectName=autoGenerateRequest.getProjectDetails().getProjectName();
		String packageName=autoGenerateRequest.getProjectDetails().getPackageName();
		for (ModelDetailsRequest model : autoGenerateRequest.getModels()) {
			String modelName = model.getModelName();
			 List<Field> property=model.getFields();
			if(model.isEntity()) {
				try {
					String className = modelName.substring(0, 1).toUpperCase()+ modelName.substring(1);
					String entity="@Entity\n@Table(name=\""+ modelName +"\")\n";
					
					List<String> lines = Arrays.asList("package com."+packageName+".entity;", importGenerator() , entity+ "public class " + className + " {\n\n",
							propertyGenerator(property, false), "\n}");
					Path file = Paths.get(location+projectName+"/"+projectName+"/src/main/java/com/"+ packageName +"/entity/" + className + ".java"); 
					Files.write(file, lines, Charset.forName("UTF-8"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
