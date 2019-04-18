import java.util.Random;
import java.util.Date;



// randomize
class CoordinateGenerator {
private Random randomGenerator;
public CoordinateGenerator() {
Date now = new Date();
long sec = now.getTime();
randomGenerator = new Random(sec);
}
public int generateX() {
int x = randomGenerator.nextInt(101);
if(x < 5) {
x = 0;
} else if(x > 95) {
x = 100;
} else {
x = randomGenerator.nextInt(99) + 1;
}
return x;
}
public int generateY() {
int y = randomGenerator.nextInt(101);
if(y < 5) {
y = 0;
} else if(y > 95) {
y = 50;
} else {
y = randomGenerator.nextInt(49) + 1;
}
return y;
}
}


class Joc
{
	private Minge ball;
	private String teamName1, teamName2;
	private int outs=0, corners=0, teamGoals1=0, teamGoals2=0;
	private static final int LAPS = 1000;
	
	public Joc(String teamName1, String teamName2)
	{
		this.teamName1=teamName1;
		this.teamName2=teamName2;
		ball=new Minge(50,20);
		
		flow();
	}
	
	public void flow()
	{
		for(int i=1;i<=LAPS;i++)
		{
			ball.suteaza();
			int value=ball.findErrors();			
			if(value!=0)
			{
				if(value==1)outs++;
				else if(value==2)teamGoals1++;
				else if(value==3)corners++;
				else teamGoals2++;
			}			
			System.out.println(toString());
		}
		
		System.out.println("Out-uri: "+outs+" Corners: "+corners+" Goluri totale: "+(teamGoals1+teamGoals2));
	}
	
	public String toString()
	{
		String str=teamName1+" - "+teamName2+" Mingea ii pe pozitia "+ball.findX()+","+ball.findY(); //ball.findX();		
		return str;
	}	
}


class Minge
{
	private int pozX, pozY;
	private CoordinateGenerator gen;
	
	public Minge(int x, int y)
	{
		pozX=x;
		pozY=y;	
		gen = new CoordinateGenerator();	
	}
	
	public int findX()
	{
		return pozX;
	}
	
	public int findY()
	{
		return pozY;
	}
	
	public void suteaza()
	{	
		//System.out.println(pozX+" "+pozY);
		pozX=gen.generateX();
		pozY=gen.generateY();		
	}
	
	public int findErrors()
	{
		if(pozY==0||pozY==50)return 1;
		if((pozX==0||pozX==100)&&(pozY>=20&&pozY<=30))
		{
			pozX=50;
			pozY=25;		
			if(pozX==0)	return 2;
			return 4;
		}
		if((pozX==0||pozX==100)&&(pozY<20||pozY>30))
		{
			if(pozY<20)pozY=0;
			else if(pozY>30)pozY=50;
			return 3;
		}
		
		return 0;
	}
}


class Main
{
	public static void main(String chow[])
	{
		Joc x = new Joc("Uta", "Poli");	
		
	}
}