package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.CalendrierDAO;
import exo.Balade;
import exo.Calendrier;
import exo.Categorie;
import exo.Membre;
import exo.Responsable;

public class CalendrierDAO extends DAO<Calendrier> 
{
	public CalendrierDAO(Connection conn) 
	{
		super(conn);
	}
	
	@Override
	public boolean create(Calendrier obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean create(Calendrier calendrier, Balade balade, Categorie categorie){
		System.out.println(calendrier.getDateCal() + balade.getLibelle() + categorie.getNom());
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Calendrier (nom, dateCal, IDB) VALUES ('" + calendrier.getNomCal() + "','" + calendrier.getDateCal() + "','" + balade.getiDB() + "')" + ";";
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
	
	public boolean delete(Calendrier calendrier){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "DELETE FROM Calendrier WHERE IDB = " + calendrier.getiD() + ";";
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
	
	public boolean update(Calendrier obj){
		return false;
	}
	public Calendrier find(int id){
		Calendrier calendrier = new Calendrier();
		try
		{
			ResultSet result = this.connect.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY	).executeQuery("SELECT * FROM Balade WHERE IDB = " + id);
			if(result.first())
				calendrier = new Calendrier();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return calendrier;
	}
	
	public List<Calendrier> listCalendrier()
	{
		List<Calendrier> listCalendrier = new ArrayList<>();
		Calendrier calendrier;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Calendrier WHERE dateCal != \"N/A\" AND nom != \"N/A\"");
	System.out.println("after");
			while(result.next())
			{
				calendrier = new Calendrier(result.getInt("IdCal"), result.getString("dateCal"));
				listCalendrier.add(calendrier);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listCalendrier;
	}
	
	public List<Calendrier> listCalendrierByCategorie(Categorie categorie)
	{
		List<Calendrier> listCalendrier = new ArrayList<>();
		Calendrier calendrier;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Calendrier WHERE dateCal != \"N/A\" AND nom != \"N/A\" AND nom = " + "\"" + categorie.getNom() + "\"");
	System.out.println("after");
			while(result.next())
			{
				calendrier = new Calendrier(result.getInt("IdCal"), result.getString("nom"), result.getString("dateCal"));
				listCalendrier.add(calendrier);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listCalendrier;
	}
}
