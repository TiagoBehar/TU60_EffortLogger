/*
 Written by:
 -Tiago Behar
 -...
 */

package application;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EffortEditorViewController {
	private Scene scene;
	private Stage stage;
	private Login localLogin = new Login();
	private String selectedListItem;
	
	ObservableList<String> oProjectList = FXCollections.observableArrayList();
	
	ObservableList<String> oEntryList = FXCollections.observableArrayList();
	
	ObservableList<String> oStepList = FXCollections.observableArrayList();
	
	ObservableList<String> oCategoryList = FXCollections.observableArrayList();
	
	ObservableList<String> oDeliverableList = FXCollections.observableArrayList();
	
	@FXML
    private ComboBox<String> projectComboBox;
	
	@FXML
    private ComboBox<String> effortComboBox;
	
	@FXML
    private ComboBox<String> stepComboBox;
	
	@FXML
    private ComboBox<String> categoryComboBox;
	
	@FXML
    private ComboBox<String> deliverableComboBox;
	
	@FXML
    private Button updateButton;
	
	@FXML
    private Button deleteButton;
	
	@FXML
	private TextField dateField;
	
	@FXML
	private TextField minutesField;
	
	@FXML
	private TextField secondsField;
	
	public void initialize() {
		projectComboBox.setItems(oProjectList);
		effortComboBox.setItems(oEntryList);
		stepComboBox.setItems(oStepList);
		categoryComboBox.setItems(oCategoryList);
		deliverableComboBox.setItems(oDeliverableList);
		
		//add efforts from selected project to the effort list when a project is selected
		projectComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				selectedListItem = projectComboBox.getSelectionModel().getSelectedItem();
				if(selectedListItem != null) {
					oEntryList.clear();
					for(int i = 0; i < localLogin.getData().size(); i++) {
						if(selectedListItem.equals(localLogin.getData().get(i).getProject())) {
							oEntryList.add(localLogin.getData().get(i).getProject() + ", " + localLogin.getData().get(i).getStep() + ", " + localLogin.getData().get(i).getCategory() + ", " + localLogin.getData().get(i).getDeliverable());
						}
					}
				}
				
			}
		});
		
		//set the text of the comboBoxes for step, category, and deliverable to the current values of the selected effort
		effortComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				selectedListItem = effortComboBox.getSelectionModel().getSelectedItem();
				if(selectedListItem != null) { //make sure we have an item selected
					for(int i = 0; i < localLogin.getData().size(); i++) {
						if(selectedListItem.equals(localLogin.getData().get(i).getProject() + ", " + localLogin.getData().get(i).getStep() + ", " + localLogin.getData().get(i).getCategory() + ", " + localLogin.getData().get(i).getDeliverable())) {
							stepComboBox.setPromptText(localLogin.getData().get(i).getStep());
							categoryComboBox.setPromptText(localLogin.getData().get(i).getCategory());
							deliverableComboBox.setPromptText(localLogin.getData().get(i).getDeliverable());
							dateField.setText(localLogin.getData().get(i).getFormattedDate());
							minutesField.setText(localLogin.getData().get(i).getMinutes() + "");
							secondsField.setText(localLogin.getData().get(i).getSeconds() + "");
						}
					}
				}
			}
		});
		
	}
	
	public void updateEntry(ActionEvent event)
	{
		selectedListItem = effortComboBox.getSelectionModel().getSelectedItem();
		String newMinutesString = minutesField.getText();
		String newSecondsString = secondsField.getText();
		String newDate = dateField.getText();
		
		//get the selected item from each step, category and deliverable. If none is selected, get the promt text which will be the original entry data if a project was selected
		String newStep = stepComboBox.getSelectionModel().getSelectedItem();
		if(newStep == null) {
			newStep = stepComboBox.getPromptText();
		}
		String newCategory = categoryComboBox.getSelectionModel().getSelectedItem();
		if(newCategory == null) {
			newCategory = categoryComboBox.getPromptText();
		}
		String newDeliverable = deliverableComboBox.getSelectionModel().getSelectedItem();
		if(newDeliverable == null) {
			newDeliverable = deliverableComboBox.getPromptText();
		}
		//make sure none of the fields are null so that we do not update the data to have incomplete fields
		if(selectedListItem != null && newMinutesString != null && newSecondsString != null && newDate != null && newStep != null && newCategory != null && newDeliverable != null) {
			for(int i = 0; i < localLogin.getData().size(); i++) { //find the original data in the list
				if(selectedListItem.equals(localLogin.getData().get(i).getProject() + ", " + localLogin.getData().get(i).getStep() + ", " + localLogin.getData().get(i).getCategory() + ", " + localLogin.getData().get(i).getDeliverable())) 
				{
					localLogin.deleteData(i); //remove the old data from the list
					
					int newMinutes = Integer.parseInt(newMinutesString);
					int newSeconds = Integer.parseInt(newSecondsString);
					String selectedProject = projectComboBox.getSelectionModel().getSelectedItem();
					
					System.out.println("Updating data to: " + selectedProject + " " + newStep + " " + newCategory + " " + newDeliverable + " " + "(description)" + " Date: " + newDate + " Minutes: " + newMinutes + " Seconds: " + newSeconds);
					
					//add the updated data back into the list
					UserData newData = new UserData(selectedProject, newStep, newCategory, newDeliverable, "", newDate, newMinutes, newSeconds);
					
					localLogin.addData(newData);
					
					//refresh the effort list to show updated data
					
					if(selectedProject != null) {
						oEntryList.clear();
						for(int j = 0; j < localLogin.getData().size(); j++) {
							if(selectedProject.equals(localLogin.getData().get(j).getProject())) {
								oEntryList.add(localLogin.getData().get(j).getProject() + ", " + localLogin.getData().get(j).getStep() + ", " + localLogin.getData().get(j).getCategory() + ", " + localLogin.getData().get(j).getDeliverable());
							}
						}
					}
					stepComboBox.getSelectionModel().clearSelection();
					categoryComboBox.getSelectionModel().clearSelection();
					deliverableComboBox.getSelectionModel().clearSelection();
					
					stepComboBox.setPromptText(null);
					categoryComboBox.setPromptText(null);
					deliverableComboBox.setPromptText(null);
					dateField.setText(null);
					minutesField.setText(null);
					secondsField.setText(null);
					break;
				}
			}
		}
	}
	
	public void deleteEntry(ActionEvent event) //delete button which removes the effort entry from the users data
	{
		selectedListItem = effortComboBox.getSelectionModel().getSelectedItem();
		if(selectedListItem != null) {
			for(int i = 0; i < localLogin.getData().size(); i++) {
				if(selectedListItem.equals(localLogin.getData().get(i).getProject() + ", " + localLogin.getData().get(i).getStep() + ", " + localLogin.getData().get(i).getCategory() + ", " + localLogin.getData().get(i).getDeliverable())) {
					localLogin.deleteData(i);
					stepComboBox.setPromptText(null);
					categoryComboBox.setPromptText(null);
					deliverableComboBox.setPromptText(null);
					dateField.setText(null);
					minutesField.setText(null);
					secondsField.setText(null);
					
					effortComboBox.setPromptText(null);
					oEntryList.remove(selectedListItem);
				}
			}
		}
	}
	
	public void setLogin(Login login)
	{
		//get the login data and initialize local variables
		localLogin.setName(login.getName());
		localLogin.setPassword(login.getPassword());
		//copy data from user into localLogin data variable
		for(int i = 0; i < login.getData().size(); i++) {
			localLogin.addData(login.getData().get(i));
		}
		//copy data from user into project list variable avoiding duplicates
		boolean alreadyInList = false;
		for(int i = 0; i < localLogin.getData().size(); i++) {
			alreadyInList = false;
			for(int j = 0; j < oProjectList.size(); j++) {
				if(oProjectList.get(j).equals(localLogin.getData().get(i).getProject())) {
					alreadyInList = true;
				}
			}
			if(!alreadyInList) {
				oProjectList.add(localLogin.getData().get(i).getProject());
			}
		}
		
		stepComboBox.getItems().addAll("Step1", "Step2", "Step3");
        categoryComboBox.getItems().addAll("Category1", "Category2", "Category3");
        deliverableComboBox.getItems().addAll("Deliverable1", "Deliverable2", "Deliverable3");
	}
	
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
