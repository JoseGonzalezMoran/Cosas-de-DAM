import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class Actualizar {
	
	/*
	 * IMPORTANTE
	 * Vamos a utilizar los mismos metodos que utilizamos en otras clases
	 * Por ejemplo la opcion de ACTULIZAR = BUSCAR + INSERTAR.
	 * Por eso vamos a utilizar los metodos 
	 * ---------------consultarSocio(File dirSocios)
	 * ---------------buscarScosio(String nombre, File dirSocios)
	 */
	
	//Mis tres constantes que van a ser las opciones del Menu	
	private final static String MENU_BACK = "Volviendo al menu principal";
	private final static String MENU_0 = "1.Actualizar socio";								
	private final static String MENU_1 = "2.Actualizar libro";
	private final static String MENU_2 = "3.Voler al menu principal";
	
	private final static String OK = "Estas seguro de querer modificar/eliminar este socio?";
	
	//Variable que me sirve para guardar cada registro del fichero de SOCIOS en un
	//elemento del ArrayList<String>
	private static ArrayList<String> socios = new ArrayList<String>();
	private static ArrayList<String> libros = new ArrayList<String>();
	
	//El menu de la opcion de actualizar
	public static void menuActualizar()  {
		
		int opc = JOptionPane.showOptionDialog(null, "Escoge la opción para actualizar",
				"ACTUALIZAR", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				new Object[] {MENU_0, MENU_1, MENU_2}, 
				MENU_0);
		
		
		
		switch (opc) {
		
		case 0:
			try {
				actualizarSocio();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
			
		case 1: 
			try {
				actualizarLibro();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
			
		case 2: 
		default: JOptionPane.showMessageDialog(null, MENU_BACK);
			break;
		
		}
	}
	
	/*
	 * Metodo donde englobo todo el algortimo de actualizar un socio
	 */
	private static void actualizarSocio() throws IOException {
		socios = info(Main.dirSocios);
		
		if (socios != null) {
			// Lee todos los socios registrados y devuelve el nombre del socio para realizar
			// su busqueda
			String nombreSocio = Consultar.consultarSocios(Main.dirSocios);
			// Busca los socios con el nombre que le introduzcas
			Consultar.buscarSocios(nombreSocio, Main.dirSocios);
			
			if (fragmentarSocio()) {
				confirmarSocio(Main.dirSocios, socios);
				nombreSocio = Consultar.consultarSocios(Main.dirSocios);
				// Busca los socios con el nombre que le introduzcas
				Consultar.buscarSocios(nombreSocio, Main.dirSocios);
			}
		} else {
			JOptionPane.showMessageDialog(null, "No hay datos a actualizar");
		}
	}
	private static void actualizarLibro() throws IOException {
		libros = info(Main.dirLibros);
		
		if (libros != null) {			
			Consultar.consultarLibros(Main.dirLibros);
			
			if (fragmentarLibro()) {
				confirmarLibro(Main.dirLibros, libros);			
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "No hay datos a actualizar");
		}
	}
	
	/*
	 * Copio todas las lineas del archivo y las guardo en un 
	 * ArrayList<String> donde cada linea es el registro de un socio
	 */
	public static ArrayList<String> info(File fsocios_libros) throws IOException {
		ArrayList<String> toret = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(fsocios_libros));
		String linea = "";
		
		while ((linea = br.readLine()) != null) {
			toret.add(linea);	
		}	
		br.close();
		return toret;
	}
	
	
	
	/*
	 * Por cada linea del fichero, es decir por cada REGISTRO de socio, hago un SPLIT(;)
	 * Lo que me devuelve un String[] por cada elemento SOCIO/LIBRO del arrayList
	 * De este array solo utilizo la primera posicion fragmentado[0]= "id_socio=X"
	 * Con este el string de fragmentado[0] = "id_socio=1", compruebo en buscar socio si es el socio 
	 * que se quiere actualizar, en el caso de serlo paso el INDEX de ese mismo SOCIO 
	 * al metodo aplicarActualizacion(int INDEX)
	 */
	private static boolean fragmentarSocio() throws IOException {
		String [] fragmentado = null;
		String numID = JOptionPane.showInputDialog("Introduce el ID del SOCIO que quieres actualizar");
		
		if (numID != null) {
			for (int i = 0; i < socios.size(); i++) {
				fragmentado = socios.get(i).split(";");
				if (buscar(fragmentado[0], numID)) {
					aplicarActualizacionSocios(i);
					return true;
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "ID invalido");
			return false;
		}
		JOptionPane.showMessageDialog(null, "Socio o libro NO ENCONRADO");
		return false;
	}	
	private static boolean fragmentarLibro() throws IOException {
		String [] fragmentado = null;
		String titulo = JOptionPane.showInputDialog("Introduce el TITULO del LIBRO que quieres actualizar");
		 
		if (titulo != null) {
			for (int i = 0; i < libros.size(); i++) {
				fragmentado = libros.get(i).split(";");
				if (buscar(fragmentado[0], titulo)) {
					aplicarActualizacionLibros(i);
					return true;
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Titulo no valido");
			return false;
		}
		JOptionPane.showMessageDialog(null, "Socio o libro NO ENCONRADO");
		return false;
	}

	
	/*
	 * Este metodo nos indica si hemos encontrado o no el socio que buscabamos
	 * a través de su ID, como explico en el metodo anterior lo hago atraves de fragmentar
	 * el registro de Socio en un String[], donde la primera posicion String[0] ="id_socio=X"
	 */
	public static boolean buscar(String atributo, String busqueda) throws IOException {				
		//Si el String[0] termina con el numero, es decir el ID que pido por pantalla entonces es el socio que 
		//deseamos actualizar
		if(atributo.endsWith(busqueda)) {
			JOptionPane.showMessageDialog(null, "ENCONTRADO\nPasando a editar/eliminar");
			return true;			
		} else {
			return false;
		}	
	}
	
	/*
	 * Creo un nuevo socio y lo reemplazo en mi ArrayList de socios
	 * 
	 */
	private static void aplicarActualizacionSocios(int index) throws IOException {
		Socio actualizado = new Socio();
		//Con el metodo add(int index, String) reemplazo en la posicion indicada
		//mi elemento 
		socios.add(index, actualizado.toString());			

		//Al utilizar el metodo anterior nos reemplaza el elemento y el que habia 
		//en la posicion inicial es desplazado havia delante, por eso debemos eliminarlo	
		socios.remove(index + 1);
	}
	
	private static void aplicarActualizacionLibros(int index) throws IOException {
		//pedimos los nuevos datos del libro	
		String titulo = JOptionPane.showInputDialog(null, "Escribe el titulo del libro");
		String categoria = JOptionPane.showInputDialog(null, "Escribe la categoria a la que pertenece el libro");
		String libro = "Titulo = " + titulo + "; Categoria= " + categoria;
		//Con el metodo add(int index, String) reemplazo en la posicion indicada
		//mi elemento 
		libros.add(index, libro);	
		
		//Al utilizar el metodo anterior nos reemplaza el elemento y el que habia 
		//en la posicion inicial es desplazado havia delante, por eso debemos eliminarlo
		libros.remove(index +1);
		
	}

	/*
	 * Metodo que elimina el fichero antiguo y crea uno nuevo con la infromacion
	 * actualizada, es decir, escribe el ArrayList, linea a linea 
	 */
	public static void confirmarLibro(File file, ArrayList<String> info) throws IOException {	
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		int op = JOptionPane.showConfirmDialog(null, OK, "CONFIRMACION",JOptionPane.OK_CANCEL_OPTION);
		
		switch (op) {
		case 0:
			for (Iterator iterator = info.iterator(); iterator.hasNext();) {
				String linea = (String) iterator.next();
				bw.write(linea + "\n");				
			}
			JOptionPane.showMessageDialog(null, "Actualizacion/Eliminacion de LIBRO realizada con exito");
			break;
			
		//EN EL CASO QUE CIERRE EL JOPTIONPANE o en el caso de que le pulse NO el resultado es el mismo
		case 1:			
		default:
			JOptionPane.showMessageDialog(null, "Operacion descartada");
			break;
		}		
		bw.close();
	}
	
	/*
	 * Metodo que elimina el fichero antiguo y crea uno nuevo con la infromacion
	 * actualizada, es decir, escribe el ArrayList, linea a linea 
	 */
	public static void confirmarSocio(File file, ArrayList<String> info) throws IOException {	
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		
		int op = JOptionPane.showConfirmDialog(null, OK, "CONFIRMACION",JOptionPane.OK_CANCEL_OPTION);
		
		switch (op) {
		case 0:
			for (Iterator iterator = info.iterator(); iterator.hasNext();) {
				String linea = (String) iterator.next();
				bw.write(linea + "\n");				
			}
			JOptionPane.showMessageDialog(null, "Actualizacion/Eliminacion de SOCIO realizada con exito");
			break;
			
		//EN EL CASO QUE CIERRE EL JOPTIONPANE o en el caso de que le pulse NO el resultado es el mismo
		case 1:			
		default:
			JOptionPane.showMessageDialog(null, "Operacion descartada");
			break;
		}		
		bw.close();
	}
}