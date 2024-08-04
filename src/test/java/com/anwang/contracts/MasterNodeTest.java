package com.anwang.contracts;

import com.anwang.Safe4;
import com.anwang.types.masternode.MasterNodeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.List;

public class MasterNodeTest {

    private Web3j web3j;
    private Safe4 safe4;

    @BeforeEach
    public void init() {
        web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
        safe4 = new Safe4(web3j, 6666666);
    }

    @Test
    public void testRegister() throws Exception {
        // mn: 0x69a6d725f772e44f11bd6d21ec5a92fdc7eab652, privateKey: 0x40f93c4fb6ea8bcbc5e88c3c213bc86c72e68f5404ef047d6ac3b3e3db2dd816
        BigInteger value = new BigInteger("200000000000000000000");
        Address addr = new Address("0x69a6d725f772e44f11bd6d21ec5a92fdc7eab652");
        BigInteger lockDay = BigInteger.valueOf(720);
        String name = "masternode-3";
        String enode = "enode://f687439863fce1ff70dc40bec0fe5ea1ad0833a2672c29590b0aa9001e1488013e42e8adc96a6a9312ed1426d6bea47026d057df57a5856970b207afac771f09@10.0.0.173:30303";
        String description = "masternode3 for test";
        String txid = safe4.masternode.register("0x40f93c4fb6ea8bcbc5e88c3c213bc86c72e68f5404ef047d6ac3b3e3db2dd816", value, true, addr, lockDay, enode, description, BigInteger.valueOf(50), BigInteger.valueOf(50));
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testAppendRegister() throws Exception {
        // mn: 0x69a6d725f772e44f11bd6d21ec5a92fdc7eab652, privateKey: 0x40f93c4fb6ea8bcbc5e88c3c213bc86c72e68f5404ef047d6ac3b3e3db2dd816
        BigInteger value = new BigInteger("800000000000000000000");
        Address addr = new Address("0x69a6d725f772e44f11bd6d21ec5a92fdc7eab652");
        BigInteger lockDay = BigInteger.valueOf(360);
        String txid = safe4.masternode.appendRegister("0x40f93c4fb6ea8bcbc5e88c3c213bc86c72e68f5404ef047d6ac3b3e3db2dd816", value, addr, lockDay);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testTurnRegister() throws Exception {
        // mn: 0x69a6d725f772e44f11bd6d21ec5a92fdc7eab652, privateKey: 0x40f93c4fb6ea8bcbc5e88c3c213bc86c72e68f5404ef047d6ac3b3e3db2dd816
        Address addr = new Address("0x69a6d725f772e44f11bd6d21ec5a92fdc7eab652");
        String txid = safe4.masternode.turnRegister("0x40f93c4fb6ea8bcbc5e88c3c213bc86c72e68f5404ef047d6ac3b3e3db2dd816", addr, BigInteger.valueOf(15));
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testChangeAddress() throws Exception {
        // mn: 0x69a6d725f772e44f11bd6d21ec5a92fdc7eab652, privateKey: 0x40f93c4fb6ea8bcbc5e88c3c213bc86c72e68f5404ef047d6ac3b3e3db2dd816
        Address addr = new Address("0x69a6d725f772e44f11bd6d21ec5a92fdc7eab652");
        Address newAddr = new Address("0xd52114c4071b5bfbd06a657a3db538bfd559a481");
        String txid = safe4.masternode.changeAddress("0x40f93c4fb6ea8bcbc5e88c3c213bc86c72e68f5404ef047d6ac3b3e3db2dd816", addr, newAddr);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testChangeEnode() throws Exception {
        Address addr = new Address("0xd52114c4071b5bfbd06a657a3db538bfd559a481");
        String enode = "enode://f687439863fce1ff70dc40bec0fe5ea1ad0833a2672c29590b0aa9001e1488013e42e8adc96a6a9312ed1426d6bea47026d057df57a5856970b207afac771f09@10.0.0.3:30303";
        String txid = safe4.masternode.changeEnode("0x40f93c4fb6ea8bcbc5e88c3c213bc86c72e68f5404ef047d6ac3b3e3db2dd816", addr, enode);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testChangeDescription() throws Exception {
        Address addr = new Address("0xd52114c4071b5bfbd06a657a3db538bfd559a481");
        String txid = safe4.masternode.changeDescription("0x40f93c4fb6ea8bcbc5e88c3c213bc86c72e68f5404ef047d6ac3b3e3db2dd816", addr, "mn-3 for test");
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testGetInfo() throws Exception {
        MasterNodeInfo info = safe4.masternode.getInfo(new Address("0xd52114c4071b5bfbd06a657a3db538bfd559a481"));
        System.out.println(info.toString());
        Assertions.assertTrue(info.id.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetInfoByID() throws Exception {
        MasterNodeInfo info = safe4.masternode.getInfoByID(BigInteger.valueOf(3));
        System.out.println(info.toString());
        Assertions.assertTrue(info.id.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetNext() throws Exception {
        Address addr = safe4.masternode.getNext();
        System.out.println(addr);
        Assertions.assertTrue(addr.toString().length() > 0);
    }

    @Test
    public void testGetNum() throws Exception {
        BigInteger num = safe4.masternode.getNum();
        System.out.println(num);
        Assertions.assertTrue(num.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Address> addrs = safe4.masternode.getAll(BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(addrs);
        Assertions.assertTrue(addrs.size() > 0);
    }

    @Test
    public void testGetAddrNum4Creator() throws Exception {
        Address creator = new Address("0x69a6d725f772e44f11bd6d21ec5a92fdc7eab652");
        BigInteger num = safe4.masternode.getAddrNum4Creator(creator);
        System.out.println(num);
        Assertions.assertTrue(num.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetAddrs4Creator() throws Exception {
        Address creator = new Address("0x69a6d725f772e44f11bd6d21ec5a92fdc7eab652");
        List<Address> addrs = safe4.masternode.getAddrs4Creator(creator, BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(addrs);
        Assertions.assertTrue(addrs.size() > 0);
    }

    @Test
    public void testGetOfficials() throws Exception {
        List<Address> addrs = safe4.masternode.getOfficials();
        System.out.println(addrs);
        Assertions.assertTrue(addrs.size() > 0);
    }

    @Test
    public void testExist() throws Exception {
        Boolean flag = safe4.masternode.exist(new Address("0xd52114c4071b5bfbd06a657a3db538bfd559a481"));
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testExistID() throws Exception {
        Boolean flag = safe4.masternode.existID(BigInteger.ONE);
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testExistEnode() throws Exception {
        String enode = "enode://f687439863fce1ff70dc40bec0fe5ea1ad0833a2672c29590b0aa9001e1488013e42e8adc96a6a9312ed1426d6bea47026d057df57a5856970b207afac771f09@10.0.0.3:30303";
        Boolean flag = safe4.masternode.existEnode(enode);
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testExistLockID() throws Exception {
        Address addr = new Address("0xd52114c4071b5bfbd06a657a3db538bfd559a481");
        BigInteger lockID = BigInteger.valueOf(15);
        Boolean flag = safe4.masternode.existLockID(addr, lockID);
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testIsValid() throws Exception {
        Address addr = new Address("0xd52114c4071b5bfbd06a657a3db538bfd559a481");
        Boolean flag = safe4.masternode.isValid(addr);
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }
}
