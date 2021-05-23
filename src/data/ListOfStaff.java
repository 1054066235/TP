package data;

import java.util.ArrayList;

public class ListOfStaff {
	
	private ArrayList<Staff> staffList;

	protected ListOfStaff() {
		staffList = new ArrayList<>();
	}


	protected ArrayList<Staff> getStaffList() {
		return staffList;
	}


	public void addStaff(String staffName) throws Exception {
		if(staffName.equals("")) {//throws exception if user entered space only
			throw new Exception();
		}
		Staff s = new Staff(staffName, new ArrayList<String>());
		this.getStaffList().add(s);	
	}


	public void subStaff(String staffName) throws Exception {
		int numberOfStaff=staffList.size();
		for (int i = 0; i < staffList.size(); i++) {
			if(staffList.get(i).getName().equals(staffName)) {
				staffList.remove(i);
			}
		}
		if(numberOfStaff==staffList.size()){// if user entered a name that does not exist, throws exception
			throw new Exception();
		}
	}


	public void addTrainingToStaff(String staffName, String training) throws Exception {
		if(!hasStaff(staffName)) {//if the staff name entered by the user does not exist, throws exception
			throw new Exception();
		}
		else if(training.equals("")){
			throw new Exception();//throws exception if user entered space only for training
		}
		else {
			for (int i = 0; i < staffList.size(); i++) {
				if(staffList.get(i).getName().equals(staffName)) {
					staffList.get(i).getTraining().add(training);
				}
			}
		}
	}


	public boolean hasStaff(String staffName) {
		for (int i = 0; i < staffList.size(); i++) {
			if(staffList.get(i).getName().equals(staffName)) {
				return true;
			}
		}
		return false;
	}


	public void subTrainingFromStaff(String staffName, String trainingToCancel) throws Exception {
		for (int i = 0; i < staffList.size(); i++) {
			if(staffList.get(i).getName().equals(staffName)) {
				int pos = staffList.get(i).getTraining().indexOf(trainingToCancel);
				if(pos == -1) {
					throw new Exception();//throws exception if user entered training that does not exist
				}
				staffList.get(i).getTraining().remove(pos);
			}
		}
	}


	public String showStaff() {
		String str = "";
		if(staffList.size()==0) {
			str="no staff";
		}
		for (int i = 0; i < staffList.size(); i++) {
			str += staffList.get(i).toString() + "\r\n";
		}
		return str;
	}
	
	public String showTraining(String staffName) {
		String str = "";
		  for (int i = 0; i < staffList.size(); i++) {
		   if(staffList.get(i).getName().equals(staffName)) {
		    if(staffList.get(i).getTraining().size() == 0) {
		     str = "There is no training of this staff";
		    }else {
		     String[] subTraining = staffList.get(i).toString().split(":");
		     str += subTraining[1];
		    }
		   }
		  }
		  return str;
	}
}
