

 
 import java.net.* ;
public class receve extends Thread {

 private volatile boolean running = true;
 
 int port;
 int pr=1;
  MulticastSocket socket;
 DatagramPacket packet;
 RecordPlayback playback;
 InetAddress host;
  byte [] data;
  
  
  receve( int port,RecordPlayback playback) {              // this is the constructor
	  
      this.port = port;	
	  try
      {
        this.socket = new  MulticastSocket( this.port ) ; 
        InetAddress address = InetAddress.getByName("224.2.5.5");
     	socket.joinGroup(address);   // ssign a socket
		
	  }
     catch( Exception e2 )
      {
        System.out.println( e2 ) ;
		
      }
		this.data = new byte [508];
		this.playback = playback;
        this.packet = new DatagramPacket(data,data.length );  // packet always acess the play back object and store byte array
	}
   
   
   
   public void run() {

    convert con2 = new convert();
    counter counthis = new counter();
    int prev=-2;
      try
      {
         // Convert the argument to ensure that is it valid
         for( ;; )
         {
        while (running){

		socket.receive( packet );     //receve packet and store in playback object
      playback.tempBuffer1=con2.getdata(data);
      long id =con2.getid(data);

      if(id>2000){//System.out.println("id - "+id);
      if(peer1.next==0 && id!=peer1.myid){peer1.next=id;
      if(id!=2*peer1.myid)System.out.println("Next wii be "+id+" . press g then enter to give up" );}
  	  if(id==peer1.myid*2 ){
	peer1.talknow=true;
	peer1.next=0;
  Thread.sleep(1);
	if(pr==1){
	System.out.println("please press s and enter to talk");
	this.pr=0;}}}

      int i = counthis.count(id);

      if (prev>i && id<2000 && prev<=1000)
      System.out.println("packet drop precentage is " + (1000-prev)*100/1000 + "%" + " for last 1000 packets." );
      prev=i;

      if ((counthis.countfull == true)){
        counthis.countempty = true;
        
      }
    // System.out.println( packet.getAddress() + " : " + packet.getPort() + ": " + new String(packet.getData()) ) ;
			playback.play();			  // play the stored packet
			
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
   
}
