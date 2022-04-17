import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;

import java.util.ArrayList;
import java.util.List;

class server
{
	public static void main(String args[]) {		
		try {
		   ServerSocket ss = new ServerSocket(1111);           
		   System.out.println("Waiting player 1...");	
		   Socket s1 = ss.accept();
		   System.out.println("Local port: " +  s1.getLocalPort());	
		   System.out.println("Remote port: " + s1.getPort() + '\n');	
		   
		   System.out.println("Waiting player 2...");	
		   Socket s2 = ss.accept();
		   System.out.println("Local port: " +  s2.getLocalPort());	
		   System.out.println("Remote port: " + s2.getPort() + '\n');
		   
		   InputStream in1 = s1.getInputStream();
		   OutputStream out1 = s1.getOutputStream();
		   
		   InputStream in2 = s2.getInputStream();
		   OutputStream out2 = s2.getOutputStream();
		   
		   boolean stop = false;
		   int matches = 37;
		   int activeplayer = (int) (Math.random() * 2);
		   
		   while (!stop) {
				if (activeplayer == 0) {
					byte[] buf = ByteBuffer.allocate(4).putInt(matches).array();
					out1.write(buf);
					
					byte[] buf2 = new byte[2000];
					int count = in1.read(buf2);
					ByteBuffer bb = ByteBuffer.wrap(buf2, 0, count);
					double res = bb.getInt();
					
					matches -= res;
					System.out.println("Send a data: " + matches + " to Player " + activeplayer);
					activeplayer = 1;
				}
				else {
					byte[] buf = ByteBuffer.allocate(4).putInt(matches).array();
					out2.write(buf);
					
					byte[] buf2 = new byte[2000];
					int count = in2.read(buf2);
					ByteBuffer bb = ByteBuffer.wrap(buf2, 0, count);
					double res = bb.getInt();
					
					matches -= res;
					System.out.println("Send a data: " + matches + " to Player " + activeplayer);
					activeplayer = 0;
				}
				
				if (matches <= 0) {
					stop = true;
				}
		   }
		   
			in1.close();
			out1.close();
			in2.close();
			out2.close();
			s1.close();
			s2.close();
			ss.close();
    
           System.out.println("Ending...");
		}
		catch (Exception e) {
			System.out.println("Error: " + e);	
		}
	}
	
}
