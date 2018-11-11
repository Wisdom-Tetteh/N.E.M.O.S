package moos;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.packet.Packet;
import jpcap.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;
import java.util.List;
import javax.swing.ImageIcon;

public class Capture_Start extends javax.swing.JFrame {

    public Capture_Start() {
        initComponents();
        BtnStart.setEnabled(false);
        BtnEnd.setEnabled(false);
        BtnLogExit.setEnabled(false);
        combox_filter.setEnabled(false);
        setLocation(175, 135);
        setIconImage(new ImageIcon("src/nemos_demi/logo.png").getImage());

    }
    //Globals
    public static NetworkInterface[] Interfaces_Networks;
    public static JpcapCaptor JcapCapture;
    Capture_JcapProcess Jcap_Thread;
    public static int Capture_index = 0;
    public static int Flag_int = 0;
    public static int int_counter = 0;
    boolean JcapCaptureState = false;
    public static int thread_number = 0;

    JpcapWriter jcap_writer = null;
    List<Packet> capture_packet_list = new ArrayList<>();

    public void CapturePackets() {

        Jcap_Thread = new Capture_JcapProcess() {

            public Object construct() {

                try {

                    JcapCapture = JpcapCaptor.openDevice(Interfaces_Networks[Capture_index], 65535, false, 20);
                    jcap_writer = JpcapWriter.openDumpFile(JcapCapture, "captureddata");
                    if ("UDP".equals(combox_filter.getSelectedItem().toString())) {
                        JcapCapture.setFilter("udp", true);
                    } else if ("TCP".equals(combox_filter.getSelectedItem().toString())) {
                        JcapCapture.setFilter("tcp", true);
                    } else if ("ICMP".equals(combox_filter.getSelectedItem().toString())) {
                        JcapCapture.setFilter("icmp", true);
                    }
                    //else if ("ARP".equals(combox_filter.getSelectedItem().toString())) {
                      //  JcapCapture.setFilter("arp", true);
                    //}

                    while (JcapCaptureState) {

                        JcapCapture.processPacket(1, new Capture_Packets_Info());
                        capture_packet_list.add(JcapCapture.getPacket());
                    }
                    JcapCapture.close();

                } catch (Exception e) {
                    System.out.print(e);
                }
                return 0;
            }

            public void finished() {
                this.interrupt();
            }
        };

        Jcap_Thread.start();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        scrollpane_table = new javax.swing.JScrollPane();
        table_packet = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        scrollpane_packetinfo = new javax.swing.JScrollPane();
        packet_textarea = new javax.swing.JTextArea();
        BtnNetworkSelect = new java.awt.Button();
        combox_filter = new javax.swing.JComboBox<>();
        BtnStart = new java.awt.Button();
        BtnEnd = new java.awt.Button();
        BtnLogExit = new java.awt.Button();
        jMenuBar1 = new javax.swing.JMenuBar();

        jMenu2.setText("File");
        jMenuBar2.add(jMenu2);

        jMenu3.setText("Edit");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("jMenu4");

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        setName("nemos_capture"); // NOI18N
        setResizable(false);

        table_packet.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N
        table_packet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Interface Number", "Packet Length", "Packet Source", "Packet Destination", "Protocols Using"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table_packet.setRowHeight(20);
        table_packet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_packetMouseClicked(evt);
            }
        });
        scrollpane_table.setViewportView(table_packet);

        packet_textarea.setEditable(false);
        packet_textarea.setColumns(20);
        packet_textarea.setFont(new java.awt.Font("Georgia", 2, 14)); // NOI18N
        packet_textarea.setRows(5);
        scrollpane_packetinfo.setViewportView(packet_textarea);

        BtnNetworkSelect.setActionCommand("List Interfaces");
        BtnNetworkSelect.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N
        BtnNetworkSelect.setForeground(new java.awt.Color(0, 0, 0));
        BtnNetworkSelect.setLabel("Select Network Interfaces");
        BtnNetworkSelect.setPreferredSize(new java.awt.Dimension(90, 26));
        BtnNetworkSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNetworkSelectActionPerformed(evt);
            }
        });

        combox_filter.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N
        combox_filter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Filter", "TCP", "UDP", "ICMP", "ARP" }));
        combox_filter.setPreferredSize(new java.awt.Dimension(320, 24));
        combox_filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combox_filterActionPerformed(evt);
            }
        });

        BtnStart.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N
        BtnStart.setLabel("Start ");
        BtnStart.setName(""); // NOI18N
        BtnStart.setPreferredSize(new java.awt.Dimension(83, 24));
        BtnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnStartActionPerformed(evt);
            }
        });

        BtnEnd.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N
        BtnEnd.setLabel("End ");
        BtnEnd.setPreferredSize(new java.awt.Dimension(83, 24));
        BtnEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEndActionPerformed(evt);
            }
        });

        BtnLogExit.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N
        BtnLogExit.setLabel("Save Logs and Exit ");
        BtnLogExit.setPreferredSize(new java.awt.Dimension(83, 24));
        BtnLogExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLogExitActionPerformed(evt);
            }
        });
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollpane_table)
                        .addGap(18, 18, 18)
                        .addComponent(scrollpane_packetinfo, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addComponent(combox_filter, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(BtnNetworkSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(BtnEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(BtnLogExit, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollpane_table, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollpane_packetinfo, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(combox_filter, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnEnd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnLogExit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnNetworkSelect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnStart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void table_packetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_packetMouseClicked

        Object obj = table_packet.getModel().getValueAt(table_packet.getSelectedRow(), 0);
        if (Capture_Packets_Info.rowList.get((int) obj)[4] == "TCP") {

            packet_textarea.setText("Packet Number: " + Capture_Packets_Info.rowList.get((int) obj)[0]
                    + "\nSequence Number: " + Capture_Packets_Info.rowList.get((int) obj)[10]
                    + "\nImplemented Protocol: " + Capture_Packets_Info.rowList.get((int) obj)[4]
                    + "\nSource IP: " + Capture_Packets_Info.rowList.get((int) obj)[2]
                    + "\nDestination IP: " + Capture_Packets_Info.rowList.get((int) obj)[3]
                    + "\nPacket Length: " + Capture_Packets_Info.rowList.get((int) obj)[1]
                    + "\nSource Port: " + Capture_Packets_Info.rowList.get((int) obj)[5]
                    + "\nDestination Port: " + Capture_Packets_Info.rowList.get((int) obj)[6]
                    + "\nAcknowledge: " + Capture_Packets_Info.rowList.get((int) obj)[7]
                    + "\nAcknowledge No: " + Capture_Packets_Info.rowList.get((int) obj)[8]
                    + "\nSequence No: " + Capture_Packets_Info.rowList.get((int) obj)[10]
                    //+ "\nOffset: " + Capture_Packets_Info.rowList.get((int) obj)[11]
                    + "\nPacket Header: " + Capture_Packets_Info.rowList.get((int) obj)[12]
                    + "\nData: " + Capture_Packets_Info.rowList.get((int) obj)[9]
            );

        } else if (Capture_Packets_Info.rowList.get((int) obj)[4] == "UDP") {
            packet_textarea.setText("Packet Number: " + Capture_Packets_Info.rowList.get((int) obj)[0]
                    + "\nImplemented Protocol:" + Capture_Packets_Info.rowList.get((int) obj)[4]
                    + "\nSource IP: " + Capture_Packets_Info.rowList.get((int) obj)[2]
                    + "\nDestination IP: " + Capture_Packets_Info.rowList.get((int) obj)[3]
                    + "\nPacket Length: " + Capture_Packets_Info.rowList.get((int) obj)[1]
                    + "\nSource Port: " + Capture_Packets_Info.rowList.get((int) obj)[5]
                    + "\nDestination Port: " + Capture_Packets_Info.rowList.get((int) obj)[6]
                    + "\nOffset: " + Capture_Packets_Info.rowList.get((int) obj)[8]
                    + "\nPacket Header: " + Capture_Packets_Info.rowList.get((int) obj)[9]
                    + "\nData: " + Capture_Packets_Info.rowList.get((int) obj)[7]
            );

        } else if (Capture_Packets_Info.rowList.get((int) obj)[4] == "ICMP") {
            packet_textarea.setText("Packet Number: " + Capture_Packets_Info.rowList.get((int) obj)[0]
                    + "\nImplemented Protocol:" + Capture_Packets_Info.rowList.get((int) obj)[4]
                    + "\nSource IP: " + Capture_Packets_Info.rowList.get((int) obj)[2]
                    + "\nDestination IP: " + Capture_Packets_Info.rowList.get((int) obj)[3]
                    + "\nPacket Length: " + Capture_Packets_Info.rowList.get((int) obj)[1]
                    + "\nChecksum: " + Capture_Packets_Info.rowList.get((int) obj)[5]
                    + "\nPacket Header: " + Capture_Packets_Info.rowList.get((int) obj)[6]
                    + "\nOffset: " + Capture_Packets_Info.rowList.get((int) obj)[7]
                    + "\nOriginate TimeStamp: " + Capture_Packets_Info.rowList.get((int) obj)[8] + "bits"
                    + "\nRecieve TimeStamp: " + Capture_Packets_Info.rowList.get((int) obj)[9] + "bits"
                    + "\nTransmit TimeStamp: " + Capture_Packets_Info.rowList.get((int) obj)[10] + "bits"
                    + "\nData: " + Capture_Packets_Info.rowList.get((int) obj)[11]
            );

        }
        else if (Capture_Packets_Info.rowList.get((int) obj)[4] == "ARP") {
            packet_textarea.setText("Packet Number: " + Capture_Packets_Info.rowList.get((int) obj)[0]
                    + "\nImplemented Protocol:" + Capture_Packets_Info.rowList.get((int) obj)[4]
                    + "\nSource IP: " + Capture_Packets_Info.rowList.get((int) obj)[2]
                    + "\nDestination IP: " + Capture_Packets_Info.rowList.get((int) obj)[3]
                    + "\nPacket Length: " + Capture_Packets_Info.rowList.get((int) obj)[1]
                    + "\nChecksum: " + Capture_Packets_Info.rowList.get((int) obj)[5]
                    + "\nPacket Header: " + Capture_Packets_Info.rowList.get((int) obj)[6]
                    + "\nOffset: " + Capture_Packets_Info.rowList.get((int) obj)[7]
                    + "\nOriginate TimeStamp: " + Capture_Packets_Info.rowList.get((int) obj)[8] + "bits"
                    + "\nRecieve TimeStamp: " + Capture_Packets_Info.rowList.get((int) obj)[9] + "bits"
                    + "\nTransmit TimeStamp: " + Capture_Packets_Info.rowList.get((int) obj)[10] + "bits"
                    + "\nData: " + Capture_Packets_Info.rowList.get((int) obj)[11]
            );

        }
    }//GEN-LAST:event_table_packetMouseClicked

    private void BtnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnStartActionPerformed

        JcapCaptureState = true;
        CapturePackets();
        BtnLogExit.setEnabled(false);
        combox_filter.setEnabled(false);
        BtnNetworkSelect.setEnabled(false);
    }//GEN-LAST:event_BtnStartActionPerformed

    private void BtnEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEndActionPerformed
        // TODO add your handling code here:
        JcapCaptureState = false;
        Jcap_Thread.finished();
        BtnLogExit.setEnabled(true);
        combox_filter.setEnabled(true);
        BtnNetworkSelect.setEnabled(true);
    }//GEN-LAST:event_BtnEndActionPerformed

    private void BtnNetworkSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNetworkSelectActionPerformed
        // TODO add your handling code here:
        Capture_Network_Select nw = new Capture_Network_Select();
    }//GEN-LAST:event_BtnNetworkSelectActionPerformed

    private void BtnLogExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLogExitActionPerformed

        Jcap_Thread = new Capture_JcapProcess() {
            public Object construct() {

                jcap_writer = null;
                try {
                    JcapCapture = JpcapCaptor.openDevice(Interfaces_Networks[Capture_index], 65535, false, 20);

                    jcap_writer = JpcapWriter.openDumpFile(JcapCapture, "nemos_capture.pcapng");
                } catch (IOException ex) {
                    Logger.getLogger(Capture_Start.class.getName()).log(Level.SEVERE, null, ex);
                }

                for (int i = 0; i < thread_number; i++) {
                    jcap_writer.writePacket(capture_packet_list.get(i));
                }

                return 0;
            }

            public void finished() {
                this.interrupt();
            }
        };

        Jcap_Thread.start();

    }//GEN-LAST:event_BtnLogExitActionPerformed

    private void combox_filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combox_filterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combox_filterActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Capture_Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Capture_Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Capture_Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Capture_Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Capture_Start().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static java.awt.Button BtnEnd;
    public static java.awt.Button BtnLogExit;
    public static java.awt.Button BtnNetworkSelect;
    public static java.awt.Button BtnStart;
    public static javax.swing.JComboBox<String> combox_filter;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    public static javax.swing.JTextArea packet_textarea;
    private javax.swing.JScrollPane scrollpane_packetinfo;
    private javax.swing.JScrollPane scrollpane_table;
    public static javax.swing.JTable table_packet;
    // End of variables declaration//GEN-END:variables
}
