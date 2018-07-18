package exo;

import java.util.ArrayList;
import java.util.List;

public class Calendrier 
{
	private int ID;
	private String dateCal;
	private List<Balade> listBalade = new ArrayList<>();
	
	public Calendrier(String dateCal, List<Balade> listBalade)
	{
		this.dateCal = dateCal;
		this.listBalade = listBalade;
	}
	
	public Calendrier(String dateCal)
	{
		this.dateCal = dateCal;
	}
	
	public Calendrier(int ID, String dateCal)
	{
		this.ID = ID;
		this.dateCal = dateCal;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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
		return "Calendrier [ID=" + ID + ", dateCal=" + dateCal + ", listBalade=" + listBalade + "]";
	}
	
	
}
