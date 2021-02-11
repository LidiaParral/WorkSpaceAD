package dam.act1.main;

import java.util.ArrayList;
import java.util.Scanner;

import dam.act1.javabeans.Escuderia;
import dam.act1.javabeans.Piloto;
import dam.act1.persistencia.PilotosPersistencia;

public class GestionPilotos {

	static final String OPCION_IP = "IP";
	static final String OPCION_MP = "MP";
	static final String OPCION_ME = "ME";
	static final String OPCION_CT = "CT";
	static final String OPCION_CE = "CE";
	static final String OPCION_CP = "CP";
	static final String OPCION_SALIR = "S";

	static Scanner sc;
	static PilotosPersistencia pPersistencia;

	public static void main(String[] args) {
		// inicializamos
		sc = new Scanner(System.in);
		pPersistencia = new PilotosPersistencia();

		String opcion = "";

		while (!opcion.equals(OPCION_SALIR)) {
			opcion = mostrarMenu();

			switch (opcion) {
			case OPCION_IP:
				insertarPilotos();
				break;

			case OPCION_MP:
				modificarnpPiloto();
				break;

			case OPCION_ME:
				modificarEscuderia();
				break;

			case OPCION_CT:
				consultarPilotos();
				break;

			case OPCION_CE:
				consultarEscuderias();
				break;

			case OPCION_CP:
				consultarmpPilotos();
				break;

			case OPCION_SALIR:
				System.out.println("***FIN DEL PROGRAMA***");
				break;

			default:
				System.out.println("\nLa opción introducida no es una de las indicadas");
				break;
			}

		}

		sc.close();

	}

	private static void listadoPE() {
		String res = pPersistencia.selectPE();
		System.out.println(res);

	}

	private static void consultarmpPilotos() {
		Piloto pilotoMP = pPersistencia.selectmpPiloto();

		if (pilotoMP == null) {
			System.out.println("Aún no se han añadido datos");
		} else {
			System.out.println("El piloto que tiene más puntos es: ");
			System.out.println(pilotoMP);
		}

	}

	private static void consultarEscuderias() {
		ArrayList<Escuderia> escuderias = new ArrayList<Escuderia>();
		escuderias = pPersistencia.selectEscuderia();

		if (escuderias.isEmpty()) {
			System.out.println("La lista de escuderías está vacía");
		} else {
			System.out.println("****** Lista de Escudería *******");
			for (Escuderia escuderia : escuderias) {
				System.out.println(escuderia);
			}
		}

	}

	private static void consultarPilotos() {
		ArrayList<Piloto> pilotos = new ArrayList<Piloto>();
		pilotos = pPersistencia.selectPilotos();

		if (pilotos.isEmpty()) {
			System.out.println("La lista de pilotos está vacía");
		} else {
			System.out.println("****** Lista de Pilotos *******");
			for (Piloto piloto : pilotos) {
				System.out.println(piloto);
			}
		}

	}

	private static void modificarEscuderia() {

		System.out.println("Introduce el nombre del piloto a modificar: ");
		String nomPiloto = sc.nextLine();

		System.out.println("Nuevo nombre de la escudería: ");
		String nomEscuderia = sc.nextLine();

		Escuderia esc = new Escuderia(nomEscuderia);

		int res = pPersistencia.updateEsc(esc);

		if (res == 1) {
			System.out.println("Equipo actualizado correctamente");
		} else if (res == 0) {
			System.out.println("No se ha podido realizar la modificación");
		}

	}

	private static void modificarnpPiloto() {
		System.out.println("Introduce el nombre del piloto a modificar: ");
		String nombre = sc.nextLine();
		int compNom = pPersistencia.comprobarNombrePil(nombre);

		if (compNom == 0) {
			System.out.println("No existe un piloto con el nombre indicado");

		} else {
			int puntos = solicitarNumero("Introduce el nuevo número de puntos: ");

			Piloto piloto = new Piloto(nombre, puntos);

			int res = pPersistencia.updatePiloto(piloto);

			if (res == 1) {
				System.out.println("La modificación se ha realizado correctamente");
			} else {
				System.out.println(
						"La modificación no se ha realizado porque no existe ningún piloto con el nombre " + nombre);
			}
		}

	}

	private static void insertarPilotos() {
		/*
		 * Si el usuario introduce IP se le deberán pedir los datos de la escudería y
		 * del piloto. Antes de insertar la escudería se deberá comprobar que no existe,
		 * solo habrá que insertarla si no existe.
		 */
		System.out.println("Introduce el nombre del piloto:");
		String nom = sc.nextLine();

		Escuderia escu = ingresarEscu();

		int anioNac = solicitarNumero("Introduce el año de nacimiento:");
		int numPuntos = solicitarNumero("Introduce el número de puntos obtenidos:");

		Piloto piloto = new Piloto(nom, anioNac, numPuntos);

		int res = pPersistencia.insertPiloto(piloto);

		if (res == 1) {
			System.out.println("EL piloto se ha almacenado correctamente");
		} else if (res == 0) {
			System.out.println("No se ha podido realizar la inserción");
		} else if (res == -1) {
			System.out.println("Ya existe un piloto con el código indicado");
		} else {
			System.out.println("No se ha podido realizar la conexión con la base de datos");
		}

	}

	private static Escuderia ingresarEscu() {

		System.out.println("Introduzca  el nombre de la escudería: ");
		String nomEsc = sc.nextLine().toUpperCase();

		System.out.println("Introduce la nacionalidad de la escudería: ");
		String nacEsc = sc.nextLine().toUpperCase();

		Escuderia esc = new Escuderia(nomEsc, nacEsc);

		int res = pPersistencia.insertEscu(esc);

		if (res == 1) {
			System.out.println("Se ha insertado la escudería");
		} else {
			System.out.println("No se ha podido insertar la escudería");
		}
		return esc;

	}

	private static int solicitarNumero(String mensaje) {

		boolean datoOk = false;
		int num = 0;

		while (!datoOk) {
			try {
				System.out.println(mensaje);
				num = Integer.parseInt(sc.nextLine());

				// Comprobar que es positivo
				if (num < 0) {
					System.out.println("Debe introducir un valor positivo");
				} else {
					datoOk = true;
				}

			} catch (NumberFormatException e) {
				System.out.println("Debe introducir un valor entero");
			}

		}
		return num;
	}

	private static String mostrarMenu() {
		System.out.println("Introduzca IP para insertar piloto.");
		System.out.println("Introduzca MP para modificar el número de puntos de un piloto.");
		System.out.println("Introduzca ME para modificar la escudería de un piloto.");
		System.out.println("Introduzca CT para consultar todos los pilotos");
		System.out.println("Introduzca CE para consultar las escuderías");
		System.out.println("Introduzca CP para consultar el piloto que más puntos lleva");
		System.out.println("Introduzca S para terminar el proceso");
		String resp = sc.nextLine();

		return resp;
	}

}