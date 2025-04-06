
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;


/**
@author: Tej Jaideep Patel, tfp5304@psu.edu , 975793441
**/

public class ScheduleQueries {
    
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getScheduledStudentCount;
    private static PreparedStatement getScheduledStudentsByCourse;
    private static PreparedStatement getWaitlistedStudentsByCourse;
    private static PreparedStatement dropStudentScheduleByCourse;
    private static PreparedStatement dropScheduleByCourse;
    private static PreparedStatement updateScheduleEntry;
    private static PreparedStatement getremainingcourses;
    private static ResultSet resultSet;
    
    public static void addScheduleEntry(ScheduleEntry scheduleEntry)
    {
        connection = DBConnection.getConnection();
        try
        {
            addScheduleEntry = connection.prepareStatement("insert into app.schedule (semester, coursecode, studentid, status, timestamp) values (?, ?, ?, ?, ?)");
            addScheduleEntry.setString(1, scheduleEntry.semester);
            addScheduleEntry.setString(2, scheduleEntry.courseCode);
            addScheduleEntry.setString(3, scheduleEntry.studentID);
            addScheduleEntry.setString(4, scheduleEntry.status);
            addScheduleEntry.setTimestamp(5, scheduleEntry.currentTimestamp);
            addScheduleEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> studentSchedule = new ArrayList<ScheduleEntry>();
        
        try
        {
            getScheduleByStudent = connection.prepareStatement("select semester, coursecode, studentid, status, timestamp from app.schedule where semester = (?) and studentid = (?) order by coursecode");
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, studentID);
            resultSet = getScheduleByStudent.executeQuery();
            
            while(resultSet.next())
            {
                ScheduleEntry scheduleEntry = new ScheduleEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5));
                studentSchedule.add(scheduleEntry);
                
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return studentSchedule;
    }
    
