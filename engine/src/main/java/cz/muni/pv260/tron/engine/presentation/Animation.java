package cz.muni.pv260.tron.engine.presentation;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Animation {
	
	private List<Scene> scenes;
	private int sceneIndex;
	private long movieTime;
	private long totalTime;
	
	public Animation(){
		scenes = new ArrayList<>();
		totalTime = 0;
		start();
	}
	
	public synchronized void addScene(Image image, long time){
		totalTime += time;
		scenes.add(new Scene(image, totalTime));
	}
	
	public synchronized void start(){
		movieTime = 0;
		sceneIndex = 0;
	}
	
	public synchronized void update(long timePassed){
		if(scenes.size()>1){
			movieTime += timePassed;
			if(movieTime>=totalTime){
				movieTime = 0;
				sceneIndex = 0;
			}
			while(movieTime > getScene(sceneIndex).endTime){
				sceneIndex++;
			}
		}
	}
	
	public synchronized Image getImage(){
		if(scenes.isEmpty()) {
			return null;
		}else{
			return getScene(sceneIndex).getImage();
		}
	}
	
	private Scene getScene(int x){
		return scenes.get(x);
	}
	
	private class Scene {
		private Image image;
		private long endTime;
		
		public Scene(Image image, long endTime){
			this.image = image;
			this.endTime = endTime;
		}
		
		public Image getImage() {
			return image;
		}
		
		public long getEndTime() {
			return endTime;
		}
	}
}
