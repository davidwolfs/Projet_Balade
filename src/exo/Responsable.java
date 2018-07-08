package exo;

import java.util.Date;

public class Responsable extends Personne {
	private Date dateDebut;

	public Responsable(int iD, String nom, String prenom, Date dateNaiss, String email, String password/*,
			Date dateDebut*/) {
		super(iD, nom, prenom, dateNaiss, email, password);
		//this.dateDebut = dateDebut;
	}

	/*public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}*/
}
