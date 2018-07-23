package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import exo.Balade;
import exo.Membre;
import exo.Responsable;
import exo.Vehicule;

public class MembreDAO extends DAO<Membre> 
{
	public MembreDAO(Connection conn) 
	{
		super(conn);
	}
	
	public boolean create(Membre obj){
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Membre (IDM, nomM, prenomM, dateNaissM,  emailM, passwordM) VALUES ('" + obj.getiD() + "','" + obj.getNom() + "','" + obj.getPrenom() + "','" + "1994-02-18" + "','" + obj.getEmail() + "','" + obj.getPassword() + "')" + ";";
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
	
	public boolean delete(Membre obj){
		return false;
	}
	
	public boolean update(Membre obj){
		return false;
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
	
	public boolean findByEmailPassword(String email, String password){
		boolean existe = false;
		Membre membre = new Membre(1, null, null, null, email, password);
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Membre WHERE EmailM = " + "\"" + email + "\" AND PasswordM = " + "\"" + password + "\"");
			if(result.first())
			{
				membre = new Membre(1, result.getString("nomM"), result.getString("prenomM"), result.getString("dateNaissM"), email, password);
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
}
