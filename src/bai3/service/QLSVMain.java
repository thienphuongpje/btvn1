package bai3.service;

import model.TableRow;
import bai3.entity.GPAManagement;
import bai3.entity.Student;
import bai3.logichandle.StudentService;
import bai3.logichandle.SubjectService;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class QLSVMain {
    public static ArrayList<GPAManagement> studentResultList = new ArrayList<> ();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit){
            int menu = functionChoice ();
            switch (menu){
                case 1:
                    StudentService.inputStudentInfo ();
                    System.out.println ("Nhap phim bat ky de thoat");
                    new Scanner (System.in).next ();
                    break;
                case 2:
                    StudentService.showStudent ();
                    try {
                        writeOutput ();
                        System.out.println ("Danh sach sinh vien da duoc in ra tai \"D:\\test2.txt\"");
                    }catch (IOException exception){
                        System.out.println ("Khong the ghi du lieu vao file ");
                    }
                    System.out.println ("Nhap phim bat ky de thoat");
                    new Scanner (System.in).next ();
                    break;
                case 3:
                    SubjectService.addNewSubject ();
                    System.out.println ("Nhap phim bat ky de thoat");
                    new Scanner (System.in).next ();
                    break;
                case 4:
                    SubjectService.showSubject ();
                    System.out.println ("Nhap phim bat ky de thoat");
                    new Scanner (System.in).next ();
                    break;
                case 5:
                    GPAManagement.addSubjectResult ();
                    System.out.println ("Nhap phim bat ky de thoat");
                    new Scanner (System.in).next ();
                    break;
                case 6:
                    GPAManagement student = GPAManagement.getGPAInfo ();
                    GPAManagement.showResult (student);
                    System.out.println ("Nhap phim bat ky de thoat");
                    new Scanner (System.in).next ();
                    break;
                case 7:
                    ArrayList<GPAManagement> sortLits = sortResultList ();
                    for (int i = 0; i < sortLits.size ();i++) {
                        System.out.println (sortLits.get (i).toString ());
                    }
                    System.out.println ("Nhap phim bat ky de thoat");
                    new Scanner (System.in).next ();
                    break;
                case 8:
                    ArrayList<GPAManagement> sortedByName = studentResultList.stream ()
                            .sorted (Comparator.comparing ((GPAManagement o) -> (int) (o.getStudent ().getFirstName ().charAt (0))))
                            .collect (Collectors.toCollection (ArrayList::new));
                    averageSoreTablePrint (sortedByName);
                    System.out.println ("Nhap phim bat ky de thoat");
                    new Scanner (System.in).next ();
                    break;
                case 9:
                    try {
                        StudentService.automaticInput ();
                    }catch (FileNotFoundException e) {
                        throw new RuntimeException (e);
                    }
                    System.out.println ("Nhap phim bat ky de thoat");
                    new Scanner (System.in).next ();
                    break;
                case 10:
                    exit = true;
                    break;
                default:
                    System.out.println ("Chuong trinh ban chon khong co, vui long chon lai");
            }
        }
    }

    private static int functionChoice() {
        showMenu();
        System.out.print("Xin moi chon chuc nang: ");
        int choice;
        do {
            choice = new Scanner(System.in).nextInt();
            if (choice >= 1 && choice <= 10) {
                break;
            }
            System.out.print("Lua chon khong hop le, vui long chon lai: ");
        } while (true);
        return choice;
    }

    static void showMenu(){
        System.out.println("Moi chon chuong trinh");
        System.out.println("1- Them sinh vien");
        System.out.println("2- Xem danh sach sinh vien");
        System.out.println("3- Them mon hoc");
        System.out.println("4- Xem danh sach mon hoc");
        System.out.println("5- Nhap ket qua thi cho sinh vien");
        System.out.println("6- Tra cuu ket qua thi cua sinh vien");
        System.out.println("7- Sap xep sinh vien theo thu tu");
        System.out.println("8- Bang tra cuu diem trung binh hoc ki của sinh vien");
        System.out.println("9- Nhap thong tin sinh vien bang file ben ngoai");
        System.out.println("10- Thoat khoi chương trinh");
    }

    static ArrayList<GPAManagement> sortResultList(){
        System.out.println (" Chon phuong thuc sap xep\n1- Sap xep theo ten\n2- Sap xep theo tong so tin chi da hoc ");
        int sortWay = new Scanner (System.in).nextInt ();
        ArrayList<GPAManagement> sortedByName = studentResultList;
        switch (sortWay) {
            case 1:
                sortedByName = studentResultList.stream ()
                        .sorted (Comparator.comparing ((GPAManagement o) -> (int) (o.getStudent ().getFirstName ().charAt (0))))
                        .collect (Collectors.toCollection (ArrayList::new));
                break;
            case 2:
                sortedByName = studentResultList.stream ()
                        .sorted (Comparator.comparing (GPAManagement::getTotalCredits))
                        .collect (Collectors.toCollection (ArrayList::new));
                break;
            default: System.out.println ("Lua chon cua ban khong co");
        }
        return sortedByName;
    }

    static void averageSoreTablePrint(ArrayList<GPAManagement> list){
        System.out.println(new String(new char[32]).replace("\0", "-") + "Danh sach diem trung binh sinh vien ky nay" + new String(new char[33]).replace("\0", "-"));
        int[] withd = {30, 15, 15, 20, 15};
        int z = 0;
        String[] titleConten = {"NAME", "STUDENT ID", "CLASSID", "TOTAL CREDITS", "GPA"};
        TableRow table = new TableRow(5, withd);
        table.printTitle(titleConten);
        for (GPAManagement i : list) {
            table.setCenterContent(0, String.valueOf (z+1));
            table.setContent (1, i.getStudent ().getName ());
            table.setCenterContent (2,String.valueOf(i.getStudent ().getId ()));
            table.setCenterContent (3, i.getStudent ().getClassID ());
            table.setCenterContent(4, String.valueOf(Math.floor (24-Math.random ()*10)));
            table.setCenterContent(5, String.valueOf (i.getGPA ()));
            table.printConten();
            z++;
        }
        table.linePrint();
    }

    static void writeOutput() throws IOException {
        Writer file = new FileWriter ("D:\\test2.txt",true);
        BufferedWriter bw = new BufferedWriter (file);
        for (Student i : StudentService.studentList ) {
            bw.write (i.getName ());
            bw.newLine ();
            bw.write (i.getAddress ());
            bw.newLine ();
            bw.write (i.getPhoneNumber ());
            bw.newLine ();
            bw.write (i.getClassID ());
            bw.newLine ();

        }
        bw.close ();
    }
}
