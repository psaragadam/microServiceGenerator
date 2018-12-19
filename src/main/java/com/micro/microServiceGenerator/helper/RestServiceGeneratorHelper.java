package com.micro.microServiceGenerator.helper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class RestServiceGeneratorHelper {

	public static void generateRestService(String projectName, String packageName) {
		try {
			String packageNameValue = "package com." + packageName + ".rest;";
			StringBuilder imports = buildImports();
			StringBuilder classDef = buildClassDef();

			List<String> lines = Arrays.asList(packageNameValue, imports.toString(), classDef.toString());

			Path file = Paths.get("./target/" + projectName + "/" + projectName + "/src/main/java/com/" + packageName
					+ "/rest/RestService.java");

			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static StringBuilder buildImports() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nimport java.util.*;\n");
		builder.append("import org.springframework.http.*;\n");
		builder.append("import org.springframework.web.client.RestTemplate;\n");
		builder.append("import org.springframework.beans.factory.annotation.Autowired;\n");
		builder.append("import org.springframework.stereotype.Service;\n");
		return builder;
	}

	public static StringBuilder buildClassDef() {
		StringBuilder builder = new StringBuilder();
		builder.append("@Service\n");
		builder.append("public class RestService {\n\n");
		builder.append("\t@Autowired\n");
		builder.append("\tprivate RestTemplate restTemplate;\n\n");
		builder.append(buildExchangeMethod());
		builder.append("}\n");
		return builder;
	}

	public static StringBuilder buildExchangeMethod() {
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

}
