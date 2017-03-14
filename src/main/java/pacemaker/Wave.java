package pacemaker;

import java.util.ArrayList;
import java.util.List;

public class Wave {
	private int time=1000;
	private int P = 100;
	private int QRS = 120;
	private int T = 160;
	private int STSeg;
	private int PRSeg;
	private int remainder;
	List<Double> wave = new ArrayList<Double>();
	
	public Wave(int PRSeg, int STSeg) {
		this.PRSeg = PRSeg;
		this.STSeg = STSeg;
		remainder = time-P-PRSeg-QRS-STSeg-T;
	}
	
	public List<Double> generateWave(){
		wave.clear();
		generateFlat(remainder);
		generateP(true);
		generateFlat(PRSeg);
		generateQRS(true);
		generateFlat(STSeg);
		generateT(true);
		return wave;
	}
	
	public List<Double> generateNormal(){
		wave.clear();
		generateFlat(360);
		generateP(true);
		generateFlat(100);
		generateQRS(true);
		generateFlat(160);
		generateT(true);
		return wave;
	}
	
	public List<Double> generateFast(int time){
		wave.clear();
		if(time<10)
			time=10;
		else if(time>300)
			time=300;
		
		generateFlat(time);
		generateP(true);
		generateFlat(100);
		generateQRS(true);
		generateFlat(160);
		generateT(true);
		return wave;
	}
	
	public List<Double> generateSlow(int time){
		wave.clear();
		if(time<400)
			time=400;
		
		generateFlat(time);
		generateP(true);
		generateFlat(100);
		generateQRS(true);
		generateFlat(160);
		generateT(true);
		return wave;
	}
	
	public List<Double> generateAAI(){
		wave.clear();
		generateFlat(360);
		generateP(true);
		generateFlat(100);
		generateQRS(false);
		generateFlat(160);
		generateT(true);
		return wave;
	}
	
	private void generateFlat(int time){
		for(int i=0; i<time; i++){
			wave.add(0.0);
		}
	}
	
	private void generateP(boolean positive){
		for(int i=0; i<P; i++){
			int tp = P;
			wave.add((double) ((positive ? 1 : -1)*(200/tp/2) * (tp/2 - Math.abs(i % (2*tp/2) - tp/2))));
		}
	}
	
	private void generateQRS(boolean positive){
		for(int i=0; i<QRS; i++){
			int tp = QRS;
			wave.add((double) ((positive ? 1 : -1)*(1000/tp/2) * (tp/2 - Math.abs(i % (2*tp/2) - tp/2))));
		}
	}
	
	private void generateT(boolean positive){
		for(int i=0; i<T; i++){
			int tp = T;
			wave.add((double) ((positive ? 1 : -1)*(500/tp/2) * (tp/2 - Math.abs(i % (2*tp/2) - tp/2))));
		}
	}
}





