package exo;

public class Categorie {
	private int supplement;
	private String typePneu;
	private String nom;

	public Categorie(int supplement, String typePneu, String nom) {
		this.supplement = supplement;
		this.typePneu = typePneu;
		this.nom = nom;
	}

	public int getSupplement() {
		return supplement;
	}

	public void setSupplement(int supplement) {
		this.supplement = supplement;
	}

	public String getTypePneu() {
		return typePneu;
	}

	public void setTypePneu(String typePneu) {
		this.typePneu = typePneu;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
