/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.prvinastup;

import domain.OpstiDomenskiObjekat;
import domain.PrviNastup;
import java.util.ArrayList;
import java.util.List;
import respository.db.DatabaseBroker;
import so.ApstraktnaSO;

/**
 *
 * @author Marko
 */
public class NadjiPrveNastupe extends ApstraktnaSO {

    private List<PrviNastup> prviNastupi = new ArrayList<>();

    @Override
    public void executeOperation(Object object) throws Exception {
        prviNastupi = (List<PrviNastup>) (ArrayList<?>) DatabaseBroker.getInstance().selectMany((PrviNastup) object);
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof PrviNastup)) {
            throw new Exception("Objekat nije validan!");
        }
    }

    public List<PrviNastup> vratiPrveNastupe() {
        return prviNastupi;
    }

}
