/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.ugovor;

import domain.Igrac;
import domain.StavkaUgovora;
import domain.Ugovor;
import java.sql.*;
import java.text.SimpleDateFormat;
import respository.db.DatabaseBroker;
import so.ApstraktnaSO;
import so.ApstraktnaSO;

/**
 *
 * @author Marko
 */
public class ZapamtiUgovor extends ApstraktnaSO {

    @Override
    public void executeOperation(Object object) throws Exception {
        Ugovor ugovor = (Ugovor) object;
        if (ugovor.getOdlazeciKlub().getNaziv().equals("--Slobodan igrac--") || ugovor.getOdlazeciKlub().getNaziv().equals("--Draftovan--")) {
            ugovor.setOdlazeciKlub(null);
        }
        if (ugovor.getDolazeciKlub().getNaziv().equals("--Slobodan igrac--")) {
            ugovor.setDolazeciKlub(null);
        }
        PreparedStatement ps = DatabaseBroker.getInstance().insert((Ugovor) object);

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        long ugovorId = rs.getLong(1);
        ugovor.setId((int) ugovorId);
        int stavkaUgovoraId = 0;
        for (StavkaUgovora stavkaUgovora : ugovor.getStavkeUgovora()) {
            stavkaUgovoraId = stavkaUgovoraId + 1;
            stavkaUgovora.setUgovor(ugovor);
            stavkaUgovora.setId(stavkaUgovoraId);
            DatabaseBroker.getInstance().insert(stavkaUgovora);
        }
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Ugovor)) {
            throw new Exception("Objekat nije validan!");
        }
        Ugovor ugovor = (Ugovor) object;
        String exceptionString = "\n";
        if (ugovor.getOdlazeciKlub().getNaziv().equals(ugovor.getDolazeciKlub().getNaziv())) {
            if (ugovor.getOdlazeciKlub().getNaziv().equals("--Slobodan igrac--")) {
                exceptionString += "Slobodan igrac ne moze opet postati slobodan igrac!" + "\n";
            } else {
                exceptionString += "Napusteni i pridruzeni klub ne smeju biti isti!" + "\n";
            }
        }
        for (StavkaUgovora stavkaUgovora : ugovor.getStavkeUgovora()) {
            if (stavkaUgovora.getOpis() == null) {
                exceptionString += "Opis ne sme biti prazan!" + "\n";
            } else {
                if (!stavkaUgovora.getOpis().matches("[A-Za-z\s]+")) {
                    exceptionString += "Opis mora sadrzati samo slova!" + "\n";
                }
                if (!Character.isUpperCase(stavkaUgovora.getOpis().charAt(0))) {
                    exceptionString += "Pocetno slovo opisa mora biti veliko!" + "\n";
                }
            }
            if (stavkaUgovora.getDatum() == null) {
                exceptionString += "Datum ne sme biti prazan!" + "\n";
            }
            java.util.Date gornjiDatum = null;
            java.util.Date donjiDatum = null;
            if (stavkaUgovora.getDatum() != null) {
                String gordnjiDatumString = "04.20.2024";
                SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
                gornjiDatum = format1.parse(gordnjiDatumString);
                String donjiDatumString = "01.01.1891";
                SimpleDateFormat format2 = new SimpleDateFormat("dd.MM.yyyy");
                donjiDatum = format2.parse(donjiDatumString);
                if (stavkaUgovora.getDatum().before(donjiDatum)) {
                    exceptionString += "Datum ne sme biti pre 01.01.1891!" + "\n";
                }
                if (stavkaUgovora.getDatum().after(gornjiDatum)) {
                    exceptionString += "Datum ne sme biti u buducnosti!" + "\n";
                }
            }
        }

        if (!exceptionString.equals("\n")) {
            throw new Exception(exceptionString);
        }
    }
}
