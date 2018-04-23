package cz.muni.pv260.tron.engine.presentation;


import java.awt.*;

public interface ScreenManager {
	
	Window initWindow();
	
	Window getWindow();
	
	void restoreWindow();
	
	Graphics getGraphics();
	
	void update();
	
}
