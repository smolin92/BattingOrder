import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Lineup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			File roster=new File(args[0]);
			Scanner input=new Scanner(roster);

			final int[] lineupSlots={7,6,8,9,5,4,3,2,1};//have to make sure it can't pick the same one again
			String name;
			float obp;
			int sb;
			int rbi;
			int hr;
			int singles;
			int doubles;
			int triples;
			int restDays;
			int salary;
			String pposition;
			String sposition;
			ArrayList<BaseballPlayer> team = new ArrayList<BaseballPlayer>();
			ArrayList<BaseballPlayer> starter = new ArrayList<BaseballPlayer>();//players that are picked by salary, position, and rest
			String[] lineup = new String[9];//final lineup for specific game (use only their names and maybe position)

			while(input.hasNext()){
				name=input.nextLine();
				obp=Float.parseFloat(input.nextLine());
				sb=Integer.parseInt(input.nextLine());
				rbi=Integer.parseInt(input.nextLine());
				hr=Integer.parseInt(input.nextLine());
				singles=Integer.parseInt(input.nextLine());
				doubles=Integer.parseInt(input.nextLine());
				triples=Integer.parseInt(input.nextLine());
				restDays=Integer.parseInt(input.nextLine());
				salary=Integer.parseInt(input.nextLine());
				pposition=input.nextLine();
				sposition=input.nextLine();
				team.add(new BaseballPlayer(name,obp,sb,rbi,hr,singles,doubles,triples,restDays,salary,pposition,sposition));
				if(input.hasNext()){
					input.nextLine();
				}//skips the blank space in between players
			}
			input.close();

			File seasonLineup=new File(args[1]);
			PrintWriter output=new PrintWriter(seasonLineup);//need output.print stuff and then output.close

			ArrayList<BaseballPlayer> first=new ArrayList<BaseballPlayer>();
			ArrayList<BaseballPlayer> second=new ArrayList<BaseballPlayer>();
			ArrayList<BaseballPlayer> shortstop=new ArrayList<BaseballPlayer>();
			ArrayList<BaseballPlayer> third=new ArrayList<BaseballPlayer>();
			ArrayList<BaseballPlayer> left=new ArrayList<BaseballPlayer>();
			ArrayList<BaseballPlayer> center=new ArrayList<BaseballPlayer>();
			ArrayList<BaseballPlayer> right=new ArrayList<BaseballPlayer>();
			ArrayList<BaseballPlayer> catcher=new ArrayList<BaseballPlayer>();
			ArrayList<BaseballPlayer> dh=new ArrayList<BaseballPlayer>();
			for(int i=0; i<team.size(); i++){
				if (team.get(i).is1B()||team.get(i).isAlso1B()){
					first.add(team.get(i));
				}
				if (team.get(i).is2B()||team.get(i).isAlso2B()){
					second.add(team.get(i));
				}
				if (team.get(i).is3B()||team.get(i).isAlso3B()){
					third.add(team.get(i));
				}
				if (team.get(i).isLF()||team.get(i).isAlsoLF()){
					left.add(team.get(i));
				}
				if (team.get(i).isCF()||team.get(i).isAlsoCF()){
					center.add(team.get(i));
				}
				if (team.get(i).isRF()||team.get(i).isAlsoRF()){
					right.add(team.get(i));
				}
				if (team.get(i).isSS()||team.get(i).isAlsoSS()){
					shortstop.add(team.get(i));
				}
				if (team.get(i).isCatcher()||team.get(i).isAlsoCatcher()){
					catcher.add(team.get(i));
				}
				if (team.get(i).isDH()||team.get(i).isAlsoDH()){
					dh.add(team.get(i));
				}
			}
			ArrayList<BaseballPlayer> storedFirst=first;
			ArrayList<BaseballPlayer> storedSecond=second;
			ArrayList<BaseballPlayer> storedThird=third;
			ArrayList<BaseballPlayer> storedSS=shortstop;
			ArrayList<BaseballPlayer> storedCatcher=catcher;
			ArrayList<BaseballPlayer> storedCenter=center;
			ArrayList<BaseballPlayer> storedLeft=left;
			ArrayList<BaseballPlayer> storedRight=right;
			ArrayList<BaseballPlayer> storedDH=dh;
			
			double max=0;
			int index=0;
			for(int game=1; game<=162; game++){
				int last=0;
				for(int j=0; j<catcher.size(); j++){
					if (catcher.get(j).getSalary()>max && Math.random()*162>catcher.get(j).getRestDays()){
						max=catcher.get(j).getSalary();
						index=j;
						catcher.get(j).setPosition("C");
						last=1;//enters that player is playing catcher for that game
					}//can rest any number of random days measured against last seasons rest days
				}
				if(last!=1){
					catcher.get(1).setPosition("C");
					index=1;
				}
				starter.add(catcher.get(index));
				for(int k=0;k<dh.size();k++){
					if(dh.get(k).getName().equals(catcher.get(index).getName())){
						dh.remove(k);//need to remove this player from any other position lists
					}//catchers only have dh as secondary position
				}
				max=0;
				index=0;
				last=0;
				for(int j=0; j<first.size(); j++){
					if (first.get(j).getSalary()>max && Math.random()*162>first.get(j).getRestDays()){
						max=first.get(j).getSalary();
						index=j;
						first.get(j).setPosition("1B");
						last=1;
					}//random number greater than rest days means they don't rest
				}
				if(last!=1){
					first.get(0).setPosition("1B");
					index=0;
				}
				starter.add(first.get(index));
				for(int k=0;k<dh.size();k++){//dh list is largest need to make sure others don't through index error
					if(dh.get(k).getName().equals(first.get(index).getName())){
						dh.remove(k);//need to remove this player from any other position lists
					}//removes 1Bs that play DH
					if(k<first.size() && k<second.size() && second.get(k).getName().equals(first.get(index).getName())){
						second.remove(k);//need to remove this player from any other position lists
					}//removes 1Bs that play 2B
					if(k<first.size() && k<third.size() && third.get(k).getName().equals(first.get(index).getName())){
						third.remove(k);//need to remove this player from any other position lists
					}//removes 1Bs that play 3B
				}
				max=0;
				index=0;
				last=0;
				for(int j=0; j<second.size(); j++){
					if (second.get(j).getSalary()>max && Math.random()*162>second.get(j).getRestDays()){
						max=second.get(j).getSalary();
						index=j;
						second.get(j).setPosition("2B");
						last=1;
					}
				}
				if(last!=1){
					second.get(0).setPosition("2B");
					index=0;
				}
				starter.add(second.get(index));
				for(int k=0;k<dh.size();k++){//dh list is largest need to make sure others don't through index error
					if(dh.get(k).getName().equals(second.get(index).getName())){
						dh.remove(k);//need to remove this player from any other position lists
					}//removes 2Bs that play DH
					if(k<shortstop.size() && shortstop.get(k).getName().equals(second.get(index).getName())){
						shortstop.remove(k);//need to remove this player from any other position lists
					}//removes 2Bs that play SS
					if(k<third.size() && third.get(k).getName().equals(second.get(index).getName())){
						third.remove(k);//need to remove this player from any other position lists
					}//removes 2Bs that play 3B
				}
				max=0;
				index=0;
				last=0;
				for(int j=0; j<shortstop.size(); j++){
					if (shortstop.get(j).getSalary()>max && Math.random()*162>shortstop.get(j).getRestDays()){
						max=shortstop.get(j).getSalary();
						index=j;
						shortstop.get(j).setPosition("SS");
						last=1;
					}
				}
				if(last!=1){
					shortstop.get(0).setPosition("SS");
					index=0;
				}
				starter.add(shortstop.get(index));
				for(int k=0;k<dh.size();k++){//dh list is largest need to make sure others don't through index error
					if(dh.get(k).getName().equals(shortstop.get(index).getName())){
						dh.remove(k);//need to remove this player from any other position lists
					}//removes SSs that play DH
				}
				max=0;
				index=0;
				last=0;
				for(int j=0; j<third.size(); j++){
					if (third.get(j).getSalary()>max && Math.random()*162>third.get(j).getRestDays()){
						max=third.get(j).getSalary();
						index=j;
						third.get(j).setPosition("3B");
						last=1;
					}
				}
				if(last!=1){
					third.get(0).setPosition("3B");
					index=0;
				}
				starter.add(third.get(index));
				for(int k=0;k<dh.size();k++){//dh list is largest need to make sure others don't through index error
					if(dh.get(k).getName().equals(third.get(index).getName())){
						dh.remove(k);//need to remove this player from any other position lists
					}//removes 3Bs that play DH
				}
				max=0;
				index=0;
				last=0;
				for(int j=0; j<center.size(); j++){
					if (center.get(j).getSalary()>max && Math.random()*162>center.get(j).getRestDays()){
						max=center.get(j).getSalary();
						index=j;
						center.get(j).setPosition("CF");
						last=1;
					}
				}
				if(last!=1){
					center.get(0).setPosition("CF");
					index=0;
				}
				starter.add(center.get(index));	
				for(int k=0;k<dh.size();k++){//dh list is largest need to make sure others don't through index error
					if(dh.get(k).getName().equals(center.get(index).getName())){
						dh.remove(k);//need to remove this player from any other position lists
					}//removes CFs that play DH
					if(k<left.size() && left.get(k).getName().equals(center.get(index).getName())){
						left.remove(k);//need to remove this player from any other position lists
					}//removes CFs that play LF
					if(k<right.size() && right.get(k).getName().equals(center.get(index).getName())){
						right.remove(k);//need to remove this player from any other position lists
					}//removes CFs that play RF
				}
				max=0;
				index=0;
				last=0;
				for(int j=0; j<left.size(); j++){
					if (left.get(j).getSalary()>max && Math.random()*162>left.get(j).getRestDays()){
						max=left.get(j).getSalary();
						index=j;
						left.get(j).setPosition("LF");
					}
				}
				if(last!=1){
					left.get(0).setPosition("LF");
					index=0;
				}
				starter.add(left.get(index));
				for(int k=0;k<dh.size();k++){//dh list is largest need to make sure others don't through index error
					if(dh.get(k).getName().equals(left.get(index).getName())){
						dh.remove(k);//need to remove this player from any other position lists
					}//removes LFs that play DH
					if(k<right.size() && right.get(k).getName().equals(left.get(index).getName())){
						right.remove(k);//need to remove this player from any other position lists
					}//removes LFs that play RF NO NEED TO REMOVE FROM CENTER BC CENTER HAS BEEN PICKED ALREADY
				}
				max=0;
				index=0;
				last=0;
				for(int j=0; j<right.size(); j++){
					if (right.get(j).getSalary()>max && Math.random()*162>right.get(j).getRestDays()){
						max=right.get(j).getSalary();
						index=j;
						right.get(j).setPosition("RF");
						last=1;
					}
				}
				if(last!=1){
					right.get(0).setPosition("RF");
					index=0;
				}
				starter.add(right.get(index));
				for(int k=0;k<dh.size();k++){//dh list is largest need to make sure others don't through index error
					if(dh.get(k).getName().equals(right.get(index).getName())){
						dh.remove(k);//need to remove this player from any other position lists
					}//removes RFs that play DH NO NEED TO REMOVE FROM CENTER OR LEFT BC THEY HAVE BEEN PICKED ALREADY
				}
				max=0;
				index=0;
				last=0;
				for(int j=0; j<dh.size(); j++){
					if (dh.get(j).getSalary()>max && Math.random()*162>dh.get(j).getRestDays()){
						max=dh.get(j).getSalary();
						index=j;
						dh.get(j).setPosition("DH");
						last=1;
					}
				}
				if(last!=1){
					dh.get(0).setPosition("DH");
					index=0;
				}
				starter.add(dh.get(index));				
				max=0;
				index=0;//reset values
				first=storedFirst;
				second=storedSecond;
				third=storedThird;
				shortstop=storedSS;
				center=storedCenter;
				left=storedLeft;
				right=storedRight;
				catcher=storedCatcher;
				dh=storedDH;//all players are added back to their proper position lists
				//at this point the top paid players per position that aren't resting have been chosen
				
				int fastestLeft=0;//index
				int current=0;//value
				int previous=10;
				int h=0;
				while(h<9){
					for(int k=0; k<9; k++){
						if (current<lineupSlots[k] && lineupSlots[k]<previous){
							current=lineupSlots[k];
							fastestLeft=k;
						}
					}//yields the fastest remaining machine and its index
					previous=current;
					current=0;
					double best=10000000;//
					int spot=0;
					//int[] used=new int[9];
					for (int m=0; m<starter.size(); m++){
						if(starter.get(m).getValues(fastestLeft)<best /*&& spot!=m*/){//want the lowest number (SPT)
							spot=m;
							best=starter.get(m).getValues(fastestLeft);
						}
					}//finds index and value of the best player for that slot
					best=100000000;
					lineup[fastestLeft]=starter.get(spot).toString();//adds that players name to the lineup
					starter.remove(spot);//removes that player from starter list
					//at this point the first player has been scheduled into slot for the game
					h++;
				}//at each iteration the next fastest machine (slot) is scheduled and that player is removed
				output.println("Game "+ game + ": \n"+ lineup[0].toString());//prints that game's batting order
				output.println(lineup[1].toString());
				output.println(lineup[2].toString());
				output.println(lineup[3].toString());
				output.println(lineup[4].toString());
				output.println(lineup[5].toString());
				output.println(lineup[6].toString());
				output.println(lineup[7].toString());
				output.println(lineup[8].toString()+"\n");
			}
			output.close();

		}
		catch(FileNotFoundException e){
			System.out.println("Please input the correct file name.");
		}
	}

}
