package Controller.Employee;

import DBConnection.DBConnection;
import Model.employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jasypt.util.text.BasicTextEncryptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController implements EmployeeService{

    private static EmployeeController employeeController;

    private EmployeeController(){

    }
    public static EmployeeController getInstance(){
        if (employeeController == null){
            employeeController = new EmployeeController();
        }
        return employeeController;
    }


    @Override
    public List<employee> GetAllEmployee() throws SQLException {

        List<employee> employeeList = new ArrayList<>();

        ResultSet rest = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select * from employees");
        while (rest.next()){
            employeeList.add(new employee(rest.getString(1),rest.getString(2),rest.getString(3),rest.getString(4),rest.getString(5),rest.getString(6)));
        }

        return employeeList;
    }

    @Override
    public boolean saveEmployee(employee employee) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pre = connection.prepareStatement("Select * from employees where email= ?");
        pre.setString(1,employee.getEmail());
        ResultSet res = pre.executeQuery();

        if (!res.next()){
            String key ="1234";
            BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
            basicTextEncryptor.setPassword(key);

            String encrypt = basicTextEncryptor.encrypt(employee.getPassword());

            String sql ="insert into employees values (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,employee.getEmployeeid());
            preparedStatement.setString(2,employee.getUserid());
            preparedStatement.setString(3,employee.getName());
            preparedStatement.setString(4,employee.getCompany());
            preparedStatement.setString(5,employee.getEmail());
            preparedStatement.setString(6,encrypt);
            return preparedStatement.executeUpdate() > 0;

        }
        return false;
    }

    @Override
    public boolean updateEmployee(employee employee) throws SQLException {
        PreparedStatement psTm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE Employees set UserID = ?,Name= ?,Company=?,Email=?,Password=? where EmployeeID= ?");
        psTm.setObject(1,employee.getUserid());
        psTm.setObject(2,employee.getName());
        psTm.setObject(3,employee.getCompany());
        psTm.setObject(4,employee.getEmail());
        psTm.setObject(5,employee.getPassword());
        psTm.setObject(6,employee.getEmployeeid());
        return psTm.executeUpdate()>0;
    }

    @Override
    public boolean deleteEmployee(String employeeid) throws SQLException {
        PreparedStatement psTm = DBConnection.getInstance().getConnection().prepareStatement("Delete from employees where EmployeeID = ?");
        psTm.setObject(1,employeeid);
        return psTm.executeUpdate()>0;

    }

    @Override
    public employee searchEmployee(String employeeid) throws SQLException {

        ResultSet res = DBConnection.getInstance().getConnection().createStatement().executeQuery("select * from employees where EmployeeID ='"+employeeid+"'");
        if (res.next()){
            return new employee(res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6));
        }
        return null;
    }


    public ObservableList<String> getEmployeesid() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        GetAllEmployee().forEach(employee -> {
            list.add(employee.getEmployeeid());
        });
        return list;
    }
}
