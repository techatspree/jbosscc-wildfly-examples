package de.akquinet.jbosscc.ejb;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless(name="calculator")
public class CalculatorBean implements Calculator {
	
	@Resource
	private SessionContext context;

	@Override
	public int add(int x, int y) {
		return x + y;
	}

	@Override
	public int subtract(int x, int y) {
		return x - y;
	}

}
