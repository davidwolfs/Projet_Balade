package DAO;

import java.sql.*;

import DAO.BaladeDAO;
import exo.Balade;

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
			String query = "INSERT INTO Balade (IDB, libelleB, lieuDepart, dateDepart, forfait, IdCal) VALUES ('" + obj.getIDB() + "','" + obj.getLibelle() + "','" + obj.getLieuDepart() + "','" + obj.getDateDepart() + "','" + obj.getForfait() + "','" + 2 + "')" + ";";
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
	
	public boolean delete(Balade obj){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "DELETE FROM Balade WHERE IDB = " + obj.getIDB() + ";";
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
		return false;
	}
	public Balade find(int id){
		Balade balade = new Balade();
		try
		{
			ResultSet result = this.connect.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY	).executeQuery("SELECT * FROM Balade WHERE IDB = " + id);
			if(result.first())
				balade = new Balade(id, result.getString("lieuDepart"), result.getString("dateDepart"), Double.parseDouble(result.getString("forfait")), result.getString("libelleB"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return balade;
	}
}
