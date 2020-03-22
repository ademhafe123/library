package biblioteka;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	static Scanner unos = new Scanner(System.in);
	static ArrayList<Racun> racuni = new ArrayList<Racun>();
	static ArrayList<Knjiga> knjige = new ArrayList<Knjiga>();
	static boolean statusKnjige = true;
	static File racun = new File("racun.txt");
	static File knjiga = new File("knjiga.txt");
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
			
		spremanjeUListu();
		
		try {
			menu();
		}catch(InputMismatchException e) {
			unos.nextLine();
			System.out.println("Morate unijeti broj!");
			menu();
		}
	}
	
		public static void menu() throws InputMismatchException, IOException {
		
			System.out.println("Koju uslugu zelite? ");                   // MENU
			
			System.out.println("1. Kreiranje racuna. ");
			System.out.println("2. Kreiranje knjige. ");
			System.out.println("3. Podizanje knjige. ");
			System.out.println("4. Vracanje knjige. ");
			System.out.println("5. Ispis detalja postojeceg racuna.");
			System.out.println("6. Izlaz iz aplikacije.");
			
			int korisnikovUnos = unos.nextInt();
					
			switch(korisnikovUnos) {                                  // BIRANJE USLUGA
			case 1:
				kreiranjeRacuna();
				break;
			case 2:
				kreiranjeKnjiga();
				break;
			case 3: 
				podizanjeKnjige();
				break;
			case 4:
				vracanjeKnjige();
				break;
			case 5:
				detaljiPostojecegRacuna();
				break;
			case 6:
				exit();
				break;
			}		
		}
		
		public static void spremanjeUListu() throws ClassNotFoundException, IOException, FileNotFoundException {			
			
			
			FileInputStream ff = new FileInputStream(racun);
			try {
			ObjectInputStream oo = new ObjectInputStream(ff);
			
			while(true) {
				try {
				racuni.add((Racun)(oo.readObject()));
				} catch(EOFException e) {
					break;
				}
			}
			oo.close();
			}catch(EOFException e) {
				
			}try {
			FileInputStream fff = new FileInputStream(knjiga);
			ObjectInputStream ooo = new ObjectInputStream(fff);
			
			while(true) {
				try {
					knjige.add( (Knjiga)(ooo.readObject()) );
				} catch(EOFException e) {
					break;
				}
			}
			
			ooo.close();
			}catch(FileNotFoundException e) {	
				File knjiga = new File("knjiga.txt");
				if (!knjiga.exists())
					knjiga.createNewFile();
			}catch(EOFException e) {
				
			}
		}
		
		public static void spremanjeUFajl() throws IOException {
			
			FileOutputStream ff = new FileOutputStream(racun);
			ObjectOutputStream oo = new ObjectOutputStream(ff);
			
			for(int i = 0; i < racuni.size(); i++) {
				try {					
					oo.writeObject(racuni.get(i));					
				}
				catch(EOFException e) {
					break;
				}
			}
			oo.close();			
		
		}
				
		public static void kreiranjeRacuna() throws IOException {
			System.out.println("Unesite broj racuna koji zelite imati: ");          // RACUN
			
			int brojRacuna = unos.nextInt();
			provjeraBrojaRacuna(brojRacuna);
			
			System.out.println("Unesite ime musterije: ");			
			unos.nextLine();
			String imeMusterije = unos.nextLine();
			
			Racun racun = new Racun(brojRacuna, imeMusterije, 0);               // KONSTRUKTOR ZA KLASU RACUN
			racuni.add(racun);                                                  // SPREMANJE RACUNA U MEMORIJI ARRAY LISTE
			
			System.out.println("Cestitamo, uspjesno ste kreirali racun! \n");
			spremanjeUFajl();
			menu();                                                             // VRACANJE POCETNOG MENUA NA KONZOLU
		}
		
		
		public static void kreiranjeKnjiga() throws IOException {                                 // KREIRANJE KNJIGE
			System.out.println("Unesite broj knjige: ");
			int brojKnjige = unos.nextInt();

			provjeraBrojaKnjige(brojKnjige);
		
			System.out.println("Unesite ime knjige: ");
			String imeKnjige = unos.nextLine();
			unos.nextLine();
			
			Knjiga knjiga = new Knjiga(brojKnjige, imeKnjige, false);           // KONSTRUKTOR KLASE KNJIGA
			knjige.add(knjiga);                                                 // SPREMANJE KNJIGE U ARRAY LISTU
			
			System.out.println("Uspjesno ste kreirali knjigu! \n ");
			spremanjeUFajl();
			menu();
		}
		
		public static void podizanjeKnjige() throws IOException {                                // PODIZANJE KNJIGE
			System.out.println("Unesite broj vaseg racuna: ");
			int brojRacuna = unos.nextInt();
			
			System.out.println("Unesite broj knjige: ");
			int brojKnjige = unos.nextInt();
			
			for(int i = 0; i < racuni.size(); i++) {
				if(racuni.get(i).brojRacuna == brojRacuna) {
					if(racuni.get(i).brojPosudjenihKnjiga >= 3) {
						System.out.println("Imate 3 posudjene knjige, morate vratiti jednu da biste ponovo podigli! ");
					}else {
						povecanjaBrojaPosudjenihKnjiga(brojRacuna);
						promjenaStatusKnjigeNaPosedjene(brojKnjige);
						System.out.println("Uspjesno ste podigli knjigu! ");
					}
				}
			}
			
			spremanjeUFajl();
			menu();
						
		}
		
		public static void vracanjeKnjige() throws IOException {                               // VRACANJE KNJIGE
			System.out.println("Unesite broj racuna: ");
			int brojRacuna = unos.nextInt();
			
			smanjenjeBrojaPosudjenihKnjiga(brojRacuna);                    // POZIVA METODU KOJA SMANJUJE BROJ POSUDJENIH KNJIGA NA RACUNU
			
			System.out.println("Unesite broj knjige: ");
			int brojKnjige = unos.nextInt();
		
			promjenaStatusaKnjigeNaNeposudjene(brojKnjige);                // MIJENJA STATUS KNJIGE NA SLOBODNO (FALSE)
			
			System.out.println("Uspjesno ste vratili knjigu! \n");
			spremanjeUFajl();
			menu();
		
		}
				
		
		public static void detaljiPostojecegRacuna() throws IOException {                     // DETALJI POSTOJECEG RACUNA
			System.out.println("Unesite broj vaseg racuna: ");
			int brojRacuna = unos.nextInt();
			Racun trenutniRacun = null;                                  // POSTAVLJA TRENUTNI RACUN NA NULL KAKO BI MOGAO KASIJE DA SE ON POSTAVI KAO RACUN IZ ARRAY LISTE NA POZIJI i  
			try {
			for (int i = 0; i < racuni.size(); i++)
				if (racuni.get(i).brojRacuna == brojRacuna) {
					trenutniRacun = racuni.get(i);                     // POSTAVLJA TRENUTNI RACUN NA RACUN U ARRAY LISTI NA POZIJI i
					break;
				}
		
			System.out.println(trenutniRacun.brojPosudjenihKnjiga + " - broj knjiga \n" + trenutniRacun.brojRacuna + " - broj racuna \n" + trenutniRacun.imeMusterije + " - ime musterije \n");
			}catch(NullPointerException e) {
				System.out.println("Ukucali ste nepostojeci broj racuna!");
				menu();
			}
			spremanjeUFajl();
			menu();
		}

		
		public static int provjeraBrojaRacuna(int brojRacuna) {                      
			if(brojRacuna < 0) {                                         // AKO JE BROJ NEGATIVAN MNOZI GA SA -1 KAKO BI POSTAO POZITIVAN
				brojRacuna = brojRacuna * (-1);
			}
			
			for(int i = 0; i < racuni.size(); i++) {                      // PROVJERAVA DA LI JE BROJ RACUNA ZAUZET
				while(brojRacuna == racuni.get(i).brojRacuna) {
					System.out.println("Broj racuna je zauzet, unesite ponovo: ");
					brojRacuna = unos.nextInt();
				}
				
			} 
			
			return brojRacuna;		                                   // VRACA KONACAN BROJ RACUNA
		}
		
		public static int provjeraBrojaKnjige(int brojKnjige) {                // ISTO KAO I METODA RANIJE SAMO ZA BROJ KNJIGE
			if(brojKnjige < 0) {
				brojKnjige = brojKnjige * (-1);
			}
			
			for(int i = 0; i < knjige.size(); i++) {
				while(brojKnjige == knjige.get(i).brojKnjige) {
					System.out.println("Broj knjige je zauzet, unesite novi: ");
					brojKnjige = unos.nextInt();
				}
			}
			return brojKnjige;
		}
		
		public static void promjenaStatusKnjigeNaPosedjene(int brojKnjige) {
			for(int i = 0; i < knjige.size(); i++) {                             // IDE KROZ CIJELU ARRAY LISTU
				if(brojKnjige == knjige.get(i).brojKnjige) {                     //MIJENJA STATUS KNJIGE NA POZICIJI i NA ZAUZETO AKO JE BROJ KNJIGE JEDNAK UNESENOM BROJU KNJIGE
					knjige.get(i).statusKnjige = true;
				}
			}
		}
		
		public static void promjenaStatusaKnjigeNaNeposudjene(int brojKnjige) {     
			for(int i = 0; i < knjige.size(); i++) {                               // ISTO KAO I PRETHODNA METODA SAMO MIJENJA STATUS NA SLOBODNO
				if(brojKnjige == knjige.get(i).brojKnjige) {
					knjige.get(i).statusKnjige = false;
				}
			}
		}
		
		public static void povecanjaBrojaPosudjenihKnjiga(int brojRacuna) {
			for(int i = 0; i < racuni.size(); i++) {                           // ISTO KAO I PRETHODNA METODA SAMO ZA BROJ POSUDJENIH KNJIGA NA RACUNU
				if(brojRacuna == racuni.get(i).brojRacuna) {
					racuni.get(i).brojPosudjenihKnjiga++;
				}
			}
		}
		
		public static void smanjenjeBrojaPosudjenihKnjiga(int brojRacuna) {
			for(int i = 0; i < racuni.size(); i++) {                          // OVDJE SAMO SMANJUJE TAJ BROJ
				if(brojRacuna == racuni.get(i).brojRacuna) {
					racuni.get(i).brojPosudjenihKnjiga--;
				}
			}
		}
		
		public static boolean statusKnjige (int brojKnjige) {
			for(int i = 0; i < knjige.size(); i++) {
				if(brojKnjige == knjige.get(i).brojKnjige) {
					if(knjige.get(i).statusKnjige == true) {
						return true;
					}
				}
			}
			return false;
		}
		
		public static void exit() {
			System.exit(0);
		}
		
		
			
}

















