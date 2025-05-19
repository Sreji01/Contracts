/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.igrac;

import domain.Igrac;
import domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import respository.db.DatabaseBroker;
import so.ApstraktnaSO;

/**
 *
 * @author Marko
 */
public class UcitajListuIgraca extends ApstraktnaSO{
    
    private List<Igrac> igraci;

    @Override
    public void executeOperation(Object object) throws Exception {
        igraci = (List<Igrac>) (ArrayList<?>) DatabaseBroker.getInstance().selectMany((Igrac) object);
    }
    
    public List<Igrac> vratiIgrace(){
        return igraci;
    }
    
    @Override
    protected void validate(Object object) throws Exception {
    }
    
}
