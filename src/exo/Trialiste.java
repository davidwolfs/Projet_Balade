package exo;

import java.io.Serializable;

public class Trialiste extends VTT implements Serializable {
	private static final long serialVersionUID = 7787170877756499146L;
	private String selle;

	public Trialiste(int supplement, String nom, int NbrPlateau, String selle) {
		super(supplement, nom, NbrPlateau);
		this.selle = selle;
	}

	public Trialiste() {
		super();
	}
	
	public String getSelle() {
		return selle;
	}

	public void setSelle(String selle) {
		this.selle = selle;
	}
}