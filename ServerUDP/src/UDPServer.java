import java.io.*;
import java.net.*;

class UDPServer
{
    public static void main(String args[]) throws Exception
    {
        DatagramSocket serverSocket = new DatagramSocket(4000);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        while(true)
        {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String( receivePacket.getData());
            System.out.println("FROM CLIENT: " + sentence.trim());
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String msg = inp.readLine();
            sendData = msg.getBytes();
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }
}
