
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
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import static java.util.Arrays.asList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Siddharth
 */
public class GetUserInput extends javax.swing.JFrame implements ActionListener{
    
    gtfFile myGTF=new gtfFile();
    FastaFile myFasta=new FastaFile();
    HashMap<String,ArrayList<ArrayList<Integer>>> highlight_hash=new HashMap<>();
    TableModel myModel;

    

    
    /**
     * Creates new form GetUserInput
     */
    public GetUserInput() {
        initComponents();
    }
    
    
    public void positions_to_highlight(){
        myFasta.header_to_key();
        for (String key: myFasta.FastaGTFHash.keySet()){
            String key_2=myFasta.FastaGTFHash.get(key);
            HashMap<String,ArrayList> myHash=myGTF.gene_exon_hash.get(key_2);
            ArrayList<Integer> Start_postitions=  myHash.get("Exon_start");
            ArrayList<Integer> End_positions=   myHash.get("Exon_end");
            int gene_start_position=Integer.parseInt(key_2.split(":")[1]);
            for (int i=0;i<Start_postitions.size();i++){
                Start_postitions.set(i,Start_postitions.get(i)-gene_start_position);
                End_positions.set(i,End_positions.get(i)-gene_start_position);
            }
            ArrayList<ArrayList<Integer>> out_array_list= new ArrayList<>();
            out_array_list.add(Start_postitions);out_array_list.add(End_positions);
            highlight_hash.put(key,out_array_list);
            
            
            
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        exonDisplayPanel1 = new ExonDisplayPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setToolTipText("");
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setAutoscrolls(true);
        jScrollPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        jTable1.setCellSelectionEnabled(true);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(13, 13, 13))
        );

        jTabbedPane1.addTab("GTF File display", jPanel1);

        jTextArea1.setColumns(20);
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
                        .addGap(0, 603, Short.MAX_VALUE)
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

