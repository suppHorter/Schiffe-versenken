package application;

import java.util.ArrayList;

public class Ship {
	//type: 0=Schlachtschiff; 1 = Kreuzer; 2 = Zerst�rer, 3 = UBoot
	private int type;
	private int length;
	private GameField[] gfUsed;
	private int maxAnz;
	private int dir = 1; //-1 horizontal 1 vertikal


	Ship(int type)
	{
		this.type = type;
		switch (type)
		{
			case 0:
				//Schlachtschiff 
				this.length = 5; 
				this.maxAnz = 1;
				break;
			case 1: 
				this.length = 4; 
				this.maxAnz = 2;
				break;
			case 2: 
				this.length = 3; 
				this.maxAnz = 3;
				break;
			case 3: 
				this.length = 2; 
				this.maxAnz = 4;
				break;
		}
	}

	public int getMaxAnz() {
		return maxAnz;
	}

	public GameField[] getUsedGameFields()
	{
		return this.gfUsed;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public int getDir() {
		return dir;
	}

	public void viewUsedGameFields()
	{
		for(int i=0;i<this.gfUsed.length;i++){
	    	System.out.println(this.gfUsed[i].getXKoord()+" "+this.gfUsed[i].getYKoord());
	    }
	}
	
	public void setUsedGameFields(GameField[] gameFields)
	{
		this.gfUsed = gameFields;
	}
	
	public int getType()
	{
		return this.type;
	}
	
	public int getLength()
	{
		return this.length;
	}
}
