package dam.act1.persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.sqlite.SQLiteConfig;

public class AccesoBBDD {


	private String url;
	private String driver;
	
	public AccesoBBDD() {
		cargarPropiedades();
	}
	
	private void cargarPropiedades() {
		
		Properties prop = new Properties();
		InputStream is = null;
		
		try {
			is = new FileInputStream("configuracion.properties");
			prop.load(is);
			
			url = prop.getProperty("url");
			driver = prop.getProperty("driver");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public Connection getConnection() 
			throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		SQLiteConfig config = new SQLiteConfig();
		config.enforceForeignKeys(true);
		Connection con = DriverManager.getConnection(url, config.toProperties());
		return con;
	}


}


