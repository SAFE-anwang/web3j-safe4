package com.anwang.contracts.additions;

import com.anwang.Safe4;
import com.anwang.types.dapp.DAppInfo;
import org.bouncycastle.util.encoders.Hex;
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

public class DAppManagerTest {

    private Web3j web3j;
    private Safe4 safe4;

    @BeforeEach
    public void init() {
        web3j = Web3j.build(new HttpService("http://127.0.0.1:40405"));
        safe4 = new Safe4(web3j, 6666666);
    }

    @Test
    public void testRegister() throws Exception {
        // 0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2: privateKey: 0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010
        String name = "test1";
        Address contractAddr = new Address("0x55a628d5c68adbcba3c5e1a6f6ad2f114f5a9b40");
        String runUrl = "https://test1.dapp.com";
        String description = "This is a dapp for testing";
        String txid = safe4.dapp.register("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", name, contractAddr, runUrl, description, "", "", "");
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testSetName() throws Exception {
        String name = "test_1";
        String txid = safe4.dapp.setName("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", BigInteger.valueOf(2), name);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testSetContractAddr() throws Exception {
        Address contractAddr = new Address("0x978926313e56dc33ec7d9137d8910ba7c822f5a8");
        String txid = safe4.dapp.setContractAddr("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", BigInteger.valueOf(2), contractAddr);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testSetRunUrl() throws Exception {
        String runUrl = "https://test_1.dapp.com";
        String txid = safe4.dapp.setRunUrl("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", BigInteger.valueOf(2), runUrl);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testSetGitUrl() throws Exception {
        String gitUrl = "https://www.github.com/dapp/test_1";
        String txid = safe4.dapp.setGitUrl("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", BigInteger.valueOf(2), gitUrl);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testSetOfficialUrl() throws Exception {
        String officialUrl = "https://www.dapp.com";
        String txid = safe4.dapp.setOfficialUrl("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", BigInteger.valueOf(2), officialUrl);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testSetOfficialEmail() throws Exception {
        String officialEmail = "test_1@dapp.com";
        String txid = safe4.dapp.setOfficialEmail("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", BigInteger.valueOf(2), officialEmail);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testSetOfficialAccount() throws Exception {
        Address account = new Address("0xa5CEc2B8CdA30dA3F3170b4505CB44226b6c9Dd2");
        String txid = safe4.dapp.setOfficialAccount("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", BigInteger.valueOf(2), account);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testSetDescription() throws Exception {
        String description = "This is a dapp for testing!!!";
        String txid = safe4.dapp.setDescription("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", BigInteger.valueOf(2), description);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testSetKeyword() throws Exception {
        String keyword = "test|game";
        String txid = safe4.dapp.setKeyword("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", BigInteger.valueOf(2), keyword);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testSetLogo() throws Exception {
        byte[] logo = Files.readAllBytes(Paths.get("C:\\Users\\Administrator\\Desktop\\100.png"));
        String txid = safe4.dapp.setLogo("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", BigInteger.valueOf(2), logo);
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testRemove() throws Exception {
        String txid = safe4.dapp.remove("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", BigInteger.valueOf(2));
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testMarkFraud() throws Exception {
        String txid = safe4.dapp.markFraud("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", BigInteger.valueOf(2), true);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testGetInfo() throws Exception {
        DAppInfo info = safe4.dapp.getInfo(BigInteger.valueOf(2));
        System.out.println(info.toString());
        Assertions.assertTrue(info.id.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetInfoByName() throws Exception {
        DAppInfo info = safe4.dapp.getInfoByName("test_1");
        System.out.println(info.toString());
        Assertions.assertTrue(info.id.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetInfoByContractAddr() throws Exception {
        DAppInfo info = safe4.dapp.getInfoByContractAddr(new Address("0x978926313e56dc33ec7d9137d8910ba7c822f5a8"));
        System.out.println(info.toString());
        Assertions.assertTrue(info.id.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetInfoByRunUrl() throws Exception {
        DAppInfo info = safe4.dapp.getInfoByRunUrl("https://test_1.dapp.com");
        System.out.println(info.toString());
        Assertions.assertTrue(info.id.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetLogo() throws Exception {
        byte[] logo = safe4.dapp.getLogo(BigInteger.valueOf(2));
        System.out.println("logo: " + Hex.toHexString(logo));
        Assertions.assertTrue(logo.length != 0);
    }

    @Test
    public void testGetNum() throws Exception {
        BigInteger num = safe4.dapp.getNum();
        System.out.println(num);
        Assertions.assertTrue(num.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetIDs() throws Exception {
        List<BigInteger> ids = safe4.dapp.getIDs(BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(ids.toString());
        Assertions.assertTrue(ids.size() > 0);
    }

    @Test
    public void testGetMineNum() throws Exception {
        BigInteger mineNum = safe4.dapp.getMineNum(new Address("0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2"));
        System.out.println(mineNum);
        Assertions.assertTrue(mineNum.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetMineIDs() throws Exception {
        List<BigInteger> mineIDs = safe4.dapp.getMineIDs(new Address("0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2"), BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(mineIDs.toString());
        Assertions.assertTrue(mineIDs.size() > 0);
    }

    @Test
    public void testExistID() throws Exception {
        Boolean flag = safe4.dapp.existID(BigInteger.valueOf(2));
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testExistName() throws Exception {
        Boolean flag = safe4.dapp.existName("test_1");
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testExistContractAddr() throws Exception {
        Boolean flag = safe4.dapp.existContractAddr(new Address("0x978926313e56dc33ec7d9137d8910ba7c822f5a8"));
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testExistRunUrl() throws Exception {
        Boolean flag = safe4.dapp.existRunUrl("https://test_1.dapp.com");
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testIsMarkedFraud() throws Exception {
        Boolean flag = safe4.dapp.isMarkedFraud(new Address("0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2"), BigInteger.valueOf(2));
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testIsFrozen() throws Exception {
        Boolean flag = safe4.dapp.isFrozen(BigInteger.valueOf(2));
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testGetLogoPayAmount() throws Exception {
        BigInteger payAmount = safe4.dapp.getLogoPayAmount();
        System.out.println("logo-pay-amount: " + payAmount);
        Assertions.assertEquals(0, payAmount.compareTo(new BigInteger("100000000000000000000")));
    }

    @Test
    public void testGetLogoPayAddress() throws Exception {
        Address payAddress = safe4.dapp.getLogoPayAddress();
        System.out.println("logo-pay-address: " + payAddress);
        Assertions.assertNotEquals(payAddress, Address.DEFAULT);
    }
}
