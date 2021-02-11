package dam.act1.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dam.act1.javabeans.Equipo;



public class EquiposLFPersistencia {
	

	private AccesoBBDD acceso;

	public EquiposLFPersistencia() {
		acceso = new AccesoBBDD();
	}

	public int insertEquipo(Equipo eq) {

		String query = "INSERT INTO EQUIPOS (NOMBRE, SIGLAS, COD_CIUDAD, VECES_CAMPEON, VECES_SUBCAMPEON, ULTIMO_ANIO)"
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		Connection con = null;
		PreparedStatement pstmt = null;
		int res = 0;

		try {
			con = acceso.getConnection();

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, eq.getNombre());
			pstmt.setString(2, eq.getSiglas());
			pstmt.setInt(3, eq.getCiudad());
			pstmt.setInt(4, eq.getVeces_campeon());
			pstmt.setInt(5, eq.getVeces_subcampeon());
			pstmt.setInt(6, eq.getUltimo_anio());

			res = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			res = -2;
			// e.printStackTrace();
		} catch (SQLException e) {
			res = -1;
			// e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	public int updateEquipo(Equipo eq) {

		int res = 0;

		String query = "UPDATE EQUIPOS SET VECES_CAMPEON = ?, VECES_SUBCAMPEON = ?, ULTIMO_ANIO = ? WHERE NOMBRE = ?  COLLATE NOCASE";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = acceso.getConnection();
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, eq.getVeces_campeon());
			pstmt.setInt(2, eq.getVeces_subcampeon());
			pstmt.setInt(3, eq.getUltimo_anio());
			pstmt.setString(4, eq.getNombre());

			res = pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			res = -1;
			e.printStackTrace();

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;

	}

	public int insertCiudad(String ciudad) {

		String query = "INSERT INTO CIUDADES (NOMBRE) VALUES (?)";

		Connection con = null;
		PreparedStatement pstmt = null;
		int res = 0;

		try {
			con = acceso.getConnection();
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, ciudad);

			res = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			res = -2;
			// e.printStackTrace();
		} catch (SQLException e) {
			res = -1;
			// e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	//
	public Equipo selectEquipoGanador() {

		Equipo equipoGanador = null;
		String query = "SELECT * FROM EQUIPOS ORDER BY ULTIMO_ANIO DESC LIMIT 1";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;

		try {
			con = acceso.getConnection();

			pstmt = con.prepareStatement(query);

			rslt = pstmt.executeQuery();

			while (rslt.next()) {
				equipoGanador = new Equipo(rslt.getInt(1), rslt.getString(2), rslt.getString(3), rslt.getInt(4),
						rslt.getInt(5), rslt.getInt(6), rslt.getInt(7));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rslt != null)
					rslt.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return equipoGanador;
	}

	public ArrayList<Equipo> consultarEquipos() {

		ArrayList<Equipo> listaEquipos = new ArrayList<Equipo>();

		String query = "SELECT * FROM EQUIPOS";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;

		Equipo eq = null;

		try {
			con = acceso.getConnection();
			pstmt = con.prepareStatement(query);
			rslt = pstmt.executeQuery();

			while (rslt.next()) {
				eq = new Equipo(rslt.getInt(1), rslt.getString(2), rslt.getString(3), rslt.getInt(4), rslt.getInt(5),
						rslt.getInt(6), rslt.getInt(7));
				listaEquipos.add(eq);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//CERRAR CONEXIÓN
			try {
				if (rslt != null)
					rslt.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listaEquipos;

	}
	
	//COMPROBAR QUE EL NOMBRE DEL EQUIPO YA EXISTE EN EL REGISTRO
	public int comprobarNombreEqu(String nombre) {

		int res = 0;

		String query = "SELECT * FROM EQUIPOS WHERE NOMBRE = ? ";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;

		try {
			con = acceso.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "" + nombre + "");
			rslt = pstmt.executeQuery();

			if (rslt.next()) {
				res = 1;
			}

		} catch (ClassNotFoundException e) {
			res = -1;
			e.printStackTrace();

		} catch (SQLException e) {
			res = -1;
			e.printStackTrace();

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;

	}

	public int comprobarCiudad(String ciudad) {
		int codCiudad = 0;

		String query = "SELECT CODIGO FROM CIUDADES WHERE NOMBRE = ?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;

		try {
			con = acceso.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, ciudad);
			rslt = pstmt.executeQuery();

			while (rslt.next()) {
				codCiudad = rslt.getInt(1);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (rslt != null)
					pstmt.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return codCiudad;
	}

	
	//LISTADO DE CONSULTAR CIUDAD

	public String consultaEqCiudad() {
		String res = "";
		String query = "SELECT E.NOMBRE, C.NOMBRE FROM EQUIPOS E, CIUDADES C WHERE E.COD_CIUDAD == C.CODIGO";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;

		try {
			con = acceso.getConnection();
			pstmt = con.prepareStatement(query);
			rslt = pstmt.executeQuery();

			while (rslt.next()) {
				res = res + rslt.getString(1) + " - " + rslt.getString(2) + "\n";
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (rslt != null)
					rslt.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return res;
	}
	
	// LISTADO DE CONSULTAR EQUIPO
	public ArrayList<Equipo> listaEquiCiudad(String ciudad) {
		
		ArrayList<Equipo> listaEquipoCiudad = new ArrayList<Equipo>();

		String query = "SELECT * FROM EQUIPOS WHERE COD_CIUDAD = (SELECT CODIGO FROM CIUDADES WHERE NOMBRE = ? COLLATE NOCASE)";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;

		Equipo eq = null;

		try {
			con = acceso.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, ciudad);
			rslt = pstmt.executeQuery();

			while (rslt.next()) {
				eq = new Equipo(rslt.getInt(1), rslt.getString(2), rslt.getString(3), rslt.getInt(4), rslt.getInt(5),
						rslt.getInt(6), rslt.getInt(7));
				listaEquipoCiudad.add(eq);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//CERRAR CONEXIÓN
			try {
				if (rslt != null)
					rslt.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listaEquipoCiudad;
	}

}
