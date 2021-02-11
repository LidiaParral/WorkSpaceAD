package dam.act1.javabeans;

public class Equipo{
	
	private int cod_equipo;
	private String nombre;
	private String siglas;
	private int ciudad;
	private int veces_campeon;
	private int veces_subcampeon;
	private int ultimo_anio;
	
	
	public Equipo(int cod_equipo, String nombre, String siglas, int ciudad, int veces_campeon, int veces_subcampeon,
			int ultimo_anio) {
		this.cod_equipo = cod_equipo;
		this.nombre = nombre;
		this.siglas = siglas;
		this.ciudad = ciudad;
		this.veces_campeon = veces_campeon;
		this.veces_subcampeon = veces_subcampeon;
		this.ultimo_anio = ultimo_anio;
	}
	


	public Equipo(String nombre, String siglas, int ciudad, int veces_campeon, int veces_subcampeon,
			int ultimo_anio) {
		this.nombre = nombre;
		this.siglas = siglas;
		this.ciudad = ciudad;
		this.veces_campeon = veces_campeon;
		this.veces_subcampeon = veces_subcampeon;
		this.ultimo_anio = ultimo_anio;
	}

	

	public Equipo(String nombre, int veces_campeon, int veces_subcampeon, int ultimo_anio) {
		super();
		this.nombre = nombre;
		this.veces_campeon = veces_campeon;
		this.veces_subcampeon = veces_subcampeon;
		this.ultimo_anio = ultimo_anio;
	}



	public int getCod_equipo() {
		return cod_equipo;
	}


	public String getNombre() {
		return nombre;
	}


	public String getSiglas() {
		return siglas;
	}


	public int getCiudad() {
		return ciudad;
	}


	public int getVeces_campeon() {
		return veces_campeon;
	}


	public int getVeces_subcampeon() {
		return veces_subcampeon;
	}


	public int getUltimo_anio() {
		return ultimo_anio;
	}


	@Override
	public String toString() {
		return "Equipo - código:" + cod_equipo + ", nombre:" + nombre + ", siglas:" + siglas + ", código ciudad:"
				+ ciudad + ", nº de veces campeón:" + veces_campeon + ", nº de veces subcampeón:" + veces_subcampeon
				+ ", último año:" + ultimo_anio;
	}
	
	
	
	
}
