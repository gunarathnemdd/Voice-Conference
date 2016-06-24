
import java.net.* ;
import java.io.*;
public class send extends Thread 
{
 
 private volatile boolean running = true;
 int port;
 DatagramSocket socket;
 DatagramPacket packet;
 RecordPlayback playback;
 InetAddress host;
 long idno;
  byte [] data,empty="empty".getBytes();

send( String h,int port,RecordPlayback playback,long idno) {  // send constructor
	  
      this.port = port;
	   
	  
	  try
      {
		   this.host = InetAddress.getByName(h);           // taking host ip
        this.socket = new DatagramSocket() ;			// creating a socket with ip and port.
		
	  }
     catch( Exception e2 )
     {
        System.out.println( e2 ) ;
		
     }
		//this.data = new byte [508];
     this.idno=idno;
		this.playback = playback;
       // this.packet =  new DatagramPacket(data,data.length,host,port);  // packet always acess playback object
			       
   }

	
	
   
   
   
   public void run() {         // overide the run method

    boolean lock = false;
    
    long id;
   convert con1 = new convert();
   counter makeid = new counter();
      try
      {
         // Convert the argument to ensure that is it valid
		
         for( ;; )
         {

          while (running){

       if(idno>1999){
     lock = true;
     //System.out.println("locked");
      }
      else lock = false;

			playback.capture();  // capture audio and store in the byte array in object playback
      if(!lock)
       id =makeid.id();
      else  id = idno;
      if(!lock){
      byte[]  data = con1.serialize(id,playback.tempBuffer2);
      packet =  new DatagramPacket(data,data.length,host,port);
			socket.send( packet );   // send stored byte array
     // System.out.println("send");
      Thread.sleep(1);
			       }
      if(lock){
      for(int i=0;i<=5;i++){
      byte[]  data = con1.serialize(id,empty);
      packet =  new DatagramPacket(data,data.length,host,port);
      socket.send( packet );   // send stored byte array
      //System.out.println("port " +port+"idno "+idno+"packet no"+i);
              }
             Thread.sleep(700); 
             idno = 0;
             this.port=12345;
             }
    
            }
	       }  
     }
     catch( Exception e )
     {
        System.out.println( e ) ;
		
     }
   }
   
   public void pauseThread() throws InterruptedException
    {
        running = false;
    }

    public void startThread()
    {
        running = true;
    }
    public void startThread(int port,long idno)
    {
        this.port=port;
        running = true;
        this.idno = idno;
    }
}