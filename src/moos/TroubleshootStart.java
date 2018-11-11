package moos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ImageIcon;

/**
 *
 * @author admin
 */
public class TroubleshootStart
        extends JFrame implements TroubleshootInterface {

    private JButton IpcheckStart;private JLabel Status_client;
    private JLabel Status_client2;
    private JPanel Tab_client;
    private JTextField Host_dns;
    private JTextArea IP_dns;
    private JButton Resolve_dns;
    private JTextField Server_dns;
    private JLabel Status_dns;
    private JComboBox Type_dns;
    private JTextField Down_speed;

    private JTextField IPinternetTF;
    private JTextField IPintranetTF;
    private JLabel Status_IP;
    private JLabel label_1;
    private JLabel label_10;
    private JLabel label_11;
    private JLabel label_12;
    private JLabel label_13;
    private JLabel label_14;
    private JLabel label_15;
    private JLabel label_16;
    private JLabel label_2;
    private JLabel label_3;
    private JLabel label_4;
    private JLabel label_5;
    private JLabel label_6;
    private JLabel label_7;
    private JLabel label_8;
    private JLabel label_9;
    private JPanel panel_1;
    private JPanel panel_10;
    private JPanel panel_2;
    private JPanel panel_3;
    private JPanel panel_5;
    private JPanel panel_6;
    private JPanel panel_7;
    private JPanel panel_8;
    private JPanel panel_9;
    private JScrollPane Scroll_pane1;
    private JTabbedPane Tabbed_pane1;
    private JLabel Latency_label;
    private JTextField Latency_max_ever;
    private JTextField Latency_max_window;
    private JTextField Host_ping;
    private JButton Ping_startBtn;
    private JLabel StatusPing_label;
    private JTextField PortServerfield;
    private JButton serverStart;
    private JLabel Status_Server;
    private JPanel TabToolsPanel;
    private JTextField UpSpeedfield;

    public Troubleshoot_Client Client;
    public Troubleshoot_Ping Ping;
    public Troubleshoot_DNS DNS;

    public int[] latency_integer;
    public char mode_char;
    public int Max_ever_int; public int Max_window_int;
    private JTextField Host_client;
    private JComboBox Mode_client;
    private JTextField Port_client;
    private JButton Start_client;
    private JButton startclientBtn2;
    
   

    public TroubleshootStart() {
        initComponents();
        setTitle("");
        setLocation(175, 135); //300 - 170
     //Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //this.setLocation(dim.width/2-this.getSize().width/2,dim.height/2-this.getSize().height/2)
        setSize(1064, 503);
        setResizable(false);
        this.latency_integer = new int[this.Latency_label.getWidth()];
        setIconImage(new ImageIcon("src/nemos_demi/logo.png").getImage());
    }
    

    private void initComponents() {
        this.Tabbed_pane1 = new JTabbedPane();
        Tabbed_pane1.setFont(new java.awt.Font("Georgia", 3, 12));

        this.Tab_client = new JPanel();
        Tab_client.setFont(new java.awt.Font("Georgia", 3, 12));
        this.panel_8 = new JPanel();
        panel_8.setFont(new java.awt.Font("Georgia", 3, 12));
        this.label_4 = new JLabel();

        this.label_3 = new JLabel();
        label_3.setFont(new java.awt.Font("Georgia", 3, 12));
        this.label_2 = new JLabel();
        label_2.setFont(new java.awt.Font("Georgia", 3, 12));
        this.Port_client = new JTextField();
        Port_client.setFont(new java.awt.Font("Georgia", 3, 12));

        this.Mode_client = new JComboBox();
        Mode_client.setFont(new java.awt.Font("Georgia", 3, 12));
        this.Host_client = new JTextField();
        Host_client.setFont(new java.awt.Font("Georgia", 3, 12));
        this.panel_9 = new JPanel();
        panel_9.setFont(new java.awt.Font("Georgia", 3, 12));
        this.Status_client = new JLabel();

        this.Start_client = new JButton();
        Start_client.setFont(new java.awt.Font("Georgia", 3, 12));

        this.label_5 = new JLabel();

        this.UpSpeedfield = new JTextField();
        UpSpeedfield.setFont(new java.awt.Font("Georgia", 3, 12));
        this.label_6 = new JLabel();
        label_6.setFont(new java.awt.Font("Georgia", 3, 12));
        this.Down_speed = new JTextField();
        Down_speed.setFont(new java.awt.Font("Georgia", 3, 12));
        this.panel_10 = new JPanel();
        panel_10.setFont(new java.awt.Font("Georgia", 3, 12));

        this.Latency_label = new JLabel() {
            public void paintComponent(Graphics g) {
                TroubleshootStart.this.paintLatency(g);
            }
        };
        this.startclientBtn2 = new JButton();
        startclientBtn2.setFont(new java.awt.Font("Georgia", 3, 12));
        this.Status_client2 = new JLabel();
        this.label_15 = new JLabel();
        this.Latency_max_ever = new JTextField();
        Latency_max_ever.setFont(new java.awt.Font("Georgia", 3, 12));
        this.label_16 = new JLabel();
        this.Latency_max_window = new JTextField();
        Latency_max_window.setFont(new java.awt.Font("Georgia", 3, 12));
        this.panel_7 = new JPanel();
        this.Status_Server = new JLabel();
        this.PortServerfield = new JTextField();
        PortServerfield.setFont(new java.awt.Font("Georgia", 3, 12));
        this.serverStart = new JButton();
        serverStart.setFont(new java.awt.Font("Georgia", 3, 12));
        this.label_1 = new JLabel();
        this.TabToolsPanel = new JPanel();
        TabToolsPanel.setFont(new java.awt.Font("Georgia", 3, 12));
        this.panel_5 = new JPanel();
        panel_5.setFont(new java.awt.Font("Georgia", 3, 12));
        this.label_8 = new JLabel();
        this.Host_ping = new JTextField();
        Host_ping.setFont(new java.awt.Font("Georgia", 3, 12));

        this.Ping_startBtn = new JButton();
        Ping_startBtn.setFont(new java.awt.Font("Georgia", 3, 12));
        this.StatusPing_label = new JLabel();
        this.panel_6 = new JPanel();
        panel_6.setFont(new java.awt.Font("Georgia", 3, 12));
        this.label_7 = new JLabel();
        this.Host_dns = new JTextField();
        Host_dns.setFont(new java.awt.Font("Georgia", 3, 12));
        this.label_9 = new JLabel();

        this.Resolve_dns = new JButton();
        Resolve_dns.setFont(new java.awt.Font("Georgia", 3, 12));
        this.Status_dns = new JLabel();
        this.label_11 = new JLabel();
        this.Server_dns = new JTextField();
        Server_dns.setFont(new java.awt.Font("Georgia", 3, 12));
        this.Scroll_pane1 = new JScrollPane();
        Scroll_pane1.setFont(new java.awt.Font("Georgia", 3, 12));
        this.IP_dns = new JTextArea();
        IP_dns.setFont(new java.awt.Font("Georgia", 3, 12));
        this.Type_dns = new JComboBox();
        Type_dns.setFont(new java.awt.Font("Georgia", 3, 12));
        this.label_12 = new JLabel();
        this.panel_3 = new JPanel();
        panel_3.setFont(new java.awt.Font("Georgia", 3, 12));
        this.IpcheckStart = new JButton();
        IpcheckStart.setFont(new java.awt.Font("Georgia", 3, 12));
        this.label_13 = new JLabel();
        this.label_14 = new JLabel();
        this.IPinternetTF = new JTextField();
        IPinternetTF.setFont(new java.awt.Font("Georgia", 3, 12));
        this.IPintranetTF = new JTextField();
        IPintranetTF.setFont(new java.awt.Font("Georgia", 3, 12));
        this.Status_IP = new JLabel();
        Status_IP.setFont(new java.awt.Font("Georgia", 3, 12));
        this.panel_1 = new JPanel();
        panel_1.setFont(new java.awt.Font("Georgia", 3, 12));
        this.panel_2 = new JPanel();
        panel_2.setFont(new java.awt.Font("Georgia", 3, 12));
        this.label_10 = new JLabel();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("");
        setResizable(false);

        this.panel_8.setBorder(BorderFactory.createTitledBorder(""));
        panel_8.setFont(new java.awt.Font("Georgia", 3, 12));

        this.label_4.setText("Mode");
        label_4.setFont(new java.awt.Font("Georgia", 3, 12));

        this.label_3.setText("Port");
        label_3.setFont(new java.awt.Font("Georgia", 3, 12));

        this.label_2.setText("Server");
        label_2.setFont(new java.awt.Font("Georgia", 3, 12));

        this.Port_client.setText("80");

        this.Mode_client.setModel(new DefaultComboBoxModel(new String[]{"Full Duplex", "Send Only", "Receive Only"}));

        this.Host_client.setText("127.0.0.1");

        this.panel_9.setBorder(BorderFactory.createTitledBorder(""));
        panel_9.setFont(new java.awt.Font("Georgia", 3, 12));

        this.Status_client.setText("");
        Status_client.setFont(new java.awt.Font("Georgia", 3, 12));

        this.Start_client.setText("Test Bandwidth ");
        Start_client.setFont(new java.awt.Font("Georgia", 3, 12));
        this.Start_client.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                TroubleshootStart.this.clientStartActionPerformed(evt);
            }
        });
        this.label_5.setText("Upload Speed");
        label_5.setFont(new java.awt.Font("Georgia", 3, 12));

        this.UpSpeedfield.setEditable(false);

        this.label_6.setText("Download Speed");

        this.Down_speed.setEditable(false);

               GroupLayout jPanel9Layout = new GroupLayout(this.panel_9);
        this.panel_9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(jPanel9Layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(this.Status_client, -1, -1, 32767)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.Start_client))
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(this.label_5)
                                        .addGap(31, 31, 31)
                                        .addComponent(this.UpSpeedfield))
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(this.label_6)
                                        .addGap(18, 18, 18)
                                        .addComponent(this.Down_speed, -1, 288, 32767)))
                        .addContainerGap()));

        jPanel9Layout.setVerticalGroup(jPanel9Layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.label_5)
                                .addComponent(this.UpSpeedfield, -1, -1, -1))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.label_6)
                                .addComponent(this.Down_speed, -1, -1, -1))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(this.Status_client)
                                .addComponent(this.Start_client))
                        .addContainerGap()));

        //this.panel_10.setBorder(BorderFactory.createTitledBorder("Transmission Error Test"));
        panel_10.setFont(new java.awt.Font("Georgia", 3, 12));

        this.Latency_label.setBackground(new Color(0, 0, 0));
        this.Latency_label.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        this.startclientBtn2.setText("Start Ping");
        this.startclientBtn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                TroubleshootStart.this.clientStart2ActionPerformed(evt);
            }
        });
        this.Status_client2.setText("");
        Status_client2.setFont(new java.awt.Font("Georgia", 3, 12));

       // this.label_15.setText("Max (us)");
        label_15.setFont(new java.awt.Font("Georgia", 3, 12));

        //this.Latency_max_ever.setEditable(false);

        //this.label_16.setText("Window Max (us)");
        label_16.setFont(new java.awt.Font("Georgia", 3, 12));  //354- 390

        this.Latency_max_window.setEditable(false);

        GroupLayout jPanel8Layout = new GroupLayout(this.panel_8);
        this.panel_8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(jPanel8Layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(this.label_2)
                                                .addComponent(this.label_3))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(this.Host_client)
                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                        .addComponent(this.Port_client, -1, 88, -1)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(this.label_4)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(this.Mode_client, -1, 152, -1)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767))))
                                .addComponent(this.panel_9, -1, -1, 32767)
                                .addComponent(this.panel_10, -1, -2, 32767))
                        .addContainerGap()));

        jPanel8Layout.setVerticalGroup(jPanel8Layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.label_2)
                                .addComponent(this.Host_client, -1, -1, -1))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.label_3)
                                .addComponent(this.Port_client, -1, -1, -1)
                                .addComponent(this.Mode_client, -1, -1, -1)
                                .addComponent(this.label_4))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(this.panel_9, -2, -1, -2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(this.panel_10, -2, -1, -2)
                        .addContainerGap(-1, 32767)));

       // this.panel_7.setBorder(BorderFactory.createTitledBorder("Server"));
        panel_7.setFont(new java.awt.Font("Georgia", 3, 12));

        //this.Status_Server.setText("");
        Status_Server.setFont(new java.awt.Font("Georgia", 3, 12));

        //this.PortServerfield.setText("3306");
        PortServerfield.setFont(new java.awt.Font("Georgia", 3, 12));

        this.serverStart.setText("Test");
        this.serverStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               // TroubleshootStart.this.serverStartActionPerformed(evt);
            }
        });
        this.label_1.setText("Port");
        label_1.setFont(new java.awt.Font("Georgia", 3, 12));

       

        GroupLayout clientTabLayout = new GroupLayout(this.Tab_client);
        this.Tab_client.setLayout(clientTabLayout);
        clientTabLayout.setHorizontalGroup(clientTabLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(clientTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(clientTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.panel_8, -1, -1, 32767)
                                .addComponent(this.panel_7, -1, -1, 32767))
                        .addContainerGap()));

        clientTabLayout.setVerticalGroup(clientTabLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, clientTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(this.panel_7, -1, -1, -1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(this.panel_8, -1, -1, -1)
                        .addContainerGap(-1, 32767)));

        this.Tabbed_pane1.addTab("Bandwidth Test", this.Tab_client);
        Tabbed_pane1.setFont(new java.awt.Font("Georgia", 3, 12));

        //this.panel_5.setBorder(BorderFactory.createTitledBorder("Ping"));
        panel_5.setFont(new java.awt.Font("Georgia", 3, 12));

        this.label_8.setText("Host");
        label_8.setFont(new java.awt.Font("Georgia", 3, 12));

        this.Host_ping.setText("localhost");
        Host_ping.setFont(new java.awt.Font("Georgia", 3, 12));

        this.Ping_startBtn.setText("Ping Start");
        this.Ping_startBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                TroubleshootStart.this.Ping_start_action(evt);
            }
        });
        this.StatusPing_label.setText("");
        StatusPing_label.setFont(new java.awt.Font("Georgia", 3, 12));

        GroupLayout jPanel5Layout = new GroupLayout(this.panel_5);
        this.panel_5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(jPanel5Layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(this.label_8)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.Host_ping))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(this.StatusPing_label, -1, 357, 32767)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.Ping_startBtn)))
                        .addContainerGap()));

        jPanel5Layout.setVerticalGroup(jPanel5Layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.label_8)
                                .addComponent(this.Host_ping, -1, -1, -1))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.Ping_startBtn)
                                .addComponent(this.StatusPing_label))
                        .addContainerGap()));
        this.panel_6.setBorder(BorderFactory.createTitledBorder("")); //DNS panel
        panel_6.setFont(new java.awt.Font("Georgia", 3, 12));

        this.label_7.setText("Host:");
        label_7.setFont(new java.awt.Font("Georgia", 3, 12));

        this.Host_dns.setText("google.com");

        this.label_9.setText("Output:");
        label_9.setFont(new java.awt.Font("Georgia", 3, 12));

        this.Resolve_dns.setText("Check DNS Server Info");
        this.Resolve_dns.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                TroubleshootStart.this.Dns_resolve_action(evt);
            }
        });
        this.Status_dns.setText("");
        Status_dns.setFont(new java.awt.Font("Georgia", 3, 12));

        this.label_11.setText("Server:");
        label_11.setFont(new java.awt.Font("Georgia", 3, 12));

        this.Server_dns.setText("8.8.8.8");
        Server_dns.setFont(new java.awt.Font("Georgia", 3, 12));

        this.IP_dns.setEditable(false);
        this.IP_dns.setColumns(20);
        this.IP_dns.setRows(5);
        this.Scroll_pane1.setViewportView(this.IP_dns);

        this.Type_dns.setModel(new DefaultComboBoxModel(new String[]{"A", "MX", "AAAA"}));

        this.label_12.setText("DNS Recods:");
        label_12.setFont(new java.awt.Font("Georgia", 3, 12));

        GroupLayout jPanel6Layout = new GroupLayout(this.panel_6);
        this.panel_6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(jPanel6Layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(this.label_7)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.Host_dns)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.label_12)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.Type_dns, -1, -1, -1))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(this.label_11)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.Server_dns))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(this.label_9)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.Scroll_pane1))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(this.Status_dns, -1, -1, 32767)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.Resolve_dns)))
                        .addContainerGap()));

        jPanel6Layout.setVerticalGroup(jPanel6Layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.label_7)
                                .addComponent(this.Host_dns, -1, -1, -1)
                                .addComponent(this.Type_dns, -1, -1, -1)
                                .addComponent(this.label_12))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.label_11)
                                .addComponent(this.Server_dns, -1, -1, -1))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.label_9)
                                .addComponent(this.Scroll_pane1, -1, 150, -1))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.Status_dns)
                                .addComponent(this.Resolve_dns))
                        .addContainerGap(-1, 32767)));

        this.panel_3.setBorder(BorderFactory.createTitledBorder(""));

        this.IpcheckStart.setText("Check IP Addresses Info");
        this.IpcheckStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                TroubleshootStart.this.checkipStartActionPerformed(evt);
            }
        });
        this.label_13.setText("Internet IP:");
        label_13.setFont(new java.awt.Font("Georgia", 3, 12));

        this.label_14.setText("Intranet IP:");
        label_14.setFont(new java.awt.Font("Georgia", 3, 12));

        this.IPinternetTF.setEditable(false);

        this.IPintranetTF.setEditable(false);

        this.Status_IP.setText("");

        GroupLayout jPanel3Layout = new GroupLayout(this.panel_3);
        this.panel_3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(this.Status_IP, -1, -1, 32767)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.IpcheckStart))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(this.label_13)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.IPinternetTF))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(this.label_14)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.IPintranetTF)))
                        .addContainerGap()));

        jPanel3Layout.setVerticalGroup(jPanel3Layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.label_13)
                                .addComponent(this.IPinternetTF, -1, -1, -1))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.label_14)
                                .addComponent(this.IPintranetTF, -1, -1, -1))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.IpcheckStart)
                                .addComponent(this.Status_IP))
                        .addContainerGap()));

        GroupLayout toolsTabLayout = new GroupLayout(this.TabToolsPanel);
        this.TabToolsPanel.setLayout(toolsTabLayout);
        toolsTabLayout.setHorizontalGroup(toolsTabLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(toolsTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(toolsTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.panel_5, -1, -1, 32767)
                                .addComponent(this.panel_6, -1, -1, 32767)
                                .addComponent(this.panel_3, -1, -1, 32767))
                        .addContainerGap()));

        toolsTabLayout.setVerticalGroup(toolsTabLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(toolsTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(this.panel_5, -1, -1, -1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(this.panel_6, -1, -1, -1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(this.panel_3, -1, -1, -1)
                        .addContainerGap(100, 32767)));

        this.Tabbed_pane1.addTab("Network Troubleshoot", this.TabToolsPanel);

        GroupLayout jPanel1Layout = new GroupLayout(this.panel_1);
        this.panel_1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(this.panel_2, -1, -1, 32767)
                        .addContainerGap()));

        jPanel1Layout.setVerticalGroup(jPanel1Layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(this.panel_2, -1, -1, -1)
                        .addContainerGap(426, 32767)));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(this.Tabbed_pane1, -1, 475, -1));

        layout.setVerticalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(this.Tabbed_pane1));

        pack();
    }

    private void Dns_resolve_action(ActionEvent evt) {
        if (this.DNS == null) {
            this.IP_dns.setText("");
            this.DNS = new Troubleshoot_DNS(this, this.Server_dns.getText(), this.Host_dns.getText(), (String) this.Type_dns.getSelectedItem());
            this.DNS.start();
            this.Resolve_dns.setText("Stop");
        } else {
            this.DNS.close();
            this.DNS = null;
            this.Resolve_dns.setText("DNS Information");
        }
    }

    private void Ping_start_action(ActionEvent evt) {
        if (this.Ping == null) {
            this.Ping = new Troubleshoot_Ping(this, this.Host_ping.getText());
            this.Ping.start();
            this.Ping_startBtn.setText("Stop Ping");
        } else {
            this.Ping.close();
            this.Ping = null;
            this.Ping_startBtn.setText("Ping Network Server");
        }
    }

    private void clientStartActionPerformed(ActionEvent evt) {
        startClientBandwidthTest();
    }

    private void checkipStartActionPerformed(ActionEvent evt) {
        getIP();
    }

    private void clientStart2ActionPerformed(ActionEvent evt) {
        //startClientLatencyTest();
    }

    public static void main(String[] args) {
        boolean server = false;
        int port = 33000;
        if ((args != null) && (args.length > 0)) {
            for (int a = 0; a < args.length; a++) {
                if (args[a].equals("-server")) {
                    server = true;
                } else if (args[a].startsWith("-port=")) {
                    port = Integer.valueOf(args[a].substring(6)).intValue();
                }
            }
        }
        if (server) {
            //  new Troubleshoot_Server(null, port).start();
            return;
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TroubleshootStart().setVisible(true);
            }
        });
    }

    private char getMode() {
        return ((CharSequence) this.Mode_client.getSelectedItem()).charAt(0);
    }

    @Override
    public void setServerStatus(String status) {
        this.Status_Server.setText(status);
    }

    @Override
    public void setClientStatus(String status) {
        if (this.mode_char == 'L') {
            this.Status_client2.setText(status);
        } else {
            this.Status_client.setText(status);
        }
    }

    @Override
    public void setReadSpeed(String speed) {
        this.Down_speed.setText(speed);
    }

    @Override
    public void setWriteSpeed(String speed) {
        this.UpSpeedfield.setText(speed);
    }

    @Override
    public void setPingStatus(String status) {
        this.StatusPing_label.setText(status);
    }

    @Override
    public void setDNSStatus(String status) {
        this.Status_dns.setText(status);
    }

    @Override
    public void setDNSIP(String status) {
        this.IP_dns.setText(status);
        this.Resolve_dns.setText("Check DNS Server Info");
        this.Status_dns.setText("");
        this.DNS = null;
    }

    private void getIP() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://checkip.dyndns.org").openStream()));
            String line = reader.readLine();
            int i1 = line.indexOf("Address: ");
            int i2 = line.indexOf("</body>");
            this.IPinternetTF.setText(line.substring(i1 + 9, i2));

            Socket socket = new Socket("checkip.dyndns.org", 80);
            String intra = socket.getLocalAddress().getHostAddress();
            socket.close();
            this.IPintranetTF.setText(intra);
        } catch (Exception e) {
            e.printStackTrace();
            this.Status_IP.setText(e.toString());
        }
    }

    private void startClientBandwidthTest() {
        if (this.Client == null) {
            this.Host_client.setEnabled(false);
            this.Port_client.setEnabled(false);
            this.Mode_client.setEnabled(false);
            this.Start_client.setText("Stop");
            this.startclientBtn2.setEnabled(false);
            this.mode_char = getMode();
            this.Client = new Troubleshoot_Client(this, this.Host_client.getText(), Integer.valueOf(this.Port_client.getText()).intValue(), this.mode_char);
            this.Client.start();
        } else {
            this.Client.close();
            this.Client = null;
            this.Start_client.setText("Test Bandwidth Speed");
            Start_client.setFont(new java.awt.Font("Georgia", 3, 12));
            this.startclientBtn2.setEnabled(true);
            this.Status_client.setText("");
            Start_client.setFont(new java.awt.Font("Georgia", 3, 12));
            this.Host_client.setEnabled(true);
            this.Port_client.setEnabled(true);
            this.Mode_client.setEnabled(true);
        }
    }



    private void clearLatency() {
        Arrays.fill(this.latency_integer, 5);
        this.Max_ever_int = 0;
        this.Max_window_int = 0;
    }

    @Override
    public void addLatency(int value) {
        for (int a = 1; a < this.latency_integer.length; a++) {
            this.latency_integer[(a - 1)] = this.latency_integer[a];
        }
        this.latency_integer[(this.latency_integer.length - 1)] = value;
        this.Latency_label.repaint();
    }

    @Override
    public void paintLatency(Graphics g) {
        int w = this.latency_integer.length;
        int h = this.Latency_label.getHeight();
        int max = 1;
        for (int a = 0; a < w; a++) {
            if (this.latency_integer[a] > max) {
                max = this.latency_integer[a];
            }
        }
        if (max > this.Max_ever_int) {
            this.Max_ever_int = max;
            this.Latency_max_ever.setText(Integer.toString(this.Max_ever_int));
        }
        if (max != this.Max_window_int) {
            this.Max_window_int = max;
            this.Latency_max_window.setText(Integer.toString(this.Max_window_int));
        }
        //g.setBackground(new java.awt.Color(204, 51, 255));
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, w, h);
        g.setColor(Color.RED);
        for (int x = 0; x < w; x++) {
            int y = h - this.latency_integer[x] * 100 / max;
            if (y >= h) {
                y = h - 1;
            }
            if (y < 0) {
                y = 0;
            }
            g.drawLine(x, y, x, h - 2);
        }
    }
}
