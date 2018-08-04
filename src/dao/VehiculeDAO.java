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
import exo.Tresorier;import exo.Trialiste;
import exo.Vehicule;

public class VehiculeDAO extends DAO<Vehicule>{

	public VehiculeDAO(Connection conn) {
		super(conn);
	}

	public boolean create(Vehicule vehicule, Membre membre) 
	{
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Vehicule (Immatriculation, Marque, Modele, NombrePlaceMembre, NombrePlaceVelo, IDM) VALUES ('" + vehicule.getImmatriculation() + "','" + vehicule.getMarque() + "','" + vehicule.getModele() + "'," + vehicule.getNombrePlaceMembre() + "," + vehicule.getNombrePlaceVelo() + "," + membre.getiD() + ")" + ";";
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
	
	public Vehicule getChauffeur(Vehicule vehicule)
	{
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Vehicule WHERE Immatriculation = " + "\"" + vehicule.getImmatriculation() + "\"");
			if(result.first())
			{
				Membre membre = new Membre();
				membre.setiD(result.getInt("IDM"));
				vehicule.setChauffeur(membre);
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
	public boolean alreadyInBalade(Balade balade, Vehicule vehicule)
	{
		boolean exist = false;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Ligne_balade WHERE Immatriculation = " + "\"" + vehicule.getImmatriculation() + "\"" + "AND IDM = " + balade.getiDB());
			if(result.first())
			{
				exist = true;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return exist;
	}
	
	public List<Vehicule> listVehiculeByBalade(Membre membre, Balade balade, Vehicule vehicule)
	{
		VehiculeDAO vehiculeDAO = new VehiculeDAO(connect);
		MembreDAO membreDAO = new MembreDAO(connect);
		System.out.println(balade.getiDB());
		List<Vehicule> listVehicule = new ArrayList<>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT  DISTINCT Vehicule.Immatriculation, Vehicule.IDM, Vehicule.Marque, Vehicule.Modele, Vehicule.NombrePlaceMembre, Vehicule.NombrePlaceVelo FROM Vehicule INNER JOIN Ligne_Balade ON Vehicule.Immatriculation = Ligne_Balade.Immatriculation WHERE IDB = " + balade.getiDB());
			while(result.next())
			{
				Membre foundMembre = new Membre();
				foundMembre.setiD(result.getInt("IDM"));
				foundMembre = membreDAO.findMembre(foundMembre);
				vehicule = new Vehicule(result.getString("Immatriculation"), result.getString("Marque"), result.getString("Modele"), result.getInt("nombrePlaceMembre"), result.getInt("nombrePlaceVelo"), foundMembre);
				vehiculeDAO.getListOnlyMembreVehicule(vehicule);
				listVehicule.add(vehicule);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listVehicule;
	}

	public List<Vehicule> listVehiculeByMembre(Membre membre)
	{
		Membre storedMembre = membre;
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
				membre = storedMembre;
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
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISTINCT Ligne_Balade.IDM AS Passager, Vehicule.Immatriculation, Vehicule.Marque, Vehicule.Modele, Vehicule.nombrePlaceMembre, Vehicule.nombrePlaceVelo  FROM Ligne_Balade LEFT JOIN Vehicule ON Ligne_Balade.IDM = Vehicule.IDM WHERE IDB = " + balade.getiDB() + " AND Ligne_Balade.type = \"membre\" AND paye = 0 AND Vehicule.IDM IS NULL");
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
	
	public List<Vehicule> listPassagerInBalade(Balade balade, Vehicule vehicule) {
		MembreDAO membreDAO = new MembreDAO(connect);
		System.out.println(balade);
		List<Vehicule> listVehicule = new ArrayList<>();
		List<Membre> listMembre = new ArrayList<>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISTINCT Ligne_Balade.IDM AS Passager, Vehicule.Immatriculation, Vehicule.Marque, Vehicule.Modele, Vehicule.nombrePlaceMembre, Vehicule.nombrePlaceVelo  FROM Ligne_Balade LEFT JOIN Vehicule ON Ligne_Balade.IDM = Vehicule.IDM WHERE IDB = " + balade.getiDB() + " AND Ligne_Balade.type = \"membre\" AND paye = 0 AND Vehicule.IDM IS NULL");
			while(result.next())
			{
				Membre membre = membreDAO.find(result.getInt("Passager"));
				listMembre.add(membre);
				vehicule.setListMembre(listMembre);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listVehicule;
	}
	
	
	public List<Membre> listMembreCotisationNonPayee()
	{
		MembreDAO membreDAO = new MembreDAO(connect);
		Membre membre = null;
		List<Membre> listMembre = new ArrayList<>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Membre WHERE Paye = FALSE AND nomM != \"N/A\"");
			while(result.next())
			{
				//Membre membre = membreDAO.find(result.getInt("Passager"));
				membre = new Membre(result.getInt("IDM"), result.getString("nomM"), result.getString("prenomM"), result.getString("dateNaissM"), result.getString("EmailM"), result.getString("PasswordM"));
				listMembre.add(membre);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listMembre;
	}
	
	public Vehicule getListMembreVehicule(Vehicule vehicule)
	{
		List<Membre> listMembre = new ArrayList<>();
		Membre membre = null;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Vehicule INNER JOIN Ligne_Balade ON Vehicule.Immatriculation = Ligne_Balade.Immatriculation INNER JOIN Membre ON Ligne_Balade.IDM = Membre.IDM WHERE Vehicule.Immatriculation = " + "\"" + vehicule.getImmatriculation() + "\"");
			while(result.next())
			{
				membre = new Membre(result.getString("nomM"), result.getString("prenomM"), result.getString("datenaissM"), result.getString("EmailM"), result.getString("PasswordM"), result.getDouble("Solde"));
				listMembre.add(membre);
				vehicule.setListMembre(listMembre);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return vehicule;
	}

	public Vehicule getListOnlyMembreVehicule(Vehicule vehicule)
	{
		List<Membre> listMembre = new ArrayList<>();
		Membre membre = null;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Vehicule INNER JOIN Ligne_Balade ON Vehicule.Immatriculation = Ligne_Balade.Immatriculation INNER JOIN Membre ON Ligne_Balade.IDM = Membre.IDM WHERE Vehicule.Immatriculation = " + "\"" + vehicule.getImmatriculation() + "\"" + " AND type = \"membre\"");
			while(result.next())
			{
				membre = new Membre(result.getString("nomM"), result.getString("prenomM"), result.getString("datenaissM"), result.getString("EmailM"), result.getString("PasswordM"), result.getDouble("Solde"));
				listMembre.add(membre);
				vehicule.setListMembre(listMembre);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return vehicule;
	}
	
	@Override
	public boolean create(Vehicule obj) {
		// TODO Auto-generated method stub
		return false;
	}
}