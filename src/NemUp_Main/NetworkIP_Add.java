/*
 * NetworkIP_Add.java
 *
 */

package NemUp_Main;

import javax.swing.JOptionPane;

/**
 *
 * @author  admin
 */
public class NetworkIP_Add extends javax.swing.JDialog {
    
    /** Creates new form AddObjectDialog */
    public NetworkIP_Add(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldReachNetInter = new javax.swing.JTextField();
        jTextFieldPorts = new javax.swing.JTextField();
        jTextFieldtimeout = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldObject = new javax.swing.JTextField();
        jBtnOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        setLocation(new java.awt.Point(80, 100));
        setLocationByPlatform(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Scan options"));
        jPanel1.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N
        jLabel1.setText("Scan Through");

        jLabel2.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N
        jLabel2.setText("Check open ports:");

        jLabel3.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N
        jLabel3.setText("Ping timeout (ms):");

        jTextFieldReachNetInter.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N
        jTextFieldReachNetInter.setText("192.168.1.3");

        jTextFieldPorts.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N
        jTextFieldPorts.setText("21 23 80 445");

        jTextFieldtimeout.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N
        jTextFieldtimeout.setText("3000");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldtimeout, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldPorts, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addComponent(jTextFieldReachNetInter, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldReachNetInter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldtimeout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldPorts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Object to scan"));
        jPanel2.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N

        jTextFieldObject.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N
        jTextFieldObject.setText("192.168.0.254");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jTextFieldObject, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                .addGap(67, 67, 67))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jTextFieldObject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jBtnOk.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jBtnOk.setText("Add Network IP ");
        jBtnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jBtnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(296, 296, 296))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jBtnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOkActionPerformed
        if (checkFeilds()) {
            NemUp_Start.AddNewElement(
                    NemUp_Start.jListObjects, 
                    NemUp_Start.objectModel, 
                    jTextFieldObject.getText());
            NemUp_Main.ObjectsList.AddNewObject(new String[] {
                    jTextFieldObject.getText(),
                    jTextFieldReachNetInter.getText(),
                    jTextFieldtimeout.getText(),
                    jTextFieldPorts.getText()});
            this.dispose();
        }
    }//GEN-LAST:event_jBtnOkActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NetworkIP_Add dialog = new NetworkIP_Add(new javax.swing.JFrame(), true);
                dialog.setVisible(true);
            }
        });
    }
    
    public boolean checkFeilds()
    {
        // one instance of object is allowed in the list
         if (NemUp_Start.getElements(NemUp_Start.objectModel).indexOf(jTextFieldObject.getText()) != -1)
        {
            JOptionPane.showMessageDialog(null, "Object already  exists !!");
            jTextFieldObject.selectAll();
            jTextFieldObject.setFocusable(true);
            return false;
        }       
        
        // IP Validation check 
        if (!IPAddress.validObject(jTextFieldObject.getText())) 
        {
            JOptionPane.showMessageDialog(null, "Invalid Object !!");
            jTextFieldObject.selectAll();
            jTextFieldObject.setFocusable(true);
            return false;
        }  
         
        // IP Validation check 
        if (!IPAddress.validIPAddress(jTextFieldReachNetInter.getText())) 
        {
            JOptionPane.showMessageDialog(null, "Invalid IP Adresse !!");
            jTextFieldReachNetInter.selectAll();
            jTextFieldReachNetInter.setFocusable(true);            
            return false;
        }           
        
         // valid time out
        int timeout = NemUp_Utilities.StrToInt(jTextFieldtimeout.getText());
        if (timeout <= -1)
        {    
            JOptionPane.showMessageDialog(null, "Invalid Time out !!");
            jTextFieldtimeout.selectAll();
            jTextFieldtimeout.setFocusable(true);            
            return false;            
        }
        
        // valid port range
        if (!NemUp_Main.Ports.validPort(jTextFieldPorts.getText()))
        {    
            JOptionPane.showMessageDialog(null, "Invalid port range !!");
            jTextFieldPorts.selectAll();
            jTextFieldPorts.setFocusable(true);            
            return false;            
        }   
        return true;
    }    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextFieldObject;
    private javax.swing.JTextField jTextFieldPorts;
    private javax.swing.JTextField jTextFieldReachNetInter;
    private javax.swing.JTextField jTextFieldtimeout;
    // End of variables declaration//GEN-END:variables
    
}
