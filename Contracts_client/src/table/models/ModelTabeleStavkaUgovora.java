/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table.models;

import domain.StavkaUgovora;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import ui.form.ugovor.ModifikacijaUgovoraForma;
import ui.form.ugovor.ObicanTransferForma;

/**
 *
 * @author Marko
 */
public class ModelTabeleStavkaUgovora extends AbstractTableModel {

    private List<StavkaUgovora> stavkeUgovora = new ArrayList<>();
    private String[] kolone = {"Id", "Opis", "Datum"};
    private ObicanTransferForma otf;
    private ModifikacijaUgovoraForma muf;

    public ModelTabeleStavkaUgovora(List<StavkaUgovora> stavkeUgovora) {
        this.stavkeUgovora = stavkeUgovora;
    }

    public List<StavkaUgovora> getStavkeUgovora() {
        return stavkeUgovora;
    }

    public void setStavkeUgovora(List<StavkaUgovora> stavkeUgovora) {
        this.stavkeUgovora = stavkeUgovora;
    }

    public ModifikacijaUgovoraForma getMuf() {
        return muf;
    }

    public void setMuf(ModifikacijaUgovoraForma muf) {
        this.muf = muf;
    }

    public ObicanTransferForma getOtf() {
        return otf;
    }

    public void setOtf(ObicanTransferForma otf) {
        this.otf = otf;
    }

    @Override
    public int getRowCount() {
        if (stavkeUgovora != null) {
            return stavkeUgovora.size();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (stavkeUgovora != null) {
            StavkaUgovora stavkaUgovora = stavkeUgovora.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    switch (rowIndex) {
                        default:
                            return rowIndex + 1;
                    }
                case 1:
                    return stavkaUgovora.getOpis();
                case 2:
                    if (stavkaUgovora.getDatum() != null) {
                        return getDatumString(stavkaUgovora.getDatum());
                    }
                    return null;
                default:
                    return "N/A";
            }

        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (stavkeUgovora != null) {
            StavkaUgovora stavkaUgovora = stavkeUgovora.get(rowIndex);
            if (stavkaUgovora != null) {
                switch (columnIndex) {
                    case 1:
                        stavkaUgovora.setOpis((String) aValue);
                        break;
                    case 2: {
                        try {
                            stavkaUgovora.setDatum(getUtilDatum((String) aValue));
                        } catch (Exception ex) {
                            String warningString = "Datum nije u dobrom formatu";                       
                            if (otf != null) {
                                otf.prikaziUpozorenje(warningString);
                            }
                            if(muf != null){
                                muf.prikaziUpozorenje(warningString);
                            }                           
                            stavkaUgovora.setDatum(null);
                        }
                    }
                    break;

                    default:
                        break;
                }
            }
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    private Date getUtilDatum(String string) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return format.parse(string);
        } catch (ParseException ex) {
            throw new Exception();
        }
    }

    public void dodajStavku(StavkaUgovora stavkaUgovora) {
        stavkeUgovora.add(stavkaUgovora);
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (stavkeUgovora != null) {
            StavkaUgovora stavkaUgovora = stavkeUgovora.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return false;
                case 1:
                    return true;
                case 2:
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    private String getDatumString(Date datum) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(datum);
    }

    public void obrisiStavku(int selektovaniRed) {
        stavkeUgovora.remove(selektovaniRed);
        fireTableDataChanged();
    }

}
