
import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

class Main {
  static Scanner in = new Scanner(System.in);

  public static void main(String[] args) {

  //   System.out.println("\n\nStudent Registration:");
  //   if(registerStudent()){
  //     System.out.println("Student is successfully registered");
  //   }
  //   else System.out.println("Sorry");

  // System.out.println("\n\nStudent Login:");
  //   if(logIn()){
  //     System.out.println("Done");
  //   }
  //   else System.out.println("Sorry");

  // System.out.println("\n\nStudent Withdraw:");  
  //   withdrawStudent();

  }

// ***********************************************   Shoyeb Start
  public static boolean registerStudent() {

        String name, e_name, DOB, e_DOB, major, e_major;

            System.out.print("\nStudent Name: ");
            name = in.nextLine();
            
            Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
            Matcher hasSpecial = special.matcher(name);

            if(name.isEmpty()) {
                System.out.print("\nPlease Enter Studnet Name.");
                System.exit(0);
            }

            else if(name.matches(".*\\d.*")) {
                System.out.print("\nPlease Enter only TEXT ont Number.");
                System.exit(0);
            }

            else if(hasSpecial.find()) {
                System.out.print("\nPlease Enter only TEXT ont Symbol.");
                System.exit(0);
            }

            else 
              e_name = name;;


            System.out.print("\nStudent Date of Birth: ");
            DOB = in.nextLine();
          
            if(DOB.isEmpty()) {
                System.out.print("\nPlease Enter Student Date of Birth.");
                System.exit(0);
            }

            else if(!DOB.matches("^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-((19[8-9][0-9]|200[0-5]))$")) {
                System.out.print("\nPlease Enter date by DD-MM-YYYY");
                System.exit(0);
            }

            else 
              e_DOB = DOB;



            System.out.print("\nMajor Studying: ");
            major = in.nextLine();
            
            if(major.isEmpty()) {
                System.out.print("\nPlease Enter Major Studying.");
                System.exit(0);
            }

            else if(major.matches(".*\\d.*")) {
                System.out.print("\nPlease Enter only TEXT ont Number.");
                System.exit(0);
            }

            else 
              e_major = major;

    

    int status = 0;


    String sid = getStudentID();
    String password = getPassword();


    try{
        File userFileRead = new File("Student.txt");
        FileWriter userFileWrite = new FileWriter(userFileRead, true);

      userFileWrite.write(sid + "_" + name + "_" + DOB + "_" + major + "_" + password + "\n");
      userFileWrite.close();
      
      System.out.println("\n\nStudent Name: " + name + "\nStudent ID: " + sid + "\nDate of Birth: " + DOB + "\nMajor Studying: " + major);
      status = 1;
    }

    catch (IOException e) {
          System.out.println("Sorry file did not created");
          e.printStackTrace();
    }

    if(status == 1)
        return true;
    
    else return false;
}


  public static boolean logIn() {

    String studentID, password;

        System.out.print("\nStudent ID: ");
        studentID = in.nextLine();

        if(studentID.isEmpty()) {
            System.out.print("\nPlease Enter Studnet ID.");
            System.exit(0);;
        }


      

        System.out.print("\nPassword: ");
        password = in.nextLine();

        if(password.isEmpty()) {
            System.out.print("\nPlease Enter Password.");
            System.exit(0);
        }


    String dbStudentID = "", bdPassword = "";

    try{
        File userFileRead = new File("Student.txt");
        Scanner myReader = new Scanner(userFileRead);     

        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();

            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter("_");
            while(lineScanner.hasNext()){
              dbStudentID = lineScanner.next();
              String name = lineScanner.next();
              String date = lineScanner.next();
              String major = lineScanner.next();
              bdPassword = lineScanner.next();
            }

            if(allowStudent(dbStudentID, studentID, bdPassword, password) == true) {
                return true;
            }
            else continue;
        }
    }

