/*
 * TurnServer, the OpenSource Java Solution for TURN protocol. Maintained by the
 * Jitsi community (http://jitsi.org).
 *
 * Copyright @ 2015 Atlassian Pty Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jitsi.turnserver.turnClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.Socket;

import org.ice4j.socket.IceTcpSocketWrapper;

public class TcpPeer
{

    public static void main(String... args)
        throws IOException,
        InterruptedException
    {
        System.out.println("sending request to server....");
        Socket client = new Socket(InetAddress.getLocalHost(), 8080);
        System.out.println("successfully conneted from "
            + client.getLocalPort());
        IceTcpSocketWrapper mySock = new IceTcpSocketWrapper(client);

        byte[] data = new byte[1500];
        DatagramPacket p = new DatagramPacket(data, 0);
        System.out.println("Waiting for packet.");
        mySock.receive(p);
        System.out.println("packet received");
        data = p.getData();
        for (int i = 0; i < p.getLength(); i++)
        {
            System.out.print(String.format("%02X, ", data[i]));
        }
        System.out.println();
        String returnMessage = "Paras";
        p =
            new DatagramPacket(returnMessage.getBytes(), returnMessage.length());
        System.out.println("Sending return message-"
            + byteArrayToHex(p.getData()));
        mySock.send(p);
        System.out.println("Message Sent.");
        System.out.println("Thread going to sleep");
        Thread.sleep(100000);
    }

    private static String byteArrayToHex(byte[] data)
    {
        String arrayToHex = "";
        for (int i = 0; i < data.length; i++)
        {
            arrayToHex += String.format("%02X, ", data[i]);
        }
        return arrayToHex;
    }

    public static void main2(String[] args)
    {
        String returnMessage = "Paras";
        DatagramPacket p =
            new DatagramPacket(returnMessage.getBytes(), returnMessage.length());
        System.out.println("Sending return message-"
            + byteArrayToHex(p.getData()));
        System.out.println(p.getLength() + ", " + p.getOffset());

    }

}