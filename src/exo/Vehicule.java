package exo;

import java.util.ArrayList;
import java.util.List;

public class Vehicule {
	private int iDV;
	private String chauffeur;
	private int nombrePlace;
	private String immatriculation;
	private int nombrePlaceVelo;
	private List<Membre> listMembre = new ArrayList<>();

	public Vehicule(int iDV, String chauffeur, int nombrePlace, String immatriculation,
			int nombrePlaceVelo, List<Membre> listMembre) {
		this.iDV = iDV;
		this.chauffeur = chauffeur;
		this.nombrePlace = nombrePlace;
		this.immatriculation = immatriculation;
		this.nombrePlaceVelo = nombrePlaceVelo;
		this.listMembre = listMembre;
	}
	
	public Vehicule(int iDV, String chauffeur, int nombrePlace, String immatriculation,
			int nombrePlaceVelo) {
		this(iDV, chauffeur, nombrePlace, immatriculation, nombrePlaceVelo, new ArrayList<Membre>());
	}
	
	public int getIDV() {
		return iDV;
	}

	public void setIDV(int iDV) {
		this.iDV = iDV;
	}

	public String getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(String chauffeur) {
		this.chauffeur = chauffeur;
	}

	public int getNombrePlace() {
		return nombrePlace;
	}

	public void setNombrePlace(int nombrePlace) {
		this.nombrePlace = nombrePlace;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public int getNombrePlaceVelo() {
		return nombrePlaceVelo;
	}

	public void setNombrePlaceVelo(int nombrePlaceVelo) {
		this.nombrePlaceVelo = nombrePlaceVelo;
	}
	
	public List<Membre> getListMembre() {
		return listMembre;
	}

	public void setListMembre(List<Membre> listMembre) {
		this.listMembre = listMembre;
	}

	@Override
	public String toString() {
		return "Vehicule [iDV=" + iDV + ", chauffeur=" + chauffeur + ", nombrePlace=" + nombrePlace
				+ ", immatriculation=" + immatriculation + ", nombrePlaceVelo=" + nombrePlaceVelo + ", listMembre="
				+ listMembre + "]";
	}
	
}
