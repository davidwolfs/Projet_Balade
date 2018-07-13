package dao;

import java.sql.*;

import exo.Descendeur;
import exo.Membre;
import exo.Responsable;

public class DescendeurDAO extends DAO<Descendeur> 
{
	public DescendeurDAO(Connection conn) 
	{
		super(conn);
	}
	
	public boolean create(Descendeur obj){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Descendeur (IDM, nomM, prenomM, dateNaissM,  emailM, passwordM) VALUES ('" + obj.getiD() + "','" + obj.getNom() + "','" + obj.getPrenom() + "','" + "1994-02-18" + "','" + obj.getEmail() + "','" + obj.getPassword() + "')" + ";";
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
	
	public boolean delete(Descendeur obj){
		return false;
	}
	
	public boolean update(Descendeur obj){
		return false;
	}
	public Descendeur find(int id){
		Descendeur descendeur = new Descendeur();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Descendeur WHERE IDDesc = " + id);
			if(result.first())
				descendeur = new Descendeur(id, result.getString("nomR"), result.getString("prenomR"), result.getString("dateNaissR"), result.getString("emailR"), result.getString("passwordR"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return descendeur;
	}
}
