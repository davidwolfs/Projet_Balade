package exo;

import java.util.ArrayList;
import java.util.List;

public class Vehicule {
	private int IDV;
	private String chauffeur;
	private int PlaceLibreMembre;
	private int immatriculation;
	private int placeLibreVelo;
	private List<Membre> listMembre = new ArrayList<>();

	public Vehicule(int IDV, String chauffeur, List<Membre> listMembre, int PlaceLibreMembre, int immatriculation,
			int placeLibreVelo) {
		this.IDV = IDV;
		this.chauffeur = chauffeur;
		this.listMembre = listMembre;
		this.PlaceLibreMembre = PlaceLibreMembre;
		this.immatriculation = immatriculation;
		this.placeLibreVelo = placeLibreVelo;
	}
	
	public Vehicule() {
		
	}

	public int getIDV() {
		return IDV;
	}

	public void setIDV(int iDV) {
		IDV = iDV;
	}

	public List<Membre> getListMembre() {
		return listMembre;
	}

	public void setListMembre(List<Membre> listMembre) {
		this.listMembre = listMembre;
	}

	public String getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(String chauffeur) {
		this.chauffeur = chauffeur;
	}

	public int getPlaceLibreMembre() {
		return PlaceLibreMembre;
	}

	public void setPlaceLibreMembre(int placeLibreMembre) {
		PlaceLibreMembre = placeLibreMembre;
	}

	public int getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(int immatriculation) {
		this.immatriculation = immatriculation;
	}

	public int getPlaceLibreVelo() {
		return placeLibreVelo;
	}

	public void setPlaceLibreVelo(int placeLibreVelo) {
		this.placeLibreVelo = placeLibreVelo;
	}

}
