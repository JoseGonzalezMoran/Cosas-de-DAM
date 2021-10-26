import java.io.Serializable;

import javax.swing.JOptionPane;

public class Socio implements Serializable {
	private String id_socio;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String telefono;
	private String fecha_alta;
	
	public Socio(String id,String nombre,String apellidos,String direccion,String telefono,String fecha_alta) {
		this.id_socio=id;
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.direccion=direccion;
		this.telefono=telefono;
		this.fecha_alta=fecha_alta;
	}
	
	public Socio() {
		this.id_socio = JOptionPane.showInputDialog("Escribe el id del socio");
		this.nombre = JOptionPane.showInputDialog("Escribe el nombre del socio");
		this.apellidos = JOptionPane.showInputDialog("Escribe los apellidos del socio");
		this.direccion = JOptionPane.showInputDialog("Escribe la direccion del socio");
		this.telefono = JOptionPane.showInputDialog("Escribe el telefono del socio");
		this.fecha_alta = JOptionPane.showInputDialog("Escribe la fecha de alta del socio");
	}
	
	public String getId_socio() {
		return id_socio;
	}
	public void setId_socio(String id_socio) {
		this.id_socio = id_socio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFecha_alta() {
		return fecha_alta;
	}
	public void setFecha_alta(String fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
	@Override
	public String toString() {
		return "id_socio=" + id_socio + "; nombre=" + nombre + "; apellidos=" + apellidos + "; direccion="
				+ direccion + "; telefono=" + telefono + "; fecha_alta=" + fecha_alta;
	}
}


