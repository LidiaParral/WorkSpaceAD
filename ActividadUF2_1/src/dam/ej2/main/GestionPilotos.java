package dam.ej2.main;

import java.util.ArrayList;
import java.util.Scanner;

import dam.ej2.javabeans.Piloto;
import dam.ej2.persistencia.AccesoPilotosDB4O;

public class GestionPilotos {

	static Scanner sc;
	static AccesoPilotosDB4O abdb;

	static final String OPCION_IP = "IP";
	static final String OPCION_MP = "MP";
	static final String OPCION_ME = "ME";
	static final String OPCION_CT = "CT";
	static final String OPCION_CP = "CP";
	static final String OPCION_SALIR = "S";

	public static void main(String[] args) {

		sc = new Scanner(System.in);
		abdb = new AccesoPilotosDB4O();

		String opcion = "";

		while (!opcion.equals(OPCION_SALIR)) {
			opcion = mostrarMenu();

			switch (opcion) {
			case OPCION_IP:
				introducirPiloto();
				break;

			case OPCION_MP:
				modificarPuntos();
				break;

			case OPCION_ME:
				cambiarEscuderia();
				break;

			case OPCION_CT:
				consultarPilotos();
				break;

			case OPCION_CP:
				consultarPilotosEscuderia();
				break;

			case OPCION_SALIR:
				System.out.println("FIN DEL PROGRAMA");
				break;

			default:
				System.out.println("\nLa opción introducida no es una de las indicadas");
				break;
			}
		}

		sc.close();
		abdb.cerrarBBDD();

	}

	private static void consultarPilotosEscuderia() {
		System.out.println("Introduce la escudería: ");
		String escuderia = sc.nextLine();

		ArrayList<Piloto> pilotosEsc = abdb.selectPilotosEsc(escuderia);

		if (pilotosEsc.size() > 0) {
			System.out.println("Pilotos de la escudería: " + escuderia);
			for (Piloto piloto : pilotosEsc) {
				System.out.println(piloto);
			}
		} else {
			System.out.println("No existe ningún piloto en la escudería " + escuderia);
		}
	}

	private static void consultarPilotos() {
		ArrayList<Piloto> pilotos = abdb.selectPilotos();

		if (pilotos.size() > 0) {
			System.out.println("\nListado de pilotos: ");
			for (Piloto piloto : pilotos) {
				System.out.println(piloto);
			}
		} else {
			System.out.println("No existe ningún piloto en la BBDD");
		}
	}

	private static void cambiarEscuderia() {
		System.out.println("Introduce la escudería a modificar: ");
		String escuderia = sc.nextLine();

		System.out.println("Introduce la nueva escudería: ");
		String nuevaEscuderia = sc.nextLine();

		int res = abdb.updateEscuderia(escuderia, nuevaEscuderia);

		if (res == 1) {
			System.out.println("La modificación se ha realizado correctamente");
		} else {
			System.out.println(
					"La modificación no se ha realizado porque no existe ningún escudería con el nombre " + escuderia);
		}
	}

	private static void modificarPuntos() {
		System.out.println("Introduce el nombre del piloto a modificar: ");
		String nombre = sc.nextLine();

		int puntos = solicitarNumero("Introduce el nuevo número de puntos: ");

		int res = abdb.updatePiloto(nombre, puntos);

		if (res == 1) {
			System.out.println("La modificación se ha realizado correctamente");
		} else {
			System.out.println(
					"La modificación no se ha realizado porque no existe ningún piloto con el nombre " + nombre);
		}
	}

	private static void introducirPiloto() {
		Piloto piloto = solicitarDatosPiloto();
		
		int res = abdb.insertarPiloto(piloto);
		if(res == 1) {
			System.out.println("La inserción se ha realizado correctamente");
		} else {
			System.out.println("No se ha insertado porque la información " 
					+ "ya existe en la BBDD");
		}
	}

	private static Piloto solicitarDatosPiloto() {
		int numero = solicitarNumero("Introduce el número: ");
		System.out.println("Introduce el nombre: ");
		String nombre = sc.nextLine();
		System.out.println("Introduce la escudería: ");
		String escuderia = sc.nextLine();
		int puntos = solicitarNumero("Introduce los puntos: ");

		return new Piloto(numero, nombre, escuderia, puntos);
	}

	private static int solicitarNumero(String msg) {
		boolean datoOk = false;
		int num = 0;

		while (!datoOk) {
			try {
				System.out.println(msg);
				num = Integer.parseInt(sc.nextLine());

				if (num <= 0) {
					System.out.println("\nDebes introducir un valor positivo");
				} else {
					datoOk = true;
				}

			} catch (NumberFormatException e) {
				// TODO: handle exception
			}
		}

		return num;
	}

	private static String mostrarMenu() {
		System.out.println("----------------------------------------------------");
		System.out.println("Indica cuál es la operación que deseas realizar: ");
		System.out.println(OPCION_IP + ": para insertar pilotos");
		System.out.println(OPCION_MP + ": para modificar el número de puntos de un piloto");
		System.out.println(OPCION_ME + ": para cambiar pilotos de una escudería a otra");
		System.out.println(OPCION_CT + ": para consultar todos los pilotos");
		System.out.println(OPCION_CP + ": para consultar los pilotos de una escudería");
		System.out.println(OPCION_SALIR + ": para terminar el proceso");
		String resp = sc.nextLine().toUpperCase();
		System.out.println("----------------------------------------------------");

		return resp;
	}
}
