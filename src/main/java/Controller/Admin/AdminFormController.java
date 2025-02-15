package Controller.Admin;


import Model.admin;
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

public class AdminFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private AnchorPane loadContentAdmin;

    @FXML
    private TableView<admin> tblAdmin;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtid;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colid.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
        loadTable();
    }
    void loadTable() throws SQLException {
        ObservableList<admin>adminObservableList = FXCollections.observableArrayList();
        adminObservableList.addAll(AdminController.getInstance().GetAllAdmin());
        tblAdmin.setItems(adminObservableList);
    }

    @FXML
    void btnAddAdOnAction(ActionEvent event) throws SQLException {
        try {
            boolean isAdd = AdminController.getInstance().saveAdmin(new admin(txtid.getText(), txtName.getText(), txtEmail.getText(), txtPassword.getText()));
            if (isAdd){
                clearTxt();
                new Alert(Alert.AlertType.CONFIRMATION,"Admin Add is Successfully").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Added is Fail").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadTable();
    }

    @FXML
    void btnDeleteAdOnAction(ActionEvent event) {
        boolean isDelete = false;
        try {
            isDelete = AdminController.getInstance().deleteAdmin(txtid.getText());
            if (isDelete){
                new Alert(Alert.AlertType.INFORMATION,"Delete is Successfully").show();
                clearTxt();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isDelete){

        }
    }

    @FXML
    void btnSeartchAdOnAction(ActionEvent event) {
        try {
            admin admin = AdminController.getInstance().seartchAdmin(txtid.getText());
            if (admin != null){
                txtid.setText(admin.getUserID());
                txtName.setText(admin.getName());
                txtEmail.setText(admin.getEmail());
                txtPassword.setText(admin.getPassword());
            }else {
                new Alert(Alert.AlertType.ERROR,"Admin is not Found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnUpdateAdOnAction(ActionEvent event) {
        boolean isAdd = false;
        try {
            isAdd = AdminController.getInstance().updateAdmin(new admin(txtid.getText(), txtName.getText(), txtEmail.getText(), txtPassword.getText()));
            if (isAdd){
                new Alert(Alert.AlertType.CONFIRMATION,"Update is Successfully").show();
                clearTxt();
            }else {
                new Alert(Alert.AlertType.ERROR,"Update is not Successfully").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void clearTxt(){
        txtid.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
    }


}
