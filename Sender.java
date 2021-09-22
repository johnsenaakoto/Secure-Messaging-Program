package com.company;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Signature;

public class Sender {
    // Prints the message
    public static void printMessage(Message message) {
        System.out.println(message.name);
        System.out.println(message.content);
    }

    public static void printReceivedResult(Message results) {

    }


    // Sign Message name
    public static byte[] sign_message_name(Message message, PrivateKey pk) throws Exception {
        // Convert the message to bytes
        byte[] messsage_bytes = message.name.getBytes();
        // Sign the message
        Signature sg = Signature.getInstance("SHA1withDSA");
        sg.initSign(pk);
        sg.update(messsage_bytes);
        byte[] signature = sg.sign();
        System.out.println(signature);
        return signature;

    }

    // Sign message content - the Integer
    public static byte[] sign_message_content(Message message, PrivateKey pk) throws Exception {
        // Convert the message to bytes
//        byte[] messsage_bytes = message.content.toByte();
        byte[] message_bytes = BigInteger.valueOf(message.content).toByteArray();
        // Sign the message
        Signature sg = Signature.getInstance("SHA1withDSA");
        sg.initSign(pk);
        sg.update(message_bytes);
        byte[] signature = sg.sign();
        System.out.println(signature);
        return signature;

    }

    public static boolean verify(byte[] hashValue, Message message) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        // Convert message name to byte[]
//        byte[] msgBytes = message.name.getBytes();
        // Convert message content to byte[]
        byte[] msgBytes = BigInteger.valueOf(message.content).toByteArray();
        md.update(msgBytes);
        byte[] mdBytes = md.digest();
        if (MessageDigest.isEqual(hashValue, mdBytes))
            return true;
        else
            return false;
    }
}
