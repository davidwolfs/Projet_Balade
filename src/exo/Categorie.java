package exo;

public class Categorie {
	private int iD;
	private static final int cotisationAnnuelle = 20;
	private static final int supplement = 5;
	private String typePneu;
	private String nom;
	private Calendrier calendrier;
	private Responsable responsable;

	public Categorie(int iD, String typePneu, String nom, Calendrier calendrier, Responsable responsable) {
		this.iD = iD;
		this.typePneu = typePneu;
		this.nom = nom;
		this.calendrier = calendrier;
		this.responsable = responsable;
	}
	
	public Categorie(String typePneu, String nom) 
	{
		this.typePneu = typePneu;
		this.nom = nom;
	}
	
	public Categorie(int iD, String typePneu, String nom) 
	{
		this.iD = iD;
		this.typePneu = typePneu;
		this.nom = nom;
	}
	
	public Categorie(String nom) {
		this.nom = nom;
	}
	
	public Categorie() {
		
	}
	
	
	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public int getSupplement() {
		return supplement;
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
	
	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

	/*@Override
	public String toString() {
		return "Categorie [supplement=" + supplement + ", typePneu=" + typePneu + ", nom=" + nom + ", calendrier="
				+ calendrier + "]";
	}*/
	
	@Override
	public String toString() {
		return nom;
	}
	
	
	
}
