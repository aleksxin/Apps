package net.allpa.contentconnector;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.client.api.Document;

public class CmisDocumentManager implements DocumentsManager {
	private Map<String,Session> userSessions= new ConcurrentHashMap<String,Session>(); 
	private String cmisurl;
	private String defaultUser;
	private String defaultPass;
	private int repIndex=0;
	private PasswordCallback pwdcallback=null;
	private Repository cmisrepository=null;

	
	public CmisDocumentManager()
	{
		super();
	
	}
	public CmisDocumentManager(PasswordCallback passwordcallback){
		super();
		pwdcallback=passwordcallback;
	
	}
	public void setPasswordCallback(PasswordCallback passwordCallback)
	{
		pwdcallback=passwordCallback;
	}
	
	@Override
	public Object createConnection(String username,char[] password) {
		// TODO Auto-generated method stub
		if (username==""){
			username=defaultUser;
			password=defaultPass.toCharArray();
		}
		Session session=userSessions.get(username);
		if (session==null)
		{
		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put(SessionParameter.USER, username);
        parameters.put(SessionParameter.PASSWORD, new String(password));
        parameters.put(SessionParameter.ATOMPUB_URL,cmisurl);
        parameters.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
        parameters.put(SessionParameter.COMPRESSION, "true");
        parameters.put(SessionParameter.CACHE_TTL_OBJECTS, "0"); // Caching is turned off

        // If there is only one repository exposed (e.g. Alfresco), these
        // lines will help detect it and its ID
       // List<Repository> repositories = sessionFactory.getRepositories(parameters);
        Repository repository = sessionFactory.getRepositories(parameters).get(repIndex);
        if (repository != null){
        	session = repository.createSession();

        // Save connection for reuse
        	userSessions.put(username, session);
        //	return session;
        }

		}
		return session;
	}

	/*@Override
	public byte[] getDocument(String user, String path) {
		// TODO Auto-generated method stub
		Session session=null;
		byte[] content;
		if (userSessions.containsKey(user)){
			session=userSessions.get(user);
		}
		else if(pwdcallback!=null){
			session=(Session)createConnection(user,pwdcallback.getPasswordFromUser(user));
		}
		if (session!=null)
		{
			//String path = newFolder.getPath() + "/" + filename;
	        //System.out.println("Getting object by path " + path);
			//byte[] content;
	        Document doc = (Document) session.getObjectByPath(path);
	        try {
	        	InputStream is = getContentAsInputStream(content[0]);
				ByteArrayOutputStream os = getByteArrayOutputStream(is);
				content = os.toByteArray();
	            //content = doc.getContentStream();
	        } catch (IOException e) {
	           
	        }
	        
		}
		
		return 0;
	}*/

	@Override
	public int writeDocument(String user, String filename, String mimetype,
			byte[] content) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void init(Map<String, String> parameter) {

			// TODO Auto-generated method stub
			if (parameter.containsKey("URL")){
				cmisurl=parameter.get("URL");}
			if (parameter.containsKey("USER")){
				defaultUser=parameter.get("USER");}
			if (parameter.containsKey("PASSWORD")){
				defaultPass=parameter.get("PASSWORD");}
			if (parameter.containsKey("REPOSITORY_INDEX")){
				repIndex=Integer.parseInt(parameter.get("REPOSITORY_INDEX"));}
			
		
		
	}

	@Override
	public int deleteConnection(String username) {
		// TODO Auto-generated method stub
		if (userSessions.containsKey(username)){
			userSessions.remove(username);
		}
		return 0;
	}

}
