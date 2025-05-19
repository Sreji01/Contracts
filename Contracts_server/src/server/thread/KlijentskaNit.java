/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.thread;

import communication.Odgovor;

import communication.Zahtev;
import domain.Dvorana;
import domain.Igrac;
import domain.Klub;
import domain.Menadzer;
import domain.PrviNastup;
import domain.Trener;
import domain.Ugovor;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import logic.Controller;

/**
 *
 * @author Sreja
 */
public class KlijentskaNit extends Thread {

    private Socket clientSocket;

    public KlijentskaNit() {
    }

    public KlijentskaNit(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            while (!clientSocket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                Zahtev zahtev = (Zahtev) in.readObject();
                Odgovor odgovor = obradiZahtev(zahtev);
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                out.writeObject(odgovor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Odgovor obradiZahtev(Zahtev zahtev) {
        Odgovor odgovor = new Odgovor();
        try {
            switch (zahtev.getOperacija()) {
                case ULOGUJ_SE:
                    Controller.getInstance().ulogujSe((Menadzer) zahtev.getParametar());
                    break;
                case ODJAVI_SE:
                    Controller.getInstance().izlogujSe((Menadzer) zahtev.getParametar());
                case UCITAJ_LISTU_KLUBOVA:
                    odgovor.setRezultat(Controller.getInstance().ucitajListuKlubova());
                    break;
                case UCITAJ_LISTU_DVORANA:
                    odgovor.setRezultat(Controller.getInstance().ucitajListuDvorana());
                    break;
                case NADJI_IGRACE:
                    List<Object> objekti = (List<Object>) zahtev.getParametar();
                    List<Igrac> igraci = (List<Igrac>) objekti.get(0);
                    Klub klub = (Klub) objekti.get(1);
                    odgovor.setRezultat(Controller.getInstance().nadjiIgrace(klub, igraci));
                    break;
                case NADJI_PRVE_NASTUPE:
                    List<Object> objekti1 = (List<Object>) zahtev.getParametar();
                    List<PrviNastup> prviNastupi = (List<PrviNastup>) objekti1.get(0);
                    Dvorana dvorana = (Dvorana) objekti1.get(1);
                    odgovor.setRezultat(Controller.getInstance().nadjiPrveNastupe(dvorana, prviNastupi));
                    break;
                case UCITAJ_IGRACA:
                    odgovor.setRezultat(Controller.getInstance().ucitajIgraca((Igrac) zahtev.getParametar()));
                    break;
                case UCITAJ_PRVI_NASTUP:
                    odgovor.setRezultat(Controller.getInstance().ucitajPrviNastup((PrviNastup) zahtev.getParametar()));
                    break;
                case ZAPAMTI_IGRACA:
                    Controller.getInstance().zapamtiIgraca((Igrac) zahtev.getParametar());
                    break;
                case ZAPAMTI_KLUB:
                    Controller.getInstance().zapamtiKlub((Klub) zahtev.getParametar());
                    break;
                case IZMENI_IGRACA:
                    Controller.getInstance().izmeniIgraca((Igrac) zahtev.getParametar());
                    break;
                case NADJI_TRENERE:
                    List<Object> objekti2 = (List<Object>) zahtev.getParametar();
                    List<Trener> treneri = (List<Trener>) objekti2.get(0);
                    int minimalanBrojTitula = (int) objekti2.get(1);
                    odgovor.setRezultat(Controller.getInstance().nadjiTrenere(minimalanBrojTitula, treneri));
                    break;
                case UCITAJ_TRENERA:
                    odgovor.setRezultat(Controller.getInstance().ucitajTrenera((Trener) zahtev.getParametar()));
                    break;
                case UCITAJ_LISTU_IGRACA:
                    odgovor.setRezultat(Controller.getInstance().ucitajListuIgraca());
                    break;
                case ZAPAMTI_UGOVOR:
                    Controller.getInstance().zapamtiUgovor((Ugovor) zahtev.getParametar());
                    break;
                case NADJI_UGOVORE:
                    List<Object> objekti3 = (List<Object>) zahtev.getParametar();
                    List<Ugovor> ugovori = (List<Ugovor>) objekti3.get(0);
                    String imePrezime = (String) objekti3.get(1);
                    odgovor.setRezultat(Controller.getInstance().nadjiUgovore(imePrezime, ugovori));
                    break;
                case UCITAJ_UGOVOR:
                    odgovor.setRezultat(Controller.getInstance().ucitajUgovor((Ugovor) zahtev.getParametar()));
                    break;
                case UCITAJ_LISTU_UGOVORA:
                    odgovor.setRezultat(Controller.getInstance().ucitajListuUgovora());
                    break;
                case IZMENI_UGOVOR:
                    Controller.getInstance().izmeniUgovor((Ugovor) zahtev.getParametar());
                    break;
                case OBRISI_UGOVOR:
                    Controller.getInstance().obrisiUgovor((Ugovor) zahtev.getParametar());
                    break;
                default:
                    throw new AssertionError();
            }

        } catch (Exception e) {
            odgovor.setException(e);
        }
        return odgovor;
    }

}
