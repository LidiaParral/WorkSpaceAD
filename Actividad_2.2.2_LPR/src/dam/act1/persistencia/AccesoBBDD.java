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

	private String driver;
	private String  url;
	
	public AccesoBBDD() {
		
		Properties properties = new Properties();
		InputStream entradaD = null;
		
		try {
			entradaD = new FileInputStream("configuracion.properties");
			properties.load(entradaD);
			
			this.driver = properties.getProperty("driver");
			this.url = properties.getProperty("url");
			
		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el archivo");
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public Connection getConexion() throws ClassNotFoundException, SQLException{
		Class.forName(driver);
		SQLiteConfig sqConfig = new SQLiteConfig();
		sqConfig.enforceForeignKeys(true);
		Connection con = DriverManager.getConnection(url, sqConfig.toProperties());
		return con;
	}
	
	
	

}
