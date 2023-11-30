/*
 Written by:
 -Tiago Behar
 -...
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PlanningPokerViewController implements Initializable{
	private Scene scene;
	private Stage stage;
	private Login localLogin = new Login();
	private String selectedListItem;
	
	ObservableList<String> oList = FXCollections.observableArrayList(); //This stores the data added to planning poker
	
	ObservableList<String> oDataList = FXCollections.observableArrayList(); //This stores data that the user will select to add to planning poker
	
	@FXML
	private ListView<String> planningPokerListView;
	
	@FXML
	private ComboBox<String> comboBox;
	
	@FXML
	private TextField minutesTextField;
	
	@FXML
	private TextField secondsTextField;
	
	@FXML
	private TextArea descriptionTextArea;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		planningPokerListView.setItems(oList);
		comboBox.setItems(oDataList);
		minutesTextField.setText(null);
		secondsTextField.setText(null);
		descriptionTextArea.setText(null);
		
		//This listener gets the minutes and seconds, eventually the description of the effort selected in the listView
		planningPokerListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				int breakIndex = -1;
				selectedListItem = planningPokerListView.getSelectionModel().getSelectedItem();
				if(selectedListItem != null) {
					//System.out.println("data is valid");
					for(int i = 0; i < localLogin.getData().size(); i++) {
						if(selectedListItem.equals(localLogin.getData().get(i).getProject() + ", " + localLogin.getData().get(i).getStep() + ", " + localLogin.getData().get(i).getCategory() + ", " + localLogin.getData().get(i).getDeliverable())) {
							//System.out.println("found match at index: " + i);
							breakIndex = i;
							break;
						}
					}
					if(breakIndex >= 0) {
						//System.out.println("setting text fields");
						minutesTextField.setText(localLogin.getData().get(breakIndex).getMinutes() + "");
						secondsTextField.setText(localLogin.getData().get(breakIndex).getSeconds() + "");
						descriptionTextArea.setText(localLogin.getData().get(breakIndex).getDescription());
					}
				}
				
			}
		});
		
		
	}
	
	@FXML
	void getComboBoxInfo(ActionEvent event) { //add the selected item from the comboBox to the listView
		String data = comboBox.getValue();
		if(data == null) {
			return;
		}
		for(int i = 0; i < planningPokerListView.getItems().size(); i++) {
			if(data.equals(planningPokerListView.getItems().get(i))) {
				return; //don't allow duplicate items
			}
		}
		planningPokerListView.getItems().add(comboBox.getValue());
	}
	
	@FXML
	void removeFromList(ActionEvent event) { //remove the selected item from the listView
		final int selectedElementIndex = planningPokerListView.getSelectionModel().getSelectedIndex();
		if(selectedElementIndex >= 0) {
			planningPokerListView.getItems().remove(selectedElementIndex);
		}
	}
	
	@FXML 
	void addAllComboBoxItems(ActionEvent event) { //add all items from the comboBox to the listView
		String data;
		int breakIndex;
		for(int j = 0; j < comboBox.getItems().size(); j++) { //check that each item is not yet in the list view
			breakIndex = 0;
			data = comboBox.getItems().get(j);
			for(int i = 0; i < planningPokerListView.getItems().size(); i++) {
				if(data.equals(planningPokerListView.getItems().get(i))) {
					breakIndex = i;
					break; //don't allow duplicate items
				}
			}
			if(planningPokerListView.getItems().size() == 0) { // if the list is empty, we do not need to perform check
				planningPokerListView.getItems().add(data);
			} else if( !data.equals(planningPokerListView.getItems().get(breakIndex))) { //if it isn't we need to check that the item at breakIndex is not the same as the data we are trying to add
				planningPokerListView.getItems().add(data);
			}
		}
	}
	
	@FXML
	void removeAllFromList(ActionEvent event) {
		planningPokerListView.getItems().clear();
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
		//copy data from user into observable list variable with format: project, step, category, deliverable
		for(int i = 0; i < localLogin.getData().size(); i++) {
			oDataList.add(localLogin.getData().get(i).getProject() + ", " + localLogin.getData().get(i).getStep() + ", " + localLogin.getData().get(i).getCategory() + ", " + localLogin.getData().get(i).getDeliverable());
		}

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
