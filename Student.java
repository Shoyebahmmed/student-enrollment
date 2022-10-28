public class Student
{
	// Instance Variables
	 String name, sid, date, major, password;

public Student(String sid, String name, String date, String major, String password) {
        this.name = name;
        this.sid = sid;
        this.date = date;
        this.major = major;
        this.password = password;
    }

     public String getID()
    {
        return sid;
    }

}
