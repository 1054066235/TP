package control;

import javax.swing.JOptionPane;

public class Control { //Control is a Singleton

	private Model model=null;
	private static Control control=new Control();
	
	private Control() {
	}
	public static Control instance() {	//add the single Model object to the single Control object
		try {
			control.model=Model.instance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return control;
	}
	
	public void openingPage() {//at the beginning the user has 3 options
		String[] options = {"I am Course Director","I am Administrator","Save and Exit"};
		String op=(String)JOptionPane.showInputDialog
		(null,"What do you want to do?","",JOptionPane.QUESTION_MESSAGE,null,options,options[2]);
		if(op!=null) {
			switch(op) {
			case "Save and Exit": 
				this.exit();
				return;
			case "I am Course Director":
				this.director();
				return;
			case "I am Administrator":
				this.admin();
				return;
			}
		}
		else this.exit();
	}

//-----------------------------Methods related to course director-------------------------------------------------	
	private void director() {
		// if the user has chosen "I am Course Director", 
		//he only can choose from courses that are already written in the file. 
		//if there is no information in the course.txt file, the program does not make sense
		String[] options = {"Choose a course"};
		String op=(String)JOptionPane.showInputDialog
		(null,"What do you want to do?","",JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
		if(op!=null) {
			switch(op) {
			case "Choose a course": 
				this.chooseCourse();
				return;
			}
		}
		else this.openingPage();
	}
	private void chooseCourse() {//let user enter a course name from the courses in the file
		String courses=this.model.showCourses();
		String op=JOptionPane.showInputDialog(null,"Which course do you want to deal with?\n"+courses);
		if(op!=null) {
			this.dealWithCourse(op.trim());
		}
		else this.director();
	}
	private void dealWithCourse(String course) {//after the user entered a course name, he has 3 further options
		if(this.model.hasCourse(course)) {
			String[] options = {"View labs","Add a lab","Cancel a lab"};
			String op=(String)JOptionPane.showInputDialog
			(null,"What do you want to do with course "+course+"?","",JOptionPane.QUESTION_MESSAGE,null,options,options[2]);
			if(op!=null) {
				switch(op) {
				case "View labs": 
					this.showLabs(course);
					return;
				case "Add a lab":
					this.addLab(course);
					return;
				case "Cancel a lab":
					this.subLab(course);
					return;
				}
			}
			else this.chooseCourse();
		}
		else {// if invalid course name is entered, ask the user to enter again
			JOptionPane.showMessageDialog(null,"Enter valid course name!");
			this.chooseCourse();
		}
	}

	private void subLab(String course) {//delete the lab that the user entered
		String labs=this.model.showLabs(course);
		String labName=JOptionPane.showInputDialog(null,course+":\n"+labs+"\nEnter the name of the lab you want to cancel");
		if(labName!=null) {
			try {
				this.model.subLab(course,labName.trim());
				this.dealWithCourse(course);
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null,"Enter valid lab name!");
				this.subLab(course);
			}
		}
		else this.dealWithCourse(course);
	}
	private void addLab(String course) {//add the lab that the user entered
		String labName=JOptionPane.showInputDialog(null,"Enter lab name");
		if(labName==null) this.dealWithCourse(course);//return to choose what to do with the course selected
		String training=JOptionPane.showInputDialog(null,"Enter the training that the staff members are required to have");
		if(training==null) this.addLab(course);//return to the beginning of adding a lab
		int numberOfStaff=-1;
		while(true) {
			String numberOfStaffString=JOptionPane.showInputDialog(null,"Enter the number of staff members required for the lab");
			if(numberOfStaffString==null) this.addLab(course);//return to the beginning of adding a lab
			try{
				numberOfStaff=Integer.parseInt(numberOfStaffString.trim());
				break;
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null,"Enter a number!");
			}
		}
		try {
			this.model.addLab(course,labName.trim(),numberOfStaff,training.trim());
			this.dealWithCourse(course);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"Enter valid lab information!");
			this.addLab(course);
		}
	}
	private void showLabs(String course) {
		String courseInfo=this.model.showLabs(course);
		JOptionPane.showMessageDialog(null,courseInfo);
		this.dealWithCourse(course);
	}
	
//-----------------------------Methods related to administrator-------------------------------------------------	
	private void admin() {// if the user has chosen "I am Administrator", he has 5 further options
		String[] options = 
			{"View all staff members","Add a staff member","Remove a staff member","Add training to a staff member","Delete training from a staff member"};
		String op=(String)JOptionPane.showInputDialog
		(null,"What do you want to do?","",JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
		if(op!=null) {
			switch(op) {
			case "View all staff members": 
				this.showStaff();
				return;
			case "Add a staff member": 
				this.addStaff();
				return;
			case "Remove a staff member": 
				this.subStaff();
				return;
			case "Add training to a staff member": 
				this.addTraining();
				return;
			case "Delete training from a staff member": 
				this.subTraining();
				return;
			}
		}
		else this.openingPage();
	}
	
	private void subTraining() {// ask the user to enter a staff member's name and the training that he wants to delete
		String existingTraining="";
		String staffName=JOptionPane.showInputDialog(null,"Enter the name of the staff member you want to deal with");
		if(staffName==null) this.admin();
		if(this.model.hasStaff(staffName)) {
		existingTraining=this.model.showTraining(staffName);
		}
		else {//if the user entered an invalid name, restart subTraining
			JOptionPane.showMessageDialog(null,"Enter the name of an existing staff member!");
			this.subTraining();
		}
		
		String trainingToCancel=
			JOptionPane.showInputDialog(null,"Here is the training the staff member already has:\n"+existingTraining+"\nEnter the training you want to delete");
		if(trainingToCancel==null) this.subTraining();// if the user clicked 'cancel', restart subTraining
		try {
			this.model.subTrainingFromStaff(staffName.trim(),trainingToCancel.trim());
			this.admin();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"Enter existing training of "+staffName+"!");
			this.subTraining();
		}
		
	}
	private void addTraining() {//ask the user to enter a staff member's name and the training that he wants to add
		String staffName=JOptionPane.showInputDialog(null,"Enter the name of the staff member you want to deal with");
		if(staffName==null) this.admin();
		String training=JOptionPane.showInputDialog(null,"Enter the training you want to add");
		if(training==null) this.addTraining();
		try {
			this.model.addTrainingToStaff(staffName.trim(),training.trim());
			this.admin();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Enter valid information!");
			this.addTraining();
		}
	}
	private void subStaff() {//ask the user to enter the staff member's name that he wants to delete
		String staffName=JOptionPane.showInputDialog(null,"Enter the name of the staff member you want to remove");
		if(staffName==null) this.admin();
		try {
			this.model.subStaff(staffName.trim());
			this.admin();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Enter the name of an existing staff member!");
			this.subStaff();
		}
	}
	private void addStaff() {//ask the user to enter a staff member's name that he wants to add
		String staffName=JOptionPane.showInputDialog(null,"Enter the name of the new staff member");
		if(staffName==null) this.admin();
		try {
			this.model.addStaff(staffName.trim());
			this.admin();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Enter valid name!");
			this.addStaff();
		}
}
	private void showStaff() {//show the information of all staff members on the window
		String staffInfo=this.model.showStaff();
		JOptionPane.showMessageDialog(null,staffInfo);
		this.admin();
	}
//------------------------------------------------------------------------------
	private void exit() {//save the changes made in the file and exit the program
		try {
			this.model.write();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	

	public static void main(String[] args) {

	}
}
