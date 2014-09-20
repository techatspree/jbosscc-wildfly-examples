package de.akquinet.jbosscc;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.akquinet.jbosscc.ejb.Calculator;

public class Main {
	
	private Context context = null;

	public static void main(String[] args) throws Exception {
		Main main = new Main();
		
		try {
			main.createInitialContext();
			Calculator calculator = main.lookup();
			System.out.println(calculator);
			int result = calculator.add(1, 1);
			System.out.println(result);
		} finally {
			main.closeContext();
		}
	}

	public Calculator lookup() throws NamingException {

		final String appName = "";
		final String moduleName = "remote";
		final String distinctName = "";
		final String beanName = "calculator";
		final String viewClassName = Calculator.class.getName();

		return (Calculator) context.lookup("" + appName + "/" + moduleName
				+ "/" + distinctName + "/" + beanName + "!" + viewClassName);
	}

	public void createInitialContext() throws NamingException {
		Properties prop = new Properties();

		prop.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jboss.naming.remote.client.InitialContextFactory");
		prop.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
		prop.put(Context.SECURITY_PRINCIPAL, "admin");
		prop.put(Context.SECURITY_CREDENTIALS, "secret123!");
		prop.put("jboss.naming.client.ejb.context", true);

		context = new InitialContext(prop);
	}
	
	public void closeContext() throws NamingException{
		if(context != null){
			context.close();
		}
	}

}
