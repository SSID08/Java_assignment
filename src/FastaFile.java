//import relevant classes
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Siddharth
 */
public class FastaFile {

    //initialise new class variables
    HashMap<String, String> FastaHash = new HashMap();//Hashmap relating headers to their respective sequences
    String file_location;
    HashMap<String, String> FastaGTFHash = new HashMap<>();//Hashmap relating input headers to truncated headers
    ArrayList<String> FileHeaderList = new ArrayList<>();//stores the order in which headers appear in the fasta 

    //Method to read the fasta file
    public void readfasta() throws Exception {
        String header = "";//Create a new variable to store temporary headers while iterating through the lines
        //System.out.println(file_location);
        FileReader frs = null; //Initialise new filereader
        BufferedReader inFile = null;//Initialise new BufferReader
        StringBuffer sequence = new StringBuffer(""); ////Create a new variable to store temporary sequence while iterating through the lines
        try {
            //Initialise the variables to read each line, line-by-line in the fasta
            frs = new FileReader(file_location);
            inFile = new BufferedReader(frs);
            String line = inFile.readLine();
            if (!line.matches("^>[0-9]+ dna:chromosome[^\n]+:[0-9]")) {
                throw new IncorrectFileFormatError();
            }//Trigger exception and if the file is found to have an incorrect header format              
            while (line != null) {//loop as long as it is not the end of the file
                if (line.startsWith(">")) {//check if line is a header
                    if (!sequence.isEmpty()) {
                        //if the sequence variable is not empty and the next header is and the next header is found, store the sequence into the hashmap with appropriate key
                        FastaHash.put(header, sequence.toString());
                        FileHeaderList.add(header);//add header into the arraylist to keep track of the order of headers in the fasta
                        sequence.delete(0, sequence.length());//empty the sequence variable after is has been added to the hashmap
                    }
                    header = line;//set header variable equal to current line
                    line = inFile.readLine();
                }//move on to next line 
                sequence = sequence.append(line);//append current line to sequence variable
                line = inFile.readLine();//move onto next line
                if (line == null) {//if the line is null, the end of the file is reached and the while loop will terminate at the end of this block
                    FastaHash.put(header, sequence.toString());//Put the header and its respective sequence into the hashmap
                    FileHeaderList.add(header);//add header to the appropriate list
                    sequence.delete(0, 0);
                }//empty the sequence variable
            }
            inFile.close();//close file after reading
        } catch (IOException e) {//catch IOerrors which may be trigerred during the file opening and reading
            JOptionPane.showMessageDialog(null, "Input/Output exception occured. Please check the file path.", "Error message", JOptionPane.ERROR_MESSAGE);
        } catch (IncorrectFileFormatError e) {//catch Errors related to file format
            JOptionPane.showMessageDialog(null, "The Fasta file was not found to be in the correct format. Please check the documentation for the correct Fasta format.", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
    }

    public double sequence_length() {//Method to average sequence length
        double sum_length = 0;//initialise initial variable to store sum of all sequence lengths in fasta
        for (String key : FastaHash.keySet()) {//iterate through the keyset of the hash
            sum_length += FastaHash.get(key).length();//add length of current sequence to the running sum
        }
        double average_length = sum_length / FastaHash.size(); //calculate average 
        return average_length;//return average length
    }

    public ArrayList<Double> GC_content() {
        ArrayList<Double> temp_array = new ArrayList<>(); //initialise output variable 
        for (String key : FastaHash.keySet()) {//iterate through the keyset containing fasta headers
            double GC_count = 0;//initialise running count for this iteration of the loop
            for (char ele : FastaHash.get(key).toCharArray()) {//iterate through the characters of the selected sequence
                if (ele == "G".charAt(0) | ele == "C".charAt(0)) {
                    GC_count += 1;//add to count if character is equal to G or C
                }
            }
            temp_array.add((GC_count / FastaHash.get(key).length()) * 100);//add GC count as percentage of sequence length into the output array
        }
        return temp_array;//return output array
    }

    public String extract_header_information(String str) {//Method to create truncated header from sequence header information in the fasta
        String[] input_arr = str.split(":");//split the header on ;
        String out = ("chr" + input_arr[3] + ":" + input_arr[4] + ":" + input_arr[5]);//Create an output string through concatenation of relevant fields 
        return out;//return output string
    }

    public void header_to_key() {//Method to store truncated header in relation to Fasta sequence headers
        for (String key : FastaHash.keySet()) {//Iterate through all keys in the header:sequence hashmap
            FastaGTFHash.put(key, extract_header_information(key));//Add header and truncated header as key: value pairs
        }
    }

}
