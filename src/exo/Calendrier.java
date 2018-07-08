package exo;

import java.util.ArrayList;
import java.util.List;

public class Calendrier 
{
	private List<Balade> listBalade = new ArrayList<>();
	
	public Calendrier(List<Balade> listBalade)
	{
		this.listBalade = listBalade;
	}
	
	public void AjouterBalade(Balade balade)
	{
		listBalade.add(balade);
	}
}
