import org.junit.Test;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import pacemaker.Wave;


public class WaveTest {

	@Test
	public void test() {
		double[] xData = new double[1000];
	    double[] yData = new double[1000];

	    for(int i=0; i<1000; i++){
	    	xData[i] = i;
	    }
	    
	    Wave wave = new Wave(200, 120, 320);
	    yData = wave.generateWave();
	 
	    for(int i=0; i<yData.length; i++)
	    	System.out.println(yData[i]);
	}

}