    catch (IOException e) {
          System.out.println("Sorry file did not created");
          e.printStackTrace();
    } 
      return false;
  }


  public static boolean allowStudent(String id1, String id2, String pass1, String pass2) {
    
    if(id1.equals(id2)) {
      if(pass1.equals(pass2))
        return true;
    }
    else 
      return false;

    return false;
  }


  public static String getStudentID() {
            int n = 6;
        String number = "0123456789";
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
              int index = (int)(number.length() * Math.random());
              sb.append(number.charAt(index)); 
        }
        return sb.toString();
    }


  public static String getPassword() {
        int n = 8;
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
 /*
  public static void withdrawStudent() {
    try {
            File inputFile = new File("Student.txt");
            Scanner myReader = new Scanner(inputFile);
            if (!inputFile.isFile()) {
                System.out.println("File does not exist");
                return;
            }
            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");
            BufferedReader br = new BufferedReader(new FileReader("Student.txt"));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            String line = null;

            String studentID, dbStudentID = "";

                    System.out.print("\nStudent ID: ");
                    studentID = in.nextLine();

                    if(studentID.isEmpty()) {
                        System.out.print("\nPlease Enter Studnet ID.");
                        System.exit(0);;
                    }


            String deleteLine = "";

            while (myReader.hasNextLine()) {
              String oldLine = myReader.nextLine();

              Scanner lineScanner = new Scanner(oldLine);
              lineScanner.useDelimiter("_");
              while(lineScanner.hasNext()){
                dbStudentID = lineScanner.next();
                String name = lineScanner.next();
                String date = lineScanner.next();
                String major = lineScanner.next();
                String password = lineScanner.next();
            }

            if(studentID.equals(dbStudentID)) {
                deleteLine = oldLine;
            }
            else continue;
        }

            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {
                if (!line.trim().equals(deleteLine)) {
                    pw.println(line);
                    pw.flush();
                    System.out.println("Student ID is not in the Database.");
                }

                else {
                  System.out.println("You have Successfully Withdraw the Student ");
                }
            }
            pw.close();
            br.close();

            //Delete the original file
            if (!inputFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inputFile))
                System.out.println("Could not rename file");
            }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

  } */
// ***********************************************   Shoyeb End

  public static void withdrawStudent() {

    ArrayList<Student> studnetList = new ArrayList<Student>();
    String studentID;
        System.out.print("\nStudent ID: ");
        studentID = in.nextLine();

        if(studentID.isEmpty()) {
            System.out.print("\nPlease Enter Studnet ID.");
            System.exit(0);
        }
    try {
        File inputFile = new File("Student.txt");
        Scanner myReader = new Scanner(inputFile);
        while (myReader.hasNextLine()) {
            String oldLine = myReader.nextLine();

            Scanner lineScanner = new Scanner(oldLine);
            lineScanner.useDelimiter("_");

              while(lineScanner.hasNext()){
                String sid = lineScanner.next();
                String name = lineScanner.next();
                String date = lineScanner.next();
                String major = lineScanner.next();
                String password = lineScanner.next();
                Student s1 = new Student(sid, name, date, major, password);

                studnetList.add(s1);
            }
        }

      if (studnetList.isEmpty() == true)
            System.out.println("Sorry There is some Problems");

      else {
           

      for (Student s : studnetList) { 		      
           if(s.getID().equals(studentID)) {
              studnetList.remove(s); 		
              }
      }

         updateFile(studnetList);

      }
        

    }

    catch (IOException ex) {
        ex.printStackTrace();
    }



  }

  public static void updateFile(ArrayList<Student> list) {
    System.out.println("Done!");
  }


}

// public class Student {
//    


// }

// Class Declaration





