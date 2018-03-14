package com.djt.cbs.entity;

public class Test {
    static {
        try {
            System.load("/Users/benio/Documents/lib/libsoLib.so");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load.\n" + e);
            System.exit(1);
        }
    }

    public static void main(String argv[]) {

        System.out.println("");
    }
}
