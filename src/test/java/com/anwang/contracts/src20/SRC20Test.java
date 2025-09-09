package com.anwang.contracts.src20;

import com.anwang.src20.SRC20;
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

public class SRC20Test {

    private Web3j web3j;
    private SRC20 src20;

    @BeforeEach
    public void init() {
        web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
        src20 = new SRC20(web3j, 6666666, "0x6b3914aF517A56D47A9997397325B7F37414A49c"); // SRC20: ABCD, contract-addr: 0x6b3914aF517A56D47A9997397325B7F37414A49c, total: 10000, creator: 0x4c207825db1c46Dd836123E58ecaE85de7025879
    }

    @Test
    public void testDeploy() throws Exception {
        src20 = new SRC20(web3j, 6666666);
        String name = "LMB100";
        String symbol = "LMB100";
        BigInteger totalSupply = new BigInteger("10000000000000000000000000000"); // 1B
        List<String> ret = src20.deploy("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", name, symbol, totalSupply); // 0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2: privateKey: 0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010
        System.out.println("contract address: " + ret.get(0));
        System.out.println("deploy txid: " + ret.get(1));
        Assertions.assertTrue(ret.get(0).length() != 0);
    }

    @Test
    public void testTransfer() throws Exception {
        String txid = src20.transfer("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", new Address("0x4c207825db1c46Dd836123E58ecaE85de7025879"), new BigInteger("100000000000000000000"));
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testApprove() throws Exception {
        String txid = src20.approve("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", new Address("0x4f203092FB68732D8484c099a72dDc5a195f26f9"), new BigInteger("1000000000000000000")); // approve 1 ABCD to SRC20LockFactory: 0x4f203092FB68732D8484c099a72dDc5a195f26f9
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testName() throws Exception {
        String name = src20.name();
        System.out.println("name: " + name);
        Assertions.assertTrue(name.length() != 0);
    }

    @Test
    public void testSymbol() throws Exception {
        String symbol = src20.symbol();
        System.out.println("symbol: " + symbol);
        Assertions.assertTrue(symbol.length() != 0);
    }

    @Test
    public void testDecimals() throws Exception {
        BigInteger decimals = src20.decimals();
        System.out.println("decimals: " + decimals);
        Assertions.assertEquals(18, decimals.intValue());
    }

    @Test
    public void testTotalSupply() throws Exception {
        BigInteger totalSupply = src20.totalSupply();
        System.out.println("totalSupply: " + totalSupply);
        Assertions.assertEquals(0, totalSupply.compareTo(new BigInteger("10000000000000000000000000000")));
    }

    @Test
    public void testOwner() throws Exception {
        Address owner = src20.owner();
        System.out.println("owner: " + owner);
        Assertions.assertNotEquals(Address.DEFAULT.getValue(), owner.getValue());
    }

    @Test
    public void testBalanceOf() throws Exception {
        BigInteger balance = src20.balanceOf(new Address("0x4c207825db1c46Dd836123E58ecaE85de7025879"));
        System.out.println("balance: " + balance);
        Assertions.assertEquals(0, balance.compareTo(new BigInteger("100000000000000000000")));
    }

    @Test
    public void testAllowance() throws Exception {
        BigInteger allowance = src20.allowance(new Address("0xa5CEc2B8CdA30dA3F3170b4505CB44226b6c9Dd2"), new Address("0x4f203092FB68732D8484c099a72dDc5a195f26f9"));
        System.out.println("allowance: " + allowance);
        Assertions.assertEquals(0, allowance.compareTo(new BigInteger("1000000000000000000")));
    }

    @Test
    public void testSetOrgName() throws Exception {
        String txid = src20.setOrgName("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", "lmb");
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testSetLogo() throws Exception {
        byte[] logo = Files.readAllBytes(Paths.get("C:\\Users\\Administrator\\Desktop\\100.png"));
        String txid = src20.setLogo("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", logo);
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testSetDescription() throws Exception {
        String txid = src20.setDescription("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", "LMB100 is a test token");
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testSetOfficialUrl() throws Exception {
        String txid = src20.setOfficialUrl("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", "https://www.lmb.com");
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testSetWhitePaperUrl() throws Exception {
        String txid = src20.setWhitePaperUrl("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", "https://www.lmb.com/whitepaper.pdf");
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testOrgName() throws Exception {
        String orgName = src20.orgName();
        System.out.println("orgName: " + orgName);
        Assertions.assertTrue(orgName.length() != 0);
    }

    @Test
    public void testLogo() throws Exception {
        byte[] logo = src20.logo();
        System.out.println("logo: " + Hex.toHexString(logo));
        Assertions.assertTrue(logo.length != 0);
    }

    @Test
    public void testDescritpion() throws Exception {
        String description = src20.description();
        System.out.println("description: " + description);
        Assertions.assertTrue(description.length() != 0);
    }

    @Test
    public void testOfficialUrl() throws Exception {
        String officialUrl = src20.officialUrl();
        System.out.println("officialUrl: " + officialUrl);
        Assertions.assertTrue(officialUrl.length() != 0);
    }

    @Test
    public void testWhitePaperUrl() throws Exception {
        String whitePaperUrl = src20.whitePaperUrl();
        System.out.println("whitePaperUrl: " + whitePaperUrl);
        Assertions.assertTrue(whitePaperUrl.length() != 0);
    }

    @Test
    public void testVersion() throws Exception {
        String version = src20.version();
        System.out.println("version: " + version);
        Assertions.assertTrue(version.length() != 0);
    }
}
