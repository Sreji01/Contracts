/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import domain.Dvorana;
import domain.Igrac;
import domain.Klub;
import domain.Menadzer;
import domain.OpstiDomenskiObjekat;
import domain.PrviNastup;
import domain.StavkaUgovora;
import domain.Trener;
import domain.Ugovor;
import java.util.ArrayList;
import java.util.List;
import server.thread.KlijentskaNit;
import so.igrac.NadjiIgrace;
import so.prvinastup.NadjiPrveNastupe;
import so.trener.NadjiTrenere;
import so.igrac.UcitajIgraca;
import so.dvorana.UcitajListuDvorana;
import so.igrac.UcitajListuIgraca;
import so.klub.UcitajListuKlubova;
import so.prvinastup.UcitajPrviNastup;
import so.trener.UcitajTrenera;
import so.igrac.ZapamtiIgraca;
import so.klub.ZapamtiKlub;
import so.ugovor.ZapamtiUgovor;
import java.lang.Exception;
import javax.swing.JOptionPane;
import so.igrac.IzmeniIgraca;
import so.ugovor.IzmeniUgovor;
import so.ugovor.NadjiUgovore;
import so.ugovor.ObrisiUgovor;
import so.ugovor.UcitajListuUgovora;
import so.ugovor.UcitajUgovor;

/**
 *
 * @author Sreja
 */
public class Controller {

    private static Controller instance;
    private List<Menadzer> trenutnoUlogovani = new ArrayList<>();
    private List<Menadzer> menadzeri = new ArrayList<>();
    private int MaksBrojKlijenata;

    private Controller() {
        Menadzer m1 = new Menadzer(1, "Sreji", "sreji");
        Menadzer m2 = new Menadzer(2, "Zika", "zika");
        Menadzer m3 = new Menadzer(3, "Mile", "mile");
        menadzeri.add(m1);
        menadzeri.add(m2);
        menadzeri.add(m3);

    }

    public int getMaksBrojKlijenata() {
        return MaksBrojKlijenata;
    }

