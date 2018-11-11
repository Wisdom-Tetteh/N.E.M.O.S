package moos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Random;

/**
 *
 * @author admin
 */
public class Troubleshoot_DNS
        extends Thread {

    private TroubleshootStart monitor_start;
    private DatagramSocket datagram_socket;
    private static int maxmtu = 512;
    private String server;
    private String query;
    private int type_dns;
    private short id;
    private static final int QR = 32768;
    private static final int OPCODE_QUERY = 0;
    private static final int OPCODE_IQUERY = 16384;
    private static final int AA = 1024;
    private static final int TC = 512;
    private static final int RD = 256;
    private static final int RA = 128;
    private static final int Z = 64;
    private static final int AD = 32;
    private static final int CD = 16;
    private static final int A = 1;
    private static final int CNAME = 5;
    private static final int MX = 15;
    private static final int AAAA = 28;
    private byte[] data;
    private int Data_offset;
    private ByteBuffer buffer;
    private int Name_length;

    public Troubleshoot_DNS(TroubleshootStart window, String server, String query, String type) {
        this.monitor_start = window;
        this.server = server;
        this.query = query;
        this.type_dns = decodeType(type);
    }

    @Override
    public void run() {
        runDNS();
    }

    private void runJava() {
        try {
            this.monitor_start.setDNSStatus("Resolving host...");
            InetAddress ia = InetAddress.getByName(this.query);
            this.monitor_start.setDNSIP(ia.getHostAddress().toString());
        } catch (Exception e) {
            e.printStackTrace();
            this.monitor_start.setDNSStatus(e.toString());
        }
    }

    private void runDNS() {
        try {
            this.datagram_socket = new DatagramSocket();
            this.id = ((short) (new Random().nextInt() & 0x7FFF));

            buildRequest(this.query, this.type_dns, this.id);

            DatagramPacket out = new DatagramPacket(this.data, this.Data_offset);
            out.setAddress(InetAddress.getByName(this.server));
            out.setPort(53);
            this.datagram_socket.send(out);

            this.data = new byte[maxmtu];
            DatagramPacket in = new DatagramPacket(this.data, this.data.length);
            this.datagram_socket.setSoTimeout(2500);
            this.datagram_socket.receive(in);

            this.datagram_socket.close();
            this.datagram_socket = null;

            processReply(in.getData(), in.getLength());
        } catch (Exception e) {
            e.printStackTrace();
            this.monitor_start.setDNSStatus(e.toString());
        }
        if (this.datagram_socket != null) {
            this.datagram_socket.close();
            this.datagram_socket = null;
        }
    }

    public void close() {
        if (this.datagram_socket != null) {
            this.datagram_socket.close();
            this.datagram_socket = null;
        }
    }

    private String getName(byte[] data, int offset) {
        StringBuilder name = new StringBuilder();
        boolean jump = false;
        this.Name_length = 0;
        for (;;) {
            if (!jump) {
                this.Name_length += 1;
            }
            int length = data[(offset++)] & 0xFF;
            if (length == 0) {
                break;
            }
            if (length >= 192) {
                if (!jump) {
                    this.Name_length += 1;
                }
                jump = true;
                int newOffset = (length & 0x3F) << 8;
                newOffset += (data[offset] & 0xFF);
                offset = newOffset;
            } else {
                if (!jump) {
                    this.Name_length += length;
                }
                if (name.length() != 0) {
                    name.append(".");
                }
                name.append(new String(data, offset, length));
                offset += length;
            }
        }
        return name.toString();
    }

    private int encodeName(String domain) {
        String[] p = domain.split("[.]");
        int length = 0;
        for (int a = 0; a < p.length; a++) {
            int strlen = p[a].length();
            this.data[(this.Data_offset++)] = ((byte) strlen);
            length++;
            System.arraycopy(p[a].getBytes(), 0, this.data, this.Data_offset, strlen);
            this.Data_offset += strlen;
            length += strlen;
        }
        this.data[(this.Data_offset++)] = 0;
        length++;
        return length;
    }

    private void buildRequest(String query, int type, int id) {
        this.data = new byte[maxmtu];
        this.buffer = ByteBuffer.wrap(this.data);
        this.buffer.order(ByteOrder.BIG_ENDIAN);
        this.Data_offset = 0;
        putShort((short) id);
        putShort((short) 256);
        putShort((short) 1);
        putShort((short) 0);
        putShort((short) 0);
        putShort((short) 0);
        encodeName(query);
        putShort((short) type);
        putShort((short) 1);
        switch (type) {
            case 1:
                encodeName(query);
                putShort((short) type);
                putShort((short) 1);
                break;
            case 5:
                encodeName(query);
                putShort((short) type);
                putShort((short) 1);
                break;
            case 15:
                encodeName(query);
                putShort((short) type);
                putShort((short) 1);
                break;
            case 28:
                encodeName(query);
                putShort((short) type);
                putShort((short) 1);
        }
    }

    private int decodeType(String type) {
        if (type.equals("A")) {
            return 1;
        }
        if (type.equals("AAAA")) {
            return 28;
        }
        if (type.equals("MX")) {
            return 15;
        }
        return -1;
    }

    private String typeToString(int type) {
        switch (type) {
            case 15:
                return "MX";
            case 1:
                return "A";
            case 28:
                return "AAAA";
            case 5:
                return "CNAME";
        }
        return "?" + type;
    }

    private String decodeData(byte[] data, int offset, int type) {
        StringBuilder sb = new StringBuilder();
        switch (type) {
            case 1:
                sb.append("IP4=");
                for (int a = 0; a < 4; a++) {
                    if (a > 0) {
                        sb.append('.');
                    }
                    sb.append(Integer.toString(data[(offset + a)] & 0xFF));
                }
                break;
            case 5:
                sb.append("CNAME=");
                sb.append(getName(data, offset));
                break;
            case 15:
                sb.append("MX=");
                int pref = data[(offset++)] & 0xFF;
                pref <<= 8;
                pref += (data[(offset++)] & 0xFF);
                sb.append(getName(data, offset));
                sb.append(":pref=" + pref);
                break;
            case 28:
                sb.append("IP6=");
                for (int a = 0; a < 8; a++) {
                    if (a > 0) {
                        sb.append(':');
                    }
                    int o = data[(offset + a * 2)] & 0xFF;
                    o <<= 8;
                    o += (data[(offset + a * 2 + 1)] & 0xFF);
                    sb.append(String.format("%04x", new Object[]{Integer.valueOf(o)}));
                }
                break;
            default:
                sb.append("unsupported type:" + type);
        }
        return sb.toString();
    }

    private void processReply(byte[] data, int dataLength)
            throws Exception {
        ByteBuffer bb = ByteBuffer.wrap(data);
        bb.order(ByteOrder.BIG_ENDIAN);
        short id = bb.getShort(0);
        if (id != this.id) {
            throw new Exception("id does not match:" + Integer.toString(id, 16) + "!=" + Integer.toString(this.id, 16));
        }
        short flgs = bb.getShort(2);
        if ((flgs & 0x8000) == 0) {
            throw new Exception("not a response");
        }
        short cntQ = bb.getShort(4);
        if (cntQ != 1) {
            throw new Exception("only 1 question supported");
        }
        short cntA = bb.getShort(6);

        short cntS = bb.getShort(8);

        short cntAdd = bb.getShort(10);

        int offset = 12;
        StringBuilder sb = new StringBuilder();
        for (int a = 0; a < cntQ; a++) {
            String query = getName(data, offset);
            offset += this.Name_length;
            int type = bb.getShort(offset);
            offset += 2;
            int cls = bb.getShort(offset);
            if (cls != 1) {
                throw new Exception("Only internet class supported:" + cls);
            }
            offset += 2;
            sb.append("Query:" + query + ":" + typeToString(type) + "\n");
        }
        for (int a = 0; a < cntA; a++) {
            String query = getName(data, offset);
            offset += this.Name_length;
            int type = bb.getShort(offset);
            offset += 2;
            int cls = bb.getShort(offset);
            if (cls != 1) {
                throw new Exception("Only internet class supported:" + cls);
            }
            offset += 2;
            int ttl = bb.getInt(offset);
            offset += 4;
            short rdataLength = bb.getShort(offset);
            offset += 2;
            sb.append("Anwser:" + query + ":" + typeToString(type) + ":TTL=" + ttl + ":" + decodeData(data, offset, type) + "\n");
            offset += rdataLength;
        }
        for (int a = 0; a < cntS; a++) {
            String query = getName(data, offset);
            offset += this.Name_length;
            int type = bb.getShort(offset);
            offset += 2;
            int cls = bb.getShort(offset);
            if (cls != 1) {
                throw new Exception("Only internet class supported:" + cls);
            }
            offset += 2;
            int ttl = bb.getInt(offset);
            offset += 4;
            short rdataLength = bb.getShort(offset);
            offset += 2;
            sb.append("AuthServer:" + query + ":" + typeToString(type) + ":TTL=" + ttl + ":" + decodeData(data, offset, type) + "\n");
            offset += rdataLength;
        }
        for (int a = 0; a < cntAdd; a++) {
            String query = getName(data, offset);
            offset += this.Name_length;
            int type = bb.getShort(offset);
            offset += 2;
            int cls = bb.getShort(offset);
            if (cls != 1) {
                throw new Exception("Only internet class supported:" + cls);
            }
            offset += 2;
            int ttl = bb.getInt(offset);
            offset += 4;
            short rdataLength = bb.getShort(offset);
            offset += 2;
            sb.append("Additional:" + query + ":" + typeToString(type) + ":TTL=" + ttl + ":" + decodeData(data, offset, type) + "\n");
            offset += rdataLength;
        }
        this.monitor_start.setDNSIP(sb.toString());
    }

    private void putIP4(String ip) {
        String[] p = ip.split("[.]");
        for (int a = 0; a < 4; a++) {
            this.data[(this.Data_offset++)] = ((byte) Integer.valueOf(p[a]).intValue());
        }
    }

    private void putIP6(String ip) {
        String[] p = ip.split(":");
        for (int a = 0; a < 8; a++) {
            putShort((short) Integer.valueOf(p[a], 16).intValue());
        }
    }

    private void putShort(short value) {
        this.buffer.putShort(this.Data_offset, value);
        this.Data_offset += 2;
    }

    private void putInt(int value) {
        this.buffer.putInt(this.Data_offset, value);
        this.Data_offset += 4;
    }
}
