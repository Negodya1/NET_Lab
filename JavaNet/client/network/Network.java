package client.network;

public class Network {
    private Socketwork work;

    public Network() {
        work = new Socketwork("127.0.0.1",1111);
    }

    public boolean send(int sended) {
        return work.send(sended);
    }

    public boolean send(double sended) {
        return work.send(sended);
    }

    public boolean send(String sended) {
        return work.send(sended);
    }

    public int waitForInt() {
        return work.waitForInt();
    }

    public double waitForDouble() {
        return work.waitForDouble();
    }

    public String waitForString() {
        return work.waitForString();
    }
}