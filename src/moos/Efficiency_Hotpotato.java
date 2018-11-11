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
public class Efficiency_Hotpotato {

    LinkedList nexthop = new LinkedList();
    LinkedList source = new LinkedList();
    topologyreader tr = new topologyreader();
    Efficiency_Router router;

    public Efficiency_Hotpotato() {
        tr.freader(Efficiency_Start.topologyname);
        router = new Efficiency_Router();
    }

    public void getHops() {
        for (int i = 0; i < tr.nodename.size(); i++) {
            source.add("" + tr.nodename.get(i));
            nexthop.add("" + router.getNextHop("" + tr.nodename.get(i)));
        }
        viewhotpotato vf = new viewhotpotato(source, nexthop);
        vf.show();
    }

    public double getHotpotatoEfficiancy(Efficiency_Packet p) {
        double efficiancy = 0;
        Efficiency_Link_Config l = new Efficiency_Link_Config();
        Efficiency_Router r = new Efficiency_Router();
        // rb = new Efficiency_Router_Buffer();
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
        for (int i = 0; i < lr.size(); i++) {
//            rb.addPacketToBuffer(p, "" + lr.get(i));
            efficiancy = efficiancy + r.getRouterEfficiancy();
        }
        int total = tr.nodename.size();
        double efficiancy1 = efficiancy / total;
        return (efficiancy1);
    }
}

class viewhotpotato extends JFrame {

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    JTable table;
    JScrollPane scrollpane;
    Container container;
    String[][] sdf = new String[200][2];
    String[] headings = {"Source", "NextHop(s)"};
    Dimension dimesion1, dimension2;
    Image imageicon;

    public viewhotpotato(LinkedList source, LinkedList nexthops) {
        container = getContentPane();
        table = new JTable(sdf, headings);
        table.setRowSelectionAllowed(false);
        table.setCellSelectionEnabled(false);
        table.setFont(new Font("Dialog", Font.ITALIC, 12));
        table.setBackground(new Color(240, 240, 240));
        scrollpane = new JScrollPane(table);
        setSize(300, 400);
        setTitle("Hot-Potato");
        dimesion1 = getSize();
        dimension2 = toolkit.getScreenSize();
        imageicon = toolkit.getImage("src/nemos_demi/icon3.png");
        setIconImage(imageicon);
        setLocation((dimension2.width - dimesion1.width) / 2, (dimension2.height - dimesion1.height) / 2);
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
