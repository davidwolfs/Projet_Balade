package exo;

public class VTT extends Categorie
{
	private int NbrPlateau;

	public VTT(int supplement, String typePneu, String nom, int NbrPlateau) 
	{
		super(supplement, typePneu, nom);
		this.NbrPlateau = NbrPlateau;
	}

	public VTT() 
	{
		super();
	}
	
	public int getNbrPlateau() {
		return NbrPlateau;
	}

	public void setNbrPlateau(int nbrPlateau) {
		NbrPlateau = nbrPlateau;
	}
}
