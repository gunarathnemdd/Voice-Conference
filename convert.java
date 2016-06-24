
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;

public class convert {
        
	/**
	 * The size required to store a reading.
	 */
	static final int SIZE = Long.SIZE + 500*Byte.SIZE ;
	/**
	 * ByteBuffer is a handy type for storing binary data.
	 */

	
	public  byte[] serialize(long id, byte[] data) { // serialize
	
		ByteBuffer temp = ByteBuffer.allocate(SIZE);		
		temp.putLong(id);
	    temp.put(data);	
		
		String s1 = new String(temp.array());
		   // System.out.println("Text Decryted : " + s1);  // this method is used to add an id to a packet(0<=id<=1200)
			return temp.array();
	}
	
	
	
	public long getid(byte[] data) {  //deserialize
		ByteBuffer temp;	
		temp = ByteBuffer.allocate(data.length);        // this method is used to getting packets id.
		temp.put(data);
		temp.flip();
		return temp.getLong();
				
	}
	
	public  byte[] getdata(byte[] data) { //deserialize
		ByteBuffer temp;	
		temp = ByteBuffer.allocate(data.length);          // this method is used to geeting byte array from packet removing the header.
		temp.put(data);
		temp.flip();
		temp.getLong();
		byte[] bytes = new byte[temp.remaining()];
        temp.get(bytes, 0, bytes.length);
		return bytes;
			// serialize	
	}
	 
	 

	 
	public static void main(String[] args)  {
		
		
	}
}
