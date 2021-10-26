import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Eliminar {
	
	//Mis tres constantes que van a ser las opciones del Menu
	private static final String MENU_0 = "1.Eliminar Socio";
	private static final String MENU_1 = "2.Eliminar libro";
	private final static String MENU_2 = "3.Voler al menu principal";
	
	//Variable que me sirve para guardar cada registro del fichero de SOCIOS en un
	//elemento del ArrayList<String>
	private static ArrayList<String> socios = new ArrayList<String>();
	private static ArrayList<String> libros = new ArrayList<String>();
		
	public static void menuEliminar() {
		
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
				eliminarSocio();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
			
		case 1: 
			try {
				eliminarLibro();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
			
		case 2: 
		default: break;
		
		}
	}
	
	private static void eliminarSocio() throws IOException {
		socios = Actualizar.info(Main.dirSocios);
		
		if (socios != null) {
			// Lee todos los socios registrados y devuelve el nombre del socio para realizar
			// su busqueda
			String nombreSocio = Consultar.consultarSocios(Main.dirSocios);
			// Busca los socios con el nombre que le introduzcas
			Consultar.buscarSocios(nombreSocio, Main.dirSocios);
			if (fragmentarSocio()) {
				Actualizar.confirmarSocio(Main.dirSocios, socios);
			}
		} else {
			JOptionPane.showMessageDialog(null, "No hay datos a eliminar");
		}
	}
	
	private static void eliminarLibro() throws IOException {
		libros = Actualizar.info(Main.dirLibros);
		
		if (libros != null) {	
			Consultar.consultarLibros(Main.dirLibros);
			if (fragmentarLibro()) {
				Actualizar.confirmarLibro(Main.dirLibros, libros);
			}
		} else {
			JOptionPane.showMessageDialog(null, "No hay datos a actualizar");
		}
	}
	
	private static boolean fragmentarSocio() throws IOException {
		String [] fragmentado = null;
		String id = JOptionPane.showInputDialog("Introduce el ID del SOCIO que quieres ELIMINAR");
		 
		if (id != "") {
			for (int i = 0; i < socios.size(); i++) {
				fragmentado = socios.get(i).split(";");
				if (Actualizar.buscar(fragmentado[0], id)) {
					aplicarEliminacionSocio(i);
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
	
	private static void aplicarEliminacionSocio(int index) {		
		//Eliminamos el socio
		socios.remove(index);
	}
	
	private static boolean fragmentarLibro() throws IOException {
		String [] fragmentado = null;
		String titulo = JOptionPane.showInputDialog("Introduce el TITULO del LIBRO que quieres ELIMINAR");
		 
		if (titulo != null) {
			for (int i = 0; i < libros.size(); i++) {
				fragmentado = libros.get(i).split(";");
				if (Actualizar.buscar(fragmentado[0], titulo)) {
					aplicarEliminacionLibro(i);
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
	
	private static void aplicarEliminacionLibro(int index) {		
		//eliminamos el libro 	
		libros.remove(index);
	}
		
}//CLASS ELIMINAR
	

