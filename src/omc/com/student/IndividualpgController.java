/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package omc.com.student;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
//import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import omc.com.imageUtil;

/**
 * FXML Controller class
 *
 * @author HP-PC
 */
public class IndividualpgController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private JFXTextField txtfileno;

    @FXML
    private JFXTextField txtname;

    @FXML
    private JFXTextField txtgender;

    @FXML
    private JFXTextField txtadmissiondate;

    @FXML
    private JFXTextField txtparent;

    @FXML
    private JFXTextField txtaddress;

    @FXML
    private JFXTextField txtphoneno;

    @FXML
    private JFXTextField txtdob;
     @FXML
    private ImageView imgpasport;

    @FXML
    private JFXTextField txtorigin;
    Stage stage;
//    private Image image;
    private File file;
    

    @FXML
    void print(ActionEvent event) throws FileNotFoundException {
            Document document = new Document();
            
            
            String studentid = txtfileno.getText();
            String studentname = txtname.getText();
            String gender = txtgender.getText();
          if(txtfileno.getText().equals("")){
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Empty Files");
              alert.setContentText("No Record to Print ");
              alert.showAndWait();
          }
          else
          try {
              
              PdfWriter output = PdfWriter.getInstance(document,new FileOutputStream(txtname.getText()+".pdf"));
              
              document.open();
              document.add(new Paragraph(new Date().toString()));
              Image image =Image.getInstance("era.jpg");
//              document.add(new Paragraph("Image"));
              document.add(image);
              document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------"));
              document.add(new Paragraph("." ,FontFactory.getFont(FontFactory.TIMES_BOLD,11,BaseColor.WHITE)));
              document.add(new Paragraph("STUDENT BIODATA",FontFactory.getFont(FontFactory.TIMES_BOLD,16,Font.UNDERLINE)));
              document.add(new Paragraph("STUDENT IDENTITY NUMBER:  "+studentid));
              document.add(new Paragraph("STUDENT'S NAME:  "+studentname));
              document.add(new Paragraph("GENDER:  "+gender));
              document.add(new Paragraph("Date of Addmission:  "+ txtadmissiondate.getText()));
              document.add(new Paragraph("State of Origin:  "+ txtorigin.getText()));
              document.add(new Paragraph("Date of Birth:  "+ txtdob.getText()));
              document.add(new Paragraph("." ,FontFactory.getFont(FontFactory.TIMES_BOLD,11,BaseColor.WHITE)));
              document.add(new Paragraph("PARENT/GUARDIAN INFORMATION",FontFactory.getFont(FontFactory.TIMES_BOLD,16,Font.UNDERLINE)));
              document.add(new Paragraph("Name:  "+ txtparent.getText()));
              document.add(new Paragraph("Addess:  "+ txtaddress.getText()));
              document.add(new Paragraph("Phone Number:  "+ txtphoneno.getText()));
              document.add(new Paragraph("." ,FontFactory.getFont(FontFactory.TIMES_BOLD,11,BaseColor.WHITE)));
              document.add(new Paragraph(".",FontFactory.getFont(FontFactory.TIMES_BOLD,11,BaseColor.WHITE)));
              document.add(new Paragraph(".",FontFactory.getFont(FontFactory.TIMES_BOLD,11,BaseColor.WHITE)));
              document.add(new Paragraph("---------------------------                                                   "+"-------------------------------------"));
              document.add(new Paragraph("Student's Signature                                                   "+"Parent/Guardian Signature"));
              document.add(new Paragraph(".",FontFactory.getFont(FontFactory.TIMES_BOLD,11,BaseColor.WHITE)));
              document.add(new Paragraph(".",FontFactory.getFont(FontFactory.TIMES_BOLD,11,BaseColor.WHITE)));
              document.add(new Paragraph(".",FontFactory.getFont(FontFactory.TIMES_BOLD,11,BaseColor.WHITE)));
              document.add(new Paragraph(".",FontFactory.getFont(FontFactory.TIMES_BOLD,11,BaseColor.WHITE)));
              document.add(new Paragraph("------------------------------------------------                   "));
              document.add(new Paragraph("   Principal's Signature & Date                         "));
              document.close();
              
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Report");
              alert.setContentText("the Document as been saved as pdf file successfully");
              alert.showAndWait();
              
              txtname.setText("");
              txtfileno.setText("");
              txtgender.setText("");
              txtadmissiondate.setText("");
              txtorigin.setText("");
              txtdob.setText("");
              txtparent.setText("");
              txtaddress.setText("");
              txtphoneno.setText("");
          } catch (DocumentException ex) {
              Logger.getLogger(IndividualpgController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IOException ex) {
              Logger.getLogger(IndividualpgController.class.getName()).log(Level.SEVERE, null, ex);
          }
         
        

    }

    @FXML
    void search(ActionEvent event) {
        
        if(txtfileno.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empty Field");
            alert.setContentText("Student Id is required");
            alert.showAndWait();
        }
        else
          try {
              Class.forName("com.mysql.jdbc.Driver");
              Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newera","root","");
              String sql = "SELECT * FROM studenttb WHERE studentid='"+txtfileno.getText()+"'";
              PreparedStatement prepare = conn.prepareStatement(sql);
              ResultSet rs = prepare.executeQuery();
              if(rs.next()){
                    
                     txtname.setText(rs.getString("name"));
                  txtgender.setText(rs.getString("gender"));
                  txtdob.setText(rs.getString("dob"));
                  txtadmissiondate.setText(rs.getString("admissiondate")+", "+rs.getString("year"));
                  txtparent.setText(rs.getString("parentname"));
                  txtaddress.setText(rs.getString("residence"));
                  txtphoneno.setText(rs.getString("parentno"));
                  txtorigin.setText(rs.getString("state"));
                            
                           
                 
//                  OutputStream os = new FileOutputStream(new File("img"));
//                  byte [] content = new byte[2048];
//                  int size=0;
//                  while ((size=is.read(content))!=-1){
//                      os.write(content,0,size);
//                  }
//                  os.close();
//                  is.close();

//                  javafx.scene.image.Image passport = new Image("file:img",imgpasport.getFitWidth(),imgpasport.getFitHeight(),true,true);
//                  imgpasport.setImage(passport);
//                  imgpasport.setPreserveRatio(true);
                  
                 
                  
                  
                
//                  BufferedImage bi = null;
//                  bi =ImageIO.read(is);
//                  WritableImage toFXImage = SwingFXUtils.toFXImage(bi, null);
//                    imgpasport.setImage(toFXImage);
//                  javafx.scene.image.Image passport = new Image("file:img",img pasport.getFitWidth(),imgpasport.getFitHeight(),true,true);
                  

                  
              }
              else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Data Not Found");
            alert.setContentText("No Record Found for this ID");
            alert.showAndWait();    
            
            txtfileno.setText("");
                      }
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(IndividualpgController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (SQLException ex) {
              Logger.getLogger(IndividualpgController.class.getName()).log(Level.SEVERE, null, ex);
//          } catch (FileNotFoundException ex) {
//              Logger.getLogger(IndividualpgController.class.getName()).log(Level.SEVERE, null, ex);
//          } catch (IOException ex) {
//              Logger.getLogger(IndividualpgController.class.getName()).log(Level.SEVERE, null, ex);
//          }

    }     
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public Stage getStage(){
        return stage;
    }
    public void setStage(Stage stage){
        this.stage=stage;
    }
    
}
