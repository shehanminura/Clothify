package Controller.Products;

import DBConnection.DBConnection;
import Model.OrderDetail;
import Model.products;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsController implements ProductServies {
    private static ProductsController productsController;
    public ProductsController(){

    }
    public static ProductsController getInstance(){
        if (productsController == null){
            productsController = new ProductsController();

        }
        return productsController;
    }

    @Override
    public List<products> getAllProducts() throws SQLException {
        List<products>productsList = new ArrayList<>();
        ResultSet rest = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM products");
        while (rest.next()){
            productsList.add(new products(
                    rest.getString(1),
                    rest.getString(2),
                    rest.getString(3),
                    rest.getString(4),
                    rest.getDouble(5),
                    rest.getInt(6),
                    rest.getString(7),
                    rest.getString(8)
            ));
        }
        return productsList;
    }

    @Override
    public boolean saveproducts(products products) throws SQLException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO products (productID, name, category, size, price, quantity, supplierID, imageURL) VALUES (?,?,?,?,?,?,?,?)");
        stm.setObject(1, products.getProductID());
        stm.setObject(2,products.getName());
        stm.setObject(3,products.getCategory());
        stm.setObject(4,products.getSize());
        stm.setObject(5,products.getPrice());
        stm.setObject(6,products.getQuantity());
        stm.setObject(7,products.getSupplierID());
        stm.setObject(8,products.getImageURL());
        return stm.executeUpdate()>0;
    }

    @Override
    public boolean updateproducts(products products) throws SQLException {
        PreparedStatement psTm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE products SET Name=?,Category = ? ,Size = ?, Price = ?, Quantity =?,SupplierID=?,ImageURL=? where ProductID = ?");
        psTm.setObject(1,products.getName());
        psTm.setObject(2,products.getCategory());
        psTm.setObject(3,products.getSize());
        psTm.setObject(4,products.getPrice());
        psTm.setObject(5,products.getQuantity());
        psTm.setObject(6,products.getSupplierID());
        psTm.setObject(7,products.getImageURL());
        psTm.setObject(8,products.getProductID());
        return psTm.executeUpdate()>0;
             }

    @Override
    public boolean deleteproducts(String products) throws SQLException {
        PreparedStatement psTm = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM products where ProductID = ? ");
        psTm.setObject(1,products);
        return psTm.executeUpdate()>0;
    }

    @Override
    public products searchproducts(String products) throws SQLException {
        ResultSet rest = DBConnection.getInstance().getConnection().createStatement().executeQuery("select * from products where ProductID ='" + products + "'");
       if (rest.next()){
           return new products(rest.getString(1),rest.getString(2),rest.getString(3),rest.getString(4),rest.getDouble(5),
                   rest.getInt(6),rest.getString(7),rest.getString(8));
       }
        return null;
    }

    public ObservableList<String> getProductsid() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        getAllProducts().forEach(products ->{
            list.add(products.getProductID());
        });
        return list;
    }

    public boolean updateproduct(List<OrderDetail> orderDetails) throws SQLException {
    for (OrderDetail orderDetail :orderDetails){
        boolean isUpdateStock = updateproducts(orderDetail);
        if (isUpdateStock){
            return false;
        }
    }
        return true;
    }

    private boolean updateproducts(OrderDetail orderDetail) {
    String SQL = "UPDATE PRODUCTS SET Quantity = Quantity-? WHERE ProductID = ?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,orderDetail.getQuantity());
            psTm.setObject(2,orderDetail.getOrderID());
            return psTm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}