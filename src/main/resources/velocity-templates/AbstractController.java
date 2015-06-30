package ${packageName};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

import edu.uiowa.icts.datatable.DataTable;
import edu.uiowa.icts.spring.Security;
import ${abstractResourcePackageName}.${abstractResourceClassName};

/**
 * Generated by Protogen
 * @since ${date}
 */
public abstract class ${abstractControllerClassName} extends ${abstractResourceClassName} {

	/**
	 * @param e
	 * @param draw
	 * @return {@link DataTable}
	 */
	protected DataTable datatableError( Exception e, String draw ) {
		String stackTrace = e.getMessage() + String.valueOf( '\n' );
		for ( StackTraceElement ste : e.getStackTrace() ) {
			stackTrace += ste.toString() + String.valueOf( '\n' );
		}
		DataTable error = new DataTable();
		error.setDraw( draw );
		error.setRecordsFiltered( 0 );
		error.setRecordsTotal( 0 );
		error.setError( stackTrace );
		return error;
	}

}