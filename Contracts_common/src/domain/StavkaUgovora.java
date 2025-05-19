/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Sreja
 */
public class StavkaUgovora implements Serializable, OpstiDomenskiObjekat {

    private Ugovor ugovor;
    private int id;
    private String opis;
    private Date datum;

    public StavkaUgovora() {
    }

    public StavkaUgovora(Ugovor ugovor, int id, String opis, Date datum) {
        this.ugovor = ugovor;
        this.id = id;
        this.opis = opis;
        this.datum = datum;
    }

    public Ugovor getUgovor() {
        return ugovor;
    }

    public void setUgovor(Ugovor ugovor) {
        this.ugovor = ugovor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    /* @Override
    public String toString() {
        return opis + ": " +getStringDatum(datum);
    }*/
    @Override
    public String toString() {
        return opis + ": " + getStringDatum(datum);
    }

    private String getStringDatum(Date datumNastupa) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String datumNastupaString = format.format(datumNastupa);
        return datumNastupaString;
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
        final StavkaUgovora other = (StavkaUgovora) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.ugovor, other.ugovor);
    }

    @Override
    public String getTableName() {
        return "STAVKA_UGOVORA";
    }

    @Override
    public String alijas() {
        return "su";
    }

    @Override
    public String join() {
        return "JOIN UGOVOR u ON su.ugovorId = u.id "
                + "JOIN IGRAC i ON u.igracId = i.id "
                + "LEFT JOIN KLUB k ON i.klubId = k.id "
                + "LEFT JOIN DVORANA d ON k.dvoranaId = d.id "
                + "LEFT JOIN KLUB k1 ON u.klubId1 = k1.id "
                + "LEFT JOIN DVORANA d1 ON k1.dvoranaId = d1.id "
                + "LEFT JOIN KlUB k2 ON u.klubId2 = k2.id "
                + "LEFT JOIN DVORANA d2 ON k2.dvoranaId = d2.id";
    }

    @Override
    public String getWhereCondition() {
        return "WHERE u.id=" + ugovor.getId() + "";
    }

    @Override
    public String getPrimaryKey() {
        return "su.ugovorId=" + ugovor.getId() + " AND su.id=" + id + "";
    }

    @Override
    public OpstiDomenskiObjekat getNewRecord(ResultSet rs) throws SQLException {
        if (rs.getString("k.naziv") == null && rs.getString("k2.naziv") == null) {
            return new StavkaUgovora(new Ugovor(rs.getInt("u.id"), new Igrac(rs.getInt("i.id"), rs.getString("i.ime"), rs.getString("i.prezime"), Pozicija.valueOf(rs.getString("i.pozicija")), getUtilDatum(rs.getDate("i.datumRodjenja")), rs.getInt("i.visina"),
                    null),
                    new Klub(rs.getInt("k1.id"), rs.getString("k1.naziv"), rs.getDate("k1.datumOsnivanja"), rs.getString("k1.lokacija"),
                            new Dvorana(rs.getInt("d1.id"), rs.getString("d1.naziv"), rs.getInt("d1.kapacitet"))),
                    null,
                    null), rs.getInt("su.id"), rs.getString("su.opis"), getUtilDatum(rs.getDate("su.datum")));
        }
        if (rs.getString("k.naziv") == null && rs.getString("k2.naziv") != null) {
            return new StavkaUgovora(new Ugovor(rs.getInt("u.id"), new Igrac(rs.getInt("i.id"), rs.getString("i.ime"), rs.getString("i.prezime"), Pozicija.valueOf(rs.getString("i.pozicija")), getUtilDatum(rs.getDate("i.datumRodjenja")), rs.getInt("i.visina"),
                    null),
                    new Klub(rs.getInt("k1.id"), rs.getString("k1.naziv"), rs.getDate("k1.datumOsnivanja"), rs.getString("k1.lokacija"),
                            new Dvorana(rs.getInt("d1.id"), rs.getString("d1.naziv"), rs.getInt("d1.kapacitet"))),
                    new Klub(rs.getInt("k2.id"), rs.getString("k2.naziv"), rs.getDate("k2.datumOsnivanja"), rs.getString("k2.lokacija"),
                            new Dvorana(rs.getInt("d2.id"), rs.getString("d2.naziv"), rs.getInt("d2.kapacitet"))),
                    null), rs.getInt("su.id"), rs.getString("su.opis"), getUtilDatum(rs.getDate("su.datum")));
        }
        if (rs.getString("k.naziv") != null && rs.getString("k2.naziv") == null) {
            return new StavkaUgovora(new Ugovor(rs.getInt("u.id"), new Igrac(rs.getInt("i.id"), rs.getString("i.ime"), rs.getString("i.prezime"), Pozicija.valueOf(rs.getString("i.pozicija")), getUtilDatum(rs.getDate("i.datumRodjenja")), rs.getInt("i.visina"),
                    new Klub(rs.getInt("k.id"), rs.getString("k.naziv"), getUtilDatum(rs.getDate("k.datumOsnivanja")), rs.getString("k.lokacija"),
                            new Dvorana(rs.getInt("d.id"), rs.getString("d.naziv"), rs.getInt("d.kapacitet")))),
                    new Klub(rs.getInt("k1.id"), rs.getString("k1.naziv"), rs.getDate("k1.datumOsnivanja"), rs.getString("k1.lokacija"),
                            new Dvorana(rs.getInt("d1.id"), rs.getString("d1.naziv"), rs.getInt("d1.kapacitet"))),
                    new Klub(rs.getInt("k2.id"), rs.getString("k2.naziv"), rs.getDate("k2.datumOsnivanja"), rs.getString("k2.lokacija"),
                            new Dvorana(rs.getInt("d2.id"), rs.getString("d2.naziv"), rs.getInt("d2.kapacitet"))),
                    null), rs.getInt("su.id"), rs.getString("su.opis"), getUtilDatum(rs.getDate("su.datum")));
        }
        return new StavkaUgovora(new Ugovor(rs.getInt("u.id"), new Igrac(rs.getInt("i.id"), rs.getString("i.ime"), rs.getString("i.prezime"), Pozicija.valueOf(rs.getString("i.pozicija")), getUtilDatum(rs.getDate("i.datumRodjenja")), rs.getInt("i.visina"),
                new Klub(rs.getInt("k.id"), rs.getString("k.naziv"), getUtilDatum(rs.getDate("k.datumOsnivanja")), rs.getString("k.lokacija"),
                        new Dvorana(rs.getInt("d.id"), rs.getString("d.naziv"), rs.getInt("d.kapacitet")))),
                new Klub(rs.getInt("k1.id"), rs.getString("k1.naziv"), rs.getDate("k1.datumOsnivanja"), rs.getString("k1.lokacija"),
                        new Dvorana(rs.getInt("d1.id"), rs.getString("d1.naziv"), rs.getInt("d1.kapacitet"))),
                null,
                null), rs.getInt("su.id"), rs.getString("su.opis"), getUtilDatum(rs.getDate("su.datum")));
    }

    private Date getUtilDatum(java.sql.Date datum) {
        Date datumUTIL = new Date(datum.getTime());
        return datumUTIL;
    }

    @Override
    public String getColumnsForInsert() {
        return "ugovorId, id, opis, datum";
    }

    @Override
    public String getParamsForInsert() {
        return ugovor.getId() + ", " + id + ", " + "'" + opis + "'" + ", " + "'" + getSQLDatum(datum) + "'";
    }

    private java.sql.Date getSQLDatum(Date datum) {
        java.sql.Date datumSQL = new java.sql.Date(datum.getTime());
        return datumSQL;
    }

    @Override
    public String setAtrValue() {
        return "ugovorId=" + ugovor.getId() + ", " + "id=" + id + ", " + "opis=" + "'" + opis + "'" + ", " + "datum=" + "'" + getSQLDatum(datum) + "'";
    }

    @Override
    public String getDeleteCondition() {
        return "ugovorId=" + ugovor.getId() + "";
    }
}
