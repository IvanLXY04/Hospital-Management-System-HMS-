import java.io.*;
import java.util.*;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class Doctor{
	 // Private attributes for doctor information
	private String id;
	private String name;
	private String specialist;
	private String workTime;
	private String qualification;
	private int room;
	
	// Getter and setter methods for each attribute
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSpecialist() {
		return specialist;
	}
	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	
	// Method to set button font and style
	private void setButtonFont(Button button) {
	    button.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 14));
	    button.setStyle("-fx-background-color: peachpuff; -fx-text-fill: rosybrown; -fx-border-color: darkkhaki; -fx-border-width: 2px;");
	}
	
	// Method to create a new doctor entry
	public BorderPane newDoctor(ArrayList<Doctor> doctors) {
		// Create UI components for input
		Text header = new Text("Doctor");
	    header.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 40));

	    Label messageLabel = new Label();
	    messageLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));

	    // Create text fields for doctor information
	    TextField idtxt = new TextField();
	    TextField nametxt = new TextField();
	    TextField sptxt = new TextField();
	    TextField wttxt = new TextField();
	    TextField qltxt = new TextField();
	    TextField roomtxt = new TextField();

	    // Create submit button
	    Button button = new Button("Submit");
	    setButtonFont(button);
	    button.setPrefWidth(150);

	    // Set action for submit button
	    button.setOnAction(e -> {
	        this.id = idtxt.getText();
	        this.name = nametxt.getText();
	        this.specialist = sptxt.getText();
	        this.workTime = wttxt.getText();
	        this.qualification = qltxt.getText();
	        this.room = Integer.parseInt(roomtxt.getText());

	        // Check if doctor already exists
	        String doctorExist = nametxt.getText();
	        for (int i = 0; i < doctors.size(); i++) {
	            if (doctors.get(i).getName().equalsIgnoreCase(doctorExist)) {
	                messageLabel.setText("Doctor Already Exists. Please Update Information Instead.");
	                return;
	            }
	        }

	        // Output to file
	        FileWriter fw = null;
	        try {
	            fw = new FileWriter("Doctor.txt", true);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }

	        PrintWriter outputFile = new PrintWriter(fw);
	        // Write doctor information to file
	        outputFile.println(id);
	        outputFile.println(name);
	        outputFile.println(specialist);
	        outputFile.println(workTime);
	        outputFile.println(qualification);
	        outputFile.println(room);
	        outputFile.println("");
	        outputFile.close();
	        
	        // Add doctor to the list and show success message
	        doctors.add(this);
	        messageLabel.setText("Added successfully!");
	    });

	    // Create and set up the layout
	    GridPane gridpane = new GridPane();
	    gridpane.setHgap(10);
	    gridpane.setVgap(10);

	    gridpane.add(new Label("Enter id:"), 0, 0);
	    gridpane.add(idtxt, 1, 0);
	    gridpane.add(new Label("Enter name:"), 0, 1);
	    gridpane.add(nametxt, 1, 1);
	    gridpane.add(new Label("Enter specialist:"), 0, 2);
	    gridpane.add(sptxt, 1, 2);
	    gridpane.add(new Label("Enter work time:"), 0, 3);
	    gridpane.add(wttxt, 1, 3);
	    gridpane.add(new Label("Enter qualification:"), 0, 4);
	    gridpane.add(qltxt, 1, 4);
	    gridpane.add(new Label("Enter room:"), 0, 5);
	    gridpane.add(roomtxt, 1, 5);

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
	
	// Method to display doctor information in a GridPane
	public GridPane showDoctorInfo(GridPane pane, int row) {
		pane.add(new Text(id), 0, row);
		pane.add(new Text(name), 1, row);
		pane.add(new Text(specialist), 2, row);
		pane.add(new Text(workTime), 3, row);
		pane.add(new Text(qualification), 4, row);
		pane.add(new Text(String.valueOf(room)), 5, row); 
		return pane;
	}
}