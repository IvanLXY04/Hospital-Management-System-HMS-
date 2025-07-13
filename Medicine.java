import java.io.*;
import java.util.*;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class Medicine{

	private String name,manufacturer,expiryDate;
	private int cost,count;
	
	//get
	public String getName() {
		return name;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public int getCost() {
		return cost;
	}
	public int getCount() {
		return count;
	}

	//set
	public void setName(String name) {
		this.name=name;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer=manufacturer;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate=expiryDate;
	}
	public void setCost(int cost) {
		this.cost=cost;
	}
	public void setCount(int count) {
		this.count=count;
	}
	
	private void setButtonFont(Button button) {
	    button.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 14));
	    button.setStyle("-fx-background-color: peachpuff; -fx-text-fill: rosybrown; -fx-border-color: darkkhaki; -fx-border-width: 2px;");
	}
	
	public BorderPane newMedicine(ArrayList<Medicine> medicine) {
		
		//text
	    Text header = new Text("Medicine");
	    header.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 40));
	    Label messageLabel = new Label();
	    messageLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
	    
	    TextField nametxt = new TextField();
	    TextField mantxt = new TextField();
	    TextField exptxt = new TextField();
	    TextField costtxt = new TextField();
	    TextField counttxt = new TextField();

	    //button
	    Button button = new Button("Submit");
	    setButtonFont(button);
	    button.setPrefWidth(150);
	    
	    button.setOnAction(e -> {
			this.name = nametxt.getText();
			this.manufacturer = mantxt.getText();
			this.expiryDate = exptxt.getText();
			this.cost = Integer.parseInt(costtxt.getText());
			this.count = Integer.parseInt(counttxt.getText());
			
			for(int i = 0; i < medicine.size(); i++) {
				if(medicine.get(i).getName().equalsIgnoreCase(this.name)) {
					messageLabel.setText("Item Already Exists. Please Update Count Instead.");
					return;
				}
			}

			//output to file
			FileWriter fw = null;
			try {
				fw = new FileWriter("Medicine.txt", true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			PrintWriter outputFile = new PrintWriter(fw);
			outputFile.println(name);
			outputFile.println(manufacturer);
			outputFile.println(expiryDate);
			outputFile.println(cost);
			outputFile.println(count);
			outputFile.println("");
			outputFile.close();	
			
			medicine.add(this);
			messageLabel.setText("Added successfully!");
		});
	    
	    //layout
	    GridPane gridpane = new GridPane();
	    gridpane.setHgap(10);
	    gridpane.setVgap(10);
	    gridpane.add(new Label("Enter name:"), 0, 0);
	    gridpane.add(nametxt, 1, 0);
	    gridpane.add(new Label("Enter manufacturer:"), 0, 1);
	    gridpane.add(mantxt, 1, 1);
	    gridpane.add(new Label("Enter expiryDate:"), 0, 2);
	    gridpane.add(exptxt, 1, 2); 
	    gridpane.add(new Label("Enter cost:"), 0, 3);
	    gridpane.add(costtxt, 1, 3);
	    gridpane.add(new Label("Enter Unit:"), 0, 4);
	    gridpane.add(counttxt, 1, 4);
	    gridpane.setHgap(10);
	    gridpane.setVgap(10);
	    gridpane.setPadding(new Insets(15));
	    gridpane.setAlignment(Pos.CENTER);
	    
	    VBox vbox = new VBox(button, messageLabel);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setPadding(new Insets(15));
	    
	    BorderPane borderpane = new BorderPane();
	    borderpane.setTop(header);
	    borderpane.setCenter(gridpane);
	    borderpane.setBottom(vbox);
	    BorderPane.setAlignment(header, Pos.CENTER);
	    BorderPane.setAlignment(gridpane, Pos.CENTER);
	    BorderPane.setAlignment(vbox, Pos.CENTER);
	    return borderpane;
	}

	public GridPane findMedicine(GridPane gridpane, int row) {
		gridpane.add(new Text(name), 0, row);
		gridpane.add(new Text(manufacturer), 1, row);
		gridpane.add(new Text(expiryDate), 2, row);
		gridpane.add(new Text(Integer.toString(cost)), 3, row);
		gridpane.add(new Text(Integer.toString(count)), 4, row);
		return gridpane;
	}
	
}