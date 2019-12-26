package gui;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import DataAccess.DatabaseHandler;
import classes.BookInfo;
import classes.IssuedBook;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
//import static libraryassistant.signup.ForgotPassController.userInfoList;

public class ToIssueBookController implements Initializable {

    @FXML
    private Button issuebooks;
    @FXML
    private Button showissuedBooks;
    @FXML
    private TextField ISBN;
    @FXML
    private DatePicker issuedate;
    @FXML
    private DatePicker returndate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void issueAction(MouseEvent event) throws SQLException, ClassNotFoundException {
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydb","root","pass");
        Statement statement=conn.createStatement();

        String query= " SELECT * FROM bookinfo WHERE ISBN LIKE '%"+ISBN.getText()+"%'";
        ResultSet rs=statement.executeQuery(query);

        if(rs.next()){
            String title= rs.getString("Title");
            String isbn=rs.getString("ISBN");
            String author=rs.getString("Author");
            String publisher =rs.getString("Publisher");
            String categories=rs.getString("Categories");
            String subcategories=rs.getString("Subcategories");
            int year=rs.getInt("Year");
            int rating=rs.getInt("Rating");
            int numcopies =rs.getInt("numberOfCopies");

            IssuedBook bookobj=new IssuedBook(title,isbn,author,publisher);
            BookInfo bookinfo=new BookInfo(title,isbn,author,publisher,categories, subcategories, year, rating, numcopies);

            //database action
            DatabaseHandler dbAction=new DatabaseHandler();
            dbAction.addissuedbooks(bookobj);
            DatabaseHandler dbAction2=new DatabaseHandler();
            dbAction2.decrementCopies(bookinfo);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Book issued successfully");

            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error!");
            alert.setHeaderText("Can not issue book");
            alert.setContentText("This ISBN number does not exist");

            alert.showAndWait();
        }
    }

    @FXML
    private void BackAction(MouseEvent event) {
        try {

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MainPanel.fxml")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }


}