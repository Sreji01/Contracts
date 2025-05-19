/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.igrac;

import domain.Igrac;
import java.text.SimpleDateFormat;
import java.util.Date;
import respository.db.DatabaseBroker;
import so.ApstraktnaSO;

/**
 *
 * @author Marko
 */
public class IzmeniIgraca extends ApstraktnaSO {

    @Override
    public void executeOperation(Object object) throws Exception {
        DatabaseBroker.getInstance().update((Igrac) object);
    }

    @Override
    protected void validate(Object object) throws Exception {
        String exceptionString = "\n";
        if (!(object instanceof Igrac)) {
            exceptionString += "Objekat nije validan!" + "\n";
        }
        Igrac noviIgrac = (Igrac) object;
        if (!noviIgrac.getIme().matches("[A-Za-z]+") || !noviIgrac.getPrezime().matches("[A-Za-z]+")) {
            exceptionString += "Ime i prezime ne smeju sadrzati nista sem slova!" + "\n";
        }
        if (!Character.isUpperCase(noviIgrac.getIme().charAt(0)) || !Character.isUpperCase(noviIgrac.getPrezime().charAt(0))) {
            exceptionString += "Pocetno slovo imena i prezimena igraca mora biti veliko!" + "\n";
        }
        String datumString = "24.02.2006";
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date granicniDatum = format.parse(datumString);
        if (noviIgrac.getDatumRodjenja().after(granicniDatum)) {
            exceptionString += "Igrac ne sme imati manje od 18 godina!" + "\n";
        }
        String datumString1 = "22.04.1974";
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        Date granicniDatum1 = format1.parse(datumString1);
        if (noviIgrac.getDatumRodjenja().before(granicniDatum1)) {
            exceptionString += "Igrac ne sme imati vise  od 50 godina!" + "\n";
        }
        if (noviIgrac.getVisina() < 100 || noviIgrac.getVisina() > 260) {
            exceptionString += "Igraceva visina ne sme biti manja od 100 i veca od 260 centimetra!" + "\n";
        }
        if (!(object instanceof Igrac) || (!noviIgrac.getIme().matches("[A-Za-z]+") || !noviIgrac.getPrezime().matches("[A-Za-z]+"))
                || (!Character.isUpperCase(noviIgrac.getIme().charAt(0)) || !Character.isUpperCase(noviIgrac.getPrezime().charAt(0)))
                || noviIgrac.getVisina() == 0 || noviIgrac.getDatumRodjenja().after(granicniDatum) || 
                noviIgrac.getDatumRodjenja().before(granicniDatum1) || noviIgrac.getVisina() < 100 || noviIgrac.getVisina() > 260) {
            throw new Exception(exceptionString);
        }
    }
}
