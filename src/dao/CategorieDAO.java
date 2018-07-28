package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.CategorieDAO;
import exo.Balade;
import exo.Calendrier;
import exo.Categorie;
import exo.Membre;
import exo.Responsable;

public class CategorieDAO extends DAO<Categorie> 
{
	public CategorieDAO(Connection conn) 
	{
		super(conn);
	}
	
	public boolean create_Categorie_Responsable(Categorie categorie, Responsable responsable){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Categorie_Responsable (nom, EmailR) VALUES ('" + categorie.getNom() + "','" + responsable.getEmail() + "')" + ";";
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
	
	public boolean create_Categorie_Membre(Categorie categorie, Membre membre){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Categorie_Membre (nom, IDM) VALUES ('" + categorie.getNom() + "','" + membre.getiD() + "')" + ";";
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
	
	public boolean delete(Categorie categorie){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "DELETE FROM Categorie WHERE nom = " + categorie.getNom() + ";";
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
	
	public boolean update_Categorie_Responsable(Categorie categorie){

		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Categorie_Responsable SET EmailR = " + "'" + categorie.getResponsable().getEmail() + "'" + " WHERE nom = " + "'" + categorie.getNom() + "'";
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
	
	public boolean delete_Categorie_Responsable(Categorie categorie)
	{
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Categorie_Responsable SET EmailR = " + "\"N/A\"" + " WHERE nom = " + "\"" + categorie.getNom() + "\"";
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
	
	public boolean create_Categorie_Categorie_Membre(Categorie categorie, Membre membre)
	{

		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Categorie_Membre (nom, EmailR) VALUES (" + "'" + categorie.getNom() + "'" + "','" + membre.getEmail() + "'" + ")";
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
	
	public Categorie find(int id){
		Categorie categorie = new Categorie();
		try
		{
			ResultSet result = this.connect.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY	).executeQuery("SELECT * FROM Categorie WHERE IdCat = " + id);
			if(result.first())
				categorie = new Categorie(result.getInt("IdCat"), result.getString("typePneu"), result.getString("nom"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return categorie;
	}
	
	public List<Categorie> listCategorie()
	{
		List<Categorie> listCategorie = new ArrayList<>();
		Categorie categorie = null;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Categorie_Responsable WHERE nom != \"N/A\"");
			while(result.next())
			{
				categorie = new Categorie(result.getInt("supplement"), result.getString("typePneu"), result.getString("nom"));
				listCategorie.add(categorie);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listCategorie;
	}
	
	public List<Categorie> listCategorie_Responsable(Responsable responsable)
	{
		List<Categorie> listCategorie = new ArrayList<>();
		Categorie categorie;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Categorie_Responsable WHERE EmailR = " + "\"" + responsable.getEmail() + "\"");
	System.out.println("after");
			while(result.next())
			{
				categorie = new Categorie(result.getInt("IdCat"), result.getString("typePneu"), result.getString("nom"));
				listCategorie.add(categorie);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listCategorie;
	}
	
	public List<Categorie> listCategorie_Membre(Membre membre)
	{
		List<Categorie> listCategorie = new ArrayList<>();
		Categorie categorie;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Categorie_Membre WHERE IDM = " + membre.getiD());
	System.out.println("after");
			while(result.next())
			{
				categorie = new Categorie(result.getInt("IdCat"), result.getString("typePneu"), result.getString("nom"));
				listCategorie.add(categorie);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listCategorie;
	}

	public boolean appartientCategorie(Membre membre)
	{
		boolean appartient = false;
		Categorie categorie;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Membre INNER JOIN Categorie_Membre ON Membre.IDM = Categorie_Membre.IDM WHERE Categorie_Membre.IDM = " + membre.getiD() + "");
			if(result.first())
			{
				categorie = new Categorie(result.getInt("IdCat"), result.getString("typePneu"), result.getString("nom"));
				appartient = true;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return appartient;
	}
	
	public boolean appartientCategorieDonnee(Categorie categorie, Membre membre)
	{
		System.out.println("CATEGORIE NOM : " + categorie.getNom());
		boolean appartient = false;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Categorie_Membre WHERE nom = " + "\"" + categorie.getNom() + "\"" + " AND IDM = " + membre.getiD());
			if(result.first())
			{
				appartient = true;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return appartient;
	}
	
	public List<Categorie> getCategorieByMembre(Membre membre)
	{
		List<Categorie> listCategorie = new ArrayList<>();
		
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Categorie_Membre WHERE IDM = " + membre.getiD());
			while(result.next())
			{
				membre.setiD(result.getInt("IDM"));
				Categorie categorie = new Categorie();
				categorie.setiD(result.getInt("IdCat"));
				categorie.setNom(result.getString("nom"));
				categorie.setTypePneu(result.getString("typePneu"));
				System.out.println("CATEGORIE : " + categorie);
				listCategorie.add(categorie);
			}
			for(Categorie cat : listCategorie)
			System.out.println("LISTE CATEGORIE : " + cat);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return listCategorie;
	}
	
	public boolean isNotResponsable(Categorie categorie, Responsable responsable)
	{
		boolean appartient = true;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Categorie_Responsable WHERE nom = " + "\"" + categorie.getNom() + "\"" + " AND EmailR = " + "\"" + responsable.getEmail() + "\"");
			if(result.first())
			{
				appartient = false;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return appartient;
	}
	
	public boolean isResponsable(Categorie categorie, Responsable responsable)
	{
		boolean appartient = false;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Categorie_Responsable WHERE nom = " + "\"" + categorie.getNom() + "\"" + " AND EmailR = " + "\"" + responsable.getEmail() + "\"");
			if(result.first())
			{
				appartient = true;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return appartient;
	}
	
	public boolean haveAlreadyResponsable(Categorie categorie, Responsable responsable)
	{
		boolean appartient = false;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Categorie_Responsable WHERE Categorie_Responsable.nom  = " + "\"" + categorie.getNom() + "\"" + " AND Categorie_Responsable.EmailR != " + "'N/A'");
			if(result.first())
			{
				appartient = true;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return appartient;
	}
	
	@Override
	public boolean create(Categorie obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Categorie obj) {
		// TODO Auto-generated method stub
		return false;
	}
}