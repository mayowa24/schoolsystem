/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package omc.com.student;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP-PC
 */
public class RecordpgController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private JFXComboBox<String> combsearch;

    @FXML
    private TableColumn<RecordModel, String> colname;

    @FXML
    private TableColumn<RecordModel, String> colfilenumber;

    @FXML
    private TableColumn<RecordModel, String> coldate;

    @FXML
    private TableColumn<RecordModel, String> colgender;
    @FXML
    private JFXCheckBox checkmale;

    @FXML
    private JFXCheckBox checkfemale;
    
    ObservableList<RecordModel>rec;
    
      @FXML
    private TableView<RecordModel> studentrec;

    @FXML
    private TableColumn<RecordModel, String> colparent;
    Stage stage;

    @FXML
    void Search(ActionEvent event) {
        rec = FXCollections.observableArrayList();
        
          try {
              Class.forName("com.mysql.jdbc.Driver");
              Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newera","root","");
              String sql = "SELECT * FROM studenttb WHERE currentclass = '"+combsearch.getValue()+"'";
              PreparedStatement prepare = conn.prepareStatement(sql);
              ResultSet rs = prepare.executeQuery();
              while (rs.next()){
                  rec.add(new RecordModel(rs.getString("name"), rs.getString("studentid"), rs.getString("gender"), rs.getString("year"), rs.getString("parentname")));
                 
                 colname.setCellValueFactory(new PropertyValueFactory<>("name"));
                 colfilenumber.setCellValueFactory(new PropertyValueFactory<>("studentid"));
                 colgender.setCellValueFactory(new PropertyValueFactory<>("gender"));
                 coldate.setCellValueFactory(new PropertyValueFactory<>("year"));
                 colparent.setCellValueFactory(new PropertyValueFactory<>("parentname"));
                 
                 studentrec.setItems(rec);
              }
              
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(RecordpgController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (SQLException ex) {
              Logger.getLogger(RecordpgController.class.getName()).log(Level.SEVERE, null, ex);
          }
          if(combsearch.getValue().equals("All")){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newera","root","");
                String sql = "SELECT * FROM studenttb ";
                PreparedStatement prepare = conn.prepareStatement(sql);
                ResultSet rs = prepare.executeQuery();
                while (rs.next()){
                    rec.add(new RecordModel(rs.getString("name"), rs.getString("studentid"), rs.getString("gender"), rs.getString("year"), rs.getString("parentname")));
                    
                    colname.setCellValueFactory(new PropertyValueFactory<>("name"));
                    colfilenumber.setCellValueFactory(new PropertyValueFactory<>("studentid"));
                    colgender.setCellValueFactory(new PropertyValueFactory<>("gender"));
                    coldate.setCellValueFactory(new PropertyValueFactory<>("year"));
                    colparent.setCellValueFactory(new PropertyValueFactory<>("parentname"));
                    
                    studentrec.setItems(rec);
                }   } catch (ClassNotFoundException ex) {
                Logger.getLogger(RecordpgController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(RecordpgController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        ObservableList<String> level = FXCollections.observableArrayList();
        level.add("JSS 1");
        level.add("JSS 2");
        level.add("JSS 3");
        level.add("SS 1");
        level.add("SS 2");
        level.add("SS 3");
        level.add("All");
        combsearch.setItems(level);
        
        
    }    
    public Stage getStage(){
        return stage;
    }
    public void setStage(Stage stage){
        this.stage=stage;
    }
    
}
