package org.tux.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "personne")
public class Personne {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private Integer id;
	private String prenom ;
	private String nom ;


	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Personne(String prenom, String nom) {
		super();
		this.prenom = prenom;
		this.nom = nom;
	}
	public Personne() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Personne(Integer id, String prenom, String nom) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
	}
	@Override
	public String toString() {
		return "Personne [" + (id != null ? "id=" + id + ", " : "")
				+ (prenom != null ? "prenom=" + prenom + ", " : "")
				+ (nom != null ? "nom=" + nom : "") + "]";
	}
	
	
}
