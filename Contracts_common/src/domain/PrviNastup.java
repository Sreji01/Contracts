/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Sreja
 */
public class PrviNastup implements Serializable, OpstiDomenskiObjekat, Comparable<PrviNastup> {

    private Igrac igrac;
    private Dvorana dvorana;
    private Date datumNastupa;
    private int brojPoena;

    public PrviNastup() {
    }

    public PrviNastup(Igrac igrac, Dvorana dvorana, Date datumNastupa, int brojPoena) {
        this.igrac = igrac;
        this.dvorana = dvorana;
        this.datumNastupa = datumNastupa;
        this.brojPoena = brojPoena;
    }

    public Igrac getIgrac() {
        return igrac;
    }

    public void setIgrac(Igrac igrac) {
        this.igrac = igrac;
    }

    public Dvorana getDvorana() {
        return dvorana;
    }

    public void setDvorana(Dvorana dvorana) {
        this.dvorana = dvorana;
    }

    public Date getDatumNastupa() {
        return datumNastupa;
    }

    public void setDatumNastupa(Date datumNastupa) {
        this.datumNastupa = datumNastupa;
    }

    public int getBrojPoena() {
        return brojPoena;
    }

    public void setBrojPoena(int brojPoena) {
        this.brojPoena = brojPoena;
    }

    @Override
    public String toString() {
        return "Igrac: " + igrac.toString() + " je debitovao datuma: " + getStringDatum(datumNastupa) + " u dvorani: " + dvorana.toString() + " i postigao " + brojPoena + "poen(a)";
    }

    private String getStringDatum(Date datumNastupa) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String datumNastupaString = dateFormat.format(datumNastupa);
        return datumNastupaString;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PrviNastup other = (PrviNastup) obj;
        if (!Objects.equals(this.igrac, other.igrac)) {
            return false;
        }
        return Objects.equals(this.dvorana, other.dvorana);
    }

    @Override
    public String getTableName() {
        return "PRVI_NASTUP";
    }

    @Override
    public String alijas() {
        return "pn";
    }

    @Override
    public String join() {
        return "JOIN IGRAC i ON pn.igracId = i.id LEFT JOIN KLUB k ON i.klubId = k.id LEFT JOIN DVORANA d ON k.dvoranaId = d.id JOIN DVORANA dv ON pn.dvoranaId = dv.id";
    }

    @Override
    public String getWhereCondition() {
        return "WHERE pn.dvoranaId= " + dvorana.getId() + "";
    }

    @Override
    public String getPrimaryKey() {
        return "pn.igracID=" + igrac.getId() + " AND " + "pn.dvoranaId=" + dvorana.getId();
    }

    @Override
    public OpstiDomenskiObjekat getNewRecord(ResultSet rs) throws SQLException {
        if (rs.getString("k.naziv") == null) {
            return new PrviNastup(new Igrac(rs.getInt("i.id"), rs.getString("i.ime"), rs.getString("i.prezime"), Pozicija.valueOf(rs.getString("i.pozicija")), getUtilDatum(rs.getDate("i.datumRodjenja")), rs.getInt("i.visina"),
                    null),
                    new Dvorana(rs.getInt("dv.id"), rs.getString("dv.naziv"), rs.getInt("dv.kapacitet")), getUtilDatum(rs.getDate("pn.datumNastupa")), rs.getInt("pn.brojPoena"));
        }
        return new PrviNastup(new Igrac(rs.getInt("i.id"), rs.getString("i.ime"), rs.getString("i.prezime"), Pozicija.valueOf(rs.getString("i.pozicija")), getUtilDatum(rs.getDate("i.datumRodjenja")), rs.getInt("i.visina"),
                new Klub(rs.getInt("k.id"), rs.getString("k.naziv"), getUtilDatum(rs.getDate("k.datumOsnivanja")), rs.getString("k.lokacija"),
                        new Dvorana(rs.getInt("d.id"), rs.getString("d.naziv"), rs.getInt("d.kapacitet")))),
                new Dvorana(rs.getInt("dv.id"), rs.getString("dv.naziv"), rs.getInt("dv.kapacitet")), getUtilDatum(rs.getDate("pn.datumNastupa")), rs.getInt("pn.brojPoena"));
    }

    private Date getUtilDatum(java.sql.Date datumNastupa) {
        Date datumNastupaUTIL = new Date(datumNastupa.getTime());
        return datumNastupaUTIL;
    }

    @Override
    public String getColumnsForInsert() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getParamsForInsert() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String setAtrValue() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getDeleteCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int compareTo(PrviNastup o) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(o.getDatumNastupa());
        int zamba = calendar.get(Calendar.YEAR);
        calendar.setTime(this.getDatumNastupa());
        int lumba = calendar.get(Calendar.YEAR);
        return lumba - zamba;
    }
}
