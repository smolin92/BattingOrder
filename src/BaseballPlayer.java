//***********************************************
//BaseballPlayer.java
//
//Baseball Player class to create player object
//methods to get stats
//
//by Stefanie Molin
//April 8, 2014
//**********************************************
public class BaseballPlayer {
	String name;
	public float obp;
	public int sb;
	public int rbi;
	public int hr;
	public int singles;
	public int doubles;
	public int triples;
	public int restDays;
	public int salary;
	String primaryPosition;
	String secondaryPosition;
	String playing;//position they are playing that game
	double[] values = new double[10];
	
	public BaseballPlayer(String n, float o, int s, int r, int h, int si, int d, int t, int rD, int sal, String pp, String sp){
		name=n;
		obp=o;
		sb=s;
		rbi=r;
		hr=h;
		singles=si;
		doubles=d;
		triples=t;
		restDays=rD;
		salary=sal;
		primaryPosition=pp;
		secondaryPosition=sp;
	}
	
	public double getSalary(){
		return salary;
	}
	
	public String getPrimPosition(){
		return primaryPosition;
	}
	
	public String getSecPosition(){
		return secondaryPosition;
	}
	
	public String getName(){
		return name;
	}
	
	public double getValues(int i){
		/*values[0]=1-obp;//1-on base percentage greatest
		values[1]=1-(obp+(sb/1000));//2-obp + (SB/1000) greatest
		if(rbi==0 || hr==0){
			values[2]=10000000;//if these are zero this player should be the last choice for this slot, so it is a high value
		}
		else{
			values[2]=1/(rbi/hr);//3-RBI/HR highest
		}
		if(rbi==0 || hr==0){
			values[3]=10000000;
		}
		else{
			values[3]=1-(hr/rbi);//4-HR/RBI highest
		}
		values[4]=1/(doubles+triples);//5-Doubles + triples greatest
		values[5]=1/(doubles+singles);//6-Doubles +singles greatest
		values[6]=1/(singles);//7-singles greatest
		values[7]=values[1];//8-obp + (SB/1000) 2nd best
		values[8]=values[7];//9-obp + (SB/1000) 3rd best
		return values[i];*///original method
		
		//Ryan's method
		values[0]=1.0/(obp+sb+triples);//makes sure the fastest that gets on base a lot is here
		values[1]=1.0/(obp+(sb/500)+triples/10 +rbi/10 +singles);
		values[2]=1.0/(obp*160+doubles+triples+hr+rbi/10);//160 bc obp is 1.6 times as valuable as slugging (extra base hits)
		values[3]=1.0/(obp*130+doubles+triples+3*hr+rbi/10);//good hitter and obp but not as good as the 3rd
		values[4]=1.0/(obp*100+15*hr);//worse obp but good hitter
		values[5]=1.0/(obp*115+doubles+triples+15*hr);//not as good with extra base hits now so obp is weight more
		if(hr==0){
			values[6]=1000000;
			values[7]=1000000;
			values[8]=1000000;
		}
		else{
			values[6]=1.0/hr;//bad hitter mostly singles
			values[7]=1.0/(doubles + triples + hr);//bad hitter mostly singles but slightly faster than the 7 hitter
			values[8]=1.0/singles;
		}
		
		return values[i];
		
	}
	
	public int getRestDays(){
		return restDays;
	}
	
	public boolean isAlsoCatcher(){
		return secondaryPosition.equalsIgnoreCase("C");
	}
	public boolean isAlsoDH(){
		return secondaryPosition.equalsIgnoreCase("DH");
	}
	public boolean isAlsoLF(){
		return secondaryPosition.equalsIgnoreCase("LF");
	}
	public boolean isAlsoCF(){
		return secondaryPosition.equalsIgnoreCase("CF");
	}
	public boolean isAlsoRF(){
		return secondaryPosition.equalsIgnoreCase("RF");
	}
	public boolean isAlso1B(){
		return secondaryPosition.equalsIgnoreCase("1B");
	}
	public boolean isAlso2B(){
		return secondaryPosition.equalsIgnoreCase("2B");
	}
	public boolean isAlso3B(){
		return secondaryPosition.equalsIgnoreCase("3B");
	}
	public boolean isAlsoSS(){
		return secondaryPosition.equalsIgnoreCase("SS");
	}
	public boolean isCatcher(){
		return primaryPosition.equalsIgnoreCase("C");
	}
	public boolean isDH(){
		return primaryPosition.equalsIgnoreCase("DH");
	}
	public boolean isLF(){
		return primaryPosition.equalsIgnoreCase("LF");
	}
	public boolean isCF(){
		return primaryPosition.equalsIgnoreCase("CF");
	}
	public boolean isRF(){
		return primaryPosition.equalsIgnoreCase("RF");
	}
	public boolean is1B(){
		return primaryPosition.equalsIgnoreCase("1B");
	}
	public boolean is2B(){
		return primaryPosition.equalsIgnoreCase("2B");
	}
	public boolean is3B(){
		return primaryPosition.equalsIgnoreCase("3B");
	}
	public boolean isSS(){
		return primaryPosition.equalsIgnoreCase("SS");
	}
	public void setPosition(String p){
		playing=p;
	}
	public String toString(){
		return name + " " + playing;
	}
}
