package dam.act1.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dam.act1.javabeans.Escuderia;
import dam.act1.javabeans.Piloto;

public class PilotosPersistencia {

	private AccesoBBDD acces;

	public PilotosPersistencia() {
		acces = new AccesoBBDD();
	}

	public int comprobarEsc(String nomEsc) {
		acces = new AccesoBBDD();
		int res = 0;

		String query = "SELECT * FROM ESCUDERIA WHERE NOMBRE = ? COLLATE NOCASE";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;

		try {
			con = acces.getConexion();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "" + nomEsc + "");
			rslt = pstmt.executeQuery();

			if (rslt.next()) {
				res = 1;
			}

		} catch (ClassNotFoundException e) {
			res = -1;

			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	public int insertPiloto(Piloto piloto) {
		int res = 0;

		String query = "INSERT INTO PILOTO (NOMBRE, ANIO_NAC, NUM_PUNTOS)" + "VALUES (?, ?, ?)";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = acces.getConexion();

			pstmt = con.prepareStatement(query);

			pstmt.setString(1, piloto.getNombre());
			// pstmt.setInt(2, piloto.getCodEscu());
			pstmt.setInt(2, piloto.getAnioNac());
			pstmt.setInt(3, piloto.getPuntos());
			res = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			res = -2;
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

	public int insertEscu(Escuderia nomEsc) {

		int res = 0;
		String query = "INSERT INTO ESCUDERIA (NOMBRE, NACIONALIDAD) VALUES  (?, ?)";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = acces.getConexion();
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, nomEsc.getNombre());
			pstmt.setString(2, nomEsc.getNacionalidad());

			res = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			res = -2;

		} catch (SQLException e) {
			res = -1;

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

	public int comprobarNombrePil(String nombre) {
		int res = 0;

		String query = "SELECT * FROM PILOTO WHERE NOMBRE = ? COLLATE NOCASE";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;

		try {
			con = acces.getConexion();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "" + nombre + "");
			rslt = pstmt.executeQuery();

			if (rslt.next()) {
				res = 1;
			}

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

	public int updatePiloto(Piloto piloto) {

		int res = 0;

		String query = "UPDATE PILOTO SET NUM_PUNTOS= ? WHERE NOMBRE = ?  COLLATE NOCASE";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = acces.getConexion();
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, piloto.getPuntos());
			pstmt.setString(2, piloto.getNombre());

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

	public ArrayList<Piloto> selectPilotos() {

		ArrayList<Piloto> listaPiloto = new ArrayList<Piloto>();

		String query = "SELECT * FROM PILOTO";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;

		Piloto piloto = null;

		try {
			con = acces.getConexion();
			pstmt = con.prepareStatement(query);
			rslt = pstmt.executeQuery();

			while (rslt.next()) {

				piloto = new Piloto(rslt.getInt(1), rslt.getString(2), rslt.getInt(3), rslt.getInt(4), rslt.getInt(5));
				listaPiloto.add(piloto);

			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Se cierra la conexi�n
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

		return listaPiloto;
	}

	public ArrayList<Escuderia> selectEscuderia() {

		ArrayList<Escuderia> listaEscu = new ArrayList<Escuderia>();

		String query = "SELECT * FROM ESCUDERIA";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;

		Escuderia escuderia = null;

		try {
			con = acces.getConexion();
			pstmt = con.prepareStatement(query);
			rslt = pstmt.executeQuery();

			while (rslt.next()) {

				escuderia = new Escuderia(rslt.getInt(1), rslt.getString(2), rslt.getString(3));
				listaEscu.add(escuderia);

			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Se cierra la conexión
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

		return listaEscu;
	}

	public Piloto selectmpPiloto() {

		String query = "SELECT * FROM PILOTO ORDER BY NUM_PUNTOS DESC LIMIT 1";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;

		Piloto pilotoMP = null;

		try {
			con = acces.getConexion();
			pstmt = con.prepareStatement(query);
			rslt = pstmt.executeQuery();

			while (rslt.next()) {

				pilotoMP = new Piloto(rslt.getInt(1), rslt.getString(2), rslt.getInt(3), rslt.getInt(4),
						rslt.getInt(5));

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
		return pilotoMP;
	}

	public String selectPE() {

		String res = "";
		String query = "SELECT P.NOMBRE, E.NOMBRE FROM PILOTO P, ESCUDERIA E WHERE P.COD_ESCUU == E.COD_ESCU";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;

		try {
			con = acces.getConexion();
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

	public int updateEsc(Escuderia esc) {
		//
		int res = 0;

		String query = "UPDATE ESCUDERIA SET NOMBRE = ? WHERE CODIGO = ?  COLLATE NOCASE";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = acces.getConexion();
			pstmt = con.prepareStatement(query);

			
			pstmt.setString(1, esc.getNombre());
			pstmt.setInt(2, esc.getCodigo());

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


}
