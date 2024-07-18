package com.anwang.contracts;

import com.anwang.Safe4;
import com.anwang.types.accountmanager.AccountAmountInfo;
import com.anwang.types.accountmanager.AccountRecord;
import com.anwang.types.accountmanager.RecordUseInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class AccountManagerTest {

    private Web3j web3j;
    private Safe4 safe4;

    @BeforeEach
    public void init() {
        web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
        safe4 = new Safe4(web3j, 6666667);
    }

    @Test
    public void testDeposit() throws Exception {
        // 0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2: privateKey: 0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010
        String txid = safe4.account.deposit("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", new BigInteger("1000000000000000000"), new Address("0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2"), BigInteger.ZERO);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testBatchDeposit4One() throws Exception {
        // 0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2: privateKey: 0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010
        String txid = safe4.account.batchDeposit4One("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", new BigInteger("2000000000000000000"), new Address("0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2"), BigInteger.valueOf(3), BigInteger.ONE, BigInteger.ONE);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testBatchDeposit4Multi() throws Exception {
        // 0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2: privateKey: 0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010
        List<Address> addrs = new ArrayList<>();
        addrs.add(new Address("0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2"));
        addrs.add(new Address("0x64ae0d18085d0c3ec202a208e96bc2fc24e4a7e8"));
        String txid = safe4.account.batchDeposit4Multi("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", new BigInteger("2000000000000000000"), addrs, BigInteger.valueOf(2), BigInteger.ONE, BigInteger.ONE);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testWithdraw() throws Exception {
        String txid = safe4.account.withdraw("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010");
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testWithdrawByID() throws Exception {
        List<BigInteger> ids = new ArrayList<>();
        ids.add(BigInteger.ZERO);
        ids.add(BigInteger.valueOf(12));
        String txid = safe4.account.withdrawByID("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", ids);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testTransfer() throws Exception {
        String txid = safe4.account.transfer("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", new Address("0x64ae0d18085d0c3ec202a208e96bc2fc24e4a7e8"), new BigInteger("100000000000000000"), BigInteger.ONE);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testAddLockDay() throws Exception {
        String txid = safe4.account.addLockDay("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", BigInteger.valueOf(12), BigInteger.ONE);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testGetTotalAmount() throws Exception {
        AccountAmountInfo info = safe4.account.getTotalAmount(new Address("0x64ae0d18085d0c3ec202a208e96bc2fc24e4a7e8"));
        System.out.println(info.toString());
        Assertions.assertTrue(info.getAmount().compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetTotalIDs() throws Exception {
        List<BigInteger> ids = safe4.account.getTotalIDs(new Address("0x64ae0d18085d0c3ec202a208e96bc2fc24e4a7e8"), BigInteger.ZERO, BigInteger.TEN);
        System.out.println(ids.toString());
        Assertions.assertTrue(ids.size() > 0);
    }

    @Test
    public void testGetAvailableAmount() throws Exception {
        AccountAmountInfo info = safe4.account.getAvailableAmount(new Address("0x64ae0d18085d0c3ec202a208e96bc2fc24e4a7e8"));
        System.out.println(info.toString());
        Assertions.assertTrue(info.getAmount().compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetAvailableIDs() throws Exception {
        List<BigInteger> ids = safe4.account.getAvailableIDs(new Address("0x64ae0d18085d0c3ec202a208e96bc2fc24e4a7e8"), BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(ids.toString());
        Assertions.assertTrue(ids.size() > 0);
    }

    @Test
    public void testGetLockedAmount() throws Exception {
        AccountAmountInfo info = safe4.account.getLockedAmount(new Address("0x64ae0d18085d0c3ec202a208e96bc2fc24e4a7e8"));
        System.out.println(info.toString());
        Assertions.assertTrue(info.getAmount().compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetLockedIDs() throws Exception {
        List<BigInteger> ids = safe4.account.getLockedIDs(new Address("0x64ae0d18085d0c3ec202a208e96bc2fc24e4a7e8"), BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(ids.toString());
        Assertions.assertTrue(ids.size() > 0);
    }

    @Test
    public void testGetUsedAmount() throws Exception {
        AccountAmountInfo info = safe4.account.getUsedAmount(new Address("0x64ae0d18085d0c3ec202a208e96bc2fc24e4a7e8"));
        System.out.println(info.toString());
        Assertions.assertTrue(info.getAmount().compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetUsedIDs() throws Exception {
        List<BigInteger> ids = safe4.account.getUsedIDs(new Address("0x64ae0d18085d0c3ec202a208e96bc2fc24e4a7e8"), BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(ids.toString());
        Assertions.assertTrue(ids.size() > 0);
    }

    @Test
    public void testGetRecord0() throws Exception {
        AccountRecord record = safe4.account.getRecord0(new Address("0x64ae0d18085d0c3ec202a208e96bc2fc24e4a7e8"));
        System.out.println(record.toString());
        Assertions.assertTrue(record.getAmount().compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetRecordByID() throws Exception {
        AccountRecord record = safe4.account.getRecordByID(BigInteger.valueOf(1));
        System.out.println(record.toString());
        Assertions.assertTrue(record.getAmount().compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetRecordUseInfo() throws Exception {
        RecordUseInfo info = safe4.account.getRecordUseInfo(BigInteger.valueOf(1));
        System.out.println(info.toString());
        Assertions.assertTrue(info.getUnfreezeHeight().compareTo(BigInteger.ZERO) > 0);
    }
}
