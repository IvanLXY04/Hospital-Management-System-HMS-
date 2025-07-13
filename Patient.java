import java.io.*;
import java.util.*;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class Patient {

	// Private attributes for patient information
    private String id, name, disease, sex, admitStatus;
    private int age;

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

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAdmitStatus() {
        return admitStatus;
    }

    public void setAdmitStatus(String admitStatus) {
        this.admitStatus = admitStatus;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Method to set button font and style
    private void setButtonFont(Button button) {
        button.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 14));
        button.setStyle("-fx-background-color: peachpuff; -fx-text-fill: rosybrown; -fx-border-color: darkkhaki; -fx-border-width: 2px;");
    }

    // Method to create a new patient entry
    public BorderPane newPatient(ArrayList<Patient> patients) {
    	// Create UI components for input
    	Text header = new Text("Patient");
        header.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 40));

        Label messageLabel = new Label();
        messageLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));

        // Create text fields for patient information
        TextField idtxt = new TextField();
        TextField nametxt = new TextField();
        TextField diseasetxt = new TextField();
        TextField sextxt = new TextField();
        TextField admitStatustxt = new TextField();
        TextField agetxt = new TextField();

        // Create submit button
        Button button = new Button("Submit");
        setButtonFont(button);
        button.setPrefWidth(150);
        // Set action for submit button
        button.setOnAction(e -> {
            this.id = idtxt.getText();
            this.name = nametxt.getText();
            this.disease = diseasetxt.getText();
            this.sex = sextxt.getText();
            this.admitStatus = admitStatustxt.getText();
            this.age = Integer.parseInt(agetxt.getText());
            
            String patientExist = nametxt.getText();
	        for (int i = 0; i < patients.size(); i++) {
	            if (patients.get(i).getName().equalsIgnoreCase(patientExist)) {
	                messageLabel.setText("Patient Already Exists. Please Update Information Instead.");
	                return;
	            }
	        }
            
            // Output to file
            try (FileWriter fw = new FileWriter("Patient.txt", true);
                PrintWriter outputFile = new PrintWriter(fw)) {
            	// Write patient information to file
            	outputFile.println(id);
                outputFile.println(name);
                outputFile.println(disease);
                outputFile.println(sex);
                outputFile.println(admitStatus);
                outputFile.println(age);
                outputFile.println("");
                // Add patient to the list and show success message
                patients.add(this);
                messageLabel.setText("Added successfully!");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        // Create and set up the layout
        GridPane gridpane = new GridPane();
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        gridpane.setPadding(new Insets(15));
        gridpane.setAlignment(Pos.CENTER);
        
        gridpane.add(new Label("Enter id:"), 0, 0);
        gridpane.add(idtxt, 1, 0);
        gridpane.add(new Label("Enter name:"), 0, 1);
        gridpane.add(nametxt, 1, 1);
        gridpane.add(new Label("Enter disease:"), 0, 2);
        gridpane.add(diseasetxt, 1, 2);
        gridpane.add(new Label("Enter sex:"), 0, 3);
        gridpane.add(sextxt, 1, 3);
        gridpane.add(new Label("Enter admit status:"), 0, 4);
        gridpane.add(admitStatustxt, 1, 4);
        gridpane.add(new Label("Enter age:"), 0, 5);
        gridpane.add(agetxt, 1, 5);

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

    // Method to display patient information in a GridPane
    public GridPane showPatientInfo(GridPane pane, int row) {
        pane.add(new Text(id), 0, row);
        pane.add(new Text(name), 1, row);
        pane.add(new Text(disease), 2, row);
        pane.add(new Text(sex), 3, row);
        pane.add(new Text(admitStatus), 4, row);
        pane.add(new Text(String.valueOf(age)), 5, row);
        return pane;
    }
}