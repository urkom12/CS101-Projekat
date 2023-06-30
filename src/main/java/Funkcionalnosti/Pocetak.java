/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funkcionalnosti;

import Klase.Automobil;
import Klase.Automobil.aSortCena;
import Klase.Automobil.aSortGodiste;
import Klase.Automobil.aSortKilometraza;
import Klase.Kombi;
import Klase.Kombi.kSortCena;
import Klase.Kombi.kSortGodiste;
import Klase.Kombi.kSortKilometraza;
import Klase.Motor;
import Klase.Motor.mSortCena;
import Klase.Motor.mSortGodiste;
import Klase.Motor.mSortKilometraza;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Urkom
 */
public class Pocetak {
    public static void start() throws FileNotFoundException, IOException{
        Automobil auto = new Automobil();
        Motor motor = new Motor();
        Kombi kombi = new Kombi();
        Scanner sc = new Scanner(System.in);
        String s;
        System.out.println("Sta zelite da uradite sa vozilima?(Upisi, Ispis, Kupi, Sortiraj): ");
        s=sc.next();
        if(s.equalsIgnoreCase("upis") || s.equalsIgnoreCase("upisi")){
            System.out.println("Koji tip vozila zelite da upisete?(Auto, Kombi, Motor)");
            s=sc.next();
            if(s.equalsIgnoreCase("automobil") || s.equalsIgnoreCase("auto")){
                auto.upis();
            }
            else if(s.equalsIgnoreCase("motor") || s.equalsIgnoreCase("motocikl")){
                motor.upis();
            }
            else if(s.equalsIgnoreCase("kombi")){
                kombi.upis();
            }
        }
        else if(s.equalsIgnoreCase("ispis") || s.equalsIgnoreCase("ispisi")){
            System.out.println("Koji tip vozila zelite da ispisete?(Auto, Kombi, Motor)");
            s=sc.next();
            if(s.equalsIgnoreCase("automobil") || s.equalsIgnoreCase("auto")){
                auto.ispisUKonzoli();
            }
            else if(s.equalsIgnoreCase("motor") || s.equalsIgnoreCase("motocikl")){
                motor.ispisUKonzoli();
            }
            else if(s.equalsIgnoreCase("kombi")){
                kombi.ispisUKonzoli();
            }
        }
        else if(s.equalsIgnoreCase("kupi") || s.equalsIgnoreCase("kupovanje")){
            System.out.println("Koji tip vozila zelite da kupite?(Auto, Kombi, Motor)");
            s=sc.next();
            int id;
            if(s.equalsIgnoreCase("automobil") || s.equalsIgnoreCase("auto")){
                System.out.println("Upisite ID vozila.");
                auto.ispisUKonzoli();
                id=sc.nextInt();
                Automobil.brisi(id);
            }
            else if(s.equalsIgnoreCase("motor") || s.equalsIgnoreCase("motocikl")){
                System.out.println("Upisite ID vozila.");
                motor.ispisUKonzoli();
                id=sc.nextInt();
                Motor.brisi(id);
            }
            else if(s.equalsIgnoreCase("kombi")){
                System.out.println("Upisite ID vozila.");
                kombi.ispisUKonzoli();
                id=sc.nextInt();
                Kombi.brisi(id);
            }
            
        }
        else if(s.equalsIgnoreCase("sort") || s.equalsIgnoreCase("sortiranje") || s.equalsIgnoreCase("sortiraj")){
            System.out.println("Koji tip vozila zelite da sortirate?(Auto, Kombi, Motor)");
            s=sc.next();
            int id;
            if(s.equalsIgnoreCase("automobil") || s.equalsIgnoreCase("auto")){
                System.out.println("Po cemu zelite da sortirate?(Cena, Godiste, Kilometraza)");
                s=sc.next();
                if(s.equalsIgnoreCase("cena")){
                    ArrayList<Automobil> lista = Automobil.izlistaj(); 
                    Collections.sort(lista, new aSortCena());
                    System.out.println(lista.toString().replace(",", "").replace("[", "").replace("]", ""));
                }
                if(s.equalsIgnoreCase("godiste")){
                    ArrayList<Automobil> lista = Automobil.izlistaj();
                    Collections.sort(lista, new aSortGodiste());
                    System.out.println(lista.toString().replace(",", "").replace("[", "").replace("]", ""));
                }
                if(s.equalsIgnoreCase("kilometraza")){
                    ArrayList<Automobil> lista = Automobil.izlistaj();
                    Collections.sort(lista, new aSortKilometraza());
                    System.out.println(lista.toString().replace(",", "").replace("[", "").replace("]", ""));
                }
            }
            else if(s.equalsIgnoreCase("motor") || s.equalsIgnoreCase("motocikl")){
                System.out.println("Po cemu zelite da sortirate?(Cena, Godiste, Kilometraza)");
                s=sc.next();
                if(s.equalsIgnoreCase("cena")){
                    ArrayList<Motor> lista = Motor.izlistaj();
                    Collections.sort(lista, new mSortCena());
                    System.out.println(lista.toString().replace(",", "").replace("[", "").replace("]", ""));
                }
                if(s.equalsIgnoreCase("godiste")){
                    ArrayList<Motor> lista = Motor.izlistaj();
                    Collections.sort(lista, new mSortGodiste());
                    System.out.println(lista.toString().replace(",", "").replace("[", "").replace("]", ""));
                }
                if(s.equalsIgnoreCase("kilometraza")){
                    ArrayList<Motor> lista = Motor.izlistaj();
                    Collections.sort(lista, new mSortKilometraza());
                    System.out.println(lista.toString().replace(",", "").replace("[", "").replace("]", ""));
                }
            }
            else if(s.equalsIgnoreCase("kombi")){
                System.out.println("Po cemu zelite da sortirate?(Cena, Godiste, Kilometraza)");
                s=sc.next();
                if(s.equalsIgnoreCase("cena")){
                    ArrayList<Kombi> lista = Kombi.izlistaj();
                    Collections.sort(lista, new kSortCena());
                    System.out.println(lista.toString().replace(",", "").replace("[", "").replace("]", ""));
                }
                if(s.equalsIgnoreCase("godiste")){
                    ArrayList<Kombi> lista = Kombi.izlistaj();
                    Collections.sort(lista, new kSortGodiste());
                    System.out.println(lista.toString().replace(",", "").replace("[", "").replace("]", ""));
                }
                if(s.equalsIgnoreCase("kilometraza")){
                    ArrayList<Kombi> lista = Kombi.izlistaj();
                    Collections.sort(lista, new kSortKilometraza());
                    System.out.println(lista.toString().replace(",", "").replace("[", "").replace("]", ""));
                }
            }
        }
    }
}