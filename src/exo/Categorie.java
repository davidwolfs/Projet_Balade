package exo;

import java.io.Serializable;

public class Categorie implements Serializable
{
	private static final long serialVersionUID = 7787170877756499146L;
	private int iD;
	private static final int cotisationAnnuelle = 20;
	private static final int supplement = 5;
	private String nom;
	private Calendrier calendrier;
	private Responsable responsable;

	public Categorie(int iD, String nom, Calendrier calendrier, Responsable responsable) {
		this.iD = iD;
		this.nom = nom;
		this.calendrier = calendrier;
		this.responsable = responsable;
	}
	
	public Categorie(String nom) 
	{
		this.nom = nom;
	}
	
	public Categorie(int iD, String nom) 
	{
		this.iD = iD;
		this.nom = nom;
	}
	
	public Categorie() {
		
	}
	
	
	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public int getSupplement() {
		return supplement;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Calendrier getCalendrier() {
		return calendrier;
	}

	public void setCalendrier(Calendrier calendrier) {
		this.calendrier = calendrier;
	}
	
	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

	/*@Override
	public String toString() {
		return "Categorie [supplement=" + supplement + ", nom=" + nom + ", calendrier="
				+ calendrier + "]";
	}*/
	
	@Override
	public String toString() {
		return nom;
	}
}
