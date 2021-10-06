import java.io.BufferedReader;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.FileWriter;

import java.io.IOException;

import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class main {

	static String palabra;

	public static void crearFichero() { // Metodo de creación de fichero

		File fichero = null; // Creo el fichero y lo inicializo a nulo

		JOptionPane.showMessageDialog(null, "Creando Fichero..."); // Se muestra el mensaje de

		// La opción de crear

		try { // Primer try para las excepciones

			fichero = new File("C:\\Users\\Alumno\\Desktop\\ahorcado.txt"); // ruta del fichero

			if (!fichero.exists()) // Comprobación de si el fichero existe

			{

				fichero.createNewFile(); // si no existe, se crea

			} else {

				JOptionPane.showMessageDialog(null, "Cambiando la palabra...");

			}

		} catch (Exception e) { // segundo catch

			e.printStackTrace(); // mensaje de la excepción

		}

	}

	public static void escribirFichero() { // metodo para escribir ficheros

		PrintWriter pw = null; // Se inicializa a nulo

		FileWriter fichero = null;

		palabra = JOptionPane.showInputDialog(null, "Escribe la palabra");

		try {

			fichero = new FileWriter("C:\\Users\\Alumno\\Desktop\\ahorcado.txt"); // Ruta del fichero

			pw = new PrintWriter(fichero); // Se crea un PrintWriter para escribir en el fichero

			pw.println(palabra);

		} catch (Exception e) { // catcha para las excepciones

			e.printStackTrace();

		} finally { // Finally para comprobar si el fichero sigue abierto

			try {

				if (null != fichero) // S i aun esta abierto, se cierra

					fichero.close();

			} catch (Exception e2) {

				e2.printStackTrace();

			}

		}

	}

	public static void leerFichero() throws IOException { // metodo leerFichero capturando la IOException

		File f = null; // Se inicializa todo a nulo

		FileReader fr = null;

		BufferedReader br = null;

		try { // inicio del try

			f = new File("C:\\Users\\Alumno\\Desktop\\ahorcado.txt"); // ruta absoluta del fichero

			fr = new FileReader(f); // Se crea un FileReader y un BufferedReader

			br = new BufferedReader(fr);

			String linea;

			while ((linea = br.readLine()) != null) { // Si la linea no es nula, se lee

				if (linea != null) {// Aqui si la linea no es nula, se mete en la cadena para guardarla

					palabra = linea;

				}

			}

		} catch (FileNotFoundException e) { // catch para la capturar la excepcion FileNotFoundException e

			e.printStackTrace(); // mensaje para la captura de la excepcion

		}

	}

	private static String inicializarLetrasEncontradas(int longitud) {// metodo para buscar las letras

		String letras = "";// Se inicializa la String

		int i;

		for (i = 0; i < longitud; i++) {// se recorre la palabra

			letras = letras + "_";// Se guardan las letras

		}

		return letras;// Se devuelven las letras

	}

	private static int actualizarFallos(String palabraAdivinar, String letra) {// metodo para actualizar los aciertos

		int i;

		int encontrado = 0;// Se inicializa a 0 y se empieza a contar

		for (i = 0; i < palabraAdivinar.length(); i++) {// Se recorre la palabra para buscar si aciertas

			if (palabraAdivinar.charAt(i) == letra.charAt(0)) {// Si la encuentra, actualiza la posicion con la letra

				encontrado++;

			}

		}

		return encontrado;// Se devuelve encontrado

	}

	private static String actualizarLetrasEncontradas(String palabraAdivinar, String letra, String letrasEncontradas) {// metodo

		// para

		// escribir

		// las

		// letras

		// en

		// el

		// juego

		int i;

		String actualizacionLetras = "";

		for (i = 0; i < palabraAdivinar.length(); i++) {

			if (letra.charAt(0) == palabraAdivinar.charAt(i)) {

				actualizacionLetras += letra.charAt(0);

			} else {

				actualizacionLetras += letrasEncontradas.charAt(i);

			}

		}

		return actualizacionLetras;

	}

	private static String dibujarAhorcado(int fallos) {// metodo para el dibujo

		String dibujo = "";

		switch (fallos) {

		case 0:

			dibujo = "_____";

			break;

		case 1:

			dibujo = "|\n|\n|\n_____";

			break;

		case 2:

			dibujo = "|\n|\n|\n|\n|\n_____";

			break;

		case 3:

			dibujo = "____\n|\n|\n|\n|\n|\n_____";

			break;

		case 4:

			dibujo = "____\n|      |\n|\n|\n|\n|\n_____";

			break;

		case 5:

			dibujo = "____\n|      |\n|     O\n|\n|\n|\n_____";

			break;

		case 6:

			dibujo = "____\n|      |\n|     O\n|      |\n|\n|\n_____";

			break;

		case 7:

			dibujo = "____\n|      |\n|     O\n|      |\n|     /\\  \n|\n_____";

			break;

		}

		return dibujo;

	}

	public static void main(String[] args) throws IOException {

		int fallosPermitidos = 7, fallos = 0; // Se inicializan las variables

		int i, encontrados;

		String letra;

		String letrasEncontradas = "";

		String mensaje = "";

		boolean adivinado = false;

		boolean salir = false;

		while (!salir) {

			String[] option = { "Jugar al ahorcado", "Configuración", "Salir" };

			int opcion = JOptionPane.showOptionDialog(null, "Escoge la opción deseada.", "Elije una opción",

					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);

			switch (opcion) {

			case 0:

				palabra = palabra.toLowerCase(); // Se ponen las letras en minuscula

				letrasEncontradas = inicializarLetrasEncontradas(palabra.length());

				do {

					letra = JOptionPane.showInputDialog("Introduce una letra");

					letra = letra.toLowerCase();

					// se comprueba si ha fallado y se actualiza el número de fallos

					encontrados = actualizarFallos(palabra, letra);

					if (encontrados == 0) {

						fallos++;

					}

					mensaje += "La palabra contiene " + encontrados + " " + letra.charAt(0) + " en la palabra";

					// se dibuja el dibujo del ahorcado dependiendo del número de fallos

					mensaje += "\n" + dibujarAhorcado(fallos);

					// se actualiza la información de las letras encontradas y se imprime

					letrasEncontradas = actualizarLetrasEncontradas(palabra, letra, letrasEncontradas);

					mensaje += "\nLetras encontradas: " + letrasEncontradas;

					JOptionPane.showMessageDialog(null, mensaje);

					// se reinicia el mensaje para la siguiente letra

					mensaje = "";

					if (palabra.equals(letrasEncontradas)) { // Si se da la condición de que palabra es igual al numero

						// de letras encontradas se completa

						adivinado = true;

					}

				} while (fallos < fallosPermitidos && adivinado == false);// si el número de fallos es mayor que 7, los

				// permitidos, se finaliza

				if (adivinado == false) {

					JOptionPane.showMessageDialog(null, "Lo siento has agotado los intentos.\nHa finalizado el juego.");
					
				} else {

					JOptionPane.showMessageDialog(null, "¡Enhorabuena! has acertado la palabra");

				}
				
				adivinado=false;
				
				fallos=0;

				break;

			case 1:

				JOptionPane.showMessageDialog(null, "Accediendo a la configuración");// Opcion de configuración con la

				// llamada a los metodos

				crearFichero();

				escribirFichero();

				leerFichero();

				break;

			case 2:

				salir = true; // Si se cumple la condición de salir, se finaliza

				JOptionPane.showMessageDialog(null, "Has elegido salir.");

				break;

			}

		}

	}

}