package Controller.Order;

import DBConnection.DBConnection;
import Model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailController {
    public boolean addOrderDetail(List<OrderDetail> orderDetails) throws SQLException {
    for (OrderDetail orderDetail : orderDetails){
        boolean isOrderDetailAdd = addOrderDetail(orderDetail);
        if (!isOrderDetailAdd){
            return false;
        }
     }
    return true;
    }

    private boolean addOrderDetail(OrderDetail orderDetail) throws SQLException {
        String SQL = "INSERT INTO orderdetails values (?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement psTm = connection.prepareStatement(SQL);
        psTm.setString(1,orderDetail.getOrderID());
        psTm.setString(2,orderDetail.getProductID());
        psTm.setString(3, String.valueOf(orderDetail.getQuantity()));
        psTm.setString(4, String.valueOf(orderDetail.getSubTotal()));
        return psTm.executeUpdate()>0;
    }
}
