package com.anwang.contracts;

import com.anwang.Safe4;
import com.anwang.types.sysproperty.PropertyInfo;
import com.anwang.types.sysproperty.UnconfirmedPropertyInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.List;

public class SysPropertyTest {

    private Web3j web3j;
    private Safe4 safe4;

    @BeforeEach
    public void init() {
        web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
        safe4 = new Safe4(web3j, 6666667);
    }

    @Test
    public void testApplyUpdate() throws Exception {
        // sn: 0x556b5868919008607ef24f4eb2bde6feda3e42f2, privateKey: 0x73fc4be2c4a1d44f41c09ef138717a7994726461c9c256f25b36c3f3b570d9dd
        String txid = safe4.sysproperty.applyUpdate("0x73fc4be2c4a1d44f41c09ef138717a7994726461c9c256f25b36c3f3b570d9dd", "block_space", BigInteger.valueOf(1), "this is a test");
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testVote4Update() throws Exception {
        // sn: 0x918b8ed234e235a2ea5756fd5d9c80850ba3bb6d, privateKey: 0xfb5ae9ce44b96a5cb71f405806ec90493f18641776b44e9f267bed17c45a7874
        // sn: 0xd57574369a6c90a5622f17fbed30c891b9d70c3b, privateKey: 0x5bd818c9dd4d05351e9c7ff830d9ee7d556181a6473c84363ff7edb1dfc7e34f
        String txid = safe4.sysproperty.vote4Update("0xfb5ae9ce44b96a5cb71f405806ec90493f18641776b44e9f267bed17c45a7874", "block_space", BigInteger.ONE);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testGetInfo() throws Exception {
        PropertyInfo info = safe4.sysproperty.getInfo("block_space");
        System.out.println(info.toString());
        Assertions.assertTrue(info.getName().length() > 0);
    }

    @Test
    public void testGetUnconfirmedInfo() throws Exception {
        UnconfirmedPropertyInfo info = safe4.sysproperty.getUnconfirmedInfo("block_space");
        System.out.println(info.toString());
        Assertions.assertTrue(info.getVoters().size() > 0);
    }

    @Test
    public void testGetValue() throws Exception {
        BigInteger value = safe4.sysproperty.getValue("block_space");
        System.out.println(value);
        Assertions.assertTrue(value.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetNum() throws Exception {
        BigInteger num = safe4.sysproperty.getNum();
        System.out.println(num);
        Assertions.assertTrue(num.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetAll() throws Exception {
        List<String> names = safe4.sysproperty.getAll(BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(names);
        Assertions.assertTrue(names.size() > 0);
    }

    @Test
    public void testGetUnconfirmedNum() throws Exception {
        BigInteger num = safe4.sysproperty.getUnconfirmedNum();
        System.out.println(num);
        Assertions.assertTrue(num.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetAllUnconfirmed() throws Exception {
        List<String> names = safe4.sysproperty.getAllUnconfirmed(BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(names);
        Assertions.assertTrue(names.size() > 0);
    }

    @Test
    public void testExist() throws Exception {
        Boolean flag = safe4.sysproperty.exist("block_space");
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testExistUnconfirmed() throws Exception {
        Boolean flag = safe4.sysproperty.existUnconfirmed("block_space");
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }
}
