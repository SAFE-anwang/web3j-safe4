package com.anwang.contracts;

import com.anwang.Safe4;
import com.anwang.types.safe3.AvailableSafe3Info;
import com.anwang.types.safe3.LockedSafe3Info;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class Safe3Test {

    private Web3j web3j;
    private Safe4 safe4;

    @BeforeEach
    public void init() {
        web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
        safe4 = new Safe4(web3j, 6666666);
    }

    @Test
    public void testRedeemSafe() throws Exception {
        // compressed-addr: Xy3pSqGgGHfS6suRbUFk2mYaBD8oTYApAZ, privateKey: 0x18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725
        // uncompressed-addr: XanKmaz3PS6CCMFyh4o9BLM5bWyowyrnGR, privateKey: 0x18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725
        Map<String, List<String>> txids = safe4.safe3.redeemSafe3("0x18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725");
        System.out.println(txids);
        Assertions.assertTrue(txids.size() > 0);
    }

    @Test
    public void testRedeemMasterNode() throws Exception {
        // compressed-addr: Xy3pSqGgGHfS6suRbUFk2mYaBD8oTYApAZ, privateKey: 0x18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725
        // uncompressed-addr: XanKmaz3PS6CCMFyh4o9BLM5bWyowyrnGR, privateKey: 0x18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725
        List<String> txids = safe4.safe3.redeemMasterNode("0x18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725", "enode://NodeInfo@127.0.0.1:8545");
        System.out.println(txids);
        Assertions.assertTrue(txids.size() > 0);
    }

    @Test
    public void testGetAllAvailableNum() throws Exception {
        BigInteger num = safe4.safe3.getAllAvailableNum();
        System.out.println(num);
        Assertions.assertTrue(num.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetAvailableInfos() throws Exception {
        List<AvailableSafe3Info> infos = safe4.safe3.getAvailableInfos(BigInteger.ZERO, BigInteger.TEN);
        System.out.println(infos);
        Assertions.assertTrue(infos.size() > 0);
    }

    @Test
    public void testGetAvailableInfo() throws Exception {
        AvailableSafe3Info info = safe4.safe3.getAvailableInfo("XuPmDoaNb6rbNywefkTbESHXiYqNpYvaPU");
        System.out.println(info);
        Assertions.assertTrue(info.safe3Addr.length() > 0);
    }

    @Test
    public void testGetAllLockedNum() throws Exception {
        BigInteger num = safe4.safe3.getAllLockedNum();
        System.out.println(num);
        Assertions.assertTrue(num.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetLockedAddrNum() throws Exception {
        BigInteger num = safe4.safe3.getLockedAddrNum();
        System.out.println(num);
        Assertions.assertTrue(num.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetLockedAddrs() throws Exception {
        List<String> safe3Addrs = safe4.safe3.getLockedAddrs(BigInteger.ZERO, BigInteger.TEN);
        System.out.println(safe3Addrs);
        Assertions.assertTrue(safe3Addrs.size() > 0);
    }

    @Test
    public void testGetLockedNum() throws Exception {
        BigInteger num = safe4.safe3.getLockedNum("XuP9HFUwY37bAQ2cVsVv3T2BFBine6CbT9");
        System.out.println(num);
        Assertions.assertTrue(num.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetLockedInfo() throws Exception {
        List<LockedSafe3Info> infos = safe4.safe3.getLockedInfo("XuP9HFUwY37bAQ2cVsVv3T2BFBine6CbT9", BigInteger.ZERO, BigInteger.TEN);
        System.out.println(infos);
        Assertions.assertTrue(infos.size() > 0);
    }

    @Test
    public void testExistAvailableNeedToRedeem() throws Exception {
        Boolean flag = safe4.safe3.existAvailableNeedToRedeem("XuPmDoaNb6rbNywefkTbESHXiYqNpYvaPU");
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testExistLockedNeedToRedeem() throws Exception {
        Boolean flag = safe4.safe3.existLockedNeedToRedeem("XuP9HFUwY37bAQ2cVsVv3T2BFBine6CbT9");
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testExistMasterNodeNeedToRedeem() throws Exception {
        Boolean flag = safe4.safe3.existMasterNodeNeedToRedeem("Xm7bqZeKBooWuQxb2EWjJxN2qjQVgN4AuU");
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }
}
