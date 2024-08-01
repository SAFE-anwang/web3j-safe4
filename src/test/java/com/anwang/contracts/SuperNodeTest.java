package com.anwang.contracts;

import com.anwang.Safe4;
import com.anwang.types.supernode.SuperNodeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.List;

public class SuperNodeTest {

    private Web3j web3j;
    private Safe4 safe4;

    @BeforeEach
    public void init() {
        web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
        safe4 = new Safe4(web3j, 6666667);
    }

    @Test
    public void testRegister() throws Exception {
        // sn: 0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2, privateKey: 0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010
        BigInteger value = new BigInteger("5000000000000000000000");
        Address addr = new Address("0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2");
        BigInteger lockDay = BigInteger.valueOf(720);
        String name = "supernode-5";
        String enode = "enode://f687439863fce1ff70dc40bec0fe5ea1ad0833a2672c29590b0aa9001e1488013e42e8adc96a6a9312ed1426d6bea47026d057df57a5856970b207afac771f09@10.0.0.165:30303";
        String description = "supernode5 for test";
        String txid = safe4.supernode.register("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", value, false, addr, lockDay, name, enode, description, BigInteger.TEN, BigInteger.valueOf(50), BigInteger.valueOf(40));
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testAppendRegister() throws Exception {
        // sn: 0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2, privateKey: 0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010
        BigInteger value = new BigInteger("500000000000000000000");
        Address addr = new Address("0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2");
        BigInteger lockDay = BigInteger.valueOf(360);
        String txid = safe4.supernode.appendRegister("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", value, addr, lockDay);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testTurnRegister() throws Exception {
        // sn: 0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2, privateKey: 0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010
        Address addr = new Address("0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2");
        String txid = safe4.supernode.turnRegister("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", addr, BigInteger.valueOf(13));
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testChangeAddress() throws Exception {
        // sn: 0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2, privateKey: 0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010
        Address addr = new Address("0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2");
        Address newAddr = new Address("0xd6ebea69f2d81b9ca259c0b6ed3d9ad6aa206ef1");
        String txid = safe4.supernode.changeAddress("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", addr, newAddr);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testChangeName() throws Exception {
        Address addr = new Address("0xd6ebea69f2d81b9ca259c0b6ed3d9ad6aa206ef1");
        String txid = safe4.supernode.changeName("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", addr, "sn-5");
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testChangeEnode() throws Exception {
        Address addr = new Address("0xd6ebea69f2d81b9ca259c0b6ed3d9ad6aa206ef1");
        String enode = "enode://f687439863fce1ff70dc40bec0fe5ea1ad0833a2672c29590b0aa9001e1488013e42e8adc96a6a9312ed1426d6bea47026d057df57a5856970b207afac771f09@10.0.0.5:30303";
        String txid = safe4.supernode.changeEnode("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", addr, enode);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testChangeDescription() throws Exception {
        Address addr = new Address("0xd6ebea69f2d81b9ca259c0b6ed3d9ad6aa206ef1");
        String txid = safe4.supernode.changeDescription("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", addr, "sn-5 for test");
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testGetInfo() throws Exception {
        SuperNodeInfo info = safe4.supernode.getInfo(new Address("0xd6ebea69f2d81b9ca259c0b6ed3d9ad6aa206ef1"));
        System.out.println(info.toString());
        Assertions.assertTrue(info.name.length() > 0);
    }

    @Test
    public void testGetInfoByID() throws Exception {
        SuperNodeInfo info = safe4.supernode.getInfoByID(BigInteger.valueOf(5));
        System.out.println(info.toString());
        Assertions.assertTrue(info.name.length() > 0);
    }

    @Test
    public void testGetNum() throws Exception {
        BigInteger num = safe4.supernode.getNum();
        System.out.println(num);
        Assertions.assertTrue(num.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Address> addrs = safe4.supernode.getAll(BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(addrs);
        Assertions.assertTrue(addrs.size() > 0);
    }

    @Test
    public void testGetAddrNum4Creator() throws Exception {
        Address creator = new Address("0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2");
        BigInteger num = safe4.supernode.getAddrNum4Creator(creator);
        System.out.println(num);
        Assertions.assertTrue(num.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetAddrs4Creator() throws Exception {
        Address creator = new Address("0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2");
        List<Address> addrs = safe4.supernode.getAddrs4Creator(creator, BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(addrs);
        Assertions.assertTrue(addrs.size() > 0);
    }

    @Test
    public void testGetTops() throws Exception {
        List<Address> addrs = safe4.supernode.getTops();
        System.out.println(addrs);
        Assertions.assertTrue(addrs.size() > 0);
    }

    @Test
    public void testGetOfficials() throws Exception {
        List<Address> addrs = safe4.supernode.getOfficials();
        System.out.println(addrs);
        Assertions.assertTrue(addrs.size() > 0);
    }

    @Test
    public void testExist() throws Exception {
        Boolean flag = safe4.supernode.exist(new Address("0xd6ebea69f2d81b9ca259c0b6ed3d9ad6aa206ef1"));
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testExistID() throws Exception {
        Boolean flag = safe4.supernode.existID(BigInteger.ONE);
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testExistName() throws Exception {
        Boolean flag = safe4.supernode.existName("sn-5");
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testExistEnode() throws Exception {
        String enode = "enode://f687439863fce1ff70dc40bec0fe5ea1ad0833a2672c29590b0aa9001e1488013e42e8adc96a6a9312ed1426d6bea47026d057df57a5856970b207afac771f09@10.0.0.5:30303";
        Boolean flag = safe4.supernode.existEnode(enode);
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testExistLockID() throws Exception {
        Address addr = new Address("0xd6ebea69f2d81b9ca259c0b6ed3d9ad6aa206ef1");
        BigInteger lockID = BigInteger.valueOf(14);
        Boolean flag = safe4.supernode.existLockID(addr, lockID);
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testIsValid() throws Exception {
        Address addr = new Address("0xd6ebea69f2d81b9ca259c0b6ed3d9ad6aa206ef1");
        Boolean flag = safe4.supernode.isValid(addr);
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testIsFormal() throws Exception {
        Address addr = new Address("0x4d244c17d2a957f4a150408f8f4d19382ae8e2c8");
        Boolean flag = safe4.supernode.isFormal(addr);
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }
}
