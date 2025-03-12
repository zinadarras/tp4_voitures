package metier.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Voiture implements Serializable{
	@Id
	@Column (name="ID")
	@GeneratedValue (strategy=GenerationType.IDENTITY) 
	private Long id;
	private String marque;
	private String modele;
	private double prix;
	
	public Voiture() {
	super();
	}
	public Voiture(String marque, String modele,double prix) {
	super();
	this.marque = marque;
	this.modele=modele;
	this.prix = prix;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	@Override
	public String toString() {
		return "Voiture [id=" + id + ", marque=" + marque + ", modele=" + modele + ", prix=" + prix + "]";
	}
	
}
