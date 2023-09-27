
package repository;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.sun.deploy.util.FXLoader;
import com.sun.scenario.effect.impl.state.RenderState;
import ir.huri.jcal.JalaliCalendar;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFMap;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

enum Screens {
    OrginalScreen, AlertScreen
}


public class FXMLDocumentController implements Initializable {

    PreparedStatement ps;
    ResultSet rs;
    Connection c;

    Screens currentScreen = Screens.OrginalScreen;
    boolean isShowLoadAlert = true;
    boolean isUpdating = false;
    boolean isShowProductEditScreen = false;
    boolean isShowLoadEditScreen = false;
    boolean isDeletingProduct;
    Product selectedProduct;
    Load selectedLoad;
    User selectedUser;
    @FXML
    private HBox btn_homeScreen;
    @FXML
    private HBox btn_productsList;
    @FXML
    private HBox btn_reports;
    @FXML
    private HBox btn_users;
    @FXML
    private AnchorPane productsScreen;
    @FXML
    private AnchorPane reportsScreen;
    @FXML
    private AnchorPane homeScreen;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> col_productNumber;
    @FXML
    private TableColumn<Product, String> col_productName;
    @FXML
    private TableColumn<Product, String> col_productWeight;
    @FXML
    private TableColumn<Product, String> col_productEvacuationDate;
    @FXML
    private TableColumn<Product, String> col_productCheckNumber;
    @FXML
    private TableColumn<Product, Integer> col_productAmount;
    @FXML
    private AnchorPane alertScreen;
    @FXML
    private TextField txt_productName;
    @FXML
    private TextField txt_checkNumber;
    @FXML
    private TextField txt_productEvacuationDate;
    @FXML
    private TextField txt_productWeight;
    @FXML
    private TextField txt_amount;
    @FXML
    private AnchorPane rootScreen;
    @FXML
    private AnchorPane addWindow;
    @FXML
    private StackPane mainStack;
    @FXML
    private AnchorPane loadingScreen;
    @FXML
    private TableView<Load> productLoadingTable;
    @FXML
    private TableColumn<Load, Integer> col_loadingNumber;
    @FXML
    private TableColumn<Load, String> col_loadingEvacuationDate;
    @FXML
    private TableColumn<Load, String> col_customerName;
    @FXML
    private TableColumn<Load, String> col_loaderMan;
    @FXML
    private TableColumn<Load, String> col_loadedAmount;
    @FXML
    private TableColumn<Load, String> col_remainingAmount;
    @FXML
    private AnchorPane alertNewLoading;
    @FXML
    private AnchorPane addLoadWindow;
    @FXML
    private TextField txt_loadingDate;
    @FXML
    private TextField txt_loaderName;
    @FXML
    private TextField txt_customerName;
    @FXML
    private Text lbl_addOrUpdate;
    @FXML
    private Button btn_addOrUpdate;
    @FXML
    private Text lbl_LproductName;
    @FXML
    private Text lbl_LproductWeight;
    @FXML
    private Text lbl_Lamount;
    @FXML
    private Text lbl_LproductEDate;
    @FXML
    private Text lbl_LcheckNumber;
    @FXML
    private Text lbl_LremainingAmount;
    private AnchorPane alertEditScreen;
    @FXML
    private AnchorPane editWindow;
    @FXML
    private Text lbl_addOrUpdate1;
    @FXML
    private TextField txt_EproductName;
    @FXML
    private TextField txt_EcheckNumber;
    @FXML
    private TextField txt_EproductEvacuationDate;
    @FXML
    private TextField txt_EproductWeight;
    @FXML
    private TextField txt_Eamount;
    @FXML
    private Button btn_updateProduct;
    @FXML
    private AnchorPane editScreen;
    @FXML
    private TextField txt_loadingAmount;
    @FXML
    private TextField txt_remainingAmount;
    @FXML
    private AnchorPane editLoadScreen;
    @FXML
    private AnchorPane editLoadWindow;
    @FXML
    private TextField txt_EremainingAmount;
    @FXML
    private TextField txt_EloadingDate;
    @FXML
    private TextField txt_EcustomerName;
    @FXML
    private TextField txt_EloaderName;
    @FXML
    private TextField txt_EloadingAmount;
    @FXML
    private TextField txt_productId;
    @FXML
    private TextField txt_EproductId;
    @FXML
    private Text lbl_numberOfLoadingInDay;
    @FXML
    private Text lbl_numberOfEvacuatedInDay;
    @FXML
    private Text lbl_amountOfLoadingInDay;
    @FXML
    private Text lbl_amountOfEvacuatedInDay;
    @FXML
    private Text lbl_numberOfEvacuatedInMonth;
    @FXML
    private Text lbl_amountOfLoadingInMonth;
    @FXML
    private Text lbl_numberOfLoadingInMonth;
    @FXML
    private Text lbl_amountOfEvacuatedInMonth;
    @FXML
    private Text lbl_amountOfLoadingInYear;
    @FXML
    private Text lbl_numberOfEvacuatedInYear;
    @FXML
    private Text lbl_numberOfLoadingInYear;
    @FXML
    private Text lbl_amountOfEvacuatedInYear;
    @FXML
    private Text lbl_RtotalProductsInRepository;
    @FXML
    private Text lbl_RremainingProductsInRepository;
    @FXML
    private AnchorPane usersScreen;
    @FXML
    private TableColumn<User, Integer> col_userNumber;
    @FXML
    private TableColumn<User, String> col_userName;
    @FXML
    private TextField txt_usreName;
    @FXML
    private PasswordField txt_password;
    @FXML
    private PasswordField txt_main_key;
    @FXML
    private TableView<User> usersTable;
    private ContextMenu productNamesCM = new ContextMenu();
    private ContextMenu productEVDateCM = new ContextMenu();
    @FXML
    private ImageView excelLogo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshData();
        handleTablesClick();
        updateReports();
        setPredicationForNewProduct();
    }

    @FXML
    public void changeScreen(MouseEvent e) {
        HBox selectedButton = (HBox) e.getSource();

        if (selectedButton == btn_homeScreen) {
            homeScreen.toFront();
        }

        if (selectedButton == btn_productsList) {
            productsScreen.toFront();
            setPredicationForNewProduct();
            handleExcelButton();
        }
        if (selectedButton == btn_reports) {
            reportsScreen.toFront();
        }
        if (selectedButton == btn_users) {
            usersScreen.toFront();
            updateUsersData();
            handleUserTableClicks();
        }
    }

    @FXML
    public void addProduct() {

        if (isValidate()) {
            c = DB_Helper.getConnection();
            try {
                String query = "insert into products (idNumber,name,weight,evacuationDate,checkNumber,amount) values (?,?,?,?,?,?)";
                ps = c.prepareStatement(query);
                ps.setInt(1, Integer.parseInt(txt_productId.getText()));
                ps.setString(2, txt_productName.getText());
                ps.setString(3, txt_productWeight.getText());
                ps.setString(4, getInUniversalFormat(txt_productEvacuationDate.getText()));
                ps.setString(5, txt_checkNumber.getText());
                ps.setString(6, txt_amount.getText());
                ps.execute();
                switchScreen();
                refreshData();
                clearFields();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void deleteProduct() {
        c = DB_Helper.getConnection();
        try {
            ps = c.prepareStatement("DELETE FROM products WHERE idNumber = ?");
            ps.setInt(1, selectedProduct.getIdNumber());
            ps.execute();

            ps = c.prepareStatement("DELETE FROM loads WHERE productId = ?");
            ps.setInt(1, selectedProduct.getIdNumber());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void updateProduct() {
        if (isValidateEditProductFields()) {
            c = DB_Helper.getConnection();
            try {
                ps = c.prepareStatement("UPDATE products SET idNumber = ?, name = ? , weight = ? , evacuationDate = ? ,checkNumber = ? , amount = ? where idNumber = ?");
                ps.setString(1, txt_EproductId.getText());
                ps.setString(2, txt_EproductName.getText());
                ps.setString(3, txt_EproductWeight.getText());
                ps.setString(4, getInUniversalFormat(txt_EproductEvacuationDate.getText()));
                ps.setString(5, txt_EcheckNumber.getText());
                ps.setString(6, txt_Eamount.getText());
                ps.setInt(7, selectedProduct.getIdNumber());
                ps.execute();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            refreshData();
            switchProductsScreenOrEditScreen();
        }
    }

    @FXML
    private void closeApp(MouseEvent event
    ) {
        Platform.exit();
    }

    public boolean isValidate() {

        if (txt_productId.getText().equals("")) {
            showAlert("لطفا نمبر محصول را وارد کنید");
            return false;
        }
        if (!txt_productId.getText().matches("[0-9]*") && !txt_productId.getText().matches("[۰-۹]*")) {
            showAlert("نمبر محصول باید عددی باشد");
            return false;
        }
        if (isDuplicateProductNumber(Integer.parseInt(txt_productId.getText()))) {
            showAlert("نمبر محصول تکراری است");
            return false;
        }
        if (txt_productName.getText().equals("")) {
            showAlert("لطفا نام محصول را وارد کنید");
            return false;
        }
        if (txt_productWeight.getText().equals("")) {
            showAlert("لطفا وزن را وارد کنید");
            return false;
        }
        if (!txt_productWeight.getText().matches("[0-9]*") && !txt_productWeight.getText().matches("[۰-۹]*")) {
            showAlert("وزن باید عددی باشد");
            return false;
        }
        if (txt_productEvacuationDate.getText().equals("")) {
            showAlert("لطفا تاریخ تخلیه را وارد کنید");
            return false;
        }
        if (!Date_Helper.isValidDate(txt_productEvacuationDate.getText())) {
            showAlert("فارمت تاریخ نادرست است");
            return false;
        }
        if (txt_checkNumber.getText().equals("")) {
            showAlert("لطفا نمبر چک را وارد کنید");
            return false;
        }
        if (txt_amount.getText().equals("")) {
            showAlert("لطفا تعداد را وارد کنید");
            return false;
        }
        if (!txt_amount.getText().matches("[0-9]*") && !txt_amount.getText().matches("[۰-۹]*")) {
            showAlert("تعداد باید عددی باشد");
            return false;
        }
        return true;
    }

    public boolean isDuplicateProductNumber(int productNumber) {
        boolean isExist = true;
        c = DB_Helper.getConnection();
        try {
            ps = c.prepareStatement("SELECT * FROM products WHERE idNumber =  ?");
            ps.setInt(1, productNumber);
            rs = ps.executeQuery();

            isExist = rs.next();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            releaseResources();
        }
        return isExist;
    }

    public boolean isSameWithAnotherProduct(int productNumber) {
        boolean isExist = false;
        c = DB_Helper.getConnection();
        try {
            ps = c.prepareStatement("SELECT idNumber FROM products WHERE idNumber !=  ?");
            ps.setInt(1, selectedProduct.getIdNumber());
            rs = ps.executeQuery();
            while (rs.next()) {
                if (productNumber == Integer.parseInt(rs.getString(1))) {
                    isExist = true;
                    break;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            releaseResources();
        }
        return isExist;
    }

    public boolean isValidateEditProductFields() {

        if (txt_EproductId.getText().equals("")) {
            showAlert("لطفا نمبر محصول را وارد کنید");
            return false;
        }
        if (!txt_EproductId.getText().matches("[0-9]*") && !txt_EproductId.getText().matches("[۰-۹]*")) {
            showAlert("نمبر محصول باید عددی باشد");
            return false;
        }
        if (isSameWithAnotherProduct(Integer.parseInt(txt_EproductId.getText()))) {
            showAlert("نمبر محصول تکراری است");
            return false;
        }
        if (txt_EproductName.getText().equals("")) {
            showAlert("لطفا نام محصول را وارد کنید");
            return false;
        }
        if (txt_EproductWeight.getText().equals("")) {
            showAlert("لطفا وزن را وارد کنید");
            return false;
        }
        if (!txt_EproductWeight.getText().matches("[0-9]*") && !txt_EproductWeight.getText().matches("[۰-۹]*")) {
            showAlert("وزن باید عددی باشد");
            return false;
        }
        if (txt_EproductEvacuationDate.getText().equals("")) {
            showAlert("لطفا تاریخ تخلیه را وارد کنید");
            return false;
        }
        if (!Date_Helper.isValidDate(txt_EproductEvacuationDate.getText())) {
            showAlert("فارمت تاریخ نادرست است");
            return false;
        }
        if (txt_EcheckNumber.getText().equals("")) {
            showAlert("لطفا نمبر چک را وارد کنید");
            return false;
        }
        if (txt_Eamount.getText().equals("")) {
            showAlert("لطفا تعداد را وارد کنید");
            return false;
        }
        if (!txt_Eamount.getText().matches("[0-9]*") && !txt_Eamount.getText().matches("[۰-۹]*")) {
            showAlert("تعداد باید عددی باشد");
            return false;
        }
        return true;
    }

    public void showAlert(String message) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SimpleAlert.fxml"));
            AnchorPane parent = (AnchorPane) loader.load();
            SimpleAlertController controller = loader.getController();
            controller.setMessage(message);
            JFXDialog dialog = new JFXDialog(mainStack, parent, JFXDialog.DialogTransition.TOP);
            controller.setOnButtonClicked(new CallBackInterface() {
                @Override
                public void closeDialog() {
                    dialog.close();
                }
            });
            dialog.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refreshData() {
        ObservableList<Product> productsList = FXCollections.observableArrayList();
        c = DB_Helper.getConnection();
        try {
            ps = c.prepareStatement("select * from products Order by idNumber ASC");
            rs = ps.executeQuery();
            int count = 1;
            while (rs.next()) {
                Product p = new Product(Integer.parseInt(
                        rs.getString(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        Integer.parseInt(rs.getString(6)));
                p.setNumber(count++);
                productsList.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        col_productNumber.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
        col_productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_productWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        col_productEvacuationDate.setCellValueFactory(new PropertyValueFactory<>("evacuationDate"));
        col_productCheckNumber.setCellValueFactory(new PropertyValueFactory<>("checkNumber"));
        col_productAmount.setCellValueFactory(new PropertyValueFactory<>("productAmount"));
        productTable.setItems(productsList);
        productTable.getColumns().forEach(column -> {
            column.setStyle("-fx-alignment:center");
        });
        updateReports();
        setPredicationForNewProduct();

    }

    @FXML
    public void switchScreen() {
        if (currentScreen == Screens.OrginalScreen) {
            alertScreen.toFront();
            ScaleTransition tr = new ScaleTransition(Duration.millis(300), addWindow);
            tr.setFromX(0.3);
            tr.setToX(1);
            tr.setFromY(0.3);
            tr.setToY(1);
            tr.setInterpolator(Interpolator.EASE_BOTH);
            tr.play();
            currentScreen = Screens.AlertScreen;

        } else {
            rootScreen.toFront();
            currentScreen = Screens.OrginalScreen;
        }

    }

    @FXML
    public void switchProductsScreenOrEditScreen() {
        if (isShowProductEditScreen) {
            txt_EproductId.setText(String.valueOf(selectedProduct.getIdNumber()));
            txt_EproductName.setText(selectedProduct.getName());
            txt_EproductWeight.setText(selectedProduct.getWeight());
            txt_EproductEvacuationDate.setText(selectedProduct.getEvacuationDate());
            txt_EcheckNumber.setText(selectedProduct.getCheckNumber());
            txt_Eamount.setText(String.valueOf(selectedProduct.getProductAmount()));
            editScreen.toFront();
            ScaleTransition tr = new ScaleTransition(Duration.millis(300), editWindow);
            tr.setFromX(0.3);
            tr.setToX(1);
            tr.setFromY(0.3);
            tr.setToY(1);
            tr.setInterpolator(Interpolator.EASE_BOTH);
            tr.play();
        } else {
            rootScreen.toFront();
        }
        isShowProductEditScreen = !isShowProductEditScreen;

    }

    @FXML
    public void switchLoadScreenOrLoadEditScreen() {
        if (isShowLoadEditScreen) {
            txt_EloadingDate.setText(selectedLoad.getLoadingDate());
            txt_EcustomerName.setText(selectedLoad.getCustomerName());
            txt_EloaderName.setText(selectedLoad.getWorkerName());
            txt_EloadingAmount.setText(selectedLoad.getLoadedAmount());
            txt_EremainingAmount.setText(selectedLoad.getRemainingAmount());
            editLoadScreen.toFront();
            ScaleTransition tr = new ScaleTransition(Duration.millis(300), editLoadWindow);
            tr.setFromX(0.3);
            tr.setToX(1);
            tr.setFromY(0.3);
            tr.setToY(1);
            tr.setInterpolator(Interpolator.EASE_BOTH);
            tr.play();
        } else {
            rootScreen.toFront();
        }
        isShowLoadEditScreen = !isShowLoadEditScreen;

    }

    @FXML
    public void updateLoad() {
        List<Load> loads;
        int rem = 0;
        c = DB_Helper.getConnection();
        if (isValidateEditLoad()) {
            try {

                int selectedIndex = productLoadingTable.getSelectionModel().getSelectedIndex();
                //check if there is another element before this
                if (selectedIndex > 0) {
                    loads = productLoadingTable.getItems().subList(selectedIndex - 1, productLoadingTable.getItems().size());
                    rem = Integer.parseInt(loads.get(0).getRemainingAmount());
                } else {
                    loads = productLoadingTable.getItems().subList(selectedIndex, productLoadingTable.getItems().size());
                    rem = Integer.parseInt(lbl_Lamount.getText());
                }
                ps = c.prepareStatement("UPDATE loads set loadingDate = ? ,customerName = ?,workerName = ?,loadedAmount = ?,remainingAmount = ? where idNumber = ?");
                ps.setString(1, getInUniversalFormat(txt_EloadingDate.getText()));
                ps.setString(2, txt_EcustomerName.getText());
                ps.setString(3, txt_EloaderName.getText());
                ps.setString(4, txt_EloadingAmount.getText());

                int remainingAmount = rem - Integer.parseInt(txt_EloadingAmount.getText());
                rem -= Integer.parseInt(txt_EloadingAmount.getText());
                ps.setString(5, String.valueOf(remainingAmount));
                ps.setInt(6, selectedLoad.getIdNumber());
                ps.executeUpdate();

                for (int i = selectedIndex > 0 ? 2 : 1; i < loads.size(); i++) {
                    remainingAmount = rem - Integer.parseInt(loads.get(i).getLoadedAmount());
                    rem -= Integer.parseInt(loads.get(i).getLoadedAmount());
                    c = DB_Helper.getConnection();
                    ps = c.prepareStatement("UPDATE loads SET remainingAmount = '" + (remainingAmount) + "' WHERE  idNumber = " + loads.get(i).getIdNumber() + " ;");
                    ps.executeUpdate();

                }

                refreshLoadsTableData();
                switchLoadScreenOrLoadEditScreen();
                lbl_LremainingAmount.setText(getRemainingAmount(selectedProduct.getIdNumber()) + "");
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                releaseResources();
            }
        }
    }

    public void deleteLoad() {
        c = DB_Helper.getConnection();
        try {
            ps = c.prepareStatement("DELETE FROM loads WHERE idNumber = ? ");
            ps.setInt(1, selectedLoad.getIdNumber());
            ps.execute();
            releaseResources();
            int selectedIndex = productLoadingTable.getSelectionModel().getSelectedIndex();
            List<Load> loads = productLoadingTable.getItems();
            int rem = Integer.parseInt(lbl_Lamount.getText());

            String sql = "";
            int remainingAmount;
            System.out.println(rem);
            for (int i = 0; i < loads.size(); i++) {
                remainingAmount = rem - Integer.parseInt(loads.get(i).getLoadedAmount());
                rem -= Integer.parseInt(loads.get(i).getLoadedAmount());
                c = DB_Helper.getConnection();
                ps = c.prepareStatement("UPDATE loads SET remainingAmount = '" + (remainingAmount) + "' WHERE  idNumber = " + loads.get(i).getIdNumber() + " ;");
                ps.executeUpdate();
                releaseResources();

            }
            lbl_LremainingAmount.setText(String.valueOf(getRemainingAmount(selectedProduct.getIdNumber())));

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            releaseResources();
        }
        updateReports();
    }

    @FXML
    public void switchLoadAlertOrLoadingPage() {

        if (isShowLoadAlert) {
            alertNewLoading.toFront();
            ScaleTransition sr = new ScaleTransition(Duration.millis(300), addLoadWindow);
            sr.setInterpolator(Interpolator.EASE_OUT);
            sr.setFromX(1.1);
            sr.setFromY(1.1);
            sr.setToX(1);
            sr.setToY(1);
            sr.play();

            clearNewLoadFields();
        } else {
            loadingScreen.toFront();
            alertNewLoading.toBack();
        }
        isShowLoadAlert = !isShowLoadAlert;

    }

    public void handleTablesClick() {
        productTable.setRowFactory(new Callback<TableView<Product>, TableRow<Product>>() {
            @Override
            public TableRow<Product> call(TableView<Product> param) {
                TableRow<Product> row = new TableRow<>();

                ContextMenu contextMenu = new ContextMenu();
                MenuItem updateItem = new MenuItem("ویرایش محصول");
                updateItem.setOnAction(ee -> {
                    selectedProduct = productTable.getSelectionModel().getSelectedItem();
                    isShowProductEditScreen = true;
                    switchProductsScreenOrEditScreen();
                });
                MenuItem deleteItem = new MenuItem(" حذف محصول ");
                deleteItem.setOnAction(ee -> {
                    selectedProduct = productTable.getSelectionModel().getSelectedItem();
                    isDeletingProduct = true;
                    showProductDeleteRequestAlert(1);

                });

                contextMenu.getItems().addAll(updateItem, deleteItem);
                row.setContextMenu(contextMenu);

                return row;
            }
        });

        productLoadingTable.setRowFactory(new Callback<TableView<Load>, TableRow<Load>>() {
            @Override
            public TableRow<Load> call(TableView<Load> param) {
                TableRow<Load> row = new TableRow<>();

                ContextMenu contextMenu = new ContextMenu();
                MenuItem updateItem = new MenuItem("ویرایش بارگیری");
                updateItem.setOnAction(ee -> {
                    selectedLoad = productLoadingTable.getSelectionModel().getSelectedItem();
                    isShowLoadEditScreen = true;

                    switchLoadScreenOrLoadEditScreen();
                });
                MenuItem deleteItem = new MenuItem(" حذف  بارگیری ");
                deleteItem.setOnAction(ee -> {
                    selectedLoad = productLoadingTable.getSelectionModel().getSelectedItem();
                    isDeletingProduct = false;
                    showProductDeleteRequestAlert(0);
                });

                contextMenu.getItems().addAll(updateItem, deleteItem);
                row.setContextMenu(contextMenu);

                return row;
            }
        });

        productTable.setOnMousePressed(e -> {

            if (e.getClickCount() == 2 && e.getButton() == MouseButton.PRIMARY) {
                selectedProduct = productTable.getSelectionModel().getSelectedItem();
                lbl_LproductName.setText(selectedProduct.getName());
                lbl_LproductWeight.setText(selectedProduct.getWeight());
                lbl_LproductEDate.setText(selectedProduct.getEvacuationDate());
                lbl_LcheckNumber.setText(selectedProduct.getCheckNumber());
                lbl_Lamount.setText(String.valueOf(selectedProduct.getProductAmount()));
                lbl_LremainingAmount.setText(String.valueOf(getRemainingAmount(selectedProduct.getIdNumber())));

                txt_loadingAmount.textProperty().addListener(l -> {
                    if (!txt_loadingAmount.getText().equals("")) {
                        int loadAmount = Integer.parseInt(txt_loadingAmount.getText());
                        int rem = getRemainingAmount(selectedProduct.getIdNumber());

                        if ((loadAmount <= rem) && loadAmount > 0) {
                            txt_remainingAmount.setStyle("-fx-border-color:orange;"
                                    + "-fx-border-width:1.5;-fx-border-radius:100;-fx-background-color:transparent");
                            txt_remainingAmount.setText(String.valueOf(rem - loadAmount));
                        } else {
                            txt_remainingAmount.setStyle("-fx-border-color:red;"
                                    + "-fx-border-width:1.5;-fx-border-radius:100;-fx-background-color:transparent");
                            //showAlert("مقدار بارگیری بیشتر از باقیمانده است");
                        }

                    } else {
                        txt_remainingAmount.setStyle("-fx-border-color:orange;-fx-border-width:1.5;-fx-border-radius:100;-fx-background-color:transparent");
                        txt_remainingAmount.setText("");
                    }

                });
                txt_EloadingAmount.textProperty().addListener(l -> {
                    if (!txt_EloadingAmount.getText().equals("")) {
                        int loadAmount = Integer.parseInt(txt_EloadingAmount.getText());
                        int rem = getRemainingAmountExcept(selectedProduct.getIdNumber(), selectedLoad.getIdNumber());
                        if ((loadAmount <= rem) && loadAmount > 0) {
                            txt_EremainingAmount.setStyle("-fx-border-color:orange;"
                                    + "-fx-border-width:1.5;-fx-border-radius:100;-fx-background-color:transparent");
                            txt_EremainingAmount.setText(String.valueOf(rem - loadAmount));
                        } else {
                            txt_EremainingAmount.setStyle("-fx-border-color:red;"
                                    + "-fx-border-width:1.5;-fx-border-radius:100;-fx-background-color:transparent");
                            //showAlert("مقدار بارگیری بیشتر از باقیمانده است");
                        }

                    } else {
                        txt_EremainingAmount.setStyle("-fx-border-color:orange;-fx-border-width:1.5;-fx-border-radius:100;-fx-background-color:transparent");
                        txt_EremainingAmount.setText("");
                    }

                });
                refreshLoadsTableData();
                loadingScreen.toFront();
                TranslateTransition tr = new TranslateTransition(Duration.millis(300), loadingScreen);
                tr.setFromY(loadingScreen.getHeight());
                tr.setToY(0);
                tr.play();

            }

        });
    }

    private void clearFields() {
        txt_productEvacuationDate.setText("");
        txt_amount.setText("");
        txt_checkNumber.setText("");
        txt_productWeight.setText("");
        txt_productName.setText("");
    }

    @FXML
    public void doNothing(MouseEvent e) {
        e.consume();
    }

    @FXML
    public void addLoads() {

        if (isValidateNewLoad()) {
            int loadingAmount = Integer.parseInt(txt_loadingAmount.getText());
            int amount = Integer.parseInt(lbl_Lamount.getText());
            int remainingAmount = Integer.parseInt(lbl_LremainingAmount.getText());
            int curRem = remainingAmount - loadingAmount;
            c = DB_Helper.getConnection();

            try {
                ps = c.prepareStatement("Insert into loads (idNumber,loadingDate,customerName,workerName,loadedAmount,remainingAmount,productId) values (Null,?,?,?,?,?,?)");
                ps.setString(1, getInUniversalFormat(txt_loadingDate.getText()));
                ps.setString(2, txt_customerName.getText());
                ps.setString(3, txt_loaderName.getText());
                ps.setString(4, txt_loadingAmount.getText());
                ps.setString(5, String.valueOf(curRem));
                ps.setString(6, String.valueOf(selectedProduct.getIdNumber()));
                ps.execute();
                refreshLoadsTableData();
                switchLoadAlertOrLoadingPage();
                lbl_LremainingAmount.setText(getRemainingAmount(selectedProduct.getIdNumber()) + "");
                clearNewLoadFields();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public boolean isValidateNewLoad() {
        String loadingAmount = txt_loadingAmount.getText();
        if (txt_loadingDate.getText().equals("")) {
            showAlert("لطفا تاریخ بارگیری را وارد کنید");
            return false;
        }
        if (!Date_Helper.isValidDate(txt_loadingDate.getText())) {
            showAlert("فارمت تاریخ نادرست است");
            return false;
        }
        if (txt_customerName.getText().equals("")) {
            showAlert("لطفا نام مشتری را وارد کنید");
            return false;
        }
        if (txt_loaderName.getText().equals("")) {
            showAlert("لطفا نام جوالی را وارد کنید");
            return false;
        }
        if (loadingAmount.equals("")) {
            showAlert("لطفا مقدار بارگیری را وارد کنید");
            return false;
        }
        if (!loadingAmount.matches("[0-9]*") && !loadingAmount.matches("[۰-۹]*")) {
            showAlert("مقدار بارگیری باید یک عدد باشد");
            return false;
        }
        int loadAmount = Integer.parseInt(txt_loadingAmount.getText());
        int rem = getRemainingAmount(selectedProduct.getIdNumber());

        if (!(loadAmount <= rem) || loadAmount < 1) {
            showAlert("مقدار بارگیری درست نیست");
            return false;
        }

        return true;

    }

    public boolean isValidateEditLoad() {
        String loadingAmount = txt_EloadingAmount.getText();
        if (txt_EloadingDate.getText().equals("")) {
            showAlert("لطفا تاریخ بارگیری را وارد کنید");
            return false;
        }
        if (!Date_Helper.isValidDate(txt_EloadingDate.getText())) {
            showAlert("فارمت تاریخ نادرست است");
            return false;
        }
        if (txt_EcustomerName.getText().equals("")) {
            showAlert("لطفا نام مشتری را وارد کنید");
            return false;
        }
        if (txt_EloaderName.getText().equals("")) {
            showAlert("لطفا نام جوالی را وارد کنید");
            return false;
        }
        if (loadingAmount.equals("")) {
            showAlert("لطفا مقدار بارگیری را وارد کنید");
            return false;
        }
        if (!loadingAmount.matches("[0-9]*") && !loadingAmount.matches("[۰-۹]*")) {
            showAlert("مقدار بارگیری باید یک عدد باشد");
            return false;
        }
        int loadAmount = Integer.parseInt(txt_EloadingAmount.getText());
        int rem = getRemainingAmountExcept(selectedProduct.getIdNumber(), selectedLoad.getIdNumber());

        if (!(loadAmount <= rem) || loadAmount < 1) {
            showAlert("مقدار بارگیری درست نیست");
            return false;
        }

        return true;

    }

    public void clearNewLoadFields() {
        txt_loadingAmount.setText("");
        txt_loadingDate.setText("");
        txt_customerName.setText("");
        txt_loaderName.setText("");
        txt_remainingAmount.setText("");
    }

    public void refreshLoadsTableData() {
        ObservableList<Load> loadsList = FXCollections.observableArrayList();
        c = DB_Helper.getConnection();
        try {
            ps = c.prepareStatement("SELECT * FROM loads where productId = ?  Order by idNumber ASC");
            ps.setInt(1, selectedProduct.getIdNumber());
            rs = ps.executeQuery();
            int no = 1;
            while (rs.next()) {
                Load load = new Load(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7)
                );
                load.setNumber(no++);
                loadsList.add(load);

            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_loadingNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_loadingEvacuationDate.setCellValueFactory(new PropertyValueFactory<>("loadingDate"));
        col_customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        col_loaderMan.setCellValueFactory(new PropertyValueFactory<>("workerName"));
        col_loadedAmount.setCellValueFactory(new PropertyValueFactory<>("loadedAmount"));
        col_remainingAmount.setCellValueFactory(new PropertyValueFactory<>("remainingAmount"));
        productLoadingTable.setItems(loadsList);

        updateReports();
    }

    public int getRemainingAmount(int productId) {
        int rem = 0;
        int loadsAmount = 0;
        c = DB_Helper.getConnection();
        try {
            ps = c.prepareStatement("SELECT loadedAmount FROM loads where productId = ?");
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                loadsAmount += Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rem = Integer.parseInt(lbl_Lamount.getText()) - loadsAmount;
        return rem;
    }

    public int getRemainingAmountExcept(int productId, int loadId) {
        int rem = 0;
        int loadsAmount = 0;
        c = DB_Helper.getConnection();
        try {
            ps = c.prepareStatement("SELECT loadedAmount FROM loads where productId = ? AND idNumber != ?");
            ps.setInt(1, productId);
            ps.setInt(2, loadId);
            rs = ps.executeQuery();
            while (rs.next()) {
                loadsAmount += Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rem = Integer.parseInt(lbl_Lamount.getText()) - loadsAmount;
        return rem;
    }

    public void releaseResources() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (ps != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (c != null) {
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteRequestedItem() {

        if (isDeletingProduct) {
            deleteProduct();
            refreshData();
        } else {
            deleteLoad();
            refreshLoadsTableData();
        }
        isDeletingProduct = !isDeletingProduct;
    }

    public void updateUsersData() {
        ObservableList<User> users = FXCollections.observableArrayList();
        c = DB_Helper.getConnection();
        try {
            ps = c.prepareStatement("SELECT * FROM users");
            rs = ps.executeQuery();
            int count = 1;
            while (rs.next()) {
                User user = new User(
                        Integer.parseInt(rs.getString(1)),
                        rs.getString(2),
                        rs.getString(3)
                );
                user.setNumber(count++);
                users.add(user);
            }

            col_userNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
            col_userName.setCellValueFactory(new PropertyValueFactory<>("name"));
            usersTable.setItems(users);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showProductDeleteRequestAlert(int alertType) {

        if (alertType == 1) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteAlert.fxml"));
                Parent p = loader.load();
                DeleteAlertController controller = loader.getController();

                controller.setMessage("آیا از حذف ".concat(selectedProduct.getName()).concat(" ").concat(" مطمءن هستید ؟"));
                JFXDialog dialog = new JFXDialog(mainStack, (AnchorPane) p, JFXDialog.DialogTransition.TOP);
                controller.setCallBack(new DeleteCallBack() {
                    @Override
                    public void onDeleteAlertResult() {
                        deleteRequestedItem();
                        dialog.close();
                    }
                });
                dialog.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteAlert.fxml"));
                Parent p = loader.load();
                DeleteAlertController controller = loader.getController();

                controller.setMessage("آیا از حذف بارگیری برای ".concat(selectedLoad.getCustomerName()).concat(" ").concat(" مطمءن هستید ؟"));
                JFXDialog dialog = new JFXDialog(mainStack, (AnchorPane) p, JFXDialog.DialogTransition.TOP);
                controller.setCallBack(new DeleteCallBack() {
                    @Override
                    public void onDeleteAlertResult() {
                        deleteRequestedItem();
                        dialog.close();
                    }

                    @Override
                    public void closeOpenedDialog() {
                        dialog.close();
                    }
                });
                dialog.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void updateReports() {

        JalaliCalendar afgCalendar = new JalaliCalendar();
        int today = afgCalendar.getDay();
        int currentMonth = afgCalendar.getMonth();
        int currentYear = afgCalendar.getYear();

        ObservableList<Load> loads = FXCollections.observableArrayList();
        ObservableList<Product> products = FXCollections.observableArrayList();

        c = DB_Helper.getConnection();
        try {
            ps = c.prepareStatement("SELECT loadingDate, loadedAmount from loads");
            rs = ps.executeQuery();

            while (rs.next()) {
                loads.add(new Load(0, rs.getString(1), "", "", rs.getString(2), "", 0));
            }

            releaseResources();
            c = DB_Helper.getConnection();
            ps = c.prepareStatement("SELECT evacuationDate , amount FROM products ORDER BY idNumber ASC");
            rs = ps.executeQuery();

            while (rs.next()) {
                products.add(new Product(0, "", "", rs.getString(1), "", Integer.parseInt(rs.getString(2))));
            }

            lbl_RtotalProductsInRepository
                    .setText(String.valueOf(products.stream().count()));

            lbl_RremainingProductsInRepository
                    .setText(String.valueOf(products
                            .stream()
                            .map(p -> p.getProductAmount())
                            .reduce((x, y) -> x + y).orElse(0)
                            - loads
                                    .stream()
                                    .map(l -> Integer.parseInt(l.getLoadedAmount()))
                                    .reduce((x, y) -> x += y).orElse(0)));

            //start of daily reports
            lbl_numberOfLoadingInDay
                    .setText(String.valueOf(loads.stream()
                            .filter(l -> l.getLoadingDate()
                            .equals(currentYear + "/" + currentMonth + "/" + today))
                            .count()));
            lbl_amountOfLoadingInDay
                    .setText(String.valueOf(loads
                            .stream()
                            .filter(l -> l.getLoadingDate().equals(currentYear + "/" + currentMonth + "/" + today))
                            .map(l -> Integer.parseInt(l.getLoadedAmount())).reduce((x, y) -> x += y).orElse(0)));
            lbl_numberOfEvacuatedInDay.setText(
                    String.valueOf(
                            products.stream()
                                    .filter(p -> p.getEvacuationDate().contains(currentYear + "/" + currentMonth + "/" + today)).count()));

            lbl_amountOfEvacuatedInDay
                    .setText(String.valueOf(
                            products.stream()
                                    .filter(p -> p.getEvacuationDate().contains(currentYear + "/" + currentMonth + "/" + today))
                                    .map(p -> p.getProductAmount()).reduce((x, y) -> x += y).orElse(0)
                    ));

            //end of daily reports
            //Start of monthly reports
            lbl_numberOfLoadingInMonth
                    .setText(String.valueOf(loads.stream()
                            .filter(l -> l.getLoadingDate()
                            .contains(currentYear + "/" + currentMonth))
                            .count()));
            lbl_amountOfLoadingInMonth
                    .setText(String.valueOf(loads
                            .stream()
                            .filter(l -> l.getLoadingDate().contains(currentYear + "/" + currentMonth))
                            .map(l -> Integer.parseInt(l.getLoadedAmount())).reduce((x, y) -> x += y).orElse(0)));
            lbl_numberOfEvacuatedInMonth
                    .setText(String.valueOf(
                            products.stream()
                                    .filter(p -> p.getEvacuationDate().contains(currentYear + "/" + currentMonth)).count()));

            lbl_amountOfEvacuatedInMonth
                    .setText(String.valueOf(
                            products.stream()
                                    .filter(p -> p.getEvacuationDate().contains(currentYear + "/" + currentMonth))
                                    .map(p -> p.getProductAmount()).reduce((x, y) -> x += y).orElse(0)
                    ));

            //end of monthly report
            //start of yearly reports
            lbl_numberOfLoadingInYear
                    .setText(String.valueOf(loads.stream()
                            .filter(l -> l.getLoadingDate()
                            .contains(String.valueOf(currentYear)))
                            .count()));
            lbl_amountOfLoadingInYear
                    .setText(String.valueOf(loads
                            .stream()
                            .filter(l -> l.getLoadingDate().contains(String.valueOf(currentYear)))
                            .map(l -> Integer.parseInt(l.getLoadedAmount())).reduce((x, y) -> x += y).orElse(0)));
            lbl_numberOfEvacuatedInYear
                    .setText(String.valueOf(
                            products.stream()
                                    .filter(p -> p.getEvacuationDate().contains(String.valueOf(currentYear))).count()));

            lbl_amountOfEvacuatedInYear
                    .setText(String.valueOf(
                            products.stream()
                                    .filter(p -> p.getEvacuationDate().contains(String.valueOf(currentYear)))
                                    .map(p -> p.getProductAmount()).reduce((x, y) -> x += y).orElse(0)
                    ));

            //yearly reports
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void handleUserTableClicks() {
        usersTable.setRowFactory(new Callback<TableView<User>, TableRow<User>>() {
            @Override
            public TableRow<User> call(TableView<User> param) {
                TableRow<User> row = new TableRow<>();
                ContextMenu contextMenu = new ContextMenu();
                MenuItem updateItem = new MenuItem("ویرایش");

                

                updateItem.setOnAction(ee -> {
                    selectedUser = usersTable.getSelectionModel().getSelectedItem();
                    lbl_addOrUpdate.setText("ویرایش کاربر");
                    txt_usreName.setText(selectedUser.getName());
                    txt_password.setText(selectedUser.getPass());
                    btn_addOrUpdate.setText("ویرایش");

                });
                MenuItem deleteItem = new MenuItem("حذف");
                deleteItem.setOnAction(ee -> {
                    selectedUser = usersTable.getSelectionModel().getSelectedItem();
                    deleteUser();
                    updateUsersData();
                });

                if (!isManager(selectedUser)) {
                    contextMenu.getItems().addAll(updateItem, deleteItem);
                } else {
                    contextMenu.getItems().addAll(updateItem);
                }

                row.setContextMenu(contextMenu);

                return row;
            }
        });
    }

    public void deleteUser() {
        c = DB_Helper.getConnection();
        try {
            ps = c.prepareStatement("DELETE FROM users WHERE id = ?");
            ps.setInt(1, selectedUser.getId());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void addOrUpdateUser(ActionEvent e) {
        Button b = (Button) e.getSource();
        c = DB_Helper.getConnection();
        if (isValidateUser()) {
            if (isManager()) {
                try {
                    if (b.getText().equals("افزودن")) {
                        ps = c.prepareStatement("INSERT INTO users (name,password) VALUES(?,?)");
                    } else {
                        ps = c.prepareStatement("UPDATE users SET name = ?, password = ? WHERE id = ?");
                        ps.setInt(3, selectedUser.getId());
                    }
                    ps.setString(1, txt_usreName.getText());
                    ps.setString(2, txt_password.getText());
                    ps.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                clearUserFields();
                updateUsersData();
            } else {
                showAlertForNewUserValidation("کلید اصلی نادرست است");
                clearUserFields();
            }
        }
    }

    public void clearUserFields() {
        lbl_addOrUpdate.setText("کاربر جدید");
        txt_usreName.setText("");
        txt_password.setText("");
        txt_main_key.setText("");
        btn_addOrUpdate.setText("افزودن");

    }

    public boolean isManager() {
        c = DB_Helper.getConnection();
        try {
            ps = c.prepareStatement("SELECT password FROM users WHERE isManager = ?");
            ps.setString(1, "yes");
            rs = ps.executeQuery();
            rs.next();
            if (rs.getString(1).equals(txt_main_key.getText())) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isManager(User u) {
        c = DB_Helper.getConnection();
        try {
            ps = c.prepareStatement("SELECT name,password FROM users WHERE isManager = ?");
            ps.setString(1, "yes");
            rs = ps.executeQuery();
            rs.next();
            if (rs.getString(1).equals(u.getName()) && rs.getString(2).equals(u.getPass())) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isValidateUser() {
        if (txt_usreName.getText().equals("")) {
            showAlertForNewUserValidation("لطفا نام را وارد کنید");
            return false;
        }
        if (txt_password.getText().equals("")) {
            showAlertForNewUserValidation("لطفا رمز عبور را وارد کنید");
            return false;
        }
        if (txt_main_key.getText().equals("")) {
            showAlertForNewUserValidation("لطفا کلید اصلی را وارد کنید");
            return false;
        }

        return true;
    }

    public void showAlertForNewUserValidation(String message) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SimpleAlert.fxml"));
            AnchorPane alert = (AnchorPane) loader.load();
            SimpleAlertController controller = loader.getController();
            controller.setMessage(message);
            JFXDialog dialog = new JFXDialog(mainStack, alert, JFXDialog.DialogTransition.TOP);

            controller.setOnButtonClicked(new CallBackInterface() {
                @Override
                public void closeDialog() {
                    dialog.close();
                }
            });
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getInUniversalFormat(String date) {
        String universal = "";
        String[] dateParts = date.split("/");
        universal = Integer.parseInt(dateParts[0]) + "/" + Integer.parseInt(dateParts[1]) + "/" + Integer.parseInt(dateParts[2]);

        return universal;
    }

    public void setPredicationForNewProduct() {

        Set<String> names = new TreeSet<>();
        Set<String> dates = new TreeSet<>();

        c = DB_Helper.getConnection();
        try {
            ps = c.prepareStatement("SELECT DISTINCT name FROM products");
            rs = ps.executeQuery();
            while (rs.next()) {
                names.add(rs.getString(1));
            }
            JalaliCalendar calendar = new JalaliCalendar();
            String today = calendar.getYear() + "/" + calendar.getMonth() + 1 + "/" + calendar.getDay();
            dates.add(today);
        
            SmartTextField.bindAutoCompletion(txt_productName, productNamesCM, names, false);
            SmartTextField.bindAutoCompletion(txt_productEvacuationDate, productEVDateCM, dates, false);

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void handleExcelButton() {
        excelLogo.setOnMouseEntered((event) -> {
            ScaleTransition tr = new ScaleTransition(Duration.ONE, excelLogo);
            tr.setFromX(1);
            tr.setFromY(1);

            tr.setToX(1.5);
            tr.setToY(1.5);
            tr.play();

        });
        excelLogo.setOnMouseExited((event) -> {
            ScaleTransition tr = new ScaleTransition(Duration.ONE, excelLogo);
            tr.setFromX(1.5);
            tr.setFromY(1.5);

            tr.setToX(1);
            tr.setToY(1);
            tr.play();

        });

        excelLogo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save File");
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Excel", ".xlsx"));

                File selectedFile = fileChooser.showSaveDialog(excelLogo.getScene().getWindow());

                if (selectedFile != null) {

                    FileOutputStream fio;
                    try {
                        ObservableList<Product> products = productTable.getItems();
                        XSSFWorkbook workbook = new XSSFWorkbook();
                        for (int r = 0; r < products.size(); r++) {

                            XSSFSheet sheet = workbook.createSheet(r + 1 + "");
                            XSSFRow headerRow = sheet.createRow(3);
                            XSSFRow subTitleRow = sheet.createRow(5);

                            Product p = products.get(r);

                            headerRow.createCell(6).setCellValue((r + 1) + "                                                                  "
                                    + p.getName() + p.getWeight() + " کیلو  نمبر" + ": " + p.getNumber());
                            //subTitleRow.createCell(6).setCellValue();

                            subTitleRow.createCell(6).setCellValue("نمبر چک" + ": " + p.getCheckNumber() + "    تعداد" + ": " + p.getProductAmount());
                            subTitleRow.createCell(9).setCellValue("تاریخ تخلیه           " + " :" + p.getEvacuationDate());
                            sheet.addMergedRegion(new CellRangeAddress(3, 4, 6, 11));
                            sheet.addMergedRegion(new CellRangeAddress(5, 6, 6, 8));
                            sheet.addMergedRegion(new CellRangeAddress(5, 6, 9, 11));

                            XSSFRow colTitleRow = sheet.createRow(7);
                            colTitleRow.createCell(11).setCellValue("شماره");
                            colTitleRow.createCell(10).setCellValue("تاریخ بارگیری");
                            colTitleRow.createCell(9).setCellValue("اسم مشتری");
                            colTitleRow.createCell(8).setCellValue("اسم جوالی");
                            colTitleRow.createCell(7).setCellValue("تعداد بارشده ");
                            colTitleRow.createCell(6).setCellValue("تعداد باقیمانده");

                            CellStyle style = workbook.createCellStyle();
                            style.setBorderTop(BorderStyle.THIN);
                            style.setBorderRight(BorderStyle.THIN);
                            style.setBorderLeft(BorderStyle.THIN);
                            style.setAlignment(HorizontalAlignment.CENTER);

                            sheet.getRow(3).getCell(6).setCellStyle(style);
                            sheet.getRow(3).createCell(7).setCellStyle(style);
                            sheet.getRow(3).createCell(8).setCellStyle(style);
                            sheet.getRow(3).createCell(9).setCellStyle(style);
                            sheet.getRow(3).createCell(10).setCellStyle(style);
                            sheet.getRow(3).createCell(11).setCellStyle(style);
                            XSSFRow row4 = sheet.createRow(4);
                            row4.createCell(6).setCellStyle(style);
                            row4.createCell(11).setCellStyle(style);
                            sheet.getRow(5).createCell(11).setCellStyle(style);
                            sheet.getRow(5).createCell(10).setCellStyle(style);
                            sheet.getRow(5).getCell(9).setCellStyle(style);
                            sheet.getRow(5).createCell(8).setCellStyle(style);
                            sheet.getRow(5).createCell(7).setCellStyle(style);
                            sheet.getRow(5).getCell(6).setCellStyle(style);

                            XSSFRow row6 = sheet.createRow(6);
                            row6.createCell(6).setCellStyle(style);
                            row6.createCell(9).setCellStyle(style);
                            row6.createCell(11).setCellStyle(style);
                            for (int i = 6; i <= 11; i++) {
                                sheet.autoSizeColumn(i);
                                sheet.getRow(7).getCell(i).setCellStyle(style);
                            }

                            c = DB_Helper.getConnection();
                            ps = c.prepareStatement("SELECT * from loads WHERE productId = ?");
                            ps.setInt(1, p.getIdNumber());
                            rs = ps.executeQuery();
                            int rowNumber = 8;
                            int loadNumber = 1;
                            while (rs.next()) {
                                XSSFRow sheetRow = sheet.createRow(rowNumber++);
                                sheetRow.createCell(11).setCellValue(loadNumber++);
                                sheetRow.createCell(10).setCellValue(rs.getString(2));
                                sheetRow.createCell(9).setCellValue(rs.getString(3));
                                sheetRow.createCell(8).setCellValue(rs.getString(4));
                                sheetRow.createCell(7).setCellValue(rs.getString(5));
                                sheetRow.createCell(6).setCellValue(rs.getString(6));
                                for (int i = 6; i <= 11; i++) {
                                    sheetRow.getCell(i).setCellStyle(style);
                                }

                            }
                            releaseResources();

                        }
                        String path = selectedFile.getPath();
                        fio = new FileOutputStream(path);
                        workbook.write(fio);
                        fio.close();
                    } catch (Exception ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });
    }
}
