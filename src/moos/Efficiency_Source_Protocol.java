/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author admin
 */
public class Efficiency_Source_Protocol extends JFrame implements ActionListener {

    String source = "";
    Container container;
    JComboBox combox;
    JLabel label;
    JScrollPane scrollpane;
    JTable table;
    String[][] sdf = new String[200][2];
    String[] headings = {"Via", "NextHop(s)"};

    JButton btn1;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension dimension1, dimension2;
    topologyreader tr = new topologyreader();
    LinkedList nexthop;
    Efficiency_Router router = new Efficiency_Router();
    Image imageicon;

    public Efficiency_Source_Protocol(String psource) {
        source = psource;
        setTitle("Source Routing->Source :" + psource);
        container = getContentPane();
        dimension1 = toolkit.getScreenSize();
        setSize(400, 500);
        dimension2 = getSize();
        setLocation((dimension1.width - dimension2.width) / 2, (dimension1.height - dimension2.height) / 2);
        container.setLayout(new FlowLayout());
        btn1 = new JButton("OK");
        btn1.setFont(new java.awt.Font("Georgia", 3, 12));
        label = new JLabel("Destination");
        label.setFont(new java.awt.Font("Georgia", 3, 12));
        combox = new JComboBox();
        table = new JTable(sdf, headings);
        table.setRowSelectionAllowed(false);
        table.setCellSelectionEnabled(false);
        imageicon = toolkit.getImage("up.gif");
        setIconImage(imageicon);
        table.setBackground(new Color(240, 240, 240));
        table.setFont(new Font("Dialog", Font.ITALIC, 12));
        scrollpane = new JScrollPane(table);
        container.add(label);
        container.add(combox);
        container.add(btn1);
        btn1.addActionListener(this);
        tr.freader(Efficiency_Start.topologyname);
        combox.addItem("-Select-");
        combox.setFont(new java.awt.Font("Georgia", 3, 12));
        for (int i = 0; i < tr.nodename.size(); i++) {
            if (!("" + tr.nodename.get(i)).equals(source)) {
                combox.addItem("" + tr.nodename.get(i));
            }
        }
    }

    public void actionPerformed(ActionEvent ae) {
        String pdest = "";
        if (("" + combox.getSelectedItem()).equals("-Select-")) {
            JOptionPane.showMessageDialog(this, "Select a node as Destination");
        } else {
            pdest = "" + combox.getSelectedItem();
            simulate(pdest);
            container.removeAll();
            container.setLayout(null);
            Dimension d10 = getSize();
            scrollpane.setBounds(0, 0, d10.width - 10, d10.height - 10);
            container.add(scrollpane);
            setTitle("Source Routing->Source :" + source + " Destination :" + pdest);
            setSize(dimension2.width - 1, dimension2.height - 1);
        }
    }

    public void simulate(String dest) {
        LinkedList temp1 = router.getNextHop("" + source, router.getRouterState(source));
        LinkedList via = new LinkedList();
        for (int j = 0; j < tr.nodename.size(); j++) {
            via.add("" + tr.nodename.get(j));
        }
        if ((router.getRouterState(dest)).equals("Up")) {
            for (int j = 0; j < via.size(); j++) {
                table.setValueAt("" + via.get(j), j, 0);
                nexthop = new LinkedList();
                if ((router.getRouterState("" + via.get(j))).equals("Up")) {
                    for (int i = 0; i < temp1.size(); i++) {
                        if (!dest.equals("" + temp1.get(i))) {
                            findDest(dest, "" + temp1.get(i), "" + via.get(j));
                        }
                        if (via.get(j).equals("" + temp1.get(i)) && dest.equals("" + temp1.get(i))) {
                            nexthop.add("" + temp1.get(i));
                        }
                    }
                }
                table.setValueAt("" + nexthop, j, 1);
            }
        } else {
            setVisible(false);
            JOptionPane.showMessageDialog(this, "The Destination is Dowm");
        }
    }

    public void findDest(String dest, String nodename, String via) {
        Efficiency_Link_Config l = new Efficiency_Link_Config();
        boolean rflag = false;
        Efficiency_Router rou = new Efficiency_Router();
        if ((rou.getRouterState(nodename)).equals("Up")) {
            LinkedList ln1 = rou.getNextHop(nodename, rou.getRouterState(nodename));
            ln1 = l.findRemove(ln1, source);
            LinkedList nodell1 = new LinkedList();
            nodell1.add("" + nodename);
            for (int i = 0; i < ln1.size(); i++) {
                nodell1.add("" + ln1.get(i));
            }
            nodell1 = l.removeDupli(nodell1);
            nodell1 = l.findRemove(nodell1, source);
            if (true) {
                for (int i = 0; i < ln1.size(); i++) {
                    if ((rou.getRouterState("" + ln1.get(i))).equals("Up")) {
                        if (dest.equals("" + ln1.get(i))) {
                            nodell1.add("" + ln1.get(i));
                            break;
                        } else {
                            nodell1.add("" + ln1.get(i));
                            LinkedList lt1 = new LinkedList();
                            lt1 = rou.getNextHop("" + ln1.get(i), rou.getRouterState("" + ln1.get(i)));
                            for (int j = 0; j < lt1.size(); j++) {
                                if ((rou.getRouterState("" + lt1.get(j))).equals("Up")) {
                                    ln1.add("" + lt1.get(j));
                                }
                            }
                            ln1 = l.findRemove(ln1, source);
                            if ((ln1.size()) >= 200 * (tr.nodename.size())) {
                                break;
                            }
                        }
                    } else {
                        break;
                    }
                }
                nodell1 = l.removeDupli(nodell1);
                nodell1 = l.findRemove(nodell1, source);

                boolean flag1 = true;
                for (int i = 0; i < nodell1.size(); i++) {
                    if (flag1 == true) {
                        if (via.equals("" + nodell1.get(i))) {
                            flag1 = false;
                            i = 0;
                        }
                    }
                    if (flag1 == false) {
                        if (dest.equals("" + nodell1.get(i))) {
                            nexthop.add("" + nodename);
                            break;
                        }
                    }
                }
            }
        }
    }

