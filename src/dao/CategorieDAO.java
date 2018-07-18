package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.CategorieDAO;
import exo.Balade;
import exo.Calendrier;
import exo.Categorie;
import exo.Responsable;

public class CategorieDAO extends DAO<Categorie> 
{
	public CategorieDAO(Connection conn) 
	{
		super(conn);
	}
	
	public boolean create(Categorie obj){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Categorie (IdCat, IDR, IDMl) VALUES ('" + obj.getIdCat() + "','" + obj.getLibelle() + "','" + obj.getLieuDepart() + "','" + obj.getDateDepart() + "','" + obj.getForfait() + "','" + 2 + "')" + ";";
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
	
	public boolean delete(Categorie obj){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "DELETE FROM Categorie WHERE IdCat = " + obj.getIdCat() + ";";
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
		return false;
	}
	public Categorie find(int id){
		Categorie categorie = new Categorie();
		try
		{
			ResultSet result = this.connect.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY	).executeQuery("SELECT * FROM Categorie WHERE IdCat = " + id);
			if(result.first())
				categorie = new Categorie(id, result.getString(""), result.getString(""));
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
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Categorie");
	System.out.println("after");
			while(result.next())
			{
				categorie = new Categorie(result.getInt("Supplement"), result.getString("TypePneu"), result.getString("nom"));
				listCategorie.add(categorie);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listCategorie;
	}
}
