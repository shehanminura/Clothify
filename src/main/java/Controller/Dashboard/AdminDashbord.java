package Controller.Dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class AdminDashbord {

    @FXML
    private AnchorPane loadContentAdmin;

    @FXML
    void btnAddEmployeeOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("../../View/Employee_Form.fxml");

        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        this.loadContentAdmin.getChildren().clear();
        this.loadContentAdmin.getChildren().add(load);
    }

    @FXML
    void btnAddItemOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("../../View/Products_Form.fxml");

        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        this.loadContentAdmin.getChildren().clear();
        this.loadContentAdmin.getChildren().add(load);
    }

    @FXML
    void btnAddSupplierOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("../../View/Supplier_Form.fxml");

        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        this.loadContentAdmin.getChildren().clear();
        this.loadContentAdmin.getChildren().add(load);

    }

    @FXML
    void btnOrderDetailsOnAction(ActionEvent event) {

    }

    @FXML
    void btnOrderOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("../../View/Order_Form.fxml");

        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        this.loadContentAdmin.getChildren().clear();
        this.loadContentAdmin.getChildren().add(load);

    }

    @FXML
    void btnReportsOnAction(ActionEvent event) {

    }

    public void btnAdminOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("../../View/Admin_Form.fxml");

        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        this.loadContentAdmin.getChildren().clear();
        this.loadContentAdmin.getChildren().add(load);

    }
}
