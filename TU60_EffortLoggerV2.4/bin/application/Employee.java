/*
 Written by:
 -Wyatt Mahony
 -...
 */

package application;


public class Employee {
    private String name;
    private String username;
    private String emailAddress;
    private String birthdate;
    private String title;
    private int employeeId;
    private double salary;

    // Constructors
    public Employee(String name, String username, String emailAddress, String birthdate, String title) {
        this.name = name;
        this.username = username;
        this.emailAddress = emailAddress;
        this.birthdate = birthdate;
        this.title = title;
        // Initialize other attributes as needed
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Other methods and operations specific to Employee
}
