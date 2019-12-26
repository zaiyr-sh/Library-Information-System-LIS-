//package gui;
//import java.net.URL;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import DataAccess.DatabaseHandler;
//import classes.BookInfo;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Alert;
//import javafx.scene.control.SelectionMode;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.input.MouseEvent;
//import classes.BookInfo;
//
//
//public class UserAllBooksController implements Initializable {
//
//    @FXML
//    private TableColumn<BookInfo, String> titleColumn;
//    @FXML
//    private TableColumn<BookInfo, String> isbnColumn;
//    @FXML
//    private TableColumn<BookInfo, String> authorColumn;
//    @FXML
//    private TableColumn<BookInfo, String> publisherColumn;
//    @FXML
//    private TableColumn<BookInfo, Integer> numcopies;
//    @FXML
//    private TableView<BookInfo> bookInfoTable;
//    @FXML
//    private TextField titleEditField;
//    @FXML
//    private TextField AuthorEditField;
//    @FXML
//    private TextField PublisherEditField;
//
//    static ObservableList<BookInfo> bookInfoList=FXCollections.observableArrayList();
//
//    @FXML
//    private TextField numofCopies;
//    @FXML
//    private TextField ISBNEditField;
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        bookInfoTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        DatabaseHandler dbAction=new DatabaseHandler();
//        try {
//            bookInfoList= dbAction.getAllbookInfo();
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(UserAllBooksController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        titleColumn.setCellValueFactory(new PropertyValueFactory<BookInfo, String>("title"));
//        isbnColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("Isbn"));
//        authorColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("author"));
//        publisherColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("publisher"));
//        numcopies.setCellValueFactory(new PropertyValueFactory<BookInfo,Integer>("numcopies"));
//
//        bookInfoTable.setItems(bookInfoList);
//    }
//
//
//    @FXML
//    private void updateButtonAction(MouseEvent event) throws SQLException, ClassNotFoundException {
//        if(titleEditField.getText().equals("") || ISBNEditField.getText().equals("") || AuthorEditField.getText().equals("") ||
//                PublisherEditField.getText().equals("") ) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText(null);
//            alert.setContentText("Please give all info");
//            alert.showAndWait();
//            return;
//        }
//
//        String newTitle = titleEditField.getText();
//        String bookisbn=ISBNEditField.getText();
//        String newAuthor = AuthorEditField.getText();
//        String newPublisher = PublisherEditField.getText();
//        int numofcopies=Integer.parseInt(numofCopies.getText());
//
//        BookInfo bookinfoobj=new BookInfo(newTitle, bookisbn, newAuthor, newPublisher,numofcopies);
//
//
//
//        //database action
//        DatabaseHandler dbAction=new DatabaseHandler();
//        dbAction.updatebookInfo(bookinfoobj);
//
//
//
//
//        titleEditField.clear();
//        ISBNEditField.clear();
//        AuthorEditField.clear();
//        PublisherEditField.clear();
//
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setHeaderText(null);
//        alert.setContentText("Book info Updated Successfully");
//        alert.showAndWait();
//
//
//    }
//
//
//    @FXML
//    private void deleteButtonAction(MouseEvent event) throws SQLException, ClassNotFoundException {
//        ObservableList<BookInfo> selectedBooks=FXCollections.observableArrayList();
//        selectedBooks=bookInfoTable.getSelectionModel().getSelectedItems();
//        AdminAddBookController.bookInfoList.removeAll(selectedBooks);
//        DatabaseHandler dbAction=new DatabaseHandler();
//        dbAction.deletebooks(selectedBooks);
//    }
//
//
//
//
//}
