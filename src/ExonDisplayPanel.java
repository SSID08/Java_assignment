
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
/**
 *
 * @author Siddharth
 */
public class ExonDisplayPanel extends javax.swing.JPanel {

    //initialise class variables
    int state = 0;//Class variable to track whether paintComponent is called directly or through repaint
    int gene_start;
    int gene_end;
    ArrayList<Integer> exon_starts = new ArrayList<>();
    ArrayList<Integer> exon_ends = new ArrayList<>();
    ArrayList<Double> exon_start_convert = new ArrayList<>();
    ArrayList<Double> exon_end_convert = new ArrayList<>();
    String gene_name;

    /**
     * Creates new form ExonDisplayPanel
     */
    public ExonDisplayPanel() {
        initComponents();
    }

    public void convert_exon_positions() {//Method to convert exon positions relative to gene start positions
        double gene_length = gene_end - gene_start;//Calculate gene length of the gene model selected by the user
        for (int i = 0; i < exon_starts.size(); i++) {//Iterate through the exon array
            //Represent start and end positions as a proportion of total gene length
            double start = (exon_starts.get(i) - gene_start) / gene_length;
            double end = (exon_ends.get(i) - gene_start) / gene_length;
            exon_start_convert.add(start);//Add converted exon start and end positions to their respective arrays
            exon_end_convert.add(end);
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

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void paintComponent(Graphics g) {//The paintComponent method is overidden to allow for drawing of the gene models. 
        if (state == 0) {//if state is 0; paintComponent is not called through repaint but by the default paintcomponent method. Therefore do nothing.
        } else {//if state is not 0; the repaint method has been called
            super.paintComponent(g);//call the default paintComponent 
            convert_exon_positions();//Call the method to convert exon positions relative to gene length
            g.drawRect(30, 200, 1000, 50);//Draw the gene
            g.setFont(new Font("Times Roman", Font.PLAIN, 20));
            g.drawString(gene_name, 30, 100);//Draw a string to show the gene name as a concatenation of chromosome, gene start position and gene end position
            g.setFont(new Font("Helvetica", Font.PLAIN, 12));
            g.drawString("Gene and Exon start positions", 1050, 200);//Draw the start and end positions of the gene
            g.drawString("Gene and Exon end positions", 1050, 250);
            g.setColor(Color.red);//Set a new color to highlight the exons
            for (int i = 0; i < exon_start_convert.size(); i++) {//iterate through the exons
                int start = (int) (30 + exon_start_convert.get(i) * 1000);//set exon start pointer relative to gene start and in proportion with represented gene length
                int end = (int) (30 + exon_end_convert.get(i) * 1000);//set exon start pointer relative to gene start and in proportion with represented gene length
                g.fillRect(start, 200, end - start, 50);//Fill rectangle in the region of the exons
                g.setColor(Color.BLACK);//set color back to black
                g.setFont(new Font("Times Roman", Font.PLAIN, 7));
                //Draw the gene and exon start end positions next to the model 
                g.drawString(Integer.toString(gene_start), 20, 180);
                g.drawString(Integer.toString(gene_end), 1020, 270);
                if(exon_starts.get(i)!=gene_start){
                g.drawString(exon_starts.get(i).toString(), start, 180);}
                if(exon_ends.get(i)!=gene_end){
                g.drawString(exon_ends.get(i).toString(), end, 270);}
                g.setColor(Color.red);//Set color back to red
            }
            
            //System.out.println(gene_start);
            //System.out.println(exon_starts.toString());
            //System.out.println(exon_ends.toString());
            //Clear the class variables     
            exon_start_convert.clear();
            exon_end_convert.clear();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
