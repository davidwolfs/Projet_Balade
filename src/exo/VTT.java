package exo;

import java.io.Serializable;

public class VTT extends Categorie implements Serializable
{
	private static final long serialVersionUID = 7787170877756499146L;
	private int NbrPlateau;

	public VTT(int supplement, String nom, int NbrPlateau) 
	{
		super(supplement, nom);
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
