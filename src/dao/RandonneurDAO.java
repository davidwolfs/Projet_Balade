package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import exo.Randonneur;

public class RandonneurDAO extends DAO<Randonneur>{

	public RandonneurDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Randonneur obj) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Randonneur (IDR, nomR, prenomR, dateNaissR,  emailR, passwordR) VALUES ('" + obj.getiD() + "','" + obj.getNom() + "','" + obj.getPrenom() + "','" + "1994-02-18" + "','" + obj.getEmail() + "','" + obj.getPassword() + "')" + ";";
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
	public boolean delete(Randonneur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Randonneur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Randonneur find(int id) {
		Randonneur randonneur = new Randonneur();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Randonneur WHERE IDR = " + id);
			if(result.first())
				randonneur = new Randonneur(id, result.getString("nomR"), result.getString("prenomR"), result.getString("dateNaissR"), result.getString("emailR"), result.getString("passwordR"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return randonneur;
	}

}
