/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moos;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;
import javax.swing.ImageIcon;
import java.io.*;
import java.awt.image.*;

/**
 *
 * @author admin
 */
public class Efficiency_Start extends JFrame implements ActionListener {

    public static String topologyname = "";
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Container container;
    Dimension Dimension;
    
    //Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //this.setLocation(dim.width/2-this.getSize().width/2,dim.height/2-this.getSize().height/2)

    topologyreader reader_topology = new topologyreader();
    public LinkedList link_list1 = new LinkedList();
    public LinkedList link_list2 = new LinkedList();
    Dimension dimenson_2;
    ImageIcon image_icon;
    Image comp_image = toolkit.getImage("src/nemos_demi/logo.png");  // Toolkit enables image insertion and size
    int integer = 0;

    JScrollPane scroll_pane_a, scroll_pane_b;
    JPanel panel1;
    static LinkedList routerbuffers = new LinkedList();
    JMenuBar menu_bar;
    JMenu menu;
    JMenuItem menu_item_a, menu_item_b;

    public Efficiency_Start() {
        container = getContentPane();
        container.setLayout(null);
        setIconImage(new ImageIcon("src/nemos_demi/logo.png").getImage());
        setResizable(false);
        setLocation(240, 135);   // padding left - padding up
        setBackground(new Color(204, 204, 204));
        Dimension dimension_1 = toolkit.getScreenSize();
        // = Dimension.height;
        // = Dimension.width;
        setSize(dimension_1.width - 470, dimension_1.height - 220);  //set size of the default display window); -450  -150
        setTitle("");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
       
  
        //int windowX = Math.max(0, (dimension_1.width  - windowSize.width ) / 2);
        //int windowY = Math.max(0, (dim.height - windowSize.height) / 2);
        dimenson_2 = getSize();
        menu_bar = new JMenuBar();
        
        

        setJMenuBar(menu_bar);
        panel1 = new JPanel(null);
        panel1.setBounds(0, 0, dimenson_2.width, dimenson_2.height);
        panel1.setBackground(new Color(240, 240, 240));
        container.add(panel1);
        panel1.add(new Effciency_FrameConfig());
        panel1.add(new Select_topology());

    }
   
