package dam.ej2.persistencia;

import java.util.ArrayList;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import dam.ej1.javabeans.Autor;
import dam.ej2.javabeans.Piloto;

public class AccesoPilotosDB4O {

	private ObjectContainer db;

	public AccesoPilotosDB4O() {
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "DB4O/pilotosF1.yap");
	}

	public void cerrarBBDD() {
		db.close();
	}

	public int insertarPiloto(Piloto piloto) {
		
		/*
		Piloto pilotoDB = consultarPilotoNombre(piloto.getNombre(), piloto.getNumero());
		if (pilotoDB != null) {
			db.store(piloto);
		}*/
		
		
		int res = 0;
		//Comprobar que la PERSONA no existe ya en la BASE DE DATOS una persona con esos mismos datos.
		ObjectSet<Piloto> osPiloto = db.queryByExample(piloto);
		
		if(!osPiloto.hasNext()) {
			//Antes de insertar la persona, comprobar si existe
			db.store(piloto);
			res = 1;
		}
		
		return res;	
		
	}

	private Piloto consultarPilotoNombre(String nombre, int numero) {
		Piloto pilotoDB = new Piloto();
		pilotoDB.setNombre(nombre);
		pilotoDB.setNumero(numero);
		
		ObjectSet<Piloto> osPilotos = db.queryByExample(pilotoDB);
		
		if (osPilotos.hasNext()) {
			pilotoDB = osPilotos.next();
		}
		
		return pilotoDB;
	}

	private Piloto consultarPiloto(Piloto piloto) {
		Piloto pilotoDB = null;

		ObjectSet<Piloto> osPilotos = db.queryByExample(piloto);

		if (osPilotos.hasNext()) {
			pilotoDB = osPilotos.next();
		}

		return pilotoDB;
	}

	public ArrayList<Piloto> selectPilotos() {
		ArrayList<Piloto> pilotos = new ArrayList<Piloto>();

		ObjectSet<Piloto> osPilotos = db.queryByExample(Piloto.class);
		while (osPilotos.hasNext()) {
			pilotos.add(osPilotos.next());
		}

		return pilotos;
	}

	public int updatePiloto(String nombre, int puntos) {
		ArrayList<Piloto> listaPilotos = selectPilotosNombre(nombre);

		int res = 0;
		if (listaPilotos.size() > 0) {
			res = 1;

			for (Piloto piloto : listaPilotos) {
				piloto.setPuntos(puntos);
				db.store(piloto);
			}
		}

		return res;
	}

	private ArrayList<Piloto> selectPilotosNombre(String nombre) {
		ArrayList<Piloto> pilotos = new ArrayList<Piloto>();
		Piloto pilotoCons = new Piloto();
		pilotoCons.setNombre(nombre);

		ObjectSet<Piloto> osPilotos = db.queryByExample(pilotoCons);
		while (osPilotos.hasNext()) {
			pilotos.add(osPilotos.next());
		}

		return pilotos;
	}

	public int updateEscuderia(String escuderia, String nuevaEscuderia) {
		ArrayList<Piloto> listaPilotos = selectPilotosEsc(escuderia);

		int res = 0;
		if (listaPilotos.size() > 0) {
			res = 1;
			for (Piloto piloto : listaPilotos) {
				piloto.setEscuderia(nuevaEscuderia);
				db.store(piloto);
			}
		}

		return res;
	}

	public ArrayList<Piloto> selectPilotosEsc(String escuderia) {
		ArrayList<Piloto> pilotosEsc = new ArrayList<Piloto>();

		Query query = db.query();
		query.constrain(Piloto.class);
		query.descend("escuderia").constrain(escuderia).like();

		ObjectSet<Piloto> osPilotos = query.execute();

		while (osPilotos.hasNext()) {
			pilotosEsc.add(osPilotos.next());
		}

		return pilotosEsc;
	}

}