/*
// Ahmed Start
  //code re-used from Soyeb's part

  //to work on same file structure
  public static boolean StudentExists(String sid, String pwd) {
	    String dbStudentID = "", bdPassword = "";

	    try{
	        File userFileRead = new File("Student.txt");
	        Scanner myReader = new Scanner(userFileRead);     

	        while (myReader.hasNextLine()) {
	            String line = myReader.nextLine();

	            Scanner lineScanner = new Scanner(line);
	            lineScanner.useDelimiter("_");
	            while(lineScanner.hasNext()){
	              dbStudentID = lineScanner.next();
	              String name = lineScanner.next();
	              String date = lineScanner.next();
	              String major = lineScanner.next();
	              bdPassword = lineScanner.next(); 
	            }
	        }
	    }

	    catch (IOException e) {
	          System.out.println("Sorry file did not created");
	          e.printStackTrace();
	    } 

	    return allowStudent(dbStudentID, sid, bdPassword, pwd);	        

  }
  
 public static Map<String, Double> extractSubjects(String sid, String pwd){
	 Map<String, Double> subjects = new HashMap<String, Double>();
	  try{
	        File userFileRead = new File("subjects.txt");
	        Scanner myReader = new Scanner(userFileRead);     

	        //format per line is: SubjectName: Date,Date,Date, etc...
	        
	        while (myReader.hasNextLine()) {
	            String line = myReader.nextLine();
	            
	            //format is: subjectName, Price: Date, date, etc...
	            String subName = line.substring(0, line.indexOf(","));
	            Double price = Double.parseDouble(line.substring(line.indexOf(",") + 1, line.indexOf(":")));
	            subjects.put(subName, price);
	        }
	    }

	    catch (IOException e) {
	          System.out.println("Sorry file did not created");
	          e.printStackTrace();
	    } 
	 return subjects;
 }
  
  public static boolean generateFee() {
	  String sid = getLigInPassword();
	  String pwd = getLigInPassword();
	  boolean existsInDb = StudentExists(sid, pwd);
	  if (existsInDb) {
		  
		  //extract subjects
		  Map<String, Double> subjects = extractSubjects(sid, pwd);
		  if (subjects.size() > 0) { //if we have at least subject
			  double totalAmount = 0d;
			  for (Map.Entry<String, Double> keypair : subjects.entrySet()) {
				  System.out.println("Subject: " + keypair.getKey() + ", costs: " + keypair.getValue() + "AED");
				  totalAmount += keypair.getValue();
			  }
			  
			  System.out.println("\nTotal amount to pay for " + subjects.size() + " subjects is: " + totalAmount + "AED");
			  return true;
		  } else System.out.println(sid + " is currently not taking any subjects!");
	  } else System.out.println(sid + " does not exist in the system!");
	  return false;
  }
  
  private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
  private static Date[] extractTimings(String subjectName) throws ParseException {
	  List<Date> dates = new ArrayList<Date>();
	  try{
	        File userFileRead = new File("subjects.txt");
	        Scanner myReader = new Scanner(userFileRead);     

	        //format per line is: SubjectName: Date,Date,Date, etc...
	        
	        while (myReader.hasNextLine()) {
	            String line = myReader.nextLine();

	            if (line.startsWith(subjectName + ": ")) {
	            	Scanner lineScanner = new Scanner(line.substring(line.indexOf(subjectName + ": ")));
		            lineScanner.useDelimiter(",");
		            
		            while(lineScanner.hasNext()){
		              dates.add(formatter.parse(lineScanner.next()));
		            }
		            
	            }
	            
	        }
	    }

	    catch (IOException e) {
	          System.out.println("Sorry file did not created");
	          e.printStackTrace();
	    } 
	  
	  Date[] _dates = new Date[dates.size()];
      dates.toArray(_dates);
      return _dates;
	  
  }
  
  private static boolean subjectExists(String subjectName) {
	  try{
	        File userFileRead = new File("subjects.txt");
	        Scanner myReader = new Scanner(userFileRead);     

	        //format per line is: SubjectName: Date,Date,Date, etc...
	        
	        while (myReader.hasNextLine()) {
	            String line = myReader.nextLine();

	            if (line.startsWith(subjectName + ": "))
	            	return true;
	            
	        }
	    }

	    catch (IOException e) {
	          System.out.println("Sorry file did not created");
	          e.printStackTrace();
	    } 
	  return false;
  }
  
  private static String getSubjectName() {
	    String subjectName;

	    do {
	    	System.out.print("Subject Name: ");
	    	subjectName = in.nextLine();
	    } while (subjectName.isEmpty());
	    return subjectName;
  }
  
  private static int readOption(int min, int max) {
	  int option;
	  do {
		  System.out.println("Enter an option between (" + min + ", " + max + "):");
		  option = in.nextInt();
	  } while (option < min && option > max);
	  return option;
  }
  
private static void addStudentToSubject() {
	
}
  
  public static boolean enrollInClass() throws ParseException {
	  String sid = getLigInPassword();
	  String pwd = getLigInPassword();
	  int maxNumberOfSubjects = 4;
	  boolean existsInDb = StudentExists(sid, pwd);
	  if (existsInDb) {
		//extract subjects
		  Map<String, Double> subjects = extractSubjects(sid, pwd);
		  if (subjects.size() < maxNumberOfSubjects) {
			  String subjectName = getSubjectName();
			  if (subjectExists(subjectName)) {
				  Date[] timings = extractTimings(subjectName);
				  System.out.println("There are " + timings.length + " available timings. Choose one from below:");
				  for (int i = 0; i < timings.length; i++) {
					  System.out.print((i + 1) + ": ");
					  System.out.println(formatter.format(timings[i]));
				  }
				  int option = readOption(0, timings.length) - 1;
				  System.out.println("Student enrolled in " + formatter.format(timings[option]));
				  
			  } else System.out.println("Subject does not exist in database!");
			  
			  
		  }else System.out.println(sid + " is currently taking the max of subjects, which is " + maxNumberOfSubjects);
		  
	  }else System.out.println(sid + " does not exist in the system!");
	  return false;
  }


// Ahmed Ennab

*/