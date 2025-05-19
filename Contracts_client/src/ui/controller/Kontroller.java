/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.controller;

import communication.Odgovor;
import communication.Operacije;
import communication.Zahtev;
import domain.Dvorana;
import domain.Igrac;
import domain.Klub;
import domain.Menadzer;
import domain.PrviNastup;
import domain.StavkaUgovora;
import domain.Trener;
import domain.Ugovor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Marko
 */
public class Kontroller {

    private Socket socket;

    private static Kontroller instance;

    private Kontroller() throws Exception {
        try {
            socket = new Socket("localhost", 9000);
        } catch (IOException ex) {
            throw new Exception("Server je ugasen!");
        }
    }

    public static Kontroller getInstance() throws Exception {
        if (instance == null) {
            instance = new Kontroller();
        }
        return instance;
    }

    public void ulogujSe(Menadzer menadzer) throws Exception {
        posaljiZahtev(Operacije.ULOGUJ_SE, menadzer);
    }

    public void odjaviSe(Menadzer ulogovaniMenadzer) throws Exception {
        posaljiZahtev(Operacije.ODJAVI_SE, ulogovaniMenadzer);
    }

    public List<Klub> ucitajListuKlubova() throws Exception {
        return (List<Klub>) posaljiZahtev(Operacije.UCITAJ_LISTU_KLUBOVA, null);
    }

    public void zapamtiIgraca(Igrac igrac) throws Exception {
        posaljiZahtev(Operacije.ZAPAMTI_IGRACA, igrac);
    }

    public List<Igrac> nadjiIgrace(List<Object> objekti) throws Exception {
        return (List<Igrac>) posaljiZahtev(Operacije.NADJI_IGRACE, objekti);
    }

    public Igrac ucitajIgraca(Igrac selektovaniIgrac) throws Exception {
        return (Igrac) posaljiZahtev(Operacije.UCITAJ_IGRACA, selektovaniIgrac);
    }

    public void izmeniIgraca(Igrac igrac) throws Exception {
        posaljiZahtev(Operacije.IZMENI_IGRACA, igrac);
    }

    public List<Dvorana> ucitajListuDvorana() throws Exception {
        return (List<Dvorana>) posaljiZahtev(Operacije.UCITAJ_LISTU_DVORANA, null);
    }

    public void zapamtiKlub(Klub klub) throws Exception {
        posaljiZahtev(Operacije.ZAPAMTI_KLUB, klub);
    }

    public List<PrviNastup> nadjiPrveNastupe(List<Object> objekti) throws Exception {
        return (List<PrviNastup>) posaljiZahtev(Operacije.NADJI_PRVE_NASTUPE, objekti);
    }

    public PrviNastup ucitajPrviNastup(PrviNastup selektovaniNastup) throws Exception {
        return (PrviNastup) posaljiZahtev(Operacije.UCITAJ_PRVI_NASTUP, selektovaniNastup);
    }

    public List<Trener> nadjiTrenere(List<Object> objekti) throws Exception {
        return (List<Trener>) posaljiZahtev(Operacije.NADJI_TRENERE, objekti);
    }

    public Trener ucitajTrenera(Trener selektovaniTrener) throws Exception {
        return (Trener) posaljiZahtev(Operacije.UCITAJ_TRENERA, selektovaniTrener);
    }

    public List<Igrac> ucitajListuIgraca() throws Exception {
        return (List<Igrac>) posaljiZahtev(Operacije.UCITAJ_LISTU_IGRACA, null);
    }

    public void zapamtiUgovor(Ugovor ugovor) throws Exception {
        posaljiZahtev(Operacije.ZAPAMTI_UGOVOR, ugovor);
    }

    public List<Ugovor> nadjiUgovore(List<Object> objekti) throws Exception {
        return (List<Ugovor>) posaljiZahtev(Operacije.NADJI_UGOVORE, objekti);
    }

    public Ugovor ucitajUgovor(Ugovor selektovaniUgovor) throws Exception {
        return (Ugovor) posaljiZahtev(Operacije.UCITAJ_UGOVOR, selektovaniUgovor);
    }

    public List<Ugovor> ucitajListuUgovora() throws Exception {
        return (List<Ugovor>) posaljiZahtev(Operacije.UCITAJ_LISTU_UGOVORA, null);
    }

    private Object posaljiZahtev(Operacije operacija, Object parametar) throws Exception {
        Zahtev zahtev = new Zahtev(operacija, parametar);

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(zahtev);
        ///
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Odgovor odgovor = (Odgovor) in.readObject();
        if (odgovor.getException() == null) {
            return odgovor.getRezultat();
        } else {
            throw odgovor.getException();
        }
    }

    public void izmeniUgovor(Ugovor ugovor) throws Exception {
        posaljiZahtev(Operacije.IZMENI_UGOVOR, ugovor);
    }

    public void obrisiUgovor(Ugovor ugovor) throws Exception {
        posaljiZahtev(Operacije.OBRISI_UGOVOR, ugovor);
    }
}
