package com.micro.microServiceGenerator.helper;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.micro.microServiceGenerator.model.Field;

public class RestServiceGeneratorHelper {

	public static void generateRestService(String projectName, String packageName, String location) {
		try {
			/**
			 * rest service generation
			 */
			String packageNameValue = "package com." + packageName + ".rest;";
			StringBuilder imports = buildServiceImports();
			StringBuilder classDef = buildServiceClassDef();

			List<String> lines = Arrays.asList(packageNameValue, imports.toString(), classDef.toString());

			// Path file = Paths.get(URI.create(location + projectName + "/" + projectName +
			// "/src/main/java/com/" + packageName
			// + "/rest/RestService.java"));
			// Files.write(file, lines, Charset.forName("UTF-8"));
			StringBuilder builder = new StringBuilder();
			builder.append(packageNameValue);
			builder.append(imports.toString());
			builder.append(classDef.toString());
			File file = new File(location + projectName + "/" + projectName + "/src/main/java/com/" + packageName
					+ "/rest/RestService.java");
			FileUtils.writeStringToFile(file, builder.toString());
			/**
			 * Rest Controller generation
			 */
			imports = buildControllerImports();
			classDef = buildControllerClassDef();

			lines = Arrays.asList(packageNameValue, imports.toString(), classDef.toString());

			// Path file = Paths.get(URI.create(location + projectName + "/" + projectName +
			// "/src/main/java/com/" + packageName
			// + "/rest/RestService.java"));
			// Files.write(file, lines, Charset.forName("UTF-8"));
			builder = new StringBuilder();
			builder.append(packageNameValue);
			builder.append(imports.toString());
			builder.append(classDef.toString());
			file = new File(location + projectName + "/" + projectName + "/src/main/java/com/" + packageName
					+ "/rest/CustomRestController.java");
			FileUtils.writeStringToFile(file, builder.toString());
			/**
			 * generate Rest Model
			 */
			StringBuilder restModel = generateRestModel();

			lines = Arrays.asList(packageNameValue, imports.toString(), classDef.toString());

			// Path file = Paths.get(URI.create(location + projectName + "/" + projectName +
			// "/src/main/java/com/" + packageName
			// + "/rest/RestService.java"));
			// Files.write(file, lines, Charset.forName("UTF-8"));
			builder = new StringBuilder();
			builder.append(packageNameValue);
			builder.append(restModel.toString());
			file = new File(location + projectName + "/" + projectName + "/src/main/java/com/" + packageName
					+ "/rest/RestRequestDetails.java");
			FileUtils.writeStringToFile(file, builder.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static StringBuilder buildServiceImports() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n\nimport java.util.*;\n");
		builder.append("import org.springframework.http.*;\n");
		builder.append("import org.springframework.web.client.RestTemplate;\n");
		builder.append("import org.springframework.beans.factory.annotation.Autowired;\n");
		builder.append("import org.springframework.stereotype.Service;\n");
		return builder;
	}

	public static StringBuilder buildServiceClassDef() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n@Service\n");
		builder.append("public class RestService {\n\n");
		builder.append("\t@Autowired\n");
		builder.append("\tprivate RestTemplate restTemplate;\n\n");
		builder.append(buildServiceExchangeMethod());
		builder.append("}\n");
		return builder;
	}

	public static StringBuilder buildServiceExchangeMethod() {
		StringBuilder builder = new StringBuilder();
		builder.append(
				"\tpublic ResponseEntity exchange(String url, String method, Map<String, String> headers, Object entity, Map<String, String> params) {\n");
		builder.append("\t\tHttpMethod httpMethod = HttpMethod.valueOf(method.toUpperCase());\n");
		builder.append("\t\tHttpHeaders httpheaders = new HttpHeaders();\n");
		builder.append("\t\tif (headers != null && !headers.isEmpty()) {\r\n"
				+ "			for (Map.Entry<String, String> entry : headers.entrySet()) {\r\n"
				+ "				httpheaders.set(entry.getKey(), entry.getValue());\r\n" + "			}\r\n"
				+ "		}\n");

		builder.append("\t\tHttpEntity<Object> request = new HttpEntity<Object>(entity, httpheaders);\n");
		builder.append(
				"\t\tResponseEntity<? extends Object> response = restTemplate.exchange(url, httpMethod, request, entity.getClass(), params);\n");
		builder.append("\t\treturn response;\n");
		builder.append("\t}\n\n");
		return builder;

	}

	private static StringBuilder buildControllerImports() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n\nimport org.springframework.beans.factory.annotation.Autowired;\r\n");
		builder.append("import org.springframework.http.*;\r\n");
		builder.append("import org.springframework.web.bind.annotation.*;\n");
		return builder;
	}

	private static StringBuilder buildControllerClassDef() {
		StringBuilder build = new StringBuilder();
		build.append("\n@RestController\r\n");
		build.append("@RequestMapping(\"/api/rest\")\r\n");
		build.append("public class CustomRestController {\n\n");
		build.append("\t @Autowired\n");
		build.append("\t private RestService restService;\n\n");
		build.append(buildControllerExchangeMethod());
		build.append("\n}\n");
		return build;
	}

	private static StringBuilder buildControllerExchangeMethod() {
		StringBuilder builder = new StringBuilder();
		builder.append("\t @RequestMapping(value = \"/exchange\", method=RequestMethod.POST)\r\n");
		builder.append("\t public Object exchange(@RequestBody RestRequestDetails restRequestDetails) {\n");
		builder.append(
				"\t\t ResponseEntity<? extends Object> response = restService.exchange(restRequestDetails.getUrl(), restRequestDetails.getMethod(), restRequestDetails.getHeaders(), restRequestDetails.getEntity(), restRequestDetails.getParams());\n");
		builder.append("\t\t return response.getBody();\n");
		builder.append("\t }\n");
		return builder;

	}

	public static StringBuilder generateRestModel() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n\nimport java.util.*;\n\n");
		builder.append("public class RestRequestDetails {\n\n");
		builder.append(restPropertyGenerator());
		builder.append("\n}");
		return builder;
	}

	private static String restPropertyGenerator() {
		StringBuilder build = new StringBuilder();
		build.append("\t private String url;\n");
		build.append("\t private String method;\n");
		build.append("\t private Map<String, String> headers;\n");
		build.append("\t private Object entity;\n");
		build.append("\t private Map<String, String> params;\n\n");
		build.append("\t public String getUrl() {\r\n" + "		return url;\r\n" + "	}\n\n");
		build.append("\t public void setUrl(String url) {\r\n" + "		this.url = url;\r\n" + "	}\n\n");
		build.append("\t public String getMethod() {\r\n" + "		return method;\r\n" + "	}\n\n");
		build.append("\t public void setMethod(String method) {\r\n" + "		this.method = method;\r\n" + "	}\n\n");
		build.append("\t public Map<String, String> getHeaders() {\r\n" + "		return headers;\r\n" + "	}\n\n");
		build.append("\t public void setHeaders(Map<String, String> headers) {\r\n" + "		this.headers = headers;\r\n"
				+ "	}\n\n");
		build.append("\t public Object getEntity() {\r\n" + "		return entity;\r\n" + "	}\n\n");
		build.append("\t public void setEntity(Object entity) {\r\n" + "		this.entity = entity;\r\n" + "	}\n\n");
		build.append("\t public Map<String, String> getParams() {\r\n" + "		return params;\r\n" + "	}\n\n");
		build.append("\t public void setParams(Map<String, String> params) {\r\n" + "		this.params = params;\r\n"
				+ "	}\n\n");
		return build.toString();
	}

}
