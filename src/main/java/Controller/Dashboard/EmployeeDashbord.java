package Controller.Dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class EmployeeDashbord {

    @FXML
    private AnchorPane loadContentEmployee;

    @FXML
    void btnItemOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("../../view/Products_Form.fxml");

        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        this.loadContentEmployee.getChildren().clear();
        this.loadContentEmployee.getChildren().add(load);
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {

    }

    @FXML
    void btnOrderDetailsOnAction(ActionEvent event) {

    }

    @FXML
    void btnOrderOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("../../View/Order_Form.fxml");
        assert resource !=null;
        Parent load = FXMLLoader.load(resource);
        this.loadContentEmployee.getChildren().clear();
        this.loadContentEmployee.getChildren().add(load);
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("../../View/Supplier_Form.fxml");

        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        this.loadContentEmployee.getChildren().clear();
        this.loadContentEmployee.getChildren().add(load);
    }

}