    public void drawtopology() {
        integer += 1;
        setTitle("Topology  : " + topologyname.toLowerCase());

        try {
            if (integer > 1) {
                panel1.removeAll();
                Linkdraw_panel ld = new Linkdraw_panel();
                nodedraw nd = new nodedraw();
                panel1.add(ld);
                panel1.add(nd);
                panel1.add(new Effciency_FrameConfig());
                panel1.add(new Select_topology());
            }
            Linkdraw_panel ld = new Linkdraw_panel();
            nodedraw nd = new nodedraw();
            panel1.add(ld);
            panel1.add(nd);
            panel1.add(new Effciency_FrameConfig());
            panel1.add(new Select_topology());
            setSize(dimenson_2.width - 1, dimenson_2.height - 1);
            setSize(dimenson_2.width + 1, dimenson_2.height + 1);
        } catch (Exception e) {
            System.out.println("  " + e);
        }
    }
    
  
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class Select_topology extends JPanel implements ActionListener, ItemListener {

        JButton btn1, btn2, btn3;
        JTextField textfield;
        JLabel label;
        JComboBox combox_1;
        JFileChooser file_choose;
        Efficiency_Num_nodes eff_nonodes = new Efficiency_Num_nodes();

        public Select_topology() {
            setSize(300, 150); // Frame size for Select topology
            setLocation(580, 357); //580,310
            setBorder(new EtchedBorder(15));
            setLayout(null);
            setBackground(new Color(240, 240, 240));
            setIconImage(new ImageIcon("src/nemos_demi/logo.png").getImage());
            btn1 = new JButton("Proceed");
            btn1.setFont(new java.awt.Font("Georgia", 3, 12));  // THIS SETS UP THE FONT OF THE BUTTON Proceed
            btn2 = new JButton("..");
            btn2.setFont(new java.awt.Font("Georgia", 3, 12));
            combox_1 = new JComboBox();
            textfield = new JTextField();
            combox_1.setBackground(new Color(240, 240, 240));
            textfield.setBackground(new Color(240, 240, 240));
            btn1.setBackground(new Color(240, 240, 240));
            btn2.setBackground(new Color(240, 240, 240));
            combox_1.addItem("");

            combox_1.addItem("Ring Topology".toString());
            combox_1.setFont(new java.awt.Font("Georgia", 3, 12));
            combox_1.addItem("Mesh Topology".toString());
            combox_1.addItem("Bus Topology".toString());
            combox_1.addItem("Star Topology".toString());
            label = new JLabel("Path:");
            label.setFont(new java.awt.Font("Georgia", 3, 12));
            label.setForeground(new Color(0, 0, 0));
            textfield.setForeground(new Color(0, 0, 0));
            label.setBounds(15, 10, 45, 25);
            combox_1.setBounds(100, 10, 110, 25);
            textfield.setBounds(60, 10, 110, 25);
            btn1.setBounds(110, 80, 90, 25);
            btn2.setBounds(170, 10, 25, 25);
            add(label);
            add(combox_1);
            add(btn1);
            add(btn2);
            btn1.addActionListener(this);
            btn2.addActionListener(this);
            combox_1.addItemListener(this);
           
        }

        public void itemStateChanged(ItemEvent ie) {
            eff_nonodes.show();
        }

        public void actionPerformed(ActionEvent ae) {
            topologywriter tw = new topologywriter();

            if ((ae.getActionCommand()).equals("Proceed")) {
                String fname = "";
                action_btn.states.clear();
                routerbuffers.clear();
                boolean flag = true;
                if (!(edit_file.filename_config).equals("")) {
                    fname = edit_file.filename_config;
                    add(combox_1);
                    remove(textfield);
                    flag = false;
                    JOptionPane.showMessageDialog(this, "Topology Selected is" + edit_file.filename_config);
                } else {
                    add(combox_1);
                    remove(textfield);
                }
                if (!("" + combox_1.getSelectedItem()).equals("-Select-")) {
                    fname = "" + combox_1.getSelectedItem();
                } else {
                    if (flag == true) {
                        JOptionPane.showMessageDialog(this, "Select the topology");
                    }
                }
                if (fname.equalsIgnoreCase("Ring Topology")) {
                    tw.writeRing(Efficiency_Num_nodes.nonodes);
                }
                if (fname.equalsIgnoreCase("Bus Topology")) {
                    tw.writeBus(Efficiency_Num_nodes.nonodes);
                }
                if (fname.equalsIgnoreCase("Mesh Topology")) {
                    tw.writeMesh(Efficiency_Num_nodes.nonodes);
                }
                if (fname.equalsIgnoreCase("Star Topology")) {
                    tw.writeStar(Efficiency_Num_nodes.nonodes);
                }
                topologyname = fname;
                reader_topology.freader(fname);
                drawtopology();
                drawtopology();
            }
            if ((ae.getActionCommand()).equals("..")) {
                remove(combox_1);
                add(textfield);
                combox_1.setSelectedIndex(0);
                edit_file fe = new edit_file();
                fe.show();
                textfield.setText("" + edit_file.filename_config);
            }
        }
    }

    public static void main(String args[]) {
        JFrame start = new Efficiency_Start();
        start.show();
        
    }
}

class canvas_dialog extends Canvas {

    String node_name = "";

    public canvas_dialog(String nodename, int l, int t) {
        node_name = nodename;
        node_name = node_name.toUpperCase();
        setFont(new Font("Dialog", Font.ITALIC, 15));

        setForeground(new Color(240, 240, 120));
        setSize(35, 15);
        setLocation(l, t);
        
    }

    public void paint(Graphics g) {
        g.drawString(node_name, 10, 10);
    }
}

class action_btn extends JButton implements ActionListener {

