package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "TelefonoTalleres")
public class TelefonoTaller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTelTaller;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del xd no puede contener un caracter especial")
	@Pattern(regexp = "[^a-b]+", message = "El nombre del xd no puede contener un número")
	@Column(name = "telTaller",length = 9, nullable = false)
	private String telTaller;

	public TelefonoTaller() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TelefonoTaller(int idTelTaller, String telTaller) {
		super();
		this.idTelTaller = idTelTaller;
		this.telTaller = telTaller;
	}



	public int getIdTelTaller() {
		return idTelTaller;
	}

	public void setIdTelTaller(int idTelTaller) {
		this.idTelTaller = idTelTaller;
	}

	public String getTelTaller() {
		return telTaller;
	}

	public void setTelTaller(String telTaller) {
		this.telTaller = telTaller;
	}

	
	
	
}
