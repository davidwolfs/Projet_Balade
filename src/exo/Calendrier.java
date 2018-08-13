package exo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Calendrier implements Serializable 
{
	private static final long serialVersionUID = 7787170877756499146L;
	private int iD;
	private String nomCal;
	private List<Balade> listBalade = new ArrayList<>();
	
	public Calendrier(int iD, String nomCal, List<Balade> listBalade)
	{
		this.iD = iD;
		this.nomCal = nomCal;
		this.listBalade = listBalade;
	}
	
	public Calendrier(int iD, String nomCal)
	{
		this.iD = iD;
		this.nomCal = nomCal;
	}
	
	public Calendrier(String nomCal)
	{
		this.nomCal = nomCal;
	}
	
	public Calendrier()
	{
		
	}
	
	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public String getNomCal() {
		return nomCal;
	}

	public void setNomCal(String nomCal) {
		this.nomCal = nomCal;
	}
	
	public List<Balade> getListBalade() {
		return listBalade;
	}

	public void setListBalade(List<Balade> listBalade) {
		this.listBalade = listBalade;
	}
	
	public void AjouterBalade(Balade balade)
	{
		listBalade.add(balade);
	}
	
	public void SupprimerBalade(Balade balade)
	{
		listBalade.remove(balade);
	}
	
	@Override
	public String toString() {
		return "Calendrier [iD=" + iD + ", nomCal=" + nomCal + ", listBalade=" + listBalade
				+ "]";
	}
}
