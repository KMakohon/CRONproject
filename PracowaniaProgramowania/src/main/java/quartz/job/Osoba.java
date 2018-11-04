package quartz.job;

import java.util.*;
import java.util.ArrayList;
import java.util.Collections;

public class Osoba implements Comparable<Osoba> {
    public static List<Osoba> Ludzie;
    public String miasto;
    public String imie;
    public String nazwisko;
    public String pesel;

    public Osoba(){};

    public Osoba(String m, String i, String n, String p) {
        this.miasto = m;
        this.imie = i;
        this.nazwisko = n;
        this.pesel = p;
    }

    public String dajPesel() {
        return this.pesel;
    }

    public String wypis(){
        return this.miasto + "    " + this.pesel + "    " + this.imie + "    " + this.nazwisko + "\n";
    }

    @Override
    public int compareTo(Osoba o) {
        return this.miasto.compareTo(o.miasto);
    }


    }

