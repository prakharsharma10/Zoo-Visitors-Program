
//   Functions 
//   public static void main(String args)throws IOException,ParseException
//   entry point for the program ,returns 0 on normal termination 

//   static String getMaxWeekDay(int n)
//   returns a string for the day 
//   for eg for n=1 it returns "Sunday"
//   for n=7 it returns "Saturday"


//   Class
//   public class DataAnalysis 
//   It is the class containig main function and is the only class in the program
//   It uses a hashtable to keep a count of visitors on a givrn date and day
//   It is then used to find the different stats as mentiond in the requirements

//   Data Requirements
//   formatter		 type:Dateformat       used for formatting
//   idate		 type:Date             used for date
//   maxweekday          type:int	       keeps the day on which maximum visitors visited
//   days		 type:int[]            keeps the count of no of vistors for 7 days
//   imaxday and iminday type:Date             reference for day on which maximum and minimum visitors visted the zoo
//   few self-explanatory variables/references.....
   


import java.io.*;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Date;
import java.util.Set;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DataAnalysis 
{

	 /**
	 * @author Prakhar Sharma
	 * Last modified by Prakhar Sharma
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException, ParseException
	{
		
	DateFormat formatter = null;
        Date idate= null;
        Hashtable<Date,Integer> visitors= new Hashtable<Date,Integer>();
        String s;
        
	File f = new File("input.txt");
	FileReader fr = new FileReader(f);
	BufferedReader br  = new BufferedReader(fr);	
        
        while((s=br.readLine())!=null)
        {       	
           String[] details = s.split("\\s");
       	   if(details[0].equals(""))break;
        	formatter = new SimpleDateFormat("yyyy/MM/dd");
	   details[0] =  "20" + details[0];		
       	   // 13/10/14--->> 2013/10/14 
           idate=(Date)formatter.parse(details[0]);	        
        	
        	
           // Update Hashtable with no. of visitors
           if(visitors.containsKey(idate))				
        	visitors.put(idate, visitors.get(idate)+Integer.parseInt(details[2]));
           else
        	visitors.put(idate,Integer.parseInt(details[2]));        		
        	
        }
	br.close();
        Calendar c=Calendar.getInstance();
        Set<Date> keys = visitors.keySet();
        Date imaxday=null,iminday=null;
        int imaxvisitor=-1,iminvisitor=1000000000;
        int days[]=new int[8];
        for(Date key:keys)
        {
        	
           if(visitors.get(key)>imaxvisitor)
        	{
        	  imaxvisitor=visitors.get(key);
        	  imaxday=key;
        	}
           if(visitors.get(key)<iminvisitor)
        	{
        	  iminvisitor=visitors.get(key);
        	  iminday=key;
        	}
           c.setTime(key);
           days[c.get(Calendar.DAY_OF_WEEK)]+=visitors.get(key);        	
        	
        	
        }
        	
        File  fout=new File("Output.txt");
        fout.createNewFile();
        FileWriter fw = new FileWriter(fout);        
        PrintWriter pw  = new PrintWriter(fw);
         	
        DateFormat df=DateFormat.getDateInstance(DateFormat.FULL);
        	
        pw.println("Date and Day on which maximum no. of visitors visited :"+df.format(imaxday));       
    	pw.println("Date and Day on which minimum no. of visitors visited :"+df.format(iminday));
    	
    	int maxweekday=-1,uptillmax=-1;
    	int weekendvisitors=0,totalvisitors=0;
    	for(int i=1;i<=7;i++)
    	{
    	    if(days[i]>uptillmax)
    		{
                    uptillmax=days[i];
    		    maxweekday=i;
    		}
    			
    			
    	    if(i==1||i==7)
      		weekendvisitors+=days[i];
    		
    	    totalvisitors+=days[i];
    			
    	}
    	
    	s=getMaxWeekDay(maxweekday);
    	
    	pw.println("Day on which most no. of visitors visit on average : "+s+"day");
    	pw.println("Percentage of weekend visitors over total visitors : "+((double)weekendvisitors/totalvisitors)*100.00+"%");

    	pw.close();
	}
	
	
	
	static String getMaxWeekDay(int n)
	{
	    String s="";
	    switch(n)
	    {
	      case 1:s="Sun";break;
	      case 2:s="Mon";break;
	      case 3:s="Tues";break;
	      case 4:s="Wednes";break;
	      case 5:s="Thurs";break;
	      case 6:s="Fri";break;
	      case 7:s="Satur";break;
	    }
	    return s;
		
		
	}

}
