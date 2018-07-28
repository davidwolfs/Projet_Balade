package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import exo.Balade;
import exo.Categorie;
import exo.Membre;
import exo.Tresorier;
import exo.Trialiste;
import exo.Vehicule;

public class VehiculeDAO extends DAO<Vehicule>{

	public VehiculeDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Vehicule vehicule) 
	{
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Vehicule (Immatriculation, Marque, Modele, NombrePlaceMembre, NombrePlaceVelo, IDM) VALUES ('" + vehicule.getImmatriculation() + "','" + vehicule.getMarque() + "','" + vehicule.getModele() + "'," + vehicule.getNombrePlaceMembre() + "," + vehicule.getNombrePlaceVelo() + "," + vehicule.getChauffeur().getiD() + ")" + ";";
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
		return false;
		/*boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Vehicule SET nombrePlaceMembre = " + obj.getNombrePlaceMembre() + "-1" + ", " + "nombrePlaceVelo = " + obj.getNombrePlaceVelo() + "-1" + " WHERE Immatriculation = " + "\"" + obj.getImmatriculation() + "\"" + ";";
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
	public Vehicule find(int id) {
		return null;
		/*Vehicule vehicule = null;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Vehicule WHERE IDV = " + id);
			if(result.first())
				vehicule = new Vehicule(result.getInt("IDV"), result.getInt("nombrePlace"), result.getString("Immatriculation"), result.getInt("nombrePlaceVelo"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return vehicule;*/
	}

	public Vehicule find(String Immatriculation) {
		Vehicule vehicule = null;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Vehicule WHERE Immatriculation = " + Immatriculation);
			if(result.first())
			{
				Membre membre = new Membre();
				membre.setiD(result.getInt("IDM"));
				vehicule = new Vehicule(result.getString("Immatriculation"), result.getString("Marque"), result.getString("Modele"), result.getInt("nombrePlaceMembre"), result.getInt("nombrePlaceVelo"), membre);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return vehicule;
	}
	
	public boolean alreadyExist(String immatriculation)
	{
		boolean exist = false;
		Vehicule vehicule = null;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Vehicule WHERE Immatriculation = " + "\"" + immatriculation + "\"");
			if(result.first())
			{
				vehicule = new Vehicule();
				exist = true;
			}
				
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return exist;
	}
	
	public List<Vehicule> listVehiculeByBalade(Balade balade, Vehicule vehicule)
	{
		List<Vehicule> listVehicule = new ArrayList<>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Vehicule INNER JOIN Ligne_Balade ON Vehicule.Immatriculation = Ligne_Balade.Immatriculation WHERE IDB = " + balade.getiDB() + " AND type != \"membre\"" + " AND type!= \"velo\"" + " OR type IS NULL" + " OR type IS NULL");
			while(result.next())
			{
				if(vehicule != null)
				{
					Membre membre = new Membre();
					membre.setiD(result.getInt("IDM"));
					vehicule.setImmatriculation(result.getString("Immatriculation"));
					vehicule.setMarque(result.getString("Marque"));
					vehicule.setModele(result.getString("Modele"));
					listVehicule.add(vehicule);
				}
				else
				{
					Membre membre = new Membre();
					membre.setiD(result.getInt("IDM"));
					vehicule = new Vehicule(result.getString("Immatriculation"), result.getString("Marque"), result.getString("Modele"), result.getInt("nombrePlaceMembre"), result.getInt("nombrePlaceVelo"), membre);
					listVehicule.add(vehicule);
				}
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listVehicule;
	}

	public List<Vehicule> listVehiculeByMembre(Membre membre)
	{
		List<Vehicule> listVehicule = new ArrayList<>();
		Vehicule vehicule = null;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Vehicule WHERE IDM = " + membre.getiD());
			while(result.next())
			{
				membre.setiD(result.getInt("IDM"));
				vehicule = new Vehicule(result.getString("Immatriculation"), result.getString("Marque"), result.getString("Modele"), result.getInt("nombrePlaceMembre"), result.getInt("nombrePlaceVelo"), membre);;
				listVehicule.add(vehicule);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listVehicule;
	}
	
	public List<Vehicule> listVehicule()
	{
		List<Vehicule> listVehicule = new ArrayList<>();
		Vehicule vehicule = null;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Vehicule");
			while(result.next())
			{
				Membre membre = new Membre();
				membre.setiD(result.getInt("IDM"));
				vehicule = new Vehicule(result.getString("Immatriculation"), result.getString("Marque"), result.getString("Modele"), result.getInt("nombrePlaceMembre"), result.getInt("nombrePlaceVelo"), membre);
				listVehicule.add(vehicule);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listVehicule;
	}

	public List<Vehicule> listChauffeur(Balade balade) {
		MembreDAO membreDAO = new MembreDAO(connect);
		System.out.println(balade);
		Vehicule vehicule = null;
		List<Vehicule> listVehicule = new ArrayList<>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISTINCT Vehicule.IDM AS Chauffeur, Vehicule.Immatriculation, Vehicule.Marque, Vehicule.Modele, Vehicule.nombrePlaceMembre, Vehicule.nombrePlaceVelo  FROM Vehicule INNER JOIN Ligne_Balade ON Vehicule.Immatriculation = Ligne_Balade.Immatriculation WHERE IDB = " + balade.getiDB() + " AND Ligne_Balade.Immatriculation = Vehicule.Immatriculation AND paye = 0"); /* + "\"" + vehicule.getImmatriculation() + "\"");*/
			while(result.next())
			{
				Membre membre = membreDAO.find(result.getInt("Chauffeur"));
				vehicule = new Vehicule(result.getString("Immatriculation"), result.getString("Marque"), result.getString("Modele"), result.getInt("nombrePlaceMembre"), result.getInt("nombrePlaceVelo"), membre);
				listVehicule.add(vehicule);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listVehicule;
		
	}
	
	public List<Vehicule> listPassager(Balade balade) {
		MembreDAO membreDAO = new MembreDAO(connect);
		System.out.println(balade);
		Vehicule vehicule = null;
		List<Vehicule> listVehicule = new ArrayList<>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISTINCT Vehicule.IDM AS Passager, Vehicule.Immatriculation, Vehicule.Marque, Vehicule.Modele, Vehicule.nombrePlaceMembre, Vehicule.nombrePlaceVelo  FROM Ligne_Balade LEFT JOIN Vehicule ON Ligne_Balade.Immatriculation = Vehicule.Immatriculation WHERE IDB = " + balade.getiDB() + " AND Ligne_Balade.type = \"membre\" AND paye = 0 AND Vehicule.Immatriculation IS NULL");
			while(result.next())
			{
				Membre membre = membreDAO.find(result.getInt("Passager"));
				vehicule = new Vehicule(result.getString("Immatriculation"), result.getString("Marque"), result.getString("Modele"), result.getInt("nombrePlaceMembre"), result.getInt("nombrePlaceVelo"), membre);
				listVehicule.add(vehicule);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listVehicule;
	}
}