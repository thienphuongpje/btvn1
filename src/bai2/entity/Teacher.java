package bai2.entity;
import model.People;

public class Teacher extends People {
    enum Education{
        GS_TS("abb"),
        PGS_TS("aaa"),
        Giang_Vien_Chinh("aaaa"),
        Thac_Sy("aaaaaaaa");
        private String value;

        private Education(String value) {
            this.value = value;
        }
    }
    private int teacherCode;
    private Education educationLevel;

    public Teacher(String teacherName, String teacherAddress, String phoneNumber, int education) {
        this.setName(teacherName);
        this.setAddress(teacherAddress);
        this.setPhoneNumber(phoneNumber);
        switch (education){
            case 1: this.educationLevel = Education.GS_TS;
                break;
            case 2: this.educationLevel = Education.PGS_TS;
                break;
            case 3: this.educationLevel = Education.Giang_Vien_Chinh;
                break;
            case 4: this.educationLevel = Education.Thac_Sy;
                break;
            default:
                System.out.println(" Thông tin trình độ giáo viên chưa hợp lệ ");
        }

    }

    public int getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(int teacherCode) {
        this.teacherCode = teacherCode;
    }

    public String getEducationLevel() {
        return educationLevel.value;
    }

    public void setEducationLevel(Education educationLevel) {
        this.educationLevel = educationLevel;
    }

}


