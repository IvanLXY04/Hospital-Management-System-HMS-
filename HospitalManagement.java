import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

import javafx.animation.*;
import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.util.Duration;

public class HospitalManagement extends Application{
	// ArrayLists to store data for different entities
	ArrayList<Doctor> doctors = new ArrayList<Doctor>(25);
	ArrayList<Patient> patients = new ArrayList<Patient>(100);
	ArrayList<Lab> laboratories = new ArrayList<Lab>(20);
	ArrayList<Facility> facilities = new ArrayList<Facility>(20);
	ArrayList<Medicine> medicine = new ArrayList<Medicine>(100);
	ArrayList<Staff> staff = new ArrayList<Staff>(100);
	String selection = "";
	BorderPane borderpane = new BorderPane();
	
	@Override
	
	// Main method to start the application
	public void start(Stage primaryStage) {
		//ReadFile
		try {
			readFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Text text = new Text("Welcome to the HMS");
		text.setFont(Font.font("Brush Script MT",FontWeight.BOLD, FontPosture.REGULAR, 65));
		text.setFill(Color.TAN);
		text.setStroke(Color.LIGHTSALMON);
		text.setStrokeWidth(1);
		
		Text datetxt = new Text(" Date            Time");
		datetxt.setFont(Font.font("Serif",FontWeight.BOLD, FontPosture.REGULAR, 35));
		Text date = new Text();
		date.setFont(Font.font("Serif",FontWeight.BOLD, FontPosture.REGULAR, 35));
		
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.seconds(1), event -> {
		            LocalDateTime now = LocalDateTime.now();
		            String dateTimeString = now.format(DateTimeFormatter.ofPattern("dd-MM-yyyy	HH:mm:ss"));
		            date.setText(dateTimeString);
				})
		);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

		// Exit Button
	    Button exitButton = new Button("Exit");
		setButtonFont(exitButton);
		exitButton.setPrefWidth(150);
	    exitButton.setOnAction(e -> {
	        primaryStage.close();  // Closes the application window
	    });

