package data;

import java.util.ArrayList;

/**
 * @Author Zhe Miao Guid: 2598803M
 * @Date 2021/5/16 23:45
 * @Version 1.0
 */
public class Staff {
    private String name;
    private ArrayList<String> training;

    protected Staff() {
    }

    protected Staff(String name, ArrayList<String> training) {
        this.name = name;
        this.training = training;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected String getName() {
        return name;
    }

    protected ArrayList<String> getTraining() {
        return training;
    }

    @Override
    public String toString() {
        if (training == null){
            return name;
        }
        return name + ":" + helperfForToString();
    }

    private String helperfForToString(){
        String str = "";
        for (int i = 0; i < training.size(); i++) {
            if (i == training.size()-1){
                str += training.get(i);
            }else {
                str += training.get(i) + ",";
            }
        }
        return str;
    }
}
