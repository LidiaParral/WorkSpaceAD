package dam.ej2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeerFicheroTexto {

	public static void main(String[] args) {

		try (BufferedReader bfr = new BufferedReader(new FileReader(
				"textoEj2.txt"));) {

			String linea;

			while ((linea = bfr.readLine()) != null) {
				linea = linea.replace('e', 'a');
				linea = linea.replace('i', 'a');
				linea = linea.replace('o', 'a');
				linea = linea.replace('u', 'a');

				System.out.println(linea);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
