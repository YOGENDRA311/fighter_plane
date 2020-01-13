package fighter_setup;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import fighter.display;
import fighter_image.Load_images;
import fighter_manager.manager;

public class setup implements Runnable{
	
	private String title;
	private int width;
	private int height;
	private boolean running;
	private BufferStrategy buffer;
	private Graphics g;
	private manager Man;
	private int y;
	private boolean start;
	
	private Thread thread;
	
	private display Display;
	public static final int gamewidht=400;
	public static final int gameheight=400;
	
	public setup(String title,int width,int height){
		this.title = title;
		this.width = width;
		this.height=height;
		
	}
	
	public void init(){
		
		Display = new display(title,width,height);
		Load_images.init();
		Man = new manager();
		Man.init();
		start = true;
		
	}
	
	public synchronized void start(){
		if(running){
			return;
		}
		running = true;
		if(thread==null){
		thread = new Thread(this);
		thread.start();
		}
	}
	public  synchronized void stop(){
		if(!running){
			return;
		}
		running = false;
		try{
			thread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	public void tick(){
		Man.tick();
		y += 1;
	}
	public void render(){
		buffer = Display.getCanvas().getBufferStrategy();
		if(buffer==null){
			Display.getCanvas().createBufferStrategy(3);
			return;
		}
		g=buffer.getDrawGraphics();
		g.clearRect(0, 0 ,width, height);
		//draw
			
		g.drawImage(Load_images.image,50, 50 , gamewidht, gameheight,null);
		Man.render(g);
		
		//end draw
		buffer.show();
		g.dispose();
	}
	public void run() {
			
			init();
			int fps = 50;
			double timepertick = 1000000000/fps;
			double delta = 0;
			long current = System.nanoTime();
			
			while(running){
				delta = delta+(System.nanoTime()-current)/timepertick;
				current = System.nanoTime();
				if(delta>=1){
				tick();
			    render();
			    delta--;
			}
		}
	}		

}
