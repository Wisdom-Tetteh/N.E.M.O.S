/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moos;

import java.util.*;

/**
 *
 * @author admin
 */
public class Efficiency_Packet {

    String packet_source = "", packet_destination = "";

    public Efficiency_Packet(String psource, String pdest) {
        packet_source = psource;
        packet_destination = pdest;
    }

    public String getPacketSource() {
        return packet_source;
    }

    public String getPacketDestination() {
        return packet_destination;
    }
}
