/*
 Author: Wyatt Mahony
 Date: Novermber 12, 2023
 */

package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

import javafx.scene.text.Text;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class EffortConsoleViewController {
	
	
	//declarations
	private Scene scene;
	private Stage stage;
	private Login localLogin = new Login();
	private LocalDateTime startTime;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private int reportCounter = 1;

    @FXML
    private AnchorPane statusBox;

    @FXML
    private Text statusText;

    @FXML
    private Button startButton;

    @FXML
    private ComboBox<String> projectComboBox;

    @FXML
    private ComboBox<String> lifeCycleStepComboBox;

    @FXML
    private ComboBox<String> effortCategoryComboBox;

    @FXML
    private ComboBox<String> deliverableComboBox;

    @FXML
    private Button stopButton;
    
    
    
    public void setLogin(Login login)
	{
		//get the login data and initialize local variables
		localLogin.setName(login.getName());
		localLogin.setPassword(login.getPassword());
		//copy data from user into localLogin data variable
		for(int i = 0; i < login.getData().size(); i++) {
			localLogin.addData(login.getData().get(i));
		}
	}

    public void initialize() {
       
    	// sets status box to state 1 where activity clock is not running
        setStatusBox(false);
        
        //initializes options in drop down menu, can add access to database later or
        //separate module to allow managers to set these
         projectComboBox.getItems().addAll("Project1", "Project2", "Project3");
         lifeCycleStepComboBox.getItems().addAll("Step1", "Step2", "Step3");
         effortCategoryComboBox.getItems().addAll("Category1", "Category2", "Category3");
         deliverableComboBox.getItems().addAll("Deliverable1", "Deliverable2", "Deliverable3");
    }
    
    
    //when start activity button is pressed, gets time and moves status box to state2 as clock runs
    @FXML
    private void startActivity(ActionEvent event) {
       
    	setStatusBox(true);
        startTime = LocalDateTime.now();
    }

    //when stop activity button is pressed, status box set back to state 1, report generated, and 
    //report counter increased
    @FXML
    private void stopActivity(ActionEvent event) {
        
    	setStatusBox(false);
        LocalDateTime stopTime = LocalDateTime.now();
        generateAndDisplayReport(startTime, stopTime);
        reportCounter++;
    }
    
    //formats the report by number, date, time and project details
    private void generateAndDisplayReport(LocalDateTime startTime, LocalDateTime stopTime) {
        
        long minutes = java.time.Duration.between(startTime, stopTime).toMinutesPart();
        long seconds = java.time.Duration.between(startTime, stopTime).toSecondsPart();

        
        String formattedDate = startTime.format(dateFormatter);

        // Format the report string
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedStartTime = startTime.format(timeFormatter);
        String formattedStopTime = stopTime.format(timeFormatter);
        String reportContent = String.format("Report #%d. %s (%s - %s) %s; %s; %s; %s",
                reportCounter, formattedDate, formattedStartTime, formattedStopTime,
                projectComboBox.getValue(), lifeCycleStepComboBox.getValue(),
                effortCategoryComboBox.getValue(), deliverableComboBox.getValue());


        // print the report content
        // basically just showing that data is collected, code can be adjusted later  so data can be used
        // in other modules
        System.out.println("Generated Report:");
        System.out.println(reportContent);

        //add the report to the users data
        //constructor format: String project, String step, String category, String deliverable, String description(PLACEHOLDER: please replace with description text inputed by user), String formattedDate, long minutes, long seconds
        UserData reportData = new UserData(projectComboBox.getValue(), lifeCycleStepComboBox.getValue(), effortCategoryComboBox.getValue(), deliverableComboBox.getValue(), "This is a placeholder description, please add description text in effort console", formattedDate, minutes, seconds);
        localLogin.addData(reportData);
    }

    //changes the status box color and text based on activity clock running or not
    private void setStatusBox(boolean running) {
        if (running) {
            // Green color for the HBox within statusBox
            statusBox.getChildren().get(1).setStyle("-fx-background-color: #2ecc71;");
            statusText.setText("Clock Running");
        } else {
            // Red color for the HBox within statusBox
            statusBox.getChildren().get(1).setStyle("-fx-background-color: #e74c3c;");
            statusText.setText("Clock Stopped");
        }
    }
	
    
    // home button functionality to take user back to effort logger display page
	public void displayView(ActionEvent event) throws IOException //go back to the display view (Home button)
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DisplayPage.fxml"));
		
		//change the scene
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 900, 600);
		stage.setTitle("Home");
		
		//pass over the login data
		DisplayViewController control = fxmlLoader.getController();
		control.setLogin(localLogin);
		stage.setScene(scene);
		stage.show();
		
		
	}
}
