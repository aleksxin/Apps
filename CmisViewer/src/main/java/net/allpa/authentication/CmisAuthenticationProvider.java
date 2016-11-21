package net.allpa.authentication;

import net.allpa.contentconnector.DocumentsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CmisAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	DocumentsManager fd;
	
	
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {
		
		
		char[] password=auth.getCredentials().toString().toCharArray();
		String username=auth.getName();
		Object obj;
		try {


			 obj = fd.createConnection(username, password);
		}
		catch (Exception e){
			throw new BadCredentialsException("Wrong credentials.");
		}

			if (obj==null)
				throw new BadCredentialsException("Wrong credentials.");


		return new UsernamePasswordAuthenticationToken(username,password,new ArrayList());
	}

	public boolean supports(Class<?> auth) {
		// TODO Auto-generated method stub
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}

}
