package bai3.logichandle;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SubjectService {
    public static Set<Subject> subjects = new HashSet<>();

    public static void addNewSubject(){
        Subject subject = new Subject ();
        subject.getInputInfo();
        subject.setSubjectType();
        SubjectService.saveSubject(subject);
    }

    public static void saveSubject(Subject subject){
        if (!subjects.contains(subject)){
            subjects.add(subject);
        }else {
            System.out.println("Môn học đã tồn tại bạn có muốn cập nhật thông tin môn học, Nhập \"Yes\" để cập nhật. ");
            String update = new Scanner(System.in).nextLine();
            if (update.toUpperCase().equals("YES")){
                for (Subject i:subjects){
                    if (i.equals(subject)){
                        i.setCredits(subject.getCredits());
                        i.setType(Subject.SubjectType.valueOf (subject.getType ()));
                    }
                }
            }
        }
    }

    public static void showSubject(){
        System.out.println(new String(new char[36]).replace("\0", "-") + "Danh sach mon hoc hien tai" + new String(new char[36]).replace("\0", "-"));
        int[] withd = {12, 30, 30, 15};
        int z = 0;
        String[] titleConten = {"SUJECT NO.", "NAME", "CREDITS", "TYPE"};
        TableRow table2 = new TableRow(4, withd);
        table2.printTitle(titleConten);
        for (Subject i : subjects) {
            table2.setCenterContent(0, String.valueOf (z+1));
            table2.setCenterContent(1, String.valueOf(i.getSubjectNo ()));
            table2.setContent(2, i.getSubjectName ());
            table2.setCenterContent (3, String.valueOf (i.getCredits ()));
            table2.setCenterContent(4, i.getType ());
            table2.printConten();
            z++;
        }
        table2.linePrint();
    }
}
