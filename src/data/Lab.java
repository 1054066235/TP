package data;

public class Lab {
	
    private String name;
    private int numberOfStaff;
    private String training;

    protected Lab() {
    }

    protected Lab(String name, int numberOfStaff, String training) {
        this.name = name;
        this.numberOfStaff = numberOfStaff;
        this.training = training;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected int getNumberOfStaff() {
        return numberOfStaff;
    }

    protected void setNumberOfStaff(int numberOfStaff) {
        this.numberOfStaff = numberOfStaff;
    }

    protected String getTraining() {
        return training;
    }

    protected void setTraining(String training) {
        this.training = training;
    }

	public String toString() {
        return name + "," + numberOfStaff + "," + training;
    }
}