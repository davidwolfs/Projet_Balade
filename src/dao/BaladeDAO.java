package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.BaladeDAO;
import exo.Balade;
import exo.Calendrier;
import exo.Categorie;
import exo.Membre;
import exo.Responsable;
import exo.Vehicule;

public class BaladeDAO extends DAO<Balade> 
{
	public BaladeDAO(Connection conn) 
	{
		super(conn);
	}
	
	public boolean create(Balade obj){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Balade (IDB, libelleB, lieuDepart, dateDepart, forfaitBalade, forfaitRemboursement) VALUES ('" + obj.getiDB() + "','" + obj.getLibelle() + "','" + obj.getLieuDepart() + "','" + obj.getDateDepart() + "','" + obj.getForfaitBalade() + "','" + obj.getForfaitRemboursement() + "')" + ";";
			for(int i = 0; i < obj.getListVehicule().size() ; i++)
			{
				//String query2 = "INSERT INTO Ligne_Balade (ID, IDB, IDV, IDM) VALUES ('" + 1 + "','" + obj.getIDB() + "','" + obj.getListVehicule().get(i).getIDV() + "','" + obj."
			}
			System.out.println(query);
			statementResult = true;
			statementResult = statement.execute(query);
		} catch (SQLException e) {
			statementResult = false;
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println(statementResult);
		return statementResult;
	}
	
