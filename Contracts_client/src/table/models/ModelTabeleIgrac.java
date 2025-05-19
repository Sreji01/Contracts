/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table.models;

import domain.Igrac;
import domain.OpstiDomenskiObjekat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marko
 */
public class ModelTabeleIgrac extends AbstractTableModel {

    private List<Igrac> igraci;
    private String[] kolone = {"Ime", "Prezime", "Pozicija", "Datum rodjenja", "Visina"};

    public ModelTabeleIgrac(List<Igrac> igraci) {
        if (igraci != null) {
            this.igraci = igraci;
        }

    }

    @Override
    public int getRowCount() {
        if (igraci != null) {
            return igraci.size();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (igraci != null) {
            Igrac igrac = (Igrac) igraci.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return igrac.getIme();
                case 1:
                    return igrac.getPrezime();
                case 2:
                    return igrac.getPozicija();
                case 3:
                    return getDatumString(igrac.getDatumRodjenja());
                case 4:
                    return igrac.getVisina();
                default:
                    return "N/A";
            }
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    private Object getDatumString(Date datumRodjenja) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String datumRodjenjaString = dateFormat.format(datumRodjenja);
        return datumRodjenjaString;
    }

}
