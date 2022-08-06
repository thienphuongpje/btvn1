package bai2.entity;

import QlyGiaoVien.SalaryMangament;
import QlyGiaoVien.Subject;
import QlyGiaoVien.Teacher;
import Tool.TableRow;

import javax.security.auth.Subject;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class QLGV_Main {

    private SalaryMangament[] classesList = new SalaryMangament[0]; // Danh sách giảng viên và số môn học đang đảm nhận
    private Teacher[] teachers = new Teacher[0];
    private Subject[] subjectList = new Subject[0];
    private Scanner ip = new Scanner(System.in);
    private boolean exit = false;
    private boolean exist = false;


    public static void main(String[] args) throws Exception {
        QLGV_Main main = new QLGV_Main ();
        try {
            main.mainProgram();
        } catch (InputMismatchException exception) {
            System.out.println("có lỗi");
        }
    }

    static Subject[] arrayConcat(Subject[] a, Subject[] b){
        Subject[] ab = new Subject[a.length+b.length];
        for (int i = 0; i < ab.length; i++) {
            if (i<a.length) ab[i] = a[i];
            else ab[i] = b[ab.length-i-1];
        }
        return ab;
    }
    static Teacher[] arrayConcat(Teacher[] a, Teacher[] b){
        Teacher[] ab = new Teacher[a.length+b.length];
        for (int i = 0; i < ab.length; i++) {
            if (i<a.length) ab[i] = a[i];
            else ab[i] = b[ab.length-1-i];
        }
        return ab;
    }
    static SalaryMangament[] arrayConcat(SalaryMangament[] a, SalaryMangament[] b){
        SalaryMangament[] ab = new SalaryMangament[a.length+b.length];
        for (int i = 0; i < ab.length; i++) {
            if (i<a.length) ab[i] = a[i];
            else ab[i] = b[ab.length-1-i];
        }
        return ab;
    }
    static SalaryMangament[] sortArrayByName(SalaryMangament[] a){
        int n = a.length;
        SalaryMangament b;
        for (int i = 0; i < n-1 ; i++) {
            int j = i+1;
            b = a[j];
            while (j>0&&((int)b.getTeacher().getName().charAt(0))<((int)a[j-1].getTeacher().getName().charAt(0))){
                a[j] = a[j-1];
                j--;
            }
            a[j] = b;
        }
        return a;
    }
    static SalaryMangament[] sortArrayByCredit(SalaryMangament[] a){
        int n = a.length;
        SalaryMangament b;
        for (int i = 0; i < n-1 ; i++) {
            int j = i+1;
            b = a[j];
            while (j>0&&(b.calculateTotalCredit()>a[j-1].calculateTotalCredit())){
                a[j] = a[j-1];
                j--;
            }
            a[j] = b;
        }
        return a;
    }

    public void mainProgram() throws InputMismatchException {
        while (!exit){
/*
        Render lại danh sách classesList, mỗi khi danh sách giảng viên thay đổi
 */
            if(teachers.length!=classesList.length){
                int a = teachers.length;
                int b = classesList.length;
                int updateNumbers = a-b;
                for(int i = b; i<updateNumbers; i++){
                    SalaryMangament[] newTeacher = new SalaryMangament[1];
                    newTeacher[0] = new SalaryMangament(teachers[i]);
                    classesList = arrayConcat(classesList,newTeacher);
                }
            }
/*
        Hiển thị danh sách các chương trình có sẵn
 */
            System.out.println("Mời chọn chương trình");
            System.out.println("1- Thêm danh sách giảng viên");
            System.out.println("2- Thêm danh sách môn học");
            System.out.println("3- Xem danh sách giảng viên");
            System.out.println("4- Xem danh sách môn học");
            System.out.println("5- Đăng ký lớp học cho giảng viên");
            System.out.println("6- Xem danh sách đăng ký giảng dạy");
            System.out.println("7- Sắp xếp giảng viên theo thứ tự");
            System.out.println("8- Tra cứu, tính toán tiền lương của giảng viên");
            System.out.println("9- Thoát khỏi chương trình");
            int menu = ip.nextInt();
            boolean empty;
            switch (menu) {
/*
        Chương trình thêm giảng viên mới
 */
                case 1:
                    System.out.println("Nhập số lượng giáo viên muốn thêm vào danh sách");
                    int n1 = ip.nextInt();
                    for (int i = 0; i < n1; i++) {
                        Teacher[] teacher = new Teacher[1];
                        System.out.println("Nhập tên giảng viên:");
                        ip.nextLine();
                        String teacherName = ip.nextLine();
                        boolean teacherExits = false;
                        for (int j = 0; j < teachers.length; j++) {
                            if (teachers[j].getName().toUpperCase().equals(teacherName.toUpperCase())) {
                                teacherExits = true;
                                break;
                            }
                        }
                        System.out.println("Nhập địa chỉ của giảng viên");
                        String address = ip.nextLine();
                        System.out.println("Nhập số điện thoại của giảng viên");
                        String phoneNumber = ip.nextLine();
                        System.out.println("Chọn trình độ học vấn của giảng viên: 1- GS-TS\t2- PGS-TS\t3- Giảng viên chính\t4- Thạc sỹ");
                        int education = ip.nextInt();
                        empty = teachers.length == 0;
                        if (empty) {
                            teachers = new Teacher[1];
                            teachers[0] = new Teacher(teacherName, address, phoneNumber, education);
                            teachers[0].setTeacherCode(100);
                        } else {
                            if (!teacherExits) {
                                teacher[0] = new Teacher(teacherName, address, phoneNumber, education);
                                teacher[0].setTeacherCode(100 + i + teachers.length);
                                teachers = arrayConcat(teachers, teacher);
                            } else System.out.println("Giảng viên này đã tồn tại trong hệ thống");

                        }
                    }
                    System.out.println("Danh sách giảng viên hiện tại");
                    int z = 0;
                    for (Teacher i : teachers) {
                        System.out.println(z + " - " + i.getName());
                        z++;
                    }
                    System.out.println("Nhập phím bất kỳ để tiếp tục ");
                    ip.next();
                    break;
/*
        Chương trình thêm môn học mới
 */
                case 2:
                    System.out.println("Thêm dánh sách môn học");
                    System.out.println("Nhập số lượng môn học muốn thêm");
                    int n2 = ip.nextInt();
                    for (int i = 0; i < n2; i++) {
                        Subject[] subject = new Subject[1];
                        System.out.println("Nhập tên môn học: ");
                        ip.nextLine();
                        String subjectName = ip.nextLine();
                        boolean subjectExits = false;
                        for (int j = 0; j < subjectList.length; j++) {
                            if (subjectList[j].getSubjectName().toUpperCase().equals(subjectName.toUpperCase())) {
                                subjectExits = true;
                                break;
                            }
                        }
                        System.out.println("Nhập tổng số tiết của môn học");
                        int credit = ip.nextInt();
                        System.out.println("Nhập số tiết lý thuyết của môn học");
                        int practiceCredit = ip.nextInt();
                        System.out.println("Nhập giá 1 tiết của môn học");
                        double creditPrice = ip.nextDouble();
                        empty = subjectList.length == 0;
                        if (empty) {
                            subjectList = new Subject[1];
                            subjectList[0] = new Subject(subjectName, credit, practiceCredit, creditPrice);
                            subjectList[0].setSubjectCode(100);
                        } else {
                            if (!subjectExits) {
                                subject[0] = new Subject(subjectName, credit, practiceCredit, creditPrice);
                                subject[0].setSubjectCode(100 + i + subjectList.length);
                                subjectList = arrayConcat(subjectList, subject);
                            } else System.out.println("Môn học này đã có trong hệ thống");
                        }
                    }
                    System.out.println("Danh sách môn học hiện tại");
                    z = 0;
                    for (Subject i : subjectList) {
                        System.out.println(z + " - " + i.getSubjectName());
                        z++;
                    }
                    System.out.println("Nhập phím bất kỳ để tiếp tục ");
                    ip.next();
                    break;
/*
        In ra danh sách giảng viên hiện tại
 */
                case 3:
                    System.out.println(new String(new char[41]).replace("\0", "-") + "Danh sach giang vien hien tai" + new String(new char[42]).replace("\0", "-"));
                    int[] withd = {12, 30, 30, 15, 15};
                    String[] titleConten = {"TECHER ID", "NAME", "ADDRESS", "PHONE", "EDUCATION"};
                    TableRow table = new TableRow(5, withd);
                    table.printTitle(titleConten);
                    z = 0;
                    for (Teacher i : teachers) {
                        table.setCenterContent(0, String.valueOf(z));
                        table.setCenterContent(1, String.valueOf(i.getTeacherCode()));
                        table.setContent(2, i.getName());
                        table.setContent(3, i.getAddress());
                        table.setCenterContent(4, i.getPhoneNumber());
                        table.setCenterContent(5, i.getEducationLevel());
                        table.printConten();
                        z++;
                    }
                    table.linePrint();
                    System.out.println("Nhập phím bất kỳ để tiếp tục ");
                    ip.next();
                    break;
/*
        In ra danh sách môn học hiện tại
 */
                case 4:
                    System.out.println(new String(new char[51]).replace("\0", "-") + "Danh sach mon hoc hien tai" + new String(new char[51]).replace("\0", "-"));
                    int[] withd2 = {20, 30, 20, 20, 20};
                    String[] titleConten2 = {"SUBJECT CODE", "SUBJECT NAME", "TOTAL CREDITS", "PRATICE CREDITS", "CREDIT PRICE"};
                    TableRow table2 = new TableRow(5, withd2);
                    table2.printTitle(titleConten2);
                    z = 0;
                    for (Subject i : subjectList) {
                        table2.setCenterContent(0, String.valueOf(z));
                        table2.setCenterContent(1, String.valueOf(i.getSubjectCode()));
                        table2.setContent(2, i.getSubjectName());
                        table2.setCenterContent(3, String.valueOf(i.getTotalCredits()));
                        table2.setCenterContent(4, String.valueOf(i.getpracticeCredits()));
                        table2.setCenterContent(5, String.format("%,.2f", i.getCreditPrice()));
                        table2.printConten();
                        z++;
                    }
                    table2.linePrint();
                    System.out.println("Nhập phím bất kỳ để tiếp tục ");
                    ip.next();
                    break;
/*
        Đăng ký lớp học cho giảng viên
 */
                case 5:
                    System.out.println("Nhập tên giáo viên: ");
                    Scanner input = new Scanner(System.in);
                    String teacherInputName = input.nextLine();
                    for (SalaryMangament i : classesList) {
                        if (i.getTeacher().getName().toUpperCase().equals(teacherInputName.toUpperCase())) {
                            exist = true;
                            boolean subjectExist = false;
                            System.out.println("Nhập tên môn học: ");
                            Scanner input1 = new Scanner(System.in);
                            String sName = input1.nextLine();
                            Subject currentSubject = new Subject();
                            for (Subject j : subjectList) {
                                if (j.getSubjectName().toUpperCase().equals(sName.toUpperCase())) {
                                    subjectExist = true;
                                    currentSubject = j;
                                    break;
                                }
                            }
                            if (subjectExist) {
                                System.out.println(" Nhập số lớp của môn học");
                                int classNumbers = ip.nextInt();
                                i.takeClass(currentSubject, classNumbers);
                                break;
                            } else System.out.println("Môn học không tồn tại");
                        }
                    }
                    if (!exist) System.out.println("Giáo viên không tồn tại");
                    System.out.println("Nhập phím bất kỳ để tiếp tục ");
                    ip.next();
                    break;
/*
        Xem danh sách giảng viên và số môn học đảm nhận
 */
                case 6:
                    for (SalaryMangament i : classesList) {
                        System.out.printf("- Giảng viên %s đang dạy %d lớp, tổng %d tiết dạy \n", i.getTeacher().getName(), i.calculateTotalClass(), i.calculateTotalCredit());
                        System.out.print("Danh sách các môn đảm nhận: ");
                        for (Subject j : i.getSubjects()) {
                            System.out.println("+, " + j.getSubjectName());
                        }
                    }
                    System.out.println("Nhập phím bất kỳ để tiếp tục ");
                    ip.next();
                    break;

/*
        Sắp xếp danh sách giảng viên
 */
                case 7:
                    System.out.println("Lựa chọn cách thức sắp xếp");
                    System.out.println("1- Sắp xếp theo tên");
                    System.out.println("2- Sắp xếp theo số tiết dạy ");
                    int sortType = ip.nextInt();
                    switch (sortType) {
                        case 1:
                            if (classesList.length > 1) {
                                SalaryMangament[] nameSorted = sortArrayByName(classesList);
                                for (SalaryMangament i : classesList) {
                                    System.out.print(i.getTeacher().getName() + "\t");
                                }
                                System.out.println("Nhập phím bất kỳ để tiếp tục ");
                                ip.next();
                            }
                            break;
                        case 2:
                            if (classesList.length > 1) {
                                SalaryMangament[] creditSorted = sortArrayByCredit(classesList);
                                for (SalaryMangament i : classesList) {
                                    System.out.print(i.getTeacher().getName() + "\t");
                                }
                                System.out.println("Nhập phím bất kỳ để tiếp tục ");
                                ip.next();
                            }
                            break;
                    }
                    break;

/*
        Lập bảng tính tiền công cho các giáo viên
 */
                case 8:
                    System.out.println(new String(new char[55]).replace("\0", "-") + "Danh sach luong giang vien hien tai" + new String(new char[55]).replace("\0", "-"));
                    double[] salaries = new double[classesList.length];
                    String[] subjectName = new String[0];
                    for (int i = 0; i < classesList.length; i++) {
                        double salary = 0;
                        for (Subject j : classesList[i].getSubjects()) {
                            salary += j.getClassNumbers() * (j.getTheoryCredits() * j.getCreditPrice() + 0.7 * j.getpracticeCredits() * j.getCreditPrice());
                        }
                        salaries[i] = salary;
                    }

                    int[] withd3 = {30, 40, 20, 20, 25};
                    String[] titleConten3 = {"NAME", "SUBJECTS", "TOLTAL CREDITS", "AVERAGE PRICE", "TOTAL SALARY"};
                    TableRow table3 = new TableRow(5, withd3);
                    table3.printTitle(titleConten3);
                    z = 0;
                    for (SalaryMangament i : classesList) {
                        subjectName = new String[i.getSubjects().length];
                        for (int j = 0; j < i.getSubjects().length; j++) {
                            subjectName[j] = i.getSubjects()[j].getSubjectName();
                        }
                        // gán các thuộc tính và các phần tử trống trong bảng
                        table3.setCenterContent(0, String.valueOf(z));
                        table3.setContent(1, String.valueOf(i.getTeacher().getName()));
                        table3.setContent(2, Arrays.toString(subjectName).replace('[', ' ').replace(']', ' '));
                        table3.setCenterContent(3, String.valueOf(i.calculateTotalCredit()));
                        table3.setCenterContent(4, String.format("%,.2f", (float) salaries[z] / i.calculateTotalCredit()));
                        table3.setCenterContent(5, String.format("%,.2f", salaries[z]));
                        table3.printConten();
                        z++;
                    }
                    table3.linePrint();

                    System.out.println("Nhập phím bất kỳ để tiếp tục ");
                    ip.next();
                    break;
                case 9:
                    exit = true;
                    break;
                default:
                    System.out.println("Chương trình không tồn tại, hãy nhập lại");
            }
        }
    }
}
