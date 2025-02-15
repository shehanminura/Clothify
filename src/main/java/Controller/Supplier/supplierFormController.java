package Controller.Supplier;


import Model.supplier;
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
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class supplierFormController implements Initializable {

    @FXML
    private TableColumn colCompany;

    @FXML
    private TableColumn colEmail;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableView<supplier> tblSupplier;

    @FXML
    private JFXTextField txtCompany;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("SupplierID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("Company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        loadTableSupplier();

    }

    void loadTableSupplier() throws SQLException {
        ObservableList<supplier>supplierObservableList = FXCollections.observableArrayList();
        supplierObservableList.addAll(supplierController.getInstance().getAllSupplier());
        tblSupplier.setItems(supplierObservableList);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {
        boolean isadd = supplierController.getInstance().saveSupplier(new supplier(txtId.getText(),txtName.getText(),txtCompany.getText(),txtEmail.getText()));
        if (isadd){
            new Alert(Alert.AlertType.CONFIRMATION,"Supplier Add is Successfully").show();
            cleanText();
        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"Supplier Add is not Successfully").show();
        }
        loadTableSupplier();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            boolean deleted =supplierController.getInstance().deleteSupplier(txtId.getText());
            if (deleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Delete is Successfully").show();
                cleanText();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Delete is Successfully").show();
            }
            loadTableSupplier();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSeartchOnAction(ActionEvent event) {
        try {
            supplier supplier= supplierController.getInstance().searchSupplier(txtId.getText());
            if (supplier != null){
                txtId.setText(supplier.getSupplierID());
                txtName.setText(supplier.getName());
                txtCompany.setText(supplier.getCompany());
                txtEmail.setText(supplier.getEmail());
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier is Found").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Supplier is not Found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        boolean isUpdate = supplierController.getInstance().updateSupplier(new supplier(txtId.getText(), txtName.getText(), txtCompany.getText(), txtEmail.getText()));
        if (isUpdate){
            new Alert(Alert.AlertType.CONFIRMATION,"Supplier is Updated").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Supplier is not Updated").show();
        }
        loadTableSupplier();
    }

    void cleanText(){
        txtId.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtCompany.setText("");
    }
}
