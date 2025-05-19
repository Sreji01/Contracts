/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.igrac;

import domain.Igrac;
import respository.db.DatabaseBroker;
import so.ApstraktnaSO;

/**
 *
 * @author Marko
 */
public class UcitajIgraca extends ApstraktnaSO{
    private Igrac igrac;

    @Override
    public void executeOperation(Object object) throws Exception {
        igrac = (Igrac) DatabaseBroker.getInstance().selectOne((Igrac) object);
    }
    
    public Igrac vratiIgraca(){
        return igrac;
    }
       
    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Igrac)) {
            throw new Exception("Objekat nije validan!");
        }
    }
    
}
