package fighter_manager;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import fighter_bullet.Bullet;
import fighter_enemy.Enemy;
import fighter_entity.player;
import fighter_setup.setup;

public class manager {
	
	private player Player;
	public static ArrayList<Bullet>bullet; 
	public static ArrayList<Enemy>enemy;
	private int health;
	
	private long current;
	private long delay;
	public manager(){
		
	}
	public void init(){
		Player = new player((setup.gamewidht/2)+35,(setup.gameheight-30)+50);
		Player.init();
		
		bullet = new ArrayList<Bullet>();
		
		enemy = new ArrayList<Enemy>();
		current = System.nanoTime();
		delay = 2000;
		health = Player.getHealth();
	}
	public void tick(){
		Player.tick();
		
		for(int i=0 ; i<bullet.size(); i++){
			bullet.get(i).tick();
		}
		
		long breaks = (System.nanoTime()-current)/1000000;
		if(breaks > delay){
		for(int i=0;i<2;i++){
			Random rand = new Random();
			int randx = rand.nextInt(450);
			int randy = rand.nextInt(450);
			if(health > 0){
			enemy.add(new Enemy(randx,-randy));}
		 }
			current = System.nanoTime();
		}
		
		for(int i=0; i<enemy.size(); i++){
			enemy.get(i).tick();
		}
		
	}
	public void render(Graphics g){
		Player.render(g);
		for(int i=0;i<bullet.size();i++){
			bullet.get(i).render(g);
		}
		for(int i=0;i<bullet.size();i++){
			if(bullet.get(i).getY()<=50){
				bullet.remove(i);
				i--;
			}
		}
		for(int i=0;i<enemy.size();i++){
			if (!(enemy.get(i).getx()<=50 || enemy.get(i).getx()>=450-25 
					|| enemy.get(i).gety()>=450-25)){
				if(enemy.get(i).gety()>=50){
				
			enemy.get(i).render(g);}
			}
		}
		for(int i=0;i<enemy.size();i++){
			int ex = enemy.get(i).getx();	
			int ey = enemy.get(i).gety();
			
			int px = Player.getX();
			int py  =Player.getY();
			
			if(px  < ex + 25 && px + 30 > ex && py < ey + 25 
					&& py + 30 > ey){
				enemy.remove(i);
				i--;
				health--;
				if(health <= 0)
				{
					enemy.removeAll(enemy);
					Player.setHealth(0);					
				}
			}
			
			
			
			
			for(int j=0;j<bullet.size();j++){
				int bx = bullet.get(j).getX();
				int by = bullet.get(j).getY();
				
				if(ex < bx  + 6 && ex + 25 > bx && ey < by + 6
						&& ey + 25 > by){
					enemy.remove(i);
					i--;
					bullet.remove(j);
					j--;
				}
				
			}
		}
	}
	

}