    public void setMaksBrojKlijenata(int MaksBrojKlijenata) {
        this.MaksBrojKlijenata = MaksBrojKlijenata;
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void ulogujSe(Menadzer menadzer) throws Exception {
        for (Menadzer menadzer1 : menadzeri) {
            if (menadzer.equals(menadzer1)) {
                if (!trenutnoUlogovani.contains(menadzer)) {
                    if (trenutnoUlogovani.size() < MaksBrojKlijenata) {
                        trenutnoUlogovani.add(menadzer);
                        return;
                    } else {
                        throw new Exception("Server je pun, pokusajte kasnije!");
                    }
                } else {
                    throw new Exception("Vec ste ulogovani!");
                }
            }
        }
        throw new Exception("Pogresno korisnicko ime ili sifra!");
    }
    
    public void izlogujSe(Menadzer menadzer) {
        trenutnoUlogovani.remove(menadzer);
    }

    public List<OpstiDomenskiObjekat> ucitajListuKlubova() throws Exception {
        UcitajListuKlubova ucitajListuKlubova = new UcitajListuKlubova();
        ucitajListuKlubova.execute(new Klub());
        return ucitajListuKlubova.vratiKlubove();
    }

    public void zapamtiIgraca(Igrac i) throws Exception {
        ZapamtiIgraca zapamtiIgraca = new ZapamtiIgraca();
        zapamtiIgraca.execute(i);
    }

    public List<Igrac> nadjiIgrace(Klub k, List<Igrac> igraci) throws Exception {
        NadjiIgrace nadjiIgrace = new NadjiIgrace();
        Igrac igrac = new Igrac();
        igrac.setKlub(k);
        nadjiIgrace.execute(igrac);
        if (nadjiIgrace.vratiIgrace().isEmpty()) {
            throw new Exception();
        }
        igraci = nadjiIgrace.vratiIgrace();
        return igraci;
    }

    public Igrac ucitajIgraca(Igrac i) throws Exception {
        UcitajIgraca ucitajIgraca = new UcitajIgraca();
        ucitajIgraca.execute(i);
        if (ucitajIgraca.vratiIgraca() == null) {
            throw new Exception();
        }
        return ucitajIgraca.vratiIgraca();
    }

    public void izmeniIgraca(Igrac i1) throws Exception {
        IzmeniIgraca izmeniIgraca = new IzmeniIgraca();
        izmeniIgraca.execute(i1);
    }

    public List<Dvorana> ucitajListuDvorana() throws Exception {
        UcitajListuDvorana ucitajListuDvorana = new UcitajListuDvorana();
        ucitajListuDvorana.execute(new Dvorana());
        return ucitajListuDvorana.vratiDvorane();
    }

    public void zapamtiKlub(Klub k) throws Exception {
        ZapamtiKlub zapamtiKlub = new ZapamtiKlub();
        zapamtiKlub.execute(k);
    }

    public List<PrviNastup> nadjiPrveNastupe(Dvorana d, List<PrviNastup> prviNastupi) throws Exception {
        NadjiPrveNastupe nadjiPrveNastupe = new NadjiPrveNastupe();
        PrviNastup prviNastup = new PrviNastup();
        prviNastup.setDvorana(d);
        nadjiPrveNastupe.execute(prviNastup);
        prviNastupi = nadjiPrveNastupe.vratiPrveNastupe();
        if (prviNastupi.isEmpty()) {
            throw new Exception();
        }
        return prviNastupi;
    }

    public PrviNastup ucitajPrviNastup(PrviNastup pn) throws Exception {
        UcitajPrviNastup ucitajPrviNastup = new UcitajPrviNastup();
        ucitajPrviNastup.execute(pn);
        if (ucitajPrviNastup.vratiPrviNastup() == null) {
            throw new Exception();
        }
        return ucitajPrviNastup.vratiPrviNastup();
    }

    public List<Trener> nadjiTrenere(int minimalanBrojTitula, List<Trener> treneri) throws Exception {
        NadjiTrenere nadjiTrenere = new NadjiTrenere();
        Trener trener = new Trener();
        trener.setBrojTitula(minimalanBrojTitula);
        nadjiTrenere.execute(trener);
        treneri = nadjiTrenere.vratiTrenere();
        if (treneri.isEmpty()) {
            throw new Exception();
        }
        return treneri;
    }

    public Trener ucitajTrenera(Trener t) throws Exception {
        UcitajTrenera ucitajTrenera = new UcitajTrenera();
        ucitajTrenera.execute(t);
        if (ucitajTrenera.vratiTrenera() == null) {
            throw new Exception();
        }
        return ucitajTrenera.vratiTrenera();
    }

    public List<Igrac> ucitajListuIgraca() throws Exception {
        UcitajListuIgraca ucitajListuIgraca = new UcitajListuIgraca();
        ucitajListuIgraca.execute(new Igrac());
        return ucitajListuIgraca.vratiIgrace();
    }

    public void zapamtiUgovor(Ugovor u) throws Exception {
        ZapamtiUgovor zapamtiUgovor = new ZapamtiUgovor();
        zapamtiUgovor.execute(u);
    }

    public List<Ugovor> nadjiUgovore(String imePrezime, List<Ugovor> ugovori) throws Exception {
        NadjiUgovore nadjiUgovore = new NadjiUgovore();
        Ugovor u = new Ugovor();
        Igrac i = new Igrac();
        i.setIme(imePrezime);
        i.setPrezime(imePrezime);
        if (imePrezime.contains(" ")) {
            String[] delovi = imePrezime.split(" ");
            String deo = delovi[0];
            i.setIme(deo);
            i.setPrezime(deo);
        }
        u.setIgrac(i);
        nadjiUgovore.execute(u);
        ugovori = nadjiUgovore.vratiUgovore();
        if (ugovori.isEmpty()) {
            throw new Exception();
        }
        return ugovori;
    }

    public Ugovor ucitajUgovor(Ugovor u) throws Exception {
        UcitajUgovor ucitajUgovor = new UcitajUgovor();
        ucitajUgovor.execute(u);
        if (ucitajUgovor.vratiUgovor() == null) {
            throw new Exception();
        }
        return ucitajUgovor.vratiUgovor();
    }

    public List<Ugovor> ucitajListuUgovora() throws Exception {
        UcitajListuUgovora ucitajListuUgovora = new UcitajListuUgovora();
        ucitajListuUgovora.execute(new Ugovor());
        return ucitajListuUgovora.vratiUgovore();
    }

    public void izmeniUgovor(Ugovor u) throws Exception {
        IzmeniUgovor izmeniUgovor = new IzmeniUgovor();
        izmeniUgovor.execute(u);
    }

    public void obrisiUgovor(Ugovor u) throws Exception {
        ObrisiUgovor obrisiUgovor = new ObrisiUgovor();
        obrisiUgovor.execute(u);
    }
}
