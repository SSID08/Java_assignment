
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.IncompleteAnnotationException;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Siddharth
 */
public class FastaFile {
    
    HashMap<String, String> FastaHash= new HashMap();
    String file_location;
    HashMap<String,String> FastaGTFHash=new HashMap<>();
    
    public void readfasta() throws Exception{
        String loc=file_location;
        String header="Head";
        System.out.println(file_location);
        FileReader frs=null;
        BufferedReader inFile=null;
        StringBuffer sequence=new StringBuffer("");
        try{
            frs=new FileReader(loc);
            inFile= new BufferedReader(frs);
            String line =inFile.readLine();
            if(!line.startsWith(">")){
                throw new IncorrectFileFormatError();
            }              
            while (line !=null){
            if (line.startsWith(">")){
                header= line;
                line=inFile.readLine();}
                //while (!line.startsWith(">")){
            sequence=sequence.append(line);
            line=inFile.readLine();
            if(line !=null){
            if (line.startsWith(">")){
            FastaHash.put(header,sequence.toString());
            sequence.delete(0, 0);
            }}
            else{FastaHash.put(header,sequence.toString());}
            }
            inFile.close();
        }
        catch(IOException e){
        System.out.println("Exception occured");}
        catch(IncorrectFileFormatError e){
        
        }}
    
    
    public double sequence_length(){
        double sum_length=0;
        for (String key:FastaHash.keySet()){
            sum_length+=FastaHash.get(key).length();
        }
        double average_length=sum_length/FastaHash.size();
        return average_length;
    }
    
    public ArrayList<Double> GC_content(){
    ArrayList<Double> temp_array = new ArrayList<>();
    for(String key:FastaHash.keySet()){
    double GC_count=0;
    for(char ele: FastaHash.get(key).toCharArray()){
    if(ele=="G".charAt(0)|ele=="C".charAt(0)){
        GC_count+=1;
    }
    }
    temp_array.add((GC_count/FastaHash.get(key).length())*100);
    }
    return temp_array;
    }
    
    public String extract_header_information(String str){
        String[] input_arr=str.split(":");
        String out=("chr" + input_arr[3] + ":" + input_arr[4] + ":" + input_arr[5]);
        return out;
    }
    
    public void header_to_key(){
        for (String key: FastaHash.keySet()){
            FastaGTFHash.put(key, extract_header_information(key));
        }
    }    
    
    
    
    
    }   