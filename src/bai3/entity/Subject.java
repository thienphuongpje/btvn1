package bai3.entity;

import model.getInput;
import java.util.Scanner;

public class Subject implements getInput {

    public enum SubjectType {
        DAI_CUONG("ĐẠI CƯƠNG "),
        CO_SO_NGANH("CƠ SỞ NGÀNG "),
        CHUYEN_NGANH("CHUYÊN NGÀNh ");

        private String value;

        SubjectType(String value) {
            this.value = value;
        }
    }
    private static int numbers = 100;
    private int subjectNo;
    private String subjectName;
    private int credits;
    private SubjectType type;

    public Subject(){

    }

    public Subject(String subjectName, int credits, SubjectType type) {
        this.subjectName = subjectName;
        this.credits = credits;
        this.type = type;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }


    public int getSubjectNo() {
        return subjectNo;
    }

    public void setSubjectNo(int subjectNo) {
        this.subjectNo = subjectNo;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getType() {
        return type.value;
    }


    public void setType(SubjectType type) {
        this.type = type;
    }

    @Override
    public void getInputInfo() {
        Subject.numbers++;
        this.setSubjectNo (Subject.numbers);
        System.out.println("Nhập tên môn học: ");
        this.setSubjectName(new Scanner(System.in).nextLine());
        System.out.println("Nhập số tín chỉ môn học: ");
        this.setCredits(new Scanner(System.in).nextInt());
    }

    public void setSubjectType(){
        System.out.println("Lựa chọn loại môn học là một trong các loại sau đây\n" +
                "1- Đại cương \n" +
                "2- Cơ sở ngàng \n" +
                "3- Chuyên Ngành");
        int i = new Scanner(System.in).nextInt();
        while (true){
            if (i<=3 && i>0) break;
            else System.out.println(" Nhập chưa đúng hãy nhập lại! ");

        }
        switch (i){
            case 1:
                this.setType(SubjectType.DAI_CUONG);
                break;
            case 2:
                this.setType(SubjectType.CO_SO_NGANH);
                break;
            case 3:
                this.setType(SubjectType.CHUYEN_NGANH);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Subject){
            Subject annother = (Subject) obj;
            return ((Subject) annother).getSubjectName().toUpperCase().equals(this.subjectName.toUpperCase());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int subjectHashCode=0;
        for (char c:this.getSubjectName().toUpperCase().toCharArray()){
            subjectHashCode += (int)c;
        }
        return super.hashCode();
    }
}
