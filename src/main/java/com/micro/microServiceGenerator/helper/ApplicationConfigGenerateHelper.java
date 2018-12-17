package com.micro.microServiceGenerator.helper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.micro.microServiceGenerator.model.AutoGenerateRequest;

public class ApplicationConfigGenerateHelper {

	public static void generateConfig(AutoGenerateRequest autoGenerateRequest) {
		try {
			String projectName = autoGenerateRequest.getProjectDetails().getProjectName();
			String packageName = autoGenerateRequest.getProjectDetails().getPackageName();
			StringBuilder imports = buildImports(autoGenerateRequest);
			StringBuilder classDef = buildClassDef(autoGenerateRequest);
			List<String> lines = Arrays.asList(imports.toString(), classDef.toString());
			Path file = Paths.get("./target/" + projectName + "/" + projectName + "/src/main/java/com/" + packageName
					+ "/config/ApplicationConfig.java");
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static StringBuilder buildImports(AutoGenerateRequest autoGenerateRequest) {
		boolean hasSwagger = autoGenerateRequest.getIntegrationDetails().isHasSwagger();
		boolean hasRest = autoGenerateRequest.getIntegrationDetails().isRestTemplate();
		String packageName = autoGenerateRequest.getProjectDetails().getPackageName();
		StringBuilder builder = new StringBuilder();
		builder.append("package com." + packageName + ".config;\n\n");
		builder.append("import org.springframework.context.annotation.Bean;\r\n");
		builder.append("import org.springframework.context.annotation.Configuration;\r\n");
		if (hasSwagger) {
			builder.append("import springfox.documentation.builders.PathSelectors;\r\n");
			builder.append("import springfox.documentation.builders.RequestHandlerSelectors;\r\n");
			builder.append("import springfox.documentation.spi.DocumentationType;\r\n");
			builder.append("import springfox.documentation.spring.web.plugins.Docket;\r\n");
			builder.append("import springfox.documentation.swagger2.annotations.EnableSwagger2;\r\n");
		}
		if (hasRest) {
			builder.append("import org.springframework.web.client.RestTemplate;\r\n");
			builder.append("import org.springframework.boot.web.client.RestTemplateBuilder;\r\n");
		}
		return builder;
	}

	private static StringBuilder buildClassDef(AutoGenerateRequest autoGenerateRequest) {
		boolean hasSwagger = autoGenerateRequest.getIntegrationDetails().isHasSwagger();
		boolean hasRest = autoGenerateRequest.getIntegrationDetails().isRestTemplate();
		StringBuilder builder = new StringBuilder();
		builder.append("@Configuration\r\n");
		builder.append("@EnableSwagger2\r\n");
		builder.append("public class ApplicationConfig {\r\n\n");
		if (hasSwagger) {
			builder.append(generateSwaggerConfig(autoGenerateRequest));
		}
		if (hasRest) {
			builder.append(generateRestTemplateConfig(autoGenerateRequest));
		}
		builder.append("}\n");
		return builder;
	}
	
	private static StringBuilder generateSwaggerConfig(AutoGenerateRequest autoGenerateRequest) {
		StringBuilder builder = new StringBuilder();
		builder.append("\t@Bean\r\n");
		builder.append("\tpublic Docket api() { \r\n");
		builder.append("\t\treturn new Docket(DocumentationType.SWAGGER_2)\r\n");
		builder.append("\t\t\t.select()\r\n");
		builder.append("\t\t\t.apis(RequestHandlerSelectors.any())\r\n");
		builder.append("\t\t\t.paths(PathSelectors.any())\r\n");
		builder.append("\t\t\t.build().enable(true);\r\n");
		builder.append("\t}\r\n\n");
		return builder;
	}

	private static StringBuilder generateRestTemplateConfig(AutoGenerateRequest autoGenerateRequest) {
		StringBuilder builder = new StringBuilder();
		builder.append("\t@Bean\r\n");
		builder.append("\tpublic RestTemplate restTemplate(RestTemplateBuilder builder) {\r\n");
		builder.append("\t\treturn builder.build();\r\n");
		builder.append("\t}\r\n\n");
		return builder;
	}

}
