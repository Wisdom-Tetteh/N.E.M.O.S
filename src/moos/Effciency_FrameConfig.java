/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moos;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.ImageIcon;

/**
 *
 * @author admin
 */
public class Effciency_FrameConfig extends JPanel implements ActionListener, ItemListener {

    JComboBox combobox1;
    JLabel label;
    JButton btn1, btn2;// btn4
    public static String frameconfig = "";
    Dimension dimension1;
    String akser = "";

    public Effciency_FrameConfig() {
        setSize(300, 300);
        setLocation(580, 5); //580 , 5
        dimension1 = getSize();
        setBorder(new EtchedBorder(7));
        setLayout(null);
        setBackground(new Color(240, 240, 240));
        combobox1 = new JComboBox();
        label = new JLabel("Determine Hops:");
        label.setFont(new java.awt.Font("Georgia", 3, 12));
        btn1 = new JButton("Hops");
        btn1.setFont(new java.awt.Font("Georgia", 3, 12));
        btn2 = new JButton("Efficiency");
        btn2.setFont(new java.awt.Font("Georgia", 3, 12));

        combobox1.setBackground(new Color(240, 240, 240));
        btn1.setBackground(new Color(240, 240, 240));
        btn2.setBackground(new Color(240, 240, 240));
      

        combobox1.addItem("");
        combobox1.addItem("Flooding Algorithm");
        combobox1.setFont(new java.awt.Font("Georgia", 3, 12));
        combobox1.addItem("Hot-Potato Algorithm");
       // combobox1.addItem("Source Routing Algorithm");
       // combobox1.addItem("Distance Vector Algorithm");
       // combobox1.addItem("Routing Information Algorithm");
       // combobox1.addItem("Link State Algorithm");
        label.setBounds(80, 5, 190, 25);
        label.setForeground(new Color(0, 0, 0));
        combobox1.setBounds(55, 35, 190, 25);
        btn1.setBounds((dimension1.width - 120) / 2, 100, 120, 25);
        btn2.setBounds((dimension1.width - 120) / 2, 160, 120, 25);
        
        
        

        add(label);
        add(combobox1);
        add(btn1);
        add(btn2);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        combobox1.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent ie) {
    }

    public void actionPerformed(ActionEvent ae) {
        String actioncommand = ae.getActionCommand();

        if (actioncommand.equals("Hops")) {
            frameconfig = "" + combobox1.getSelectedItem();
            if (("" + combobox1.getSelectedItem()).equals("Flooding Algorithm")) {
                Efficiency_Flooding flood = new Efficiency_Flooding();
                flood.getHops();
            }
            if (("" + combobox1.getSelectedItem()).equals("Hot-Potato Algorithm")) {
                Efficiency_Hotpotato hot = new Efficiency_Hotpotato();
                hot.getHops();
            }
        }
        if (actioncommand.equals("Efficiency")) {
            Efficiency_Config_Efficiency eff = new Efficiency_Config_Efficiency();
            eff.show();
        }
    }
}
