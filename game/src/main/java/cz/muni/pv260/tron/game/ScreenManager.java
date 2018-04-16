package cz.muni.pv260.tron.game;

import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

public class ScreenManager {
	
	private GraphicsDevice graphicsDevice;
	
	public ScreenManager(){
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
	}
	
	public List<DisplayMode> getCompatibleDisplayModes(){
		return Arrays.asList(graphicsDevice.getDisplayModes());
	}
	
	public DisplayMode findFirstCompatibaleMode(DisplayMode[] modes){
		
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
	
	public DisplayMode getCurrentDM(){
		return graphicsDevice.getDisplayMode();
	}
	
	public boolean displayModesMatch(DisplayMode m1, DisplayMode m2){
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
	
	public void setFullScreen(DisplayMode dm){
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.setIgnoreRepaint(true);
		frame.setResizable(false);
		graphicsDevice.setFullScreenWindow(frame);
		
		if(dm != null && graphicsDevice.isDisplayChangeSupported()){
			try {
				graphicsDevice.setDisplayMode(dm);
			} catch(Exception ex) {
				// TODO
			}
			frame.createBufferStrategy(2);
		}
	}
	
	public Graphics2D getGraphics(){
		Window window = graphicsDevice.getFullScreenWindow();
		if(window != null){
			BufferStrategy bs = window.getBufferStrategy();
			return (Graphics2D)bs.getDrawGraphics();
		}
		else{
			return null;
		}
	}
	
	public void update(){
		Window window = graphicsDevice.getFullScreenWindow();
		if(window != null){
			BufferStrategy bs = window.getBufferStrategy();
			if(!bs.contentsLost()){
				bs.show();
			}
		}
	}
	
	public Window getFullScreenWindow(){
		return graphicsDevice.getFullScreenWindow();
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
	
	public void restoreScreen(){
		Window window = graphicsDevice.getFullScreenWindow();
		if(window != null){
			window.dispose();
		}
		graphicsDevice.setFullScreenWindow(null);
	}
	
	public BufferedImage createCompatibaleImage(int width, int height, int transparency){
		Window window = graphicsDevice.getFullScreenWindow();
		if(window != null){
			GraphicsConfiguration gc = window.getGraphicsConfiguration();
			return gc.createCompatibleImage(width,height,transparency);
		}else{
			return null;
		}
		
	}
	
}
