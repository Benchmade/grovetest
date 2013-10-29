package org.grove.common.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DBConnectionProxy implements InvocationHandler {

	private final Object proxyClass;

	public DBConnectionProxy(Object proxyClass) {
		this.proxyClass = proxyClass;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("DBConnectionProxy 1");
		Object result = method.invoke(proxyClass, args);
		System.out.println("DBConnectionProxy 2");
		return result;
	}

	public static void main(String[] args) {
		MysqlConnection m = new MysqlConnection();
		InvocationHandler ih = new DBConnectionProxy(m);
		DBConnection dbc = (DBConnection) Proxy.newProxyInstance(m.getClass().getClassLoader(), m.getClass().getInterfaces(), ih);
		System.out.println(dbc.getDBName());
	}
}
