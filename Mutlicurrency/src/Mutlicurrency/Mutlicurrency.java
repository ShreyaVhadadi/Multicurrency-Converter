package Mutlicurrency;
import java.util.*;

public class Mutlicurrency {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			List<String> list=new ArrayList<>();
			HashMap<String,Double> map = new HashMap<>();
			map.put("EUR",100.00);
			map.put("USD",100.00);
			map.put("GBP",100.00);
			list.add("DEPOSIT,100.00,EUR");
			list.add("WITHRAW,600.00,USD");	
			String s=printBalances(list,map);
			System.out.print(s);
			
	}

	private static String printBalances(List<String> list,HashMap<String,Double> map) {
		// TODO Auto-generated method stub
		String[] a= {"USD","EUR","GBP"};
		boolean flag=false;
		for(String s:list) 
		{
			String[] arr=s.split(",");
			Double amount=Double.parseDouble(arr[1]);
			String curr=arr[2];
			int index=curr.equals("USD")?0:curr.equals("EUR")?1:2;
			Double bal=map.get(curr);
			if(arr[0].equals("DEPOSIT")) {
				map.put(curr, bal+amount);
			}
			else {
				if(bal>=amount) {
					bal=bal-amount;
					map.put(curr,bal);
				}
				else 
				{
					int i=3;
					while(i>0) 
					{
						if(amount==0.0)break;
						index=index%3;
						String s1=a[index++];
						if(s1.equals(curr)) 
						{
							double b=map.get(s1);
							if(b<amount) {																		
								amount=amount-b;
								map.put(s1,0.0);
							}
							else {
								map.put(s1,b-amount);	
								amount=0.0;
							}
						}
						else {
							double b=map.get(s1);
							double b1=b*conversion(s1,curr);
							if(b1<amount) {																		
								amount=amount-b1;
								map.put(s1,0.0);
							}
							else {
								double newamt=b1-amount;
								double newamt1=newamt*conversion(curr,s1);
								map.put(s1,newamt1);	
								amount=0.0;
							}							
						}
						i--;
					}
					if(amount!=0.0){
					flag=true;
					break;}
				}

			}
			
		}
		String ans=flag!=true?String.format("%.2f USD, %.2f EUR, %.2f GBP ", map.get("USD"),map.get("EUR"),map.get("GBP")):"Insufficient funds";
		
		return ans;
	}
	public static double conversion(String from, String to) {
		//EUR to USD
		//GBP to USD
		//USD to EUR
		//GBP to EUR
		//USD to GBP
		//EUR to GBP
		if(from.equals("EUR")&&to.equals("USD")) {
			return 1.18;
		}
		if(from.equals("GBP")&&to.equals("USD")) {
			return 1.38;
		}
		if(from.equals("USD")&&to.equals("EUR")) {
			return 0.85;
		}
		if(from.equals("GBP")&&to.equals("EUR")) {
			return 1.18;
		}
		if(from.equals("USD")&&to.equals("GBP")) {
			return 0.72;
		}
		if(from.equals("EUR")&&to.equals("GBP")) {
			return 0.85;
		}
		return 1.0;
	} 

}
