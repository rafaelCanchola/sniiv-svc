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
				registry.addMapping("/gis-api/poligonosmexico").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/poligonosinsus").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/poligonosmexicoconteo").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/poligonosconteo").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/predioidentify").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/poliinsusall").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/polimexicoall").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/uploadchargepoli").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/predioidentify").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/getMaxByState").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/getMaxByTown").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/periodo").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/allPeriodos").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/prueba").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/uploadpnv").allowedOrigins(env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/uploadpnvreporte").allowedOrigins(env.getProperty("app.cross.origins"));


			}
		};
	}

}
