package exo;

public class Trialiste extends VTT {
	private String selle;

	public Trialiste(int supplement, String typePneu, String nom, int NbrPlateau, String selle) {
		super(supplement, typePneu, nom, NbrPlateau);
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