    PopupMenu popmenu;
    MenuItem menu_1, menu_2, menu_3;
    Efficiency_Router route_eff;
    String source = "";
    String router_state = "Up";
    topologyreader tr = new topologyreader();
    static LinkedList states = new LinkedList();
    String router_name = "";

    public action_btn(String name, int x, int y, ImageIcon icon3) {
        tr.freader(Efficiency_Start.topologyname);
        source = name;
        setSize(35, 35);
        setLocation(x, y);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        //setBackground(new Color(120,130,180));
        addActionListener(this);
        setIcon(icon3);
        setFont(new Font("Dialog", Font.ITALIC, 1));
        setText(name);
        
        popmenu = new PopupMenu();
        menu_1 = new MenuItem("Choose State");
        menu_1.setFont(new java.awt.Font("Georgia", 3, 12));
        menu_2 = new MenuItem("Down");
        menu_2.setFont(new java.awt.Font("Georgia", 3, 12));
        menu_3 = new MenuItem("Up");
        menu_3.setFont(new java.awt.Font("Georgia", 3, 12));
        menu_1.setFont(new Font("Dialog", Font.ITALIC, 12));
        menu_2.setFont(new Font("Dialog", Font.ITALIC, 12));
        menu_3.setFont(new Font("Dialog", Font.ITALIC, 12));
        popmenu.add(menu_1);
        popmenu.add(menu_2);
        route_eff = new Efficiency_Router(name);
        add(popmenu);
        menu_1.addActionListener(this);
        menu_2.addActionListener(this);
        menu_3.addActionListener(this);
        addMouseListener(new mouse_adapter());
        for (int i = 0; i < tr.nodename.size(); i++) {
            states.add("" + tr.nodename.get(i));
            states.add("Up");
        }
        if (states.size() > (2 * (tr.nodename.size()))) {
            int srt = 2 * (tr.nodename.size());
            for (int i = srt; i < states.size(); i++) {
                states.remove(i);
            }
        }
    }

    public void setState(String state) {
        router_state = state;
    }

    public String getState() {
        return (router_state);
    }

    class mouse_adapter extends MouseAdapter {

        public void mouseReleased(MouseEvent me) {
            router_name = getText();
            if (me.isPopupTrigger()) {
                if ((getState()).equals("Down")) {
                    popmenu.remove(menu_2);
                    popmenu.add(menu_3);
                }
                if ((getState()).equals("Up")) {
                    popmenu.remove(menu_3);
                    popmenu.add(menu_2);
                }
                popmenu.show(me.getComponent(), me.getX(), me.getY());
            }
        }
    }

    public void actionPerformed(ActionEvent ae) {  //sor - source of routing protocol 
        String sor = ae.getActionCommand();
        if ((sor.equals("Choose State")) || (sor.equals("Down")) || (sor.equals("Up"))) {
            if (sor.equals("Choose State")) {
                // rbv = new Efficiency_Router_Buffer_View();
                //rbv.showBuffer("" + router_name);
                //rbv.show();
            }
            if (sor.equals("Down")) {
                menu_1.setEnabled(false);
                setState("Down");
                router_state = getState();
                ImageIcon icon = new ImageIcon("src/nemos_demi/down.png");
                setIcon(icon);
                for (int i = 0; i < states.size(); i++) {
                    if ((states.get(i)).equals(source)) {
                        states.set(i + 1, "Down");
                        break;
                    }
                }
            }
            if (sor.equals("Up")) {
                menu_1.setEnabled(true);
                setState("Up");
                router_state = getState();
                ImageIcon icon = new ImageIcon("src/nemos_demi/icon3.png");
                setIcon(icon);
                for (int i = 0; i < states.size(); i++) {
                    if ((states.get(i)).equals(source)) {
                        states.set(i + 1, "Up");
                        break;
                    }
                }
            }
        } else {
            if (getState().equals("Up")) {
                if ((Effciency_FrameConfig.frameconfig).equals("Source Protocol")) {
                    Efficiency_Source_Protocol sr = new Efficiency_Source_Protocol(sor);
                    sr.show();
                }
                if ((Effciency_FrameConfig.frameconfig).equals("Distance Protocol")) {
                    Efficiency_DistanceVector dvr = new Efficiency_DistanceVector(sor);
                    dvr.show();
                }
                if ((Effciency_FrameConfig.frameconfig).equals("Routing Information Protocal")) {
                    Efficiency_RoutingInformationProtocol ri = new Efficiency_RoutingInformationProtocol(sor);
                    ri.show();
                }
                if ((Effciency_FrameConfig.frameconfig).equals("Link State Protocol")) {
                    Efficiency_LinkstateRoute lsr = new Efficiency_LinkstateRoute(sor);
                    lsr.show();
                }
            }
        }
    }
}

