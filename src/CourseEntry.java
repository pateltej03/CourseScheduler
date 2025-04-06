
/**
@author: Tej Jaideep Patel, tfp5304@psu.edu , 975793441
**/

public class CourseEntry {
    public String semester;
    public String courseCode;
    public String courseDescription;
    public int seats;

    public CourseEntry(String semester, String courseCode, String courseDescription, int seats) {
        this.semester = semester;
        this.courseCode = courseCode;
        this.courseDescription = courseDescription;
        this.seats = seats;
    }
            
    public String toString() {
        return(this.courseCode);
    }
    
    public String getSemester() {
        return semester;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public int getSeats() {
        return seats;
    }
    
    
}
