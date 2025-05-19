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
public class Igrac implements Serializable, OpstiDomenskiObjekat, Comparable<Igrac> {

    private int id;
    private String ime;
    private String prezime;
    private Pozicija pozicija;
    private Date datumRodjenja;
    private int visina;
    private Klub klub;

    public Igrac() {
    }

    public Igrac(int id, String ime, String prezime, Pozicija pozicija, Date datumRodjenja, int visina, Klub klub) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.pozicija = pozicija;
        this.datumRodjenja = datumRodjenja;
        this.visina = visina;
        this.klub = klub;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Pozicija getPozicija() {
        return pozicija;
    }

    public void setPozicija(Pozicija pozicija) {
        this.pozicija = pozicija;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public int getVisina() {
        return visina;
    }

    public void setVisina(int visina) {
        this.visina = visina;
    }

    public Klub getKlub() {
        return klub;
    }

    public void setKlub(Klub klub) {
        this.klub = klub;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
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
        final Igrac other = (Igrac) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        return Objects.equals(this.datumRodjenja, other.datumRodjenja);
    }

    @Override
    public String getTableName() {
        return "IGRAC";
    }

    @Override
    public String alijas() {
        return "i";
    }

    @Override
    public String join() {
        return "LEFT JOIN KLUB K ON i.klubId = k.id LEFT JOIN DVORANA d ON k.dvoranaId = d.id";
    }

    @Override
    public String getWhereCondition() {
        if (klub == null) {
            return "";
        }
        if (klub.getNaziv().equals("--Slobodan igrac--")) {
            return "WHERE klubId IS" + " NULL";
        }
        return "WHERE klubId=" + klub.getId();
    }

    @Override
    public String getPrimaryKey() {
        return "i.id=" + id;
    }

    @Override
    public OpstiDomenskiObjekat getNewRecord(ResultSet rs) throws SQLException {
        if (rs.getInt("i.klubId") != 0) {
            return new Igrac(rs.getInt("i.id"), rs.getString("i.ime"), rs.getString("i.prezime"), Pozicija.valueOf(rs.getString("i.pozicija")), getUtilDatum(rs.getDate("i.datumRodjenja")), rs.getInt("i.visina"),
                    new Klub(rs.getInt("k.id"), rs.getString("k.naziv"), getUtilDatum(rs.getDate("k.datumOsnivanja")), rs.getString("k.lokacija"),
                            new Dvorana(rs.getInt("d.id"), rs.getString("d.naziv"), rs.getInt("d.kapacitet"))));
        }
        return new Igrac(rs.getInt("i.id"), rs.getString("i.ime"), rs.getString("i.prezime"), Pozicija.valueOf(rs.getString("i.pozicija")), getUtilDatum(rs.getDate("i.datumRodjenja")), rs.getInt("i.visina"),
                null);
    }

    private Date getUtilDatum(java.sql.Date datumRodjenja) {
        Date datumRodjenjaUTIL = new Date(datumRodjenja.getTime());
        return datumRodjenjaUTIL;
    }

    private java.sql.Date getSQLDatum(Date datumRodjenja) {
        java.sql.Date datumRodjenjaSQL = new java.sql.Date(datumRodjenja.getTime());
        return datumRodjenjaSQL;
    }

    @Override
    public String getColumnsForInsert() {
        if (klub == null || klub.getNaziv().equals("--Slobodan igrac--")) {
            return "ime, prezime, pozicija, datumRodjenja, visina";
        }
        return "ime, prezime, pozicija, datumRodjenja, visina, klubId";
    }

    @Override
    public String getParamsForInsert() {
        if (klub == null || klub.getNaziv().equals("--Slobodan igrac--")) {
            return "'" + ime + "'" + ", " + "'" + prezime + "'" + ", " + "'" + pozicija.toString() + "'" + ", " + "'" + getSQLDatum(datumRodjenja) + "'" + ", " + visina;
        }
        return "'" + ime + "'" + ", " + "'" + prezime + "'" + ", " + "'" + pozicija.toString() + "'" + ", " + "'" + getSQLDatum(datumRodjenja) + "'" + ", " + visina + ", " + klub.getId();
    }

    @Override
    public String setAtrValue() {
        if (klub == null || klub.getNaziv().equals("--Slobodan igrac--")) {
            return "i.id=" + id + ", " + "i.ime=" + "'" + ime + "'" + ", " + "i.prezime=" + "'" + prezime + "'" + ", " + "i.pozicija=" + "'" + pozicija.toString() + "'"
                    + ", " + "i.datumRodjenja=" + "'" + getSQLDatum(datumRodjenja) + "'" + ", " + "i.visina=" + visina + ", " + "i.klubId=" + "NULL";
        }
        /*if(klub.getNaziv().equals("--Slobodan igrac--")){
             return "i.id=" + id + ", " + "i.ime=" + "'" + ime + "'" + ", " + "i.prezime=" + "'" + prezime + "'" + ", " + "i.pozicija=" + "'" + pozicija.toString() + "'"
                + ", " + "i.datumRodjenja=" + "'" + getSQLDatum(datumRodjenja) + "'" + ", " + "i.visina=" + visina + ", " + "i.klubId=" + "NULL";
        }*/
        return "i.id=" + id + ", " + "i.ime=" + "'" + ime + "'" + ", " + "i.prezime=" + "'" + prezime + "'" + ", " + "i.pozicija=" + "'" + pozicija.toString() + "'"
                + ", " + "i.datumRodjenja=" + "'" + getSQLDatum(datumRodjenja) + "'" + ", " + "i.visina=" + visina + ", " + "i.klubId=" + klub.getId();
    }

    @Override
    public int compareTo(Igrac o) {
        int zamba = o.getIme().charAt(0);
        if (this.getIme().charAt(0) - zamba == 0) {
            zamba = o.getPrezime().charAt(0);
            return this.getPrezime().charAt(0) - zamba;
        }
        return this.getIme().charAt(0) - zamba;
    }

    @Override
    public String getDeleteCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

