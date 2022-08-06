package bai3.logichandle;

import java.io.*;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentService {
    public static Set<Student> studentList = new LinkedHashSet<>();

    public static void inputStudentInfo(){
        System.out.println ("Them sinh vien vao danh sach");
        Student student = new Student();
        student.getInputInfo();
        studentSaveInfo(student);
    }

    public static void studentSaveInfo(Student student){
        if (!studentList.contains(student)) {
            StudentService.studentList.add(student);
            StudentService.studentList = StudentService.studentList.stream()
                    .sorted(Comparator.comparingInt((Student o) -> (int) o.getFirstName().charAt(0)).thenComparing ((Student o) -> (int) o.getFirstName().charAt(1)))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
            QLSVMain.studentResultList.add (new GPAManagement (student));
        }else {
            System.out.println("Sinh viên này đã có trong hệ thống, bạn có muốn thay đổi thông tin. Nhập \" Yes \" để cập nhật thông tin ");
            String update = new Scanner(System.in).nextLine();
            if (update.toUpperCase().equals("YES")){
                for (Student i:studentList){
                    if (student.equals(i)){
                        i.setAddress(student.getAddress());
                        i.setPhoneNumber(student.getPhoneNumber());
                        i.setClassID(student.getClassID());
                    }
                }
            }else System.out.println("Cập nhật thông tin không thành công");
        }
    }

    public static void showStudent(){
        System.out.println(new String(new char[42]).replace("\0", "-") + "Danh sach sinh vien hien tai" + new String(new char[43]).replace("\0", "-"));
        int[] withd = {12, 30, 30, 15, 15};
        int z = 0;
        String[] titleConten = {"STUDENT ID", "NAME", "ADDRESS", "PHONE", "CLASSID"};
        TableRow table = new TableRow(5, withd);
        table.printTitle(titleConten);
        for (Student i : studentList) {
            table.setCenterContent(0, String.valueOf (z+1));
            table.setCenterContent(1, String.valueOf(i.getId ()));
            table.setContent(2, i.getName());
            table.setContent(3, i.getAddress());
            table.setCenterContent(4, i.getPhoneNumber());
            table.setCenterContent(5, i.getClassID ());
            table.printConten();
            z++;
        }
        table.linePrint();
    }

    public static void automaticInput() throws FileNotFoundException {
        System.out.println ("Nhap duong dan den file input");
        String url = new Scanner (System.in).nextLine ();
        Reader rd = new FileReader(url);
        BufferedReader br = new BufferedReader (rd);
        try {
            String i = br.readLine ();
            while (i!=null){
                Student student = new Student ();
                String a = i;
                i = br.readLine ();
                String b = i;
                i = br.readLine ();
                String c = i;
                i = br.readLine ();
                String d = i;
                i = br.readLine ();
                student.getAutoInputInfo (a,b,c,d);
                studentSaveInfo (student);
            }
        } catch (IOException e) {
            throw new RuntimeException (e);
        }

    }
}
