package cz.muni.pv260.tron.game.controller;

import cz.muni.pv260.tron.engine.controller.AbstractGame;
import cz.muni.pv260.tron.engine.presentation.Presenter;
import cz.muni.pv260.tron.game.presentation.ArenaPresenter;

public class Tron extends AbstractGame {

	public static void main(String[] args) {
		new Tron().start();
	}
	
	@Override
	public Presenter initPresenter() {
		return new ArenaPresenter();
	}
}
