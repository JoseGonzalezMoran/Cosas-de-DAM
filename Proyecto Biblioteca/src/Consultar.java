import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Consultar {

	public static List<Socio> listarSocios(File archivo) {

		// Inicializamos las variables
		FileReader fr = null;
		BufferedReader br = null;
		String line = "";
		String[] atributos = null;
		String[] valores = null;
		String[] setters = new String[6];
		int contador;
		Socio socio = null;
		String id;
		List<Socio> lista = null;

		try {
			// Leemos el archivo
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			lista = new ArrayList<Socio>();
			while ((line = br.readLine()) != null) {
				contador = 0;
				// Divido la linea en atributo=valor
				atributos = line.split(";");
				// Recorre todos los atributos de la linea
				for (String s : atributos) {
					// Separa la variable del valor
					valores = s.split("=");
					// Almacenamos los datos que recogemos en un Array y los limpiamos de espacios
					setters[contador] = valores[1].trim();
					contador++;
				}
				// Parseamos el id a un int
			//	id = Integer.parseInt(setters[0]);
				// Setters del Objeto socio
				socio = new Socio(setters[0], setters[1], setters[2], setters[3], setters[4], setters[5]);
				// Añadirmos el socio a la lista
				lista.add(socio);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// Aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (fr != null)
					fr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// Devolvemos la lista de socios
		return lista;
	}

	public static String consultarSocios(File archivo) {

		// Inicializamos las variables
		List<Socio> lista = null;
		String cadena = "";

		// Llamamos a este meto para recoger la lista de todos los socios
		lista = listarSocios(archivo);
		// La recorremos para almacenar los socios en un String y la imprimimos por
		// JOptionPane
		for (Socio s : lista) {
			cadena += s.toString() + "\n";
		}
		JOptionPane.showMessageDialog(null, cadena, "Biblioteca - Consultar - Consulta de socios",
				JOptionPane.INFORMATION_MESSAGE, null);
		// Preguntamos con un Input la busqueda del usuario y la devolvemos
		return JOptionPane.showInputDialog("Escribe el nombre que quieres buscar");
	}

	@SuppressWarnings("null")
	public static void buscarSocios(String nombre, File archivo) {

		// Inicializamos variables
		List<Socio> lista = null;
		List<Socio> busqueda = null;
		String nombreLista = "";
		String cadena = "";

		// Llamamos al metodo que nos devuelve la lista de todos los socios
		lista = listarSocios(archivo);
		busqueda = new ArrayList<Socio>();
		// Comprobamos que la busqueda no sea vacía y recorremos la lista
		if (nombre != null) {
			for (Socio socio : lista) {
				nombreLista = socio.getNombre();
				// Comparamos los 2 parametros y si son iguales añadimos el socio a la lista
				if (nombre.equals(nombreLista)) {
					busqueda.add(socio);
				}
			}
			// Si no hay resultados iguales
			if (busqueda.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No se han encontrado resultados",
						"Biblioteca - Consultar - Búsqueda de socios", JOptionPane.INFORMATION_MESSAGE, null);
			} else {
				// Imprimimos la lista por JOptionPane
				for (Socio s : busqueda) {
					cadena += s.toString() + "\n";
				}
				JOptionPane.showMessageDialog(null, cadena, "Biblioteca - Consultar - Búsqueda de socios",
						JOptionPane.INFORMATION_MESSAGE, null);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Nombre vacío", "Biblioteca - Consultar - Búsqueda de socios",
					JOptionPane.INFORMATION_MESSAGE, null);
		}

	}

	public static int consultarLibros(File archivo) {
		// Inicializamos las variables
		FileReader fr = null;
		BufferedReader br = null;
		String cadena = "";
		String line = "";
		Object[] opciones = { "Buscar por título", "Buscar por categoría" };

		// Leemos el archivo
		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				cadena += line + "\n";
			}
			// Imprimimos por JOptionPane los libros
			JOptionPane.showMessageDialog(null, cadena, "Biblioteca - Consultar - Consulta de libros",
					JOptionPane.INFORMATION_MESSAGE, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// Aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (fr != null)
					fr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// Preguntamos al usuario que tipo de búsqueda quiere hacer
		return JOptionPane.showOptionDialog(null, "Elege una opción", "Biblioteca - Consultar - Búsqueda de libros", 0,
				JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
	}

	public static void buscarLibros(File archivo, String busqueda, int parametro) {
		// Inicializamos las variables
		FileReader fr = null;
		BufferedReader br = null;
		String cadena = "";
		String line = "";
		String[] atributos = null;
		String[] valores = null;

		//Leemos el archivo
		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			// Recorro todas las lineas
			while ((line = br.readLine()) != null) {
				// Divido la linea en atributo=valor
				atributos = line.split(";");
				// Dependiendo del parametro cojo el atributo indicado y separo la variable del valor
				valores = atributos[parametro].split("=");
				// Comparo el dato introducido por el usuario con el de la lista
				if (busqueda.equals(valores[1].trim())) {
					cadena += line + "\n";
				}
			}
			// Imprimo si hay resultados
			if (cadena.length() > 0) {
				JOptionPane.showMessageDialog(null, cadena, "Biblioteca - Consultar - Búsqueda de libros",
						JOptionPane.INFORMATION_MESSAGE, null);
			} else {
				// Aviso de que no hay coincidencias
				JOptionPane.showMessageDialog(null, "No hay resultados", "Biblioteca - Consultar - Búsqueda de libros",
						JOptionPane.INFORMATION_MESSAGE, null);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// Aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (fr != null)
					fr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static String consultarPrestamos(File archivo) {
		// Inicializamos las variables
		FileReader fr = null;
		BufferedReader br = null;
		String cadena = "";
		String line = "";

		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				cadena += line + "\n";
			}
			JOptionPane.showMessageDialog(null, cadena, "Biblioteca - Consultar - Consulta de Préstamos",
					JOptionPane.INFORMATION_MESSAGE, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// Aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (fr != null)
					fr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return JOptionPane.showInputDialog("Escribe el nombre de socio que quieres buscar");
	}

	public static void buscarPrestamos(File archivo, String busqueda) {
		// Inicializamos las variables
				FileReader fr = null;
				BufferedReader br = null;
				String cadena = "";
				String line = "";
				String[] atributos = null;
				String[] valores = null;

				//Leemos el archivo
				try {
					fr = new FileReader(archivo);
					br = new BufferedReader(fr);
					// Recorro todas las lineas
					while ((line = br.readLine()) != null) {
						// Divido la linea en atributo=valor
						atributos = line.split(";");
						// Divido el atributo del valor
						valores = atributos[1].split("=");
						// Comparo el dato introducido por el usuario con el de la lista
						if (busqueda.equals(valores[1].trim())) {
							cadena += line + "\n";
						}
					}
					// Imprimo si hay resultados
					if (cadena.length() > 0) {
						JOptionPane.showMessageDialog(null, cadena, "Biblioteca - Consultar - Búsqueda de préstamos",
								JOptionPane.INFORMATION_MESSAGE, null);
					} else {
						// Aviso de que no hay coincidencias
						JOptionPane.showMessageDialog(null, "No hay resultados", "Biblioteca - Consultar - Búsqueda de libros",
								JOptionPane.INFORMATION_MESSAGE, null);
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						// Aprovechamos el finally para
						// asegurarnos que se cierra el fichero.
						if (fr != null)
							fr.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
	}
	
	public static void menuConsultar() {

		// Inicializamos variables
		int opcion = 0;
		Object[] opciones = { "Consultar socio", "Consultar libros", "Consultar préstamos", "Volver al menú" };
		String nombre = "";
		int parametro;
		String busqueda = "";
		String prestamo ="";

		// Bucle del menu para que no se cierra mientras no pulses la tecla volver al
		// menú
		while (opcion == 0 || opcion == 1 || opcion == 2) {

			// Menu donde almacenamos la respuesta que nos devulve el usuario y con esta
			// hacemos un switch
			opcion = JOptionPane.showOptionDialog(null, "Escoge una opción", "Biblioteca - Consultar", opcion,
					JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[3]);

			switch (opcion) {
			case 0:
				// Lee todos los socios registrados y devuelve el nombre del socio para realizar
				// su busqueda
				nombre = consultarSocios(Main.dirSocios);
				// Busca los socios con el nombre que le introduzcas
				buscarSocios(nombre, Main.dirSocios);
				break;
			case 1:
				// Lee todos los libros almacenados y devuelve la respuesta del tipo de
				// busqueda, por titulo o por categoria
				parametro = consultarLibros(Main.dirLibros);
				// Realiza una busqueda con el texto que introduzcas ya sea titulo o categoria
				busqueda = JOptionPane.showInputDialog("Escribe que quieres buscar");
				if (busqueda == null ) {
					JOptionPane.showMessageDialog(null, "Búsqueda vacía", "Biblioteca - Consultar - Búsqueda de libros",
							JOptionPane.INFORMATION_MESSAGE, null);
				} else if (parametro != -1) {
					buscarLibros(Main.dirLibros, busqueda, parametro);
				}
				break;
			case 2:
				// Lee todos los préstamos realizados
				prestamo = consultarPrestamos(Main.dirPrestamos);
				if(prestamo == null) {
					JOptionPane.showMessageDialog(null, "Búsqueda vacía", "Biblioteca - Consultar - Búsqueda de prestamos",
							JOptionPane.INFORMATION_MESSAGE, null);
				} else {					
					buscarPrestamos(Main.dirPrestamos, prestamo);
				}
				break;
			default:
				break;
			}

		}

	}

}