package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import exo.Membre;
import exo.Responsable;

public class ResponsableDAO extends DAO<Responsable>{

	public ResponsableDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Responsable obj) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Responsable (IDR, nomR, prenomR, dateNaissR,  emailR, passwordR) VALUES ('" + obj.getiD() + "','" + obj.getNom() + "','" + obj.getPrenom() + "','" + "1994-02-18" + "','" + obj.getEmail() + "','" + obj.getPassword() + "')" + ";";
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

	@Override
	public boolean delete(Responsable obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Responsable obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Responsable find(int id) {
		Responsable responsable = new Responsable(id, null, null, null, null, null);
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Responsable WHERE IDR = " + id);
			if(result.first())
				responsable = new Responsable(id, result.getString("nomR"), result.getString("prenomR"), result.getString("dateNaissR"), result.getString("emailR"), result.getString("passwordR"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return responsable;
	}
	
	public boolean findByEmailPassword(String email, String password){
		boolean existe = false;
		Responsable responsable = new Responsable(1, null, null, null, email, password);
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Responsable WHERE emailR = " + "\"" + email + "\" AND passwordR = " + "\"" + password + "\"");
			if(result.first())
			{
				responsable = new Responsable(1, result.getString("nomR"), result.getString("prenomR"), result.getString("dateNaissR"), email, password);
				existe = true;
			}
				
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return existe;
	}
	
	public Responsable findMembreByEmailPassword(String email, String password){
		Responsable responsable = new Responsable(1, null, null, null, email, password);
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Responsable WHERE emailR = " + "\"" + email + "\" AND passwordR = " + "\"" + password + "\"");
			if(result.first())
			{
				responsable = new Responsable(result.getInt("IDR"), result.getString("nomR"), result.getString("prenomR"), result.getString("dateNaissR"), email, password);
			}
				
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return responsable;
	}
	
	public boolean alreadyExist(String email)
	{
		boolean exist = false;
		Responsable responsable = new Responsable();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Responsable WHERE emailR = " + "\"" + email + "\"");
			if(result.first())
			{
				responsable = new Responsable(result.getInt("IDR"), result.getString("nomR"), result.getString("prenomR"), result.getString("dateNaissR"), email, result.getString("passwordR"));
				exist = true;
			}
				
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return exist;
	}
}
