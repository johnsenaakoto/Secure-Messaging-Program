package com.company;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;

public class Receiver {

    public static boolean verify(byte[] message, PublicKey pubk, byte[] signature) throws Exception {
        Signature sg = Signature.getInstance("SHA1withDSA");
        sg.initVerify(pubk);
        sg.update(message);
        System.out.println(sg.verify(signature));
        return sg.verify(signature);
    }

    public static byte[] generate(Message message) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        // Convert message name to byte[]
//        byte[] message_digest = message.name.getBytes();
        // Convert message content to byte[]
        byte[] message_digest = BigInteger.valueOf(message.content).toByteArray();
        md.update(message_digest);
        byte[] mdbytes = md.digest();
        return(mdbytes);
    }

    public static Message generateResponse() throws Exception {
        Message response = MBRC.receive();
        if (response.name == "add") {
            response.content = AddCalculation.add(response.content);
        }
        else if (response.name == "multiply") {
            response.content = MultiplyCalculation.multiply(response.content);
        }
        return response;
    }
}
