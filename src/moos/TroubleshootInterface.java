package moos;

import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author admin
 */
public interface TroubleshootInterface {

    void addLatency(int value);

    void paintLatency(Graphics g);

    void setClientStatus(String status);

    void setDNSIP(String status);

    void setDNSStatus(String status);

    void setPingStatus(String status);

    void setReadSpeed(String speed);

    void setServerStatus(String status);

    void setWriteSpeed(String speed);

}
