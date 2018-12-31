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

public class SwaggerGenerateHelper {


	public static void generateSwagger(String projectName, String packageName, String location) {
		try {
			
			StringBuilder builder=new StringBuilder();
			builder.append("package com." + packageName + ".config;"
					+ "\n\n"
					+ "import org.springframework.context.annotation.Bean;\r\n" + 
					"import org.springframework.context.annotation.Configuration;\r\n" + 
					"\r\n" + 
					"import springfox.documentation.builders.PathSelectors;\r\n" + 
					"import springfox.documentation.builders.RequestHandlerSelectors;\r\n" + 
					"import springfox.documentation.spi.DocumentationType;\r\n" + 
					"import springfox.documentation.spring.web.plugins.Docket;\r\n" + 
					"import springfox.documentation.swagger2.annotations.EnableSwagger2;\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"@Configuration\r\n" + 
					"@EnableSwagger2\r\n" + 
					"public class SwaggerConfig {\r\n\n" + 
					"    @Bean\r\n" + 
					"    public Docket api() { \r\n" + 
					"        return new Docket(DocumentationType.SWAGGER_2)  \r\n" + 
					"          .select()                                  \r\n" + 
					"          .apis(RequestHandlerSelectors.any())              \r\n" + 
					"          .paths(PathSelectors.any())                          \r\n" + 
					"          .build().enable(true);                                           \r\n" + 
					"    }\r\n" + 
					"}\n");
			
			//List<String> lines = Arrays.asList(builder.toString());
			//Path file = Paths.get(URI.create(location +projectName+"/"+projectName+"/src/main/java/com/"+ packageName +"/config/SwaggerConfig.java"));

			//Files.write(file, lines, Charset.forName("UTF-8")); 
			
			File file=new File(location +projectName+"/"+projectName+"/src/main/java/com/"+ packageName +"/config/SwaggerConfig.java");
			FileUtils.writeStringToFile(file, builder.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

}
