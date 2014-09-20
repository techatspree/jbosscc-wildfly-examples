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
		
		Calculator calc = (Calculator) context.lookup("ejb:" + appName + "/" + moduleName
				+ "/" + distinctName + "/" + beanName + "!" + viewClassName);
		
		return calc;
	}
	
	public void createInitialContext() throws NamingException {
		Properties prop = new Properties();
		prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		context = new InitialContext(prop);
	}
	
	public void closeContext() throws NamingException{
		if(context != null){
			context.close();
		}
	}
}

