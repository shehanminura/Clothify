package Controller.login;

import DBConnection.DBConnection;
import Model.employee;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginControllerFormEmp {

    public JFXTextField txtEmail;
    public JFXTextField txtPassword;

    public void btnLoginOnAction(ActionEvent actionEvent) throws SQLException, IOException {
        String key="1234";
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);

        String SQL = "SELECT * FROM Employees where email="+"'"+txtEmail.getText()+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(SQL);
        if (resultSet.next()){
            employee employee = new employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)

            );
            System.out.println(employee);
            String X = basicTextEncryptor.decrypt(employee.getPassword());
            System.out.println(X);

            if (X.equals(txtPassword.getText())){
                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Employee_Dashbord.fxml"))));
                stage.show();
                Stage currentStage = (Stage) txtEmail.getScene().getWindow();
                currentStage.close();

            }else {
                new Alert(Alert.AlertType.ERROR,"Check your Password !!").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Employee Not Found ").show();
        }


    }
}
