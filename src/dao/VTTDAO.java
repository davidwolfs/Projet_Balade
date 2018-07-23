package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import exo.Tresorier;
import exo.Trialiste;
import exo.VTT;
import exo.Vehicule;

public class VTTDAO extends DAO<VTT>{

	public VTTDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(VTT obj) {
		return false;
		/*boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO VTT (IDT, nomT, prenomT, dateNaissT,  emailT, passwordT) VALUES ('" + obj.getiD() + "','" + obj.getNom() + "','" + obj.getPrenom() + "','" + "1994-02-18" + "','" + obj.getEmail() + "','" + obj.getPassword() + "')" + ";";
			System.out.println(query);
			statementResult = true;
			statementResult = statement.execute(query);
		} catch (SQLException e) {
			statementResult = false;
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println(statementResult);
		return statementResult;*/
	}

	@Override
	public boolean delete(VTT obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(VTT obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VTT find(int id) {
		VTT vtt = new VTT();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM VTT WHERE IDVTT = " + id);
			//if(result.first())
				//vtt = new VTT(id, result.getString("nomR"), result.getString("prenomR"), result.getString("dateNaissR"), result.getString("emailR"), result.getString("passwordR"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return vtt;
	}

}
