package exo;

public class Cyclo extends Categorie
{
	private int IDCy;
	private String typePneu;
	private String nom;
	
	public Cyclo(int supplement, String typePneu, String nom) 
	{
		super(supplement, typePneu, nom);
	}
	
	public Cyclo() 
	{
		super();
	}

	public int getIDCy() {
		return IDCy;
	}

	public void setIDCy(int iDCy) {
		IDCy = iDCy;
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
