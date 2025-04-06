
import java.util.Calendar;


/**
@author: Tej Jaideep Patel, tfp5304@psu.edu , 975793441
**/


public class ScheduleEntry {
    public String semester;
    public String courseCode;
    public String studentID;
    public String status;
    public java.sql.Timestamp currentTimestamp;
    
    public ScheduleEntry(String semester, String courseCode, String studentID, String status, java.sql.Timestamp currentTimestamp) {
        this.semester = semester;
        this.courseCode = courseCode;
        this.studentID = studentID;
        this.status = status;
        this.currentTimestamp = currentTimestamp;
    }
    
    public String getSemester() {
        return semester;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStatus() {
        return status;
    }

    public java.sql.Timestamp getTimestamp() {
        return currentTimestamp;
    }
    
}
