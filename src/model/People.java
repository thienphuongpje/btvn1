package model;

public abstract class People {
    private String name;
    private int age;
    private String Address;
    private String phoneNumber;
    public People(){

    }
    public People(String name, int age, String address, String phoneNumber) {
        this.name = name;
        this.age = age;
        Address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof People){
            People another = (People) obj;
            return another.getName().equals(this.getName());
        }
        return false;
    }
}
