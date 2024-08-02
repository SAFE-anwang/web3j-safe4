package com.anwang.utils;

import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;

import java.math.BigInteger;

public class Safe4Util {
    public static byte[] signMessage(byte[] message, BigInteger privateKey) {
        Sign.SignatureData signature = Sign.signPrefixedMessage(message, ECKeyPair.create(privateKey));
        byte[] retval = new byte[65];
        System.arraycopy(signature.getR(), 0, retval, 0, 32);
        System.arraycopy(signature.getS(), 0, retval, 32, 32);
        System.arraycopy(signature.getV(), 0, retval, 64, 1);
        return retval;
    }
}
