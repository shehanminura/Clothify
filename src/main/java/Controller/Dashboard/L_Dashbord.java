package Controller.Dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class L_Dashbord {

    @FXML
    private AnchorPane LandRAnchor;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/View/loginEmp.fxml");

        if (resource == null) {
            new Alert(Alert.AlertType.ERROR,"Order Not fOUND").show();
        }
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        this.LandRAnchor.getChildren().clear();
        this.LandRAnchor.getChildren().add(load);


    }

    public void btnLoginEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/loginEmp.fxml");

        if (resource == null){
            new Alert(Alert.AlertType.ERROR,"Employee Not Found").show();
        }

        assert resource !=null;
        Parent load = FXMLLoader.load(resource);
        this.LandRAnchor.getChildren().clear();
        this.LandRAnchor.getChildren().add(load);

    }

    public void btnLoginAdminOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("View/loginAdmin.fxml");
        if (resource == null){
            new Alert(Alert.AlertType.ERROR,"Admin not Found").show();

            assert resource !=null;
            Parent load = FXMLLoader.load(resource);
            this.LandRAnchor.getChildren().clear();
            this.LandRAnchor.getChildren().add(load);
        }
    }
}
