package com.anwang.contracts.src721;

import com.anwang.src721.SRC721;
import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SRC721Test {

    private Web3j web3j;
    private SRC721 src721;

    @BeforeEach
    public void init() {
        //web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
        web3j = Web3j.build(new HttpService("http://10.0.0.142:8545"));
        src721 = new SRC721(web3j, 6666666, "0xafcdc7e2155fb913555d39671fbf77fa8faa1e2b"); // NFT: LMB1, contract-addr: 0xafcdc7e2155fb913555d39671fbf77fa8faa1e2b, max supply: 10000, creator: 0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2
    }

    @Test
    public void testDeploy() throws Exception {
        src721 = new SRC721(web3j, 6666666);
        String name = "LMB1";
        String symbol = "LMB1";
        String baseURI = "https://www.test.com/";
        BigInteger maxSupply = new BigInteger("10000");
        BigInteger mintPrice = Convert.toWei("0.1", Convert.Unit.ETHER).toBigInteger(); // 0.1 SAFE
        List<String> ret = src721.deploy("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", name, symbol, baseURI, maxSupply, mintPrice); // 0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2: privateKey: 0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010
        System.out.println("contract address: " + ret.get(0));
        System.out.println("deploy txid: " + ret.get(1));
        Assertions.assertTrue(ret.get(0).length() != 0);
    }

    @Test
    public void testAdminMint() throws Exception {
        Address to = new Address("0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2");
        BigInteger amount = new BigInteger("100");
        String txid = src721.adminMint("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", to, amount);
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testMint() throws Exception {
        BigInteger mintPrice = Convert.toWei("0.1", Convert.Unit.ETHER).toBigInteger(); // 0.1 SAFE
        Address to = new Address("0x5E5e15A6BC5F699a246F59101f86307eDa9859fA");
        BigInteger amount = new BigInteger("2");
        String txid = src721.mint("0x1b0610af92bfb76fdb2e63b61aeaf5f4e19e62c841c8390abb36efed10142077", mintPrice.multiply(amount), to, amount);
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testWithdraw() throws Exception {
        String txid = src721.withdraw("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010");
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testApprove() throws Exception {
        Address to = new Address("0x5E5e15A6BC5F699a246F59101f86307eDa9859fA");
        BigInteger tokenId = new BigInteger("1"); // tokenId: 1
        String txid = src721.approve("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", to, tokenId);
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testSafeTransferFrom() throws Exception {
        Address from = new Address("0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2");
        Address to = new Address("0x5E5e15A6BC5F699a246F59101f86307eDa9859fA");
        BigInteger tokenId = new BigInteger("0");
        String txid = src721.safeTransferFrom("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", from, to, tokenId);
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testSetBaseURI() throws Exception {
        String txid = src721.setBaseURI("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", "http://www.lmb.com");
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testSetMintPrice() throws Exception {
        BigInteger mintPrice = Convert.toWei("0.5", Convert.Unit.ETHER).toBigInteger(); // 0.5 SAFE
        String txid = src721.setMintPrice("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", mintPrice);
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testSetMaxSupply() throws Exception {
        BigInteger maxSupply = new BigInteger("50000");
        String txid = src721.setMaxSupply("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", maxSupply);
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testSetAllowList() throws Exception {
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("0x5E5e15A6BC5F699a246F59101f86307eDa9859fA"));
        addresses.add(new Address("0x44d798A6226794ef51839Fc661EBd46A96dd1Ba9"));
        List<BigInteger> amounts = new ArrayList<>();
        amounts.add(new BigInteger("5"));
        amounts.add(new BigInteger("100"));
        String txid = src721.setAllowList("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", addresses, amounts);
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testOwner() throws Exception {
        Address owner = src721.owner();
        System.out.println("NFT contract owner: " + owner);
        Assertions.assertEquals(owner.getValue(), "0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2");
    }

    @Test
    public void testName() throws Exception {
        String name = src721.name();
        System.out.println("name: " + name);
        Assertions.assertTrue(name.length() != 0);
    }

    @Test
    public void testSymbol() throws Exception {
        String symbol = src721.symbol();
        System.out.println("symbol: " + symbol);
        Assertions.assertTrue(symbol.length() != 0);
    }

    @Test
    public void testBaseURI() throws Exception {
        String baseURI = src721.baseURI();
        System.out.println("baseURI: " + baseURI);
        Assertions.assertTrue(baseURI.length() != 0);
    }

    @Test
    public void testMintPrice() throws Exception {
        BigInteger mintPrice = src721.mintPrice();
        System.out.println("mintPrice: " + mintPrice);
        Assertions.assertTrue(mintPrice.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testMaxSupply() throws Exception {
        BigInteger maxSupply = src721.maxSupply();
        System.out.println("maxSupply: " + maxSupply);
        Assertions.assertTrue(maxSupply.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testTotalSupply() throws Exception {
        BigInteger totalSupply = src721.totalSupply();
        System.out.println("totalSupply: " + totalSupply);
        Assertions.assertTrue(totalSupply.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testRemainSupply() throws Exception {
        BigInteger remainSupply = src721.remainSupply();
        System.out.println("remainSupply: " + remainSupply);
        Assertions.assertTrue(remainSupply.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testBalanceOf() throws Exception {
        BigInteger balance = src721.balanceOf(new Address("0x5E5e15A6BC5F699a246F59101f86307eDa9859fA"));
        System.out.println("balance: " + balance);
        Assertions.assertTrue(balance.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testOwnerof() throws Exception {
        Address owner = src721.ownerOf(BigInteger.ZERO);
        System.out.println("owner: " + owner);
        Assertions.assertEquals(owner.getValue(), "0x5e5e15a6bc5f699a246f59101f86307eda9859fa");
    }

    @Test
    public void testTokenByIndex() throws Exception {
        BigInteger tokenId = src721.tokenByIndex(BigInteger.ZERO);
        System.out.println("tokenId: " + tokenId);
        Assertions.assertEquals(tokenId.compareTo(BigInteger.ZERO), 0);
    }

    @Test
    public void testTokenOfOwnerByIndex() throws Exception {
        BigInteger tokenId = src721.tokenOfOwnerByIndex(new Address("0xa5cec2b8cda30da3f3170b4505cb44226b6c9dd2"), BigInteger.TEN);
        System.out.println("tokenId: " + tokenId);
        Assertions.assertEquals(tokenId.compareTo(BigInteger.TEN), 0);
    }

    @Test
    public void testAmountAllowToMint() throws Exception {
        Address addr = new Address("0x44d798A6226794ef51839Fc661EBd46A96dd1Ba9");
        BigInteger amount = src721.amountAllowToMint(addr);
        System.out.println("allow amount: " + amount);
        Assertions.assertEquals(amount.intValue(), 100);
    }

    @Test
    public void testCanMint() throws Exception {
        Address addr = new Address("0x44d798A6226794ef51839Fc661EBd46A96dd1Ba9");
        Boolean flag = src721.canMint(addr);
        System.out.println("can mint: " + flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testGetApproved() throws Exception {
        BigInteger tokenId = BigInteger.ONE;
        Address addr = src721.getApproved(tokenId);
        System.out.println("approved: " + addr);
        Assertions.assertNotEquals(Address.DEFAULT.getValue(), addr.getValue());
    }

    @Test
    public void testTokenURI() throws Exception {
        BigInteger tokenId = BigInteger.TEN;
        String tokenURI = src721.tokenURI(tokenId);
        System.out.println("token URI: " + tokenURI);
        Assertions.assertTrue(!tokenURI.isEmpty());
    }

    @Test
    public void testSetOrgName() throws Exception {
        String txid = src721.setOrgName("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", "lmb");
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testSetLogo() throws Exception {
        byte[] logo = Files.readAllBytes(Paths.get("C:\\Users\\Administrator\\Desktop\\100.png"));
        String txid = src721.setLogo("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", logo);
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testSetDescription() throws Exception {
        String txid = src721.setDescription("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", "LMB1 is a nft token for testing");
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testSetOfficialUrl() throws Exception {
        String txid = src721.setOfficialUrl("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", "https://www.lmb.com");
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testSetWhitePaperUrl() throws Exception {
        String txid = src721.setWhitePaperUrl("0x7b281a9ba16001feb62a5929526ef8f69d6550c6acdc3f0579c69199c0b6a010", "https://www.lmb.com/whitepaper.pdf");
        System.out.println(txid);
        Assertions.assertTrue(txid.length() != 0);
    }

    @Test
    public void testOrgName() throws Exception {
        String orgName = src721.orgName();
        System.out.println("orgName: " + orgName);
        Assertions.assertTrue(orgName.length() != 0);
    }

    @Test
    public void testLogo() throws Exception {
        byte[] logo = src721.logo();
        System.out.println("logo: " + Hex.toHexString(logo));
        Assertions.assertTrue(logo.length != 0);
    }

    @Test
    public void testDescritpion() throws Exception {
        String description = src721.description();
        System.out.println("description: " + description);
        Assertions.assertTrue(description.length() != 0);
    }

    @Test
    public void testOfficialUrl() throws Exception {
        String officialUrl = src721.officialUrl();
        System.out.println("officialUrl: " + officialUrl);
        Assertions.assertTrue(officialUrl.length() != 0);
    }

    @Test
    public void testWhitePaperUrl() throws Exception {
        String whitePaperUrl = src721.whitePaperUrl();
        System.out.println("whitePaperUrl: " + whitePaperUrl);
        Assertions.assertTrue(whitePaperUrl.length() != 0);
    }

    @Test
    public void testVersion() throws Exception {
        String version = src721.version();
        System.out.println("version: " + version);
        Assertions.assertTrue(version.length() != 0);
    }
}
