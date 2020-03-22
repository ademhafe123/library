package biblioteka;

import java.io.Serializable;
import java.util.ArrayList;

public class Racun implements Serializable{
	
	public Racun(int brojRacuna, String imeMusterije, int brojPosudjenihKnjiga) {
		this.brojRacuna = brojRacuna;
		this.imeMusterije = imeMusterije;
		this.brojPosudjenihKnjiga = brojPosudjenihKnjiga;
	}
	
	
	public int getBrojRacuna() {
		return brojRacuna;
	}
	public void setBrojRacuna(int brojRacuna) {
		this.brojRacuna = brojRacuna;
	}
	public String getImeMusterije() {
		return imeMusterije;
	}
	public void setImeMusterije(String imeMusterije) {
		this.imeMusterije = imeMusterije;
	}
	public int getBrojKnjiga() {
		return brojPosudjenihKnjiga;
	}
	public void setBrojKnjiga(int brojKnjiga) {
		this.brojPosudjenihKnjiga = brojKnjiga;
	}
	public ArrayList<Knjiga> getKnjige() {
		return posudjeneKnjige;
	}


	public void setKnjige(ArrayList<Knjiga> knjige) {
		this.posudjeneKnjige = knjige;
	}
	
	
	int brojRacuna;
	String imeMusterije;
	int brojPosudjenihKnjiga;
	ArrayList<Knjiga> posudjeneKnjige;
	ArrayList<Racun> racuni;
	
	
	

}
