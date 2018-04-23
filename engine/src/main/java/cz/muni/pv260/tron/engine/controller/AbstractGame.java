package cz.muni.pv260.tron.engine.controller;

import cz.muni.pv260.tron.engine.model.Room;
import cz.muni.pv260.tron.engine.presentation.Presenter;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public abstract class AbstractGame implements Game, Observer {
	
	private Presenter presenter;
	private Scheduler scheduler;
	
	@Override
	public void start(){
		try {
			init();
			scheduler.addObserver(this);
			scheduler.start();
		} finally {
			terminate();
		}
	}
	
	protected void init(){
		presenter = initPresenter();
		scheduler = initScheduler();
	}
	
	protected abstract Presenter initPresenter();
	
	protected Scheduler initScheduler() {
		return new FpsScheduler(30);
	}
	
	public void update(Observable scheduler, Object timePassed) {
		presenter.update((Long) timePassed);
	}
	
	public void stop(){
		scheduler.stop();
	}
	
	protected void terminate(){
		scheduler.stop();
		presenter.terminate();
	}
	
}
