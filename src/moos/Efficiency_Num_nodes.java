/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author admin
 */
public class Efficiency_Num_nodes extends JFrame implements ActionListener {

    Container container;
    JTextField text_field;
    JButton btn1;
    JLabel label;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension dimension1, dimension2;
    static int nonodes = 0;
    JPanel panel;

    public Efficiency_Num_nodes() {
        nonodes = 0;
        container = getContentPane();
        container.setLayout(new BorderLayout());
        panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(240, 240, 240));
        setSize(350, 100);
        dimension1 = toolkit.getScreenSize();
        dimension2 = getSize();
        setLocation((dimension1.width - dimension2.width) / 2, (dimension1.height - dimension2.height) / 2);
        label = new JLabel("Number of Nodes:");
        label.setFont(new java.awt.Font("Georgia", 3, 12));
        text_field = new JTextField(20);
        text_field.setFont(new java.awt.Font("Georgia", 3, 12));
        btn1 = new JButton("Ok");
        btn1.setFont(new java.awt.Font("Georgia", 3, 12));
        btn1.setBackground(new Color(240, 240, 240));
        label.setForeground(new Color(0, 0, 0));
        text_field.setBackground(new Color(240, 240, 240));
        text_field.setFont(new Font("Dialog", Font.ITALIC, 14));
        panel.add(label);
        panel.add(text_field);
        panel.add(btn1);
        container.add(panel);
        btn1.addActionListener(this);
        setIconImage(new ImageIcon("src/nemos_demi/logo.png").getImage());
    }

    public void actionPerformed(ActionEvent ae) {
        String ser = "";
        try {
            if ((ae.getActionCommand()).equals("Ok")) {
                if (!(text_field.getText()).equals("")) {
                    ser = text_field.getText();
                    nonodes = Integer.parseInt(ser);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "NEMOS - Numbers Only");
                }
            }
        } catch (Exception e) {
            text_field.setText("");
            JOptionPane.showMessageDialog(this, "NEMOS - Numbers Only");
        }
        text_field.requestFocus();
    }
}
