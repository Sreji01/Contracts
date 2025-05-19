/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.trener;

import com.sun.source.tree.Tree;
import domain.OpstiDomenskiObjekat;
import domain.Trener;
import java.util.ArrayList;
import java.util.List;
import respository.db.DatabaseBroker;
import so.ApstraktnaSO;

/**
 *
 * @author Marko
 */
public class NadjiTrenere extends ApstraktnaSO {

    private List<Trener> treneri = new ArrayList<>();

    @Override
    public void executeOperation(Object object) throws Exception {
        treneri = (List<Trener>) (ArrayList<?>) DatabaseBroker.getInstance().selectMany((Trener) object);
    }

    public List<Trener> vratiTrenere() {
        return treneri;
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Trener)) {
            throw new Exception("Objekat nije validan!");
        }
    }

}
