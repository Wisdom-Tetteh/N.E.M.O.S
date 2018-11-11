package moos;

import com.sun.glass.events.KeyEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import jpcap.*;

public class Capture_Network_Select extends javax.swing.JFrame {

    public Capture_Network_Select() {
        initComponents();
        ListNetworkInterfaces();
        InputTxt.requestFocus();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setIconImage(new ImageIcon("src/nemos_demi/logo.png").getImage());
    }

    public void ListNetworkInterfaces() {

        Capture_Start.Interfaces_Networks = JpcapCaptor.getDeviceList();
        jTextArea1.setText("");
        for (int i = 0; i < Capture_Start.Interfaces_Networks.length; i++) {
            jTextArea1.append(
                    "\n\n-------------------------------------------------------------------Available Network Interface (" + i
                    + ") -----------------------------------------------------------------------");
            jTextArea1.append("\nNetwork Interface :   " + i);
            jTextArea1.append("\nNetwork Information:              "
                    + Capture_Start.Interfaces_Networks[i].name + "("
                    + Capture_Start.Interfaces_Networks[i].description + ")");
            jTextArea1.append("\nDatalink Name:         "
                    + Capture_Start.Interfaces_Networks[i].datalink_name + "("
                    + Capture_Start.Interfaces_Networks[i].datalink_description + ")");
            jTextArea1.append("\nNetwork Mac Address:            ");

            byte[] R = Capture_Start.Interfaces_Networks[i].mac_address;
            for (int A = 0; A < Capture_Start.Interfaces_Networks.length; A++) {

            }

            NetworkInterfaceAddress[] INT = Capture_Start.Interfaces_Networks[i].addresses;
            jTextArea1.append("\nInternet  Protocol Address(IP):                " + INT[0].address);
            jTextArea1.append("\nNetwork Subnet Mask:            " + INT[0].subnet);
            jTextArea1.append("\nNetwork Broadcast Address: " + INT[0].broadcast);

            Capture_Start.int_counter++;
        }
    }

    public void ChooseInterface() {

        int TEMP = Integer.parseInt(InputTxt.getText());

        if (TEMP > -1 && TEMP < Capture_Start.int_counter) {
            Capture_Start.Capture_index = TEMP;
            Capture_Start.BtnStart.setEnabled(true);
            Capture_Start.combox_filter.setEnabled(true);
            Capture_Start.BtnEnd.setEnabled(true);
            Capture_Start.BtnLogExit.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Selected Interface not available. NEMOS Interfaces range  = 0-" + (Capture_Start.int_counter - 1) + ".");
            Capture_Network_Select nw = new Capture_Network_Select();

        }

        InputTxt.setText("");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroolpane_networklist = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        EnableBtn = new javax.swing.JButton();
        InputTxt = new java.awt.TextField();
        Network_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("Georgia", 2, 12)); // NOI18N
        setName("Interfaces list"); // NOI18N

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        scroolpane_networklist.setViewportView(jTextArea1);

        EnableBtn.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        EnableBtn.setText("Enable");
        EnableBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnableBtnActionPerformed(evt);
            }
        });

        InputTxt.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        InputTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputTxtActionPerformed(evt);
            }
        });
        InputTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                InputTxtKeyPressed(evt);
            }
        });

        Network_label.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        Network_label.setText("Enter Network Interface Number");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scroolpane_networklist)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Network_label)
                        .addGap(89, 89, 89)
                        .addComponent(InputTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                        .addComponent(EnableBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(InputTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Network_label, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EnableBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(scroolpane_networklist, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EnableBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnableBtnActionPerformed
        // TODO add your handling code here:
        ChooseInterface();
        setVisible(false);
    }//GEN-LAST:event_EnableBtnActionPerformed

    private void InputTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_InputTxtKeyPressed
        // TODO add your handling code here:
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            ChooseInterface();
            setVisible(true);
        }
    }//GEN-LAST:event_InputTxtKeyPressed

    private void InputTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputTxtActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Capture_Network_Select.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Capture_Network_Select.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Capture_Network_Select.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Capture_Network_Select.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Capture_Network_Select().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EnableBtn;
    private java.awt.TextField InputTxt;
    private javax.swing.JLabel Network_label;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JScrollPane scroolpane_networklist;
    // End of variables declaration//GEN-END:variables
}
