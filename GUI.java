import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;

class Dimensions{
    int x,y,length,height;

    Dimensions( int length, int height){
        this.length = length;
        this.height = height;
    }

    public int getLength(){
        return length;
    }

    public int getHeight(){
        return height;
    }
}

class HanoiGUI extends Frame{

    private Map<Integer, Dimensions> rectangles = new HashMap<Integer, Dimensions>();
    private ArrayList<Integer> source = new ArrayList<Integer>();
    private ArrayList <Integer> auxiliary = new ArrayList<Integer>();
    private ArrayList <Integer> destination = new ArrayList<Integer>();
    private ArrayList<Integer> to = new ArrayList<Integer>();
    private ArrayList<Integer> from = new ArrayList<Integer>();
    private ArrayList<Integer> disk = new ArrayList<Integer>();
    private int count; 
    
    HanoiGUI(int n){
        super("Tower of Hanoi");

        Button next = new Button("Next");  
        next.setBounds(350,400,50,30);
        add(next);
        Button reset = new Button("Reset");
        reset.setBounds(400, 400, 50, 30);
        add(reset);

        for(int i=0;i<n;i++){
            rectangles.put(i+1,new Dimensions(50+i*20, 10));
            source.add(n-i);
        }

        allConfiguration(n);

        count = 0;
        next.addActionListener(new ActionListener(){  
            @Override
            public void actionPerformed(ActionEvent e){
                count++;
                configurationPegs(n, count);
                for(int i=0;i<source.size();i++){
                    System.out.print(source.get(i));
                }
                //.setText("Welcome to Javatpoint.");
            }
        });

        setSize(800,500);
        setLayout(null);
        setVisible(true);
    }

    public void towerOfHanoi(int src, int des,int aux, int n){
        if(n>0){
            towerOfHanoi(src, des, aux,n-1);
            disk.add(n);
            to.add(aux);
            from.add(src);
            System.out.println(n + " " +src + " "+aux);
            
            towerOfHanoi(des,src,aux,n-1);
            System.out.println(n + " " +aux + " "+des);
            disk.add(n);
            to.add(des);
            from.add(aux);
            
            towerOfHanoi(src,des,aux,n-1);
        }
    }

    private void allConfiguration(int n){
        towerOfHanoi(0, 2, 1, n);
    }

    public void configurationPegs(int n, int i){
        switch(to.get(i)){
            case 0 : source.add(disk.get(i));
                     break;
            case 1 : auxiliary.add(disk.get(i));
                     break;
            case 2 : destination.add(disk.get(i));
                     break;
        }

        switch(from.get(i)){
            case 0 : source.remove(source.size() - 1);
                     break;
            case 1 : auxiliary.remove(auxiliary.size() - 1);
                     break;
            case 2 : destination.remove(destination.size() - 1);
                     break;
        }
    }

    public void paint(Graphics g){
        super.paint(g);

        //Base
        g.setColor(new Color(102,102,102));
        g.fillRect(30, 350, 740,3);
        g.fillRect(160, 70, 3, 280);
        g.fillRect(400, 70, 3, 280);
        g.fillRect(640, 70, 3, 280);
        
        g.setColor(new Color(0,0,0));

        //Source
        for(int i=0;i<source.size();i++){
            int width = rectangles.get(source.get(i)).getLength();
            int height = rectangles.get(source.get(i)).getHeight();
            g.fillRect(160-width/2, 350 - (i+1)*height -(i+1)*2, width,height);
        }

         //Destination
         for(int i=0;i<destination.size();i++){
            int width = rectangles.get(destination.get(i)).getLength();
            int height = rectangles.get(destination.get(i)).getHeight();
            g.fillRect(640-width/2, 350 - (i+1)*height -(i+1)*2, width,height);
        }

         //Auxiliary
         for(int i=0;i<auxiliary.size();i++){
            int width = rectangles.get(auxiliary.get(i)).getLength();
            int height = rectangles.get(auxiliary.get(i)).getHeight();
            g.fillRect(400-width/2, 350 - (i+1)*height -(i+1)*2, width,height);
        }

        // try{
        //     Thread.sleep(1000);
        // } 
        // catch(Throwable e){
        //     System.out.println(e.getMessage()); 
        // }
    }


    public static void main(String[] args){
        HanoiGUI han = new HanoiGUI(5);
    }
}
