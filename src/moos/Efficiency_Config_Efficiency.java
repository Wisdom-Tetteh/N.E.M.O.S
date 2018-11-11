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
import javax.swing.ImageIcon;

/**
 *
 * @author admin
 */
public class Efficiency_Config_Efficiency extends JFrame implements ActionListener {

    JComboBox combox1, combox2, combox3;
    JLabel label1, label2, label3, label4, label5, label6;
    JButton btn1, btn2;
    Container container;
    JTable table;
    JScrollPane scrollpane;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension dimension1, dimension2;
    topologyreader tr = new topologyreader();
    JPanel panel;

    public Efficiency_Config_Efficiency() {
        container = getContentPane();
        setSize(400, 300);
        dimension2 = getSize();
        dimension1 = toolkit.getScreenSize();
        setLocation((dimension1.width - dimension2.width) / 2, (dimension1.height - dimension2.height) / 2);
        container.setLayout(null);
        setTitle("");
        setBackground(new Color(240, 240, 240));
        label1 = new JLabel("Packet Information");
        label1.setFont(new java.awt.Font("Georgia", 3, 12));
        label2 = new JLabel("Source");
        label2.setFont(new java.awt.Font("Georgia", 3, 12));
        label3 = new JLabel("Destination");
        label3.setFont(new java.awt.Font("Georgia", 3, 12));
        label5 = new JLabel("(For Source Routing)");
        label5.setFont(new java.awt.Font("Georgia", 3, 12));
        label6 = new JLabel("Via");
        label6.setFont(new java.awt.Font("Georgia", 3, 12));
        panel = new JPanel(null);
        ImageIcon iicon = new ImageIcon("back.gif");
        label1.setBackground(new Color(240, 240, 240));
        label2.setBackground(new Color(240, 240, 240));
        label3.setBackground(new Color(240, 240, 240));
        label5.setBackground(new Color(240, 240, 240));
        label6.setBackground(new Color(240, 240, 240));
        setIconImage(new ImageIcon("src/nemos_demi/logo.png").getImage());

        btn2 = new JButton(iicon);
        btn2.setBackground(new Color(240, 240, 240));

        btn2.setPressedIcon(new ImageIcon("logo.png"));
        btn2.setFocusPainted(false);
        btn2.setBorderPainted(false);
        btn2.setContentAreaFilled(false);
        label1.setForeground(new Color(0, 0, 0));
        label2.setForeground(new Color(0, 0, 0));
        label3.setForeground(new Color(0, 0, 0));
        label5.setForeground(new Color(0, 0, 0));
        label6.setForeground(new Color(0, 0, 0));
        combox1 = new JComboBox();
        combox2 = new JComboBox();
        combox3 = new JComboBox();
        combox1.setBackground(new Color(240, 240, 240));
        combox1.setFont(new java.awt.Font("Georgia", 3, 12));
        combox2.setBackground(new Color(240, 240, 240));
        combox2.setFont(new java.awt.Font("Georgia", 3, 12));
        combox3.setBackground(new Color(240, 240, 240));
        combox3.setFont(new java.awt.Font("Georgia", 3, 12));
        panel.setBackground(new Color(240, 240, 240));

        tr.freader(Efficiency_Start.topologyname);
        combox1.addItem("Select");
        combox2.addItem("Select");
        combox3.addItem("Select");
        for (int i = 0; i < tr.nodename.size(); i++) { //topology reader for heading
            combox1.addItem("" + tr.nodename.get(i));
            combox2.addItem("" + tr.nodename.get(i));
            combox3.addItem("" + tr.nodename.get(i));
        }
        btn1 = new JButton("Continue");
        btn1.setFont(new java.awt.Font("Georgia", 3, 12));
        btn1.setBackground(new Color(240, 240, 240));
        panel.setBounds(0, 0, dimension2.width, dimension2.height);
        container.add(panel);
        label1.setBounds(20, 20, 200, 25);
        label1.setFont(new Font("Dialog", Font.ITALIC, 14));
        label2.setBounds(20, 60, 100, 35);
        combox1.setBounds(150, 60, 100, 25);
        label3.setBounds(20, 100, 100, 25);
        combox2.setBounds(150, 100, 100, 25);
        label6.setBounds(20, 140, 100, 25);
        combox3.setBounds(150, 140, 100, 25);
        label5.setBounds(250, 140, 100, 25);
        btn1.setBounds(160, 200, 100, 25);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        label5.setFont(new Font("Dialog", Font.PLAIN, 10));
        panel.add(label1);
        panel.add(label2);
        panel.add(combox1);
        panel.add(label3);
        panel.add(combox2);
        panel.add(btn1);
        panel.add(label6);
        panel.add(label6);
        panel.add(combox3);
        container.add(label5);
    }

