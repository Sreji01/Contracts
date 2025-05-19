/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.klub;

import domain.Klub;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import respository.db.DatabaseBroker;
import so.ApstraktnaSO;

/**
 *
 * @author Marko
 */
public class ZapamtiKlub extends ApstraktnaSO {

    private String[] drzave = {"Alabama", "Alaska", "American Samoa", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "District of Columbia", "Florida",
        "Georgia", "Guam", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
        "Minor Outlying Islands", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina",
        "North Dakota", "Northern Mariana Islands", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Puerto Rico", "Rhode Island", "South Carolina", "South Dakota", "Tennessee",
        "Texas", "U.S. Virgin Islands", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};

    @Override
    public void executeOperation(Object object) throws Exception {
        DatabaseBroker.getInstance().insert((Klub) object);
    }

    @Override
    protected void validate(Object object) throws Exception {
        String exceptionString = "\n";
        if (!(object instanceof Klub)) {
            exceptionString += "Objekat nije validan!" + "\n";
        }

        List<String> listaDrzava = new ArrayList<>();
        for (String drzava : drzave) {
            listaDrzava.add(drzava);
        }

        Klub klub = (Klub) object;
        if (!klub.getNaziv().matches("[A-Za-z\s]+") && !klub.getNaziv().matches("[A-Za-z0-9\s]+")) {
            exceptionString += "Naziv kluba ne sme zadrzati nista sem slova i brojeva!" + "\n";
        }
        if (!Character.isUpperCase(klub.getNaziv().charAt(0))) {
            exceptionString += "Pocetno slovo kluba mora biti veliko!" + "\n";
        }
        Date gornjiDatum = null;
        Date donjiDatum = null;
        if (klub.getDatumOsnivanja() != null) {
            String gordnjiDatumString = "02.03.2024";
            SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
            gornjiDatum = format1.parse(gordnjiDatumString);
            String donjiDatumString = "01.01.1891";
            SimpleDateFormat format2 = new SimpleDateFormat("dd.MM.yyyy");
            donjiDatum = format2.parse(donjiDatumString);
            if (klub.getDatumOsnivanja().before(donjiDatum) || klub.getDatumOsnivanja().after(gornjiDatum)) {
                exceptionString += "Datum osnivanja kluba ne sme biti u buducnosti ili pre 01.01.1891!" + "\n";
            }
        }
        if (!listaDrzava.contains(klub.getLokacija())) {
            exceptionString += "Lokacija ne postoji!" + "\n";
        }
        if (!exceptionString.equals("\n")) {
            throw new Exception(exceptionString);
        }
    }
}
