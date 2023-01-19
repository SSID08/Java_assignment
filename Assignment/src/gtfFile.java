
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultListModel;
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
    String file_location;
    ArrayList<String> chr_values=new ArrayList<>();
    ArrayList<String> source= new ArrayList<>();
    ArrayList<String> feature=new ArrayList<>();
    ArrayList<Integer> start_position=new ArrayList<>();
    ArrayList<Integer> end_position=new ArrayList<>();
    ArrayList<Float> score=new ArrayList<>();
    ArrayList<Character> strand= new ArrayList<>();
    ArrayList<Character> frame= new ArrayList<>();
    ArrayList<String> attributes= new ArrayList<>();
    double average_num_exons;
    double average_gene_length;
    int longest_gene_length;
    int shortest_gene_length;
    String longest_gene_id;
    String shortest_gene_id;
    int gene_count=0;
    ArrayList<Integer> gene_lengths=new ArrayList<>();
    double standard_deviation_gene_length;
    HashMap<String,HashMap> gene_exon_hash = new HashMap<>();
    
    
    //DefaultListModel out_list= new DefaultListModel();


    public void readGTF(){
        String loc=file_location;
        System.out.println(file_location);
        FileReader frs=null;
        BufferedReader inFile=null;
        //out_list.addElement(("Chromosome"+"\t"+ "Source"+ "\t" + "Feature" + "\t" + "Start Position" + "\t" + "End Position" + "\t" + "Score" + "\t"+ "Strand" + "\t" + "Frame" + "\t" + "Attributes" ));
        try{
            frs=new FileReader(loc);
            inFile= new BufferedReader(frs);
            String line =inFile.readLine();
            while (line!=null){
            if (line.startsWith("##")){
                line=inFile.readLine();
                continue;}
            String [] line_split=line.split("\t");
            chr_values.add(line_split[0]);
            source.add(line_split[1]);
            feature.add(line_split[2]);
            start_position.add(Integer.valueOf(line_split[3]));
            end_position.add(Integer.valueOf(line_split[4]));
            //score.add(Float.valueOf(line_split[5]));
            strand.add(line_split[6].charAt(0));
            frame.add(line_split[7].charAt(0));
            attributes.add(line_split[8]);
            line=inFile.readLine();} 
            inFile.close();
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "File not found!", "IO error", JOptionPane.ERROR_MESSAGE);}
        catch(ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "GTF file does not have the correct number of columns. Please recheck your GTF file.", "Incorrect input file format", JOptionPane.ERROR_MESSAGE);
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "GTF file does not have the correct types of values in the 'Start' and 'End' columns. Please recheck your GTF file.", "Incorrect input file format", JOptionPane.ERROR_MESSAGE);
            }
        catch(RuntimeException e){
            JOptionPane.showMessageDialog(null, "The GTF file is quite possibly faulty. Please check the documentation for the correct file format accepted by this program.", "Incorrect input file format", JOptionPane.ERROR_MESSAGE);}
        
    }
    
    
    
    public void average_exons(){
        double exon_count=0;
        for(String one_feature:feature){
            if (one_feature.equals("gene")){
            gene_count+=1;
            }
            else if(one_feature.equals("exon")){
            exon_count+=1;
            }
            else{}
        }
    
    average_num_exons=(exon_count/gene_count);}
    
    
    
    public void gene_model_statistics(){
        int shortest_length=Integer.MAX_VALUE/100;
        int longest_length=0;
        int longest_gene=0;
        int shortest_gene=0;
        
        for(int i=0;i<feature.size();i++){
        if(feature.get(i).equals("gene")){
            int start=start_position.get(i);
            int end=end_position.get(i);
            int length=end-start;
            gene_lengths.add(length);
            if(length>longest_length){
            longest_length=length;
            longest_gene=i;
            }
            if(length<shortest_length){
            shortest_length=length;
            shortest_gene=i;
        }
        }
        }
        longest_gene_length=longest_length;
        shortest_gene_length=shortest_length;
        longest_gene_id=attributes.get(longest_gene).split(";")[0].substring(8);
        shortest_gene_id=attributes.get(shortest_gene).split(";")[0].substring(8);
    }
    
    public void gene_length_statistics(){
        average_gene_length=gene_lengths.stream().mapToDouble(d->d).average().getAsDouble();     
        
        
    }
    
    public void gene_exon_hashmap(){
    for (int i =0; i<feature.size()-1;i++){
    if (feature.get(i).equals("gene")){
        int y=i+1;
        ArrayList<Integer> Exon_start=new ArrayList<>();
        ArrayList<Integer> Exon_end=new ArrayList<>();
        while(!feature.get(y).equals("gene")){
            if(feature.get(y).equals("exon")){
                Exon_start.add(start_position.get(y));
                Exon_end.add(end_position.get(y));}
            y++;
            if (y>=feature.size()){
            break;}
        }
        HashMap<String,ArrayList<Integer>> temp_hash=new HashMap<>();
        temp_hash.put("Exon_start",Exon_start);
        temp_hash.put("Exon_end", Exon_end);
        gene_exon_hash.put((chr_values.get(i)+ ":" + start_position.get(i).toString() + ":" + end_position.get(i).toString()),temp_hash); 
    }
    }
    
    }
}
