/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;
import java.sql.*;
import java.util.List;

/**
 *
 * @author Sreja
 */
public class Klub implements Serializable, OpstiDomenskiObjekat, Comparable<Klub> {

    private int id;
    private String naziv;
    private Date datumOsnivanja;
    private String lokacija;
    private Dvorana dvorana;

    public Klub() {
    }

    public Klub(int id, String naziv, Date datumOsnivanja, String lokacija, Dvorana dvorana) {
        this.id = id;
        this.naziv = naziv;
        this.datumOsnivanja = datumOsnivanja;
        this.lokacija = lokacija;
        this.dvorana = dvorana;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getDatumOsnivanja() {
        return datumOsnivanja;
    }

    public void setDatumOsnivanja(Date datumOsnivanja) {
        this.datumOsnivanja = datumOsnivanja;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public Dvorana getDvorana() {
        return dvorana;
    }

    public void setDvorana(Dvorana dvorana) {
        this.dvorana = dvorana;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Klub other = (Klub) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);
    }
    
    @Override
    public String getTableName() {
        return "KLUB";
    }

    @Override
    public String alijas() {
        return "k";
    }

    @Override
    public String join() {
        return "JOIN DVORANA d ON k.dvoranaId = d.id";
    }

    @Override
    public String getWhereCondition() {
        return "";
    }

    @Override
    public String getPrimaryKey() {
        return "id=" + id;
    }

    @Override
    public OpstiDomenskiObjekat getNewRecord(ResultSet rs) throws SQLException {
        return new Klub(rs.getInt("k.id"), rs.getString("k.naziv"), rs.getDate("k.datumOsnivanja"), rs.getString("k.lokacija"),
                new Dvorana(rs.getInt("d.id"), rs.getString("d.naziv"), rs.getInt("d.kapacitet")));
    }

    @Override
    public String getColumnsForInsert() {
        if (datumOsnivanja != null) {
            return "naziv, datumOsnivanja, lokacija, dvoranaId";
        }
        return "naziv, lokacija, dvoranaId";
    }

    @Override
    public String getParamsForInsert() {
        if (datumOsnivanja != null) {
            return "'" + naziv + "'" + ", " + "'" + getSQLDatum(datumOsnivanja) + "'" + ", " + "'" + lokacija + "'" + ", " + dvorana.getId();
        }
        return "'" + naziv + "'" + ", " + "'" + lokacija + "'" + ", " + dvorana.getId();
    }

    private java.sql.Date getSQLDatum(Date datumOsnivanja) {
        java.sql.Date datumOsnivanjaSQL = new java.sql.Date(datumOsnivanja.getTime());
        return datumOsnivanjaSQL;
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
    public int compareTo(Klub o) {
        int zamba = o.getNaziv().charAt(0);
        return this.getNaziv().charAt(0) - zamba;
    }

}
