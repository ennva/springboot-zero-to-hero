package org.ennva;

import org.ennva.controllers.EventsController;
import org.springframework.boot.SpringApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties(GithubProperties.class)
public class DemoApplication {
	
	@Value("${app.build.version}")
	private String version;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	public String getVersion() {
		return version;
	}

}
