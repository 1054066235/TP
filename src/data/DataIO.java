package data;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author Zhe Miao Guid: 2598803M
 * @Date 2021/5/20 17:49
 * @Version 1.0
 */

public class DataIO {
    public static ListOfCourses readCourses() throws IOException {
        ListOfCourses listOfCourses = new ListOfCourses();
        ArrayList<Course> courseList = listOfCourses.getCourseList();
        String root = System.getProperty("user.dir");
        String FileName= "course.txt";
        String filePath = root+File.separator+FileName;
        FileReader fr = new FileReader(filePath);
        Scanner s = new Scanner(fr);
        while(s.hasNextLine()) {
            String line = s.nextLine();
            String[] tokens = line.split(":");
            String name = tokens[0]; //such as SE
            Course course = null;
            if(tokens.length == 1){
                course = new Course(name, null);
            }else {
                String labs = tokens[1]; //such as lab1,100,training1;lab2,200,training2;lab3,300,training3
                ArrayList<Lab> labList = new ArrayList<>();
                String[] tokens1 = labs.split(";");
                //make lab information read into a Lab object，such as lab1,100,training1
                for (int i = 0; i < tokens1.length; i++) {
                    String[] lab = tokens1[i].split(",");
                    String labName = lab[0];
                    int numberOfStaff = Integer.valueOf(lab[1]);
                    String training = lab[2];
                    Lab lab1 = new Lab(labName, numberOfStaff, training);
                    labList.add(lab1);
                }
                course = new Course(name, labList);
            }
            courseList.add(course);
        }
        fr.close();
        System.out.println(listOfCourses);
        return listOfCourses;
    }

    public static ListOfStaff readStaff() throws IOException {
        ListOfStaff listOfStaff = new ListOfStaff();
        //staffList是用来将staff.txt中的每行记录封装到每个Staff对象中
        ArrayList<Staff> staffList = listOfStaff.getStaffList();//null 这样就不是null了
        FileReader fr = null;
        String root = System.getProperty("user.dir");
        String FileName= "staff.txt";
        String filePath = root+ File.separator+FileName;
        fr = new FileReader(filePath);
        Scanner s = new Scanner(fr);
        while(s.hasNextLine()) {
            String line = s.nextLine();
            //依据：来分割一行staff信息，：前是name，：后是training
            String[] tokens = line.split(":");
            String name = tokens[0]; //例如eric
            Staff staff = null;
            if(tokens.length == 1){
                staff = new Staff(name, new ArrayList<>());
            }else {
                String training = tokens[1]; //例如training1,training2,training3
                //通过，来分割training变量，得到每个training字符串
                String[] tokens1 = training.split(",");
                ArrayList<String> trainingList = new ArrayList<>();
                for (int i = 0; i < tokens1.length; i++) {
                    trainingList.add(tokens1[i]);
                }
                staff = new Staff(name, trainingList);
            }
            staffList.add(staff);
        }
        fr.close();
        System.out.println(listOfStaff);
        return listOfStaff;
    }

    public static void write(ListOfCourses listOfCourses, ListOfStaff listOfStaff) throws IOException {
        //先写staff
        ArrayList<Staff> staffList = listOfStaff.getStaffList();
        FileWriter fw = null;
        String root = System.getProperty("user.dir");
        String FileName= "staff.txt"; //先写成staff1.txt做测试，最后改成staff.txt来覆盖原文件
        String filePath = root+File.separator+FileName;
        fw = new FileWriter(filePath);
        for (int i = 0; i < staffList.size(); i++) {
            String newLine = staffList.get(i).toString() +  "\n";
            fw.write(newLine);
        }
        fw.close();

        //写course
        ArrayList<Course> courseList = listOfCourses.getCourseList();
        fw = null;
        root = System.getProperty("user.dir");
        FileName= "course.txt"; //同上
        filePath = root+File.separator+FileName;
        fw = new FileWriter(filePath);
        for (int i = 0; i < courseList.size(); i++) {
            String newLine = courseList.get(i).toString() + "\n";
            fw.write(newLine);
        }
        fw.close();
    }


}
