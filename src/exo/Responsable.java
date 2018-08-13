package exo;

import java.io.Serializable;
import java.util.Date;

public class Responsable extends Personne implements Serializable {
	private static final long serialVersionUID = 7787170877756499146L;
	private String dateDebut;
	private Categorie categorie;
	
	public Responsable(String nom, String prenom, String dateNaiss, String email, String password, Categorie categorie/*,
			Date dateDebut*/) {
		super(nom, prenom, dateNaiss, email, password);
		//this.dateDebut = dateDebut;
	}
	
	public Responsable(String nom, String prenom, String dateNaiss, String email, String password) {
		super(nom, prenom, dateNaiss, email, password);
		//this.dateDebut = dateDebut;
	}
	
	public Responsable() {
		super();
		//this.dateDebut = dateDebut;
	}

	/*public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}*/
	
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
}