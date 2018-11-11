/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moos;

import java.util.*;

/**
 *
 * @author admin
 */
public class Efficiency_Router {

    LinkedList nexthop = new LinkedList();
    topologyreader tr = new topologyreader();
    String router_state = "Up";
    int efficiancy = 2;
    double delays = 0;

    public Efficiency_Router() {
        tr.freader(Efficiency_Start.topologyname);
    }

    public Efficiency_Router(String name) {
        tr.freader(Efficiency_Start.topologyname);
    }

    public String getRouterState(String state) {
        for (int i = 0; i < action_btn.states.size(); i++) {
            if (state.equals("" + action_btn.states.get(i))) {
                return ("" + action_btn.states.get(i + 1));
            }
        }
        return "";
    }

    public int getRouterEfficiancy() {
        return (efficiancy);
    }

    public LinkedList getNextHop(String source, String state) {
        LinkedList dl = new LinkedList();
        for (int i = 0; i < tr.linkname.size(); i++) {
            if (source.equals("" + tr.linkstart.get(i)) && (state.equals("Up"))) {
                if ((getRouterState("" + tr.linkend.get(i))).equals("Up")) {
                    dl.add("" + tr.linkend.get(i));
                }
            }
            if (source.equals("" + tr.linkend.get(i)) && (state.equals("Up"))) {
                if ((getRouterState("" + tr.linkstart.get(i))).equals("Up")) {
                    dl.add("" + tr.linkstart.get(i));
                }

            }
        }
        return dl;
    }

    public LinkedList getNextHop(String source) {
        LinkedList dl = new LinkedList();
        for (int i = 0; i < tr.linkname.size(); i++) {
            if (source.equals("" + tr.linkstart.get(i))) {
                dl.add("" + tr.linkend.get(i));
            }
            if (source.equals("" + tr.linkend.get(i))) {
                dl.add("" + tr.linkstart.get(i));
            }
        }
        return dl;
    }

}
