/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moos;

//import nemos_icud.Nemos_User;
/**
 *
 * @author admin
 */
public interface LoginNemos {

    boolean insert(Nemos_User user);

    Nemos_User getUser(String userName, String password);
}
