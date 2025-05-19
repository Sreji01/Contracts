/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.dvorana;

import domain.Dvorana;
import domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import respository.db.DatabaseBroker;
import so.ApstraktnaSO;
import so.ApstraktnaSO;

/**
 *
 * @author Marko
 */
public class UcitajListuDvorana extends ApstraktnaSO {

    private List<Dvorana> dvorane;

    @Override
    public void executeOperation(Object object) throws Exception {
        dvorane = (List<Dvorana>) (ArrayList<?>) DatabaseBroker.getInstance().selectMany((Dvorana) object);
    }

    public List<Dvorana> vratiDvorane() {
        return dvorane;
    }

    @Override
    protected void validate(Object object) throws Exception {

    }

}
