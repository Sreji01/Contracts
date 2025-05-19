/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.klub;


import domain.Dvorana;
import domain.Klub;
import domain.OpstiDomenskiObjekat;
import java.util.List;
import respository.db.DatabaseBroker;
import so.ApstraktnaSO;


/**
 *
 * @author Marko
 */
public class UcitajListuKlubova extends ApstraktnaSO{
    
    private List<OpstiDomenskiObjekat> klubovi;
 
    @Override
    public void executeOperation(Object object) throws Exception {
        klubovi = DatabaseBroker.getInstance().selectMany((Klub)object);  
    }
    
    public List<OpstiDomenskiObjekat> vratiKlubove(){
        return klubovi;
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Klub)) {
            throw new Exception("Objekat nije validan!");
        }
    }
    
}
