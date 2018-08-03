package exo;

public class Randonneur extends VTT {
	private String typeFourche;

	public Randonneur(int supplement, String nom, int NbrPlateau, String typeFourche) {
		super(supplement, nom, NbrPlateau);
		this.typeFourche = typeFourche;
	}
	
	public Randonneur() {
		super();
	}

	public String getTypeFourche() {
		return typeFourche;
	}

	public void setTypeFourche(String typeFourche) {
		this.typeFourche = typeFourche;
	}
}
