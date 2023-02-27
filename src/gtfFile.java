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
public class gtfFile {
    
    //initialise class variables
    String file_location; //store file location of the inputted gtf file
    ArrayList<String> chr_values = new ArrayList<>();//create an arraylist to store the chromosome column of the GTF file
    ArrayList<String> source = new ArrayList<>();//create an arraylist to store the source column of the GTF file
    ArrayList<String> feature = new ArrayList<>();//create an arraylist to store the feature column of the GTF file
    ArrayList<Integer> start_position = new ArrayList<>();//create an arraylist to store the startposition column of the GTF file
    ArrayList<Integer> end_position = new ArrayList<>();//create an arraylist to store the endposition column of the GTF file
    ArrayList<Float> score = new ArrayList<>();//create an arraylist to store the score column of the GTF file
    ArrayList<Character> strand = new ArrayList<>();//create an arraylist to store the strand column of the GTF file
    ArrayList<Character> frame = new ArrayList<>();//create an arraylist to store the frame column of the GTF file
    ArrayList<String> attributes = new ArrayList<>();//create an arraylist to store the attributes column of the GTF file
    //initialise class variables that will be updated by the methods
    double average_num_exons;
    double average_gene_length;
    int longest_gene_length;
    int shortest_gene_length;
    String longest_gene_id;
    String shortest_gene_id;
    int gene_count = 0;
    ArrayList<Integer> gene_lengths = new ArrayList<>();
    //double standard_deviation_gene_length;
    HashMap<String, HashMap> gene_exon_hash = new HashMap<>();
    //HashMap<String,HashMap> gene_exon_hash_for_fasta = new HashMap<>();

