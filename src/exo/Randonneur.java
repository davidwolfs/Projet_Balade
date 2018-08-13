package exo;

import java.io.Serializable;

public class Randonneur extends VTT implements Serializable {
	private static final long serialVersionUID = 7787170877756499146L;
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
