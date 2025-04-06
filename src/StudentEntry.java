
/**
@author: Tej Jaideep Patel, tfp5304@psu.edu , 975793441
**/

public class StudentEntry {
    public String studentID;
    public String firstName;
    public String lastName;

    public StudentEntry(String studentID, String firstName, String lastName) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public String toString(){
        return(this.lastName + ", " + this.firstName);
    }
    
    public String getStudentID() {
        return studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
}
