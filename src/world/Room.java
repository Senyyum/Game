package world;

import generator.RoomGenerator;
import javafx.scene.canvas.GraphicsContext;
import main.Renderer;
import utils.Vector;

public class Room {
	
	public static float SIZE = 21;
	
	public Integer[][] grid;
	public Integer difficulty;
	public String type;
	
	public Room (int type) {
		RoomGenerator room = new RoomGenerator(type,(int)SIZE);
		this.grid = room.getSurface();
		this.difficulty = room.getDifficulty();
		this.type = room.getType();
	}
	
	public boolean isGround (Vector pos) {
		return grid[(int) pos.x][(int) pos.y] == 1;
	}
	
	public void render (GraphicsContext gc) {
		for (int i = 0 ; i < grid.length ; i++) {
			for (int j = 0 ; j < grid.length ; j++) {
				if (grid[i][j] == 1) {
					gc.fillRect(i * Renderer.CELLSIZE, j * Renderer.CELLSIZE, Renderer.CELLSIZE, Renderer.CELLSIZE);
				} 
				gc.strokeRect(i * Renderer.CELLSIZE, j * Renderer.CELLSIZE, Renderer.CELLSIZE, Renderer.CELLSIZE);
			}
		}
	}
}
