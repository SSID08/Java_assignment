
//import the relevant packages
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import static java.lang.Math.round;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Siddharth
 */
public class GetUserInput extends javax.swing.JFrame implements ActionListener {

    //initialise class variables 
    gtfFile myGTF; //initialise a gtfFile object
    FastaFile myFasta;//initialise a FastaFile object
    HashMap<String, ArrayList<ArrayList<Integer>>> highlight_hash;
    TableModel myModel; //initialise a TableModel object 
    private int highlight_press;

    /**
     * Creates new form GetUserInput
     */
    public GetUserInput() {
        initComponents();
    }

    public void positions_to_highlight() {
        //This method extracts the positions to highlight the exons on for each gene in the fasta file inputted
        myFasta.header_to_key();
        for (String key : myFasta.FastaGTFHash.keySet()) {//iterate through the truncated fasta sequence headers
            try {
                String key_2 = myFasta.FastaGTFHash.get(key);//get the truncated header associated with each key
                if (!key_2.matches("chr[0-9]+:[0-9]+:[0-9]+")) {//if key format is not as expected, throw an exception
                    throw new IncorrectFileFormatError();
                }
                HashMap<String, ArrayList> myHash = myGTF.gene_exon_hash.get(key_2);//Access the gene exon hashmap for a particular gene in the fasta file
                ArrayList<Integer> Start_postitions = myHash.get("Exon_start");//get the Exon start positions for that gene of the fasta file 
                ArrayList<Integer> End_positions = myHash.get("Exon_end");//get the Exon end positions for that gene of the fasta file
                int gene_start_position = Integer.parseInt(key_2.split(":")[1]);//get the gene start position for this particular gene in the fasta file
                for (int i = 0; i < Start_postitions.size(); i++) {//iterate through the start and end position arrays and subtract from them the gene start to get their positions relative to the gene start 
                    Start_postitions.set(i, Start_postitions.get(i) - gene_start_position);
                    End_positions.set(i, End_positions.get(i) - gene_start_position);
                }
                ArrayList<ArrayList<Integer>> out_array_list = new ArrayList<>();//initialise a new array containing the array of start and end positions of this partcilar sequence
                out_array_list.add(Start_postitions); //add the elements to this array
                out_array_list.add(End_positions);
                highlight_hash.put(key, out_array_list);//put the above intitalised array into the class hashmap where the keys are the truncated headers
            } catch (IncorrectFileFormatError e) {
                JOptionPane.showMessageDialog(null, "The FASTA file headers did not split into the correct format required. Please check the documentation for the correct format required.", "File Format Error", JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException e) {//catch an exception if no gene model is found for a particular sequence within the fasta
                JOptionPane.showMessageDialog(null, "No match was found in the GTF file for sequence starting with header" + key, "No gene match found", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        exonDisplayPanel1 = new ExonDisplayPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane2.setToolTipText("");
        jScrollPane2.setAutoscrolls(true);
        jScrollPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {}
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        jTable1.setDragEnabled(true);
        jTable1.setName(""); // NOI18N
        jScrollPane2.setViewportView(jTable1);
        jTable1.setVisible(false);

        jButton2.setText("Display Gene Model");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("Welcome to GeneViz. Upload your files using the open button in the file menu.");

        jLabel2.setText("Visualise gene models by selecting the gene in the table and clicking on the Display button.");

        jLabel3.setText("Display and highlight exons in the Fasta File display tab by clicking on the Highlight Exons button");

        jLabel4.setText("View Statistics related to input files in the Statistics tab by clicking on their respective buttons");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addGap(7, 7, 7)
                .addComponent(jLabel4))
        );

        jTabbedPane1.addTab("GTF File display", jPanel1);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jButton1.setText("Highlight Exons");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 688, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addGap(45, 45, 45))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(15, 15, 15))
        );

        jTabbedPane1.addTab("FASTA file display", jPanel2);

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane1.setViewportView(jTextArea2);

        jTextArea3.setEditable(false);
        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setWrapStyleWord(true);
        jScrollPane4.setViewportView(jTextArea3);

