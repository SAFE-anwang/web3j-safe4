package com.anwang.contracts.src20;

import com.anwang.src20.SRC20LockFactory;
import com.anwang.types.src20.LockRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SRC20LockFactoryTest {

    private Web3j web3j;
    private SRC20LockFactory factory;

    @BeforeEach
    public void init() {
        web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
        factory = new SRC20LockFactory(web3j, 6666666, "0x4f203092FB68732D8484c099a72dDc5a195f26f9"); // SRC20: ABCD, contract-addr: 0x6b3914aF517A56D47A9997397325B7F37414A49c, total: 10000, creator: 0x4c207825db1c46Dd836123E58ecaE85de7025879
    }

    @Test
    public void testLock() throws Exception {
        // 0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2: privateKey: 0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010
        String txid = factory.lock("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", new Address("0x6b3914aF517A56D47A9997397325B7F37414A49c"), new Address("0xa5CEc2B8CdA30dA3F3170b4505CB44226b6c9Dd2"), new BigInteger("100000000000000000"), BigInteger.ONE); // lock 0.1 ABCD to 0xa5CEc2B8CdA30dA3F3170b4505CB44226b6c9Dd2
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testBatchLock() throws Exception {
        String txid = factory.batchLock("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", new Address("0x6b3914aF517A56D47A9997397325B7F37414A49c"), new Address("0xa5CEc2B8CdA30dA3F3170b4505CB44226b6c9Dd2"), new BigInteger("900000000000000000"), BigInteger.valueOf(3), BigInteger.ONE, BigInteger.ZERO); // batch lock 0.9 ABC to 0xa5CEc2B8CdA30dA3F3170b4505CB44226b6c9Dd2
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testWithdrawByID() throws Exception {
        List<BigInteger> ids = new ArrayList<>();
        ids.add(BigInteger.ZERO);
        ids.add(BigInteger.valueOf(12));
        String txid = factory.withdrawByID("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", new Address("0x6b3914aF517A56D47A9997397325B7F37414A49c"), ids);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testGetTotalIDNum() throws Exception {
        BigInteger num = factory.getTotalIDNum(new Address("0x6b3914aF517A56D47A9997397325B7F37414A49c"), new Address("0xa5CEc2B8CdA30dA3F3170b4505CB44226b6c9Dd2"));
        System.out.println(num);
        Assertions.assertTrue(num.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetTotalIDs() throws Exception {
        List<BigInteger> ids = factory.getTotalIDs(new Address("0x6b3914aF517A56D47A9997397325B7F37414A49c"), new Address("0xa5CEc2B8CdA30dA3F3170b4505CB44226b6c9Dd2"), BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(ids.toString());
        Assertions.assertTrue(ids.size() > 0);
    }

    @Test
    public void testGetAvailableIDNum() throws Exception {
        BigInteger num = factory.getAvailableIDNum(new Address("0x6b3914aF517A56D47A9997397325B7F37414A49c"), new Address("0xa5CEc2B8CdA30dA3F3170b4505CB44226b6c9Dd2"));
        System.out.println(num);
        Assertions.assertTrue(num.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetAvailableIDs() throws Exception {
        List<BigInteger> ids = factory.getAvailableIDs(new Address("0x6b3914aF517A56D47A9997397325B7F37414A49c"), new Address("0xa5CEc2B8CdA30dA3F3170b4505CB44226b6c9Dd2"), BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(ids.toString());
        Assertions.assertTrue(ids.size() > 0);
    }

    @Test
    public void testGetLockedIDNum() throws Exception {
        BigInteger num = factory.getLockedIDNum(new Address("0x6b3914aF517A56D47A9997397325B7F37414A49c"), new Address("0xa5CEc2B8CdA30dA3F3170b4505CB44226b6c9Dd2"));
        System.out.println(num);
        Assertions.assertTrue(num.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetLockedIDs() throws Exception {
        List<BigInteger> ids = factory.getLockedIDs(new Address("0x6b3914aF517A56D47A9997397325B7F37414A49c"), new Address("0xa5CEc2B8CdA30dA3F3170b4505CB44226b6c9Dd2"), BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(ids.toString());
        Assertions.assertTrue(ids.size() > 0);
    }

    @Test
    public void testGetRecordByID() throws Exception {
        LockRecord record = factory.getRecordByID(new Address("0x6b3914aF517A56D47A9997397325B7F37414A49c"), BigInteger.valueOf(1));
        System.out.println(record.toString());
        Assertions.assertTrue(record.amount.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetLock() throws Exception {
        Address lockContract = factory.getLock(new Address("0x6b3914aF517A56D47A9997397325B7F37414A49c"));
        System.out.println(lockContract);
        Assertions.assertNotEquals(Address.DEFAULT.getValue(), lockContract.getValue());
    }

    @Test
    public void testToken() throws Exception {
        Address tokenContract = factory.getToken(new Address("0x729b4db4a04b35c82e7c577d7ac471264bccf955"));
        System.out.println(tokenContract);
        Assertions.assertNotEquals(Address.DEFAULT.getValue(), tokenContract.getValue());
    }
}
