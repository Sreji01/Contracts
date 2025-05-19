/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.igrac;

import domain.Igrac;
import domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import java.lang.Object;
import respository.db.DatabaseBroker;
import so.ApstraktnaSO;

/**
 *
 * @author Marko
 */
public class NadjiIgrace extends ApstraktnaSO {

    private List<Igrac> igraci = new ArrayList<>();

    @Override
    public void executeOperation(Object object) throws Exception {
        igraci = (List<Igrac>) (ArrayList<?>) DatabaseBroker.getInstance().selectMany((Igrac) object);
    }

    public List<Igrac> vratiIgrace() {
        return igraci;
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Igrac)) {
            throw new Exception("Objekat nije validan!");
        }
    }

}
