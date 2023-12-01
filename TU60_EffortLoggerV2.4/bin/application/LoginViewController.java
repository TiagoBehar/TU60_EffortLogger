/*
 Written by:
 -Tiago Behar
 Changes added by;
 -Makaylah Garcia
 -...
 */

package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Node;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class LoginViewController {
	private Scene scene;
	private Stage stage;
	private Login login = new Login();
	private AuthorizedLogins loginList = new AuthorizedLogins();
	
	
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	
	
	
	public void submitLogin(ActionEvent event) throws IOException
	{
		String username = usernameField.getText();
		String password = passwordField.getText();
		
		if(username.isEmpty() || password.isEmpty()) { //validation to check user input
			//send alert to user
			Alert emptyFieldsAlert = new Alert(AlertType.ERROR);
	        emptyFieldsAlert.setHeaderText("--ERROR: Empty Field(s)--");
	        emptyFieldsAlert.setContentText("Enter both a username and password.");
	        emptyFieldsAlert.showAndWait();
	        return;
		}
		
		loginList.testLogins(); //initialize test data
		
		login.setName(username);//get name from text field
		login.setPassword(password);//get password from password field
		int valid = loginList.checkLogin(login); //returns the index of the login information
	
		if(loginList.checkLogin(login) >= 0) {//if the login is valid
			for(int i = 0; i < loginList.getLogin(valid).getData().size(); i++) { //load data about the user into the login
				//System.out.println("adding: " + loginList.getLogin(valid).getData().get(i) + " to local list");
				login.addData(loginList.getLogin(valid).getData().get(i));
			}
			//get the next stage
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DisplayPage.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(fxmlLoader.load(), 900, 600);
			stage.setTitle("Home");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//pass the login data and change the stage
			DisplayViewController control = fxmlLoader.getController();
			control.setLogin(login);
			stage.setScene(scene);
			stage.show();
		}
		else { //if the login is not valid give pop up alert
			Alert incorrectUorP = new Alert(AlertType.ERROR);
			incorrectUorP.setHeaderText("Incorrect Username or Password");
			incorrectUorP.setContentText("It appears that the username or password that you entered is not in our database, make sure you have the right username and password");
			incorrectUorP.showAndWait();
		}
	}

}
