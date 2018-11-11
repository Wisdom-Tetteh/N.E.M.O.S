package moos;

/*
 * @author admin
 */
import java.awt.EventQueue;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class Troubleshoot_Client
        extends Thread {

    private TroubleshootStart monitor_start;
    private boolean active;
    private Socket s;
    private InputStream is;
    private OutputStream os;
    private Timer timer;
    private TT task;
    private Reader reader;
    private Writer writer;
    private Latency latency;
    private long read;
    private Object readLock = new Object();
    private long written;
    private Object writtenLock = new Object();
    private String host;
    private int port;
    private char mode;

    public Troubleshoot_Client(TroubleshootStart win, String host, int port, char mode) {
        this.monitor_start = win;
        this.host = host;
        this.port = port;
        this.mode = mode;
    }

    @Override
    public void run() {
        try {
            this.monitor_start.setClientStatus("Connecting to server..." + this.host + ":" + this.port);
            this.s = new Socket(this.host, this.port);
            this.monitor_start.setClientStatus("Running...");
            this.is = this.s.getInputStream();
            this.os = this.s.getOutputStream();
            this.os.write(this.mode);
            this.active = true;
            switch (this.mode) {
                case 'F': {
                    this.reader = new Reader();
                    this.reader.start();
                    this.writer = new Writer();
                    this.writer.start();
                    break;
                }
                case 'S': {
                    this.writer = new Writer();
                    this.writer.start();
                    break;
                }
                case 'R': {
                    this.reader = new Reader();
                    this.reader.start();
                    break;
                }
                case 'L': {
                    this.latency = new Latency();
                    this.latency.start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.monitor_start.setClientStatus(e.toString());
        }
        if (this.mode != 'L') {
            this.timer = new Timer();
            this.task = new TT();
            this.timer.scheduleAtFixedRate(this.task, 1000, 1000);
        }
    }

    public void close() {
        this.active = false;
        if (this.timer != null) {
            this.timer.cancel();
            this.timer = null;
        }
        try {
            this.s.close();
        } catch (Exception exception) {
            // empty catch block
        }
    }

    private static void setuint32(byte[] data, int offset, int num) {
        data[offset + 0] = (byte) (num & 255);
        data[offset + 1] = (byte) ((num >>= 8) & 255);
        data[offset + 2] = (byte) ((num >>= 8) & 255);
        data[offset + 3] = (byte) ((num >>= 8) & 255);
    }

    private static int getuint32(byte[] data, int offset) {
        int ret = data[offset] & 255;
        ret += (data[offset + 1] & 255) << 8;
        ret += (data[offset + 2] & 255) << 16;
        return ret += (data[offset + 3] & 255) << 24;
    }

    private class Latency
            extends Thread {

        private Latency() {
        }

        @Override
        public void run() {
            byte[] data = new byte[4];
            int idx = 0;
            int ridx = 0;
            try {
                while (Troubleshoot_Client.this.active) {
                    Thread.sleep(5);
                    Troubleshoot_Client.setuint32(data, 0, idx);
                    long s1 = System.nanoTime();
                    Troubleshoot_Client.this.os.write(data);
                    int read = Troubleshoot_Client.this.is.read(data);
                    if (read == 4) {
                        ridx = Troubleshoot_Client.getuint32(data, 0);
                    }
                    if (ridx != idx) {
                        System.out.println("Error:Latency read back error");
                    }
                    long s2 = System.nanoTime();
                    long diff = s2 - s1;
                    Troubleshoot_Client.this.monitor_start.addLatency((int) (diff / 1000));
                    ++idx;
                }
            } catch (Exception read) {
                // empty catch block
            }
        }
    }

    private class Writer
            extends Thread {

        private Writer() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public void run() {
            byte[] data = new byte[1460];
            try {
                while (Troubleshoot_Client.this.active) {
                    Troubleshoot_Client.this.os.write(data);
                    Object object = Troubleshoot_Client.this.writtenLock;
                    synchronized (object) {
                        Troubleshoot_Client.this.written = Troubleshoot_Client.this.written + 1460;
                    }
                }
                return;
            } catch (Exception exception) {
                // empty catch block
            }
        }
    }

    private class Reader
            extends Thread {

        private Reader() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public void run() {
            byte[] data = new byte[1460];
            try {
                while (Troubleshoot_Client.this.active) {
                    int r = Troubleshoot_Client.this.is.read(data);
                    if (r == -1) {
                        return;
                    }
                    Object object = Troubleshoot_Client.this.readLock;
                    synchronized (object) {
                        Troubleshoot_Client.this.read = Troubleshoot_Client.this.read + r;
                    }
                }
                return;
            } catch (Exception r) {
                // empty catch block
            }
        }
    }

    private class TT
            extends TimerTask {

        private double mb;
        private double kb;

        private TT() {
            this.mb = 1048576.0;
            this.kb = 1024.0;
        }

        private String speedToString(double x) {
            if (x >= this.mb) {
                return String.format("%.3f", x / this.mb) + " MB/s";
            }
            if (x >= this.kb) {
                return String.format("%.3f", x / this.kb) + " KB/s";
            }
            return "" + x + " B/s";
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void run() {
            String ws;
            String rs;
            Object object;
            if (Troubleshoot_Client.this.mode == 'F' || Troubleshoot_Client.this.mode == 'R') {
                long r;
                object = Troubleshoot_Client.this.readLock;
                synchronized (object) {
                    r = Troubleshoot_Client.this.read;
                    Troubleshoot_Client.this.read = 0;
                }
                rs = this.speedToString(r);
            } else {
                rs = "n/a";
            }
            if (Troubleshoot_Client.this.mode == 'F' || Troubleshoot_Client.this.mode == 'S') {
                long w;
                object = Troubleshoot_Client.this.writtenLock;
                synchronized (object) {
                    w = Troubleshoot_Client.this.written;
                    Troubleshoot_Client.this.written = 0;
                }
                ws = this.speedToString(w);
            } else {
                ws = "n/a";
            }
            final String f_rs = rs;
            final String f_ws = ws;
            EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    Troubleshoot_Client.this.monitor_start.setReadSpeed(f_rs);
                    Troubleshoot_Client.this.monitor_start.setWriteSpeed(f_ws);
                }
            });
        }

    }

}
