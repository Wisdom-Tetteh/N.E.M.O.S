/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moos;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
/*
 *
 * @author admin
 */
public class Port_Nemos extends javax.swing.JFrame implements Portactions_Nemos {
    //Final variables

    private static final int Port_Width = 800;
    private static final int Port_Height = 500;

    //Flags
    private boolean outputResultDisplay = false;

    //Compoents included within the form GUI
    private JTextField IP, Port_lower, Port_higher;
    private JTextArea output;
    private JScrollPane resultsPanel;
    private JCheckBox nemosGUI;
    private JButton Port_Scan;
    private JButton Capture_Start;
    private JPanel configuration, resultArea;

    /**
     * Sets up the frame and calls
     *
     * @see #initComponents()
     */
    public Port_Nemos() {
        super("");

        initComponents();

        super.setLayout(new FlowLayout());
        super.setSize(Port_Width, Port_Height);
        super.setLocation(298, 145);

        super.setVisible(true);
        setFont(new java.awt.Font("Georgia", 3, 14));
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setIconImage(new ImageIcon("src/nemos_demi/logo.png").getImage());
    }

    /**
     * Sets up the components, panels, and adds them to the Frame
     */
    private final void initComponents() {
        //Text fields
        this.IP = new JTextField(21);
        this.Port_lower = new JTextField(20);
        this.Port_higher = new JTextField(19);
        Port_higher.setFont(new java.awt.Font("Georgia", 12, 12));
        Port_lower.setFont(new java.awt.Font("Georgia", 12, 12));
        IP.setFont(new java.awt.Font("Georgia", 12, 12));
        // this.setFont(new java.awt.Font("Georgia", 3, 14));

        //TextArea & ScrollPane
        this.output = new JTextArea(32, 35); // Height , Width
        this.output.setEditable(false);
        this.output.setLineWrap(true);
        this.resultsPanel = new JScrollPane(this.output);
        resultsPanel.setFont(new java.awt.Font("Georgia", 3, 12));
        output.setFont(new java.awt.Font("Georgia", 3, 12));

        //Check box
        this.nemosGUI = new JCheckBox("Closed and Open Ports ");
        this.nemosGUI.addChangeListener(this);
        nemosGUI.setFont(new java.awt.Font("Georgia", 3, 12));

        //Buttons
        this.Port_Scan = new JButton("Execute Scan");
        Port_Scan.setFont(new java.awt.Font("Georgia", 3, 14));
        this.Port_Scan.addActionListener(this);
        this.Capture_Start = new JButton("Access Capture");
        this.Capture_Start.addActionListener(this);
        Capture_Start.setFont(new java.awt.Font("Georgia", 3, 12));

        //JPanels
        this.configuration = new JPanel(new FlowLayout());
        //this.configuration.setBorder(BorderFactory.createTitledBorder("INPUT OF NEMOS SCAN"));
        this.configuration.setPreferredSize(new Dimension(300, 460)); //Width . Height
        this.configuration.add(new JLabel(" IP Address : "));
        this.configuration.add(this.IP);
        IP.setFont(new java.awt.Font("Georgia", 3, 12));
        this.configuration.add(new JLabel("Lower Port: "));
        this.configuration.add(this.Port_lower);

        //this.configuration.add(new JLabel("\n\n\n"));
        this.configuration.add(new JLabel("Higher Ports: "));
        this.configuration.add(this.Port_higher);
        this.configuration.add(this.nemosGUI);
        this.configuration.add(this.Port_Scan);
        configuration.setFont(new java.awt.Font("Georgia", 3, 12));

        this.resultArea = new JPanel(new FlowLayout());
        //this.resultArea.setBorder(BorderFactory.createTitledBorder("RESULTS OF NEMOS SCAN "));
        this.resultArea.add(resultsPanel);
        resultArea.setFont(new java.awt.Font("Georgia", 3, 12));

        //add components
        super.add(this.configuration);
        super.add(this.resultArea);
    }

    /**
     * Manages the button actions after clicking : Action listeners event
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.Port_Scan) {
            this.output.setText("Scanning ports.............please wait!!" + System.lineSeparator());
            scan(this.IP.getText(), this.Port_lower.getText(), this.Port_higher.getText(), 200);
            this.output.append("Ports scan completed.");
            this.setFont(new java.awt.Font("Georgia", 3, 14));
        }

    }

    /**
     * Manages the state change for the JCheckBox
     */
    @Override
    public void stateChanged(ChangeEvent ce) {
        if (ce.getSource() == nemosGUI) {
            this.outputResultDisplay = this.nemosGUI.isSelected();

        }

    }

    /**
     * Scans the specified IP address and tests the ports in the given range
     *
     * @param IP The IP address to scan
     * @param port_Start The port number that the scanner starts with
     * @param port_End The port number that the scanner ends with
     * @param timeout The timeout in milliseconds for the scan
     *
     * @see #java.net.InetSocketAddress
     */
    private final void scan(String IP, String port_Start, String port_End, int timeout) {
        int start, end;

        //verify port numbers
        try {
            start = Integer.parseInt(port_Start);
            end = Integer.parseInt(port_End);

            if (end <= start) {
                this.output.append("Nemos Port Validation failed, 2nd Port > 1st Port " + System.lineSeparator());
                return;
            }
        } catch (NumberFormatException nfe) {
            this.output.append("Please enter valid port numbers." + System.lineSeparator());
            this.setFont(new java.awt.Font("Georgia", 3, 14));
            return;
        }

        //Scan ports in range
        for (int current = start; current <= end; current++) {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(IP, current), timeout); //attempt a connection
                socket.close();

                this.output.append("Open ports available: " + current + System.lineSeparator());
                this.setFont(new java.awt.Font("Georgia", 3, 14));
            } catch (IOException ioe) { //connection failed
                if (this.outputResultDisplay) {
                    this.output.append("Closed port: " + current + System.lineSeparator());
                }
            }
        }
    }

    /**
     * Creates an instance of this GUI
     */
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        Component port_gui = new Port_Nemos();
    }
}
