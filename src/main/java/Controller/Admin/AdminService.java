package Controller.Admin;

import Model.admin;

import java.sql.SQLException;
import java.util.List;

public interface AdminService {
    List<admin> GetAllAdmin() throws SQLException;
    boolean saveAdmin(admin admin) throws SQLException;

    boolean updateAdmin(admin admin) throws SQLException;

    boolean deleteAdmin(String adminid) throws SQLException;

    admin seartchAdmin(String adminid) throws SQLException;
}
