package QuesTwo;

import java.util.*;

abstract class Person {
    private String f_name;
    private String l_name;
    private int id;
    private Address address;
    private static int latestId = 1000;

    public Person(String f_name, String l_name, Address address) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.id = ++latestId;
        this.address = address;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public abstract void goToWork();
}

class Address {
    private String area;
    private String city;
    private String state;
    private int pinCode;

    public Address(String area, String city, String state, int pinCode) {
        this.area = area;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

}

class Student extends Person {
    private double cgpa;
    private int startYear;
    private String residential;

    public Student(String f_name, String l_name, Address address, double cgpa, int startYear, String residential) {
        super(f_name, l_name, address);
        this.cgpa = cgpa;
        this.startYear = startYear;
        this.residential = residential;
    }

    private int distance;

    public double getRank() {
        return rank;
    }

    private double rank;

    public void setRank(double rank) {
        this.rank = rank;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public int getdistance() {
        return distance;
    }

    public void setdistance(int dist) {
        this.distance = dist;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public String getResidential() {
        return residential;
    }

    public void setResidential(String residential) {
        this.residential = residential;
    }

    @Override
    public void goToWork() {
        System.out.print(this + " : ");
        if (this.residential.equals("onCampus")) {
            System.out.println("By Walk");
        } else if (this.residential.equals("dayScholar")) {
            System.out.println("By Vehicle");
        }
    }

    public String toString() {
        return this.getF_name() + " " + this.getL_name();
    }
}

public class Ques2 {
    public static void rankByDistance(List<Student> stu) {
        ArrayList<Integer> students = new ArrayList<>();
        for (Student student : stu) {
            int dist = (int)Math.abs(student.getAddress().getPinCode() - 110020);
            student.setdistance(dist);
            students.add(dist);
        }
        Collections.sort(students);
        for (int i = 4; i >= 0; i--) {
            for (Student j : stu) {
                if (j.getdistance() == students.get(i)) {
                    System.out.println((5 - i) + " " + j.getF_name() + " " + j.getL_name());
                    break;
                }
            }
        }
    }

    public static void rankbyCGPA(List<Student> stu) {
        ArrayList<Double> students = new ArrayList<>();
        for (Student student : stu) {
            double cg = student.getCgpa();
            students.add(cg);
        }
        Collections.sort(students);
        int k = 1;
        for (Double i : students) {
            for (Student j : stu) {
                if (j.getCgpa() == i) {
                    System.out.println((k++) + " " + j.getF_name() + " " + j.getL_name());
                    break;
                }
            }
        }
    }

    public static void finalRanking(List<Student> stu) {
        ArrayList<Double> students = new ArrayList<>();
        for (Student student : stu) {
            double rank = student.getdistance() - 6 * student.getCgpa();
            student.setRank(rank);
            students.add(rank);
        }
        Collections.sort(students);
        int k = 1;
        for (Double i : students) {
            for (Student j : stu) {
                if (j.getRank() == i) {
                    if (k <= 3) {
                        j.setResidential("onCampus");
                    }
                    System.out.println((k++) + " " + j.getF_name() + " " + j.getL_name());
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Address add1 = new Address("Dwarka", "New Delhi", "Delhi", 110077);
        Address add2 = new Address("Sector 78", "Gurgaon", "Haryana", 110120);
        Address add3 = new Address("Sector 120", "Noida", "Uttar Pradesh", 110130);
        Address add4 = new Address("Greater Kailash", "New Delhi", "Delhi", 110030);
        Address add5 = new Address("Okhla", "New Delhi", "Delhi", 110025);
        Student stu1 = new Student("Raunak", "Singh", add1, 9.1, 2020, "dayScholar");
        Student stu2 = new Student("Sneha", "Sinha", add2, 8.5, 2022, "dayScholar");
        Student stu3 = new Student("Raman", "Nath", add3, 7.5, 2021, "dayScholar");
        Student stu4 = new Student("Kanha", "Mishra", add4, 7.2, 2019, "dayScholar");
        Student stu5 = new Student("Manya", "Mittal", add5, 9.4, 2022, "dayScholar");
        List<Student> students = Arrays.asList(stu1, stu2, stu3, stu4, stu5);
        System.out.println("Ranking by Distance in descending order");
        rankByDistance(students);
        System.out.println();
        System.out.println("Ranking by CGPA in ascending order");
        rankbyCGPA(students);
        System.out.println();
        System.out.println("Final Ranking in ascending order");
        finalRanking(students);
        System.out.println();
        for (Student student : students) {
            student.goToWork();
        }
    }
}
