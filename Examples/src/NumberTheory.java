
public class NumberTheory {

	
	//Time Complexity - O(n), Space Complexity - O(n)
	public static int powerRec(int x, int n) {
	
		if(n == 0) {
			
			return 1;
		}
		
		return x * powerRec(x, n-1);
	}
	
	//Time Complexity - O(n), Space Complexity - O(1)
	public static int powerIterative(int x, int n) {
		
		int result = 1;
		
		while(n > 0)
		{
			result = result * x;
			n--;
		}
		
		return result;
	}
	
	//Time Complexity - O(log n), Space Complexity - O(log n)
	public static int powerRecLogn(int x, int n) {
		
		if(n == 0)
			return 1;
		else if (n%2 == 0) {
			return powerRecLogn(x*x, n/2);
		}
		else {
			
			return x * powerRecLogn(x*x, n/2);
		}
				
	}
	
	//Time Complexity - O(log n), Space Complexity - O(log n)
	public static int powerRecLogn1(int x, int n) {
			
			if(n == 0)
				return 1;
			
			int temp = powerRecLogn1(x, n/2);
			if (n%2 == 0) {
				return temp * temp;
			}
			else {
				
				return x * temp * temp;
			}
					
	}
	
	//Time Complexity - O(log n), Space Complexity - O(log n)
	public static int powerItrLogn(int x, int n) {
			
			int result = 1;
			//int temp = 1;
			while(n>0)
			{
				
				if(n%2 == 1)
					result = result * x;
				
				x = x * x;
				n = n/2;
			}
			
			return result;
					
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(powerRec(2,10));
		
		System.out.println(powerIterative(2,10));
		
		System.out.println(powerRecLogn(2,10));
		
		System.out.println(powerItrLogn(2,10));
		

	}

}
