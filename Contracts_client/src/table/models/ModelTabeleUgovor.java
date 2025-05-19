/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table.models;

import domain.Ugovor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marko
 */
public class ModelTabeleUgovor extends AbstractTableModel {

    private List<Ugovor> ugovori = new ArrayList<>();
    private String[] kolone = {"Id", "Igrac", "Bivsi klub", "Novi klub"};

    public ModelTabeleUgovor(List<Ugovor> ugovori) {
        if (ugovori != null) {
            this.ugovori = ugovori;
        }
    }

    @Override
    public int getRowCount() {
        if (ugovori != null) {
            return ugovori.size();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (ugovori != null) {
            Ugovor ugovor = ugovori.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return ugovor.getId();
                case 1:
                    return ugovor.getIgrac().toString();
                case 2:
                    return ugovor.getOdlazeciKlub().toString();
                case 3:
                    return ugovor.getDolazeciKlub().toString();
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

}
