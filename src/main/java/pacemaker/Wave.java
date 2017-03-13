package pacemaker;

public class Wave {
	private int time=1000;
	private int PR;
	private int QRS;
	private int ST;
	private int remainder;
	private double [] y = new double [1000];
	
	public Wave(int PR, int QRS, int ST) {
		this.PR = PR;
		this.QRS = QRS;
		this.ST = ST;
		remainder = time-PR-QRS-ST;
	}

	public double[] normalBeat(boolean aai){
		for(int i=0; i<remainder; i++){
			y[i]=0;
		}
		for(int i=0; i<PR/2; i++){
			int tp = PR/2;
			y[i+remainder] = (200/tp/2) * (tp/2 - Math.abs(i % (2*tp/2) - tp/2));
		}
		for(int i=0; i<PR; i++)
			y[i+remainder+PR/2]=0;
		for(int i=0; i<PR; i++)
			y[i+remainder+PR]=0;
		for(int i=0; i<QRS; i++){
			int tp = QRS;
			y[i+remainder+PR] = (aai ? -1 : 1)*(1000/tp/2) * (tp/2 - Math.abs(i % (2*tp/2) - tp/2));
		}
		
		for(int i=0; i<ST/2; i++){
			y[i+remainder+PR+QRS] = 0;
		}
		for(int i=0; i<ST/2; i++){
			int tp = ST/2;
			y[i+remainder+PR+QRS+ST/2] = (500/tp/2) * (tp/2 - Math.abs(i % (2*tp/2) - tp/2));
		}

		return this.y;
	}
	
	
	/**
	 * @return the pR
	 */
	public int getPR() {
		return PR;
	}

	/**
	 * @return the qRS
	 */
	public int getQRS() {
		return QRS;
	}

	/**
	 * @return the sT
	 */
	public int getST() {
		return ST;
	}

	/**
	 * @param pR the pR to set
	 */
	public void setPR(int pR) {
		PR = pR;
	}

	/**
	 * @param qRS the qRS to set
	 */
	public void setQRS(int qRS) {
		QRS = qRS;
	}

	/**
	 * @param sT the sT to set
	 */
	public void setST(int sT) {
		ST = sT;
	}
}
	