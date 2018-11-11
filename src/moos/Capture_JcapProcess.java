package moos;

import javax.swing.SwingUtilities;

public abstract class Capture_JcapProcess {

    private Object object_value;
    private Thread thread;
    private ThreadVar thread_var;

    public abstract Object construct();

    private static class ThreadVar {

        private Thread threads;

        ThreadVar(Thread thread) {
            threads = thread;
        }

        synchronized Thread get() {
            return threads;
        }

        synchronized void clear() {
            threads = null;
        }
    }

    protected synchronized Object getValue() {
        return object_value;
    }

    private synchronized void setValue(Object x) {
        object_value = x;
    }

    public void finished() {
    }

    public void interrupt() {
        Thread thread = thread_var.get();
        if (thread != null) {
            thread.interrupt();
        }
        thread_var.clear();
    }

    public Object get() {
        while (true) {
            Thread thread = thread_var.get();
            if (thread == null) {
                return getValue();
            }
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
    }

    public Capture_JcapProcess() {
        final Runnable doFinished = new Runnable() {
            public void run() {
                finished();
            }
        };

        Runnable doConstruct = new Runnable() {
            public void run() {
                try {
                    setValue(construct());
                } finally {
                    thread_var.clear();
                }
                SwingUtilities.invokeLater(doFinished);
            }
        };
        Thread thread = new Thread(doConstruct);
        thread_var = new ThreadVar(thread);
    }

    public void start() {
        Thread thread = thread_var.get();
        if (thread != null) {
            thread.start();
        }
    }
}
