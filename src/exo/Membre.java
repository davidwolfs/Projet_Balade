package exo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Membre extends Personne implements Serializable
{
	private static final long serialVersionUID = 7787170877756499146L;
	private static final double initSolde = 0;
	private double solde;
	private List<Categorie> listCategorie;

	public Membre(int iD, String nom, String prenom, String dateNaiss, String email, String password,
			List<Categorie> listCategorie, double initSolde) {
		super(iD, nom, prenom, dateNaiss, email, password);
		this.listCategorie = listCategorie;
	}

	public Membre(int iD, String nom, String prenom, String dateNaiss, String email, String password, double initSolde) 
	{
		this(iD, nom, prenom, dateNaiss, email, password, new ArrayList<Categorie>(), initSolde);
		
	}
	
	public Membre(int iD, String nom, String prenom, String dateNaiss, String email, String password) 
	{
		 
		this(iD, nom, prenom, dateNaiss, email, password, initSolde);
		
	}
	
	public Membre(String nom, String prenom, String dateNaiss, String email, String password, double solde) 
	{
		super(nom, prenom, dateNaiss, email, password);
		this.solde = solde;
	}
	
	public Membre(String nom, String prenom, String dateNaiss, String email, String password) 
	{
		super(nom, prenom, dateNaiss, email, password);
		//this.dateDebut = dateDebut;
	}
	
	public Membre() 
	{
		
	}
	
	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}
	
	public void ajouterSolde(double solde) {
		this.solde += solde;
	}
	public void soustraitSolde(double solde) {
		this.solde -= solde;
	}

	public List<Categorie> getListCategorie() {
		return listCategorie;
	}

	public void setListCategorie(List<Categorie> listCategorie) {
		this.listCategorie = listCategorie;
	}

	public void AjouterCategorie(Categorie categorie)
	{
		listCategorie.add(categorie);
	}
	
	public void PayerCotisation(double solde)
	{
		this.soustraitSolde(solde);
		
	}
	public double CalculerCotisation(double solde)
	{
		return 0.00;
	}
}