    //DefaultListModel out_list= new DefaultListModel();
    public void readGTF() {//Method to read the GTF file
        //System.out.println(file_location);
        FileReader frs = null;//Initiliase reader variables 
        BufferedReader inFile = null;
        //out_list.addElement(("Chromosome"+"\t"+ "Source"+ "\t" + "Feature" + "\t" + "Start Position" + "\t" + "End Position" + "\t" + "Score" + "\t"+ "Strand" + "\t" + "Frame" + "\t" + "Attributes" ));
        try {
            //Initialise the variables to read each line, line-by-line in the fasta            
            frs = new FileReader(file_location);
            inFile = new BufferedReader(frs);
            String line = inFile.readLine();
            while (line != null) {//loop as long as it is not the end of the file
                if (line.startsWith("##")) {//check if line is part of the gtf table
                    line = inFile.readLine();//move onto next line
                    continue;//don't execute anything further in this iteration of the loop
                }
                String[] line_split = line.split("\t");//split line according to \t
                //add the relevant values to their respective ArrayLists
                chr_values.add(line_split[0]);
                source.add(line_split[1]);
                feature.add(line_split[2]);
                start_position.add(Integer.valueOf(line_split[3]));
                end_position.add(Integer.valueOf(line_split[4]));
                //score.add(Float.valueOf(line_split[5]));
                strand.add(line_split[6].charAt(0));
                frame.add(line_split[7].charAt(0));
                attributes.add(line_split[8]);
                //After all additions are completed, move onto the next line
                line = inFile.readLine();
            }
            inFile.close();//close file if the while loop is complete 
        } catch (IOException e) {//catch IO Exceptions that may occur when reading the file
            JOptionPane.showMessageDialog(null, "Input/Output exception occured. Please check the file path.", "IO error", JOptionPane.ERROR_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException e) {//Catch Excpetions related to the format of the input GTF file
            JOptionPane.showMessageDialog(null, "GTF file does not have the correct number of columns. Please recheck your GTF file.", "Incorrect input file format", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {//Catch Excpetions related to the format of the input GTF file
            JOptionPane.showMessageDialog(null, "GTF file does not have the correct types of values in the 'Start' and 'End' columns. Please recheck your GTF file.", "Incorrect input file format", JOptionPane.ERROR_MESSAGE);
        } catch (RuntimeException e) {////Catch Excpetions related to the format of the input GTF file 
            JOptionPane.showMessageDialog(null, "The GTF file is quite possibly faulty. Please check the documentation for the correct file format accepted by this program.", "Incorrect input file format", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void average_exons() {//Method to calculate the average exon length of the genes in the GTF file
        double exon_count = 0;//initialise running count variable 
        for (String one_feature : feature) {//Iterate through the feature ArrayList
            if (one_feature.equals("gene")) {//if the feature is a gene increment gene count
                gene_count += 1;
            } else if (one_feature.equals("exon")) {//if the feature is an exon increment exon count
                exon_count += 1;
            } else {
            }
        }

        average_num_exons = (exon_count / gene_count); //Calculate average number of exons per gene
    }

    public void gene_model_statistics() {//Method to calculate the longest and the shortest gene models in the GTF file
        int shortest_length = Integer.MAX_VALUE / 100;//initalise the shortest length to be an unreasonably high value
        int longest_length = 0;//initialise longest length to be 0
        int longest_gene = 0;//Index of the longest gene in the feature Arraylist
        int shortest_gene = 0;//Index of the shortest gene in the feature Arraylist

        for (int i = 0; i < feature.size(); i++) {//iterate through the feature Arraylist
            if (feature.get(i).equals("gene")) {//if feature is a gene 
                int start = start_position.get(i);//get start position of the gene
                int end = end_position.get(i);//get end position of the gene
                int length = end - start;//calculate gene length
                gene_lengths.add(length);//add gene length to the array storing gene lengths of the gtf file
                if (length > longest_length) {//if length of current gene is longer than longest gene length
                    longest_length = length;//update longest length to be current length if condition true
                    longest_gene = i;//update longest gene index to be current index if condition true
                }
                if (length < shortest_length) {//if length of current gene is shorter than shortest gene length
                    shortest_length = length;//update shortest length to be current length if condition true
                    shortest_gene = i;//update shortest gene index to be current index if condition true
                }
            }
        }
        longest_gene_length = longest_length; //set class variable longest_gene_length as longest_length 
        shortest_gene_length = shortest_length;//set class variable longest_gene_length as longest_length 
        longest_gene_id = attributes.get(longest_gene).split(";")[0].substring(8);//set class variable longest_gene_id as gene_id found at index longest_gene
        shortest_gene_id = attributes.get(shortest_gene).split(";")[0].substring(8);//set class variable shortest_gene_id as gene_id found at index shortest_gene
    }

    public void gene_length_statistics() {
        average_gene_length = gene_lengths.stream().mapToDouble(d -> d).average().getAsDouble();//calculate average gene length from the listarray of gene lengths

    }

    public void gene_exon_hashmap() {//create a hashmap of exons for each gene in gtf file
        for (int i = 0; i < feature.size() - 1; i++) {//iterate through the feature arraylist
            if (feature.get(i).equals("gene")) {//if gene is found enter if condition
                int y = i + 1;//initiliase another temporary variable at one more index more than previous where the gene was found
                ArrayList<Integer> Exon_start = new ArrayList<>();//initialise new array to store Exon starts
                ArrayList<Integer> Exon_end = new ArrayList<>();//initialise new array to store Exon ends
                while (!feature.get(y).equals("gene")) {//iterate until the next string gene is found
                    if (feature.get(y).equals("exon")) {//if an exon is found, enter if condition
                        Exon_start.add(start_position.get(y));//add start position when exon is found to the Exonstart array
                        Exon_end.add(end_position.get(y));//add end position when exon is found to the Exonend array
                    }
                    y++;//increment y
                    if (y >= feature.size()) {
                        break;//if y becomes greater than length of feature break for loop and end method
                    }
                }
                HashMap<String, ArrayList<Integer>> temp_hash = new HashMap<>();//create new temporary hashmap
                temp_hash.put("Exon_start", Exon_start);//put the Exon start array as value against "Exon_start" key
                temp_hash.put("Exon_end", Exon_end);//put the Exon end array as value against "Exon_end" key
                gene_exon_hash.put((chr_values.get(i) + ":" + start_position.get(i).toString() + ":" + end_position.get(i).toString()), temp_hash);/*put the temporary h
                ash as value against the key created in the format of the truncated header as described in the method extract_header_information() in the FastaFile class*/
                //gene_exon_hash_for_fasta.put((chr_values.get(i)+ ":" + start_position.get(i).toString() + ":" + end_position.get(i).toString()),temp_hash);

            }
        }

    }
}