    public LinkedList findDest(Efficiency_Packet p, String via) {
        // rb = new Efficiency_Router_Buffer();
        LinkedList ertcount = new LinkedList();
        Efficiency_Link_Config l = new Efficiency_Link_Config();
        boolean rflag = false;
        Efficiency_Router rou = new Efficiency_Router();
        LinkedList temp1 = rou.getNextHop("" + p.getPacketSource(), rou.getRouterState("" + p.getPacketSource()));
        LinkedList akl = new LinkedList();
        LinkedList stare = new LinkedList();
        for (int li = 0; li < temp1.size(); li++) {
            int akcount = 0;
            akl = new LinkedList();
            String aksource = "" + temp1.get(li);
            if ((rou.getRouterState(aksource)).equals("Up")) {
                if (via.equals("" + temp1.get(li)) && ("" + p.getPacketDestination()).equals("" + temp1.get(li))) {
                    LinkedList nodell2 = new LinkedList();
                    ertcount.add("" + akcount);
                    nodell2.add("" + aksource);
                    akl = nodell2;
                    akl.addFirst("" + p.getPacketSource());
                    stare.add(akl);
                } else {
                    LinkedList ln1 = rou.getNextHop(aksource, rou.getRouterState(aksource));
                    ln1 = l.findRemove(ln1, "" + p.getPacketSource());
                    if (ln1.size() > 1) {
                        akcount = akcount + ln1.size() - 1;
                    }
                    LinkedList nodell1 = new LinkedList();
                    nodell1.add(aksource);
                    for (int i = 0; i < ln1.size(); i++) {
                        nodell1.add("" + ln1.get(i));
                    }
                    nodell1 = l.removeDupli(nodell1);
                    nodell1 = l.findRemove(nodell1, "" + p.getPacketSource());
                    if (true) {
                        for (int i = 0; i < ln1.size(); i++) {
                            if ((rou.getRouterState("" + ln1.get(i))).equals("Up")) {
                                if (("" + p.getPacketDestination()).equals("" + ln1.get(i))) {
                                    nodell1.add("" + ln1.get(i));
                                    break;
                                } else {
                                    nodell1.add("" + ln1.get(i));
                                    LinkedList lt1 = new LinkedList();
                                    lt1 = rou.getNextHop("" + ln1.get(i), rou.getRouterState("" + ln1.get(i)));
                                    if (lt1.size() > 2) {
                                        akcount = akcount + lt1.size() - 2;
                                    }
                                    for (int j = 0; j < lt1.size(); j++) {
                                        if ((rou.getRouterState("" + lt1.get(j))).equals("Up")) {
                                            ln1.add("" + lt1.get(j));
                                        }
                                    }
                                    ln1 = l.findRemove(ln1, "" + p.getPacketSource());
                                    if ((ln1.size()) >= 200 * (tr.nodename.size())) {
                                        break;
                                    }
                                }
                            } else {
                                break;
                            }
                        }
                        ertcount.add("" + akcount);
                        nodell1 = l.removeDupli(nodell1);
                        nodell1 = l.findRemove(nodell1, "" + p.getPacketSource());
                        akl = nodell1;
                        akl.addFirst("" + p.getPacketSource());
                        stare.add(akl);
                    }
                }
            }
        }
        LinkedList akl1 = new LinkedList();
        System.out.println("" + stare);
        for (int i = 0; i < stare.size(); i++) {
            LinkedList all = (LinkedList) stare.get(i);
            boolean flag = true;
            boolean flag1 = true;
            for (int j = 0; j < all.size(); j++) {
                if (flag1 == true) {
                    if ((all.get(j)).equals(via)) {
                        flag1 = false;
                        j = 0;
                    }
                }
                if (flag1 == false) {
                    if ((all.get(j)).equals("" + p.getPacketDestination())) {
                        akl1 = all;
                        akl1.removeLast();
                        akl1.addLast("" + ertcount.get(i));
                        flag = false;
                        return akl1;
                    }
                }
            }
            if (flag == false) {
                break;
            }
        }
        return akl1;
    }

    public double getSourceRoutingEfficiancy(Efficiency_Packet p, String via1) {
        Efficiency_Router rou = new Efficiency_Router();
        // rb = new Efficiency_Router_Buffer();
        LinkedList akl = findDest(p, via1);
        for (int bi = 0; bi < akl.size() - 1; bi++) {
            //rb.addPacketToBuffer(p, "" + akl.get(bi));
        }
        double efficiancy = 0;
        int total = 0;
        double efficiancy1 = 0;
        try {
            int ur = Integer.parseInt("" + akl.getLast());
            if (ur == 0) {
                for (int i = 0; i < (akl.size() - 1); i++) {
                    efficiancy = efficiancy + rou.getRouterEfficiancy();
                }
                total = tr.nodename.size();
                efficiancy1 = efficiancy / total;
            } else {
                for (int i = 0; i < (akl.size() - ur); i++) {
                    efficiancy = efficiancy + rou.getRouterEfficiancy();
                }
                total = tr.nodename.size();
                efficiancy1 = efficiancy / total;
            }
            return (efficiancy1);
        } catch (Exception e) {
            return 0;
        }
    }
}
