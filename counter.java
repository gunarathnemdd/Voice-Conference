public class counter {
        
	/**
	 * The size required to store a reading.
	 */
	
	/**
	 * ByteBuffer is a handy type for storing binary data.
	 */
	int i = 0;
	int c= 0;
	boolean countfull = false;
	boolean countempty = false;
	
	public  int id() {
	
		i++;
		if(i>=1200)
		i=1;
		return i;
	}
	
	
	
	public int count(long id) {
		if(id<=1000){
		c++;
		return c;
	  }
	  
	  if(id>=1100)
	  	this.countfull = true;

	  if(countempty == true){
	  	c=0;
	  	this.countempty = false;
	  	this.countfull  = false;
	  }
	  return c;

	}
	
	
	
	/**
	 * A quick unit test for the class. 
	 */
	public static void main(String[] args)  {
		
		
	}
}
