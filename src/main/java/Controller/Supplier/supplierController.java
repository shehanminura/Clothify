package Controller.Supplier;

import DBConnection.DBConnection;
import Model.supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class supplierController implements supllierService {

    private static supplierController supplierController;

    public supplierController() {

    }

    public static supplierController getInstance() {
        if (supplierController == null) {
            supplierController = new supplierController();
        }
        return supplierController;
    }

    @Override
    public List<supplier> getAllSupplier() throws SQLException {
        List<supplier> supplierList = new ArrayList<>();
        ResultSet rest = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM suppliers");
        while (rest.next()) {
            supplierList.add(new supplier(
                    rest.getString(1),
                    rest.getString(2),
                    rest.getString(3),
                    rest.getString(4)
            ));
        }
        return supplierList;
    }

    @Override
    public boolean saveSupplier(supplier supplier) throws SQLException {
        PreparedStatement psTm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO suppliers values(?,?,?,?)");
        psTm.setObject(1, supplier.getSupplierID());
        psTm.setObject(2, supplier.getName());
        psTm.setObject(3, supplier.getCompany());
        psTm.setObject(4, supplier.getEmail());
        return psTm.executeUpdate() > 0;
    }


    @Override
    public boolean deleteSupplier(String id) throws SQLException {
        PreparedStatement psTm = DBConnection.getInstance().getConnection().prepareStatement("delete from suppliers where SupplierID = ? ");
        psTm.setObject(1, id);
        return psTm.executeUpdate() > 0;

    }

    @Override
    public supplier searchSupplier(String id) throws SQLException {
        ResultSet rest = DBConnection.getInstance().getConnection().createStatement().executeQuery("select * from suppliers where SupplierID ='" + id + "'");
        if (rest.next()) {
            return new supplier(rest.getString(1), rest.getString(2), rest.getString(3), rest.getString(4));

        }

        return null;
    }

    @Override
    public boolean updateSupplier(supplier supplier) throws SQLException {
        PreparedStatement psTm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE suppliers SET Name=?, Company=?, Email=? where SupplierID =? ");
        psTm.setObject(1, supplier.getName());
        psTm.setObject(2, supplier.getCompany());
        psTm.setObject(3, supplier.getEmail());
        psTm.setObject(4, supplier.getSupplierID());
        return psTm.executeUpdate() > 0;
    }
}
