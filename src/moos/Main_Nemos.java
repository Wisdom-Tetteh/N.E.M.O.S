/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moos;

//import nemos.Login_Nemos;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.ImageIcon;

/**
 *
 * @author admin
 */
public class Main_Nemos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Login_Nemos login = new Login_Nemos();
        login.setVisible(true);
        setIconImage(new ImageIcon("src/nemos_demi/logo.png").getImage());
        
    }

    private static void setIconImage(Image image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
