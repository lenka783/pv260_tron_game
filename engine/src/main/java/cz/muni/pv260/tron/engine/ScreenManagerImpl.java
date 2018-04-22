package cz.muni.pv260.tron.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

public class ScreenManagerImpl implements ScreenManager {
	
	private static final DisplayMode modes[] = {
			//new DisplayMode(1920,1080,32,0),
			new DisplayMode(1680,1050,32,0),
			//new DisplayMode(1280,1024,32,0),
			new DisplayMode(800,600,32,0),
			new DisplayMode(800,600,24,0),
			new DisplayMode(800,600,16,0),
			new DisplayMode(640,480,32,0),
			new DisplayMode(640,480,24,0),
			new DisplayMode(640,480,16,0),
	};
	
	private GraphicsDevice graphicsDevice;
	
	public ScreenManagerImpl(){
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
	}
	
	
	@Override
	public Window initWindow(){
		DisplayMode dm = findFirstCompatibaleMode();
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.setIgnoreRepaint(true);
		frame.setResizable(false);
		graphicsDevice.setFullScreenWindow(frame);
		frame.createBufferStrategy(2);
		
		if(dm != null && graphicsDevice.isDisplayChangeSupported()){
			try {
				graphicsDevice.setDisplayMode(dm);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		return graphicsDevice.getFullScreenWindow();
	}
	
	@Override
	public Window getWindow(){
		return graphicsDevice.getFullScreenWindow();
	}
	
	@Override
	public void restoreWindow() {
		Window window = graphicsDevice.getFullScreenWindow();
		if(window != null){
			window.dispose();
		}
		graphicsDevice.setFullScreenWindow(null);
	}
	
	@Override
	public Graphics getGraphics(){
		Window window = graphicsDevice.getFullScreenWindow();
		if(window != null){
			BufferStrategy bs = window.getBufferStrategy();
			if(bs != null){
				return bs.getDrawGraphics();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	@Override
	public void update() {
		getGraphics().dispose();
		Window window = graphicsDevice.getFullScreenWindow();
		if(window != null){
			BufferStrategy bs = window.getBufferStrategy();
			if(!bs.contentsLost()){
				bs.show();
			}
		}
	}
	
	private DisplayMode findFirstCompatibaleMode(){
		DisplayMode compatibleModes[] = graphicsDevice.getDisplayModes();
		for(int x = 0; x < modes.length; x++){
			for(int y = 0; y < compatibleModes.length; y++){
				if(displayModesMatch(modes[x],compatibleModes[y])){
					return modes[x];
				}
			}
		}
		return null;
	}
	
	private boolean displayModesMatch(DisplayMode m1, DisplayMode m2){
		if(m1.getWidth() != m2.getWidth() || m1.getHeight() != m2.getHeight()){
			return false;
		}
		if(m1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI
				&& m2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI
				&& m1.getBitDepth() != m2.getBitDepth()){
			return false;
		}
		if(m1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN
				&& m2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN
				&& m1.getRefreshRate() != m2.getRefreshRate()){
			return false;
		}
		return true;
	}
	
	public int getWidth(){
		Window window = graphicsDevice.getFullScreenWindow();
		if(window != null){
			return window.getWidth();
		}else{
			return 0;
		}
	}
	
	public int getHeight(){
		Window window = graphicsDevice.getFullScreenWindow();
		if(window != null){
			return window.getHeight();
		}else{
			return 0;
		}
	}
	
}
