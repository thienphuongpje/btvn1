package bai3.entity;

import Main.QLSVMain;
import Tool.TableRow;
import qlgpa.logichandle.SubjectService;

import java.util.*;

public class GPAManagement {
    private Student student;
    private ArrayList<StudyResult> studentResult = new ArrayList<> ();

    public GPAManagement() {
    }

    public GPAManagement(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }


    public ArrayList<StudyResult> getStudentResult() {
        return studentResult;
    }

    public static void addSubjectResult(){
        System.out.println ("Nhap vao ma sinh vien: ");
        int studentID = new Scanner (System.in).nextInt ();
        int score = 0;
        boolean stExist = false;
        boolean sbExit = false;
        Subject subject = new Subject ();
        for (GPAManagement i : QLSVMain.studentResultList){
            if (i.getStudent().getId ()==studentID){
                stExist = true;
                System.out.println ("Nhap ma mon hoc");
                int subjectCode = new Scanner (System.in).nextInt ();
                for (Subject j : SubjectService.subjects){
                    if (j.getSubjectNo ()==subjectCode){
                        sbExit = true;
                        subject = j;
                        System.out.println ("Nhap diem thi cua mon hoc: ");
                        score = new Scanner (System.in).nextInt ();
                    }
                }
                if (sbExit){
                    boolean duplicate = false;
                    for (StudyResult j : i.studentResult){
                        if(j.getSubject ().getSubjectNo ()==subjectCode) {
                            System.out.println ("Sinh vien da hoc mon nay roi! ");
                            duplicate = true;
                            break;
                        }
                    }
                    if (!duplicate){
                        StudyResult result = new StudyResult (subject, score);
                        i.studentResult.add (result);
                    }
                }else System.out.println ("mon hoc nay khong co trong danh sach");
                break;
            }
        }
        if (!stExist) {
            System.out.println (" Sinh vien nay khong co trong danh sach ");
        }
    }

    public double getGPA(){
        int totalScore = 0;
        int totalcredits = 0;
        double gpa = 0;
        for (StudyResult i : this.studentResult) {
            totalScore += i.getScore ()*i.getSubject ().getCredits ();
            totalcredits += i.getSubject ().getCredits ();
        }
        return (double) totalScore/totalcredits;
    }

    public int getTotalCredits(){
        int total = 0;
        for (StudyResult i : studentResult){
            total+=i.getSubject ().getCredits ();
        }
        return total;
    }

    public static GPAManagement getGPAInfo(){
        System.out.println ("De tim kiem hay nhap vao ma sinh vien: ");
        int mssv = new Scanner (System.in).nextInt ();
        GPAManagement search = new GPAManagement ();
        for (GPAManagement i : QLSVMain.studentResultList){
            if (i.getStudent ().getId ()==mssv)
                search = i;
        }
        return search;
    }

    @Override
    public String toString() {
        StringBuilder sbjs = new StringBuilder ();
        for (StudyResult i : this.studentResult){
            sbjs.append (i.getSubject ().getSubjectName ()).append ("  ");
        }
        return this.getStudent ().getName ()+"  "+ sbjs + "\n";

    }

    public static void showResult(GPAManagement studentResult){

        System.out.printf("Ket qua hoc tap cua sinh vien %s: \n",studentResult.getStudent ().getName ());
        System.out.println(new String(new char[27]).replace("\0", "-") + "Ket qua hoc tap ki nay cua sinh vien" + new String(new char[28]).replace("\0", "-"));
        int[] withd = {20, 30, 15, 15};
        int z = 0;
        String[] titleConten = {"SUBJECTN NO.", "NAME", "CREDTS", "SCORE"};
        TableRow table = new TableRow(4, withd);
        table.printTitle(titleConten);
        for (StudyResult i : studentResult.studentResult) {
            table.setCenterContent(0, String.valueOf (z+1));
            table.setCenterContent(1, String.valueOf(i.getSubject ().getSubjectNo()));
            table.setContent(2, i.getSubject ().getSubjectName ());
            table.setCenterContent (3, String.valueOf (i.getSubject ().getCredits ()));
            table.setCenterContent(4, String.valueOf (i.getScore ()));
            table.printConten();
            z++;
        }
        table.linePrint();
    }
}

