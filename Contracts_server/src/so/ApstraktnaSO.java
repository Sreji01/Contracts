/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import respository.db.DatabaseBroker;

/**
 *
 * @author Sreja
 */
public abstract class ApstraktnaSO {
    
    public void execute(Object object) throws Exception{
        try {
            validate(object);
            executeOperation(object);
            commit();
        } catch (Exception ex) {
            rollback();
            throw ex;
        }
    }
    
    private void commit() throws Exception{
        DatabaseBroker.getInstance().getConnection().commit();
    }
    
    private void rollback() throws Exception{
        DatabaseBroker.getInstance().getConnection().rollback();
    }
    
    public abstract void executeOperation(Object object) throws Exception;
    
    protected abstract void validate(Object object) throws Exception;
}
