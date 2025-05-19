/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Sreja
 */
public class Trener implements Serializable, OpstiDomenskiObjekat, Comparable<Trener> {

    private int id;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private int brojTitula;
    private Klub klub;
    private static final long serialVersionUID = 7126215146492554414L;

    public Trener() {
    }

    public Trener(int id, String ime, String prezime, Date datumRodjenja, int brojTitula, Klub klub) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.brojTitula = brojTitula;
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

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public int getBrojTitula() {
        return brojTitula;
    }

    public void setBrojTitula(int brojTitula) {
        this.brojTitula = brojTitula;
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
        int hash = 3;
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
        final Trener other = (Trener) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        return Objects.equals(this.prezime, other.prezime);
    }

    @Override
    public String getTableName() {
        return "TRENER";
    }

    @Override
    public String alijas() {
        return "t";
    }

    @Override
    public String join() {
        return "JOIN KLUB k ON t.klubId = k.id JOIN DVORANA d on k.dvoranaId = d.id";
    }

    @Override
    public String getWhereCondition() {
        return "WHERE t.brojTitula >=" + brojTitula;
    }

    @Override
    public String getPrimaryKey() {
        return "t.id=" + id;
    }

    @Override
    public OpstiDomenskiObjekat getNewRecord(ResultSet rs) throws SQLException {
        return new Trener(rs.getInt("t.id"), rs.getString("t.ime"), rs.getString("t.prezime"), getUtilDatum(rs.getDate("t.datumRodjenja")), rs.getInt("t.brojTitula"),
                new Klub(rs.getInt("k.id"), rs.getString("k.naziv"), rs.getDate("k.datumOsnivanja"), rs.getString("k.lokacija"),
                        new Dvorana(rs.getInt("d.id"), rs.getString("d.naziv"), rs.getInt("d.kapacitet"))));
    }

    private Date getUtilDatum(java.sql.Date datumRodjenja) {
        Date datumRodjenjaUtil = new Date(datumRodjenja.getTime());
        return datumRodjenjaUtil;
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
    public int compareTo(Trener o) {
        int zamba = o.getIme().charAt(0);
        if (this.getIme().charAt(0) - zamba == 0) {
            zamba = o.getPrezime().charAt(0);
            return this.getPrezime().charAt(0) - zamba;
        }
        return this.getIme().charAt(0) - zamba;
    }
}
