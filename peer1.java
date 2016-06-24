

 import java.util.Scanner;
 import java.net.* ;

public class peer1 extends Thread
{
   //Give a standard packet size
   private final static int packetsize = 500 ;
  	public static long next = 0; 
  	public static  long myid;
	public static boolean talknow = false;
	
   public static void main( String args[] ) throws InterruptedException
   {


	   
	     if( args.length != 1 )
      {
         System.out.println( "error: enter ->  java peer1 <recever's ip>" ) ; // giving error if not entered ip.
         return ;
      } 
      	 	myid =  Long.parseLong(args[0]);
	  	 Scanner in = new Scanner(System.in);
		String h = "224.2.5.5";
		RecordPlayback playback = new RecordPlayback(); // create playback object
		playback.captureAudio();                        // this must n=be called to configure and find the computers audioa hardware
      
         int portr = 12345;                             // port to receve data
		 int ports = 12346;								// port to send data
		 String s;

		 receve rs = new receve(portr,playback);
		 receve req =   new receve(ports,playback);
		 send sn = new send(h,portr,playback,0);
		 sn.start();
		 req.start();
		 rs.start();
		 sn.pauseThread();
		 req.pauseThread();
		 rs.pauseThread();

		 if(myid==3000){

		 	talknow = true;
		 }
		 else talknow = false;

		 while(true){

		 if(talknow==true){
		 System.out.println("you can talk now");
		 rs.pauseThread();	
		 sn.startThread(12345,0);
		 req.startThread();
		 req.pr=1;
		 //next = 0; 
		 
		 s = in.nextLine();

		  if(s.equals("g")){
		  	sn.pauseThread();
		  	Thread.sleep(500);	
	    	 sn.startThread(12346,next*2);
	    	//next = myid;
	     	talknow = false;
		Thread.sleep(5);
		//System.out.println("next = " + next );
	     	System.out.println("you can't talk now. press r and then enterb to make request to talk");	

	     	}

		 }

		 if(talknow == false){
		 	req.startThread();
		 	 rs.startThread();
		 	 sn.pauseThread();
		 	  s = in.nextLine();
		 	 if(s.equals("r" ) || s.equals("s")){
		 	 req.pauseThread();
		 	 sn.startThread(12346,myid);
		 	 if(s.equals("r" ))
		 	 System.out.println("request send");
		 	 Thread.sleep(500);	
		 	 req.startThread();
		 	}
		  }

		}
		 
	 }
}
   
