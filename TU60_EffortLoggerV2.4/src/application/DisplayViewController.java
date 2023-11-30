/*
 Written by:
 -Tiago Behar
 -Wyatt Mahony
 */
package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DisplayViewController {

    private Scene scene;
    private Stage stage;
    private Login localLogin = new Login();

    @FXML
    private Label username; // same name as in DisplayPage.fxml

    public void setLogin(Login login) {
        // get the login data and initialize local variables
        username.setText("Name: " + login.getName());
        localLogin.setName(login.getName());
        localLogin.setPassword(login.getPassword());
        // copy data from the user into localLogin data variable
        for (int i = 0; i < login.getData().size(); i++) {
            localLogin.addData(login.getData().get(i));
        }
    }

    public void effortConsole(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EffortConsole.fxml"));

        // change the scene
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Effort Console");

        // pass over the login data
        EffortConsoleViewController control = fxmlLoader.getController();
        control.setLogin(localLogin);
        stage.setScene(scene);
        stage.show();
    }

    public void defectConsole(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DefectConsole.fxml"));

        // change the scene
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Defect Console");

        // pass over the login data
        DefectConsoleViewController control = fxmlLoader.getController();
        control.setLogin(localLogin);
        stage.setScene(scene);
        stage.show();
    }

    public void launchPlanningPoker(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlanningPoker.fxml"));

        // change the scene
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("PlanningPoker");

        // pass over the login data
        PlanningPokerViewController control = fxmlLoader.getController();
        control.setLogin(localLogin);
        stage.setScene(scene);
        stage.show();
    }
    
    public void reportConsole(ActionEvent event) throws IOException  //Report Console button in Display Page
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReportConsole.fxml"));
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 900, 600);
		stage.setTitle("Report Console");
		
		ReportViewController control = fxmlLoader.getController();
		control.setLogin(localLogin);
		stage.setScene(scene);
		stage.show();
		
		
	}
    
    public void editorConsole(ActionEvent event) throws IOException  //Effort Editor button in Display Page
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EffortEditor.fxml"));
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 900, 600);
		stage.setTitle("Effort Editor");
		
		EffortEditorViewController control = fxmlLoader.getController();
		control.setLogin(localLogin);
		stage.setScene(scene);
		stage.show();
		
		
	}
}
