package exo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Balade {
	private int IDB;
	private List<Vehicule> listVehicule = new ArrayList<>();
	private String lieuDepart;
	private String dateDepart;
	private int forfait;
	private String libelle;

	public Balade(int IDB, String lieuDepart, String dateDepart, int forfait,
			String libelle) {
		this.IDB = IDB;
		//this.listVehicule = listVehicule;
		this.lieuDepart = lieuDepart;
		this.dateDepart = dateDepart;
		this.forfait = forfait;
		this.libelle = libelle;
	}

	public Balade() {
	}

	public int getIDB() {
		return IDB;
	}

	public void setIDB(int iDB) {
		IDB = iDB;
	}

	public List<Vehicule> getListVehicule() {
		return listVehicule;
	}

	public void setListVehicule(List<Vehicule> listVehicule) {
		this.listVehicule = listVehicule;
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

	public int getForfait() {
		return forfait;
	}

	public void setForfait(int forfait) {
		this.forfait = forfait;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public void AjouterVehicule(Vehicule vehicule)
	{
		listVehicule.add(vehicule);
	}
}
