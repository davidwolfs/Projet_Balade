package exo;

import java.util.ArrayList;
import java.util.List;

public class Vehicule 
{
	private String immatriculation;
	private int nombrePlaceMembre;
	private int nombrePlaceVelo;
	private Membre chauffeur; 
	private List<Membre> listMembre = new ArrayList<>();

	public Vehicule(String immatriculation, int nombrePlaceMembre,   
			int nombrePlaceVelo, Membre chauffeur, List<Membre> listMembre) 
	{
		this.immatriculation = immatriculation;
		this.nombrePlaceMembre = nombrePlaceMembre;
		this.nombrePlaceVelo = nombrePlaceVelo;
		this.chauffeur = chauffeur;
		this.listMembre = listMembre;
	}
	
	public Vehicule(String immatriculation, int nombrePlaceMembre, int nombrePlaceVelo, Membre chauffeur) {
		this(immatriculation, nombrePlaceMembre, nombrePlaceVelo, chauffeur, new ArrayList<Membre>());
	}
	
	public Vehicule(String immatriculation, int nombrePlaceMembre, int nombrePlaceVelo) {
		this(immatriculation, nombrePlaceMembre, nombrePlaceVelo, null, new ArrayList<Membre>());
	}
	
	public Vehicule() 
	{
		
	}
	
	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public int getNombrePlaceMembre() {
		return nombrePlaceMembre;
	}

	public void setNombrePlaceMembre(int nombrePlaceMembre) {
		this.nombrePlaceMembre -= nombrePlaceMembre;
	}

	public int getNombrePlaceVelo() {
		return nombrePlaceVelo;
	}

	public void setNombrePlaceVelo(int nombrePlaceVelo) {
		this.nombrePlaceVelo -= nombrePlaceVelo;
	}

	public Membre getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(Membre chauffeur) {
		this.chauffeur = chauffeur;
	}

	public List<Membre> getListMembre() {
		return listMembre;
	}

	public void setListMembre(List<Membre> listMembre) {
		this.listMembre = listMembre;
	}

	public void AjouterMembre(Membre membre)
	{
		listMembre.add(membre);
	}

	@Override
	public String toString() {
		return "Vehicule [immatriculation=" + immatriculation + ", nombrePlaceMembre=" + nombrePlaceMembre
				+ ", nombrePlaceVelo=" + nombrePlaceVelo + ", chauffeur=" + chauffeur + ", listMembre=" + listMembre
				+ "]";
	}
}
