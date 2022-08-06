package bai3.entity;

import model.GetFirstName;
import model.People;
import model.getInput;

import java.io.*;
import java.util.Scanner;

public class Student extends People implements getInput, GetFirstName {
    private static int studentID = 2014000;
    private int id;
    private String classID;

    public Student() {

    }

    public Student(String name, String address, String phoneNumber, String classID) {
        this.setName(name);
        this.setAddress(address);
        this.setPhoneNumber(phoneNumber);
        this.setClassID(classID);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    @Override
    public void getInputInfo() {
        Student.studentID++;
        this.setId (Student.studentID);
        System.out.println("Nhập vào tên của sinh viên: ");
        this.setName(new Scanner(System.in).nextLine());
        System.out.println("Nhập vào địa chỉ của sinh viên: ");
        this.setAddress(new Scanner(System.in).nextLine());
        System.out.println("Nhập vào SĐT của sinh viên: ");
        this.setPhoneNumber(new Scanner(System.in).nextLine());
        System.out.println("Nhập vào mã lớp của sinh viên: ");
        this.setClassID (new Scanner(System.in).nextLine());
    }

    public void getAutoInputInfo(String a, String b, String c, String d) throws IOException {
        Student.studentID++;
        this.setId (Student.studentID);
//        System.out.println("Nhập vào tên của sinh viên: ");
        this.setName(a);
//        System.out.println("Nhập vào địa chỉ của sinh viên: ");
        this.setAddress(b);
//        System.out.println("Nhập vào SĐT của sinh viên: ");
        this.setPhoneNumber(c);
//        System.out.println("Nhập vào mã lớp của sinh viên: ");
        this.setClassID (d);
    }

    @Override
    public String getFirstName(){
        String firstName;
        String[] name = this.getName().split("\s");
        firstName = name[name.length-1];
        return firstName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "classID='" + classID + '\'' +
                "name=" + getName()+
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student){
            Student another = (Student) obj;
            if (this.getName().toUpperCase().equals(another.getName().toUpperCase()))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashName=0;
        for (int i=0;i<this.getName().length();i++) {
            hashName +=(int)this.getName().toUpperCase().charAt(i);
        }
        return hashName;
    }
}
