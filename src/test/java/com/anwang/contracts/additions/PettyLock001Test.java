package com.anwang.contracts.additions;

import com.anwang.Safe4;
import com.anwang.types.accountmanager.AccountAmountInfo;
import com.anwang.types.accountmanager.AccountRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PettyLock001Test {

    private Web3j web3j;
    private Safe4 safe4;

    @BeforeEach
    public void init() {
        web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
        safe4 = new Safe4(web3j, 6666666);
    }

    @Test
    public void testBatchDeposit4One() throws Exception {
        // 0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2: privateKey: 0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010
        String txid = safe4.pettyLock001.batchDeposit4One("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", new BigInteger("300000000000000000"), new Address("0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2"), BigInteger.valueOf(5), BigInteger.ONE, BigInteger.ZERO);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testWithdrawByID() throws Exception {
        List<BigInteger> ids = new ArrayList<>();
        ids.add(BigInteger.valueOf(975));
        ids.add(BigInteger.valueOf(976));
        ids.add(BigInteger.valueOf(977));
        ids.add(BigInteger.valueOf(978));
        ids.add(BigInteger.valueOf(979));
        String txid = safe4.pettyLock001.withdrawByID("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", ids);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testGetTotalAmount() throws Exception {
        AccountAmountInfo info = safe4.pettyLock001.getTotalAmount(new Address("0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2"));
        System.out.println(info.toString());
        Assertions.assertTrue(info.amount.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetTotalIDs() throws Exception {
        List<BigInteger> ids = safe4.pettyLock001.getTotalIDs(new Address("0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2"), BigInteger.ZERO, BigInteger.TEN);
        System.out.println(ids.toString());
        Assertions.assertTrue(ids.size() > 0);
    }

    @Test
    public void testGetAvailableAmount() throws Exception {
        AccountAmountInfo info = safe4.pettyLock001.getAvailableAmount(new Address("0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2"));
        System.out.println(info.toString());
        Assertions.assertTrue(info.amount.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetAvailableIDs() throws Exception {
        List<BigInteger> ids = safe4.pettyLock001.getAvailableIDs(new Address("0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2"), BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(ids.toString());
        Assertions.assertTrue(ids.size() > 0);
    }

    @Test
    public void testGetLockedAmount() throws Exception {
        AccountAmountInfo info = safe4.pettyLock001.getLockedAmount(new Address("0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2"));
        System.out.println(info.toString());
        Assertions.assertTrue(info.amount.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetLockedIDs() throws Exception {
        List<BigInteger> ids = safe4.pettyLock001.getLockedIDs(new Address("0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2"), BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(ids.toString());
        Assertions.assertTrue(ids.size() > 0);
    }

    @Test
    public void testGetRecordByID() throws Exception {
        AccountRecord record = safe4.pettyLock001.getRecordByID(BigInteger.valueOf(975));
        System.out.println(record.toString());
        Assertions.assertTrue(record.amount.compareTo(BigInteger.ZERO) > 0);
    }
}
