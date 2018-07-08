package exo;

public class Randonneur extends VTT {
	private String typeFourche;

	public Randonneur(int supplement, String typePneu, String nom, int NbrPlateau, String typeFourche) {
		super(supplement, typePneu, nom, NbrPlateau);
		this.typeFourche = typeFourche;
	}

	public String getTypeFourche() {
		return typeFourche;
	}

	public void setTypeFourche(String typeFourche) {
		this.typeFourche = typeFourche;
	}
}
