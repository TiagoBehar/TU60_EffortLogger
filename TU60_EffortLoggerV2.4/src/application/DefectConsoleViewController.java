package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class DefectConsoleViewController implements Initializable {
	
	private Scene scene;
	private Stage stage;
	private Login localLogin = new Login();
	
	//GUI Table
    @FXML
    private TableView<defects> tableView;

    //GUI Columns
    @FXML
	private TableColumn<defects, String> defectColumn;
	@FXML
	private TableColumn<defects, String> projectColumn;
	@FXML
	private TableColumn<defects, String> phaseColumn;
	@FXML
	private TableColumn<defects, String> fixedColumn;
    @FXML
    private TableColumn<defects, Integer> effortColumn;

    //Text input
    @FXML
    private TextField defectIn;
    @FXML
    private TextField projectIn;
    @FXML
    private TextField phaseIn;
    @FXML
    private TextField fixedIn;
    @FXML
    private TextField effortIn;
 
	@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
		defectColumn.setCellValueFactory(new PropertyValueFactory<defects, String>("defect"));   
	    projectColumn.setCellValueFactory(new PropertyValueFactory<defects, String>("project"));
	    phaseColumn.setCellValueFactory(new PropertyValueFactory<defects, String>("phase"));
	    fixedColumn.setCellValueFactory(new PropertyValueFactory<defects, String>("fixed"));
	    effortColumn.setCellValueFactory(new PropertyValueFactory<defects, Integer>("effort"));
    }
	
	//Submit button for table
    @FXML
    void submit(ActionEvent event) {
    	String defectName = defectIn.getText();
    	String projectName = projectIn.getText();
    	String phaseName = phaseIn.getText();
        String fixedStatus = fixedIn.getText();
        
        int effortValue;//int verification
        try {
              effortValue = Integer.parseInt(effortIn.getText());
        } catch (NumberFormatException e) {
             System.err.println("Error: Effort should be a valid integer.");
             return; // Exit the method to prevent creating a defect with invalid data
        }
        // If all validations pass, create the defects object
        defects defect = new defects(defectName, projectName, phaseName, fixedStatus, effortValue);
        ObservableList<defects> currentDefects = tableView.getItems();
        currentDefects.add(defect);
        tableView.setItems(currentDefects);

        // Clear input fields
        defectIn.clear();
        projectIn.clear();
        phaseIn.clear();
        fixedIn.clear();
        effortIn.clear();  
    }
    //remove a defect button
    @FXML
    void removeDefect(ActionEvent event) {
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        tableView.getItems().remove(selectedID);
    }
	
    
	public void setLogin(Login login)
	{
		//get the login data and initialize local variables
		localLogin.setName(login.getName());
		localLogin.setPassword(login.getPassword());
		//copy data from user into localLogin data variable
		for(int i = 0; i < login.getData().size(); i++) {
			//System.out.println("adding: " + login.getData().get(i) + " to local list");
			localLogin.addData(login.getData().get(i));
		}
	}
	
	public void displayView(ActionEvent event) throws IOException //go back to the display view (Home button)
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DisplayPage.fxml"));
		
		//change the scene
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 900, 600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setTitle("Home");
		
		//pass over the login data
		DisplayViewController control = fxmlLoader.getController();
		control.setLogin(localLogin);
		stage.setScene(scene);
		stage.show();
		
	}
}