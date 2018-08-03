package dao;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import exo.Balade;
import exo.Categorie;
import exo.Membre;
import exo.Responsable;
import exo.Vehicule;

public class MembreDAO extends DAO<Membre> 
{
	public MembreDAO(Connection conn) 
	{
		super(conn);
	}
	
	public boolean create(Membre membre){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Membre (IDM, nomM, prenomM, dateNaissM,  emailM, passwordM, Solde) VALUES ('" + membre.getiD() + "','" + membre.getNom() + "','" + membre.getPrenom() + "','" + membre.getDateNaiss() + "','" + membre.getEmail() + "','" + membre.getPassword() + "','" + membre.getSolde() + "')" + ";";
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
	
	public boolean delete(Membre membre){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "DELETE FROM Membre WHERE IDM = " + membre.getiD();
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
	
	public boolean deleteFromBalade(Membre membre){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "DELETE FROM Ligne_Balade WHERE IDM = " + membre.getiD();
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
	
	public boolean update_Statut_Remboursement(Membre membre)
	{
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Ligne_Balade SET rembourse = 1 WHERE IDM = " + membre.getiD();
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
	public Membre find(int id){
		Membre membre = new Membre(id, null, null, null, null, null);
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Membre WHERE IDM = " + id);
			if(result.first())
				membre = new Membre(id, result.getString("nomM"), result.getString("prenomM"), result.getString("dateNaissM"), result.getString("emailM"), result.getString("PasswordM"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return membre;
	}
	
	public Membre findMembre(Membre membre)
	{
		JOptionPane.showMessageDialog(null,  membre);
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Membre WHERE IDM = " + membre.getiD());
			if(result.first())
			{
				membre.setiD(result.getInt("IDM"));
				membre.setNom(result.getString("nomM"));
				membre.setPrenom(result.getString("prenomM"));
				membre.setDateNaiss(result.getString("dateNaissM"));
				membre.setEmail(result.getString("emailM"));
				membre.setPassword(result.getString("PasswordM"));
				membre.setSolde(result.getDouble("Solde"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return membre;
	}
	
	public Membre findIdByMembre(Membre membre){
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Membre WHERE nomM = " + "\"" + membre.getNom() + "\"");
			if(result.first())
				membre = new Membre(result.getInt("IDM"), result.getString("nomM"), result.getString("prenomM"), result.getString("dateNaissM"), result.getString("emailM"), result.getString("PasswordM"), result.getDouble("Solde"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return membre;
	}
	
	public boolean findByEmailPassword(String email, String password){
		boolean existe = false;
		Membre membre;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Membre WHERE EmailM = " + "\"" + email + "\" AND PasswordM = " + "\"" + password + "\"");
			if(result.first())
			{
				membre = new Membre(result.getInt("IDM"), result.getString("nomM"), result.getString("prenomM"), result.getString("dateNaissM"), email, password);
				existe = true;
			}
				
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return existe;
	}
	
	public Membre findMembreByEmailPassword(String email, String password){
		Membre membre = new Membre();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Membre WHERE EmailM = " + "\"" + email + "\" AND PasswordM = " + "\"" + password + "\"");
			if(result.first())
			{
				membre = new Membre(result.getInt("IDM"), result.getString("nomM"), result.getString("prenomM"), result.getString("dateNaissM"), email, password);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return membre;
	}

	public void payerCotisationAnnuelle(Membre membre)
	{
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Membre SET solde = " + membre.getSolde() + " WHERE IDM = " + membre.getiD() + ";";
			System.out.println(query);
			statementResult = true;
			statementResult = statement.execute(query);
		} catch (SQLException e) {
			statementResult = false;
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println(statementResult);
	}
	
	public void payerCotisationAnnuelleChangeStatus(Membre membre)
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime()));
		String date = dateFormat.format(cal.getTime());
		
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Membre SET Paye = TRUE, DatePayement = " + "\"" + date + "\"" + " WHERE IDM = " + membre.getiD() + ";";
			System.out.println(query);
			statementResult = true;
			statementResult = statement.execute(query);
		} catch (SQLException e) {
			statementResult = false;
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println(statementResult);
	}
	
	public boolean VerifierPayementCotisationAnnuelleChangeStatus(Membre membre)
	{
		System.out.println("ID DU MEMBRE : " + membre.getiD());
		boolean paye = false;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Membre WHERE IDM = " + membre.getiD() + " AND Paye = TRUE");
			if(result.first())
			{
				/* final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
				 Date currentDate = new Date();
			        System.out.println(dateFormat.format(currentDate));
			        String datePayement = result.getString("DatePayement");
			        System.out.println("DATE ACTUELLE : " +  datePayement);*/

			       // SimpleDateFormat.parse();
				  // convert date to calendar
		       /* Calendar c = Calendar.getInstance();
		        c.setTime(result.getDate("DatePayement"));

		        // manipulate date
		        c.add(Calendar.YEAR, 1);

		        // convert calendar to date
		        Date currentDatePlusOne = c.getTime();

		        System.out.println(dateFormat.format(currentDatePlusOne));
		        
				/*
				java.sql.Date dateAuj = result.getDate("DatePayement");
				java.sql.Date datePlusUnAn = new java.sql.Date(12345);
				 System.out.println("Date aujourd hui : " + dateAuj);
				 System.out.println("Date dans un an : " + datePlusUnAn);*/
				 
				/*if(dateAuj.compareTo(datePlusUnAn) > 0)
				{
					paye = false;
				}*/
				paye = true;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return paye;
	}
	
	public boolean alreadyExist(String email)
	{
		boolean exist = false;
		Membre membre = new Membre();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Membre WHERE EmailM = " + "\"" + email + "\"");
			if(result.first())
			{
				membre = new Membre(result.getInt("IDM"), result.getString("nomM"), result.getString("prenomM"), result.getString("dateNaissM"), email, result.getString("PasswordM"));
				exist = true;
			}
				
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return exist;
	}
	
	public boolean alreadyRembourse(Membre membre, Balade balade)
	{
		boolean rembourse = false;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Ligne_Balade WHERE IDM = " + membre.getiD() + " AND IDB = " + balade.getiDB() + " AND rembourse = " + 1);
			if(result.first())
			{
				rembourse = true;
			}
				
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rembourse;
	}
	
	public List<Membre> listMembre(Vehicule vehicule)
	{
		List<Membre> listMembre = new ArrayList<>();
		Membre membre = null;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Membre INNER JOIN Ligne_Balade ON Membre.IDM = Ligne_Balade.IDM WHERE IDM = " + vehicule.getChauffeur().getiD());
	System.out.println("after");
			while(result.next())
			{
				membre = new Membre(result.getInt("IDM"), result.getString("nomM"), result.getString("prenomM"), result.getString("dateNaissM"), result.getString("EmailM"), result.getString("PasswordM"), result.getDouble("Solde"));
				listMembre.add(membre);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listMembre;
	}
	
	public Membre getSoldeMembre(Membre membre)
	{
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Membre WHERE IDM = " + membre.getiD());
			if(result.next())
			{
				membre = new Membre(result.getInt("IDM"), result.getString("nomM"), result.getString("prenomM"), result.getString("dateNaissM"), result.getString("EmailM"), result.getString("PasswordM"), result.getDouble("Solde"));
				double solde = result.getDouble("Solde");
				membre.setSolde(solde);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return membre;
	}
	
	public Membre getListCategorieMembre(Membre membre)
	{
		List<Categorie> listCategorie = new ArrayList<>();
		Categorie categorie = null;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Categorie_Membre WHERE IDM = " + membre.getiD());
			while(result.next())
			{
				categorie = new Categorie(result.getInt("IdCat"), result.getString("nom"));
				listCategorie.add(categorie);
				membre.setListCategorie(listCategorie);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return membre;
	}
	
	public boolean update_solde(Membre membre){
		System.out.println("Mon objet depuis la méthode update : " + membre);
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Membre SET solde = " + membre.getSolde() + " WHERE IDM = " + membre.getiD() + ";";
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
	public boolean update(Membre obj) {
		// TODO Auto-generated method stub
		return false;
	}
}