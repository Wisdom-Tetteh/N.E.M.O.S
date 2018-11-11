/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author admin
 */
public interface Portactions_Nemos extends ActionListener, ChangeListener {

    /**
     * Manages the button actions after clicking : Action listeners event
     */
    void actionPerformed(ActionEvent ae);

    /**
     * Manages the state change for the JCheckBox
     */
    void stateChanged(ChangeEvent ce);

}
