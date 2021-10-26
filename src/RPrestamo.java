import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class RPrestamo {
	//Variable global para indicar al menu de esta clase que debe salir
	private static boolean salir;

	/*
	 * Este es el menu de esta clase. En este menu se pregunta al usuario 3 opciones
	 * y segun elija una opción o otra se llamrá a un método o a otro y ademas si
	 * algún fichero esta vacio se habisará con que debe escribir algo.
	 */
	public static void menuRealizarPrestamo() {
		int opcN;
		String enunciado;
		boolean algunFVacio = false, FSocioVacio, FLibroVacio;
		File FLibro = Main.dirLibros;
		File FSocio = Main.dirSocios;

		FSocioVacio = comprobarFSocio(FSocio);
		FLibroVacio = comprobarFLibro(FLibro);

		if (FSocioVacio == true || FLibroVacio == true) {
			algunFVacio = true;
		}

		if (algunFVacio == false) {
			enunciado = "¿Como quieres identificarte?";
			do {
				opcN = JOptionPane.showOptionDialog(null, enunciado, "Selector de opciones",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						new Object[] { "Buscar nombre por socio", "Buscar todos", "Volver al menú principal" }, null);

				switch (opcN) {
				case 0:
					buscarNombreSocio();
					break;
				case 1:
					buscarTodosSocioId();
					break;
				case 2:
					break;
				default:
					JOptionPane.showMessageDialog(null, "ERROR! Ese dato no es válido");
					break;
				}
			} while (opcN != 2 && salir == false);
		} else {
			if (FSocioVacio == true && FLibroVacio == true) {
				enunciado = "Ficheros vacíos\nInserta datos en el fichero Socio y en el"
						+ "\nfichero Libro para acceder a este menu.";
				JOptionPane.showMessageDialog(null, enunciado);
			} else {
				if (FSocioVacio == true) {
					enunciado = "Fichero vacío\nInserta datos en el fichero Socio para acceder a " + "este menu.";
					JOptionPane.showMessageDialog(null, enunciado);
				}

				if (FLibroVacio == true) {
					enunciado = "Fichero vacío\nInserta datos en el fichero Libro para acceder a " + "este menu.";
					JOptionPane.showMessageDialog(null, enunciado);
				}
			}
		}
	}

	/*
	 * Este metodo se encarga de pedir al usuario un nombre, luego lo busca en el fichero y si no lo encuentra le dice al
	 * usuario que no lo encontro, si lo encontro una vez se llama a metodo escribirPrestamo y si lo encuentra varias veces
	 * llama al metodo buscarSociosId.
	 * */
	public static void buscarNombreSocio() {
		String nulo, enunciado, linea, palabras[], lineaSocio = "";
		int posIgual, numMismoNombre = 0;
		boolean encontrado = false;
		File FSocio = Main.dirSocios;

		enunciado = "Introduce el nombre del socio:";
		String nombre = JOptionPane.showInputDialog(enunciado);
		nulo = nombre;

		try {
			while (nulo.contentEquals("")) {
				enunciado = "Tienes que escribir un nombre de socio\nIntroduce el nombre del socio:";
				nombre = JOptionPane.showInputDialog(enunciado);
				nulo = nombre;
			}

			ArrayList<String> nombreSociosI = new ArrayList<String>();

			FileReader ficheroR = new FileReader(FSocio);
			BufferedReader mibufferR = new BufferedReader(ficheroR);

			while ((linea = mibufferR.readLine()) != null) {
				palabras = linea.split(";");
				nombre = palabras[1];

				posIgual = nombre.indexOf("=");
				nombre = nombre.substring(posIgual + 1, palabras[1].length());

				while (true) {
					if (nombre.charAt(0) == ' ') {
						nombre = nombre.substring(1, nombre.length());
					} else {
						break;
					}
				}

				while (true) {
					if (nombre.charAt(nombre.length() - 1) == ' ') {
						nombre = nombre.substring(0, nombre.length() - 1);
					} else {
						break;
					}
				}

				if (nombre.contentEquals(nulo)) {
					numMismoNombre++;
					encontrado = true;
					lineaSocio = palabras[0] + "; " + palabras[1] + ";";
				}

				if (encontrado == true) {
					nombreSociosI.add(linea);
					encontrado = false;
				}
			}

			if (numMismoNombre == 0) {
				enunciado = "No se encontro a ningún socio con ese nombre";
				JOptionPane.showMessageDialog(null, enunciado);
			} else if (numMismoNombre == 1) {
				enunciado = "Usuario encontrado";
				JOptionPane.showMessageDialog(null, enunciado);

				escribirPrestamo(lineaSocio);
			} else if (numMismoNombre > 1) {
				lineaSocio = buscarSociosId(nombreSociosI);

				if (lineaSocio.contentEquals("") && salir == false) {
					enunciado = "No se encontro a ningún socio con ese id";
					JOptionPane.showMessageDialog(null, enunciado);
				} else if (salir == false) {
					enunciado = "Usuario encontrado";
					JOptionPane.showMessageDialog(null, enunciado);

					escribirPrestamo(lineaSocio);
				}
			}

			mibufferR.close();
		} catch (java.lang.NullPointerException e) {
		} catch (java.lang.StringIndexOutOfBoundsException e) {
			enunciado = "No se encontro a ningún socio con ese nombre";
			JOptionPane.showMessageDialog(null, enunciado);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error " + e.getMessage(), "WARNING_MESSAGE",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	/*
	 * Con este método se escribe los prestamos en el fichero.
	 * 
	 * Al comienzo, del método se definen las variables.
	 * 
	 * Despues hay un try catch que capura un NullPointerException y un IOException.
	 * Al comienzo del try catch se crea un printWriter y sobrescribe lo que pueda
	 * haber en el fichero. Luego, hay un do while que se repite siempre y cuando no
	 * se haya encontrado el libro y el usuario quiera buscar otro libro. Dentro del
	 * do while hay un if que se entra si el bucle ya se repitio una vez como
	 * minimo. El if buscarLibro obtiene un true o false. Luego hay otra condicion
	 * donde se entra en caso de que la variable buscarLibro sea igual a true. Si lo
	 * es, se pregunta por el libro a buscar y si no escribio nada se pregunta de
	 * nuevo. Luego, el boolean encontrado obtiene el valor que le de la funcion
	 * buscarLibro. Si es true significa que lo encontro y si es false significa que
	 * no lo encontro. Mas adelante en el programa al salir del do while, esta
	 * presente un if se entrará en el if en caso de que se haya encontrado el
	 * libro. Dentro del if se mostrará un mensaje al usuario, la variable libro
	 * obtendra el valor de la primera posicion del arrayList y luego se escribirá
	 * en el fichero prestamo la combinación de socio, libro y un salto de linea
	 */
	public static void escribirPrestamo(String socio) {
		String enunciado, libro = "", nulo;
		boolean encontrado = false, primeraVuelta = false, buscarLibro = true;
		File FPrestamo = Main.dirPrestamos;

		try {
			PrintWriter fw = new PrintWriter(new FileWriter(FPrestamo, true));

			do {
				if (primeraVuelta == true) {
					buscarLibro = buscarOtroLibro();
				}

				if (buscarLibro == true) {
					enunciado = "¿Que libro quieres pedir?";
					libro = JOptionPane.showInputDialog(enunciado);
					nulo = libro;

					while (nulo.contentEquals("")) {
						enunciado = "Tienes que escribir el nombre de un libro\n¿Que libro quieres pedir?";
						libro = JOptionPane.showInputDialog(enunciado);
						nulo = libro;
					}

					encontrado = buscarLibro(libro);
					primeraVuelta = true;
				}
			} while (encontrado == false && buscarLibro == true);

			if (encontrado == true) {
				enunciado = "El libro ha sido encontrado\n\nTome el libro '" + libro + "'"
						+ "\nQue tengas una buena lectura";
				JOptionPane.showMessageDialog(null, enunciado);

				libro = lineaLibro.get(0);
				lineaLibro.clear();

				fw.write(socio + " " + libro + "\n");
			}

			fw.close();
		} catch (java.lang.NullPointerException e) {
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error " + e.getMessage(), "WARNING_MESSAGE",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	//Este es un arrayList global
	private static ArrayList<String> lineaLibro = new ArrayList<String>();

	/*
	 * Esta función busca un libro y si lo encuentra devuelve true y si no devuelve
	 * false. Esta función le llega un parametro que es el libro a buscar.
	 * 
	 * Al principio de la función hay un try catch donde solo se capura el
	 * IOException. Dentro del try catch, se crea el BufferedReader.
	 * 
	 * Luego, hay un while que recorrerá cada linea del fichero. Cada vez que lo
	 * recorra el vector de strings palabras obtendrá todas las strings dividivas
	 * con un punto y coma de la linea del fichero.
	 * 
	 * Luego, la String libroF obtendrá el valor de la primera string que tenga la
	 * variable palabras(hay es donde se guradará el titulo del libro).
	 * 
	 * Despues la variable posIgual obtiene la poción donde este el igual y luego la
	 * String libroF obtiene el valor de libroF desde la posicion posIgual + 1 hasta
	 * el final de la primera cadena del vector palabras en la posición 0.
	 * 
	 * Luego, esta la condicion que se entra en caso de que las variables libro y
	 * libroF sean iguales, al entrar el arrayList añade la primera string del
	 * vector palabras y la variable encontrado cambia a true;
	 */
	public static boolean buscarLibro(String libro) {
		File FLibro = Main.dirLibros;
		String linea, palabras[], libroF;
		int posIgual;
		boolean encontrado = false;

		try {
			FileReader ficheroR = new FileReader(FLibro);
			BufferedReader mibufferR = new BufferedReader(ficheroR);

			while ((linea = mibufferR.readLine()) != null) {
				palabras = linea.split(";");
				libroF = palabras[0];

				posIgual = libroF.indexOf("=");
				libroF = libroF.substring(posIgual + 1, palabras[0].length());

				while (true) {
					if (libroF.charAt(0) == ' ') {
						libroF = libroF.substring(1, libroF.length());
					} else {
						break;
					}
				}

				while (true) {
					if (libroF.charAt(libroF.length() - 1) == ' ') {
						libroF = libroF.substring(0, libroF.length() - 1);
					} else {
						break;
					}
				}

				if (libroF.contentEquals(libro)) {
					lineaLibro.add(palabras[0]);
					encontrado = true;
				}
			}

			mibufferR.close();
		} catch (java.lang.StringIndexOutOfBoundsException e) {
			encontrado = false;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error " + e.getMessage(), "WARNING_MESSAGE",
					JOptionPane.WARNING_MESSAGE);
		}

		return encontrado;
	}

	/*
	 * Función que muestra un menu, donde pregunta si quiere buscar de nuevo un
	 * libro, si responde que si entonces devuelve true y si responde que no
	 * devuelve false
	 */
	public static boolean buscarOtroLibro() {
		int opc;
		boolean buscarOtroLibro = false;
		String enunciado = "Libro no encontrado\n" + "¿Quieres buscar otro libro?";

		opc = JOptionPane.showOptionDialog(null, enunciado, "Selector de opciones", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Si", "No" }, null);
		switch (opc) {
		case 0:
			buscarOtroLibro = true;
			break;
		case 1:
			buscarOtroLibro = false;
			break;
		}

		return buscarOtroLibro;
	}

	/*
	 * A esta funcion se le envia como parametro un arrayList, el arrayList sirve para mostrar al usuario los usuarios 
	 * repetidos, ademas de que el arrayList será donde busquemos el id que nos tecleo el usuario. En caso de no encontralo
	 * la variable lineaSocio no cambiará de valor y tendrá el valor inicial de "" y en caso de encontralo la variable
	 * lineaSocio obtenrá el valor de los dos primeros datos del socio encontrado.
	 * */
	public static String buscarSociosId(ArrayList<String> nombreSociosI) {
		boolean esNumero = true, unAviso = false;
		String lineaSocio = "", nulo, palabras[], idUsuario = "", enunNum = "";
		int posIgual;

		String enunciado = "Encontrados varios usuarios con el mismo nombre"
				+ "\nPor favor escriba el id acorde con el usario que eres" + "\n\nUsuarios con el mismo nombre:";

		for (String s : nombreSociosI) {
			enunciado += "\n" + s;
		}

		try {
			do {
				esNumero = true;

				idUsuario = JOptionPane.showInputDialog(enunciado);
				nulo = idUsuario;

				while (nulo.contentEquals("")) {
					if (unAviso == false) {
						enunciado = "Tienes que escribir un id de socio\n" + enunciado;
						unAviso = true;
					}

					idUsuario = JOptionPane.showInputDialog(enunciado);
					nulo = idUsuario;
				}

				for (int i = 0; i < idUsuario.length(); i++) {
					if (!Character.isDigit(idUsuario.charAt(i))) {
						enunNum = "Introduce un numero para escribir el id";
						esNumero = false;
					}
				}

				if (esNumero == false) {
					JOptionPane.showMessageDialog(null, enunNum);
				}
			} while (esNumero == false);

			for (String s : nombreSociosI) {
				palabras = s.split(";");
				idUsuario = palabras[0];
				
				posIgual = idUsuario.indexOf("=");
				idUsuario = idUsuario.substring(posIgual + 1, palabras[0].length());

				idUsuario = buclesInfinitosId(idUsuario);

				if (idUsuario.contentEquals(nulo)) {
					lineaSocio = palabras[0] + "; " + palabras[1] + ";";
				}
			}

		} catch (java.lang.NullPointerException e) {
		}

		return lineaSocio;
	}

	/*
	 * Esta funcion le llega una String y la devuelve. Al principio hay un bucle
	 * infinito y dentro hay un if y un else en caso de que la primera letra de la
	 * variable libroF sea igual a un espacio entonces entra en el if que cambiará
	 * la variable libroF para obtener la misma cadena quitandole la ultima parte y
	 * si no es igual entonces hay un break para que salga del bucle, en caso de que
	 * ocurra algún error entonces salta al catch, donde la variable encontrado
	 * cambia a false.
	 * 
	 * Despues del bucle infinito hay otro bucle infinito que hace algo similar al
	 * anterior solo que en vez de comprobar si hay un espacio al princio y quitar
	 * ese espacio para ponerselo a la variable libroF hasta que ya no haya espacios
	 * seguidos, lo que hace es comprobar si hay un espacio al final y quitar ese
	 * espacio para ponerselo a la variable libroF hasta que ya no haya espacios
	 * seguidos.
	 */
	public static String buclesInfinitosId(String idUsuario) {
		while (true) {
			if (idUsuario.charAt(0) == ' ') {
				idUsuario = idUsuario.substring(1, idUsuario.length());
			} else {
				break;
			}
		}

		while (true) {
			if (idUsuario.charAt(idUsuario.length() - 1) == ' ') {
				idUsuario = idUsuario.substring(0, idUsuario.length() - 1);
			} else {
				break;
			}
		}
		return idUsuario;
	}

	/*
	 * Este metodo muestra todos los socios. El usuario tendra que elegir el id de un socio luego busca el id si no lo 
	 * encuentra le pregunta si quiere buscarlo de nuevo y si lo encuentra a la función escribirPrestamo
	 * */
	public static void buscarTodosSocioId() {
		boolean repetirBusqueda = false;

		do {
			String nulo, enunciado, linea, palabras[], lineaSocio = "", lineasFS = "", idUsuario = "";
			int posIgual;
			boolean encontrado = false, esNumero = true, unAviso = false;
			File FSocio = Main.dirSocios;

			ArrayList<String> lineasSocioF = new ArrayList<String>();

			try {
				FileReader ficheroR = new FileReader(FSocio);
				BufferedReader mibufferR = new BufferedReader(ficheroR);

				while ((linea = mibufferR.readLine()) != null) {
					lineasFS += linea + "\n";
					lineasSocioF.add(linea);
				}

				do {
					esNumero = true;
					enunciado = "Introduce el id del socio:\n\n" + lineasFS;

					idUsuario = JOptionPane.showInputDialog(enunciado);
					nulo = idUsuario;

					while (nulo.contentEquals("")) {
						if (unAviso == false) {
							enunciado = "Tienes que escribir un id de socio\n" + enunciado;
							unAviso = true;
						}

						idUsuario = JOptionPane.showInputDialog(enunciado);
						nulo = idUsuario;
					}

					for (int i = 0; i < idUsuario.length(); i++) {
						if (!Character.isDigit(idUsuario.charAt(i))) {
							enunciado = "Introduce un numero para escribir el id";
							esNumero = false;
						}
					}

					if (esNumero == false) {
						JOptionPane.showMessageDialog(null, enunciado);
					}
				} while (esNumero == false);

				for (String s : lineasSocioF) {
					palabras = s.split(";");
					idUsuario = palabras[0];

					posIgual = idUsuario.indexOf("=");
					idUsuario = idUsuario.substring(posIgual + 1, palabras[0].length());

					idUsuario = buclesInfinitosId(idUsuario);

					if (idUsuario.contentEquals(nulo)) {
						encontrado = true;
						lineaSocio = palabras[0] + "; " + palabras[1] + ";";
					}
				}

				if (encontrado == false) {
					repetirBusqueda = repetirBusquedaSocios();
				} else if (encontrado == true) {
					enunciado = "Usuario encontrado";
					JOptionPane.showMessageDialog(null, enunciado);
					escribirPrestamo(lineaSocio);
					repetirBusqueda = false;
				}

				mibufferR.close();
			} catch (java.lang.NullPointerException e) {
			} catch (java.lang.StringIndexOutOfBoundsException e) {
				repetirBusqueda = repetirBusquedaSocios();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error " + e.getMessage(), "WARNING_MESSAGE",
						JOptionPane.WARNING_MESSAGE);
			}
		} while (repetirBusqueda == true && salir == false);
	}

	/*
	 * Función que muestra un menu, donde pregunta si quiere buscar de nuevo un
	 * socio, si responde que si entonces devuelve true y si responde que no
	 * devuelve false
	 */
	public static boolean repetirBusquedaSocios() {
		int opc;
		boolean buscarOtroSocio = false;
		String enunciado = "No se encontro a ningún socio con ese id" + "\n¿Quiere buscarlo de nuevo?";

		opc = JOptionPane.showOptionDialog(null, enunciado, "Selector de opciones", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Si", "No" }, null);
		switch (opc) {
		case 0:
			buscarOtroSocio = true;
			break;
		case 1:
			buscarOtroSocio = false;
			break;
		}

		return buscarOtroSocio;
	}

	/*
	 * Función para comprobar si el fichero FLibro esta vacio, si lo esta enviará
	 * true y si no lo esta enviará false
	 */
	public static boolean comprobarFLibro(File FLibro) {
		boolean vacio;

		if (FLibro.length() == 0) {
			vacio = true;
		} else {
			vacio = false;
		}

		return vacio;
	}

	/*
	 * Función para comprobar si el fichero FSocio esta vacio, si lo esta enviará
	 * true y si no lo esta enviará false
	 */
	public static boolean comprobarFSocio(File FSocio) {
		boolean vacio;

		if (FSocio.length() == 0) {
			vacio = true;
		} else {
			vacio = false;
		}

		return vacio;
	}
}