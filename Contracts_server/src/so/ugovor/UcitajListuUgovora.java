/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.ugovor;

import domain.Ugovor;
import java.util.ArrayList;
import java.util.List;
import respository.db.DatabaseBroker;
import so.ApstraktnaSO;

/**
 *
 * @author Marko
 */
public class UcitajListuUgovora extends ApstraktnaSO{
    private List<Ugovor> ugovori = new ArrayList<>();

    @Override
    public void executeOperation(Object object) throws Exception {
        ugovori = (List<Ugovor>)(ArrayList<?>) DatabaseBroker.getInstance().selectMany((Ugovor) object);
    }

    @Override
    protected void validate(Object object) throws Exception {
        
    }
    
    public List<Ugovor> vratiUgovore(){
        return ugovori;
    }
    
}
