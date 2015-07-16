package ${packageName};

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ${daoPackageName}.${daoServiceClassName};
import edu.uiowa.icts.spring.Security;

/**
 * Generated by Protogen
 * @since ${date}
 */
public abstract class ${abstractResourceClassName} {

	@Autowired
	protected ${daoServiceClassName} ${daoServiceVariableName};

	@ModelAttribute( value = "username" )
	public String getUsername() {
		if ( Security.isAuthenticated() ) {
			return SecurityContextHolder.getContext().getAuthentication().getName();
		}
		return null;
	}

}