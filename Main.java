package pins;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Sets up variables
		Stage window;
		Scene scene;
		Button button;
		Pins guess = new Pins();
		// Pins originalPins = new Pins();
		ArrayList<Pins> pastGuesses = new ArrayList<Pins>();
		ArrayList<HBox> pastGuessHBox = new ArrayList<HBox>();
		ArrayList<Circle[]> pastGuessCircles = new ArrayList<Circle[]>();

		// Creates the window
		primaryStage.setTitle("Mastermind Game");
		window = primaryStage;

		final Pins originalPins = Pins.generatePins();// But I just need it to
														// do this once!!

		// Creates the drop down menus
		ChoiceBox<String>[] colourMenus = new ChoiceBox[Pins.getNumPins()];
		for (int i = 0; i < Pins.getNumPins(); i++) {
			colourMenus[i] = new ChoiceBox<>();
			for (int j = 0; j < Pins.getPinColours().length; j++) {
				colourMenus[i].getItems().add(Pins.getPinColours()[j]);
			}
			colourMenus[i].setValue(Pins.getPinColours()[0]);
		}

		// Creates the enter button
		button = new Button("Enter Guess");

		// Attaches the menus & button to the top section
		HBox topMenu = new HBox(10);
		topMenu.setPadding(new Insets(20, 20, 20, 20));
		for (int i = 0; i < Pins.getNumPins(); i++) {
			topMenu.getChildren().add(colourMenus[i]);
		}
		topMenu.getChildren().addAll(button);

		// Creates middle section
		VBox guessDisplay = new VBox(10);

		// Attaches the top menu & middle section to the screen
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(topMenu);
		borderPane.setCenter(guessDisplay);

		// Creates the button's response to being pressed
		button.setOnAction(e -> {
			pastGuesses.add(guess);
			respondToGuess(guess, originalPins, colourMenus, guessDisplay);
		});

		// Shows the window
		scene = new Scene(borderPane, 500, 500);
		window.setScene(scene);
		window.show();
	}

	public void respondToGuess(Pins guess, Pins originalPins, ChoiceBox<String>[] colourMenus, VBox guessDisplay) {

		// Gets the user's guess & adds to the list of past guesses
		for (int i = 0; i < Pins.getNumPins(); i++) {
			guess.pins[i] = colourMenus[i].getValue();
		}

		// Creates the circle output and adds to the screen
		HBox hbox = new HBox(10);
		Circle[] outputCircles = new Circle[2 * Pins.getNumPins()];

		// Circles for the guess
		for (int i = 0; i < Pins.getNumPins(); i++) {
			outputCircles[i] = new Circle(20.0, Pins.colourOfPins.get(guess.pins[i]));
		}

		// Circles for the result
		int[] a = new int[3];
		a = guess.evaluatePins(originalPins);
		for (int i = Pins.getNumPins(); i < Pins.getNumPins() + a[0]; i++) {
			outputCircles[i] = new Circle(15.0, Color.GREEN);
		}
		for (int i = Pins.getNumPins() + a[0]; i < Pins.getNumPins() + a[0] + a[1]; i++) {
			outputCircles[i] = new Circle(15.0, Color.YELLOW);
		}
		for (int i = Pins.getNumPins() + a[0] + a[1]; i < Pins.getNumPins() + a[0] + a[1] + a[2]; i++) {
			outputCircles[i] = new Circle(15.0, Color.RED);
		}
		for (int i = 0; i < 2 * Pins.getNumPins(); i++) {
			hbox.getChildren().add(outputCircles[i]);
		}
		guessDisplay.getChildren().add(hbox);

	}
}

