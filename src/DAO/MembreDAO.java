package DAO;

import java.sql.*;

import exo.Membre;
import exo.Responsable;

public class MembreDAO extends DAO<Membre> 
{
	public MembreDAO(Connection conn) 
	{
		super(conn);
	}
	
	public boolean create(Membre obj){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Membre (IDM, nomM, prenomM, dateNaissM,  emailM, passwordM) VALUES ('" + obj.getiD() + "','" + obj.getNom() + "','" + obj.getPrenom() + "','" + "1994-02-18" + "','" + obj.getEmail() + "','" + obj.getPassword() + "')" + ";";
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
	
	public boolean delete(Membre obj){
		return false;
	}
	
	public boolean update(Membre obj){
		return false;
	}
	public Membre find(int id){
		Membre membre = new Membre(id, null, null, null, null, null);
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Membre WHERE IDM = " + id);
			if(result.first())
				membre = new Membre(id, result.getString("nomM"), result.getString("prenomM"), result.getString("dateNaissM"), result.getString("emailM"), result.getString("PasswordM"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return membre;
	}
	
	public boolean findByEmailPassword(String email, String password){
		boolean existe = false;
		Membre membre = new Membre(1, null, null, null, email, password);
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Membre WHERE EmailM = " + "\"" + email + "\" AND PasswordM = " + "\"" + password + "\"");
			if(result.first())
			{
				membre = new Membre(1, result.getString("nomM"), result.getString("prenomM"), result.getString("dateNaissM"), email, password);
				existe = true;
			}
				
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return existe;
	}
}
