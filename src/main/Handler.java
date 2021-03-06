package main;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import mob.Mob;
import mob.enemy.Enemy;
import utils.Border;
import utils.Collision;
import utils.Vector;

public class Handler extends ArrayList<GameObject> {
	
	private Game game;
	public int mobCount = 0;
	
	public Handler (Game game) {
		this.game = game;
	}
	
	public GameObject exist (Vector pos) {
		for (GameObject obj: this) {
			if (Vector.compare(pos, obj.getPosition())) return obj;
		}
		return null;
	}
	
	public void process(GraphicsContext gc) {
		for (int i = this.size() - 1 ; i >= 0 ; i--) {
			if (this.get(i).isOnScreen()) this.get(i).update();
        }
		
		for (GameObject obj: this) {
			if (obj.isOnScreen()) obj.render(gc);
        }
		for (int i = this.size() - 1 ; i >= 0 ; i--) {
        	if (this.get(i).checkIfDead()) {
        		if (this.get(i) instanceof Enemy) {
        			mobCount--;
        			game.player.gold += game.level;
        		}
        		remove(i);
        	}
        }
	}
	
	public void addMob (Mob mob) {
		if (mob instanceof Enemy) mobCount++;
		this.add(mob);
	}
	
	public void reset () {
		
	}
	
	public boolean collide (Border border1, Border border2) {
		return Collision.rect(border1, border2);
	}
	
	public boolean canMoveTo (Mob mob) {
		
		//if (mob.name != "PLAYER") return false;
		Border border = mob.calculBorders();
		for (GameObject other: this) {
			if (mob != other) {
				Border otherBorder = other.calculBorders();
				if (collide(border, otherBorder)) {
					return false;
				}
			}
		}
		return true;
	}
	
}
