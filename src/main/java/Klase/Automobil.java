/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klase;

import Klase.Vozilo.Pogon;
import Funkcionalnosti.Operacije;
import Klase.Vozilo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Scanner;


/**
 *
 * @author Urkom
 */
enum Karoserija {
    Hatchback,
    Limuzina,
    Karavan,
    Kupe
}

public class Automobil extends Vozilo implements Operacije {

    public static class aSortCena implements Comparator<Automobil> {

        @Override
        public int compare(Automobil o1, Automobil o2) {
            if (o1.getCena() < o2.getCena()) {
                return -1;
            } 
            else if (o1.getCena() == o2.getCena()) {
                return 0;
            } 
            else {
                return 1;
            }
        }
    }

    public static class aSortGodiste implements Comparator<Automobil> {

        @Override
        public int compare(Automobil o1, Automobil o2) {
            if (o1.getGodiste() < o2.getGodiste()) {
                return -1;
            } 
            else if (o1.getGodiste() == o2.getGodiste()) {
                return 0;
            } 
            else {
                return 1;
            }
        }

    }

    public static class aSortKilometraza implements Comparator<Automobil> {

        @Override
        public int compare(Automobil o1, Automobil o2) {
            if (o1.getPredjeni_kilometri() < o2.getPredjeni_kilometri()) {
                return -1;
            } 
            else if (o1.getPredjeni_kilometri() == o2.getPredjeni_kilometri()) {
                return 0;
            } 
            else {
                return 1;
            }
        }

    }
    protected Karoserija tip_karoserije;
    List<Automobil> lista = new ArrayList<>();

    public Automobil(Karoserija tip_karoserije, String proizvodjac, String model, Pogon tip_pogona, double cena, int snaga, int godiste, double predjeni_kilometri) {
        super(proizvodjac, model, tip_pogona, cena, snaga, godiste, predjeni_kilometri);
        this.tip_karoserije = tip_karoserije;
    }

    public void upisUFajl() {
        try {
            File automobili = new File("automobili.txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(automobili, true));
            for (Automobil lista1 : lista) {
                pw.println(nadjiLiniju() + " " + lista1);
            }
            pw.close();
        } 
        catch (FileNotFoundException ex) {
            System.err.println("Fajl je nedostupan");
        }
    }

    public static int nadjiLiniju() throws FileNotFoundException {
        File automobili = new File("automobili.txt");
        Scanner sc = new Scanner(automobili);
        int i = 0;
        while (sc.hasNextLine()) {
            sc.nextLine();
            i++;
        }
        sc.close();
        return i;
    }

    @Override
    public void ispisUKonzoli() {
        try {
            File automobili = new File("automobili.txt");
            Scanner sc = new Scanner(automobili);
            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
            }
            sc.close();
        } 
        catch (FileNotFoundException ex) {
            System.err.println("Fajl je nedostupan");
        }
    }

    public static ArrayList<Automobil> izlistaj() throws FileNotFoundException, IOException {
        ArrayList<Automobil> lista = new ArrayList<>();
        try {
            String input;
            BufferedReader br = new BufferedReader(new FileReader("automobili.txt"));
            while ((input = br.readLine()) != null) {
                String[] s = input.split(" ");
                if (s.length == 9) {
                    lista.add(new Automobil(Karoserija.valueOf(s[8]), s[1], s[2], Pogon.valueOf(s[3]), Double.parseDouble(s[4]), Integer.parseInt(s[5]), Integer.parseInt(s[6]), Double.parseDouble(s[7])));
                } else if (s.length == 10) {
                    lista.add(new Automobil(Karoserija.valueOf(s[9]), s[1], s[2] + " " + s[3], Pogon.valueOf(s[4]), Double.parseDouble(s[5]), Integer.parseInt(s[6]), Integer.parseInt(s[7]), Double.parseDouble(s[8])));
                }
            }
            br.close();
        } 
        catch (FileNotFoundException e) {
            System.err.println("Fajl je nedostupan");
        }
        return lista;
    }

    @Override
    public void upis() {
        try {
            Scanner sc = new Scanner(System.in);
            String st;
            Automobil auto = new Automobil();
            while (true) {
                System.out.println("Upisite ime proizvodjaca: ");
                auto.setProizvodjac(sc.nextLine());
                System.out.println("Upisite model automobila: ");
                auto.setModel(sc.nextLine());
                System.out.println("Upisite pogon na koji ide automobil(Dizel, Benzin, Gas): ");
                auto.setTip_pogona(Pogon.valueOf(sc.next()));
                System.out.println("Upisite cenu automobila: ");
                auto.setCena(sc.nextDouble());
                System.out.println("Upisite snagu automobila(u kW): ");
                auto.setSnaga(sc.nextInt());
                System.out.println("Upisite godinu proizvodnje: ");
                auto.setGodiste(sc.nextInt());
                System.out.println("Upisite predjenu kilomterazu: ");
                auto.setPredjeni_kilometri(sc.nextDouble());
                System.out.println("Upisite tip karoserije(Hatchback, Limuzina, Karavan, Kupe): ");
                auto.setTip_karoserije(Karoserija.valueOf(sc.next()));
                lista.add(auto);
                upisUFajl();
                lista.clear();
                System.out.println("Da li zelite da unesete novi automobil?");
                st = sc.next();
                if (st.equalsIgnoreCase("da")) {
                    continue;
                } else {
                    break;
                }
            }
        } catch (IllegalArgumentException a) {
            System.err.println("Unesite validan tip!");
        }
    }

    public static void brisi(int id) throws FileNotFoundException, IOException {

        try {
            File automobili = new File("automobili.txt");
            File automobili2 = new File("automobili2.txt");
            automobili2.createNewFile();
            PrintWriter pw2 = new PrintWriter(new FileOutputStream(automobili2, false));
            ArrayList<Automobil> lista5 = new ArrayList<>();
            lista5 = izlistaj();
            lista5.remove(id);

            int i = 0;
            for (Automobil lista51 : lista5) {
                pw2.println(i + " " + lista51);
                i++;
            }
            pw2.close();
            automobili.delete();
            automobili2.renameTo(automobili);
        } catch (FileNotFoundException ex) {
            System.err.println("Fajl je nedostupan");
        }
    }

    public Automobil() {
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public void pogon(String pogon) {
        this.tip_pogona = tip_pogona;
    }

    public void karoserija(String karoserija) {
        this.tip_karoserije = tip_karoserije;
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

    public Karoserija getTip_karoserije() {
        return tip_karoserije;
    }

    public void setTip_karoserije(Karoserija tip_karoserije) {
        this.tip_karoserije = tip_karoserije;
    }

    @Override
    public String toString() {
        return proizvodjac + " " + model + " " + tip_pogona + " " + cena + " " + snaga + " " + godiste + " " + predjeni_kilometri + " " + tip_karoserije + "\n";
    }

}
