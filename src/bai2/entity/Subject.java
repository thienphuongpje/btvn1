package bai2.entity;

public class Subject {
    private int subjectCode;
    private String subjectName;
    private int totalCredits;
    private int theoryCredits;
    private int practiceCredits;
    private double creditPrice;
    private int classNumbers;

    public Subject(){

    }

    public Subject(String subjectName, int totalCredits, int theoryCredits, double creditPrice) {
        this.subjectName = subjectName;
        this.totalCredits = totalCredits;
        this.theoryCredits = theoryCredits;
        this.creditPrice = creditPrice;
        this.practiceCredits = totalCredits-theoryCredits;
    }

    public int getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(int subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public int getTheoryCredits() {
        return theoryCredits;
    }

    public void setTheoryCredits(int theoryCredits) {
        this.theoryCredits = theoryCredits;
    }

    public int getpracticeCredits() {
        return practiceCredits;
    }

    public double getCreditPrice() {
        return creditPrice;
    }

    public void setCreditPrice(double creditPrice) {
        this.creditPrice = creditPrice;
    }

    public int getClassNumbers() {
        return classNumbers;
    }

    public void setClassNumbers(int classNumbers) {
        this.classNumbers = classNumbers;
    }


}
