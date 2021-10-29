import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Main {
	public static void leerFicheroInicial() throws IOException {

		File f = null;
		FileReader fr = null;
		BufferedReader br = null;
		try { // inicio del try
			f = new File("C:\\Users\\Alumno\\Desktop\\Directorio\\ficheroinicial.txt");
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String linea;
			String cadena="";
			while ((linea = br.readLine()) != null) {
				cadena=cadena + linea +"\n";
			}
				JOptionPane.showMessageDialog(null, cadena); // Se imprime por pantalla
		} catch (FileNotFoundException e) { // catch para la capturar la excepcion FileNotFoundException e
			e.printStackTrace(); // mensaje para la captura de la excepcion
		}
	}

	public static void leerFicheroSegundo() throws HeadlessException, IOException {

		File f = null;
		FileReader fr = null;
		BufferedReader br = null;
		try { // inicio del try
			f = new File("C:\\Users\\Alumno\\Desktop\\Directorio\\2rutaopcion1.txt");
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String linea;
			String cadena = "";
			while ((linea = br.readLine()) != null) {
				cadena=cadena + linea +"\n";
			}
			JOptionPane.showInternalMessageDialog(null, cadena); // Se imprime por pantalla
		} catch (FileNotFoundException e) { // catch para la capturar la excepcion FileNotFoundException e
			e.printStackTrace(); // mensaje para la captura de la excepcion
		}
	}

	public static void leerFicheroTercero() throws HeadlessException, IOException {

		File f = null;
		FileReader fr = null;
		BufferedReader br = null;
		try { // inicio del try
			f = new File("C:\\Users\\Alumno\\Desktop\\Directorio\\2rutaopcion2.txt");
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String linea;
			String cadena="";
			while ((linea = br.readLine()) != null) {
				cadena=cadena + linea +"\n";
			}
				JOptionPane.showMessageDialog(null, cadena); // Se imprime por pantalla
		} catch (FileNotFoundException e) { // catch para la capturar la excepcion FileNotFoundException e
			e.printStackTrace(); // mensaje para la captura de la excepcion
		}
	}

	public static void leerFicheroMedio() throws HeadlessException, IOException {

		File f = null;
		FileReader fr = null;
		BufferedReader br = null;
		try { // inicio del try
			f = new File("C:\\Users\\Alumno\\Desktop\\Directorio\\ficheromedio.txt");
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String linea;
			String cadena="";
			while ((linea = br.readLine()) != null){
				cadena=cadena + linea +"\n";
			}
				JOptionPane.showMessageDialog(null, cadena); // Se imprime por pantalla
		} catch (FileNotFoundException e) { // catch para la capturar la excepcion FileNotFoundException e
			e.printStackTrace(); // mensaje para la captura de la excepcion
		}
	}

	public static void leerFicheroCuarto() throws HeadlessException, IOException {

		File f = null;
		FileReader fr = null;
		BufferedReader br = null;
		try { // inicio del try
			f = new File("C:\\Users\\Alumno\\Desktop\\Directorio\\3rutaopcion1.txt");
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String linea;
			String cadena="";
			while ((linea = br.readLine()) != null){
				cadena=cadena + linea +"\n";
			}
				JOptionPane.showMessageDialog(null, cadena); // Se imprime por pantalla
		} catch (FileNotFoundException e) { // catch para la capturar la excepcion FileNotFoundException e
			e.printStackTrace(); // mensaje para la captura de la excepcion
		}
	}

	public static void leerFicheroQuinto() throws HeadlessException, IOException {

		File f = null;
		FileReader fr = null;
		BufferedReader br = null;
		try { // inicio del try
			f = new File("C:\\Users\\Alumno\\Desktop\\Directorio\\3rutaopcion2.txt");
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String linea;
			String cadena="";
			while ((linea = br.readLine()) != null){
				cadena=cadena + linea +"\n";
			}
				JOptionPane.showMessageDialog(null, cadena); // Se imprime por pantalla
		} catch (FileNotFoundException e) { // catch para la capturar la excepcion FileNotFoundException e
			e.printStackTrace(); // mensaje para la captura de la excepcion
		}
	}

	public static void leerFicheroFinal() throws HeadlessException, IOException {

		File f = null;
		FileReader fr = null;
		BufferedReader br = null;
		try { // inicio del try
			f = new File("C:\\Users\\Alumno\\Desktop\\Directorio\\ficherofinal.txt");
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String linea;
			String cadena="";
			while ((linea = br.readLine()) != null){
				cadena=cadena + linea +"\n";
			}
				JOptionPane.showMessageDialog(null, cadena); // Se imprime por pantalla
		} catch (FileNotFoundException e) { // catch para la capturar la excepcion FileNotFoundException e
			e.printStackTrace(); // mensaje para la captura de la excepcion
		}
	}

	public static void leerFicheroSexto() throws HeadlessException, IOException {

		File f = null;
		FileReader fr = null;
		BufferedReader br = null;
		try { // inicio del try
			f = new File("C:\\Users\\Alumno\\Desktop\\Directorio\\4rutaopcion1.txt");
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String linea;
			String cadena="";
			while ((linea = br.readLine()) != null){
				cadena=cadena + linea +"\n";
			}
				JOptionPane.showMessageDialog(null, cadena); // Se imprime por pantalla
		} catch (FileNotFoundException e) { // catch para la capturar la excepcion FileNotFoundException e
			e.printStackTrace(); // mensaje para la captura de la excepcion
		}
	}

	public static void leerFicheroSeptimo() throws HeadlessException, IOException {

		File f = null;
		FileReader fr = null;
		BufferedReader br = null;
		try { // inicio del try
			f = new File("C:\\Users\\Alumno\\Desktop\\Directorio\\4rutaopcion2.txt");
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String linea;
			String cadena="";
			while ((linea = br.readLine()) != null){
				cadena=cadena + linea +"\n";
			}
				JOptionPane.showMessageDialog(null, cadena); // Se imprime por pantalla
		} catch (FileNotFoundException e) { // catch para la capturar la excepcion FileNotFoundException e
			e.printStackTrace(); // mensaje para la captura de la excepcion
		}
	}

	public static void main(String[] args) throws IOException {
		JOptionPane.showMessageDialog(null, "Bienvenido");
		leerFicheroInicial();
		String[] option = { "Ir a jugar al bosque", "Quedarse jugando en el jardin" };
		int opcion = JOptionPane.showOptionDialog(null, "¿Que deberia hacer Timmy?.", "Elije una opción",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
		switch (opcion) {
		case 0:
			leerFicheroSegundo();
			leerFicheroMedio();
			String[] opc = { "Ignorar el sonido", "Investigar el sonido" };
			int opt = JOptionPane.showOptionDialog(null, "¿Que deberia hacer Timmy?", "Elije una opción",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opc, opc[0]);
			switch (opt) {
			case 0:
				leerFicheroCuarto();
				leerFicheroFinal();
				String[] o = { "Saltar al pozo", "Volver a casa" };
				int op = JOptionPane.showOptionDialog(null, "¿Que deberia hacer Timmy?.", "Elije una opción",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, o, o[0]);
				switch (op) {
				case 0:
					leerFicheroSexto();
					break;
				case 1:
					leerFicheroSeptimo();
					break;
				}
				break;
			case 1:
				leerFicheroQuinto();
				break;
			}
			break;
		case 1:
			leerFicheroTercero();
			break;
		}
	}
}
