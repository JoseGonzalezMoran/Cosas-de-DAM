import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Insertar {
	
	public static void crearSocios() {
		
		JOptionPane.showMessageDialog(null, "Has escogido la opción de crear un nuevo socio");
		BufferedWriter bw = null;
		FileWriter fw = null;
		Socio s;
		String id_socio, nombre, apellidos, direccion, telefono, fecha_alta;
		id_socio = JOptionPane.showInputDialog("Escribe el id del socio");
		nombre = JOptionPane.showInputDialog("Escribe el nombre del socio");
		apellidos = JOptionPane.showInputDialog("Escribe los apellidos del socio");
		direccion = JOptionPane.showInputDialog("Escribe la direccion del socio");
		telefono = JOptionPane.showInputDialog("Escribe el telefono del socio");
		fecha_alta = JOptionPane.showInputDialog("Escribe la fecha de alta del socio");
		s = new Socio(id_socio, nombre, apellidos, direccion, telefono, fecha_alta);
		try {
			fw = new FileWriter(Main.dirSocios.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(s.toString() + "\n");
			JOptionPane.showMessageDialog(null, "Se ha agregado con exito el socio");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void crearLibros() {

		JOptionPane.showMessageDialog(null, "Has escogido la opción de crear un nuevo libro");
		BufferedWriter bw = null;
		FileWriter fw = null;
		String libro = JOptionPane.showInputDialog(null, "Escribe el titulo del libro");
		String categoria = JOptionPane.showInputDialog(null, "Escribe la categoria a la que pertenece el libro");
		libro = "Titulo = " + libro + "; Categoria= " + categoria;
		try {
			fw = new FileWriter(Main.dirLibros.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(libro + "\n");
			JOptionPane.showMessageDialog(null, "Se ha agregado con exito el libro");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void menuInsertar() throws IOException {
		
		boolean salir = false;
		while (!salir) {
			String[] option = { "Insertar socio", "Insertar libro" , "Volver al menu principal" };
			int opcion = JOptionPane.showOptionDialog(null, "Escoge la opción deseada.", "Elije una opción",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
			switch (opcion) {
			case 0:
				crearSocios();
				break;
			case 1:
				crearLibros();
				break;
			case 2:
				salir = true;
				JOptionPane.showMessageDialog(null, "Volviendo al menu principal.");
				break;
			}
		}
	}
}
