/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moos;

import javax.swing.ImageIcon;

/**
 *
 * @author admin
 */
public class Splash {
    public static void main (String[] args) throws InterruptedException{
        Splash_Nemos_GUI splash = new Splash_Nemos_GUI();
        for (int i = 0; i <= 100; i++){
            Thread.sleep(70);
            splash.setVisible(true);
            splash.setIconImage(new ImageIcon("src/nemos_demi/logo.png").getImage());
            splash.loading.setText("Starting NEMOS System...."+i+"%");
            splash.load.setValue(i);
            
            if (i == 100)
            {
               splash.dispose();
               Login_Nemos main = new Login_Nemos();
               main.setVisible(true);
               
                       
            }
        }
    }

         
    
}
