import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	public static void main(String args[]){
		String fileName = "D:\\workspace\\S&S practicum 3\\src\\Mei.txt";
		String[] woorden = {"ik", "en", "Glimlachend", "genie", "menig", "systematisch", "veldheer"
				,"computer", "geel", "zat"};
		String txt = null;
		int lastIndex = 0;
		int match = 0;
		int count = 0;
		
		try {
			txt = readFile(fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		



        KnuthMorrisPratt kmp = new KnuthMorrisPratt("ik");
        int offset = kmp.search(txt);

        System.out.println("text:    " + txt);
        System.out.print("pattern: ");

        while (lastIndex != txt.length()) {
            for (int i = 0; i < offset; i++) {
                lastIndex++;
                match++;
            }
            count++;
        }

        System.out.println("ik");
        System.out.println("count:   " + count);
        System.out.println("match:   " + match);
    }
		


	
	
	
	
	
	public static String readFile(String fileName) {
		 BufferedReader br = null;;
		try {
		    br = new BufferedReader(new FileReader(fileName));
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	
	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } catch (Exception e){
	    	return e.getMessage();
	    }
	    finally {
	    	try{
	        br.close();
	    	} catch (IOException e){
	    		return e.getMessage();
	    	}
	    }
	}
}


