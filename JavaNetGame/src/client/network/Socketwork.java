package client.network;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Socketwork {
    Socket s;

    InputStream in;
    OutputStream out;

    public Socketwork(String host, int port) {
        try {
            s = new Socket(host, port);
            System.out.println("Local port: " +  s.getLocalPort());
            System.out.println("Remote port: " + s.getPort());

            in = s.getInputStream();
            out = s.getOutputStream();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public boolean send(int sended) {
        byte[] buf = ByteBuffer.allocate(4).putInt(sended).array();
        try {
            out.write(buf);
            return true;
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    public boolean send(double sended) {
        byte[] buf = ByteBuffer.allocate(24).putDouble(sended).array();
        try {
            out.write(buf);
            return true;
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    public boolean send(String sended) {
        try {
            out.write(sended.getBytes());
            return true;
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    public int waitForInt() {
        try {
            byte[] buf2 = new byte[2000];
            int count = in.read(buf2);
            ByteBuffer bb = ByteBuffer.wrap(buf2, 0, count);
            int res = bb.getInt();
            return res;
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return -1;
        }
    }

    public double waitForDouble() {
        try {
            byte[] buf2 = new byte[2000];
            int count = in.read(buf2);
            ByteBuffer bb = ByteBuffer.wrap(buf2, 0, count);
            double res = bb.getDouble();
            return res;
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return -1;
        }
    }

    public String waitForString() {
        try {
            byte[] buf = new byte[2000];
            int count = in.read(buf);
            String res = new String(buf, 0, count);
            return res;
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return "NULL";
        }
    }
}
