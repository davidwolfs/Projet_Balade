package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import DAO.BaladeDAO;
import exo.Balade;
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
			String query = "INSERT INTO Balade (IDB, libelleB, lieuDepart, dateDepart, forfait) VALUES ('" + obj.getIDB() + "','" + obj.getLibelle() + "','" + obj.getLieuDepart() + "','" + obj.getDateDepart() + "','" + obj.getForfait() + "')" + ";";
			for(int i = 0; i < obj.getListVehicule().size() ; i++)
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
	
	public List<Balade> listBalade()
	{
		List<Balade> listBalade = new ArrayList<>();
		Balade balade = null;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Balade");
	System.out.println("after");
			while(result.next())
			{
				balade = new Balade(result.getInt("IDB"), result.getString("lieuDepart"), result.getString("dateDepart"), result.getDouble("forfait"), result.getString("libelleB"));
				listBalade.add(balade);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listBalade;
	}
}