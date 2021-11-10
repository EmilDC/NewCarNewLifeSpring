package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Modelos")
public class Modelo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idModelo;
	
	@NotEmpty(message = "Ingrese el modelo")
	@Column(name = "nameModelo", nullable = false, length = 20)
	private String nameModelo;
	
	@ManyToOne
	@JoinColumn(name = "idMarca", nullable = false)
	private Marca marca;

	public Modelo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Modelo(int idModelo, String nameModelo, Marca marca) {
		super();
		this.idModelo = idModelo;
		this.nameModelo = nameModelo;
		this.marca = marca;
	}

	public int getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(int idModelo) {
		this.idModelo = idModelo;
	}

	public String getNameModelo() {
		return nameModelo;
	}

	public void setNameModelo(String nameModelo) {
		this.nameModelo = nameModelo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	


	
	
	
}