    public static int getScheduledStudentCount(String currentSemester, String courseCode)
    {
        connection = DBConnection.getConnection();
        int scheduledcount = 0;

        try
        {
            getScheduledStudentCount = connection.prepareStatement("select count(studentid) from app.schedule where semester = (?) and coursecode = (?)");
            getScheduledStudentCount.setString(1, currentSemester);
            getScheduledStudentCount.setString(2, courseCode);
            resultSet = getScheduledStudentCount.executeQuery();
            
            while(resultSet.next())
            {
                scheduledcount = resultSet.getInt(1);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return scheduledcount;
    }
    
    
    public static ArrayList<StudentEntry> getScheduledStudentsByCourse(String semester, String courseCode) 
    {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> ScheduledStudents = new ArrayList<StudentEntry>();
        StudentEntry student;
        String status = "S";
        
        try
        {
            getScheduledStudentsByCourse = connection.prepareStatement("select studentid from app.schedule where semester = (?) and coursecode = (?) and status = (?) order by timestamp");
            getScheduledStudentsByCourse.setString(1, semester);
            getScheduledStudentsByCourse.setString(2, courseCode);
            getScheduledStudentsByCourse.setString(3, status);
            resultSet = getScheduledStudentsByCourse.executeQuery();
            
            while(resultSet.next())
            {
                String StudentID = resultSet.getString(1);
                student = StudentQueries.getStudent(StudentID);
                ScheduledStudents.add(student);
                
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return ScheduledStudents;
    }
    
    public static ArrayList<StudentEntry> getWaitlistedStudentsByCourse(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> WaitlistedStudents = new ArrayList<StudentEntry>();
        StudentEntry student;
        String status = "W";
        
        try
        {
            getWaitlistedStudentsByCourse = connection.prepareStatement("select studentid from app.schedule where semester = (?) and coursecode = (?) and status = (?) order by timestamp");
            getWaitlistedStudentsByCourse.setString(1, semester);
            getWaitlistedStudentsByCourse.setString(2, courseCode);
            getWaitlistedStudentsByCourse.setString(3, status);
            resultSet = getWaitlistedStudentsByCourse.executeQuery();
            
            while(resultSet.next())
            {
                String StudentID = resultSet.getString(1);
                student = StudentQueries.getStudent(StudentID);
                WaitlistedStudents.add(student);
                
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return WaitlistedStudents;
    }
    
    public static StudentEntry dropStudentScheduleByCourse(String semester, String studentID, String courseCode) 
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> waitlistedschedules = new ArrayList<ScheduleEntry>();
        ScheduleEntry firstschedule = null;
        StudentEntry student = null;
        int waitcount = 0;
        String status = null;
        
        try
        {
            dropStudentScheduleByCourse = connection.prepareStatement("select status from app.schedule where semester = (?) and studentid = (?) and coursecode = (?)");
            dropStudentScheduleByCourse.setString(1, semester);
            dropStudentScheduleByCourse.setString(2, studentID);
            dropStudentScheduleByCourse.setString(3, courseCode);
            resultSet = dropStudentScheduleByCourse.executeQuery();
            
            while(resultSet.next())
            {
                status = resultSet.getString(1);
            }
            if (status == null)
            {
                return student = new StudentEntry("NN", "NN", "NN");
            }
            else
            {
                if ("S".equals(status))
                {   
                    dropStudentScheduleByCourse = connection.prepareStatement("delete from app.schedule where semester = (?) and studentid = (?) and coursecode = (?)");
                    dropStudentScheduleByCourse.setString(1, semester);
                    dropStudentScheduleByCourse.setString(2, studentID);
                    dropStudentScheduleByCourse.setString(3, courseCode);
                    dropStudentScheduleByCourse.executeUpdate();


                    dropStudentScheduleByCourse = connection.prepareStatement("select count(studentid) from app.schedule where semester = (?) and coursecode = (?) and status = (?)");
                    dropStudentScheduleByCourse.setString(1, semester);
                    dropStudentScheduleByCourse.setString(2, courseCode);
                    dropStudentScheduleByCourse.setString(3, "W");
                    resultSet = dropStudentScheduleByCourse.executeQuery();

                    while(resultSet.next())
                    {
                        waitcount = resultSet.getInt(1);
                    }
                    if (waitcount > 0){
                        dropStudentScheduleByCourse = connection.prepareStatement("select semester, coursecode, studentid, status, timestamp from app.schedule where semester = (?) and coursecode = (?) and status = (?) order by timestamp");
                        dropStudentScheduleByCourse.setString(1, semester);
                        dropStudentScheduleByCourse.setString(2, courseCode);
                        dropStudentScheduleByCourse.setString(3, "W");
                        resultSet = dropStudentScheduleByCourse.executeQuery();

                        while(resultSet.next())
                        {
                            ScheduleEntry scheduleEntry = new ScheduleEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5));
                            waitlistedschedules.add(scheduleEntry);
                        }

                        firstschedule = waitlistedschedules.get(0);
                        ScheduleQueries.updateScheduleEntry(semester, firstschedule);
                        student = StudentQueries.getStudent(firstschedule.studentID);
                        return student;
                    }
                    else
                    {
                        return student = new StudentEntry("00", "00", "00");
                    }
                }
                else
                {
                    dropStudentScheduleByCourse = connection.prepareStatement("delete from app.schedule where semester = (?) and studentid = (?) and coursecode = (?)");
                    dropStudentScheduleByCourse.setString(1, semester);
                    dropStudentScheduleByCourse.setString(2, studentID);
                    dropStudentScheduleByCourse.setString(3, courseCode);
                    dropStudentScheduleByCourse.executeUpdate();
                    
                    return student = new StudentEntry("FF", "FF", "FF");
                }
            }

            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return student;
    }
    
    public static void dropScheduleByCourse(String semester, String courseCode) 
    {
        connection = DBConnection.getConnection();
        
        try
        {
            dropScheduleByCourse = connection.prepareStatement("delete from app.schedule where semester = (?) and coursecode = (?)");
            dropScheduleByCourse.setString(1, semester);
            dropScheduleByCourse.setString(2, courseCode);
            dropScheduleByCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static void updateScheduleEntry(String semester, ScheduleEntry entry)
    {
        connection = DBConnection.getConnection();
        
        try
        {
            updateScheduleEntry = connection.prepareStatement("update app.schedule set status = (?), timestamp = (?) where semester = (?) and coursecode = (?) and studentid = (?)");
            updateScheduleEntry.setString(1, "S");
            updateScheduleEntry.setTimestamp(2, new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
            updateScheduleEntry.setString(3, semester);
            updateScheduleEntry.setString(4, entry.courseCode);
            updateScheduleEntry.setString(5, entry.studentID);
            updateScheduleEntry.executeUpdate();
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<CourseEntry> getremainingcourses(String StudentID, String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> coursecodes = new ArrayList<String>();
        ArrayList<CourseEntry> remainingcourses = new ArrayList<CourseEntry>();
        CourseEntry course;
        try
        {
            getremainingcourses = connection.prepareStatement("select coursecode from app.schedule where studentid = (?) and semester = (?)");
            getremainingcourses.setString(1, StudentID);
            getremainingcourses.setString(2, semester);
            resultSet = getremainingcourses.executeQuery();
            
            while(resultSet.next())
            {
                coursecodes.add(resultSet.getString(1));
            }
            for (String coursecode:coursecodes)
            {
                getremainingcourses = connection.prepareStatement("select semester, coursecode, description, seats from app.course where coursecode = (?) and semester = (?) ");
                getremainingcourses.setString(1, coursecode);
                getremainingcourses.setString(2, semester);
                resultSet = getremainingcourses.executeQuery();
                
                while(resultSet.next())
                {
                    course = new CourseEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                    remainingcourses.add(course);
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return remainingcourses;
    }
    
}
