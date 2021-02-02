//Java
//Author: Aidan Mellin

import java.io.*;
import java.util.Scanner;
import java.util.*;

class inputReturn{
	int input_size;
	Hashtable<String,List<Integer>> prefs;
	inputReturn(int inS, Hashtable<String,List<Integer>> p){
		input_size = inS;
		prefs = p;
	}
}

public class Stable{
	/* 
	 * A Stable Matcher program
	 * 
	*/
	public static void main(String[] argv){
		/*
		 * param String[] args: Command Line Args for program
		*/
		
		inputReturn data = handleIn(argv);
		int input_size = data.input_size;
		Hashtable<String,List<Integer>> prefs_dict = data.prefs;
		Deque<Integer> DEQUE = new ArrayDeque<Integer>(); //Use Deque for a stack method that pops the head
		Hashtable<String,Integer> w_match = new Hashtable<String,Integer>();
		for(int i = 1; i <= input_size; i++){
			w_match.put("w"+i,0);
			DEQUE.add(i);
		}

		Iterator  itr = DEQUE.iterator();
		while(itr.hasNext()){ //Iterate through man stack
			int m_num = (int) itr.next();

			for(int i = 0; i < input_size; i++){ //Itr through pref list of man
				int w_num = prefs_dict.get("m" + m_num).get(i); //Woman number (ordered by pref)
				int m_ind = prefs_dict.get("w"+w_num).indexOf(m_num);
				int m_curr_ind = prefs_dict.get("w"+w_num).indexOf(w_match.get("w"+w_num));

				if(w_match.get("w"+w_num) == 0){ //If woman is free
					w_match.replace("w"+w_num, m_num);
					DEQUE.pop();
					itr = DEQUE.iterator();
					break;
				} else if(m_ind < m_curr_ind){//Likes the new guy
					DEQUE.add(w_match.get("w"+w_num));
					w_match.replace("w"+w_num,m_num);
					DEQUE.pop();
					itr = DEQUE.iterator();
					break;
				}
			}
		}

		handleOut(argv, w_match);

	}

	private static inputReturn handleIn(String[] argv){
		try{
	    	int input_size;
	        Hashtable<String,List<Integer>> prefs_dict = new Hashtable<String,List<Integer>>();
	        File file = new File(argv[0]);
	        BufferedReader fp = new BufferedReader(new FileReader(file));
	
	        String line;
	        input_size = Integer.parseInt(fp.readLine().replaceAll("\\s",""));
	
	        while((line = fp.readLine()) != null){
				line = line.replaceAll("\\s","");
				String person = line.substring(0,2);
				String prefs = line.substring(2);
				
				List<String> s_items = Arrays.asList(prefs.split(""));
				List<Integer> items = new ArrayList<Integer>();
				for(String s : s_items) items.add(Integer.parseInt(s)); //Parse ints as string to list
				prefs_dict.put(person,items);

			}
			return new inputReturn(input_size,prefs_dict);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	private static void handleOut(String[] argv, Hashtable<String,Integer> matched){
		try{
			File file = new File(argv[0]);
		    BufferedReader fp = new BufferedReader(new FileReader(file));
	
			String line;
			String formatted = fp.readLine().replaceAll("\\s","");	
		
			while((line = fp.readLine()) != null){
				line = line.trim();
				formatted += "\n" + line.substring(0,2);
				line = line.substring(2).replaceAll("\\s","").replaceAll(""," ");
				formatted += line;
			}
			
			for(String key : matched.keySet())
				formatted += "\nm"+matched.get(key)+" "+key;

			if(argv.length < 2){
				System.out.println(formatted);
			}else{
				try{
					FileWriter writeToFile = new FileWriter(argv[1]);
					writeToFile.write(formatted);
					writeToFile.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
