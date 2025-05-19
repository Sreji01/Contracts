/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.trener;

import domain.Igrac;
import domain.Klub;
import domain.Trener;
import respository.db.DatabaseBroker;
import so.ApstraktnaSO;

/**
 *
 * @author Marko
 */
public class UcitajTrenera extends ApstraktnaSO{
    private Trener trener;
   
    public Trener vratiTrenera(){
        return trener;
    }

    @Override
    public void executeOperation(Object object) throws Exception {
        trener = (Trener) DatabaseBroker.getInstance().selectOne((Trener) object);
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Trener)) {
            throw new Exception("Objekat nije validan!");
        }
    }
    
}
