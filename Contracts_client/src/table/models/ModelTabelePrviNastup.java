/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table.models;

import domain.Igrac;
import domain.Pozicija;
import domain.PrviNastup;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import ui.controller.Kontroller;

/**
 *
 * @author Marko
 */
public class ModelTabelePrviNastup extends AbstractTableModel {

    private List<PrviNastup> prviNastupi = new ArrayList<>();
    private String[] kolone = {"Igrac", "Datum nastupa"};

    public ModelTabelePrviNastup(List<PrviNastup> prviNastupi) {
        this.prviNastupi = prviNastupi;
    }

    @Override
    public int getRowCount() {
        return prviNastupi.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            PrviNastup prviNastup = prviNastupi.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return prviNastup.getIgrac().toString();
                case 1:
                    return getStringDatum(prviNastup.getDatumNastupa());
                default:
                    return "N/A";
            }
        } catch (Exception ex) {
            Logger.getLogger(ModelTabelePrviNastup.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    private String getStringDatum(Date datumNastupa) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String datumNastupaString = format.format(datumNastupa);
        return datumNastupaString;
    }

}
