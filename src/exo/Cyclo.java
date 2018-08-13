package exo;

import java.io.Serializable;

public class Cyclo extends Categorie implements Serializable
{
	private static final long serialVersionUID = 7787170877756499146L;
	private int IDCy;
	private String nom;
	
	public Cyclo(int supplement, String nom) 
	{
		super(supplement, nom);
	}
	
	public Cyclo() 
	{
		super();
	}

	public int getIDCy() {
		return IDCy;
	}

	public void setIDCy(int iDCy) {
		IDCy = iDCy;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