        jButton3.setText("Calculate GTF statistics");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(204, 204, 204));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("GTF File Statistics");
        jTextField1.setBorder(null);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(204, 204, 204));
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("FASTA file statistics");
        jTextField2.setBorder(null);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton4.setText("Calculate FASTA statistics");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 391, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane4)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("Statistics", jPanel3);

        exonDisplayPanel1.setAutoscrolls(true);

        javax.swing.GroupLayout exonDisplayPanel1Layout = new javax.swing.GroupLayout(exonDisplayPanel1);
        exonDisplayPanel1.setLayout(exonDisplayPanel1Layout);
        exonDisplayPanel1Layout.setHorizontalGroup(
            exonDisplayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 852, Short.MAX_VALUE)
        );
        exonDisplayPanel1Layout.setVerticalGroup(
            exonDisplayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 511, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Gene Model Display", exonDisplayPanel1);

        jMenu1.setText("File");

        jMenu3.setText("Open ");

        jMenuItem1.setText("FASTA file");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem2.setText("GTF file");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenu1.add(jMenu3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        jTextArea1.getHighlighter().removeAllHighlights();
        jTextArea1.setText(null);
        StringBuffer out_string = new StringBuffer();//initialise variable
        FileDialog nameBox;
        nameBox = new FileDialog(this, "Open FASTA", FileDialog.LOAD);
        nameBox.setVisible(true);//Initialise file dialog box 
        String fileDirectory = nameBox.getDirectory();//Get file directory from user input
        String filename = nameBox.getFile();//get file name from user input 
        myFasta = new FastaFile();//initialise a new FastaFile object
        try {
            filename = fileDirectory.concat(filename);//create full filepath by concatenating user input
            if (!filename.endsWith("fa")) {//Throw an error if incorrect file extension is found
                throw new IncorrectFileFormatError();
            }
            myFasta.file_location = filename;//Add filename to the class variable 
            try {
                myFasta.readfasta();//Execute the read method on the myFasta object
            } catch (Exception ex) {
                Logger.getLogger(GetUserInput.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println(myFasta.FastaHash.size());
            for (String key : myFasta.FileHeaderList) {//Iterate through the object variable and append each header followed by its respective sequence to the output stringBuffer object
                out_string.append(key).append("\n").append(myFasta.FastaHash.get(key)).append("\n");

            }
            jTextArea1.setText(out_string.toString());//set text in the text area
            jTextArea1.setLineWrap(true);//allow for linewrapping in the text area
            jTextArea1.setCaretPosition(0);
            highlight_press = 0;//set class variable value
            jTabbedPane1.setSelectedIndex(1);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(rootPane, "File not found. Please select file", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IncorrectFileFormatError e) {
            JOptionPane.showMessageDialog(rootPane, "This file does not end in '.fa'. This program therefore does not recognise this file as a FASTA file. Pleas upload the correct file type.", "Incorrect File format error", JOptionPane.ERROR_MESSAGE);
        }
        //jTextPane1.getHighlighter()
        //System.out.println(myFasta.GC_content().toString());


    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:

        //initialise variables to interact with user and get file names with paths
        FileDialog nameBox;
        DefaultTableModel model = new DefaultTableModel();//initialise new table model
        nameBox = new FileDialog(this, "Open GTF", FileDialog.LOAD);
        nameBox.setVisible(true);
        String fileDirectory = nameBox.getDirectory();
        String filename = nameBox.getFile();
        try {//check for errors in extension
            filename = fileDirectory.concat(filename);
            if (!filename.endsWith(".gtf")) {
                throw new IncorrectFileFormatError();
            }
            myGTF = new gtfFile();//create a new gtfFile object
            myGTF.file_location = filename;//add filename to class variable 
            myGTF.readGTF();//call the read method on the gtf object
            //add object variables to the model as columns
            model.addColumn("Chromosome", myGTF.chr_values.toArray());
            model.addColumn("Source", myGTF.source.toArray());
            model.addColumn("Feature", myGTF.feature.toArray());
            model.addColumn("Start", myGTF.start_position.toArray());
            model.addColumn("End", myGTF.end_position.toArray());
            model.addColumn("Score", myGTF.score.toArray());
            model.addColumn("Strand", myGTF.strand.toArray());
            model.addColumn("Frame", myGTF.frame.toArray());
            model.addColumn("Attribute", myGTF.attributes.toArray());
            jTable1.setModel(model);//set model on table 
            jTable1.setVisible(true);//set table to be visible

            //set table column widths for optimum viewing
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(1000);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(50);
            myModel = jTable1.getModel();//get table model table and store it in the class variable 
            //System.out.println(myGTF.end_position.size());
            myGTF.gene_exon_hashmap(); //run this method on the myGTF object to obtain the correspondence between genes and their respective exons
            //System.out.println(myGTF.gene_exon_hash.toString());
            //System.out.println(myGTF.gene_exon_hash.size());
            //System.out.println(myGTF.gene_count);
            //System.out.println(myGTF.gene_lengths.toString());
            //System.out.println(String.valueOf(myGTF.average_gene_length));
            //System.out.println(String.valueOf(myGTF.gene_count) + " Gene count");
        } catch (IncorrectFileFormatError e) {
            JOptionPane.showMessageDialog(rootPane, "The file does not have the correct extension. Please check the input.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(rootPane, "File not selected.Please select file!", "Error", JOptionPane.ERROR_MESSAGE, null);
        }
        //System.out.println(String.valueOf(myGTF.average_num_exons + " Average num exons"));
        //System.out.println(myGTF.longest_gene_id + String.valueOf(myGTF.longest_gene_length));


    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        //This method performs the highlighting of the exons of the displayed fasta file 
        if (highlight_press != 0) {//if True, it means that the user has asked to highlight the same file twice, thus it raises an exception
            JOptionPane.showMessageDialog(rootPane, "You have asked to highlight the same fasta file again. Please close the program and reopen it with a different fasta file to visualise their respective exons");
            return;
        }

        highlight_hash = new HashMap<>();//initialise class variable

        try {//check if myGTF has been loaded and throw an exception if not True
            if (myGTF.file_location.isEmpty()) {
                throw new Exception();
            }
        } catch (Exception e) {//catch exception and report to user to upload GTF file 
            JOptionPane.showMessageDialog(rootPane, "There is not GTF file uploaded. Therefore no visualisation can take place ", "No GTF File uploaded error", JOptionPane.ERROR_MESSAGE);
            return;//exit method
        }

        try {//check if the FASTA file has been uploaded. Throw exception if not true
            if (myFasta.file_location.isEmpty()) {
                throw new Exception();
            }
        } catch (Exception e) {//catch exception and report to user
            JOptionPane.showMessageDialog(rootPane, "There is not Fasta file uploaded. Therefore no highlighting can take place ", "No fasta File uploaded error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // TODO add your handling code here:
        //System.out.println(highlight_hash.toString());
        positions_to_highlight();//run the positions to highlight method to store the exon start and end positions of each gene in the FASTA
        DefaultHighlighter.HighlightPainter cyanPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);//initialise highlighter
        int pointer_start = 0;//initalise method variable. Pointer start is initialised to the first index in the text area
        for (String key : myFasta.FileHeaderList) {//iterate through the list of headers in the inputted fasta file
            int loop_position = pointer_start;//initialise loop variable and set it to be at the position of the pointer
            loop_position += key.length() + 1;//increase loop position by the length of the sequence header followed by a new line character
            //System.out.println("The new gene pointer is at " + String.valueOf(loop_position));
            for (int i = 0; i < highlight_hash.get(key).get(0).size(); i++) {//iterate through the exon_start and Exon_end arraylists for the selected key
                try {
                    //highlight the exon
                    jTextArea1.getHighlighter().addHighlight(loop_position + highlight_hash.get(key).get(0).get(i), loop_position + highlight_hash.get(key).get(1).get(i) + 1, cyanPainter);
                    //System.out.println(loop_position + highlight_hash.get(key).get(0).get(i));
                    //System.out.println("End position" + String.valueOf(loop_position + highlight_hash.get(key).get(1).get(i)));

                } catch (BadLocationException ex) {//catch exception if inaccesible indexes are inputted in the highlighter
                    Logger.getLogger(GetUserInput.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(rootPane, "Fatal error encountered. Please restart the program", "FATAL Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            //System.out.print("Sequence length to be added equals" + myFasta.FastaHash.get(key).length());
            pointer_start = (loop_position + myFasta.FastaHash.get(key).length() + 1); /*Set the pointer to reflect the reading of thr first sequence in the fasta.
            This update allows the pointer to be initialised at the start of the next sequence header */
        }
        //else{JOptionPane.showMessageDialog(rootPane, "You have asked to highlight the same fasta file again. No updates will be made to the text area.Select different fasta file", "Select different file", JOptionPane.INFORMATION_MESSAGE);}

        /*Document doc=jTextPane1.getDocument();
        for(int i=0;i<doc.getLength();i++){
        if(i%150==0){
            try {
                doc.insertString(i, "\n", null);
            } catch (BadLocationException ex) {
                Logger.getLogger(GetUserInput.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }*/
        highlight_press++;//Increment this class variable to sigify that this method has been performed 
    }//GEN-LAST:event_jButton1ActionPerformed


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {//This method implements the gene visualisation functionality
            if (myGTF.file_location.isEmpty()) {//check if GTF file has been loaded 
                throw new Exception();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "There is not GTF file uploaded. Therefore no visualisation can take place ", "No GTF File uploaded error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int selected_row = jTable1.getSelectedRow();//Select the row that has been selected by the user
        try {
            if (selected_row == -1) {//if not row selected ask user to select row through raising an exception
                System.out.println("Index not selected");
                throw new Exception();
            } else {
                String start = myModel.getValueAt(selected_row, 3).toString();//get the chromosome value of the selected gene
                String end = myModel.getValueAt(selected_row, 4).toString();//get the start position of the selected gene
                String chr = myModel.getValueAt(selected_row, 0).toString();//get the end position of the selected gene
                String key_4 = chr + ":" + start + ":" + end;//create a key from the previous variables to access the GTF file gene_exon_hash variable
                ArrayList<Integer> ExonStart = (ArrayList<Integer>) myGTF.gene_exon_hash.get(key_4).get("Exon_start");//get the exon starts for the selected gene
                ArrayList<Integer> ExonEnd = (ArrayList<Integer>) myGTF.gene_exon_hash.get(key_4).get("Exon_end");//get the exon ends for the selcete gene
                //Set class variables for the ExonDisplayPanel object
                exonDisplayPanel1.gene_name = key_4;
                exonDisplayPanel1.gene_start = Integer.parseInt(start);
                exonDisplayPanel1.gene_end = Integer.parseInt(end);
                exonDisplayPanel1.exon_starts = ExonStart;
                exonDisplayPanel1.exon_ends = ExonEnd;
                exonDisplayPanel1.state = 1;
               //call the paintComponent method through calling repaint
                exonDisplayPanel1.repaint();
                //clear table selection
                jTable1.clearSelection();
                jTabbedPane1.setSelectedIndex(3);//navigate user to gene display pane
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "It appears you have selected a row which does not designate a gene. Please only select rows which denotes a gene", "Incorrect row selected", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No row selected. Please select the gene row for which you'd like the model to be displayed", "No index selected error", JOptionPane.INFORMATION_MESSAGE);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // This button outputs the statistics for the GTF file
        myGTF.average_exons();//call the average exons method on the GTF file object
        jTextArea3.append("Average Number of Exons per gene" + ": " + Double.toString(round(myGTF.average_num_exons * 100.0) / 100.0) + "\n");//Append relevant class variables to the text area 
        myGTF.gene_model_statistics(); //call gene_model_statistics method on the GTF file object
        //Append relevant class variables to the text area 
        jTextArea3.append("Longest Gene in GTF file has the length: " + Integer.toString(myGTF.longest_gene_length) + "." + "The Gene ID for this gene is " + myGTF.longest_gene_id + ".\n");
        jTextArea3.append("Shortest Gene in the GTF file has the length: " + Integer.toString(myGTF.shortest_gene_length) + "." + "The Gene ID for this gene is" + myGTF.shortest_gene_id + ".\n");
        myGTF.gene_length_statistics();//calculate average gene length through the gene_length_statistics method on the GTF file object
        jTextArea3.append("The average gene length in this GTF file is " + Double.toString(round(myGTF.average_gene_length * 100.0) / 100.0));//Append average length to the text area 

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here: 
        double averagefastasequencelength = myFasta.sequence_length();//initialise and store average fasta sequence length in a new method variable
        ArrayList<Double> gcContent = myFasta.GC_content();//initialise new method variable and set it equal to the myFasta GC content class variable
        jTextArea2.append("The average sequence length of this FASTA file is " + Double.toString(round(averagefastasequencelength * 100.0) / 100.0) + ".\n");//Append relevant information to text area
        for (int i = 0; i < gcContent.size(); i++) {//loop through the GC content array and append the GC content of each sequence to the output text area
            jTextArea2.append("The " + Integer.toString(i + 1) + " sequence in the file has a GC content of " + Double.toString(round(gcContent.get(i) * 100.0) / 100.0) + "% \n");
        }
        //Append the average GC content of all sequences of fasta to the text area.
        jTextArea2.append("The average GC content of all sequences in the FASTA file is: " + Double.toString(round(gcContent.stream().mapToDouble(d -> d).average().getAsDouble() * 100.0) / 100.0) + "% ");
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {//This is the main method of the class 
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GetUserInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GetUserInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GetUserInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GetUserInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GetUserInput myUserInput = new GetUserInput();//create new GetUserInput object
                myUserInput.setExtendedState(myUserInput.MAXIMIZED_BOTH);//Set application to display in full screen
                myUserInput.setVisible(true);//Set visibilty to true to display program when run

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ExonDisplayPanel exonDisplayPanel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
