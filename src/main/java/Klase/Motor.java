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
enum TipMotora{
    Quad,
    Sport,
    Scooter
}
public class Motor extends Vozilo implements Operacije{
    public static class mSortCena implements Comparator<Motor>{
        @Override
        public int compare(Motor o1, Motor o2) {
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
    public static class mSortGodiste implements Comparator<Motor>{
        @Override
        public int compare(Motor o1, Motor o2) {
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
    public static class mSortKilometraza implements Comparator<Motor>{
        @Override
        public int compare(Motor o1, Motor o2) {
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
    List<Motor> lista = new ArrayList<>();
    public TipMotora tip;

    public Motor(TipMotora tip, String proizvodjac, String model, Pogon tip_pogona, double cena, int snaga, int godiste, double predjeni_kilometri) {
        super(proizvodjac, model, tip_pogona, cena, snaga, godiste, predjeni_kilometri);
        this.tip = tip;
    }

    public Motor() {
    }

    
    public TipMotora getTip() {
        return tip;
    }

    public void setTip(TipMotora tip) {
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
    
    
    
    @Override
    public void upis() {
        try{
            Scanner sc = new Scanner(System.in);
        String st;
        Motor motor = new Motor();
        while (true) {
            System.out.println("Upisite ime proizvodjaca: ");
            motor.setProizvodjac(sc.next());
            System.out.println("Upisite model motora: ");
            motor.setModel(sc.next());
            System.out.println("Upisite pogon na koji ide motor(Dizel, Benzin): ");
            motor.setTip_pogona(Pogon.valueOf(sc.next()));
            System.out.println("Upisite cenu motora: ");
            motor.setCena(sc.nextDouble());
            System.out.println("Upisite snagu motora(u kW): ");
            motor.setSnaga(sc.nextInt());
            System.out.println("Upisite godinu proizvodnje: ");
            motor.setGodiste(sc.nextInt());
            System.out.println("Upisite predjenu kilomterazu: ");
            motor.setPredjeni_kilometri(sc.nextDouble());
            System.out.println("Upisite tip motora(Quad, Sport, Scooter): ");
            motor.setTip(TipMotora.valueOf(sc.next()));
            lista.add(motor);
            upisUFajl();
            lista.clear();
            System.out.println("Da li zelite da unesete novi motor?");
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

    @Override
    public void ispisUKonzoli() {
        try {
            File motori = new File("motori.txt");
            Scanner sc = new Scanner(motori);
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
            File motori = new File("motori.txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(motori, true));
            for (Motor lista1 : lista) {
                pw.println(nadjiLiniju() + " " + lista1);
            }
            pw.close();
        } 
        catch (FileNotFoundException ex) {
            System.err.println("Fajl je nedostupan");
        }
    }
    
    private static int nadjiLiniju() throws FileNotFoundException {
        File motori = new File("motori.txt");
        Scanner sc = new Scanner(motori);
        int i=0;
        while (sc.hasNextLine()) {
            sc.nextLine();
            i++;
        }
        sc.close();
        return i;
   }

    
    public static ArrayList<Motor> izlistaj() throws FileNotFoundException, IOException {
        ArrayList<Motor> lista = new ArrayList<>();
        try {
            String input;
            BufferedReader br = new BufferedReader(new FileReader("motori.txt"));
            while ((input = br.readLine()) != null) {
                String[] s = input.split(" ");
                    if (s.length == 9) {
                        lista.add(new Motor(TipMotora.valueOf(s[8]), s[1], s[2], Pogon.valueOf(s[3]), Double.parseDouble(s[4]), Integer.parseInt(s[5]), Integer.parseInt(s[6]), Double.parseDouble(s[7])));
                    }
                    else if (s.length == 10) {
                        lista.add(new Motor(TipMotora.valueOf(s[9]), s[1], s[2] + " " + s[3], Pogon.valueOf(s[4]), Double.parseDouble(s[5]), Integer.parseInt(s[6]), Integer.parseInt(s[7]), Double.parseDouble(s[8])));
                    }
                }
                    br.close();
                }
                catch (FileNotFoundException e) {
                    System.err.println("Fajl je nedostupan");
                }
                return lista;
        }
    
    public static void brisi(int id) throws FileNotFoundException, IOException {
        try {
            File motori = new File("motori.txt");    
            File motori2 = new File("motori2.txt");
            motori2.createNewFile();
            PrintWriter pw = new PrintWriter(new FileOutputStream(motori2, false));
            ArrayList<Motor> lista5 = new ArrayList<>();
            lista5 = izlistaj();
            lista5.remove(id);

            int i=0;
            for (Motor lista51 : lista5) {
                pw.println(i + " " + lista51);
                i++;
            }
            pw.close();
            motori.delete();
            motori2.renameTo(motori);
        } 
        catch (FileNotFoundException ex) {
            System.err.println("Fajl je nedostupan");
        }
    }

    @Override
    public String toString() {
        return proizvodjac + " " + model + " " + tip_pogona + " " + cena + " " + snaga + " " + godiste + " " + predjeni_kilometri + " " + tip;
    }
    
}
