package Controller.Admin;

import DBConnection.DBConnection;
import Model.admin;
import org.jasypt.util.text.BasicTextEncryptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminController implements AdminService {

    private static AdminController adminController;

    private AdminController() {

    }

    public static AdminController getInstance() {
        if (adminController == null) {
            adminController = new AdminController();
        }
        return adminController;
    }

    @Override
    public List<admin> GetAllAdmin() throws SQLException {
        List<admin> adminList = new ArrayList<>();
        ResultSet res = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM admin");
        while (res.next()) {
            adminList.add(new admin(res.getString(1), res.getString(2), res.getString(3), res.getString(4)));
        }
        return adminList;
    }

    @Override
    public boolean saveAdmin(admin admin) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pre = connection.prepareStatement("Select * from admin where email = ?");
        pre.setString(1, admin.getEmail());
        ResultSet res = pre.executeQuery();

        if (!res.next()) {
            String key = "12345";
            BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
            basicTextEncryptor.setPassword(key);

            String encrypt = basicTextEncryptor.encrypt(admin.getPassword());

            String sql = "insert into admin values(?,?,?,?)";
            PreparedStatement psTm = connection.prepareStatement(sql);
            psTm.setString(1, admin.getUserID());
            psTm.setString(2, admin.getName());
            psTm.setString(3, admin.getEmail());
            psTm.setString(4, encrypt);
            return psTm.executeUpdate() > 0;
        }
        return false;
    }


    @Override
    public boolean updateAdmin(admin admin) throws SQLException {
        PreparedStatement psTm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE admin SET  Name = ? , Email = ? , Password = ? where UserId =?");
        psTm.setObject(1,admin.getName());
        psTm.setObject(2,admin.getEmail());
        psTm.setObject(3,admin.getPassword());
        psTm.setObject(4,admin.getUserID());
        return psTm.executeUpdate()>0;
    }

    @Override
    public boolean deleteAdmin(String adminid) throws SQLException {
        PreparedStatement psTm = DBConnection.getInstance().getConnection().prepareStatement("DELETE from admin where userid= ?");
        psTm.setObject(1,adminid);
        return psTm.executeUpdate()>0;
    }

    @Override
    public admin seartchAdmin(String adminid) throws SQLException {

        ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery("select * FROM admin where UserID ='"+adminid+"'");
        if (resultSet.next()){
            return new admin(resultSet.getString(1),resultSet.getString(2),
                            resultSet.getString(3),resultSet.getString(4));
        }
        return null;
    }
}
