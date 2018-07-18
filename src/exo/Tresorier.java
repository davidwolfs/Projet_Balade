package exo;

import java.util.Date;

public class Tresorier extends Personne {
	private int numCompteCourant;

	public Tresorier(int iD, String nom, String prenom, String dateNaiss, String email, String password,
			int numCompteCourant) {
		super(iD, nom, prenom, dateNaiss, email, password);
		this.numCompteCourant = numCompteCourant;
	}
	
	public Tresorier(int iD, String nom, String prenom, String dateNaiss, String email, String password) {
		super(iD, nom, prenom, dateNaiss, email, password);
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