class edit_file extends JFrame implements ActionListener {

    JScrollPane scrollpane;
    JTextArea text_area;
    JEditorPane editorpane;
    JButton Btn1, Btn2, Btn3;
    Container container;
    Dimension dimension_1, dimension_2;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    String filename = "";
    JFileChooser filechooser;
    static String filename_config = "";

    public edit_file() {
        filename_config = "";
        container = getContentPane();
        setSize(500, 500);
        dimension_1 = toolkit.getScreenSize();
        dimension_2 = getSize();
        setLocation((dimension_1.width - dimension_2.width) / 2, (dimension_1.height - dimension_2.height) / 2);
        container.setLayout(null);
        text_area = new JTextArea();
        text_area.setBackground(new Color(240, 240, 240));
        text_area.setForeground(new Color(240, 240, 240));
        text_area.setFont(new Font("Dialog", Font.ITALIC, 12));
        editorpane = new JEditorPane();
        scrollpane = new JScrollPane(text_area);
        Btn1 = new JButton("Select File");
        Btn1.setFont(new java.awt.Font("Georgia", 3, 12));
        Btn2 = new JButton("Save");
        Btn2.setFont(new java.awt.Font("Georgia", 3, 12));
        Btn3 = new JButton("Ok");
        Btn3.setFont(new java.awt.Font("Georgia", 3, 12));
        scrollpane.setBounds(0, 0, dimension_2.width - 20, dimension_2.height - 100);
        Btn1.setBounds(20, (dimension_2.height - 70), 100, 25);
        Btn2.setBounds(120, (dimension_2.height - 70), 100, 25);
        Btn3.setBounds(260, (dimension_2.height - 70), 100, 25);
        container.add(scrollpane);
        container.add(Btn1);
        container.add(Btn2);
        container.add(Btn3);
        Btn1.addActionListener(this);
        Btn2.addActionListener(this);
        Btn3.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if ((ae.getActionCommand()).equals("Select File")) {
            try {
                filechooser = new JFileChooser();
                filechooser.setCurrentDirectory(new File("C:\\Users\\admin\\Desktop")); //Location for the selection of file topology
                filechooser.showOpenDialog(this);
                filename = "" + filechooser.getSelectedFile();
                FileInputStream fin = new FileInputStream(filename);
                String str = "";
                int size = fin.available();
                for (int i = 0; i < size; i++) {
                    str = str + (char) fin.read();
                }
                text_area.setText(str.toUpperCase());
            } catch (Exception e) {
            }
        }
        if ((ae.getActionCommand()).equals("Save")) {
            try {
                FileOutputStream fout = new FileOutputStream(filename);
                String str = text_area.getText();
                int size = str.length();
                for (int i = 0; i < size; i++) {
                    fout.write((str.charAt(i)));
                }
            } catch (Exception e) {
            }
        }
        if ((ae.getActionCommand()).equals("Ok")) {
            filename_config = filename;
            setVisible(false);
        }
    }
}

class nodedraw extends JPanel {

    Dimension dimension1;
    topologyreader tr = new topologyreader();
    static LinkedList linkedlist1 = new LinkedList();
    static LinkedList linkedlist2 = new LinkedList();
    Toolkit toolkit = Toolkit.getDefaultToolkit();

