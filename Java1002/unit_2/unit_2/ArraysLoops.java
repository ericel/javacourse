package unit_2;

public class ArraysLoops {
	public static void main(String[] args) {
		/*for (int i = 1; i > 10; i++) ;
		{
		    System.out.println("Hello");
		}*/
		
		//Addition sum = new Addition(2, 2);
		//System.out.print(sum.getSum());
		
		
		SubSum sum = new SubSum();
		System.out.print(sum.getNumber_1());
		
	}
	
	
	static class Sum {
	    int number_1;
	    int number_2;
        
	    public Sum(){
	      this(1, 2);
	    }
	    public Sum(int num_1, int num_2){
	    number_1 = num_1;
	    number_2 = num_2;
	   }

	 int getSum(){
	   return number_1 + number_2;
	  }
	}
	
	static class SubSum extends Sum {
        int number_1 = 4;
        int number_2 = 5;
        
		
		SubSum(){
			super();
		}
		 int getSum(){
			 return super.getSum() ;
		 }
		 
		 int getNumber_1() {
			 return super.getSum();
		 }

		}
	
	
}

