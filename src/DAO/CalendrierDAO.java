package DAO;

import java.sql.*;

import DAO.CalendrierDAO;
import exo.Calendrier;
import exo.Responsable;

public class CalendrierDAO extends DAO<Calendrier> 
{
	public CalendrierDAO(Connection conn) 
	{
		super(conn);
	}
	
	public boolean create(Calendrier obj){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Calendrier (IdCal, dateCal, IDB, Idcat) VALUES ('" + obj.getID() + "','" + obj.getLibelle() + "','" + obj.getLieuDepart() + "','" + obj.getDateDepart() + "','" + obj.getForfait() + "','" + 2 + "')" + ";";
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
	
	public boolean delete(Calendrier obj){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "DELETE FROM Calendrier WHERE IDB = " + obj.getID() + ";";
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
}
