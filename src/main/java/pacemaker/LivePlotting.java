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

  MySwingWorker mySwingWorker;
  SwingWrapper<XYChart> sw;
  XYChart chart;

  public static void main(String[] args) throws Exception {

	LivePlotting swingWorkerRealTime = new LivePlotting();
    swingWorkerRealTime.go();
  }

  private void go() {

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

  private class MySwingWorker extends SwingWorker<Boolean, double[]> {

    LinkedList<Double> fifo = new LinkedList<Double>();
    //double[] time = {0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};
    double[] heartbeat = {0.0, 0.0, 0.0, 3.0, 3.0, 3.0, 3.0, 0.0, 0.0, 0.0};

    public MySwingWorker() {

      fifo.add(0.0);
    }

    @Override
    protected Boolean doInBackground() throws Exception {

      int iterator = 0;
      while (!isCancelled()) {
    	  
        fifo.add(heartbeat[iterator]);
        
        if (fifo.size() > 40) {
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
        	
        try {
          Thread.sleep(5);
        } catch (InterruptedException e) {
          // eat it. caught when interrupt is called
          System.out.println("MySwingWorker shut down.");
        }

      }

      return true;
    }

    @Override
    protected void process(List<double[]> chunks) {

      double[] mostRecentDataSet = chunks.get(chunks.size() - 1);

      chart.updateXYSeries("randomWalk", null, mostRecentDataSet, null);
      sw.repaintChart();

      long start = System.currentTimeMillis();
      long duration = System.currentTimeMillis() - start;
      try {
        //Thread.sleep(40 - duration); // 40 ms ==> 25fps
        Thread.sleep(1000 - duration); // 40 ms ==> 2.5fps
      } catch (InterruptedException e) {
      }

    }
  }
}