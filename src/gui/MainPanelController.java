package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DataAccess.DatabaseHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class MainPanelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView tblData;

    @FXML
    private Label lblStatus;

    @FXML
    void initialize() {
        fetchColumnList();
        fetchRowList();

    }

    PreparedStatement preparedStatement;
    Connection connection;

    public MainPanelController() throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        connection = (Connection) databaseHandler.getDbConnection();
    }

    private ObservableList<ObservableList> data;
    String SQL = "SELECT * from useraccounts";

    //only fetch columns
    private void fetchColumnList() {

        try {
            ResultSet rs = connection.createStatement().executeQuery(SQL);

            //SQL FOR SELECTING ALL OF CUSTOMER
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tblData.getColumns().removeAll(col);
                tblData.getColumns().addAll(col);

                System.out.println("Column [" + i + "] ");

            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());

        }
    }

    //fetches rows and data from the list
    private void fetchRowList() {
        data = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            rs = connection.createStatement().executeQuery(SQL);

            while (rs.next()) {
                //Iterate Row
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            tblData.setItems(data);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

//import java.net.URL;
//import java.sql.Connection;
//import java.sql.ResultSet;
//
//import DataAccess.DatabaseHandler;
//import javafx.application.Application;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableColumn.CellDataFeatures;
//import javafx.scene.control.TableView;
//import javafx.stage.Stage;
//import javafx.util.Callback;
//
//import java.util.ResourceBundle;
//
//public class MainPanelController extends Application {
//
//    //TABLE VIEW AND DATA
//    private ObservableList<ObservableList> data;
//
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private TableView<ObservableList> tblData;
//
//    @FXML
//    private Label lblStatus;
//
//    //MAIN EXECUTOR
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    //CONNECTION DATABASE
//    public void buildData(){
//        Connection c ;
//        DatabaseHandler databaseHandler = new DatabaseHandler();
//        data = FXCollections.observableArrayList();
//        try{
//            c = databaseHandler.getDbConnection();
//            //SQL FOR SELECTING ALL OF CUSTOMER
//            String SQL = "SELECT * from useraccounts";
//            //ResultSet
//            ResultSet rs = c.createStatement().executeQuery(SQL);
//
//            /**********************************
//             * TABLE COLUMN ADDED DYNAMICALLY *
//             **********************************/
//            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
//                //We are using non property style for making dynamic table
//                final int j = i;
//                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
//                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
//                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
//                        return new SimpleStringProperty(param.getValue().get(j).toString());
//                    }
//                });
//
//                tblData.getColumns().addAll(col);
//                System.out.println("Column ["+i+"] ");
//            }
//
//            /********************************
//             * Data added to ObservableList *
//             ********************************/
//            while(rs.next()){
//                //Iterate Row
//                ObservableList<String> row = FXCollections.observableArrayList();
//                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
//                    //Iterate Column
//                    row.add(rs.getString(i));
//                }
//                System.out.println("Row [1] added "+row );
//                data.add(row);
//
//            }
//
//            //FINALLY ADDED TO TableView
//            tblData.setItems(data);
//        }catch(Exception e){
//            e.printStackTrace();
//            System.out.println("Error on Building Data");
//        }
//    }
//
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        //TableView
//        tblData = new TableView();
//        buildData();
//
//        //Main Scene
//        Scene scene = new Scene(tblData);
//
//        stage.setScene(scene);
//        stage.show();
//    }
//}