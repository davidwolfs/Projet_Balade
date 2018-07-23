package exo;

import java.util.ArrayList;
import java.util.List;

public class Calendrier 
{
	private int iD;
	private String nomCal;
	private String dateCal;
	private List<Balade> listBalade = new ArrayList<>();
	
	public Calendrier(int iD, String nomCal, String dateCal, List<Balade> listBalade)
	{
		this.iD = iD;
		this.nomCal = nomCal;
		this.dateCal = dateCal;
		this.listBalade = listBalade;
	}
	
	public Calendrier(int iD, String nomCal, String dateCal)
	{
		this.iD = iD;
		this.nomCal = nomCal;
		this.dateCal = dateCal;
	}
	
	public Calendrier(String nomCal, String dateCal)
	{
		this.nomCal = nomCal;
		this.dateCal = dateCal;
	}
	
	public Calendrier(String dateCal)
	{
		this.dateCal = dateCal;
	}
	
	public Calendrier(int iD, String dateCal)
	{
		this.iD = iD;
		this.dateCal = dateCal;
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

	public String getDateCal() {
		return dateCal;
	}

	public void setDateCal(String dateCal) {
		this.dateCal = dateCal;
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
	
	@Override
	public String toString() {
		return "Calendrier [iD=" + iD + ", nomCal=" + nomCal + ", dateCal=" + dateCal + ", listBalade=" + listBalade
				+ "]";
	}
}
