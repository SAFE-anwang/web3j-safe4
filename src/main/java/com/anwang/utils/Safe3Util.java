package com.anwang.utils;

import io.github.novacrypto.base58.Base58;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

public class Safe3Util {
    public static BigInteger getUncompressedPublicKey(BigInteger privateKey) {
        BigInteger publicKey = Sign.publicKeyFromPrivate(privateKey);
        return new BigInteger("04" + Numeric.toHexStringNoPrefixZeroPadded(publicKey, 128), 16);
    }

    public static BigInteger getCompressedPublicKey(BigInteger privateKey) {
        BigInteger publicKey = Sign.publicKeyFromPrivate(privateKey);
        String prefix = publicKey.testBit(0) ? "03" : "02";
        String xHex = Numeric.toHexStringNoPrefixZeroPadded(publicKey, 128).substring(0, 64);
        return new BigInteger(prefix + xHex, 16);
    }

    public static String getSafe3Addr(BigInteger publicKey) {
        return Base58.base58Encode(getKeyID(publicKey));
    }

    private static byte[] getKeyID(BigInteger publicKey) {
//        System.out.println("1. public key: " + Hex.toHexString(publicKey.toByteArray()));

        byte[] hash = Hash.sha256(publicKey.toByteArray());
//        System.out.println("2. SHA256 of 1: " + Hex.toHexString(hash));

        byte[] r = ripemd160(hash);
//        System.out.println("3. ripemd160 of 2: " + Hex.toHexString(r));

        byte[] temp = new byte[r.length + 1];
        temp[0] = 0x4c;
        System.arraycopy(r, 0, temp, 1, r.length);
//        System.out.println("4. Add network bytes to 3: " + Hex.toHexString(temp));

        hash = sha256(temp);
//        System.out.println("5. SHA256 of 4: " + Hex.toHexString(hash));
        hash = sha256(hash);
//        System.out.println("6. SHA256 of 5: " + Hex.toHexString(hash));

        byte[] keyID = new byte[temp.length + 4];
        System.arraycopy(temp, 0, keyID, 0, temp.length);
        System.arraycopy(hash, 0, keyID, temp.length, 4);
//        System.out.println("7. KeyID: " + Hex.toHexString(keyID));
        return keyID;
    }

    public static byte[] sha256(byte[] data) {
        return Hash.sha256(data);
    }

    public static byte[] ripemd160(byte[] data) {
        RIPEMD160Digest d = new RIPEMD160Digest();
        d.update(data, 0, data.length);
        byte[] r = new byte[d.getDigestSize()];
        d.doFinal(r, 0);
        return r;
    }

    public static String getSecretKey(BigInteger privateKey, Boolean isCompress) {
        byte[] data;
        int len = 33;
        if (isCompress) {
            len += 1;
        }
        data = new byte[len];
        data[0] = (byte) 0xCC;
        if(privateKey.toByteArray().length == 33) {
            System.arraycopy(privateKey.toByteArray(), 1, data, 1, privateKey.toByteArray().length - 1);
        } else {
            System.arraycopy(privateKey.toByteArray(), 0, data, 1, privateKey.toByteArray().length);
        }
        if (isCompress) {
            data[33] = 0x01;
        }
        byte[] hash = sha256(data);
        hash = sha256(hash);

        byte[] buf = new byte[data.length + 4];
        System.arraycopy(data, 0, buf, 0, data.length);
        System.arraycopy(hash, 0, buf, data.length, 4);
        return Base58.base58Encode(buf);
    }
}
