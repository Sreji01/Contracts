/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table.models;

import domain.Trener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marko
 */
public class ModelTabeleTrener extends AbstractTableModel{
    private List<Trener> listaTrenera = new ArrayList<>();
    private String[] kolone = {"Ime", "Prezime", "Datum rodjenja"};
    
    public ModelTabeleTrener(List<Trener> listaTrenera){
        this.listaTrenera = listaTrenera;
    }

    @Override
    public int getRowCount() {
        if(listaTrenera != null){
        return listaTrenera.size();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(listaTrenera !=null){
            Trener trener = listaTrenera.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return trener.getIme();
                case 1:
                    return trener.getPrezime();
                case 2:
                    return getDatumString(trener.getDatumRodjenja());
                default:
                    return "N/A";
            }
        }
        return null;
    }
    
     private Object getDatumString(Date datumRodjenja) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String datumRodjenjaString = dateFormat.format(datumRodjenja);
        return datumRodjenjaString;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    
}
