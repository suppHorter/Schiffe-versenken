package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class GameField {
	private int xKoords;
	private int yKoords;
	
	Image imgStatus;
	//Status 
	//0 = Wasser,
	//1 = Schiff, 
	//2 = getroffen, 
	//3 = danebenv
	private ImageStatus status;
	
	public GameField() throws FileNotFoundException
	{
		setStatus(ImageStatus.WASSER);
	}
	public Image getImage()
	{
		return imgStatus;
	}
	
	public void setStatus(ImageStatus stat) throws FileNotFoundException
	{
		this.status = stat;
		//Bild auf Label setzen
		switch(stat)
		{
			case WASSER:
				FileInputStream inputstream = new FileInputStream(System.getProperty("user.dir") + "/images/Tile_Water.png");
				this.imgStatus = new Image(inputstream);
			break;
			case SCHIFF_MITTE_HOR:
				FileInputStream inputstream1 = new FileInputStream(System.getProperty("user.dir") + "/images/Tile_Ship_Mid.png");
				this.imgStatus = new Image(inputstream1);
			break;
			case SCHIFF_MITTE_VER:
				FileInputStream inputstream2 = new FileInputStream(System.getProperty("user.dir") + "/images/Tile_Ship_Mid.png");
				this.imgStatus = new Image(inputstream2);
			break;
			case CURR_MITTE_HOR:
				FileInputStream inputstream3 = new FileInputStream(System.getProperty("user.dir") + "/images/Tile_Select_Mid.png");
				this.imgStatus = new Image(inputstream3);
			break;
			case CURR_MITTE_VER:
				FileInputStream inputstream4 = new FileInputStream(System.getProperty("user.dir") + "/images/Tile_Select_Mid.png");
				this.imgStatus = new Image(inputstream4);
			break;
			case DESTR_MITTE_HOR:
				FileInputStream inputstream5 = new FileInputStream(System.getProperty("user.dir") + "/images/Tile_Destroyed_Mid.png");
				this.imgStatus = new Image(inputstream5);
				break;
			case DESTR_MITTE_VER:
				FileInputStream inputstream6 = new FileInputStream(System.getProperty("user.dir") + "/images/Tile_Destroyed_Mid.png");
				this.imgStatus = new Image(inputstream6);
				break;
			case NOPE:
				FileInputStream inputstream7 = new FileInputStream(System.getProperty("user.dir") + "/images/Tile_Select_Nope.png");
				this.imgStatus = new Image(inputstream7);
				break;
		}
	}
	public void setKoords(int x, int y)
	{
		this.xKoords = x;
		this.yKoords = y;
	}
	
	public int getXKoord()
	{
		return this.xKoords;
	}
	
	public int getYKoord()
	{
		return this.yKoords;
	}
	
	public ImageStatus getStatus()
	{
		return this.status;
	}
}