    public void actionPerformed(ActionEvent ae) {// Selecting the packet information
        Efficiency_Link_Config l = new Efficiency_Link_Config();
        Efficiency_Router r = new Efficiency_Router();
        String ser = ae.getActionCommand();
        if (ser.equals("Continue")) {  // Selecting the packet information 
            if (("" + combox1.getSelectedItem()).equals("Select") || ("" + combox2.getSelectedItem()).equals("Select") || ("" + combox3.getSelectedItem()).equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select All the Values");
            } else {
                String source = "" + combox1.getSelectedItem(); //fill the packet information
                String dest = "" + combox2.getSelectedItem();
                String via = "" + combox3.getSelectedItem();

                if ((r.getRouterState("" + source)).equals("Up") && (r.getRouterState("" + dest)).equals("Up") && (r.getRouterState("" + via)).equals("Up")) {
                    setSize(500, 250); // r = router .. this is to check the state of the nodes
                    String s[][] = new String[6][3];
                    String heads[] = {"Algorithm", "Efficiency", "Delay(s)"};
                    setTitle("Source :" + combox1.getSelectedItem() + " Destination :" + combox2.getSelectedItem() + " Via :" + combox3.getSelectedItem());
                    table = new JTable(s, heads);
                    table.setRowSelectionAllowed(false);
                    table.setCellSelectionEnabled(false);

                    scrollpane = new JScrollPane(table);
                    dimension2 = getSize();
                    label4 = new JLabel();
                    label4.setBackground(new Color(240, 240, 240));

                    //label4.setText("Router Efficiency  : " + r.getRouterEfficiancy() + "   Link Efficiency(Speed:57344) : " + l.getLinkEfficiancy(57344) + " Link Efficiency(Speed:131000) : " + l.getLinkEfficiancy(131000));
                    label4.setText("NEMOS NETWORK EFFICIENCY");
                    label4.setBounds(5, dimension2.height - 70, dimension2.width, 25);
                    label4.setFont(new Font("Dialog", Font.PLAIN, 12));
                    label4.setForeground(new Color(0, 0, 0));
                    btn2.setBounds(dimension2.width - 100, dimension2.height - 90, 80, 40);
                    panel.setBounds(0, 0, dimension2.width, dimension2.height);
                    btn2.setFont(new Font("Dialog", Font.ITALIC, 1));
                    btn2.setText("back");
                    setLocation((dimension1.width - dimension2.width) / 2, (dimension1.height - dimension2.height) / 2);
                    table.setForeground(new Color(0, 0, 0));
                    table.setFont(new Font("Dialog", Font.ITALIC, 14));
                    scrollpane.setBounds(5, 5, dimension2.width - 20, dimension2.height - 100);
                    table.setValueAt("Flooding Algorithm", 0, 0);// To set the items and arrangement in the table list
                    table.setBackground(new Color(240, 240, 240));
                    table.setValueAt("Hot-Potato Algorithm", 1, 0);
                    table.setValueAt("Source Algorithm", 2, 0);
                    table.setValueAt("Distance Vector Algortihm", 3, 0);
                    table.setValueAt("Routing Information Algortihm", 4, 0);
                    table.setValueAt("Link State Algorithm", 5, 0);
                    double efr = 0;

                    Efficiency_Flooding flood = new Efficiency_Flooding();
                    efr = flood.getFloodingEfficiancy(new Efficiency_Packet(source, dest));
                    table.setValueAt("" + efr, 0, 1);

                    Efficiency_Hotpotato hp = new Efficiency_Hotpotato();
                    efr = hp.getHotpotatoEfficiancy(new Efficiency_Packet(source, dest));
                    table.setValueAt("" + efr, 1, 1);

                    Efficiency_Source_Protocol sour = new Efficiency_Source_Protocol(source);
                    efr = sour.getSourceRoutingEfficiancy(new Efficiency_Packet(source, dest), "" + combox3.getSelectedItem());
                    table.setValueAt("" + efr, 2, 1);

                    Efficiency_DistanceVector dvr = new Efficiency_DistanceVector(source);
                    efr = dvr.getDistenceVectorEfficiancy(new Efficiency_Packet(source, dest));
                    table.setValueAt("" + efr, 3, 1);
                    table.setValueAt("" + dvr.getDistenceVectorDelay(), 3, 2);

                    Efficiency_RoutingInformationProtocol akrip = new Efficiency_RoutingInformationProtocol(source);
                    efr = akrip.getRIPEfficiancy(new Efficiency_Packet(source, dest));
                    table.setValueAt("" + efr, 4, 1);

                    Efficiency_LinkstateRoute lsr = new Efficiency_LinkstateRoute(source);
                    efr = lsr.getLinkStateEfficiancy(new Efficiency_Packet(source, dest));
                    table.setValueAt("" + efr, 5, 1);
                    table.setValueAt("" + lsr.getLinkStateDelay(), 5, 2);

                    panel.removeAll();
                    panel.add(scrollpane);
                    panel.add(label4);
                    panel.add(btn2);
                    setSize(dimension2.width - 1, dimension2.height - 1);
                    setSize(dimension2.width + 1, dimension2.height + 1);
                    setSize(dimension2.width - 1, dimension2.height - 1);
                    setSize(dimension2.width + 1, dimension2.height + 1);
                } else {
                    JOptionPane.showMessageDialog(this, "NEMOS Node(s) is Down,Up to Continue Efficiency Check");
                }
            }
        }
        if (ser.equals("back")) {
            setSize(400, 300);
            dimension2 = getSize();
            panel.setSize(dimension2.width, dimension2.height);
            setLocation((dimension1.width - dimension2.width) / 2, (dimension1.height - dimension2.height) / 2);
            panel.removeAll();
            panel.add(label1);
            panel.add(label2);
            panel.add(combox1);
            panel.add(label3);
            panel.add(combox2);
            panel.add(btn1);
            panel.add(label6);
            panel.add(label6);
            panel.add(combox3);
            panel.add(label5);
            setSize(dimension2.width - 1, dimension2.height - 1);
            setSize(dimension2.width + 1, dimension2.height + 1);
            setSize(dimension2.width - 1, dimension2.height - 1);
            setSize(dimension2.width + 1, dimension2.height + 1);

        }
    }

}
