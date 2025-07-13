import java.io.*;
import java.util.*;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class Staff {

	// Private attributes for staff information
    private String id, name, designation, sex;
    private int salary;

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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    // Method to set button font and style
    private void setButtonFont(Button button) {
        button.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 14));
        button.setStyle("-fx-background-color: peachpuff; -fx-text-fill: rosybrown; -fx-border-color: darkkhaki; -fx-border-width: 2px;");
    }

    // Method to create a new staff entry
    public BorderPane newStaff(ArrayList<Staff> staffs) {
    	// Create UI components for input
    	Text header = new Text("Staff");
        header.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 40));

        Label messageLabel = new Label();
        messageLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));

        // Create text fields for staff information
        TextField idtxt = new TextField();
        TextField nametxt = new TextField();
        TextField dstxt = new TextField();
        TextField sextxt = new TextField();
        TextField salarytxt = new TextField();

        // Create submit button
        Button button = new Button("Submit");
        setButtonFont(button);
        button.setPrefWidth(150);
        // Set action for submit button
        button.setOnAction(e -> {
            this.id = idtxt.getText();
            this.name = nametxt.getText();
            this.designation = dstxt.getText();
            this.sex = sextxt.getText();
            this.salary = Integer.parseInt(salarytxt.getText());

            String staffExist = nametxt.getText();
	        for (int i = 0; i < staffs.size(); i++) {
	            if (staffs.get(i).getName().equalsIgnoreCase(staffExist)) {
	                messageLabel.setText("Staff Already Exists. Please Update Information Instead.");
	                return;
	            }
	        }
            
            // Output to file
            try (FileWriter fw = new FileWriter("Staff.txt", true);
                PrintWriter outputFile = new PrintWriter(fw)) {
            	 // Write staff information to file
            	outputFile.println(id);
                outputFile.println(name);
                outputFile.println(designation);
                outputFile.println(sex);
                outputFile.println(salary);
                outputFile.println("");
                // Add staff to the list and show success message
                staffs.add(this);
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
        gridpane.add(new Label("Enter designation:"), 0, 2);
        gridpane.add(dstxt, 1, 2);
        gridpane.add(new Label("Enter sex:"), 0, 3);
        gridpane.add(sextxt, 1, 3);
        gridpane.add(new Label("Enter salary:"), 0, 4);
        gridpane.add(salarytxt, 1, 4);

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

 // Method to display staff information in a GridPane
    public GridPane showStaffInfo(GridPane pane, int row) {
        pane.add(new Text(id), 0, row);
        pane.add(new Text(name), 1, row);
        pane.add(new Text(designation), 2, row);
        pane.add(new Text(sex), 3, row);
        pane.add(new Text(Integer.toString(salary)), 4, row);
        return pane;
    }
}