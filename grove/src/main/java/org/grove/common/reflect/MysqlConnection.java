package org.grove.common.reflect;

public class MysqlConnection implements DBConnection {

	@Override
	public String getDBName() {
		return "mysql";
	}
}
