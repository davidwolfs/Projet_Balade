package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exo.Tresorier;
import exo.Trialiste;
import exo.Vehicule;

public class VehiculeDAO extends DAO<Vehicule>{

	public VehiculeDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Vehicule obj) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Vehicule (IDV, Chauffeur, nombrePlace, Immatriculation,  nombrePlaceVelo) VALUES ('" + obj.getIDV() + "','" + obj.getChauffeur() + "','" + obj.getNombrePlace() + "','" + obj.getImmatriculation() + "','" + obj.getNombrePlaceVelo() + "')" + ";";
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
	public boolean delete(Vehicule obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Vehicule obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vehicule find(int id) {
		Vehicule vehicule = null;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Vehicule WHERE IDT = " + id);
			if(result.first())
				vehicule = new Vehicule(result.getInt("IDV"), result.getString("Chauffeur"), result.getInt("nombrePlace"), result.getString("Immatriculation"), result.getInt("nombrePlaceVelo"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return vehicule;
	}
	
	public List<Vehicule> listVehicule()
	{
		List<Vehicule> listVehicule = new ArrayList<>();
		Vehicule vehicule = null;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Vehicule");
	System.out.println("after");
			while(result.next())
			{
				vehicule = new Vehicule(result.getInt("IDV"), result.getString("Chauffeur"), result.getInt("nombrePlace"), result.getString("Immatriculation"), result.getInt("nombrePlaceVelo"));
				listVehicule.add(vehicule);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listVehicule;
	}

}
