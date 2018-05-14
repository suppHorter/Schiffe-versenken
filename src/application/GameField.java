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
	private int status;
	
	public GameField() throws FileNotFoundException
	{
		setStatus(0);
	}
	public Image getImage()
	{
		return imgStatus;
	}
	
	public void setStatus(int stat) throws FileNotFoundException 
	{
		this.status = stat;
		//Bild auf Label setzen
		switch(stat)
		{
			case 0:
				FileInputStream inputstream = new FileInputStream(System.getProperty("user.home") + "/Desktop" + "/Schiffe-versenken/images/water.png");
				this.imgStatus = new Image(inputstream);
			break;
			case 1: 
				FileInputStream inputstream1 = new FileInputStream(System.getProperty("user.home") + "/Desktop" + "/Schiffe-versenken/images/Tile_Mid.png");
				this.imgStatus = new Image(inputstream1);
			break;
			case 2:
				FileInputStream inputstream2 = new FileInputStream(System.getProperty("user.home") + "/Desktop" + "/Schiffe-versenken-master/images/water.png");
				this.imgStatus = new Image(inputstream2);
			break;
			case 3:
				FileInputStream inputstream3 = new FileInputStream(System.getProperty("user.home") + "/Desktop" + "/Schiffe-versenken-master/images/water.png");
				this.imgStatus = new Image(inputstream3);
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
	
	public int getStatus()
	{
		return this.status;
	}
}
