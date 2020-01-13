package fighter_entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import fighter.display;
import fighter_bullet.Bullet;
import fighter_manager.manager;

public class player implements KeyListener{
		
		private int x;
		private int y;
		private boolean left,right;
		private boolean fire;
		private int health;
		
		private long current;
		private long delay;
		
		public player(int x,int y){
			this.x = x;
			this.y = y;
		}
		public void init(){
			display.frame.addKeyListener(this);
			current = System.nanoTime();
			delay = 100;
			health = 3;
		}
		public void tick(){
			if(!(health<=0)){
				if(left){
				if(x>=53){
				x -= 4;
			}
		}
			if(right){
				if(x<=450-32){
				x += 4;
			}
		}
			if(fire){
				long breaks = (System.nanoTime()-current)/1000000;
				if(breaks>delay){
				manager.bullet.add(new Bullet(x+13,y));
			   }current = System.nanoTime();
		   } }
		}
		public void render(Graphics g){
			if(!(health <= 0 )){
			g.setColor(Color.red);
			g.fillRect(x, y, 30, 30);
			
		}}
		public void keyPressed(KeyEvent e){
			int source = e.getKeyCode();
			if(source==KeyEvent.VK_LEFT){
				left = true;
			}
			if(source==KeyEvent.VK_RIGHT){
				right = true;
			}
			if(source==KeyEvent.VK_SPACE){
				fire = true;
			}
		}
		public void keyReleased(KeyEvent e){
			int source = e.getKeyCode();
			if(source==KeyEvent.VK_LEFT){
				left = false;
			}
			if(source==KeyEvent.VK_RIGHT){
				right = false;
			}
			if(source==KeyEvent.VK_SPACE){
				fire = false;
			}
			
		}
		public void keyTyped(KeyEvent e){
			
		}
		public int getX(){
			return x;
		}
		public  int getY(){
			return y;
		}
		public int getHealth(){
			return health;
		}
		public void setHealth(int health){
			this.health = health;
		}
}
