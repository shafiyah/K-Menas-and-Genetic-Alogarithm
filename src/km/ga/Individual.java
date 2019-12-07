package km.ga;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.lang.*;
import static java.lang.Math.sqrt;
import java.util.Comparator;
public class Individual {
    int fitness=0;
    int [] gen;
    int genLengh;
    int dataset[][];
    int minX,minY,maxX,maxY;
  
    public Individual(int centroid) {
        this.dataset=new int [75][3];
        this.fitness=0;
        readData();
        setMinMax();
        this.genLengh=centroid*2;
        this.gen=new int [this.genLengh];
         Random rn = new Random();
        for(int i=0;i<this.genLengh;i++){
          if(i%2==0){
              this.gen[i]=rn.nextInt((maxX - minX )+ 1) + 1;
          }else{
              this.gen[i]=rn.nextInt((maxY - minY )+ 1) + 1;
          }  
        }
        calculateFitness();  
    }
   
    
    public void readData(){
        String Location="D:/PENS/SMT05/Mesin Learning/praktikum/KM-GA/src/km/ga/ruspini.csv";
         BufferedReader br;
        String line;
        try {
            int i=0;
            br=new BufferedReader(new FileReader(Location));
            while((line=br.readLine())!=null){
                String[] ruspini=line.split(",");    
                for(int j=0;j<3;j++){
                    dataset[i][j] = Integer.parseInt(ruspini[j]);
                   // System.out.print(dataset[i][j]);
                }
                i++;   
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setMinMax(){
       minX=dataset[1][0];maxX=0;minY=dataset[1][1];maxY=0;
       for(int i=0;i<dataset.length;i++){
          this.minX=cekMinimal(minX,dataset[i][0]);
          this.maxX=cekMaximal(maxX,dataset[i][0]);
          this.minY=cekMinimal(minY,dataset[i][1]);
          this.maxY=cekMaximal(maxY,dataset[i][1]);
        }   
      // System.out.println("minX = "+minX+" maxX = "+maxX+" minY = "+minY+" maxY="+maxY);
    }
    
     public int cekMinimal(int min,int minBaru){
        if(minBaru < min)
            min=minBaru;
        return min;
    }
    
    public int cekMaximal(int max,int maxBaru){
        if(maxBaru > max)
            max=maxBaru;
        return max;
    }
    
    
    public static Comparator<Individual> fitnessComparator = new Comparator<Individual>(){
        @Override
        public int compare(Individual o1, Individual o2) {
            int fitnesno1 = o1.getFitness();
	    int fitnesno2 = o2.getFitness();
            
            return fitnesno2-fitnesno1;
            
        }
    };
    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public float getFitnesss() {
        return this.fitness;
    }

    public void setFitnesss(int fitness) {
        this.fitness = fitness;
    }
    

    public int[] getGen() {
        return this.gen;
    }

    public void setGen(int[] gen) {
        this.gen = gen;
    }
    
    
    public int getGen(int i) {
        return this.gen[i];
    }

    public void setGen(int i , int value) {
        this.gen[i] = value;
    }

    public int getGenLengh() {
        return genLengh;
    }

    public void setGenLengh(int genLengh) {
        this.genLengh = genLengh;
    }
    
    public void printGen() {
        for(int i=0;i<genLengh;i++){
           System.out.print(this.gen[i]+" ");
        }
       System.out.print(" Fitnes = "+getFitness());
       System.out.println("");
    }

    public double getDistance(int indexDataset, int indexGen){
        double distance = sqrt(((dataset[indexDataset][0]-this.gen[indexGen])*
                                (dataset[indexDataset][0]-this.gen[indexGen]))+
                                ((dataset[indexDataset][1]-this.gen[indexGen+1])*
                                (dataset[indexDataset][1]-this.gen[indexGen+1])));
       return distance;  
    }
    
    public void calculateFitness(){
        double minDistance=0.0;
        int M = 10000;
        for (int i = 0; i < dataset.length; i++) {
          double min=10000; 
          for(int j=0;j< this.genLengh;j+=2){
              double distance=getDistance(i,j);
               if(distance < min){
                 min=distance;
               }
            }
             minDistance+=min;
        }
       // System.out.println("min J = "+minDistance);
        this.fitness=(M-(int)minDistance);
        //System.out.println(this.fitness);
    }
    
    
   public void convertString(){  
      char[] huruf = {' ','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
      for(int i=0;i<this.gen.length;i++){
          System.out.print(huruf[gen[i]]);
      }
     System.out.println("");
   }

    public int[][] getDataset() {
        return dataset;
    }

    public void setDataset(int[][] dataset) {
        this.dataset = dataset;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }
    
}
