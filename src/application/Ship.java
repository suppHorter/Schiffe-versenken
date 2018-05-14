package application;

import java.util.ArrayList;

public class Ship {
	//type: 0=Schlachtschiff; 1 = Kreuzer; 2 = Zerst�rer, 3 = UBoot
	private int type;
	private int length;
	private GameField[] gfUsed;
	private int maxAnz;
	private static int currAnzSchlacht=0;
	private static int currAnzZerst=0;
	private static int currAnzKreuz=0;
	private static int currAnzUBoot=0;


	Ship(int type)
	{
		this.type = type;
		switch (type)
		{
			case 0:
				//Schlachtschiff 
				this.length = 5; 
				this.maxAnz = 1;
				currAnzSchlacht++;
				break;
			case 1: 
				this.length = 4; 
				this.maxAnz = 2;
				currAnzKreuz++;
				break;
			case 2: 
				this.length = 3; 
				this.maxAnz = 3;
				currAnzZerst++;
				break;
			case 3: 
				this.length = 2; 
				this.maxAnz = 4;
				currAnzUBoot++;
				break;
		}
	}
	
	public int getCurrAnzSchlacht()
	{
		return this.currAnzSchlacht;
	}
	
	public int getCurrAnzKreuz()
	{
		return this.currAnzKreuz;
	}
	
	public int getCurrAnzZerst()
	{
		return this.currAnzZerst;
	}
	
	public int getCurrAnzUBoot()
	{
		return this.currAnzUBoot;
	}
	
	public GameField[] getUsedGameFields()
	{
		return this.gfUsed;
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
