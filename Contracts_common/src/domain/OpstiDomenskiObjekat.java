/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.*;
import java.util.List;

/**
 *
 * @author Sreja
 */
public interface OpstiDomenskiObjekat {
    public String getTableName();
    public String alijas();
    public String join();
    public String getWhereCondition();
    public String getPrimaryKey();
    public OpstiDomenskiObjekat getNewRecord(ResultSet rs) throws SQLException;
    public String getColumnsForInsert();
    public String getParamsForInsert();
    public String setAtrValue();
    public String getDeleteCondition();
}
