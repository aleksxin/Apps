package net.allpa;

import net.allpa.contentconnector.CmisDocumentManager;
import net.allpa.contentconnector.DocumentsManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@PropertySource(value="classpath:/alfresco-local.properties}", ignoreResourceNotFound=true)
@SpringBootApplication
public class MainApplication extends SpringBootServletInitializer {

	/**
     * @param args
	 */
    @Value("${alfresco.url}")
    private String alfrescoUrl;

    @Value("${alfresco.defaultuser}")
    private String defaultUser;

    @Value("${alfresco.defaultpassword}")
    private String defaultPass;

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
		parameter.put("URL", alfrescoUrl);
		parameter.put("USER", defaultUser);
		parameter.put("PASSWORD", defaultPass);
		dm.init(parameter);
		return dm;
	
	}
	

}
