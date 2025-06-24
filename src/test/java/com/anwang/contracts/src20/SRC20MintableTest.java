package com.anwang.contracts.src20;

import com.anwang.src20.SRC20Mintable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SRC20MintableTest {

    private Web3j web3j;
    private SRC20Mintable src20;

    @BeforeEach
    public void init() {
        web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
        src20 = new SRC20Mintable(web3j, 6666666, "0x6b196c90d9aa6b37112b9e59a00fcdda2a254884");
    }

    @Test
    public void testDeploy() throws Exception {
        src20 = new SRC20Mintable(web3j, 6666666);
        String name = "LMB102";
        String symbol = "LMB102";
        BigInteger totalSupply = new BigInteger("10000000000000000000000000000"); // 1B
        List<String> ret = src20.deploy("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", name, symbol, totalSupply);
        System.out.println("contract address: " + ret.get(0));
        System.out.println("deploy txid: " + ret.get(1));
        Assertions.assertTrue(ret.get(0).length() != 0);
    }

    @Test
    public void testMint() throws Exception {
        String txid = src20.mint("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", new Address("0xAC110c0f70867F77D9d230e377043F52480A0B7d"), new BigInteger("10000000000000000000000000000"));
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testTotalSupply() throws Exception {
        BigInteger totalSupply = src20.totalSupply();
        System.out.println("totalSupply: " + totalSupply);
        Assertions.assertEquals(0, totalSupply.compareTo(new BigInteger("10000000000000000000000000000")));
    }

    @Test
    public void testSetLogo() throws Exception {
        byte[] logo = Files.readAllBytes(Paths.get("C:\\Users\\Administrator\\Desktop\\102.png"));
        String txid = src20.setLogo("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", logo);
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }
}
