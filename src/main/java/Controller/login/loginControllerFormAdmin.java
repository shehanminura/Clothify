package Controller.login;

import DBConnection.DBConnection;
import Model.admin;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginControllerFormAdmin {

    @FXML
    private AnchorPane LandRAnchor;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws SQLException, IOException {
        String key="12345";
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);

        String SQL = "SELECT * FROM admin where email ="+"'"+txtEmail.getText()+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(SQL);
        if (resultSet.next()){
            admin admin = new admin(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );

            if (basicTextEncryptor.decrypt(admin.getPassword()).equals(txtPassword.getText())){
                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Admin_Dashbord.fxml"))));
                stage.show();

            }else {
                new Alert(Alert.AlertType.ERROR,"Check your password").show();
            }

        }else {
            new Alert(Alert.AlertType.ERROR,"User is not Found !!").show();
        }


    }

}
