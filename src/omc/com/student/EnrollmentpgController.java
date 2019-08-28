/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package omc.com.student;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import omc.com.imageUtil;
//import com.mysql.jdbc.Connection;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
//import java.sql.DriverManager;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author HP-PC
 */

public class EnrollmentpgController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private JFXTextField txtstudentname;

    @FXML
    private JFXComboBox<String> combgender;

     @FXML
    private DatePicker doadmision;


    @FXML
    private ImageView imgpassport;

    @FXML
    private JFXTextField txtlga;

    @FXML
    private JFXTextField txtorigin;

    @FXML
    private DatePicker dob;

    @FXML
    private JFXTextField txtformal;

    @FXML
    private JFXComboBox<String> combclass;

    @FXML
    private JFXTextField txtparent;

    @FXML
    private JFXTextField txtaddress;

    @FXML
    private JFXTextField txtphone;
    
     @FXML
    private JFXComboBox<String> combday;

    @FXML
    private JFXComboBox<String> combmonth;

    @FXML
    private JFXComboBox<String> combyear;
    
    @FXML
    private JFXTextField txtothernames;
    
    private File file;
    private BufferedImage image;
    
    Stage stage;

    @FXML
    void cancel(ActionEvent event) {

    }
     @FXML
    void UploadPassport(ActionEvent event) throws IOException {
        
        
//       
//            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg");
//            FileChooser fileChooser = new FileChooser();
//            fileChooser
//                    .getExtensionFilters().add(imageFilter);
//            file = fileChooser.showOpenDialog(getStage());
//            if (Objects.nonNull(file)) {
//                
//                    image = ImageIO.read(file);
//                    WritableImage toFXImage = SwingFXUtils.toFXImage(image, null);
//                    imgpassport.setImage(toFXImage);
//               
//            }
//       
//        
//        
//        
////        FileChooser filechooser = new FileChooser();
////        File file = filechooser.showOpenDialog(null);
////        
////        try {
////            BufferedImage bi = ImageIO.read(file);
////            WritableImage toFXImage = SwingFXUtils.toFXImage(bi, null);
////            imgpassport.setImage(toFXImage);
////        } catch (IOException ex) {
////            Logger.getLogger(EnrollmentpgController.class.getName()).log(Level.SEVERE, null, ex);
////        }

    }


    @FXML
    void register(ActionEvent event) {
        Random random = new Random();
        int nextInt = random.nextInt(1000);
        String key = "NESS/"+nextInt;
        
        String admissiondate = combday.getValue()+" "+combmonth.getValue();
        String studentname = txtstudentname.getText()+" "+txtothernames.getText();
//        System.out.println(key);

        
        if(txtstudentname.getText().equals("")||txtorigin.getText().equals("")||txtlga.getText().equals(null)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empty Field");
            alert.setContentText("you must fill all fields");
            alert.showAndWait();
        }
        
         
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Are You Sure");
        alert.setContentText("Are You sure, you want to submit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            
          
         try {
             Class.forName("com.mysql.jdbc.Driver");
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/newera","root","");
             String sqll = "SELECT studentid FROM studenttb WHERE studentid='"+key+"'";
             PreparedStatement pre = con.prepareStatement(sqll);
             ResultSet rs = pre.executeQuery();
             while (rs.next()){
                nextInt = random.nextInt(1000);
                key = "NESS/"+nextInt;
                
                
             }
            try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newera","root","");
            String sql = "INSERT INTO studenttb(studentid,name,gender,dob,lga,state,formal,currentclass,admissiondate,parentname,residence,parentno,year)VALUES('"+key+"','"+studentname+"','"+
                    combgender.getValue()+"','"+dob.getValue()+"','"+txtlga.getText()+"','"+txtorigin.getText()+"','"+txtformal.getText()+"','"+combclass.getValue()+"','"+admissiondate+"','"+txtparent.getText()+"','"+txtaddress.getText()+"','"+txtphone.getText()+"','"+combyear.getValue()+"')";
            PreparedStatement prepare = conn.prepareStatement(sql);
            prepare.execute();
            
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Registration Number");
            alert2.setContentText("this student Reg. Number is "+key);
            alert2.showAndWait();
            
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Registration");
            alert1.setContentText("this student has been registered successfully");
            alert1.showAndWait();
            
            txtstudentname.setText("");
            txtothernames.setText("");
            combgender.setValue("");
            txtlga.setText("");
            dob.setValue(null);
            txtorigin.setText("");
            txtformal.setText("");
            combclass.setValue(null);
            
            txtparent.setText("");
            txtaddress.setText("");
            txtphone.setText("");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EnrollmentpgController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EnrollmentpgController.class.getName()).log(Level.SEVERE, null, ex);
        }
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(EnrollmentpgController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(EnrollmentpgController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
                }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> gender = FXCollections.observableArrayList();
        gender.add("Male");
        gender.add("Female");
        combgender.setItems(gender);
                
        ObservableList<String> cclass = FXCollections.observableArrayList();
        cclass.add("JSS 1");
        cclass.add("JSS 2");
        cclass.add("JSS 3");
        cclass.add("SS 1");
        cclass.add("SS 2");
        cclass.add("SS 3");
        combclass.setItems(cclass);
        
        ObservableList<String> day = FXCollections.observableArrayList();
        day.add("1");
        day.add("2");
        day.add("3");
        day.add("4");
        day.add("5");
        day.add("6");
        day.add("7");
        day.add("8");
        day.add("9");
        day.add("10");
        day.add("11");
        day.add("12");
        day.add("13");
        day.add("14");
        day.add("15");
        day.add("16");
        day.add("17");
        day.add("18");
        day.add("19");
        day.add("20");
        day.add("21");
        day.add("22");
        day.add("23");
        day.add("24");
        day.add("25");
        day.add("26");
        day.add("27");
        day.add("28");
        day.add("29");
        day.add("30");
        day.add("31");
        combday.setItems(day);
        
        ObservableList<String> month = FXCollections.observableArrayList();
        month.add("January");
        month.add("February");
        month.add("March");
        month.add("April");
        month.add("May");
        month.add("June");
        month.add("July");
        month.add("August");
        month.add("September");
        month.add("October");
        month.add("November");
        month.add("December");
        combmonth.setItems(month);
        
        ObservableList<String> year = FXCollections.observableArrayList();
        year.add("2010");
        year.add("2011");
        year.add("2012");
        year.add("2013");
        year.add("2014");
        year.add("2015");
        year.add("2016");
        year.add("2017");
        year.add("2018");
        year.add("2019");
        year.add("2020");
        year.add("2021");
        year.add("2022");
        year.add("2023");
        year.add("2024");
        year.add("2025");
        year.add("2026");
        year.add("2027");
        year.add("2028");
        year.add("2029");
        year.add("2030");
        year.add("2031");
        combyear.setItems(year);
    }    
    public Stage getStage(){
        return stage;
    }
    public void setStage(Stage stage){
        this.stage=stage;
    }
}
