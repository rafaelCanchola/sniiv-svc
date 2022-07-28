package mx.gob.sedatu.dgdusv.sniiv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SniivApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SniivApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SniivApplication.class);
	}

	@Autowired
	private Environment env;
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/poligonosmexico").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/api/poligonosinsus").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/api/poligonosmexicoconteo").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/api/poligonosconteo").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/api/predioidentify").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/api/poliinsusall").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/api/polimexicoall").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/api/uploadchargepoli").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/api/predioidentify").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/api/getMaxByState").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/api/getMaxByTown").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/api/periodo").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/api/allPeriodos").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/api/prueba").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/api/uploadpnv").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/api/uploadpnvreporte").allowedOrigins(env.getProperty("app.cross.origins"));


			}
		};
	}

}
