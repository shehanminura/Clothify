package Controller.Employee;

import Model.employee;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colAdminid;

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> coliEmpd;

    @FXML
    private AnchorPane loadContentAdmin;

    @FXML
    private TableView<employee> tblEmp;

    @FXML
    private JFXTextField txtCompany;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtadminid;

    @FXML
    private JFXTextField txtempid;

    @FXML
    private JFXTextField txtname;


    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coliEmpd.setCellValueFactory(new PropertyValueFactory<>("employeeid"));
        colAdminid.setCellValueFactory(new PropertyValueFactory<>("userid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("Company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        loadEmpTable();
    }

    void loadEmpTable() throws SQLException {
        ObservableList<employee>employeeObservableList= FXCollections.observableArrayList();
        employeeObservableList.addAll(EmployeeController.getInstance().GetAllEmployee());
        tblEmp.setItems(employeeObservableList);
    }
    @FXML
    void btnAddEmpOnAction(ActionEvent event) throws SQLException {
        try {
            boolean isAdd = EmployeeController.getInstance().saveEmployee(new employee(txtempid.getText(), txtadminid.getText(), txtname.getText(), txtCompany.getText(), txtEmail.getText(), txtPassword.getText()));
            if (isAdd){
                textClear();
                new Alert(Alert.AlertType.CONFIRMATION,"Employee Added is Successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadEmpTable();
    }

    @FXML
    void btnDeleteEmpOnAction(ActionEvent event) throws SQLException {
        boolean isDelete = EmployeeController.getInstance().deleteEmployee(txtempid.getText());
        if (isDelete){
            new Alert(Alert.AlertType.INFORMATION,"Delete is Successfully").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Delete is not Successfully").show();

        }
        loadEmpTable();
    }

    @FXML
    void btnSeartchEmpOnAction(ActionEvent event) {
        employee employee = null;
        try {
            employee = EmployeeController.getInstance().searchEmployee(txtempid.getText());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (employee != null){
            txtadminid.setText(employee.getUserid());
            txtname.setText(employee.getName());
            txtCompany.setText(employee.getCompany());
            txtEmail.setText(employee.getEmail());
            txtPassword.setText(employee.getPassword());

        }else {
            new Alert(Alert.AlertType.ERROR,"Employee Not Founded").show();

        }

    }

    @FXML
    void btnUpdateEmpOnAction(ActionEvent event) throws SQLException {
        boolean isUpdate = false;
        try {
            isUpdate = EmployeeController.getInstance().updateEmployee(new employee(txtempid.getText(), txtadminid.getText(), txtname.getText(), txtCompany.getText(), txtEmail.getText(), txtPassword.getText()));
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Update is Successfully").show();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Update is not Successfully").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadEmpTable();
    }
    void textClear(){
        txtempid.setText("");
        txtadminid.setText("");
        txtname.setText("");
        txtname.setText("");
        txtCompany.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
    }
}
