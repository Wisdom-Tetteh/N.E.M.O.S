/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moos;

//import nemos_database.SignUpNemos;
//import nemos_icud.Nemos_User;
/**
 *
 * @author admin
 */
public class Controller_Nemos {

    private static Controller_Nemos controller;
    private final SignUpNemos iCrudImpl;

    private Controller_Nemos() {
        this.iCrudImpl = new SignUpNemos();
        this.iCrudImpl.openConnection();
    }

    public static Controller_Nemos getController() {
        if (controller == null) {
            controller = new Controller_Nemos();
        }
        return controller;
    }

    public boolean signup(Nemos_User user) {
        return this.iCrudImpl.insert(user);
    }

    public Nemos_User login(String username, String password) {
        return this.iCrudImpl.getUser(username, password);
    }
}
