package dao;

import java.sql.*;

import dao.CycloDAO;
import exo.Categorie;
import exo.Cyclo;
import exo.Responsable;

public class CycloDAO extends DAO<Cyclo> 
{
	public CycloDAO(Connection conn) 
	{
		super(conn);
	}
	
	public boolean create(Cyclo obj){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Cyclo (IdCat, IDR, IDMl) VALUES ('" + obj.getIDCy() + "','" + obj.getLibelle() + "','" + obj.getLieuDepart() + "','" + obj.getDateDepart() + "','" + obj.getForfait() + "','" + 2 + "')" + ";";
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
	
	public boolean delete(Cyclo obj){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "DELETE FROM Cyclo WHERE IdCat = " + obj.getIDCy() + ";";
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
	
	public boolean update(Categorie obj){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Cyclo SET " + obj.getNom() + "WHERE" + obj.getIdCat() + "=" + obj.getIdCat();
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
	public Cyclo find(int id){
		Cyclo cyclo = new Cyclo();
		try
		{
			ResultSet result = this.connect.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY	).executeQuery("SELECT * FROM Cyclo WHERE IDCy = " + id);
			if(result.first())
				cyclo = new Cyclo(id, result.getString(""), result.getString(""));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return cyclo;
	}
}
