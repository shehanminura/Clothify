package Controller.Products;

import Model.products;

import java.sql.SQLException;
import java.util.List;

public interface ProductServies {
     List<products> getAllProducts() throws SQLException;

     boolean saveproducts(products products)throws SQLException;

     boolean updateproducts(products products) throws SQLException;

     boolean deleteproducts(String products) throws SQLException;

     products searchproducts(String products) throws SQLException;



}
