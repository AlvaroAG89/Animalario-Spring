package com.Animalario.springboot.datos.app.models.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;






/**
 * Contiene informacion de las jaulas
 * @author Alvaro
 * @version 1.1
 * @since 1.0
 */

@Entity
@Table(name="Especie")
public class Jaula implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="Codigo_Jaula")
	private String jaula;
	
	
	@Column(name="Nombres")
	@NotEmpty
	private String nombres;
	
	
	@Column(name="Laboratorio")
	@NotEmpty
	private String laboratorio;
	

	@NotEmpty
	@Column(name="Responsable")
	private String responsable;
	
	
	@Column(name="Especie")
	@NotEmpty
	private String especie;
	
	
	@NotEmpty
	@Column(name="Cepa")
	private String cepa;
	
	@NotEmpty
	@Column(name="Tipo_Rack")
	private String tipo_jaula;
	
	@NotNull
	@Column(name="Ratas_Hembra")
	private int numRatasHembras;
	

	@NotNull
	@Column(name="Ratas_Macho")
	private int numRatasMacho;	
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="Fecha_Nacimiento")
	private Date fechaNacimiento;
	
	@NotEmpty
	@Column(name="Procedimiento")
	private String procedimiento;


	@Column(name="Observaciones")
	private String observaciones;
	

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="Fecha_Reserva")
	private Date fecha_reserva;
	
	
	
	//cuando se prepare la inserccion a la BD se cargara automaticamente
	@PrePersist 
	public void prePersist() {
		if(fecha_reserva==null) {
			fecha_reserva = new Date();
		}
	}
	
	
	
	
	
	public String getJaula() {
		return jaula;
	}



	public void setJaula(String jaula) {
		this.jaula = jaula;
	}



	public String getNombres() {
		return nombres;
	}



	public void setNombres(String nombres) {
		this.nombres = nombres;
	}



	public String getLaboratorio() {
		return laboratorio;
	}



	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}



	public String getResponsable() {
		return responsable;
	}



	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}



	public String getEspecie() {
		return especie;
	}



	public void setEspecie(String especie) {
		this.especie = especie;
	}



	public String getCepa() {
		return cepa;
	}



	public void setCepa(String cepa) {
		this.cepa = cepa;
	}



	public String getTipo_jaula() {
		return tipo_jaula;
	}



	public void setTipo_jaula(String tipo_jaula) {
		this.tipo_jaula = tipo_jaula;
	}



	public int getNumRatasHembras() {
		return numRatasHembras;
	}



	public void setNumRatasHembras(int numRatasHembras) {
		this.numRatasHembras = numRatasHembras;
	}



	public int getNumRatasMacho() {
		return numRatasMacho;
	}



	public void setNumRatasMacho(int numRatasMacho) {
		this.numRatasMacho = numRatasMacho;
	}



	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}



	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}



	public String getProcedimiento() {
		return procedimiento;
	}



	public void setProcedimiento(String procedimiento) {
		this.procedimiento = procedimiento;
	}



	public String getObservaciones() {
		return observaciones;
	}



	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public Date getFecha_reserva() {
		return fecha_reserva;
	}



	public void setFecha_reserva() {

	}


	public Jaula() { //necesario para declarar objetos de esta clase y llamar a metodos de esta clase
		super();
	}

	@Override
	public String toString() {
		return "Jaula [jaula=" + jaula + ", nombres=" + nombres + ", laboratorio=" + laboratorio + ", responsable="
				+ responsable + ", especie=" + especie + ", cepa=" + cepa + ", tipo_jaula=" + tipo_jaula
				+ ", numRatasHembras=" + numRatasHembras + ", numRatasMacho=" + numRatasMacho + ", fechaNacimiento="
				+ fechaNacimiento + ", procedimiento=" + procedimiento + ", observaciones=" + observaciones
				+ ", fecha_reserva=" + fecha_reserva + "]";
	}
	
	
	public String Salida(Jaula miJaula,String orden) {
		int total = numRatasHembras + numRatasMacho;
		Date fecha = new Date();	//Fecha sistema
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		String fecha_actual =  df.format(fecha);
		
		return orden + " de la Jaula " + miJaula.getJaula() + ". Reservada por " + miJaula.getLaboratorio() + ". El responsable es " + miJaula.getResponsable() +
		" y reservo " + total + " de ratas -- " + fecha_actual + ".";
	}
	/*

	public static void GuardarHistorico(Jaula objJaula,String orden) {

		// Creamos el documento
		XWPFDocument documento = null;
		//Si el documento no existe, lo creamos con cabecera
		File f = new File ("Historico.docx");
		if(!f.exists()) {
			 documento = new XWPFDocument();
			// Creamos el titulo y le asignamos algunas propiedades
			XWPFParagraph titulo_doc = documento.createParagraph();
			titulo_doc.setAlignment(ParagraphAlignment.LEFT);
			// Alineaci�n horizontal y vertical
			titulo_doc.setVerticalAlignment(TextAlignment.TOP);
			
			// Agregamos el titulo
			XWPFRun titu = titulo_doc.createRun();
			titu.setBold(true); // Negrita
			titu.setText("Historial de Racks");
			titu.setColor("000099"); // Color texto en RGB
			titu.setFontFamily("Bookman Old Style"); // Tipo de fuente
			titu.setFontSize(18); // Tama�o
			titu.addBreak(); // Salto de l�nea
		
			//Si el documento no existe, abrimos el existente y almacenamos lo que ya tenemos en documento
		}else {
			FileInputStream fis;
			try {
				fis = new FileInputStream ( "Historico.docx" );
				documento = new XWPFDocument (OPCPackage.open (fis));				
			} catch (InvalidFormatException | IOException e) {
				e.printStackTrace();
			}
		}
				
		// Creamos el parrafo 1 y le asignamos algunas propiedades
		XWPFParagraph parrafo1 = documento.createParagraph();
		parrafo1.setAlignment(ParagraphAlignment.RIGHT); 
		parrafo1.setFirstLineIndent(400);	//1inea tabulada
		parrafo1.setWordWrapped(true);
		
		// Agregamos el texto 1
		XWPFRun t1 = parrafo1.createRun();
		t1.setText("\n" + objJaula.Salida(objJaula, orden));
		t1.setColor("000000");
		t1.setFontSize(12);
		t1.addCarriageReturn();
		
		
		//String ruta = System.getProperty("user.home") + "/Documents/prueba.docx";
		String ruta = "Historico.docx";
		try {
			FileOutputStream word = new FileOutputStream(ruta);
			documento.write(word);
			word.close();
			documento.close();
		//	File archivo = new File(ruta);
		//	Desktop.getDesktop().open(archivo);
		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {
		}
	} 
	
	
	*/
	
}
