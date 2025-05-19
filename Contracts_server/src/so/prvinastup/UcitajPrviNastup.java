/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.prvinastup;

import domain.Dvorana;
import domain.PrviNastup;
import respository.db.DatabaseBroker;
import so.ApstraktnaSO;

/**
 *
 * @author Marko
 */
public class UcitajPrviNastup extends ApstraktnaSO {

    private PrviNastup prviNastup;

    @Override
    public void executeOperation(Object object) throws Exception {
        prviNastup = (PrviNastup) DatabaseBroker.getInstance().selectOne((PrviNastup) object);
    }

    public PrviNastup vratiPrviNastup() {
        return prviNastup;
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof PrviNastup)) {
            throw new Exception("Objekat nije validan!");
        }
    }

}
