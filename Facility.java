import java.io.*;
import java.util.*;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class Facility{

	private String facility;
	
	
	
	//get
	public String getFacility() {
		return facility;
	}
	
	//set
	public void setFacility(String facility) {
		this.facility = facility;
	}
	
	private void setButtonFont(Button button) {
	    button.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 14));
	    button.setStyle("-fx-background-color: peachpuff; -fx-text-fill: rosybrown; -fx-border-color: darkkhaki; -fx-border-width: 2px;");
	}
	
	public BorderPane newFacility(ArrayList<Facility> facilities) {
		//text
		Text header = new Text("Facility");
		header.setFont(Font.font("Times New Roman",FontWeight.BOLD, FontPosture.REGULAR, 40));
	    Label messageLabel = new Label();
	    messageLabel.setFont(Font.font("Times New Roman",FontWeight.BOLD, FontPosture.REGULAR, 20));
		TextField txt = new TextField();
		
		//button
		Button button = new Button("Submit");
		setButtonFont(button);
		button.setPrefWidth(150);
		
		button.setOnAction(e->{
			this.facility = txt.getText();
			
			for(int i = 0; i < facilities.size(); i++) {
				if(facilities.get(i).getFacility().equalsIgnoreCase(this.facility)) {
					messageLabel.setText("Item Already Exists. Please Update Count Instead.");
					return;
				}
			}
			
			//output to file
			FileWriter fw = null;
			try {
				fw = new FileWriter("Facility.txt", true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			PrintWriter outputFile = new PrintWriter(fw);
			outputFile.println(facility);
			outputFile.println("");
			outputFile.close();		

			facilities.add(this);
            messageLabel.setText("Added successfully!");
		});
		
		//layout
		HBox hbox = new HBox(new Label("Enter facility: "),txt);
		hbox.setPadding(new Insets(15));
		hbox.setAlignment(Pos.CENTER);
		
	    VBox vbox = new VBox(button, messageLabel);
	    vbox.setPadding(new Insets(15));
	    vbox.setAlignment(Pos.CENTER);

		BorderPane borderpane = new BorderPane();
	    borderpane.setTop(header);
	    borderpane.setCenter(hbox);
	    borderpane.setBottom(vbox);
	    BorderPane.setAlignment(header, Pos.CENTER);
	    BorderPane.setAlignment(hbox, Pos.CENTER);
	    BorderPane.setAlignment(vbox, Pos.CENTER);
		return borderpane;
	}
	
	public GridPane showFacility(GridPane gridpane, int row) {
		gridpane.add(new Text(facility), 0, row);
		return gridpane;
	}

	
}