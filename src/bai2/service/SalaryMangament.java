package bai2.service;

import bai2.entity.Teacher;
import bai2.entity.Subject;

public class SalaryMangament {

    private Teacher teacher;
    private bai2.entity.Subject[] subjects;
    private double salary;
    private int maxClass = 3;
    private int maxCredits = 200;

    SalaryMangament(){
    }

    public SalaryMangament(Teacher teacher) {
        this.teacher = teacher;
        this.subjects = new Subject[0];
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject[] getSubjects() {
        return subjects;
    }

    public int getMaxClass() {
        return maxClass;
    }

    public void setMaxClass(int maxClass) {
        this.maxClass = maxClass;
    }

    public int getMaxCredits() {
        return maxCredits;
    }

    public void setMaxCredits(int maxCredits) {
        this.maxCredits = maxCredits;
    }

    /*
        Thiết lập phương thức đăng ký nhận lớp cho giáo viên.
     */
    public void takeClass(Subject subject, int n){
        if (subjects.length==0){
            this.subjects = new Subject[1];
            this.subjects[0] = subject;
            subject.setClassNumbers(n);
        }else {
            if (this.calculateTotalCredit()>200){
                System.out.println("Giảng viên này đã nhận tối đa số lớp có thể");
            }
            else {
                if (n>maxClass){
                    System.out.println("Đã đăng ký số lớp vượt quá quy định");
                    n = 3;
                }
                boolean exist = false;
                for (Subject i : this.subjects) {
                    if (i.getSubjectName().toUpperCase().equals(subject.getSubjectName().toUpperCase())){
                        int currentClassNumbers = i.getClassNumbers();
                        i.setClassNumbers(i.getClassNumbers()+n);
                        exist = true;
                        if (i.getClassNumbers()>maxClass){
                            System.out.printf("Tổng số lớp dạy đã vượt quá quy định, số lớp dạy thiết lập về %d \n",maxClass);
                            i.setClassNumbers(maxClass);
                        }
                        if (i.getTotalCredits()>200){
                            i.setClassNumbers(currentClassNumbers);
                            System.out.println("Đã đăng ký vượt quá số tín chỉ cho phép, hãy đăng ký lại");
                            System.out.printf("Số tín chỉ còn có thể đăng ký: %d",200 - i.getTotalCredits());
                        }
                        break;
                    }

                }
                if (!exist){
                    if ((this.calculateTotalCredit()+subject.getTotalCredits())>200){
                        System.out.println("Không thể đăng ký nhận lớp, số tín chỉ của giáo viên vượt quá quy định");
                    }else {
                        Subject[] newSubjects = new Subject[this.subjects.length + 1];
                        for (int i = 0; i < this.subjects.length; i++) {
                            newSubjects[i] = this.subjects[i];
                        }
                        newSubjects[this.subjects.length] = subject;
                        newSubjects[this.subjects.length].setClassNumbers(n);
                        this.subjects = newSubjects;
                    }
                }
            }
        }
    }

    public int calculateTotalClass(){
        int classSum = 0;
        for (Subject i:this.subjects){
            classSum+= i.getClassNumbers();
        }
        return classSum;
    }
    /*
        Hàm tính tổng số tín chỉ hiện tại của một giáo viên
     */
    public int calculateTotalCredit(){
        int creditSum = 0;
        for (Subject i : this.subjects){
            creditSum += i.getTotalCredits()*i.getClassNumbers();
        }
        return  creditSum;
    }


}
