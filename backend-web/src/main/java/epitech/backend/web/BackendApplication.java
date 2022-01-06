package epitech.backend.web;

import epitech.backend.web.global.properties.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@ComponentScan({"epitech"})
@SpringBootApplication()
@EnableConfigurationProperties(StorageProperties.class)
@EnableJpaRepositories({"epitech.backend.domain.*"})
@EntityScan({"epitech.backend.domain.*"})
@EnableAsync
public class BackendApplication extends SpringBootServletInitializer {

	//Nécessaire pour corriger le bug dans mysql qui shift d'un jour un localdate quand on l'enregistre en BDD
	// https://hibernate.atlassian.net/browse/HHH-11396?attachmentSortBy=fileName
	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("TimeZone"));
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BackendApplication.class);
	}

}
