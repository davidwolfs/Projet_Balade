package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

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
				tresorier = new Tresorier(id, result.getString("nomR"), result.getString("prenomR"), result.getDate("dateNaissR"), result.getString("emailR"), result.getString("passwordR"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return tresorier;
	}

}
