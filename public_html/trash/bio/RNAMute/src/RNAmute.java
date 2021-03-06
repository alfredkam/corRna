/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *
 *									class RNAmute
 *
 *   the driver of the program if used as an application                              
 *  
 *
 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/ 

import java.io.*;
import java.util.*;
class RNAmute{

	
	public static void main(String [] args){
		String myFile = "seq.txt";//the file seq.txt holds the sequence given by the user
		if (args.length != 0)
			myFile = args[0];
	    System.out.println("The source file: " + myFile);
	    
	    String the_seq = "";
	    
	    // check if the input is valid
	    BufferedReader seq_reader = null;
	    try{
            seq_reader = new BufferedReader(new FileReader(myFile));
        }
        catch(FileNotFoundException e){//checks if the file seq.txt exist
            System.err.println("Error: file "+/*args[0]+*/" not found!");
            System.exit(1);
        }
        try{     
        	the_seq = seq_reader.readLine();
        	String temp_seq = the_seq.trim();
        	the_seq = temp_seq;
        }
        catch(IOException e){
            System.err.println("Error: error while reading input file!");
            System.exit(1);
        }
        boolean is_valid_seq = checkText(the_seq);
        
        //if the sequence is valid the process starts
        if (is_valid_seq){ 
			try{
				System.out.println("Processing...");
				String[] run = {"/bin/sh", "-c", "bin/mute_single < " + myFile};
				Process p = Runtime.getRuntime().exec(run);//a call to mute_single with the file 
			    p.waitFor();  							   //seq.txt that holds the sequence needed to be tested.
			    										   //this process generates the file result.txt in the home directory
			}
			catch (Exception e){System.out.println(e);}
			System.out.println("Processing...");
			ProcessResult pr = new ProcessResult();		//a call to ProcessResult which handles the file
			pr.go();									//result.txt that was generated by the last process.
			System.out.println("You can find the results in the file: RESULT_TABLE.html in the current directory");
			delete_unneccesery_files(myFile); // cleans the home directory from files that were created doring the run
	    }
	    
	    //if the input of the sequence was not valid, an output file "RESULT_TABLE.html" 
	    //that contains the folowing infomation will be created.
		else {
			System.out.println("Invald input \nplease check it and try again.\nremember : use only U,G,C,T,A or u,g,c,t,a");
	    	try{
		        File main_file= new File("RESULT_TABLE.html");	
		    	FileWriter out = new FileWriter(main_file);
		    	out.write("<html><head><title>Error</title></head><body bgcolor=#99ccff> "+
		    			  " Error: ileagal input.<p>please check it and try again.<p>"+
		    			  "remember : use only U,G,C,T,A or u,g,c,t,a <p>" + 
		    			  " and don't use spaces.</body></html>");
	    			out.close(); 
	    	}
	    	catch (Exception e){System.out.println(e);}       
    		
    	}
    	
    	
    	 
    	
	}//END of main
		
	public static boolean checkText(String str){// checks that only proper latters - A,C,U/T,G are used
	   	boolean ans = true;
	   	for (int i =0; i <str.length(); i++){
		    if (str.charAt(i) !='A' && str.charAt(i)!='T'&& str.charAt(i)!='U'&& str.charAt(i)!='C'&&str.charAt(i)!='G'
	   		    &&str.charAt(i) !='a' && str.charAt(i)!='t'&& str.charAt(i)!='u'&& str.charAt(i)!='c'&&str.charAt(i)!='g') 
	   			return  false;
		   	 }	
	   return ans;
	}
	  
	public static void delete_unneccesery_files(String myFile){
		try{
			File foo_file= new File("foo_file.ct");
			foo_file.delete();	
		 	
		    File mut= new File("mut");
			mut.delete();	
			
			File our_sequence= new File("our_sequence");
			our_sequence.delete();	
			
			
			File our_sequence_txt= new File("seq");
			our_sequence_txt.delete();	
			
			
			File result= new File("result");
			result.delete();		
			
			File seq= new File(myFile);
			seq.delete();
			
			File temporary= new File("temporary.txt");
			temporary.delete();	
			
			File temporary1= new File("temporary1.txt");
			temporary1.delete();
			
			File rna= new File("rna.ps");
			rna.delete();	
		
		}
		catch (Exception e){System.out.println(e);}  
		
	}
		
}
