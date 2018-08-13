package exo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Balade implements Serializable {
	private static final long serialVersionUID = 7787170877756499146L;
	private int iDB;
	private String libelle;
	private String lieuDepart;
	private String dateDepart;
	private double forfaitBalade;
	private double forfaitRemboursement;
	private List<Vehicule> listVehicule = new ArrayList<>();

	public Balade(int iDB, String libelle, String lieuDepart, String dateDepart, double forfaitBalade, double forfaitRemboursement, List<Vehicule> listVehicule) {
		this.iDB = iDB;
		this.libelle = libelle;
		this.lieuDepart = lieuDepart;
		this.dateDepart = dateDepart;
		this.forfaitBalade = forfaitBalade;
		this.forfaitRemboursement = forfaitRemboursement;
		this.listVehicule = listVehicule;
	}
	
	public Balade(int iDB, String libelle, String lieuDepart, String dateDepart, double forfaitBalade, double forfaitRemboursement) {
		this.iDB = iDB;
		this.libelle = libelle;
		this.lieuDepart = lieuDepart;
		this.dateDepart = dateDepart;
		this.forfaitBalade = forfaitBalade;
		this.forfaitRemboursement = forfaitRemboursement;
	}
	
	public Balade(String libelle, String lieuDepart, String dateDepart, double forfaitBalade, double forfaitRemboursement) 
	{
		this.libelle = libelle;
		this.lieuDepart = lieuDepart;
		this.dateDepart = dateDepart;
		this.forfaitBalade = forfaitBalade;
		this.forfaitRemboursement = forfaitRemboursement;
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
	
	public double getForfaitBalade() {
		return forfaitBalade;
	}

	public void setForfaitBalade(double forfaitBalade) {
		this.forfaitBalade = forfaitBalade;
	}

	public double getForfaitRemboursement() {
		return forfaitRemboursement;
	}

	public void setForfaitRemboursement(double forfaitRemboursement) {
		this.forfaitRemboursement = forfaitRemboursement;
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
	
	public void AfficherForfaitCalcul()
	{
		
	}

	@Override
	public String toString() {
		return "Libelle=" + libelle + ", Lieu de départ=" + lieuDepart + ", Date de départ="
				+ dateDepart + ", Forfait balade=" + forfaitBalade + "€" + ", Forfait remboursement=" + forfaitRemboursement + "€";
	}
}