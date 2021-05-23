package data;

import java.util.ArrayList;

/**
 * @Author Zhe Miao Guid: 2598803M
 * @Date 2021/5/16 23:47
 * @Version 1.0
 */
public class Course {
    private String name;
    private ArrayList<Lab> labList;

    protected Course() {
    }

    protected Course(String name, ArrayList<Lab> labList) {
        this.name = name;
        this.labList = labList;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected ArrayList<Lab> getLabList() {
        return labList;
    }

    protected void setLabList(ArrayList<Lab> labList) {
        this.labList = labList;
    }

	public String toString() {
        if(labList != null ){
            return name + ":" + helperForToString();
        }
        return name;
    }

    private String helperForToString(){
        String str = "";
        for (int i = 0; i < labList.size(); i++) {
            if(i == labList.size() - 1){
                str += labList.get(i);
            }else {
                str += labList.get(i) + ";";
            }
        }
        return str;
    }

}