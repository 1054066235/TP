package control;

import data.DataIO;
import data.ListOfCourses;
import data.ListOfStaff;

public class Model {	//Model is a Singleton
	private ListOfCourses courses= null;
	private ListOfStaff staff=null;
	private static Model model=new Model();
	
	private Model() {
	}
	protected static Model instance() throws Exception{
		model.read();
		return model;
	}
	
//------------------------------------------------------------------------
	private void read() throws Exception{
		this.courses=DataIO.readCourses();
		
		this.staff=DataIO.readStaff();
		
	}
	protected void write() throws Exception{
		DataIO.write(this.courses,this.staff);
	}
	protected String showCourses() {
		String courseNames=courses.showCourses();//this String includes all course names
		return courseNames;
	}
	protected String showLabs(String courseName) {
		String labNames=courses.showLabs(courseName); //this String includes all lab names of a course
		return labNames;                     
	}
	protected void addLab(String courseName, String labName, int numberOfStaff, String training) {
		courses.addlab(courseName,labName,numberOfStaff,training);
		
	}
	protected boolean hasCourse(String courseName) {
		if(courses.hasCourse(courseName)) {
			return true;
		}
		return false;
	}
	protected void subLab(String courseName, String labName) throws Exception {
		courses.subLab(courseName,labName);
		
	}
	protected String showStaff() {
		String staffNames=staff.showStaff(); //this String includes all staff members' names
		return staffNames;  
		
	}
	protected void addStaff(String staffName) throws Exception {
		staff.addStaff(staffName);
		
	}
	protected void subStaff(String staffName) throws Exception {
		staff.subStaff(staffName);
		
	}
	protected void addTrainingToStaff(String staffName, String training) throws Exception {
		staff.addTrainingToStaff(staffName,training);
		
	}
	protected String showTraining(String staffName) {
		String str = staff.showTraining(staffName);
		return str;
	}
	protected boolean hasStaff(String staffName) {
		if(staff.hasStaff(staffName)) {
			return true;
		}
		return false;
	}
	protected void subTrainingFromStaff(String staffName, String trainingToCancel) throws Exception {
		staff.subTrainingFromStaff(staffName, trainingToCancel);
		
	}	
	
	
	
}
