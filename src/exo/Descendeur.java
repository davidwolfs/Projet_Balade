package exo;

public class Descendeur extends VTT {
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
