/*
Written by: Wyatt Mahony
Date: 10/29/2023
Data anonymization prototype
*/
package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

public class ReportViewController {
	
	private Scene scene;
	private Stage stage;
	private Login localLogin = new Login();
	@FXML
	private TextArea reportTextArea;
	//Employee testEmployee = new Employee("John Doe", "johndoe123", "john.doe@example.com", "1990-01-15", "Engineer");

	
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
	
	//Establishes the page for generating an anonymized report
	public void displayView(ActionEvent event) throws IOException
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DisplayPage.fxml"));
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 900, 600);
		stage.setTitle("Home");
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		DisplayViewController control = fxmlLoader.getController();
		control.setLogin(localLogin);
		stage.setScene(scene);
		stage.show();
		
		
	}
	
	public void generateReport(ActionEvent event) {
	    //Generates the report on event that generate report button is clicked
		reportTextArea.setText("");

		 String reportContent = generateReportContent();

		    // Create a test Employee object
		    Employee testEmployee = new Employee("John Doe", "johndoe", "johndoe@example.com", "01/01/1990", "Engineer");

		    // Redact the employee data from the report content
		   // String redactedReport = redactEmployeeData(reportContent, testEmployee);
		    String redactedReport = redactEmployeeAttributes(reportContent, testEmployee);
		    // Display the redacted report content
	    reportTextArea.setText(redactedReport);

	    System.out.println("Generated Report:");
	    System.out.println(redactedReport);
	}

	private String generateReportContent() {
	    //A test report for redaction testing, end report format will be different as new data is introduced
	   
	    return "This is a sample report content, John Doe, johndoe you can reach me at johndoe@example.com, i was born on 01/01/1990.";
	}
	
	//method to get attributes of employee objects and redact them from text
	//simpler method for testing
	public String redactEmployeeData(String inputText, Employee employee) {
        
       /* String[] employeeAttributes = { "name", "username", "emailAddress", "birthdate" };

        
        String redactedText = new String(inputText);

        
        for (String attribute : employeeAttributes) {
            redactedText = redactedText.replaceAll(attribute, "#####");
        }

        return redactedText;
        */
		String name = employee.getName();
	    String username = employee.getUsername();
	    String emailAddress = employee.getEmailAddress();
	    String birthdate = employee.getBirthdate();

	    // Create a copy of the input text
	    String redactedText = new String(inputText);

	    // Replace each employee attribute with "#####"
	    redactedText = redactedText.replaceAll(name, "#####");
	    redactedText = redactedText.replaceAll(username, "#####");
	    redactedText = redactedText.replaceAll(emailAddress, "#####");
	    redactedText = redactedText.replaceAll(birthdate, "#####");

	    return redactedText;
       
	}
	
	//alternate implementation that grows as employee objects become more complex
	//this will allow the addition of attributes to employee and a more simple
	//method of getting and replacing all personal data in a piece of text.
	//as # of attributes increase this will reduce repetition of 
	//redactedText = redactedText.replaceAll(String, String); 
	public String redactEmployeeAttributes(String inputText, Employee employee) {
	    // Define the attributes to be replaced
	    String[] employeeAttributes = {
	        "name",
	        "username",
	        "emailAddress",
	        "birthdate"
	        // Add more attributes as introduced
	    };

	    String redactedText = inputText;
	    
	    // Replace each instance of personal data with ######
	    for (String attribute : employeeAttributes) {
	        String attributeValue = getAttributeValue(attribute, employee);
	        redactedText = redactedText.replaceAll(attributeValue, "#####");
	    }
	    
	    return redactedText;
	}

	private String getAttributeValue(String attribute, Employee employee) {
	    switch (attribute) {
	        case "name":
	            return employee.getName();
	        case "username":
	            return employee.getUsername();
	        case "emailAddress":
	            return employee.getEmailAddress();
	        case "birthdate":
	            return employee.getBirthdate();
	        // Add more cases for other attributes
	        default:
	            return "#####"; 
	    }
	}

}
