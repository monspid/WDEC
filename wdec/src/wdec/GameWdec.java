package wdec;


import java.io.IOException;
import com.ampl.AMPL;
import com.ampl.DataFrame;
import com.ampl.Objective;
import com.ampl.Parameter;
import com.ampl.Variable;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameWdec extends Application {
	private TextField moneyTextField = new TextField("1000000");
	private TextField tvAdsTextField = new TextField("0");
	private TextField internetAdsTextField = new TextField("0");
	private TextField magazineAdsField = new TextField("0");
	private Button button = new Button("Solve");
	private Text infoText = new Text();
	private Text wolumenText = new Text();
	private Text priceText = new Text();
	private Text qualityText = new Text();
	

	private void init(Stage stage) {
	
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Label moneyLabel = new Label("Money:");
		grid.add(moneyLabel, 0, 1);

		moneyTextField.setPromptText("Enter money");
		grid.add(moneyTextField, 1, 1);

		Label tvAdsLabel = new Label("Tv Ads:");
		grid.add(tvAdsLabel, 0, 2);

		tvAdsTextField.setPromptText("Enter tv ads");
		grid.add(tvAdsTextField, 1, 2);

		Label internetAdsLabel = new Label("Internet Ads:");
		grid.add(internetAdsLabel, 0, 3);

		internetAdsTextField.setPromptText("Enter internet Ads");
		grid.add(internetAdsTextField, 1, 3);

		Label magazineAdsLabel = new Label("Magazine Ads:");
		grid.add(magazineAdsLabel, 0, 4);

		magazineAdsField.setPromptText("Enter magazine Ads");
		grid.add(magazineAdsField, 1, 4);

		HBox textHbox = new HBox();
		textHbox.setAlignment(Pos.BASELINE_CENTER);
		textHbox.getChildren().add(infoText);

		grid.add(textHbox, 0, 7, 2, 1);

		HBox buttonHbox = new HBox();
		buttonHbox.setAlignment(Pos.BASELINE_CENTER);
		buttonHbox.getChildren().add(button);

		grid.add(buttonHbox, 0, 8, 2, 1);
		
		VBox solutions = new VBox();
		solutions.setAlignment(Pos.BASELINE_CENTER);
		solutions.getChildren().add(wolumenText);
		solutions.getChildren().add(priceText);
		solutions.getChildren().add(qualityText);
		
		grid.add(solutions, 0, 9, 2, 1);

		button.setOnAction((ActionEvent event) -> {
			handleButton();
		});

		Scene scene = new Scene(grid, 500, 500);

		stage.setScene(scene);
		stage.setTitle("WDEC");
		stage.show();
	}

	private void handleButton() {

		double money, tvAds, internetAds, magazineAds;
		AMPL ampl = new AMPL();
		infoText.setText("");
		try {
			money = Double.parseDouble(moneyTextField.getText());
			tvAds = Double.parseDouble(tvAdsTextField.getText());
			internetAds = Double.parseDouble(internetAdsTextField.getText());
			magazineAds = Double.parseDouble(magazineAdsField.getText());
			
			ampl.read("gra.mod");
			ampl.readData("gra.dat");
			
			Parameter moneyParam = ampl.getParameter("gotowka");
			moneyParam.set(money);
			Parameter tvAdsParam = ampl.getParameter("reklamaTelewizja");
			tvAdsParam.set(tvAds);
			Parameter internetAdsParam = ampl.getParameter("reklamaInternet");
			internetAdsParam.set(internetAds);
			Parameter magazineParam = ampl.getParameter("reklamaMagazyny");
			magazineParam.set(magazineAds);
			
			ampl.solve();
			
			Objective total = ampl.getObjective("f_celu");
			
			System.out.println("zysk: " + total.value());
			
			Variable wolumen = ampl.getVariable("wolumen");
			Variable price = ampl.getVariable("cena");
			Variable quality = ampl.getVariable("jakosc");
			
			DataFrame df = wolumen.getValues();
			System.out.println(df);
			wolumenText.setText("Wolumen: " + df.toString());
			
			df = price.getValues();
			System.out.println(df);
			priceText.setText("Price: " + df.toString());
			
			df = quality.getValues();
			System.out.println(df);
			qualityText.setText("Quality: " + df.toString());

		} catch (NumberFormatException e) {
			infoText.setFill(Color.FIREBRICK);
			infoText.setText("Invalid number format");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ampl.close();
		}
	}

	@Override
	public void start(Stage stage) {
		init(stage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}