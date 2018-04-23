package cz.muni.pv260.tron.engine.controller;

import java.util.Observable;

public abstract class Scheduler extends Observable {

	public abstract void start();
	
	public abstract void stop();
	
}
