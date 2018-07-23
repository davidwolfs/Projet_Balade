package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
			String query = "INSERT INTO Balade (IDB, libelleB, lieuDepart, dateDepart, forfait) VALUES ('" + obj.getiDB() + "','" + obj.getLibelle() + "','" + obj.getLieuDepart() + "','" + obj.getDateDepart() + "','" + obj.getForfait() + "')" + ";";
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
	
	public boolean create_Ligne_Balade(Balade balade, Vehicule vehicule, Membre membre)
	{
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Ligne_Balade (IDB, Immatriculation, IDM) VALUES (" + balade.getiDB() + "," + "\"" + vehicule.getImmatriculation() + "\"" + "," + membre.getiD() + ");";
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
			String query = "INSERT INTO Ligne_Balade (IDB, Immatriculation, IDM) VALUES (" + balade.getiDB() + "," + "\"" + vehicule.getImmatriculation() + "\"" + "," + 1 + ");";
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
			String query = "DELETE FROM Ligne_Balade WHERE IDB = " + balade.getiDB() + " AND IDM = " + membre.getiD() + ";";
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
		System.out.println("Mon objet depuis la méthode update : " + obj);
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Balade SET libelleB = " + "'" + obj.getLibelle() +  "', " + "lieuDepart = " + "'" + obj.getLieuDepart() + "', " +  "dateDepart = " + "'" + obj.getDateDepart() + "', " + "forfait = " + "'" + obj.getForfait() + "'" + " WHERE IDB = " + obj.getiDB() + ";";
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
		System.out.println("Mon objet depuis la méthode update : " + membre);
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
				balade = new Balade(id, result.getString("libelleB"), result.getString("lieuDepart"), result.getString("dateDepart"), Double.parseDouble(result.getString("forfait")));
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
	
	public boolean alreadyInBalade(Balade baladeSelected, Membre currentMembre)
	{
		boolean exist = false;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Ligne_Balade WHERE IDB = " + baladeSelected.getiDB() + " AND IDM = " + currentMembre.getiD() + "");
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
				balade = new Balade(result.getInt("IDB"), result.getString("libelleB"), result.getString("lieuDepart"), result.getString("dateDepart"), result.getDouble("forfait"));
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
				balade = new Balade(result.getInt("IDB"), result.getString("libelleB"), result.getString("lieuDepart"), result.getString("dateDepart"), result.getDouble("forfait"));
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
					balade = new Balade(result.getInt("IDB"), result.getString("libelleB"), result.getString("lieuDepart"), result.getString("dateDepart"), result.getDouble("forfait"));
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
				balade = new Balade(result.getInt("IDB"), result.getString("libelleB"), result.getString("lieuDepart"), result.getString("dateDepart"), result.getDouble("forfait"));
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
				balade = new Balade(result.getInt("IDB"), result.getString("libelleB"), result.getString("lieuDepart"), result.getString("dateDepart"), result.getDouble("forfait"));
				solde = result.getDouble("forfait");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return solde;
	}
}