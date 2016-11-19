package net.allpa.contentconnector;

import java.util.Map;

public interface DocumentsManager {
	void init(Map<String, String> parameter);
	Object createConnection(String username,char[] password);
	int deleteConnection(String username);
	//byte[] getDocument(String user,String path);
	int writeDocument(String user,String filename,String mimetype,byte[] content);
	
	
}
