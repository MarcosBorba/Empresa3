package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BancoDados {
	private static Connection connection1;

	public static Connection createConnection() {
		if(connection1!=null) {
			return connection1;
		}
		try {
			Class.forName(DBConfig.DRIVER);
			connection1 = DriverManager.getConnection(DBConfig.URL,DBConfig.USER,DBConfig.PWD);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection1;
	}
}
