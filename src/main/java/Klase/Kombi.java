/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klase;

import Funkcionalnosti.Operacije;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Urkom
 */
enum TipKombija{
    Putnicki,
    Van
}
public class Kombi extends Vozilo implements Operacije{


    public Kombi() {
    }
    
    public static class kSortCena implements Comparator<Kombi>{
        @Override
        public int compare(Kombi o1, Kombi o2) {
            if(o1.getCena()<o2.getCena())
                {
                    return -1;
                }
                else if(o1.getCena()==o2.getCena())
                {
                    return 0;
                }
                else {
                    return 1; 
                }
        }
    }
    public static class kSortGodiste implements Comparator<Kombi>{
        @Override
        public int compare(Kombi o1, Kombi o2) {
            if(o1.getGodiste()<o2.getGodiste())
                {
                    return -1;
                }
                else if(o1.getGodiste()==o2.getGodiste())
                {
                    return 0;
                }
                else {
                    return 1; 
                }
        }

    }
    
    private static int nadjiLiniju() throws FileNotFoundException {
        File kombi = new File("kombi.txt");
        Scanner sc = new Scanner(kombi);
        int i=0;
        while (sc.hasNextLine()) {
            sc.nextLine();
            i++;
        }
        sc.close();
        return i;
   }
    
    public static class kSortKilometraza implements Comparator<Kombi>{
        @Override
        public int compare(Kombi o1, Kombi o2) {
            if(o1.getPredjeni_kilometri()<o2.getPredjeni_kilometri())
                {
                    return -1;
                }
                else if(o1.getPredjeni_kilometri()==o2.getPredjeni_kilometri())
                {
                    return 0;
                }
                else {
                    return 1; 
                }
        }

    }
    
    public static ArrayList<Kombi> izlistaj() throws FileNotFoundException, IOException {
        ArrayList<Kombi> lista = new ArrayList<>();
        try {
            String input;
            BufferedReader br = new BufferedReader(new FileReader("kombi.txt"));
            while ((input = br.readLine()) != null) {
                String[] s = input.split(" ");
                    if (s.length == 9) {
                        lista.add(new Kombi(TipKombija.valueOf(s[8]), s[1], s[2], Pogon.valueOf(s[3]), Double.parseDouble(s[4]), Integer.parseInt(s[5]), Integer.parseInt(s[6]), Double.parseDouble(s[7])));
                    }
                    else if (s.length == 10) {
                        lista.add(new Kombi(TipKombija.valueOf(s[9]), s[1], s[2] + " " + s[3], Pogon.valueOf(s[4]), Double.parseDouble(s[5]), Integer.parseInt(s[6]), Integer.parseInt(s[7]), Double.parseDouble(s[8])));
                    }
                }
                    br.close();
                }
                catch (FileNotFoundException e) {
                    System.err.println("Fajl je nedostupan");
                }
                return lista;
        }
    
    
    List<Kombi> lista = new ArrayList<>();
    public TipKombija tip;
    @Override
    public void upis() {
        try{
            Scanner sc = new Scanner(System.in);
            String st;
            Kombi kombi = new Kombi();
            while (true) {
                System.out.println("Upisite ime proizvodjaca: ");
                kombi.setProizvodjac(sc.next());
                System.out.println("Upisite model kombija: ");
                kombi.setModel(sc.next());
                System.out.println("Upisite pogon na koji ide kombi(Dizel, Benzin): ");
                kombi.setTip_pogona(Pogon.valueOf(sc.next()));
                System.out.println("Upisite cenu kombija: ");
                kombi.setCena(sc.nextDouble());
                System.out.println("Upisite snagu kombija(u kW): ");
                kombi.setSnaga(sc.nextInt());
                System.out.println("Upisite godinu proizvodnje: ");
                kombi.setGodiste(sc.nextInt());
                System.out.println("Upisite predjenu kilomterazu: ");
                kombi.setPredjeni_kilometri(sc.nextDouble());
                System.out.println("Upisite tip kombija(Putnicki, Van): ");
                kombi.setTip(TipKombija.valueOf(sc.next()));
                lista.add(kombi);
                upisUFajl();
                lista.clear();
                System.out.println("Da li zelite da unesete novi kombi?");
                st = sc.next();
                if (st.equalsIgnoreCase("da")) {
                    continue;
                } 
                else {
                    break;
                }
            }
        }
        catch(IllegalArgumentException a){
                System.err.println("Unesite validan tip!");
        }
    }

    public static void brisi(int id) throws FileNotFoundException, IOException {
        try {
            File kombi = new File("kombi.txt");    
            File kombi2 = new File("kombi2.txt");
            kombi2.createNewFile();
            PrintWriter pw = new PrintWriter(new FileOutputStream(kombi2, false));
            ArrayList<Kombi> lista5 = new ArrayList<>();
            lista5 = izlistaj();
            lista5.remove(id);

            int i=0;
            for (Kombi lista51 : lista5) {
                pw.println(i + " " + lista51);
                i++;
            }
            pw.close();
            kombi.delete();
            kombi2.renameTo(kombi);
        } 
        catch (FileNotFoundException ex) {
            System.err.println("Fajl je nedostupan");
        }
    }
    
    
    @Override
    public void ispisUKonzoli() {
        try {
            File kombi = new File("kombi.txt");
            Scanner sc = new Scanner(kombi);
            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
            }
            sc.close();
        } 
        catch (FileNotFoundException ex) {
            System.err.println("Fajl je nedostupan");
        }
    }

    @Override
    public void upisUFajl() {
        try {
            File kombi = new File("kombi.txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(kombi, true));
            for (Kombi lista1 : lista) {
                pw.println(nadjiLiniju() + " " + lista1);
            }
            pw.close();
        } 
        catch (FileNotFoundException ex) {
            System.err.println("Fajl je nedostupan");
        }
    }

    public TipKombija getTip() {
        return tip;
    }

    public void setTip(TipKombija tip) {
        this.tip = tip;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Pogon getTip_pogona() {
        return tip_pogona;
    }

    public void setTip_pogona(Pogon tip_pogona) {
        this.tip_pogona = tip_pogona;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getSnaga() {
        return snaga;
    }

    public void setSnaga(int snaga) {
        this.snaga = snaga;
    }

    public int getGodiste() {
        return godiste;
    }

    public void setGodiste(int godiste) {
        this.godiste = godiste;
    }

    public double getPredjeni_kilometri() {
        return predjeni_kilometri;
    }

    public void setPredjeni_kilometri(double predjeni_kilometri) {
        this.predjeni_kilometri = predjeni_kilometri;
    }

    public Kombi(TipKombija tip, String proizvodjac, String model, Pogon tip_pogona, double cena, int snaga, int godiste, double predjeni_kilometri) {
        super(proizvodjac, model, tip_pogona, cena, snaga, godiste, predjeni_kilometri);
        this.tip = tip;
    }

    public Kombi(TipKombija tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return proizvodjac + " " + model + " " + tip_pogona + " " + cena + " " + snaga + " " + godiste + " " + predjeni_kilometri + " " + tip;
    }
    
    
}
