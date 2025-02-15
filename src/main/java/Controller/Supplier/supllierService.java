package Controller.Supplier;

import Model.supplier;


import java.sql.SQLException;
import java.util.List;

public interface supllierService {

    List<supplier> getAllSupplier() throws SQLException;

    boolean saveSupplier(supplier supplier) throws SQLException;

    boolean deleteSupplier(String id) throws SQLException;

    supplier searchSupplier(String id) throws SQLException;

    boolean updateSupplier(supplier products) throws SQLException;

}
