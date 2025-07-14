# Hospital Management System (HMS)
The main objective of the HMS is to manage activities in a hospital, including patients, doctors, administrative staff, medicine, facilities, and laboratories. 
Staff:
Staff is the class for representing a staff object. The Staff class contains:
• Four String data fields named id, name, designation, and sex. A field named
salary that stores salary (int) of the staff.
• A method named newStaff()that prompts user to enter staff’s id, name, designation,
sex and salary.
• A method name showStaffInfo() to show the content of the staff as:
[id] [name] [designation] [sex] [salary]
Doctor:
Doctor is the class for representing a doctor object. The Doctor class contains:
• Five String data fields named id, name, specialist, workTime, and
qualification. A field named room that stores room number (int) of the doctor.
• A method named newDoctor()that prompts user to enter doctor’s id, name,
specialization, work time, qualification and room number.
5
• A method name showDoctorInfo() to show the content of the doctor as:
[id] [name] [specialist] [work time] [qualification] [Room No.]

Patient:
Patient is the class for representing a patient object. The Patient class contains:
• Five String data fields named id, name, disease, sex, and admitStatus. A
field named age that stores age (int) of the patient.
• A method named newPatient()that prompts user to enter patient’s id, name, disease,
sex, admit status and age.
• A method name showPatientInfo() to show the content of the patient as:
[id] [name] [disease] [sex] [admitStatus] [age]
Medicine:
Medicine is the class for representing a medicine object. The Medicine class contains:
• Three String data fields named name, manufacturer, and expiryDate. Two
int data fields named cost and count.
• A method named newMedicine()that prompts user to enter name, manufacturer, expiry
date, cost and number of unit.
• A method name findMedicine() to show the content of the medicine as:
[name] [manufacturer] [expiry date] [cost]
Lab:
Lab is the class for representing a lab object. The Lab class contains:
• A String data fields named lab. A data fields named cost that stores cost (int) of
the facility.
• A method named newLab()that prompts user to enter facility and cost.
• A method name labList() to show the content of the lab as:
[lab] [cost]
Facility:
Facility is the class for representing a facility object. The Facility class contains:
• A String data fields named facility.
• A method named newFacility()that prompts user to enter facility.
• A method name showFacility() to show the content of the facility as:
[facility]
HospitalManagement:
The HospitalManagement class is the application class which controls the flow of the HMS,
displays messages and gets inputs from the users. It should contain the main() method and
statements to:
5
• Declare and create an array of Doctor objects. Number of doctors in the hospital is 25.
• Declare and create an array of Patient objects that can hold 100 values.
• Declare and create an array of Lab objects that can hold 20 values.
• Declare and create an array of Facility objects that can hold 20 values.
• Declare and create an array of Medicine objects that can hold 100 values.
• Declare and create an array of Staff objects that can hold 100 values.
• Provide existing lists for doctors, patients, labs, facilities, medicine, and staffs, with a
minimum number of three objects for each class.
