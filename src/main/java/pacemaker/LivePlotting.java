package pacemaker;

import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingWorker;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

/**
 * Creates a real-time chart using SwingWorker
 */
public class LivePlotting {

 private MySwingWorker mySwingWorker;
 private SwingWrapper<XYChart> sw;
 private XYChart chart;
 private double[] heartbeat;
 public int interval;
 
   
  public void run() 
  {
    // Create Chart
    chart = QuickChart.getChart("Heart Beat Demo", "Time", "Heart Beat", "randomWalk", new double[] { 0 }, new double[] { 0 });
    chart.getStyler().setLegendVisible(false);
    chart.getStyler().setXAxisTicksVisible(false);

    // Show it
    sw = new SwingWrapper<XYChart>(chart);
    sw.displayChart();

    mySwingWorker = new MySwingWorker();
    mySwingWorker.execute();
  }
  
  public void setHeartBeat(double[] array)
  {
	  heartbeat = array;	  
  }
  
  public void setIntervalTime(int second)
  {
	  interval = second;	  
  }
  

  private class MySwingWorker extends SwingWorker<Boolean, double[]> 
  {

    LinkedList<Double> fifo = new LinkedList<Double>();

    public MySwingWorker() {

      fifo.add(0.0);
    }

    @Override
    protected Boolean doInBackground() throws Exception {

      int iterator = 0;
      while (!isCancelled()) {
    	  
        fifo.add(heartbeat[iterator]);
        
        if (fifo.size() > heartbeat.length) {
          fifo.removeFirst();
        }

        double[] array = new double[fifo.size()];
        for (int i = 0; i < fifo.size(); i++) {
          array[i] = fifo.get(i);
        }
        publish(array);
        
        iterator++;
        if(iterator == heartbeat.length)
        {
        	iterator = 0;
        }

      }

      return true;
    }

    @Override
    protected void process(List<double[]> chunks) {

      double[] mostRecentDataSet = chunks.get(chunks.size() - 1);

      chart.updateXYSeries("randomWalk", null, mostRecentDataSet, null);
      sw.repaintChart();

      try {
        Thread.sleep(interval); // 40 ms ==> 2.5fps
      } catch (InterruptedException e) {
      }

    }
  }
  
}