/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moos;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author admin
 */
public class Efficiency_Flooding {

    LinkedList nexthop = new LinkedList();
    LinkedList source = new LinkedList();
    topologyreader tr = new topologyreader();
    // router_buffer = new Efficiency_Router_Buffer();

    public Efficiency_Flooding() {
        tr.freader(Efficiency_Start.topologyname);

    }

    public void getHops() {
        action_btn actionbutton;
        Efficiency_Router r = new Efficiency_Router();
        for (int i = 0; i < tr.nodename.size(); i++) {
            LinkedList lk = r.getNextHop("" + tr.nodename.get(i));
            for (int j = 0; j < lk.size(); j++) {
//                router_buffer.addPacketToBuffer(new Efficiency_Packet("" + tr.nodename.get(i), "" + lk.get(j)), "" + tr.nodename.get(i));
            }
            source.add("" + tr.nodename.get(i));
            nexthop.add("" + r.getNextHop("" + tr.nodename.get(i)));
        }
        viewflooding vf = new viewflooding(source, nexthop);
        vf.show();
    }

    public double getFloodingEfficiancy(Efficiency_Packet p) {
        double efficiancy = 0;
        Efficiency_Link_Config l = new Efficiency_Link_Config();
        Efficiency_Router r = new Efficiency_Router();
        //  Efficiency_Router_Buffer rb = new Efficiency_Router_Buffer();
        LinkedList lr = r.getNextHop("" + p.getPacketSource());
        for (int i = 0; i < lr.size(); i++) {
            if ((r.getRouterState("" + lr.get(i))).equals("Down")) {
                lr.remove(i);
            }
            if (("" + p.getPacketDestination()).equals("" + lr.get(i))) {
                lr.clear();
                lr.add("" + p.getPacketSource());
                break;
            }
            LinkedList lt1 = r.getNextHop("" + lr.get(i));
            boolean boo = true;
            for (int j = 0; j < lt1.size(); j++) {
                if (("" + p.getPacketDestination()).equals("" + lt1.get(j))) {
                    boo = false;
                    break;
                }
                if (("" + r.getRouterState("" + lt1.get(j))).equals("Up")) {
                    lr.add("" + lt1.get(j));
                }
            }
            if (boo == false) {
                break;
            }
            lr = l.findRemove(lr, "" + p.getPacketSource());
        }
        lr = l.removeDupli(lr);
        lr = l.findRemove(lr, "" + p.getPacketSource());
        lr.addFirst("" + p.getPacketSource());
        System.out.println(" " + lr);
        for (int i = 0; i < lr.size(); i++) {
//            rb.addPacketToBuffer(p, "" + lr.get(i));
            efficiancy = efficiancy + r.getRouterEfficiancy();
        }
        int total = tr.nodename.size();
        double efficiancy1 = efficiancy / total;
        return (efficiancy1);
    }
}

class viewflooding extends JFrame {

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    JTable table;
    JScrollPane scrollpane;
    Container container;
    String[][] sdf = new String[200][2];
    String[] headings = {"Source", "NextHop(s)"};
    Dimension dimension1, dimension2;
    Image imageicon;

    public viewflooding(LinkedList source, LinkedList nexthops) {
        container = getContentPane();
        table = new JTable(sdf, headings);
        table.setBackground(new Color(240, 240, 240));
        table.setFont(new Font("Dialog", Font.ITALIC, 12));
        table.setRowSelectionAllowed(false);
        table.setCellSelectionEnabled(false);
        scrollpane = new JScrollPane(table);
        setSize(300, 400);
        setTitle("Flooding");
        dimension1 = getSize();
        imageicon = toolkit.getImage("src/nemos_demi/icon3.png");
        setIconImage(imageicon);
        dimension2 = toolkit.getScreenSize();
        setLocation((dimension2.width - dimension1.width) / 2, (dimension2.height - dimension1.height) / 2);
        container.setLayout(new BorderLayout());
        container.add(scrollpane);
        for (int i = 0; i < source.size(); i++) {
            table.setValueAt(source.get(i), i, 0);
        }
        for (int i = 0; i < nexthops.size(); i++) {
            table.setValueAt(nexthops.get(i), i, 1);
        }
    }
}
