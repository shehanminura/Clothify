package Controller.Products;

import Model.products;
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

import static java.util.Calendar.getInstance;

public class ProductsControllerForm implements Initializable {

    @FXML
    private TableColumn colCategory;

    @FXML
    private TableColumn colImg;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colPrice;

    @FXML
    private TableColumn colProId;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableColumn colSize;

    @FXML
    private TableColumn colSupp;

    @FXML
    private AnchorPane loadContentEmployee;

    @FXML
    private TableView tblProducts;

    @FXML
    private JFXTextField txtPrId;

    @FXML
    private JFXTextField txtProCategory;

    @FXML
    private JFXTextField txtProImg;

    @FXML
    private JFXTextField txtProName;

    @FXML
    private JFXTextField txtProPrice;

    @FXML
    private JFXTextField txtProQty;

    @FXML
    private JFXTextField txtProSize;

    @FXML
    private JFXTextField txtProSup;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProId.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("Size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        colSupp.setCellValueFactory(new PropertyValueFactory<>("SupplierID"));
        colImg.setCellValueFactory(new PropertyValueFactory<>("ImageURL"));
        loadTableProducts();
    }

    void loadTableProducts() throws SQLException {
        ObservableList<products>observableList = FXCollections.observableArrayList();
        observableList.addAll(ProductsController.getInstance().getAllProducts());
        tblProducts.setItems(observableList);
    }

    @FXML
    void btnAddProOnAction(ActionEvent event) {
        boolean isAdd = false;
        try {
            isAdd = ProductsController.getInstance().saveproducts(new products(txtPrId.getText(), txtProName.getText(), txtProCategory.getText(), txtProSize.getText(), Double.parseDouble(txtProPrice.getText()), Integer.parseInt(txtProQty.getText()), txtProSup.getText(), txtProImg.getText()));
            if (isAdd){
                new Alert(Alert.AlertType.CONFIRMATION,"Products Added Succesfully").show();
                CleanText();
            }else {
                new Alert(Alert.AlertType.ERROR,"Products Added Failed !").show();

            }
            loadTableProducts();
        } catch (SQLException e) {

            throw new RuntimeException(e);

        }
    }

    @FXML
    void btnDeleteProOnAction(ActionEvent event) {
        boolean isDelete = false;
        try {
            isDelete = ProductsController.getInstance().deleteproducts(txtPrId.getText());
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"Delete is Successfully").show();
                CleanText();
            }
            loadTableProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSeartchProOnAction(ActionEvent event) {
        try {
            products products = ProductsController.getInstance().searchproducts(txtPrId.getText());
            if (products != null){
                txtProName.setText(products.getName());
                txtProCategory.setText(products.getCategory());
                txtProSize.setText(products.getSize());
                txtProPrice.setText(String.valueOf(products.getPrice()));
                txtProQty.setText(String.valueOf(products.getQuantity()));
                txtProSup.setText(products.getSupplierID());
                txtProImg.setText(products.getImageURL());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateProOnAction(ActionEvent event) throws SQLException {
        boolean isupdated = ProductsController.getInstance().updateproducts(new products(txtPrId.getText(), txtProName.getText(), txtProCategory.getText(), txtProSize.getText(), Double.parseDouble(txtProPrice.getText()), Integer.parseInt(txtProQty.getText()), txtProSup.getText(), txtProImg.getText()));
        if (isupdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Update is SuccessFully").show();
        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"Update is Not SuccessFull").show();
        }
        loadTableProducts();
    }
    void CleanText(){
        txtPrId.setText("");
        txtProCategory.setText("");
        txtProImg.setText("");
        txtProName.setText("");
        txtProPrice.setText("");
        txtProQty.setText("");
        txtProSize.setText("");
        txtProSup.setText("");
    }

}
