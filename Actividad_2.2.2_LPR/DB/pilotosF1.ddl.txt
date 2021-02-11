
CREATE TABLE "Escuderia" (
	"codigo"	INTEGER NOT NULL,
	"nombre"	TEXT NOT NULL,
	"nacionalidad"	INTEGER NOT NULL,
	PRIMARY KEY("codigo")
);

CREATE TABLE "Piloto" (
	"codigo"	INTEGER NOT NULL,
	"nombre"	TEXT NOT NULL,
	"cod_escuderia"	INTEGER NOT NULL,
	"anio_nac"	INTEGER NOT NULL,
	"numPuntos"	INTEGER DEFAULT 0,
	PRIMARY KEY("codigo"),
	FOREIGN KEY("cod_escuderia") REFERENCES "Escuderia"("codigo")
);