package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MyController implements Initializable{
	@FXML
	public Label welcomeLabel;
	@FXML
	public ChoiceBox<String> choicebox;
	@FXML
	public TextField userinput;
	@FXML
	public javafx.scene.control.Button convertbutton;

	private static final String C_TO_F_TEXT = "Celsius to Fahrenheit";
	private static final String F_TO_C_TEXT = "Fahrenheit to Celsius";

	private boolean isC_TO_F = true;
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		choicebox.getItems().add(C_TO_F_TEXT);
		choicebox.getItems().add(F_TO_C_TEXT);

		choicebox.setValue(C_TO_F_TEXT);

		choicebox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if(newValue.equals(C_TO_F_TEXT)){//If user has selected "Celsius to Fahrenheit"
            	isC_TO_F = true;
            } else{                // Else user has selected "Fahrenheit to Celsius"
            	isC_TO_F = false;
            }
		});

		convertbutton.setOnAction(event -> {
			convert();
		});

	}

	private void convert() {

		String input = userinput.getText(); //23 => "23.4"
		float enteredTemperature = 0.0f;
		try{
			enteredTemperature = Float.parseFloat(input); //23.4f
		}catch(Exception exception){
            warnUser();
            return;
            // no code executed...
		}

		float newTemp = 0.0f;
		if(isC_TO_F){
            newTemp = ( enteredTemperature * 9 / 5) + 32;
		}else{
			newTemp = (enteredTemperature -32) * 5 / 9;
		}
		display(newTemp);
	}

	private void warnUser() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Occurred");
		alert.setHeaderText("Invalid Temperature Entered");
		alert.setContentText("Please enter a valid temperature");
		alert.show();

	}

	private void display(float newTemp) {

		String unit = isC_TO_F? " F" : " C";
		System.out.println("The new temperature is: " + newTemp + unit);

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Result");
		alert.setContentText("The new temperature is: " + newTemp + unit);
		alert.show();
	}
}
