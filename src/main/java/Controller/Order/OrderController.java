package Controller.Order;

import Controller.Products.ProductsController;
import DBConnection.DBConnection;
import Model.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderController {
    public boolean placeOrder(order order) throws SQLException {
        String SQL = "INSERT INTO orders Values(?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setString(1, order.getItemid());
            psTm.setString(2, order.getEmployeeId());
            psTm.setString(3, order.getTotalcost());
            psTm.setString(4, order.getOrderdate());
            boolean isOrderAdd = psTm.executeUpdate() > 0;

            if (isOrderAdd) {
                boolean isOrderDetailAdd = new OrderDetailController().addOrderDetail(order.getOrderDetails());
                if (isOrderDetailAdd) {
                    boolean isUpdateStork = new ProductsController().updateproduct(order.getOrderDetails());
                    if (isUpdateStork) {
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            connection.setAutoCommit(true);
        }
    }
}
