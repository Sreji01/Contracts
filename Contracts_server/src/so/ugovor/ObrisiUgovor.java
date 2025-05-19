/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.ugovor;

import domain.StavkaUgovora;
import domain.Ugovor;
import java.util.List;
import respository.db.DatabaseBroker;
import so.ApstraktnaSO;

/**
 *
 * @author Marko
 */
public class ObrisiUgovor extends ApstraktnaSO{

    @Override
    public void executeOperation(Object object) throws Exception {
        Ugovor ugovor = (Ugovor) object;
        List<StavkaUgovora> stavkeUgovora = ugovor.getStavkeUgovora();
        StavkaUgovora stavkaUgovora = stavkeUgovora.get(0);
        DatabaseBroker.getInstance().delete(stavkaUgovora);
        DatabaseBroker.getInstance().delete(ugovor);
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Ugovor)) {
            throw new Exception("Objekat nije validan!");

        }
    }
    
}
