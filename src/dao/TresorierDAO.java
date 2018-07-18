package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import exo.Membre;
import exo.Responsable;
import exo.Tresorier;

public class TresorierDAO extends DAO<Tresorier>{

	public TresorierDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Tresorier obj) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Tresorier (IDT, nomT, prenomT, dateNaissT,  emailT, passwordT) VALUES ('" + obj.getiD() + "','" + obj.getNom() + "','" + obj.getPrenom() + "','" + "1994-02-18" + "','" + obj.getEmail() + "','" + obj.getPassword() + "')" + ";";
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
	public boolean delete(Tresorier obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Tresorier obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tresorier find(int id) {
		Tresorier tresorier = new Tresorier(id, null, null, null, null, null);
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Tresorier WHERE IDT = " + id);
			if(result.first())
				tresorier = new Tresorier(id, result.getString("nomR"), result.getString("prenomR"), result.getString("dateNaissR"), result.getString("emailR"), result.getString("passwordR"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return tresorier;
	}
	
	public boolean findByEmailPassword(String email, String password){
		boolean existe = false;
		Tresorier tresorier= new Tresorier(1, null, null, null, email, password);
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Tresorier WHERE emailT = " + "\"" + email + "\" AND passwordT = " + "\"" + password + "\"");
			if(result.first())
			{
				tresorier = new Tresorier(1, result.getString("nomT"), result.getString("prenomT"), result.getString("dateNaissT"), email, password);
				existe = true;
			}
				
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return existe;
	}
	
	public Tresorier findTresorierByEmailPassword(String email, String password){
		Tresorier tresorier = new Tresorier(1, null, null, null, email, password);
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Tresorier WHERE emailT = " + "\"" + email + "\" AND passwordT = " + "\"" + password + "\"");
			if(result.first())
			{
				tresorier = new Tresorier(result.getInt("IDT"), result.getString("nomT"), result.getString("prenomT"), result.getString("dateNaissT"), email, password);
			}
				
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return tresorier;
	}
	
	public boolean alreadyExist(String email)
	{
		boolean exist = false;
		Tresorier tresorier = new Tresorier();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Tresorier WHERE emailT = " + "\"" + email + "\"");
			if(result.first())
			{
				tresorier = new Tresorier(result.getInt("IDT"), result.getString("nomT"), result.getString("prenomT"), result.getString("dateNaissT"), email, result.getString("passwordT"));
				exist = true;
			}
				
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return exist;
	}
}
