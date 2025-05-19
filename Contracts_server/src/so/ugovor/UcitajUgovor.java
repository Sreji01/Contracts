/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.ugovor;

import domain.StavkaUgovora;
import domain.Ugovor;
import java.util.ArrayList;
import java.util.List;
import respository.db.DatabaseBroker;
import so.ApstraktnaSO;

/**
 *
 * @author Marko
 */
public class UcitajUgovor extends ApstraktnaSO {

    private Ugovor ugovor;

    @Override
    public void executeOperation(Object object) throws Exception {
        ugovor = (Ugovor) DatabaseBroker.getInstance().selectOne((Ugovor) object);
        StavkaUgovora stavkaUgovora = new StavkaUgovora();
        stavkaUgovora.setUgovor(ugovor);
        List<StavkaUgovora> stavkeUgovora;
        stavkeUgovora = (List<StavkaUgovora>) (ArrayList<?>) DatabaseBroker.getInstance().selectMany(stavkaUgovora);
        System.out.println(stavkeUgovora);
        ugovor.setStavkeUgovora(stavkeUgovora);
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Ugovor)) {
            throw new Exception("Objekat nije validan!");
        }
    }

    public Ugovor vratiUgovor() {
        return ugovor;
    }

}
