package cz.muni.pv260.tron.engine.controller;

public abstract class AbstractScheduler extends Scheduler {
	
	private boolean running = false;
	
	@Override
	public void start() {
		running = true;
		while (running) {
			loop();
		}
	}
	
	protected abstract void loop();
	
	@Override
	public void stop() {
		running = false;
	}
	
}
