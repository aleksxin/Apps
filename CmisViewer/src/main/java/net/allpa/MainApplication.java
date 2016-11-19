package net.allpa;

import java.util.HashMap;
import java.util.Map;

import net.allpa.contentconnector.CmisDocumentManager;
import net.allpa.contentconnector.DocumentsManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class MainApplication extends SpringBootServletInitializer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(MainApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MainApplication.class);
	}
	
	@Bean
	public DocumentsManager documentsManager() {
		DocumentsManager dm = new CmisDocumentManager();
		Map<String,String> parameter = new HashMap<String,String>();
		parameter.put("URL", "http://192.168.1.111:6060/alfresco/api/-default-/public/cmis/versions/1.0/atom");
		parameter.put("USER", "admin");
		parameter.put("PASSWORD", "admin");
		dm.init(parameter);
		return dm;
	
	}
	

}
