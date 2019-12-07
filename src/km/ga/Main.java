package km.ga;

import graph.SimpleGraph;
import java.awt.Color;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;

public class Main {
    public static int generation=0;
    public static int idividualSize=0;
    public static void main(String[] args) {
       SimpleGraph graph = new SimpleGraph(); 
       graph.setGridSpreadX(10);
       graph.setGridSpreadY(10);
       int clasterSize=4;
       graph.display();
       input();
       KmeansGenetikAlogarithm demo = new KmeansGenetikAlogarithm(clasterSize,idividualSize,generation,graph);
       int i=0;
        while (i<generation) { 
           graph.clearPoints();
           System.out.println("generation = "+i); 
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                     demo.test(); 
                    demo.centroidPoint();
                    demo.dataSetPoint();
                    graph.repaint();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                }
            });
            t.run();
            i++;
        }
        demo.hasil();
        
        demo.centroidPoint();
        
    }
     public static  void input(){
       Scanner input = new Scanner(System.in);
       System.out.print("masukan jumlah individu:");
       idividualSize=Integer.parseInt(input.nextLine());
       System.out.print("masukan jumlah generasi :");
       generation=Integer.parseInt(input.nextLine());
      
    }
    
}


      
   