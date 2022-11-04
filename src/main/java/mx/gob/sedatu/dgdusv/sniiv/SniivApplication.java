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

	@Autowired
	private Environment env;

	private String ownOrigin = "http://172.16.15.94";
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SniivApplication.class);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/gis-api/poligonosmexico").allowedOrigins(ownOrigin,env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/poligonosinsus").allowedOrigins(ownOrigin,env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/poligonosmexicoconteo").allowedOrigins(ownOrigin,env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/poligonosconteo").allowedOrigins(ownOrigin,env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/predioidentify").allowedOrigins(ownOrigin,env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/poliinsusall").allowedOrigins(ownOrigin,env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/polimexicoall").allowedOrigins(ownOrigin,env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/uploadchargepoli").allowedOrigins(ownOrigin,env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/predioidentify").allowedOrigins(ownOrigin,env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/getMaxByState").allowedOrigins(ownOrigin,env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/getMaxByTown").allowedOrigins(ownOrigin,env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/periodo").allowedOrigins(ownOrigin,env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/allPeriodos").allowedOrigins(ownOrigin,env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/prueba").allowedOrigins(ownOrigin,env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/uploadpnv").allowedOrigins(ownOrigin,env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/uploadpnvreporte").allowedOrigins(ownOrigin,env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/poligonosinsusmaxmin").allowedOrigins(ownOrigin,env.getProperty("app.cross.origins"));
				registry.addMapping("/gis-api/poligonosmexicomaxmin").allowedOrigins(ownOrigin,env.getProperty("app.cross.origins"));

			}
		};
	}

}
