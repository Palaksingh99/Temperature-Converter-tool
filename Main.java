package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application{
	public static void main(String[] args){
		System.out.println("main");//not a part of lifecycle of javafx application
    launch(args);
	}

	@Override
	public void init() throws Exception {
		System.out.println("init");//Initialize our application
		super.init();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		System.out.println("start");//Starts an application
		FXMLLoader loader = new FXMLLoader(getClass().getResource("app_layout.fxml"));
		VBox rootNode = loader.load();
		MenuBar menuBar = createMenu();
		rootNode.getChildren().add(0,menuBar);

		Scene scene = new Scene(rootNode);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Temperature Converter Tool");
		primaryStage.show();

	}
	private MenuBar createMenu(){
		//file Menu
		Menu fileMenu = new Menu("File");
		MenuItem newmenuItem = new MenuItem("New");

		newmenuItem.setOnAction(event -> {
			System.out.println("New Menu Item Clicked ");
			//More code...
		});

		SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
		MenuItem menuQuit = new MenuItem("Quit");
		menuQuit.setOnAction(event->{
			Platform.exit();
			System.exit(0);
		});

		fileMenu.getItems().addAll(newmenuItem ,separatorMenuItem,menuQuit);
		//help Menu
		Menu helpMenu = new Menu("Help");
		MenuItem menuaAbout = new MenuItem("About");

		menuaAbout.setOnAction(event -> aboutApp());
		helpMenu.getItems().addAll(menuaAbout);
		//menu Bar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu ,helpMenu);

		return menuBar;
	}

	private void aboutApp() {
		Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
		alertDialog.setTitle("My fisrt desktop app");
		alertDialog.setHeaderText("Learning javaFX");
		alertDialog.setContentText("I am just a beginner but soon i will be pro and start developing awesome Java games");

		ButtonType yesbBtn = new ButtonType("Yes");
		ButtonType noBtn = new ButtonType("No");

		alertDialog.getButtonTypes().setAll(yesbBtn,noBtn);

		Optional<ButtonType> clickedButton = alertDialog.showAndWait();
		if(clickedButton.isPresent() && clickedButton.get()== yesbBtn){
			System.out.println("Yes button clicked");
		}else{
			System.out.println("No button clicked");
		}
	}

	@Override
	public void stop() throws Exception {
		System.out.println("stop");//called when application is stopped and is about to shut down
		super.stop();
	}
}
