package biblioteka;

import java.io.Serializable;
import java.util.ArrayList;

public class Knjiga implements Serializable {
	
	public int getBrojKnjige() {
		return brojKnjige;
	}
	public void setBrojKnjige(int brojKnjige) {
		this.brojKnjige = brojKnjige;
	}
	public String getImeKnjige() {
		return imeKnjige;
	}
	public void setImeKnjige(String imeKnjige) {
		this.imeKnjige = imeKnjige;
	}
	public boolean isStatusKnjige() {
		return statusKnjige;
	}
	public void setStatusKnjige(boolean statusKnjige) {
		this.statusKnjige = statusKnjige;
	}
	public ArrayList<Knjiga> getKnjiga() {
		return knjiga;
	}
	public void setKnjiga(ArrayList<Knjiga> knjiga) {
		this.knjiga = knjiga;
	}
	
	
	int brojKnjige;
	String imeKnjige;
	boolean statusKnjige;
	ArrayList<Knjiga> knjiga;
	

	
	public Knjiga (int brojKnjige, String imeKnjige, boolean statusKnjige) {
		this.brojKnjige = brojKnjige;
		this.imeKnjige = imeKnjige;
		this.statusKnjige = statusKnjige;
	}
	
	
	
	
}
