package exo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Balade {
	private int iDB;
	private String libelle;
	private String lieuDepart;
	private String dateDepart;
	private double forfait;
	private List<Vehicule> listVehicule = new ArrayList<>();

	public Balade(int iDB, String libelle, String lieuDepart, String dateDepart, double forfait, List<Vehicule> listVehicule) {
		this.iDB = iDB;
		this.libelle = libelle;
		this.lieuDepart = lieuDepart;
		this.dateDepart = dateDepart;
		this.forfait = forfait;
		this.listVehicule = listVehicule;
	}
	
	public Balade(int iDB, String libelle, String lieuDepart, String dateDepart, double forfait) {
		this.iDB = iDB;
		this.libelle = libelle;
		this.lieuDepart = lieuDepart;
		this.dateDepart = dateDepart;
		this.forfait = forfait;
	}
	
	public Balade(String libelle, String lieuDepart, String dateDepart, double forfait) 
	{
		this.libelle = libelle;
		this.lieuDepart = lieuDepart;
		this.dateDepart = dateDepart;
		this.forfait = forfait;
	}

	public Balade() {
	}
	
	public int getiDB() {
		return iDB;
	}

	public void setiDB(int iDB) {
		this.iDB = iDB;
	}

	public String getLieuDepart() {
		return lieuDepart;
	}

	public void setLieuDepart(String lieuDepart) {
		this.lieuDepart = lieuDepart;
	}

	public String getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(String dateDepart) {
		this.dateDepart = dateDepart;
	}

	public double getForfait() {
		return forfait;
	}

	public void setForfait(double forfait) {
		this.forfait = forfait;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Vehicule> getListVehicule() {
		return listVehicule;
	}

	public void setListVehicule(List<Vehicule> listVehicule) {
		this.listVehicule = listVehicule;
	}

	public void AjouterVehicule(Vehicule vehicule)
	{
		listVehicule.add(vehicule);
	}

	@Override
	public String toString() {
		return "Balade [libelle=" + libelle + ", lieuDepart=" + lieuDepart + ", dateDepart="
				+ dateDepart + ", forfait=" + forfait + ", listVehicule=" + listVehicule + "]";
	}
}