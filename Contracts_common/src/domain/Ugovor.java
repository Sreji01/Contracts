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

/**
 *
 * @author Sreja
 */
public class Ugovor implements Serializable, OpstiDomenskiObjekat {

    private int id;
    private Igrac igrac;
    private Klub odlazeciKlub;
    private Klub dolazeciKlub;
    private List<StavkaUgovora> stavkeUgovora;
    private static final long serialVersionUID = 4161193085838532439L;

    public Ugovor() {
    }

    public Ugovor(int id, Igrac igrac, Klub odlazeciKlub, Klub dolazeciKlub, List<StavkaUgovora> stavkeUgovora) {
        this.id = id;
        this.igrac = igrac;
        this.odlazeciKlub = odlazeciKlub;
        this.dolazeciKlub = dolazeciKlub;
        this.stavkeUgovora = stavkeUgovora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Igrac getIgrac() {
        return igrac;
    }

    public void setIgrac(Igrac igrac) {
        this.igrac = igrac;
    }

    public Klub getOdlazeciKlub() {
        return odlazeciKlub;
    }

    public void setOdlazeciKlub(Klub odlazeciKlub) {
        this.odlazeciKlub = odlazeciKlub;
    }

    public Klub getDolazeciKlub() {
        return dolazeciKlub;
    }

    public void setDolazeciKlub(Klub dolazeciKlub) {
        this.dolazeciKlub = dolazeciKlub;
    }

    public List<StavkaUgovora> getStavkeUgovora() {
        return stavkeUgovora;
    }

    public void setStavkeUgovora(List<StavkaUgovora> stavkeUgovora) {
        this.stavkeUgovora = stavkeUgovora;
    }

    @Override
    public String toString() {
        String stavke = "\n" + "";
        if (stavkeUgovora != null) {
            for (StavkaUgovora stavkaUgovora : stavkeUgovora) {
                stavke += stavkaUgovora.toString() + "\n";
            }
        }
        return igrac.toString() + ":   (" + odlazeciKlub.toString() + ")  --->  (" + dolazeciKlub.toString() + ")" + stavke;
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
        final Ugovor other = (Ugovor) obj;
        return this.id == other.id;
    }

    @Override
    public String getTableName() {
        return "UGOVOR";
    }

    @Override
    public String alijas() {
        return "u";
    }

    @Override
    public String join() {
        return "JOIN IGRAC i ON u.igracId = i.id "
                + "LEFT JOIN KLUB k ON i.klubId = k.id "
                + "LEFT JOIN DVORANA d ON k.dvoranaId = d.id "
                + "LEFT JOIN KLUB k1 ON u.klubId1 = k1.id "
                + "LEFT JOIN DVORANA d1 ON k1.dvoranaId = d1.id "
                + "LEFT JOIN KlUB k2 ON u.klubId2 = k2.id "
                + "LEFT JOIN DVORANA d2 ON k2.dvoranaId = d2.id";
    }

    @Override
    public String getWhereCondition() {
        if (igrac != null) {
            return "WHERE i.ime LIKE '%" + igrac.getIme() + "%' OR i.prezime LIKE '%" + igrac.getPrezime() + "%'";
        }
        return "";
    }

    @Override
    public String getPrimaryKey() {
        return "u.id=" + id;
    }

    @Override
    public OpstiDomenskiObjekat getNewRecord(ResultSet rs) throws SQLException {
        if (rs.getString("k.naziv") == null && rs.getString("k2.naziv") == null) {
            return new Ugovor(rs.getInt("u.id"), new Igrac(rs.getInt("i.id"), rs.getString("i.ime"), rs.getString("i.prezime"), Pozicija.valueOf(rs.getString("i.pozicija")), getUtilDatum(rs.getDate("i.datumRodjenja")), rs.getInt("i.visina"),
                    null),
                    new Klub(rs.getInt("k1.id"), rs.getString("k1.naziv"), rs.getDate("k1.datumOsnivanja"), rs.getString("k1.lokacija"),
                            new Dvorana(rs.getInt("d1.id"), rs.getString("d1.naziv"), rs.getInt("d1.kapacitet"))),
                    new Klub(rs.getInt("k2.id"), "--Slobodan igrac--", rs.getDate("k2.datumOsnivanja"), rs.getString("k2.lokacija"),
                            new Dvorana(rs.getInt("d2.id"), rs.getString("d2.naziv"), rs.getInt("d2.kapacitet"))),
                    null);
        }
        if (rs.getString("k.naziv") == null && rs.getString("k1.naziv") == null) {
            return new Ugovor(rs.getInt("u.id"), new Igrac(rs.getInt("i.id"), rs.getString("i.ime"), rs.getString("i.prezime"), Pozicija.valueOf(rs.getString("i.pozicija")), getUtilDatum(rs.getDate("i.datumRodjenja")), rs.getInt("i.visina"),
                    null),
                    new Klub(rs.getInt("k1.id"), "--Slobodan igrac--", rs.getDate("k1.datumOsnivanja"), rs.getString("k1.lokacija"),
                            new Dvorana(rs.getInt("d1.id"), rs.getString("d1.naziv"), rs.getInt("d1.kapacitet"))),
                    new Klub(rs.getInt("k2.id"), rs.getString("k2.naziv"), rs.getDate("k2.datumOsnivanja"), rs.getString("k2.lokacija"),
                            new Dvorana(rs.getInt("d2.id"), rs.getString("d2.naziv"), rs.getInt("d2.kapacitet"))),
                    null);
        }
        if (rs.getString("k.naziv") == null && rs.getString("k1.naziv") != null) {
            return new Ugovor(rs.getInt("u.id"), new Igrac(rs.getInt("i.id"), rs.getString("i.ime"), rs.getString("i.prezime"), Pozicija.valueOf(rs.getString("i.pozicija")), getUtilDatum(rs.getDate("i.datumRodjenja")), rs.getInt("i.visina"),
                    null),
                    new Klub(rs.getInt("k1.id"), rs.getString("k1.naziv"), rs.getDate("k1.datumOsnivanja"), rs.getString("k1.lokacija"),
                            new Dvorana(rs.getInt("d1.id"), rs.getString("d1.naziv"), rs.getInt("d1.kapacitet"))),
                    new Klub(rs.getInt("k2.id"), rs.getString("k2.naziv"), rs.getDate("k2.datumOsnivanja"), rs.getString("k2.lokacija"),
                            new Dvorana(rs.getInt("d2.id"), rs.getString("d2.naziv"), rs.getInt("d2.kapacitet"))),
                    null);
        }
        if (rs.getString("k.naziv") == null && rs.getString("k2.naziv") != null) {
            return new Ugovor(rs.getInt("u.id"), new Igrac(rs.getInt("i.id"), rs.getString("i.ime"), rs.getString("i.prezime"), Pozicija.valueOf(rs.getString("i.pozicija")), getUtilDatum(rs.getDate("i.datumRodjenja")), rs.getInt("i.visina"),
                    null),
                    new Klub(rs.getInt("k1.id"), rs.getString("k1.naziv"), rs.getDate("k1.datumOsnivanja"), rs.getString("k1.lokacija"),
                            new Dvorana(rs.getInt("d1.id"), rs.getString("d1.naziv"), rs.getInt("d1.kapacitet"))),
                    new Klub(rs.getInt("k2.id"), rs.getString("k2.naziv"), rs.getDate("k2.datumOsnivanja"), rs.getString("k2.lokacija"),
                            new Dvorana(rs.getInt("d2.id"), rs.getString("d2.naziv"), rs.getInt("d2.kapacitet"))),
                    null);
        }
        if (rs.getString("k.naziv") != null && rs.getString("k1.naziv") == null) {
            return new Ugovor(rs.getInt("u.id"), new Igrac(rs.getInt("i.id"), rs.getString("i.ime"), rs.getString("i.prezime"), Pozicija.valueOf(rs.getString("i.pozicija")), getUtilDatum(rs.getDate("i.datumRodjenja")), rs.getInt("i.visina"),
                    new Klub(rs.getInt("k.id"), rs.getString("k.naziv"), getUtilDatum(rs.getDate("k.datumOsnivanja")), rs.getString("k.lokacija"),
                            new Dvorana(rs.getInt("d.id"), rs.getString("d.naziv"), rs.getInt("d.kapacitet")))),
                    new Klub(rs.getInt("k1.id"), "--Slobodan igrac--", rs.getDate("k1.datumOsnivanja"), rs.getString("k1.lokacija"),
                            new Dvorana(rs.getInt("d1.id"), rs.getString("d1.naziv"), rs.getInt("d1.kapacitet"))),
                    new Klub(rs.getInt("k2.id"), rs.getString("k2.naziv"), rs.getDate("k2.datumOsnivanja"), rs.getString("k2.lokacija"),
                            new Dvorana(rs.getInt("d2.id"), rs.getString("d2.naziv"), rs.getInt("d2.kapacitet"))),
                    null);
        }
        if (rs.getString("k.naziv") != null && rs.getString("k2.naziv") == null) {
            return new Ugovor(rs.getInt("u.id"), new Igrac(rs.getInt("i.id"), rs.getString("i.ime"), rs.getString("i.prezime"), Pozicija.valueOf(rs.getString("i.pozicija")), getUtilDatum(rs.getDate("i.datumRodjenja")), rs.getInt("i.visina"),
                    new Klub(rs.getInt("k.id"), rs.getString("k.naziv"), getUtilDatum(rs.getDate("k.datumOsnivanja")), rs.getString("k.lokacija"),
                            new Dvorana(rs.getInt("d.id"), rs.getString("d.naziv"), rs.getInt("d.kapacitet")))),
                    new Klub(rs.getInt("k1.id"), rs.getString("k1.naziv"), rs.getDate("k1.datumOsnivanja"), rs.getString("k1.lokacija"),
                            new Dvorana(rs.getInt("d1.id"), rs.getString("d1.naziv"), rs.getInt("d1.kapacitet"))),
                    new Klub(rs.getInt("k2.id"), "--Slobodan igrac--", rs.getDate("k2.datumOsnivanja"), rs.getString("k2.lokacija"),
                            new Dvorana(rs.getInt("d2.id"), rs.getString("d2.naziv"), rs.getInt("d2.kapacitet"))),
                    null);
        }
        return new Ugovor(rs.getInt("u.id"), new Igrac(rs.getInt("i.id"), rs.getString("i.ime"), rs.getString("i.prezime"), Pozicija.valueOf(rs.getString("i.pozicija")), getUtilDatum(rs.getDate("i.datumRodjenja")), rs.getInt("i.visina"),
                new Klub(rs.getInt("k.id"), rs.getString("k.naziv"), getUtilDatum(rs.getDate("k.datumOsnivanja")), rs.getString("k.lokacija"),
                        new Dvorana(rs.getInt("d.id"), rs.getString("d.naziv"), rs.getInt("d.kapacitet")))),
                new Klub(rs.getInt("k1.id"), rs.getString("k1.naziv"), rs.getDate("k1.datumOsnivanja"), rs.getString("k1.lokacija"),
                        new Dvorana(rs.getInt("d1.id"), rs.getString("d1.naziv"), rs.getInt("d1.kapacitet"))),
                new Klub(rs.getInt("k2.id"), rs.getString("k2.naziv"), rs.getDate("k2.datumOsnivanja"), rs.getString("k2.lokacija"),
                        new Dvorana(rs.getInt("d2.id"), rs.getString("d2.naziv"), rs.getInt("d2.kapacitet"))),
                null);
    }

    private Date getUtilDatum(java.sql.Date datum) {
        Date datumUTIL = new Date(datum.getTime());
        return datumUTIL;
    }

    @Override
    public String getColumnsForInsert() {
        return "igracId, klubId1, klubId2";
    }

    @Override
    public String getParamsForInsert() {
        if (odlazeciKlub == null) {
            return igrac.getId() + ", " + "NULL" + ", " + dolazeciKlub.getId();
        }
        if (dolazeciKlub == null) {
            return igrac.getId() + ", " + odlazeciKlub.getId() + ", " + "NULL";
        }
        return igrac.getId() + ", " + odlazeciKlub.getId() + ", " + dolazeciKlub.getId();
    }

    @Override
    public String setAtrValue() {
        return "id=" + id + ", " + "igracId=" + igrac.getId() + ", " + "klubId1=" + odlazeciKlub.getId() + ", " + "klubId2=" + dolazeciKlub.getId();
    }

    @Override
    public String getDeleteCondition() {
        return "id=" + id;
    }

}
