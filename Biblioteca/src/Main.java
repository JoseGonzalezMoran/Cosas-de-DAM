import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Main {
	
	//estas CONSTANTES son las opciones del menu principal
	private final static String MENU_0 = "1.Insertar datos";								
	private final static String MENU_1 = "2.Realizar préstamo";
	private final static String MENU_2 = "3.Consultar datos";
	private final static String MENU_3 = "4.Actualizar datos";
	private final static String MENU_4 = "5.Eliminar datos";
	private final static String MENU_5 = "6.Salir";
	
	//Objectos para trabajar con los ficheros
	static File dirDatos;
		static File dirLibros;		
		static File dirSocios;
		static File dirPrestamos;
	
	//En este metodo se comprueba la estructura del proyecto
	static void initDir() throws IOException {
		dirDatos = new File("Datos");
		
		dirLibros = new File("Datos\\Libros.txt");
		
		dirPrestamos = new File("Datos\\Prestamos.txt");
		
		dirSocios = new File("Datos\\Socios.txt");
		
		//Creo estructura de directorios
		if (!dirDatos.isDirectory()) {
			dirDatos.mkdir();
		} 
		
		if (!dirPrestamos.exists()) {
			dirPrestamos.createNewFile();
		}
		
		if (!dirLibros.exists()) {
			dirLibros.createNewFile();			
		}
	
		if(!dirSocios.exists()) {
			dirSocios.createNewFile();
		}
	}
	
	//En este metodo se ejecuta nuestro programa, debemos instertar nuestras
	//clases en el switch - case de este menu
	static void menuPrincipal() {
		
		boolean salir = false;
		
		while (salir == false) {
			
			int opc = JOptionPane.showOptionDialog(null, "Escoge un opción",
					"BIBLIOTECA", 
					JOptionPane.OK_CANCEL_OPTION, 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					new Object[] {MENU_0, MENU_1, MENU_2, MENU_3, MENU_4, MENU_5}, 
					MENU_0);
			
			switch (opc) {
			
				case 0: {
					try {
						Insertar.menuInsertar();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;}
				
			
				
				case 1: {
					RPrestamo.menuRealizarPrestamo();
				
					break;}
				
			
				
				case 2: {
					Consultar.menuConsultar();
					
					break;}
				
				
				
				case 3: {
		//			Actualizar.menuActualizar();
					
					break;}
				
				
				
				case 4: {
					Eliminar.menuEliminar();
					
					break;}
				
			
				case 5:			
				default:{
					JOptionPane.showMessageDialog(null, "ADIOS");
					salir = true;}
			}
		}
		
	}
	
	
	
	public static void main(String[] args) {
		
		try {
			initDir();		
			menuPrincipal();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//END MAIN
}//END CLASS
