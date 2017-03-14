package pacemaker;

import java.util.LinkedList;
import java.util.List;
import javax.swing.SwingWorker;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class LivePlotting {

	public MySwingWorker mySwingWorker;
	public SwingWrapper<XYChart> sw;
	public Chart chart;
	
	
	public LivePlotting(String Title)
	{
		chart = new Chart(Title);		
		sw = new SwingWrapper<XYChart>(chart.XYchart);
		sw.displayChart();
				
	}
	
	public void run()
	{
		mySwingWorker = new MySwingWorker();
		mySwingWorker.execute();
	}
	
	
	public class MySwingWorker extends SwingWorker<Boolean, double[]> {
		
		

		LinkedList<Double> fifo = new LinkedList<Double>();

		public MySwingWorker() {

			fifo.add(0.0);
		}

		@Override
		protected Boolean doInBackground() throws Exception {

			int iterator = 0;
			while (!isCancelled()) {

				fifo.add(chart.heartbeat.get(iterator));

				if (fifo.size() > chart.heartbeat.size()) {
					fifo.removeFirst();
				}

				double[] array = new double[fifo.size()];
				for (int i = 0; i < fifo.size(); i++) {
					array[i] = fifo.get(i);
				}
				publish(array);

				iterator++;
				if (iterator == chart.heartbeat.size()) {
					iterator = 0;
				}

			}

			return true;
		}

		@Override
		protected void process(List<double[]> chunks) {

			double[] mostRecentDataSet = chunks.get(chunks.size() - 1);

			chart.XYchart.updateXYSeries("randomWalk", null, mostRecentDataSet, null);
			sw.repaintChart();


		}
	}


}
