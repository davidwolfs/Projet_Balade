package exo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Membre extends Personne 
{
	private double solde;
	private List<Categorie> listCategorie = new ArrayList<>();
	

	public Membre(int iD, String nom, String prenom, Date dateNaiss, String email, String password,
			List<Categorie> listCategorie, double solde) {
		super(iD, nom, prenom, dateNaiss, email, password);
		this.listCategorie = listCategorie;
		this.solde = solde;
	}

	public Membre(int iD, String nom, String prenom, Date dateNaiss, String email, String password, double solde) 
	{
		super(iD, nom, prenom, dateNaiss, email, password);
		this.solde = solde;
	}
	
	public Membre(int iD, String nom, String prenom, Date dateNaiss, String email, String password) 
	{
		super(iD, nom, prenom, dateNaiss, email, password);
	}

	
	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public List<Categorie> getListCategorie() {
		return listCategorie;
	}

	public void setListCategorie(List<Categorie> listCategorie) {
		this.listCategorie = listCategorie;
	}
}
