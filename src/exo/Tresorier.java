package exo;

import java.util.Date;

public class Tresorier extends Personne {
	private int numCompteCourant;

	public Tresorier(String nom, String prenom, String dateNaiss, String email, String password,
			int numCompteCourant) {
		super(nom, prenom, dateNaiss, email, password);
		this.numCompteCourant = numCompteCourant;
	}
	
	public Tresorier(String nom, String prenom, String dateNaiss, String email, String password) 
	{
		super(nom, prenom, dateNaiss, email, password);
		//this.dateDebut = dateDebut;
	}
	
	public Tresorier() 
	{
		
	}

	public int getNumCompteCourant() {
		return numCompteCourant;
	}

	public void setNumCompteCourant(int numCompteCourant) {
		this.numCompteCourant = numCompteCourant;
	}
}