        javax.swing.GroupLayout exonDisplayPanel1Layout = new javax.swing.GroupLayout(exonDisplayPanel1);
        exonDisplayPanel1.setLayout(exonDisplayPanel1Layout);
        exonDisplayPanel1Layout.setHorizontalGroup(
            exonDisplayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 767, Short.MAX_VALUE)
        );
        exonDisplayPanel1Layout.setVerticalGroup(
            exonDisplayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 511, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Gene Model Display", exonDisplayPanel1);

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane1.setViewportView(jTextArea2);

        jTextArea3.setEditable(false);
        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setWrapStyleWord(true);
        jScrollPane4.setViewportView(jTextArea3);

        jButton3.setText("Calculate Statistics");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setText("GTF File Statistics");
        jTextField1.setBorder(null);

        jTextField2.setEditable(false);
        jTextField2.setText("FASTA file statistics");
        jTextField2.setBorder(null);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(20, 20, 20))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(32, 32, 32))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146))
        );

        jTabbedPane1.addTab("Statistics", jPanel3);

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

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

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
        StringBuffer out_string= new StringBuffer();
        FileDialog nameBox;
        nameBox=new FileDialog(this,"Open FASTA",FileDialog.LOAD);
        nameBox.setVisible(true);
        String fileDirectory=nameBox.getDirectory();
        String filename=nameBox.getFile();
        try{
        filename=fileDirectory.concat(filename);
        myFasta.file_location=filename;
            try {
                myFasta.readfasta();
            } catch (Exception ex) {
                Logger.getLogger(GetUserInput.class.getName()).log(Level.SEVERE, null, ex);
            }
        System.out.println(myFasta.FastaHash.size());
        for(String key : myFasta.FastaHash.keySet()){
            out_string.append(key).append("\n").append(myFasta.FastaHash.get(key)).append("\n");
            
        }
        jTextArea1.setText(out_string.toString());
        jTextArea1.setLineWrap(true);}
        catch(NullPointerException e){JOptionPane.showMessageDialog(rootPane, "File not found. Please select file", "Error", JOptionPane.ERROR_MESSAGE);} 
        //jTextPane1.getHighlighter()
        //System.out.println(myFasta.GC_content().toString());
        
        
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        FileDialog nameBox;
        DefaultTableModel model = new DefaultTableModel();
        nameBox=new FileDialog(this,"Open GTF",FileDialog.LOAD);
        nameBox.setVisible(true);
        String fileDirectory=nameBox.getDirectory();
        String filename=nameBox.getFile();
        try{filename=fileDirectory.concat(filename);
        if(!filename.endsWith(".gtf")){
            throw new FileNotFoundException();
        }
        myGTF.file_location=filename;
        myGTF.readGTF();
        model.addColumn("Chromosome", myGTF.chr_values.toArray());
        model.addColumn("Source",myGTF.source.toArray());
        model.addColumn("Feature",myGTF.feature.toArray());
        model.addColumn("Start",myGTF.start_position.toArray());
        model.addColumn("End",myGTF.end_position.toArray());
        model.addColumn("Score",myGTF.score.toArray());
        model.addColumn("Strand",myGTF.strand.toArray());
        model.addColumn("Frame",myGTF.frame.toArray());
        model.addColumn("Attribute",myGTF.attributes.toArray());
        jTable1.setModel(model);
        jTable1.setVisible(true);
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(1000);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(50);
        System.out.println(myGTF.end_position.size());
        myGTF.gene_exon_hashmap();
        myModel=jTable1.getModel();
        System.out.println(myGTF.start_position.toString());
        System.out.println(myGTF.end_position.toString());
        
        //System.out.println(myGTF.gene_lengths.toString());
        //System.out.println(String.valueOf(myGTF.average_gene_length));
        System.out.println(String.valueOf(myGTF.gene_count) + " Gene count");}
        catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(rootPane,e.getCause(), "Error", JOptionPane.ERROR_MESSAGE);}
        catch(NullPointerException e){
            JOptionPane.showMessageDialog(rootPane,"File not selected.Please select file!", "Error",JOptionPane.ERROR_MESSAGE,null);
        }
        //System.out.println(String.valueOf(myGTF.average_num_exons + " Average num exons"));
        //System.out.println(myGTF.longest_gene_id + String.valueOf(myGTF.longest_gene_length));
        
    
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        positions_to_highlight();
        DefaultHighlighter.HighlightPainter cyanPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);
        int pointer_start=0;
        for (String key:highlight_hash.keySet()){
            int loop_position=pointer_start;
            loop_position+=key.length()+1;
            for (int i=0;i<highlight_hash.get(key).get(0).size();i++){
                try {   
                    jTextArea1.getHighlighter().addHighlight(loop_position+highlight_hash.get(key).get(0).get(i), loop_position+highlight_hash.get(key).get(1).get(i)+1, cyanPainter);
                } catch (BadLocationException ex) {
                    Logger.getLogger(GetUserInput.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            pointer_start+=(loop_position + myFasta.FastaHash.get(key).length()+1);
            
        }
        pointer_start=0;
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
        

      
    }//GEN-LAST:event_jButton1ActionPerformed

    private String find_gene_name(String attr){
        Pattern my_Pattern=Pattern.compile("gene_name.*\\w*;");
        Matcher m= my_Pattern.matcher(attr);
        String return_string="";
        if (m.find()){
            return_string=m.group(1).substring(11);
        }
        return return_string;
    }
    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int selected_row=jTable1.getSelectedRow();
        if(selected_row==-1){
        System.out.println("Index not selected");
        }
        else{
            exonDisplayPanel1.exon_starts=null;
            exonDisplayPanel1.exon_ends=null;
            String start=myModel.getValueAt(selected_row,3).toString();
            String end=myModel.getValueAt(selected_row,4).toString();
            String chr=myModel.getValueAt(selected_row,0).toString();
            String key=chr+":"+start+":"+end;
            ArrayList<Integer> ExonStart=(ArrayList<Integer>) myGTF.gene_exon_hash.get(key).get("Exon_start");
            //System.out.println(ExonStart.toString());
            ArrayList<Integer> ExonEnd=(ArrayList<Integer>) myGTF.gene_exon_hash.get(key).get("Exon_end");
            exonDisplayPanel1.gene_name=key;
            exonDisplayPanel1.gene_start=Integer.parseInt(start);
            exonDisplayPanel1.gene_end=Integer.parseInt(end);
            exonDisplayPanel1.exon_starts=ExonStart;
            exonDisplayPanel1.exon_ends=ExonEnd;
            exonDisplayPanel1.state+=1;
            exonDisplayPanel1.repaint();
            }
            
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        myGTF.average_exons();
        jTextArea3.append("Average Number of Exons per gene" + ": "+ Double.toString(round(myGTF.average_num_exons*100.0)/100.0) + "\n");
        myGTF.gene_model_statistics();
        jTextArea3.append("Longest Gene in GTF file has the length: " + Integer.toString(myGTF.longest_gene_length) + "." + "The Gene ID for this gene is " + myGTF.longest_gene_id + ".\n");
        jTextArea3.append("Shortest Gene in the GTF file has the length: " + Integer.toString(myGTF.shortest_gene_length) + "." + "The Gene ID for this gene is" + myGTF.shortest_gene_id + ".\n");
        myGTF.gene_length_statistics();
        jTextArea3.append("The average gene length in this GTF file is " + Double.toString(round(myGTF.average_gene_length*100.0)/100.0));
        double averagefastasequencelength=myFasta.sequence_length();
        ArrayList<Double> gcContent=myFasta.GC_content();
        jTextArea2.append("The average sequence length of this FASTA file is " + Double.toString(round(averagefastasequencelength*100.0)/100.0) + ".\n");
        for(int i=0;i<gcContent.size();i++){
            jTextArea2.append("The " + Integer.toString(i+1) + " sequence in the file has a GC content of " + Double.toString(round(gcContent.get(i)*100.0)/100.0)+ "% \n");
        }
        jTextArea2.append("The average GC content of all sequences in the FASTA file is: " + Double.toString(round(gcContent.stream().mapToDouble(d->d).average().getAsDouble()*100.0)/100.0));
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
                GetUserInput myUserInput = new GetUserInput();
                myUserInput.setExtendedState(myUserInput.MAXIMIZED_BOTH);
                myUserInput.setVisible(true);
                //new GetUserInput().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ExonDisplayPanel exonDisplayPanel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
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
