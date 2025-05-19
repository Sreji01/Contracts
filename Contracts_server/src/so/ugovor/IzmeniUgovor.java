/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.ugovor;

import domain.StavkaUgovora;
import domain.Ugovor;
import java.text.SimpleDateFormat;
import java.util.List;
import respository.db.DatabaseBroker;
import so.ApstraktnaSO;

/**
 *
 * @author Marko
 */
public class IzmeniUgovor extends ApstraktnaSO {

    @Override
    public void executeOperation(Object object) throws Exception {
        Ugovor ugovor = (Ugovor) object;
        DatabaseBroker.getInstance().update(ugovor);

        List<StavkaUgovora> stavkeUgovora = ugovor.getStavkeUgovora();
        if (!stavkeUgovora.isEmpty()) {
            StavkaUgovora stavkaUgovora = stavkeUgovora.get(0);
            DatabaseBroker.getInstance().delete(stavkaUgovora);

            int brojac = 0;
            for (StavkaUgovora stavkaUgovora1 : ugovor.getStavkeUgovora()) {
                brojac += 1;
                stavkaUgovora1.setId(brojac);
                stavkaUgovora1.setUgovor(ugovor);
                DatabaseBroker.getInstance().insert((stavkaUgovora1));
            }
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