    public nodedraw() {
        tr.freader(Efficiency_Start.topologyname);
        setSize(550, 500);
        setBackground(new Color(200, 200, 200));
        setBorder(new EtchedBorder(7));
        dimension1 = getSize();
        Image img = toolkit.getImage("src/nemos_demi/icon3.png");
        boolean nb = toolkit.prepareImage(img, 5, 5, this);
        setLocation(5, 5);
        ImageIcon iicon = new ImageIcon(img);
        setLayout(null);
        for (int i = 0; i < tr.nodename.size(); i++) {
            double d1 = Double.parseDouble(("" + tr.nodexcord.get(i))) * (640 / 2);
            double d2 = Double.parseDouble(("" + tr.nodeycord.get(i))) * (480 / 2);
            add(new action_btn("" + tr.nodename.get(i), (int) d1, (int) d2, iicon));
            int i1 = (int) d1;
            int i2 = (int) d2;
            linkedlist1.add("" + i1);
            linkedlist2.add("" + i2);
            add(new canvas_dialog("" + tr.nodename.get(i), (int) (d1), (int) (d2 + 35)));
        }
    }
}

class Linkdraw_panel extends JPanel {

    Dimension d1;
    topologyreader tr = new topologyreader();
    int x1 = 0, y1 = 0, x2 = 0, y2 = 0;

    public Linkdraw_panel() {
        tr.freader(Efficiency_Start.topologyname);
        setSize(550, 500);
        setBackground(new Color(240, 240, 240));
        setBorder(new EtchedBorder(5));
        d1 = getSize();
        setLocation(5, 5);
        setFont(new Font("Dialog", Font.ITALIC, 12));
        
    }

    public void paint(Graphics g) {
        try {
            for (int i = 0; i < nodedraw.linkedlist1.size(); i++) {
                findlinkstart("" + tr.linkstart.get(i));
                findlinkend("" + tr.linkend.get(i));
                if (x1 == x2) {
                    if (y1 < y2) {
                        y1 = y1 + 45;
                    }
                    if (y1 > y2) {
                        y2 = y2 + 45;
                    }
                    x1 = x1 + 15;
                    x2 = x2 + 15;
                }
                if (y1 == y2) {
                    if (x1 < x2) {
                        x2 = x2 - 15;
                    }
                    if (x1 > x2) {
                        x1 = x1 - 15;
                    }
                }
                if (x1 != x2) {
                    x1 = x1 + 15;
                    y1 = y1 + 20;
                    y2 = y2 + 20;
                    x2 = x2 + 15;
                }
                g.drawLine(x1, y1, x2, y2);
                setForeground(new Color(0, 102, 102));
                g.drawString(("" + tr.linkname.get(i)).toUpperCase(), (x1 + x2) / 2, (y1 + y2) / 2 + 2);
            }
        } catch (Exception e) {
        }
    }

    void findlinkstart(String linkstart) {
        double d1 = 0, d2 = 0;
        for (int i = 0; i < tr.nodename.size(); i++) {
            if (("" + tr.nodename.get(i)).equals(linkstart)) {
                d1 = Double.parseDouble(("" + tr.nodexcord.get(i))) * (640 / 2);
                d2 = Double.parseDouble(("" + tr.nodeycord.get(i))) * (480 / 2);
                x1 = (int) d1;
                y1 = (int) d2;
            }
        }
    }

    public void findlinkend(String linkend) {
        double d1 = 0, d2 = 0;
        for (int i = 0; i < tr.nodename.size(); i++) {
            if (("" + tr.nodename.get(i)).equals(linkend)) {
                d1 = Double.parseDouble(("" + tr.nodexcord.get(i))) * (640 / 2);
                d2 = Double.parseDouble(("" + tr.nodeycord.get(i))) * (480 / 2);
                x2 = (int) d1;
                y2 = (int) d2;
            }
        }
    }
}
