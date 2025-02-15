package Controller.Employee;

import Model.employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {

    List<employee> GetAllEmployee() throws SQLException;

    boolean saveEmployee (employee employee) throws SQLException;

    boolean updateEmployee (employee employee) throws SQLException;

    boolean deleteEmployee (String employeeid) throws SQLException;

    employee searchEmployee(String employeeid) throws SQLException;
}
