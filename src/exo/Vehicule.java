package exo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Vehicule implements Serializable
{
	private static final long serialVersionUID = 7787170877756499146L;
	private String immatriculation;
	private String marque;
	private String modele;
	private int nombrePlaceMembre;
	private int nombrePlaceVelo;
	private Membre chauffeur; 
	private List<Membre> listMembre = new ArrayList<>();

	public Vehicule(String immatriculation, String marque, String modele, int nombrePlaceMembre,   
			int nombrePlaceVelo, Membre chauffeur, List<Membre> listMembre) 
	{
		this.immatriculation = immatriculation;
		this.marque = marque;
		this.modele = modele;
		this.nombrePlaceMembre = nombrePlaceMembre;
		this.nombrePlaceVelo = nombrePlaceVelo;
		this.chauffeur = chauffeur;
		this.listMembre = listMembre;
	}
	
	public Vehicule(String immatriculation, String marque, String modele, int nombrePlaceMembre, int nombrePlaceVelo, Membre chauffeur) {
		this(immatriculation, marque, modele, nombrePlaceMembre, nombrePlaceVelo, chauffeur, new ArrayList<Membre>());
	}
	
	public Vehicule(String immatriculation, String marque, String modele, int nombrePlaceMembre, int nombrePlaceVelo) {
		this(immatriculation, marque, modele, nombrePlaceMembre, nombrePlaceVelo, null, new ArrayList<Membre>());
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
	
	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
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
	public String toString() 
	{
	/*	if(immatriculation = null)
		{
			
		}*/
		return "Vehicule [immatriculation=" + immatriculation + ", nombrePlaceMembre=" + nombrePlaceMembre
				+ ", nombrePlaceVelo=" + nombrePlaceVelo + ", chauffeur=" + chauffeur + ", listMembre=" + listMembre
				+ "]";
		//return chauffeur + "";
	}
	
	public String AfficherChauffeur() 
	{
		return chauffeur + "";
	}
	
	public String AfficherPassager() 
	{
		return chauffeur + "";
	}
}
