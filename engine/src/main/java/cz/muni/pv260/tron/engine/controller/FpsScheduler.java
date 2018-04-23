package cz.muni.pv260.tron.engine.controller;

public class FpsScheduler extends AbstractScheduler {
	
	private int fps;
	
	public FpsScheduler(int fps) {
		this.fps = fps;
	}
	
	@Override
	protected void loop() {
		
		long startTime = System.currentTimeMillis();
		double expectedTime = 1000.0/fps;
		
		setChanged();
		notifyObservers((long)expectedTime);
		
		long passedTime = System.currentTimeMillis() - startTime;
		
		if (expectedTime - passedTime > 0) {
			try {
				Thread.sleep((long) (expectedTime - passedTime));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("FpsScheduler is behind schedule");
		}
		
	}
	
}