	public boolean create_Ligne_Balade_Membre(Balade balade, Vehicule vehicule, Membre membre)
	{
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Ligne_Balade (IDB, Immatriculation, IDM, type, paye) VALUES (" + balade.getiDB() + "," + "\"" + vehicule.getImmatriculation() + "\"" + "," + membre.getiD() + "," + "\"membre\"" + "," + "'" + 1 + "'" + ");";
			for(int i = 0; i < balade.getListVehicule().size() ; i++)
			{
				//String query2 = "INSERT INTO Liste_Balade (ID, IDB, IDV, IDM) VALUES ('" + 1 + "','" + obj.getIDB() + "','" + obj.getListVehicule().get(i).getIDV() + "','" + obj."
			}
			System.out.println(query);
			statementResult = true;
			statementResult = statement.execute(query);
		} catch (SQLException e) {
			statementResult = false;
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println(statementResult);
		return statementResult;
	}
	
	public boolean create_Ligne_Balade_Velo(Balade balade, Vehicule vehicule, Membre membre)
	{
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Ligne_Balade (IDB, Immatriculation, IDM, type) VALUES (" + balade.getiDB() + "," + "\"" + vehicule.getImmatriculation() + "\"" + "," + membre.getiD() + "," + "\"velo\"" + ");";
			for(int i = 0; i < balade.getListVehicule().size() ; i++)
			{
				//String query2 = "INSERT INTO Liste_Balade (ID, IDB, IDV, IDM) VALUES ('" + 1 + "','" + obj.getIDB() + "','" + obj.getListVehicule().get(i).getIDV() + "','" + obj."
			}
			System.out.println(query);
			statementResult = true;
			statementResult = statement.execute(query);
		} catch (SQLException e) {
			statementResult = false;
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println(statementResult);
		return statementResult;
	}
	
	public boolean create_Ligne_Balade_SansMembre(Balade balade, Vehicule vehicule)
	{
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Ligne_Balade (IDB, Immatriculation, IDM) VALUES (" + balade.getiDB() + "," + "\"" + vehicule.getImmatriculation() + "\"" + "," + 100 + ");";
			for(int i = 0; i < balade.getListVehicule().size() ; i++)
			{
				//String query2 = "INSERT INTO Liste_Balade (ID, IDB, IDV, IDM) VALUES ('" + 1 + "','" + obj.getIDB() + "','" + obj.getListVehicule().get(i).getIDV() + "','" + obj."
			}
			System.out.println(query);
			statementResult = true;
			statementResult = statement.execute(query);
		} catch (SQLException e) {
			statementResult = false;
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println(statementResult);
		return statementResult;
	}
	
	public boolean delete(Balade balade){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "DELETE FROM Balade WHERE IDB = " + balade.getiDB() + ";";
			System.out.println(query);
			statementResult = true;
			statementResult = statement.execute(query);
		} catch (SQLException e) {
			statementResult = false;
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println(statementResult);
		return statementResult;
	}
	
	public boolean delete_Ligne_Balade(Balade balade, Membre membre){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "DELETE FROM Ligne_Balade WHERE IDB = " + balade.getiDB() + " AND IDM = " + membre.getiD();
			System.out.println(query);
			statementResult = true;
			statementResult = statement.execute(query);
		} catch (SQLException e) {
			statementResult = false;
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println(statementResult);
		return statementResult;
	}
	
	public boolean delete_Membre_Ligne_Balade(Balade balade, Membre membre){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "DELETE FROM Ligne_Balade WHERE IDB = " + balade.getiDB() + " AND IDM = " + membre.getiD() + " AND type = " + "\"membre\" OR type = \"velo\"";
			System.out.println(query);
			statementResult = true;
			statementResult = statement.execute(query);
		} catch (SQLException e) {
			statementResult = false;
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println(statementResult);
		return statementResult;
	}
	
	public boolean delete_Velo_Ligne_Balade(Balade balade, Membre membre){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "DELETE FROM Ligne_Balade WHERE ID = (SELECT MAX(ID) AS VeloSelected FROM Ligne_Balade WHERE type = \"velo\" AND IDM = " + membre.getiD() + " AND IDB = " + balade.getiDB()+")";
			System.out.println(query);
			statementResult = true;
			statementResult = statement.execute(query);
		} catch (SQLException e) {
			statementResult = false;
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println(statementResult);
		return statementResult;
	}
	
	public boolean update(Balade obj){
		System.out.println("Mon objet depuis la m�thode update : " + obj);
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Balade SET libelleB = " + "'" + obj.getLibelle() +  "', " + "lieuDepart = " + "'" + obj.getLieuDepart() + "', " +  "dateDepart = " + "'" + obj.getDateDepart() + "', " + "forfaitBalade = " + "'" + obj.getForfaitBalade() + "', " + "forfaitRemboursement = " + "'" + obj.getForfaitRemboursement() + "'" + " WHERE IDB = " + obj.getiDB() + ";";
			System.out.println(query);
			statementResult = true;
			statementResult = statement.execute(query);
		} catch (SQLException e) {
			statementResult = false;
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println(statementResult);
		return statementResult;
	}
	
	public boolean update_Ligne_Balade(Balade balade, Vehicule vehicule, Membre membre){
		System.out.println("Mon objet depuis la m�thode update : " + membre);
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Ligne_Balade SET IDM = " + "'" + membre.getiD() +  "'" + " WHERE IDB = " + balade.getiDB() + ";";
			System.out.println(query);
			statementResult = true;
			statementResult = statement.execute(query);
		} catch (SQLException e) {
			statementResult = false;
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println(statementResult);
		return statementResult;
	}
	
	public Balade find(int id){
		Balade balade = new Balade();
		try
		{
			ResultSet result = this.connect.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY	).executeQuery("SELECT * FROM Balade WHERE IDB = " + id);
			if(result.first())
				balade = new Balade(id, result.getString("libelleB"), result.getString("lieuDepart"), result.getString("dateDepart"), Double.parseDouble(result.getString("forfaitBalade")), Double.parseDouble(result.getString("forfaitRemboursement")));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return balade;
	}
	
	public boolean alreadyExist(String libelleB)
	{
		boolean exist = false;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Balade WHERE libelleB = " + "\"" + libelleB + "\"");
			if(result.first())
			{
				exist = true;
			}
				
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return exist;
	}
	
	public boolean membrealreadyInBalade(Balade baladeSelected, Membre currentMembre)
	{
		boolean exist = false;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Ligne_Balade WHERE IDB = " + baladeSelected.getiDB() + " AND IDM = " + currentMembre.getiD() + " AND type = " + "\"membre\"");
			if(result.first())
			{
				exist = true;
			}
				
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return exist;
	}
	public boolean membreVeloNotInBalade(Balade baladeSelected, Membre currentMembre)
	{
		boolean notExist = true;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Ligne_Balade WHERE IDB = " + baladeSelected.getiDB() + " AND IDM = " + currentMembre.getiD() + " AND type = " + "\"membre\" OR type = " + "\"velo\"");
			if(result.first())
			{
				notExist = false;
			}
				
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return notExist;
	}
	
