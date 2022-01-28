import java.io.*;
import java.util.*;
import java.lang.Math;

public class Course implements CourseTransaction{
	
	private int courseNumber;
	private int credit;
	
	
	public Course(){
		
	}
	
	public void setCourseNumber(int courseNumber){
		this.courseNumber = courseNumber;
	}
	public void setCredit(int credit){
		this.credit = credit;
	}
	public int getCourseNumber(){
		return courseNumber;
	}
	public int getCredit(){
		return credit;
	}
	public void showInfo(){
		System.out.println(this.courseNumber);
		System.out.println(this.credit);
	}
	
	public void setCourse(Course e){
		Scanner strInput = new Scanner(System.in);
		System.out.println("Enter Course Number: ");
		int courseNumber = strInput.nextInt();
		e.setCourseNumber(courseNumber);
		System.out.println("Enter Credit: ");
		int credit = strInput.nextInt();
		e.setCredit(credit);
	}
	
	public void adding(int quantity){
	
		try {
			String addingData = Integer.toString(quantity);
			File history = new File("C:/Users/naymul.islam/Pictures/University Management/classes/db/history_db.txt");
		
			if(!history.exists()) {
				history.createNewFile();
				}

			FileWriter fwh = new FileWriter(history);
			BufferedWriter bwh = new BufferedWriter(fwh);
			
			bwh.write(addingData);
			bwh.flush();
			bwh.close();
			
			System.out.println("Adding Successful");

			}catch(IOException ioe) {
					ioe.printStackTrace();
					}
	}
	public void dropping(int quantity){
		
		try {
			
			File history = new File("C:/Users/naymul.islam/Pictures/University Management/classes/db/history_db.txt");
			File tempFile = new File("C:/Users/naymul.islam/Pictures/University Management/classes/db/course_db_temp.txt");
			if(!history.exists()) {
				System.out.println("No History Found");
				}
				
			if(!tempFile.exists()) {
				tempFile.createNewFile();
				}

			FileReader fr = new FileReader(history);
			BufferedReader br = new BufferedReader(fr);
			
			FileWriter fw = new FileWriter(tempFile);
			BufferedWriter bwh = new BufferedWriter(fw);
			
			String record;
			
			int flag = 0;
			while( ( record = br.readLine() ) != null ) {
    			
				int data = Integer.parseInt(record);
				
				if(data>quantity){
					int result;
					result = data - quantity;
					
					String output = Integer.toString(result);
					
					System.out.println(output);
					
					bwh.write(output);
					bwh.flush();
					System.out.println("Dropping Successful");
				}
				else{
					System.out.println("This amount of course doesn't exist");
				}
 
    		}
    		
    		br.close();
    		bwh.close();
    		
    		history.delete();
    		
    		tempFile.renameTo(history);
			
			
			}catch(IOException ioe) {
					ioe.printStackTrace();
					}
	}
	
	
	public void menu(){
		
		Scanner strInput = new Scanner(System.in);
        String choice,cont = "y";        
        
        while( cont.equalsIgnoreCase("y") ) {        	
        	   System.out.println("\t\t Course Management\n\n");
        
	        System.out.println("1. Adding Course ");
	        System.out.println("2. Withdraw Course ");
	        System.out.println("3. Going Back ");	        
	    
	        System.out.print("\n\n");
	        System.out.println("Enter your choice: ");
	        choice = strInput.nextLine();
	        
	        if( choice.equals("1") ) {
	        		Course course = new Course();
					System.out.println("Enter Quantity For Adding: ");
					int quantity = strInput.nextInt();
					course.adding(quantity);
	        } else if( choice.equals("2") ) {
					Course course = new Course();
					System.out.println("Enter Quantity For Dropping: ");
					int quantity = strInput.nextInt();
					course.dropping(quantity);
	        } else  if( choice.equals("3") ) {
	        		Start start= new Start();
					Start.main(null);
	        }	
		        	
	        System.out.println("Do you want to continue? Y/N");
	        cont = strInput.nextLine();
	       	
        }
	}
}