package data;

import java.util.ArrayList;

public class ListOfCourses {
	
	private ArrayList<Course> courseList;

	protected ListOfCourses() {
		courseList = new ArrayList<>();
	}

	protected ArrayList<Course> getCourseList() {
		return courseList;
	}

	public boolean hasCourse(String courseName) {
		Course[] cs = new Course[this.getCourseList().size()];
		for (int i = 0; i < cs.length; i++) {
			cs[i] = this.getCourseList().get(i);
			if(cs[i].getName().equals(courseName)) {
				return true;
			}
		}
 		return false;
	}

	public void addlab(String courseName, String labName, int numberOfStaff, String training) {
		   for (int i = 0; i < courseList.size(); i++) {
		      if (courseName.equals(courseList.get(i).getName())){
		         Course course = courseList.get(i);
		         Lab lab = new Lab(labName,numberOfStaff,training);
		         if (course.getLabList() == null){
		            ArrayList<Lab> labList = new ArrayList<>();
		            labList.add(lab);
		            course.setLabList(labList);
		         }else{
		            course.getLabList().add(lab);
		         }
		         break;
		      }
		   }
		}

	public void subLab(String courseName, String labName) throws Exception {
		   Course course = null;
		   for (int i = 0; i < courseList.size(); i++) {// find the intended course
		      if (courseName.equals(courseList.get(i).getName())){
		         course = courseList.get(i);
		         break;
		      }
		   }
		   ArrayList<Lab> labList = course.getLabList();
		   int numberOfLabs=labList.size();
		   for (int i1 = 0; i1 < numberOfLabs; i1++) {
		      if(labName.equals(labList.get(i1).getName())){
		         labList.remove(i1);
		         break;
		      }
		   }
		   if (numberOfLabs==labList.size()) {
			   throw new Exception();//if the lab entered does not exist, throws exception
		   }
	}

	public String showCourses() {
		String str = "";
		Course[] cs = new Course[this.getCourseList().size()];
		for (int i = 0; i < cs.length; i++) {
			cs[i] = this.getCourseList().get(i);
			str = str + cs[i].getName() + "\r\n";
		}
		return str;
	}

	public String showLabs(String courseName) {
		String str = "";
		Course[] cs = new Course[this.getCourseList().size()];
		for (int i = 0; i < cs.length; i++) {
		   cs[i] = this.getCourseList().get(i);
		   if(cs[i].getName().equals(courseName)) {
			   if(cs[i].getLabList()==null) {
				   str="no lab";
			   }
			   else if(cs[i].getLabList().size()==0) {
				   str="no lab";
			   }
			   else {
			    Lab[] ls = new Lab[cs[i].getLabList().size()];		 
			    for (int j = 0; j < ls.length; j++) {         
				    ls[j] = cs[i].getLabList().get(j);
				    str = str + ls[j].toString() + ";\n";
			    }
			  }
		    } 
		  }
		  return str;
		 }
}
