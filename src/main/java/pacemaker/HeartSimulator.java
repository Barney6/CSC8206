package pacemaker;

import java.awt.event.*;
import java.util.Timer;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Integer;
/**
 * <p>Title:  模拟心电图</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: sbfqmqyhaha@163.com</p>
 *
 * <p>Company: </p>
 *
 * @author sbfqmq
 * @version 1.0
 */
public class HeartSimulator extends JFrame {
    public HeartSimulator() {
        this.setTitle("java图形程序设计");
        this.setSize(this.WIDTH, this.HEIGHT);
        this.setLocation((dim.width - this.WIDTH) / 2,
                         (dim.height - this.HEIGHT) / 2);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.add(panel);
    }
    java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit()
                             .getScreenSize();
    public static void main(String[] args) {
        HeartSimulator heart = new HeartSimulator();
        heart.setVisible(true);
        javax.swing.Timer timer=new javax.swing.Timer(100,new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e) {
             panel.repaint();
            }
        });
     timer.start();
     //javax.swing.JOptionPane.showMessageDialog(null, "OK");
    }
    public static final int WIDTH = 800;
    public static final int HEIGHT = 500;
   static MyPanel panel = new MyPanel();
}
class MyPanel extends JPanel{
    public MyPanel(){
        current=1;
        this.x=new LinkedList<Integer>();
        this.y=new LinkedList<Integer>();
    }
 public void paintComponent(Graphics g) {
     super.paintComponent(g);
      g.clearRect(SIZE,SIZE,WIDTH,HEIGHT);
      g.setColor(Color.BLACK);
      g.drawString("模拟心电图",SIZE+200,SIZE-20);
      g.setColor(Color.GREEN);
    //竖线
      g.drawLine(SIZE,HeartSimulator.HEIGHT-SIZE,SIZE,SIZE);
    //横线
      g.drawLine(SIZE,HeartSimulator.HEIGHT-SIZE,HeartSimulator.WIDTH-SIZE,HeartSimulator.HEIGHT-SIZE);
    //箭头
    g.setColor(Color.RED);
     int []x={SIZE-6,SIZE,SIZE+6};
     int []y={SIZE+8,SIZE,SIZE+8};
     g.drawPolyline(x,y,3);
     int []x2={HeartSimulator.WIDTH-SIZE-8,HeartSimulator.WIDTH-SIZE,HeartSimulator.WIDTH-SIZE-8};
     int []y2={HeartSimulator.HEIGHT-SIZE-6,HeartSimulator.HEIGHT-SIZE,HeartSimulator.HEIGHT-SIZE+6};
     g.drawPolyline(x2,y2,3);
     //
     current+=STEP;
     this.y.add(Digit.getDigit(SIZE,HeartSimulator.HEIGHT-SIZE));
  //   System.out.println(this.x);
  //   System.out.println(this.y);
     if(current>LENGTH){
         this.y.remove(0);
     }
     else{
          this.x.add(current+SIZE);
     }
       int j=0;
        //toArray();???????????
       for(Integer elem:(this.x)){
              xx[j++]=elem.intValue();
      }
      j=0;
      for(Integer elem:(this.y)){
          yy[j++]=elem.intValue();
      }
      g.drawPolyline(xx,yy,this.x.size());
 }
 private final int SIZE=100;
 private final int LENGTH=600;
 private final int STEP=5;
 private int current=1;
 private List<Integer> x;
 private List<Integer> y;
 private int []xx=new int[1000];
 private int []yy=new int[1000];
}
class Digit{
    public static int getDigit(int min,int max){
        max=max-min;
       java.util.Random ran=new java.util.Random();
        double rand=ran.nextDouble();
        return (int)(rand*max+min);
    }
}