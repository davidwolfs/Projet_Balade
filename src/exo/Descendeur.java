package exo;

import java.io.Serializable;

public class Descendeur extends VTT implements Serializable {
	private static final long serialVersionUID = 7787170877756499146L;
	private String typeSuspension;

	public Descendeur(int supplement, String nom, int NbrPlateau, String typeSuspension) {
		super(supplement, nom, NbrPlateau);
		this.typeSuspension = typeSuspension;
	}
	
	public Descendeur() {
		
	}

	public String getTypeSuspension() {
		return typeSuspension;
	}

	public void setTypeSuspension(String typeSuspension) {
		this.typeSuspension = typeSuspension;
	}
}