	    exitButton.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 14));
	    exitButton.setStyle("-fx-background-color: peachpuff; -fx-text-fill: rosybrown; -fx-border-color: darkkhaki; -fx-border-width: 2px;");
	    
	    // Adding the exit button to an HBox for alignment
	    HBox exitHbox = new HBox(10, exitButton);
	    exitHbox.setAlignment(Pos.CENTER);

		VBox vbox = new VBox();
		vbox.getChildren().addAll(datetxt,date,new Text("") ,exitHbox);
		vbox.setPadding(new Insets(15));
		vbox.setAlignment(Pos.CENTER);
	    
		borderpane.setTop(text);
		borderpane.setBottom(vbox);
		borderpane.setStyle("-fx-border-color: lightsalmon; -fx-border-width: 4px;");
	    BorderPane.setAlignment(text,Pos.BOTTOM_CENTER);
	    BorderPane.setAlignment(vbox, Pos.TOP_CENTER);
	    
		displayMenu();
		
		Scene scene = new Scene(borderpane,1280,720);
		borderpane.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
		primaryStage.setTitle("Hospital Management System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void setButtonFont(Button button) {
	    button.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 14));
	    button.setStyle("-fx-background-color: peachpuff; -fx-text-fill: rosybrown; -fx-border-color: darkkhaki; -fx-border-width: 2px;");
	}
	
	//Main Menu
	public void displayMenu(){
		Button doctorbutton = new Button("Doctors");
		setButtonFont(doctorbutton);
		doctorbutton.setPrefWidth(150);
		doctorbutton.setOnAction(e->{	
			selection = "Doctor";
			actionMenu(showDoctorInfo());
		});
		
		Button patientbutton = new Button("Patients");
		setButtonFont(patientbutton);
		patientbutton.setPrefWidth(150);
		patientbutton.setOnAction(e->{
			selection = "Patient";
			actionMenu(showPatientInfo());
		});
		
		Button medicinebutton = new Button("Medicine");
		setButtonFont(medicinebutton);
		medicinebutton.setPrefWidth(150);
		medicinebutton.setOnAction(e->{
			selection = "Medicine";
			actionMenu(findMedicine());
		});
		
		Button labbutton = new Button("Laboratory");
		setButtonFont(labbutton);
		labbutton.setPrefWidth(150);
		labbutton.setOnAction(e->{
			selection = "Laboratory";
			actionMenu(labList());
		});
		
		Button facilitybutton = new Button("Faclility");
		setButtonFont(facilitybutton);
		facilitybutton.setPrefWidth(150);
		facilitybutton.setOnAction(e->{
			selection = "Facility";
			actionMenu(showFacility());
		});
		
		Button staffbutton = new Button("Staff");
		setButtonFont(staffbutton);
		staffbutton.setPrefWidth(150);
		staffbutton.setOnAction(e->{
			selection = "Staff";
			actionMenu(showStaffInfo());
		});

		HBox hbox = new HBox(6);
		hbox.getChildren().addAll(doctorbutton, patientbutton, medicinebutton, labbutton, facilitybutton, staffbutton);
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(15);
		borderpane.setCenter(hbox);
	    BorderPane.setAlignment(hbox, Pos.CENTER);
	}
	
	//show add, draw, main menu
	public void actionMenu(VBox vbox) {
		
		Button addbutton = new Button("Add New");
		setButtonFont(addbutton);
		addbutton.setPrefWidth(150);
		addbutton.setOnAction(e->{
			if(selection.equals("Doctor")) {
				//0 means no action, 1 means add, 2 means draw
				try {
					doctorMenu(1);
				}
				catch (IOException ex){
					System.out.println("Invalid file ouput");
				}
			}
			else if(selection.equals("Patient")) {
				//0 means no action, 1 means add, 2 means draw
				try {
					patientMenu(1);
				}
				catch (IOException ex){
					System.out.println("Invalid file ouput");
				}
			}
			else if(selection.equals("Medicine")) {
				//0 means no action, 1 means add, 2 means draw
				try {
					medicineMenu(1);
				}
				catch (IOException ex){
					System.out.println("Invalid file ouput");
				}
			}
			else if(selection.equals("Laboratory")) {
				//0 means no action, 1 means add, 2 means draw
				try {
					labMenu(1);
				}
				catch (IOException ex){
					System.out.println("Invalid file ouput");
				}
			}
			else if(selection.equals("Facility")) {
				//0 means no action, 1 means add, 2 means draw
				try {
					facilityMenu(1);
				}
				catch (IOException ex){
					System.out.println("Invalid file ouput");
				}
			}
			else if(selection.equals("Staff")) {
				//0 means no action, 1 means add, 2 means draw
				try {
					staffMenu(1);
				}
				catch (IOException ex){
					System.out.println("Invalid file ouput");
				}
			}
		});
		
		Button editbutton = new Button("Edit");
		setButtonFont(editbutton);
		editbutton.setPrefWidth(150);
		editbutton.setOnAction(e->{
			if(selection.equals("Doctor")) {
				try {
					doctorMenu(2);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else if(selection.equals("Patient")) {
				try {
					patientMenu(2);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else if(selection.equals("Medicine")) {
				try {
					medicineMenu(2);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else if(selection.equals("Laboratory")) {
				try {
					labMenu(2);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else if(selection.equals("Facility")) {
				try {
					facilityMenu(2);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else if(selection.equals("Staff")) {
				try {
					staffMenu(2);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//back to main menu
		Button mainbutton = new Button("Main Menu");
		setButtonFont(mainbutton);
		mainbutton.setPrefWidth(150);
		mainbutton.setOnAction(e->{
			displayMenu();
		});
		
		HBox hbox = new HBox();
		hbox.getChildren().addAll(addbutton,editbutton,mainbutton);
		hbox.setSpacing(15);
		hbox.setAlignment(Pos.CENTER);
		
		vbox.getChildren().add(hbox);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(15);
		borderpane.setCenter(vbox);
	    BorderPane.setAlignment(vbox, Pos.CENTER);
	}
	
	
	//void Methods is for each entity (doctor, patient, medicine, lab, facility, staff) to display records, add new entries, edit or delete existing entries
	//VBox Methods to display records for each entity
	
	//doctor
	//1 return to main menu, 2 means edit
	public void doctorMenu(int action) throws IOException {
		
		if(action == 1){
			Doctor newDoctor = new Doctor();
			//Previous button
			Button prevBtn = new Button("Previous");
			setButtonFont(prevBtn);
			prevBtn.setOnAction(e->{
				actionMenu(showDoctorInfo());
			});
			prevBtn.setPrefWidth(150);
			VBox content = new VBox();
			content.setAlignment(Pos.CENTER);
			content.setSpacing(10);
			content.getChildren().addAll(newDoctor.newDoctor(doctors),prevBtn);
			borderpane.setCenter(content);
		}
		else if(action == 2){
			Label instruction = new Label("Select an ID:");
			TextField inputId = new TextField();
			inputId.setPrefWidth(100);
			HBox rowA = new HBox();
			rowA.setSpacing(20);
			rowA.getChildren().addAll(instruction,inputId);
			rowA.setAlignment(Pos.CENTER);
			Button drawBtn = new Button("Draw");
			setButtonFont(drawBtn);
			drawBtn.setOnAction(e->{
				String drawId = inputId.getText();
				for(int i = 0; i < doctors.size(); i++){
					if(doctors.get(i).getId().equals(drawId)){
						doctors.remove(i);
						try {
							outputFile();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						break;
					}
				}
				actionMenu(showDoctorInfo());
			});
			drawBtn.setAlignment(Pos.CENTER);
			
			Button prevBtn = new Button("Previous");
			setButtonFont(prevBtn);
			prevBtn.setOnAction(e->{
				actionMenu(showDoctorInfo());
			});
			VBox content = new VBox();
			prevBtn.setPrefWidth(150);
			drawBtn.setPrefWidth(150);
			content.getChildren().addAll(showDoctorInfo(),rowA,drawBtn, prevBtn);
			content.setSpacing(10);
			content.setAlignment(Pos.CENTER);
			borderpane.setCenter(content);
		
		}
		
	}
		
	public VBox showDoctorInfo() {
		GridPane record = new GridPane();
		Text object = new Text("Doctor");
		object.setFont(Font.font("Times New Roman",FontWeight.BOLD, FontPosture.REGULAR, 40));
		Text norecord = new Text("No record");
	    norecord.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
		
		
		//Header of table record
		Text idtxt = new Text("ID");
		Text nametxt = new Text("Name");
		Text sltxt = new Text("Specialist");
		Text wttxt = new Text("Work Time");
		Text qltxt = new Text("Qualification");
		Text roomtxt = new Text("Room No");
		idtxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		nametxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		sltxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		wttxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		qltxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		roomtxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		idtxt.setStroke(Color.TOMATO);
		nametxt.setStroke(Color.TOMATO);
		sltxt.setStroke(Color.TOMATO);
		wttxt.setStroke(Color.TOMATO);
		qltxt.setStroke(Color.TOMATO);
		roomtxt.setStroke(Color.TOMATO);
		record.add(idtxt, 0, 0);
		record.add(nametxt, 1, 0);
		record.add(sltxt, 2, 0);
		record.add(wttxt, 3, 0);
		record.add(qltxt, 4, 0);
		record.add(roomtxt, 5, 0);
		
		for(int col = 0; col < 5; col++) {
			record.getColumnConstraints().add(new ColumnConstraints(150));
		}

		VBox vbox = new VBox();
		vbox.getChildren().addAll(object,new Text(""));

		if(doctors.size() == 0){
			vbox.getChildren().add(record);
			vbox.getChildren().add(norecord);
		}
		else {
			for(int i = 0; i < doctors.size(); i++){
				doctors.get(i).showDoctorInfo(record, i+1);
			}
			vbox.getChildren().add(record);
		}
		
		vbox.getChildren().add(new Text(""));
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
		record.setAlignment(Pos.CENTER);
		return vbox;
	}
	
	//patient
	//0 means no action, 1 means add, 2 means draw
	public void patientMenu(int action) throws IOException {
		
		if(action == 1){
			Patient newPatient = new Patient();
			//Previous button
			Button prevBtn = new Button("Previous");
			setButtonFont(prevBtn);
			prevBtn.setOnAction(e->{
				actionMenu(showPatientInfo());
			});
			prevBtn.setPrefWidth(150);
			VBox content = new VBox();
			content.setAlignment(Pos.CENTER);
			content.setSpacing(10);
			content.getChildren().addAll(newPatient.newPatient(patients),prevBtn);
			borderpane.setCenter(content);
		}
		else if(action == 2){
			Label instruction = new Label("Select an ID:");
			TextField inputId = new TextField();
			inputId.setPrefWidth(100);
			HBox rowA = new HBox();
			rowA.setSpacing(20);
			rowA.getChildren().addAll(instruction,inputId);
			rowA.setAlignment(Pos.CENTER);
			Button drawBtn = new Button("Draw");
			setButtonFont(drawBtn);
			drawBtn.setOnAction(e->{
				String drawId = inputId.getText();
				for(int i = 0; i < patients.size(); i++){
					if(patients.get(i).getId().equals(drawId)){
						patients.remove(i);
						try {
							outputFile();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					}
				}
				actionMenu(showPatientInfo());
			});
			drawBtn.setAlignment(Pos.CENTER);
			
			Button prevBtn = new Button("Previous");
			setButtonFont(prevBtn);
			prevBtn.setOnAction(e->{
				actionMenu(showPatientInfo());
			});
			VBox content = new VBox();
			prevBtn.setPrefWidth(150);
			drawBtn.setPrefWidth(150);
			content.getChildren().addAll(showPatientInfo(),rowA,drawBtn, prevBtn);
			content.setSpacing(10);
			content.setAlignment(Pos.CENTER);
			borderpane.setCenter(content);
		
		}
		
	}
		
	public VBox showPatientInfo() {
		GridPane record = new GridPane();
		Text object = new Text("Patient");
		object.setFont(Font.font("Times New Roman",FontWeight.BOLD, FontPosture.REGULAR, 40));
		Text norecord = new Text("No record");
	    norecord.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
		
		//Header of table record
		Text idtxt = new Text("ID");
		Text nametxt = new Text("Name");
		Text diseasetxt = new Text("Disease");
		Text sextxt = new Text("Sex");
		Text adtxt = new Text("Admit Status");
		Text agetxt = new Text("Age");
		idtxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		nametxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		diseasetxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		sextxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		adtxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		agetxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		idtxt.setStroke(Color.TOMATO);
		nametxt.setStroke(Color.TOMATO);
		diseasetxt.setStroke(Color.TOMATO);
		sextxt.setStroke(Color.TOMATO);
		adtxt.setStroke(Color.TOMATO);
		agetxt.setStroke(Color.TOMATO);
		record.add(idtxt, 0, 0);
		record.add(nametxt, 1, 0);
		record.add(diseasetxt, 2, 0);
		record.add(sextxt, 3, 0);
		record.add(adtxt, 4, 0);
		record.add(agetxt, 5, 0);
		
		for(int col = 0; col < 5; col++) {
			record.getColumnConstraints().add(new ColumnConstraints(150));
		}

		VBox vbox = new VBox();
		vbox.getChildren().addAll(object,new Text(""));

		if(patients.size() == 0){
			vbox.getChildren().add(record);
			vbox.getChildren().add(norecord);
		}
		else {
			for(int i = 0; i < patients.size(); i++){
				patients.get(i).showPatientInfo(record, i+1);
			}
			vbox.getChildren().add(record);
		}
		
		vbox.getChildren().add(new Text(""));
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
		record.setAlignment(Pos.CENTER);
		return vbox;
	}	
	
	//medicine
	//1 means add, 2 means edit
	public void medicineMenu(int action) throws IOException {
		//returnbutton
		Button button = new Button("Previous");
		setButtonFont(button);
		button.setPrefWidth(150);
        button.setAlignment(Pos.CENTER);
		button.setOnAction(e->{
			actionMenu(findMedicine());
		});
		
		if(action == 1){
			//addnewmedicine
			Medicine newMedicine = new Medicine();
			VBox vbox = new VBox();
			vbox.getChildren().addAll(newMedicine.newMedicine(medicine),button);
			vbox.setSpacing(15);
			vbox.setAlignment(Pos.CENTER);
			borderpane.setCenter(vbox);
		}
		
		else if(action == 2){
			//editmedicinedetail
	        Label medName = new Label("Select a name:");
	        ComboBox<String> medicineComboBox = new ComboBox<>();
	        for (Medicine medicinemenu : medicine) {
	            medicineComboBox.getItems().add(medicinemenu.getName());
	        }

	        
	        Label updateMed = new Label("Existing Quantity (Input to change Quantity)");
	        TextField text = new TextField();
	        text.setPrefWidth(100);
	        
	        //button
	        Button updatebutton = new Button("Inventory Update");
	        Button deletebutton = new Button("Remove Medicine");
			setButtonFont(updatebutton);
			setButtonFont(deletebutton);
	        updatebutton.setPrefWidth(150);
	        deletebutton.setPrefWidth(150);
	        updatebutton.setAlignment(Pos.CENTER);
	        deletebutton.setAlignment(Pos.CENTER);
	        
	        medicineComboBox.setOnAction(e -> {
	            String selectedMedicine = medicineComboBox.getValue();
	            for (Medicine medicinemenu : medicine) {
	                if (medicinemenu.getName().equalsIgnoreCase(selectedMedicine)) {
	                	text.setText(String.valueOf(medicinemenu.getCount()));
	                    break;
	                }
	            }
	        });

	        updatebutton.setOnAction(e -> {
	            String selectedMedicine = medicineComboBox.getValue();
	            if (selectedMedicine != null) {
	                for (Medicine medicinemenu : medicine) {
	                    if (medicinemenu.getName().equalsIgnoreCase(selectedMedicine)) {
	                    	medicinemenu.setCount(Integer.parseInt(text.getText()));
	                        if (medicinemenu.getCount() == 0) {
	                        	medicine.remove(medicinemenu);
	                        }
	                        try {
	                            outputFile();
	                        } catch (IOException e1) {
	                            e1.printStackTrace();
	                        }
	                        break;
	                    }
	                }
	                actionMenu(findMedicine());
	            }
	        });

	        deletebutton.setOnAction(e -> {
	            String selectedMedicine = medicineComboBox.getValue();
	            if (selectedMedicine != null) {
	            	medicine.removeIf(medicinemenu -> medicinemenu.getName().equalsIgnoreCase(selectedMedicine));
	                try {
	                    outputFile();
	                } catch (IOException e1) {
	                    e1.printStackTrace();
	                }
	                actionMenu(findMedicine());
	            }
	        });

	        
	        //layout
	        HBox namehbox = new HBox();
	        HBox medhbox = new HBox();
	        namehbox.getChildren().addAll(medName, medicineComboBox);
	        medhbox.getChildren().addAll(updateMed, text);
	        namehbox.setSpacing(15);
	        medhbox.setSpacing(15);
	        namehbox.setAlignment(Pos.CENTER);
	        medhbox.setAlignment(Pos.CENTER);
	        
	        VBox vbox = new VBox();
	        vbox.getChildren().addAll(findMedicine(), namehbox, medhbox, deletebutton, updatebutton, button);
	        vbox.setSpacing(15);
	        vbox.setAlignment(Pos.CENTER);
	        borderpane.setCenter(vbox);
	    }
		
	}
		
	public VBox findMedicine() {
		//text
		Text header = new Text("Medicine");
		header.setFont(Font.font("Times New Roman",FontWeight.BOLD, FontPosture.REGULAR, 40));
		Text norecord = new Text("No record");
		norecord.setFont(Font.font("Times New Roman",FontWeight.BOLD, FontPosture.REGULAR, 20));
		
		Text nametxt = new Text("Name");
		Text mantxt = new Text("Manufacturer");
		Text exptxt = new Text("Expiry Date");
		Text costtxt = new Text("Cost");
		Text counttxt = new Text("Count");
		nametxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		mantxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		exptxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		costtxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		counttxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		nametxt.setStroke(Color.TOMATO);
		mantxt.setStroke(Color.TOMATO);
		exptxt.setStroke(Color.TOMATO);
		costtxt.setStroke(Color.TOMATO);
		counttxt.setStroke(Color.TOMATO);
		
		//layout
		GridPane gridpane = new GridPane();
		gridpane.add(nametxt, 0, 0);
		gridpane.add(mantxt, 1, 0);
		gridpane.add(exptxt, 2, 0);
		gridpane.add(costtxt, 3, 0);
		gridpane.add(counttxt, 4, 0);
		gridpane.setAlignment(Pos.CENTER);
		
		for(int col = 0; col < 5; col++) {
			gridpane.getColumnConstraints().add(new ColumnConstraints(150));
		}

		VBox vbox = new VBox();

		if(medicine.size() == 0){
			vbox.getChildren().addAll(header,new Text(""),gridpane,norecord);
		}
		
		else {
			for(int i = 0; i < medicine.size(); i++){
				medicine.get(i).findMedicine(gridpane, i+1);
			}
			vbox.getChildren().addAll(header,new Text(""),gridpane);
		}
		
		vbox.setSpacing(15);
		vbox.setAlignment(Pos.CENTER);
		return vbox;
	}		
	
	//lab
	//1 means add, 2 means delete
	public void labMenu(int action) throws IOException {
		//returnbutton
        Button button = new Button("Previous");
		setButtonFont(button);
		button.setPrefWidth(150);
		button.setAlignment(Pos.CENTER);
        button.setOnAction(e -> {
            actionMenu(labList());
        });
        
		if (action == 1) {
			//addnewlab
	        Lab newLab = new Lab();
	        VBox vbox = new VBox();
	        vbox.getChildren().addAll(newLab.newLab(laboratories, facilities), button);
	        vbox.setSpacing(15);
	        vbox.setAlignment(Pos.CENTER);
	        borderpane.setCenter(vbox);
	    } 
		else if (action == 2) {
	    	//create button and dropbox
	    	Label selectfacility= new Label("Select the facility:");
	        
	        ComboBox<String> facilityComboBox = new ComboBox<>();
	        for (Facility facility : facilities) {
	            facilityComboBox.getItems().add(facility.getFacility());
	        }

	        Button deletebutton = new Button("Delete");
			setButtonFont(deletebutton);
	        deletebutton.setPrefWidth(150);
	        deletebutton.setAlignment(Pos.CENTER);
	        
	        deletebutton.setOnAction(e -> {
	            String selectedFacility = facilityComboBox.getValue();
	            if (selectedFacility != null) {
	            	laboratories.removeIf(lab -> lab.getLab().equalsIgnoreCase(selectedFacility));
	                try {
	                    outputFile();
	                } catch (IOException e1) {
	                    e1.printStackTrace();
	                }
	                actionMenu(labList());
	            }
	        });
	        
	        //layout
		    GridPane gridpane = new GridPane();
		    gridpane.setHgap(10);
		    gridpane.setVgap(10);
		    gridpane.add(selectfacility, 0, 0);
		    gridpane.add(facilityComboBox, 1, 0);
		    gridpane.setHgap(10);
		    gridpane.setVgap(10);
		    gridpane.setPadding(new Insets(15));
		    gridpane.setAlignment(Pos.CENTER);

	        VBox vbox = new VBox();
	        vbox.getChildren().addAll(labList(), gridpane, deletebutton, button);
	        vbox.setSpacing(15);
	        vbox.setAlignment(Pos.CENTER);
	        borderpane.setCenter(vbox);
	    }
		
	}
		
	public VBox labList() {
		//text
		Text header = new Text("Lab");
		header.setFont(Font.font("Times New Roman",FontWeight.BOLD, FontPosture.REGULAR, 40));
		Text norecord = new Text("No record");
		norecord.setFont(Font.font("Times New Roman",FontWeight.BOLD, FontPosture.REGULAR, 20));
		
		Text labtxt = new Text("Lab");
		Text costtxt = new Text("Cost");
		labtxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		costtxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		labtxt.setStroke(Color.TOMATO);
		costtxt.setStroke(Color.TOMATO);
		
		//layout
		GridPane gridpane = new GridPane();
		gridpane.add(labtxt, 0, 0);
		gridpane.add(costtxt, 1, 0);
	    gridpane.setPadding(new Insets(15));
		gridpane.setAlignment(Pos.CENTER);
		
		for(int col = 0; col < 2; col++) {
			gridpane.getColumnConstraints().add(new ColumnConstraints(150));
		}

		VBox vbox = new VBox();

		if(laboratories.size() == 0){
			vbox.getChildren().addAll(header,new Text(""),gridpane,norecord);
		}
		else {
			for(int i = 0; i < laboratories.size(); i++){
				laboratories.get(i).labList(gridpane, i+1);
			}
			vbox.getChildren().addAll(header,new Text(""),gridpane);
		}
		
		vbox.setSpacing(15);
		vbox.setAlignment(Pos.CENTER);
		return vbox;
	}			
	
	//facility
	// 1 means add, 2 means delete
	public void facilityMenu(int action) throws IOException {
		//returnbutton
		Button button = new Button("Previous");
		setButtonFont(button); 
		button.setPrefWidth(150);
        button.setAlignment(Pos.CENTER);
		button.setOnAction(e->{
			actionMenu(showFacility());
		});

		if(action == 1){
			//addnew facility
			Facility newFacility = new Facility();
			VBox vbox = new VBox();
			vbox.getChildren().addAll(newFacility.newFacility(facilities),button);
			vbox.setSpacing(15);
			vbox.setAlignment(Pos.CENTER);
			borderpane.setCenter(vbox);
		}
		
		else if(action == 2){
			//create button and dropdownlist
			Label detelelabel = new Label("Select the facility to delete:");
			 
			ComboBox<String> facilityComboBox = new ComboBox<>();
			for (Facility facility : facilities) {
				facilityComboBox.getItems().add(facility.getFacility());
			}

			HBox hbox = new HBox();
			hbox.setSpacing(20);
			hbox.getChildren().addAll(detelelabel, facilityComboBox);
			hbox.setAlignment(Pos.CENTER);
			
	        Button deletebutton = new Button("Delete");
			setButtonFont(deletebutton); 
	        deletebutton.setPrefWidth(150);
	        deletebutton.setAlignment(Pos.CENTER);
	        
	        deletebutton.setOnAction(e -> {
	        	String selectedFacility = facilityComboBox.getValue();
	            if (selectedFacility != null) {
	            	facilities.removeIf(facility -> facility.getFacility().equals(selectedFacility));
	                try {
	                    outputFile();
	                } catch (IOException e1) {
	                    e1.printStackTrace();
	                }
	                actionMenu(showFacility());
		            }
	        });

	        //layout
	        VBox vbox = new VBox();
	        vbox.getChildren().addAll(showFacility(), hbox, deletebutton, button);
	        vbox.setSpacing(15);
	        vbox.setAlignment(Pos.CENTER);
	        borderpane.setCenter(vbox);
	    }
	}
		
	public VBox showFacility() {

		//text
		Text header = new Text("Facility");
		header.setFont(Font.font("Times New Roman",FontWeight.BOLD, FontPosture.REGULAR, 40));
		Text norecord = new Text("No record");
		norecord.setFont(Font.font("Times New Roman",FontWeight.BOLD, FontPosture.REGULAR, 20));
		
		Text text = new Text("Facility");
		text.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		text.setStroke(Color.TOMATO);
		
		GridPane gridpane = new GridPane();
		gridpane.add(text, 0, 0);
		gridpane.getColumnConstraints().add(new ColumnConstraints(150));
		
		VBox vbox = new VBox();

		if(facilities.size() == 0){
			vbox.getChildren().addAll(header,new Text(""),gridpane,norecord);
		}
		
		else {
			for (int i = 0; i < facilities.size(); i++) {
				Facility facility = facilities.get(i);
				facility.showFacility(gridpane, i + 1);
			}   
			vbox.getChildren().addAll(header,new Text(""),gridpane);
		}
		//layout
		vbox.setSpacing(15);
		vbox.setAlignment(Pos.CENTER);
		gridpane.setAlignment(Pos.CENTER);
		return vbox;
	}			
	
	//staffs
	//0 means no action, 1 means add, 2 means draw
	public void staffMenu(int action) throws IOException {
		
		if(action == 1){
			Staff newStaff = new Staff();
			//Previous button
			Button prevBtn = new Button("Previous");
			setButtonFont(prevBtn);
			prevBtn.setOnAction(e->{
				actionMenu(showStaffInfo());
			});
			prevBtn.setPrefWidth(150);
			VBox content = new VBox();
			content.setAlignment(Pos.CENTER);
			content.setSpacing(10);
			content.getChildren().addAll(newStaff.newStaff(staff),prevBtn);
			borderpane.setCenter(content);
		}
		else if(action == 2){
			Label instruction = new Label("Select an ID:");
			TextField inputId = new TextField();
			inputId.setPrefWidth(100);
			HBox rowA = new HBox();
			rowA.setSpacing(20);
			rowA.getChildren().addAll(instruction,inputId);
			rowA.setAlignment(Pos.CENTER);
			Button drawBtn = new Button("Draw");
			setButtonFont(drawBtn);
			drawBtn.setOnAction(e->{
				String drawId = inputId.getText();
				for(int i = 0; i < staff.size(); i++){
					if(staff.get(i).getId().equals(drawId)){
						staff.remove(i);
						try {
							outputFile();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					}
				}
				actionMenu(showStaffInfo());
			});
			drawBtn.setAlignment(Pos.CENTER);
			
			Button prevBtn = new Button("Previous");
			setButtonFont(prevBtn);
			prevBtn.setOnAction(e->{
				actionMenu(showStaffInfo());
			});
			VBox content = new VBox();
			prevBtn.setPrefWidth(150);
			drawBtn.setPrefWidth(150);
			content.getChildren().addAll(showStaffInfo(),rowA,drawBtn, prevBtn);
			content.setSpacing(10);
			content.setAlignment(Pos.CENTER);
			borderpane.setCenter(content);
		
		}
		
	}
		
	public VBox showStaffInfo() {
		GridPane record = new GridPane();
		Text object = new Text("Staff");
		object.setFont(Font.font("Times New Roman",FontWeight.BOLD, FontPosture.REGULAR, 40));
		Text norecord = new Text("No record");
	    norecord.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
		
		//Header of table record
		Text idtxt = new Text("ID");
		Text nametxt = new Text("Name");
		Text dtxt = new Text("Designation");
		Text sextxt = new Text("Sex");
		Text stxt = new Text("Salary");
		idtxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		nametxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		dtxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		sextxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		stxt.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
		idtxt.setStroke(Color.TOMATO);
		nametxt.setStroke(Color.TOMATO);
		dtxt.setStroke(Color.TOMATO);
		sextxt.setStroke(Color.TOMATO);
		stxt.setStroke(Color.TOMATO);
		record.add(idtxt, 0, 0);
		record.add(nametxt, 1, 0);
		record.add(dtxt, 2, 0);
		record.add(sextxt, 3, 0);
		record.add(stxt, 4, 0);
		
		for(int col = 0; col < 5; col++) {
			record.getColumnConstraints().add(new ColumnConstraints(150));
		}

		VBox vbox = new VBox();
		vbox.getChildren().addAll(object,new Text(""));

		if(staff.size() == 0){
			vbox.getChildren().add(record);
			vbox.getChildren().add(norecord);
		}
		else {
			for(int i = 0; i < staff.size(); i++){
				staff.get(i).showStaffInfo(record, i+1);
			}
			vbox.getChildren().add(record);
		}
		
		vbox.getChildren().add(new Text(""));
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
		record.setAlignment(Pos.CENTER);
		return vbox;
	}	
	
	//Read File
	public void readFile() throws IOException{
		//read Doctor

		File file = new File("Doctor.txt");
		if(file.exists()) {
			Scanner inputFile = new Scanner(file);
			while(inputFile.hasNext()) {
				String txt = inputFile.nextLine();
				Doctor doctor = new Doctor();
				if(txt.length() != 0){
					doctor.setId(txt);
					doctor.setName(inputFile.nextLine());
					doctor.setSpecialist(inputFile.nextLine());
					doctor.setWorkTime(inputFile.nextLine());
					doctor.setQualification(inputFile.nextLine());
					doctor.setRoom(Integer.parseInt(inputFile.nextLine()));
					doctors.add(doctor);
				}
			}
			inputFile.close();
		}
		
		//read Patient
		file = new File("Patient.txt");
		if(file.exists()) {
			Scanner inputFile = new Scanner(file);
			while(inputFile.hasNext()) {
				String txt = inputFile.nextLine();
				Patient patient = new Patient();
				if(txt.length() != 0){
					patient.setId(txt);
					patient.setName(inputFile.nextLine());
					patient.setDisease(inputFile.nextLine());
					patient.setSex(inputFile.nextLine());
					patient.setAdmitStatus(inputFile.nextLine());
					patient.setAge(Integer.parseInt(inputFile.nextLine()));
					patients.add(patient);
				}
			}
			inputFile.close();
		}
		
		//read medicines
		file = new File("Medicine.txt");
		if(file.exists()) {
			Scanner inputFile = new Scanner(file);
			while(inputFile.hasNext()) {
				String txt = inputFile.nextLine();
				Medicine medicineread = new Medicine();
				if(txt.length() != 0){
					medicineread.setName(txt);
					medicineread.setManufacturer(inputFile.nextLine());
					medicineread.setExpiryDate(inputFile.nextLine());
					medicineread.setCost(Integer.parseInt(inputFile.nextLine()));
					medicineread.setCount(Integer.parseInt(inputFile.nextLine()));
					medicine.add(medicineread);
				}
			}
			inputFile.close();
		}
		
		//read laboratories 
		file = new File("Laboratory.txt");
		if(file.exists()) {
			Scanner inputFile = new Scanner(file);
			while(inputFile.hasNext()) {
				String txt = inputFile.nextLine();
				Lab lab = new Lab();
				if(txt.length() != 0){
					lab.setLab(txt);
					lab.setCost(Integer.parseInt(inputFile.nextLine()));
					laboratories.add(lab);
				}
			}
			inputFile.close();
		}
		
		//read facilities
		file = new File("Facility.txt");
		if(file.exists()) {
			Scanner inputFile = new Scanner(file);
			while(inputFile.hasNext()) {
				String txt = inputFile.nextLine();
				Facility facility = new Facility();
				if(txt.length() != 0){
					facility.setFacility(txt);
					facilities.add(facility);
				}
			}
			inputFile.close();
		}
		
		//read staffs
		file = new File("Staff.txt");
		if(file.exists()) {
			Scanner inputFile = new Scanner(file);
			while(inputFile.hasNext()) {
				String txt = inputFile.nextLine();
				Staff staffss = new Staff();
				if(txt.length() != 0){
					staffss.setId(txt);
					staffss.setName(inputFile.nextLine());
					staffss.setDesignation(inputFile.nextLine());
					staffss.setSex(inputFile.nextLine());
					staffss.setSalary(Integer.parseInt(inputFile.nextLine()));
					staff.add(staffss);
				}
			}
			inputFile.close();
		}
	}
	
	//Overwrite File
	public void outputFile()throws IOException{
		PrintWriter outputFile = new PrintWriter(selection + ".txt");
		if(selection == "Doctor") {
			for(Doctor doctor : doctors) {
				outputFile.println(doctor.getId());
				outputFile.println(doctor.getName());
				outputFile.println(doctor.getSpecialist());
				outputFile.println(doctor.getWorkTime());
				outputFile.println(doctor.getQualification());
			}
		}
		else if(selection == "Patient") {
			for(Patient patient : patients) {
				outputFile.println(patient.getId());
				outputFile.println(patient.getName());
				outputFile.println(patient.getDisease());
				outputFile.println(patient.getSex());
				outputFile.println(patient.getAdmitStatus());
			}
		}
		else if(selection == "Medicine") {
			for(Medicine medicineout : medicine) {
				outputFile.println(medicineout.getName());
				outputFile.println(medicineout.getManufacturer());
				outputFile.println(medicineout.getExpiryDate());
				outputFile.println(medicineout.getCost());
				outputFile.println(medicineout.getCount());
			}
		}
		else if(selection == "Laboratory") {
			for(Lab lab : laboratories) {
				outputFile.println(lab.getLab());
				outputFile.println(lab.getCost());
			}
		}
		else if(selection == "Facility") {
			for(Facility facility : facilities) {
				outputFile.println(facility.getFacility());
			}
		}
		else if(selection == "Staff") {
			for(Staff staff : staff) {
				outputFile.println(staff.getId());
				outputFile.println(staff.getName());
				outputFile.println(staff.getDesignation());
				outputFile.println(staff.getSex());
				outputFile.println(Integer.toString(staff.getSalary()));
			}
		}
		outputFile.close();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}