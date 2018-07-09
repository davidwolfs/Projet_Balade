package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import exo.Tresorier;
import exo.Trialiste;

public class TrialisteDAO extends DAO<Trialiste>{

	public TrialisteDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Trialiste obj) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Trialiste (IDT, nomT, prenomT, dateNaissT,  emailT, passwordT) VALUES ('" + obj.getiD() + "','" + obj.getNom() + "','" + obj.getPrenom() + "','" + "1994-02-18" + "','" + obj.getEmail() + "','" + obj.getPassword() + "')" + ";";
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
	public boolean delete(Trialiste obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Trialiste obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Trialiste find(int id) {
		Trialiste trialiste = new Trialiste();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Tresorier WHERE IDT = " + id);
			if(result.first())
				trialiste = new Trialiste(id, result.getString("nomR"), result.getString("prenomR"), result.getString("dateNaissR"), result.getString("emailR"), result.getString("passwordR"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return trialiste;
	}

}
