import java.io.*;
import java.util.*;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class Lab{
	private String lab; 
	private int cost;

	//get
	public String getLab() {
		return lab;
	}
	public int getCost() {
		return cost;
	}

	//set
	public void setLab(String lab) {
		this.lab=lab;
	}
	public void setCost(int cost) {
		this.cost=cost;
	}
	private void setButtonFont(Button button) {
	    button.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 14));
	    button.setStyle("-fx-background-color: peachpuff; -fx-text-fill: rosybrown; -fx-border-color: darkkhaki; -fx-border-width: 2px;");
	}
	
	
	public BorderPane newLab(ArrayList<Lab> laboratories, ArrayList<Facility> facilities) {
		//text
	    Text header = new Text("Laboratory");
	    header.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 40));
	    Label messageLabel = new Label();
	    messageLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
	    TextField txt = new TextField();
	    
	    //button
	    Button button = new Button("Submit");
	    setButtonFont(button);
	    button.setPrefWidth(150);
	    
	    //dropdownlist
	    ComboBox<String> labComboBox = new ComboBox<>();

	    //layout
	    GridPane gridpane = new GridPane();
	    gridpane.add(new Label("Choose Facility:"), 0, 0);
	    gridpane.add(labComboBox, 1, 0);
	    gridpane.add(new Label("Enter Cost:"), 0, 1);
	    gridpane.add(txt, 1, 1);
	    gridpane.setHgap(10);
	    gridpane.setVgap(10);
	    gridpane.setPadding(new Insets(15));
	    gridpane.setAlignment(Pos.CENTER);

	    //replacedropdownlist
	    if (facilities.size() == 0) {
	        replaceNodeInGridPane(gridpane, new Label("No Facility"), 1, 0);
	    } else {
	        for (Facility facility : facilities) {
	            labComboBox.getItems().add(facility.getFacility());
	        }
	    }

	    button.setOnAction(e -> {
	        if (facilities.size() != 0) {
	            String selectedFacility = labComboBox.getValue();
	            this.lab = selectedFacility;
	            this.cost = Integer.parseInt(txt.getText());

	            // Output to file
	            try (FileWriter fw = new FileWriter("Laboratory.txt", true);
	                 PrintWriter outputFile = new PrintWriter(fw)) {
	                outputFile.println(lab);
	                outputFile.println(cost);
	                outputFile.println("");
	            } catch (IOException e1) {
	                e1.printStackTrace();
	            }

	            laboratories.add(this);
	            messageLabel.setText("Added successfully!");
	        } 
	        else {
	            messageLabel.setText("No Facility is selected. Cannot Submit");
	        }
	    });
	    
	    
	    //layout
	    VBox vbox = new VBox(button, messageLabel);
	    vbox.setPadding(new Insets(15));
	    vbox.setAlignment(Pos.CENTER);

	    BorderPane borderpane = new BorderPane();
	    borderpane.setTop(header);
	    borderpane.setCenter(gridpane);
	    borderpane.setBottom(vbox);
	    BorderPane.setAlignment(header, Pos.CENTER);
	    BorderPane.setAlignment(gridpane, Pos.CENTER);
	    BorderPane.setAlignment(vbox, Pos.CENTER);
	    return borderpane;
	}
	
	public void replaceNodeInGridPane(GridPane gridPane, Node newNode, int column, int row) {
	    // Remove the existing node at the specified row and column
	    Node nodeToRemove = null;
	    for (Node node : gridPane.getChildren()) {
	        if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
	            nodeToRemove = node;
	            break;
	        }
	    }
	    if (nodeToRemove != null) {
	        gridPane.getChildren().remove(nodeToRemove);
	    }

	    // Add the new node at the same row and column
	    gridPane.add(newNode, column, row);
	}
	
	public GridPane labList(GridPane gridpane, int row) {
		gridpane.add(new Text(lab), 0, row);
		gridpane.add(new Text(Integer.toString(cost)), 1, row);
		return gridpane;
	}
	
}