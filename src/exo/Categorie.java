package exo;

public class Categorie {
	private int IdCat;
	private int supplement;
	private String typePneu;
	private String nom;
	private Calendrier calendrier;

	public Categorie(int IdCat, int supplement, String typePneu, String nom) 
	{
		this.IdCat = IdCat;
		this.supplement = supplement;
		this.typePneu = typePneu;
		this.nom = nom;
	}
	
	public Categorie(int supplement, String typePneu, String nom, Calendrier calendrier) {
		this.supplement = supplement;
		this.typePneu = typePneu;
		this.nom = nom;
		this.calendrier = calendrier;
	}
	
	public Categorie(int supplement, String typePneu, String nom) {
		this.supplement = supplement;
		this.typePneu = typePneu;
		this.nom = nom;
	}
	
	public Categorie() {
		
	}
	
	public int getIdCat() {
		return IdCat;
	}

	public void setIdCat(int idCat) {
		IdCat = idCat;
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

	public Calendrier getCalendrier() {
		return calendrier;
	}

	public void setCalendrier(Calendrier calendrier) {
		this.calendrier = calendrier;
	}

	@Override
	public String toString() {
		return "Categorie [IdCat=" + IdCat + ", supplement=" + supplement + ", typePneu=" + typePneu + ", nom=" + nom
				+ ", calendrier=" + calendrier + "]";
	}
	
	
	
}