	public boolean membreNotInBalade(Balade baladeSelected, Membre currentMembre)
	{
		boolean exist = true;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Ligne_Balade WHERE IDB = " + baladeSelected.getiDB() + " AND IDM = " + currentMembre.getiD() + " AND type = " + "\"membre\"");
			if(result.first())
			{
				exist = false;
			}
				
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return exist;
	}
	
	/*public boolean veloalreadyInBalade(Balade baladeSelected, Membre currentMembre)
	{
		boolean exist = false;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Ligne_Balade WHERE IDB = " + baladeSelected.getiDB() + " AND IDM = " + currentMembre.getiD() + " AND type = " + "\"velo\" + ");
			if(result.first())
			{
				exist = true;
			}
				
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return exist;
	}*/
	
	public boolean veloNotInBalade(Balade baladeSelected, Membre currentMembre)
	{
		boolean exist = true;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Ligne_Balade WHERE IDB = " + baladeSelected.getiDB() + " AND IDM = " + currentMembre.getiD() + " AND type = " + "\"velo\"");
			if(result.first())
			{
				exist = false;
			}
				
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return exist;
	}
	
	public List<Balade> listBalade()
	{
		List<Balade> listBalade = new ArrayList<>();
		Balade balade = null;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Balade WHERE libelleB != \"N/A\"");
			while(result.next())
			{
				balade = new Balade(result.getInt("IDB"), result.getString("libelleB"), result.getString("lieuDepart"), result.getString("dateDepart"), result.getDouble("forfaitBalade"), result.getDouble("forfaitRemboursement"));
				listBalade.add(balade);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listBalade;
	}
	
	public List<Balade> listBalade(Categorie categorie)
	{
		List<Balade> listBalade = new ArrayList<>();
		Balade balade = null;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Balade INNER JOIN Calendrier ON Balade.IDB = Calendrier.IDB INNER JOIN Categorie_Responsable ON Calendrier.nom = Categorie_Responsable.nom WHERE Categorie_Responsable.nom = " + "\"" + categorie.getNom() + "\"" + "AND Ligne_Balade.IDM = " + 1);
			while(result.next())
			{
				balade = new Balade(result.getInt("IDB"), result.getString("libelleB"), result.getString("lieuDepart"), result.getString("dateDepart"), result.getDouble("forfaitBalade"), result.getDouble("forfaitRemboursement"));
				listBalade.add(balade);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listBalade;
	}
	
	public List<Balade> listBaladeBylistCategorie(List<Categorie> listCategorie)
	{
		for(Categorie categorie : listCategorie)
		{
			System.out.println("Categorie : "  + categorie.getNom());

			
		}
		List<Balade> listBalade = new ArrayList<>();
		Balade balade;
		try{
			for(Categorie categorie : listCategorie)
			{
				ResultSet result = this.connect.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Balade INNER JOIN Calendrier ON Balade.IDB = Calendrier.IDB INNER JOIN Categorie_Responsable ON Calendrier.nom = Categorie_Responsable.nom WHERE Categorie_Responsable.nom = " + "\"" + categorie.getNom() + "\"");
				while(result.next())
				{
					balade = new Balade(result.getInt("IDB"), result.getString("libelleB"), result.getString("lieuDepart"), result.getString("dateDepart"), result.getDouble("forfaitBalade"), result.getDouble("forfaitRemboursement"));
					listBalade.add(balade);
					System.out.println(result);
				}
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listBalade;
	}
	
	public List<Balade> listBaladeByCategorie(Categorie categorie)
	{
		List<Balade> listBalade = new ArrayList<>();
		Balade balade = null;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Balade INNER JOIN Calendrier ON Balade.IDB = Calendrier.IDB INNER JOIN Categorie_Responsable ON Calendrier.nom = Categorie_Responsable.nom WHERE Categorie_Responsable.nom = " + "\"" + categorie.getNom() + "\"" + " AND libelleB != \"N/A\"");
	System.out.println("after");
			while(result.next())
			{
				balade = new Balade(result.getInt("IDB"), result.getString("libelleB"), result.getString("lieuDepart"), result.getString("dateDepart"), result.getDouble("forfaitBalade"), result.getDouble("forfaitRemboursement"));
				listBalade.add(balade);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listBalade;
	}
	
	public List<Balade> listBaladeDisponible()
	{
		List<Balade> listBalade = new ArrayList<>();
		Balade balade = null;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Balade WHERE IDB NOT IN (SELECT IDB FROM Calendrier) AND libelleB != \"N/A\"");
	System.out.println("after");
			while(result.next())
			{
				balade = new Balade(result.getInt("IDB"), result.getString("libelleB"), result.getString("lieuDepart"), result.getString("dateDepart"), result.getDouble("forfaitBalade"), result.getDouble("forfaitRemboursement"));
				listBalade.add(balade);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listBalade;
	}
	
	public double getForfait(Balade balade)
	{
		double solde = 0;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Balade WHERE IDB = " + balade.getiDB());
			if(result.next())
			{
				balade = new Balade(result.getInt("IDB"), result.getString("libelleB"), result.getString("lieuDepart"), result.getString("dateDepart"), result.getDouble("forfaitBalade"), result.getDouble("forfaitRemboursement"));
				solde = result.getDouble("forfaitBalade");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return solde;
	}
	
	public int getPlaceMembreUtilisee(Vehicule vehicule, Balade balade)
	{
		int nombrePlaceMembreUtilisees = 0;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT COUNT(*) AS nbrPlacesMembres FROM Ligne_Balade WHERE IDB = " + balade.getiDB() + " AND Immatriculation = " + "\"" + vehicule.getImmatriculation() + "\"" + " AND type = " + "\"membre\"");
			if(result.next())
			{
				nombrePlaceMembreUtilisees = result.getInt("nbrPlacesMembres");
				System.out.println("Nombre de places membres utilis�es : " + nombrePlaceMembreUtilisees);
				/*balade = new Balade(result.getInt("IDB"), result.getString("libelleB"), result.getString("lieuDepart"), result.getString("dateDepart"), result.getDouble("forfait"));
				solde = result.getDouble("forfait");*/
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return nombrePlaceMembreUtilisees;
	}
	
	public int getPlaceVeloUtilisee(Vehicule vehicule, Balade balade)
	{
		int nombrePlaceVeloUtilisees = 0;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT COUNT(*) AS nbrPlacesVelos FROM Ligne_Balade WHERE IDB = " + balade.getiDB() + " AND Immatriculation = " + "\"" + vehicule.getImmatriculation() + "\"" + " AND type = " + "\"velo\"");
			if(result.next())
			{
				nombrePlaceVeloUtilisees = result.getInt("nbrPlacesVelos");
				System.out.println("Nombre de places v�los utilis�es : " + nombrePlaceVeloUtilisees);
				/*balade = new Balade(result.getInt("IDB"), result.getString("libelleB"), result.getString("lieuDepart"), result.getString("dateDepart"), result.getDouble("forfait"));
				solde = result.getDouble("forfait");*/
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return nombrePlaceVeloUtilisees;
	}
}