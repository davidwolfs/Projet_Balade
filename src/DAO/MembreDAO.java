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
		return false;
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
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Responsable WHERE IDR = " + id);
			if(result.first())
				membre = new Membre(id, result.getString("nomR"), result.getString("prenomR"), result.getDate("dateNaissR"), result.getString("emailR"), result.getString("passwordR"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return membre;
	}
}
