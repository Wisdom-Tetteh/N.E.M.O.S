package moos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.InputStream;
import java.net.InetAddress;

/**
 *
 * @author admin
 */
public class Troubleshoot_Ping
        extends Thread {

    private TroubleshootStart monitor_start;
    private String host;
    private boolean active_status;
    private boolean done_status;
    private int timeout = 2500;

    public Troubleshoot_Ping(TroubleshootStart win, String host) {
        this.monitor_start = win;
        this.host = host;
    }

    public void run() {
        runOS();
    }

    private void runJava() {
        this.active_status = true;
        int OK = 0;
        int Fail = 0;
        int total = 0;
        try {
            this.monitor_start.setPingStatus("Reaching Host...");
            InetAddress ia = InetAddress.getByName(this.host);
            this.monitor_start.setPingStatus("Pinging host : " + ia.getHostAddress().toString());
            while (this.active_status) {
                if (ia.isReachable(this.timeout)) {
                    OK++;
                } else {
                    Fail++;
                }
                total++;
                this.monitor_start.setPingStatus("Success=" + OK + ",Fail=" + Fail);
                try {
                    Thread.sleep(1000L);
                } catch (Exception localException1) {
                }
            }
            this.done_status = true;
        } catch (Exception e) {
            e.printStackTrace();
            this.monitor_start.setPingStatus(e.toString());
            this.active_status = false;
        }
    }

    private void runOS() {
        int OK = 0;
        int Fail = 0;
        int total = 0;
        this.active_status = true;
        try {
            this.monitor_start.setPingStatus("Resolving host...");
            InetAddress ia = InetAddress.getByName(this.host);
            String ip = ia.getHostAddress().toString();
            this.monitor_start.setPingStatus("Pinging host : " + ip);
            while (this.active_status) {
                Process p;

                if (isWindows()) {
                    p = Runtime.getRuntime().exec(new String[]{"ping", "-n", "1", ip});
                } else {
                    p = Runtime.getRuntime().exec(new String[]{"ping", "-c", "1", ip});
                }
                InputStream is = p.getInputStream();
                p.waitFor();
                StringBuilder sb = new StringBuilder();
                while (is.available() > 0) {
                    int len = is.available();
                    byte[] data = new byte[len];
                    int read = is.read(data);
                    sb.append(new String(data, 0, read));
                }
                String out = sb.toString();
                if (isWindows()) {
                    if ((out.indexOf("Request timed out") != -1) || (out.indexOf("Destination host unreachable") != -1)) {
                        Fail++;
                    } else {
                        OK++;
                    }
                } else if (out.indexOf("0 received") != -1) {
                    Fail++;
                } else {
                    OK++;
                }
                total++;
                this.monitor_start.setPingStatus("Successive Pings=" + OK + ",Failed Pings=" + Fail);
                try {
                    Thread.sleep(1000L);
                } catch (Exception localException1) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.monitor_start.setPingStatus(e.toString());
            this.active_status = false;
        }
        this.done_status = true;
    }

    private boolean isWindows() {
        return File.pathSeparatorChar == ';';
    }

    public void close() {
        this.active_status = false;
        while (!this.done_status) {
            try {
                Thread.sleep(100L);
            } catch (Exception localException) {
            }
        }
    }
}
