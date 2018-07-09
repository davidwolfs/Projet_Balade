package exo;

import java.util.ArrayList;
import java.util.List;

public class Calendrier 
{
	private int ID;
	private List<Balade> listBalade = new ArrayList<>();
	
	public Calendrier(List<Balade> listBalade)
	{
		this.listBalade = listBalade;
	}
	
	public Calendrier()
	{
		
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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
}
