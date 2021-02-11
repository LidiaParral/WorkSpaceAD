package dam.act1.main;

import java.util.ArrayList;
import java.util.Scanner;

import dam.act1.javabeans.Equipo;
import dam.act1.persistencia.EquiposLFPersistencia;



public class GestionEquiposLF {

	static final String OPCION_IE = "IE";
	static final String OPCION_ME = "ME";
	static final String OPCION_CT = "CT";
	static final String OPCION_CE = "CE";
	static final String OPCION_SALIR = "S";

	static Scanner sc;
	static EquiposLFPersistencia persistencia;

	public static void main(String[] args) {

		sc = new Scanner(System.in);
		persistencia = new EquiposLFPersistencia();

		String opcion = "";

		while (!opcion.equals(OPCION_SALIR)) {
			opcion = mostrarMenu();

			switch (opcion) {
			case OPCION_IE:
				insertarEquipo();
				break;

			case OPCION_ME:
				modificarEquipo();
				break;

			case OPCION_CT:
				consultarTodosEquipos();
				break;

			case OPCION_CE:
				consulstarEquipoGanador();
				break;

			case OPCION_SALIR:
				System.out.println("**FIN DEL PROGRAMA**");
				break;

			default:
				System.out.println("\nLa opción introducida no est� entre las indicadas");
				break;
			}

		}

		sc.close();
	}

	private static void consulstarEquipoGanador() {
		Equipo eGanador = persistencia.selectEquipoGanador();

		if (eGanador == null) {
			System.out.println("No se han insertado ningún dato");
		} else {
			System.out.println("El último equipo ganador es: ");
			System.out.println(eGanador);
		}
	}

	private static void consultarTodosEquipos() {
		ArrayList<Equipo> listaEquipos = new ArrayList<Equipo>();

		listaEquipos = persistencia.consultarEquipos();

		if (listaEquipos.isEmpty()) {
			System.out.println("La lista de equipos está vacía.");
		} else {
			System.out.println("LISTA DE EQUIPOS: ");
			for (Equipo equipo : listaEquipos) {
				System.out.println(equipo);
			}
		}
	}

	private static String mostrarMenu() {
		
		System.out.println("Introduzca IE para insertar equipos");
		System.out.println("Introduzca ME para modificar el número de veces del equipo que ha sido campeón, veces que ha sido subcampeón y �ltimo año en que fue campeón.");
		System.out.println("Introduzca CT para consultar todos los equipos");
		System.out.println("Introduzca CE para consultar el equipo que ganó la liga el último año");
		System.out.println("Introduzca S para terminar el proceso");
		String resp = sc.nextLine();
		
		
		return resp;
	}

	private static void modificarEquipo() {
		System.out.println("Nombre: ");
		String nombre = sc.nextLine();

		int compNom = persistencia.comprobarNombreEqu(nombre);

		if (compNom == 0) {
			System.out.println("No existe un equipo con el nombre indicado");

		} else {
			int vecesCampeon = solicitarNumero("Nº de veces campeón: ");
			int vecesSubcampeon = solicitarNumero("Nº de veces subcampeón: ");
			int ultimoAnio = solicitarNumero("Último año: ");

			Equipo e = new Equipo(nombre, vecesCampeon, vecesSubcampeon, ultimoAnio);

			int res = persistencia.updateEquipo(e);

			if (res == 1) {
				System.out.println("Equipo actualizado correctamente");
			} else if (res == 0) {
				System.out.println("No se ha podido realizar la modificación");
			}
		}

	}

	private static void insertarEquipo() {
		System.out.println("Nombre: ");
		String nombre = sc.nextLine();

		int compNom = persistencia.comprobarNombreEqu(nombre);

		if (compNom == 1) {
			System.out.println("Ya existe un equipo con el nombre indicado");

		} else {
			System.out.println("Siglas: ");
			String siglas = sc.nextLine();

			int codCiudad = ingresarCiudad();

			int vecesCampeon = solicitarNumero("Nº de veces campeón: ");
			int vecesSubcampeon = solicitarNumero("Nº de veces subcampeón: ");
			int ultimoAnio = solicitarNumero("Último año: ");

			Equipo e = new Equipo(nombre, siglas, codCiudad, vecesCampeon, vecesSubcampeon, ultimoAnio);

			int res = persistencia.insertEquipo(e);

			if (res == 1) {
				System.out.println("El equipo se ha almacenado correctamente");
			} else if (res == 0) {
				System.out.println("No se ha podido realizar la inserción");
			} else if (res == -1) {
				System.out.println("Ya existe un equipo con el código indicado");
			} else {
				System.out.println("No se ha podido realizar la conexión con la base de datos");
			}
		}
	}

	private static int ingresarCiudad() {
		System.out.println("Ciudad: ");
		String ciudad = sc.nextLine();

		int codigo = 0;
		int consulta = 0;

		// Se comprueba si hay una ciudad con el nombre ingresado y si es as� se
		// devuelve su c�digo
		consulta = persistencia.comprobarCiudad(ciudad);

		// Si ya existe la ciudad se devuelve el codigo
		if (consulta != 0) {
			codigo = consulta;

		} else {
			// Si no existe la ciudad, se inserta, se consulta y se devuelve el c�digo
			int insert = persistencia.insertCiudad(ciudad);
			if (insert == 1) {
				consulta = persistencia.comprobarCiudad(ciudad);
				codigo = consulta;
			}
		}

		return codigo;
	}

	private static int solicitarNumero(String msg) {
		boolean datoOk = false;
		int num = 0;

		while (!datoOk) {
			try {
				System.out.println(msg);
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


}
