package com.anwang.contracts;

import com.anwang.Safe4;
import com.anwang.types.snvote.SNVoteRetInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class SNVoteTest {

    private Web3j web3j;
    private Safe4 safe4;

    @BeforeEach
    public void init() {
        web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
        safe4 = new Safe4(web3j, 6666667);
    }

    @Test
    public void testVoteOrApproval() throws Exception {
        // addr: 0xd52114c4071b5bfbd06a657a3db538bfd559a481, privateKey: 0xe171bcc091332eaa8fd76f529f880bd210b3187354706599b033df8155a94d84
        // String txid = safe4.snvote.voteOrApproval("0xe171bcc091332eaa8fd76f529f880bd210b3187354706599b033df8155a94d84", true, new Address("0x4d244c17d2a957f4a150408f8f4d19382ae8e2c8"), Arrays.asList(BigInteger.ZERO, BigInteger.valueOf(20), BigInteger.valueOf(21)));
        String txid = safe4.snvote.voteOrApproval("0xe171bcc091332eaa8fd76f529f880bd210b3187354706599b033df8155a94d84", false, new Address("0x64ae0d18085d0c3ec202a208e96bc2fc24e4a7e8"), Arrays.asList(BigInteger.ZERO, BigInteger.valueOf(23)));
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testVoteOrApprovalWithAmount() throws Exception {
        // addr: 0xd52114c4071b5bfbd06a657a3db538bfd559a481, privateKey: 0xe171bcc091332eaa8fd76f529f880bd210b3187354706599b033df8155a94d84
        String txid = safe4.snvote.voteOrApprovalWithAmount("0xe171bcc091332eaa8fd76f529f880bd210b3187354706599b033df8155a94d84", new BigInteger("1000000000000000000"), true, new Address("0x4d244c17d2a957f4a150408f8f4d19382ae8e2c8"));
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testRemoveVoteOrApproval() throws Exception {
        String txid = safe4.snvote.removeVoteOrApproval("0xe171bcc091332eaa8fd76f529f880bd210b3187354706599b033df8155a94d84", Arrays.asList(BigInteger.valueOf(17)));
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testProxyVote() throws Exception {
        // mn: 0x64ae0d18085d0c3ec202a208e96bc2fc24e4a7e8, privateKey: 0x02b0f66ceddcf16601dfc462ee3dfcc9adca7bf76b872fc23ca88d0b82f2550f
        String txid = safe4.snvote.proxyVote("0x02b0f66ceddcf16601dfc462ee3dfcc9adca7bf76b872fc23ca88d0b82f2550f", new Address("0x4d244c17d2a957f4a150408f8f4d19382ae8e2c8"));
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testGetAmount4Voter() throws Exception {
        BigInteger amount = safe4.snvote.getAmount4Voter(new Address("0xd52114c4071b5bfbd06a657a3db538bfd559a481"));
        System.out.println(amount);
        Assertions.assertTrue(amount.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetVoteNum4Voter() throws Exception {
        BigInteger voteNum = safe4.snvote.getVoteNum4Voter(new Address("0xd52114c4071b5bfbd06a657a3db538bfd559a481"));
        System.out.println(voteNum);
        Assertions.assertTrue(voteNum.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetSNNum4Voter() throws Exception {
        BigInteger snNum = safe4.snvote.getSNNum4Voter(new Address("0xd52114c4071b5bfbd06a657a3db538bfd559a481"));
        System.out.println(snNum);
        Assertions.assertTrue(snNum.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetSNs4Voter() throws Exception {
        SNVoteRetInfo info = safe4.snvote.getSNs4Voter(new Address("0xd52114c4071b5bfbd06a657a3db538bfd559a481"), BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(info.toString());
        Assertions.assertTrue(info.getAddrs().size() > 0);
    }

    @Test
    public void testGetProxyNum4Voter() throws Exception {
        BigInteger proxyNum = safe4.snvote.getProxyNum4Voter(new Address("0xd52114c4071b5bfbd06a657a3db538bfd559a481"));
        System.out.println(proxyNum);
        Assertions.assertTrue(proxyNum.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetProxies4Voter() throws Exception {
        SNVoteRetInfo info = safe4.snvote.getProxies4Voter(new Address("0xd52114c4071b5bfbd06a657a3db538bfd559a481"), BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(info.toString());
        Assertions.assertTrue(info.getAddrs().size() > 0);
    }

    @Test
    public void testGetVotedIDNum4Voter() throws Exception {
        BigInteger idNum = safe4.snvote.getVotedIDNum4Voter(new Address("0xd52114c4071b5bfbd06a657a3db538bfd559a481"));
        System.out.println(idNum);
        Assertions.assertTrue(idNum.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetVotedIDs4Voter() throws Exception {
        List<BigInteger> ids = safe4.snvote.getVotedIDs4Voter(new Address("0xd52114c4071b5bfbd06a657a3db538bfd559a481"), BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(ids);
        Assertions.assertTrue(ids.size() > 0);
    }

    @Test
    public void testGetProxiedIDNum4Voter() throws Exception {
        BigInteger idNum = safe4.snvote.getProxiedIDNum4Voter(new Address("0xd52114c4071b5bfbd06a657a3db538bfd559a481"));
        System.out.println(idNum);
        Assertions.assertTrue(idNum.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetProxiedIDs4Voter() throws Exception {
        List<BigInteger> ids = safe4.snvote.getProxiedIDs4Voter(new Address("0xd52114c4071b5bfbd06a657a3db538bfd559a481"), BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(ids);
        Assertions.assertTrue(ids.size() > 0);
    }

    @Test
    public void testGetTotalAmount() throws Exception {
        BigInteger amount = safe4.snvote.getTotalAmount(new Address("0x4d244c17d2a957f4a150408f8f4d19382ae8e2c8"));
        System.out.println(amount);
        Assertions.assertTrue(amount.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetTotalNum() throws Exception {
        BigInteger voteNum = safe4.snvote.getTotalVoteNum(new Address("0x4d244c17d2a957f4a150408f8f4d19382ae8e2c8"));
        System.out.println(voteNum);
        Assertions.assertTrue(voteNum.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetVoterNum() throws Exception {
        BigInteger snNum = safe4.snvote.getVoterNum(new Address("0x4d244c17d2a957f4a150408f8f4d19382ae8e2c8"));
        System.out.println(snNum);
        Assertions.assertTrue(snNum.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetVoters() throws Exception {
        SNVoteRetInfo info = safe4.snvote.getVoters(new Address("0x4d244c17d2a957f4a150408f8f4d19382ae8e2c8"), BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(info.toString());
        Assertions.assertTrue(info.getAddrs().size() > 0);
    }

    @Test
    public void testGetIDNum() throws Exception {
        BigInteger idNum = safe4.snvote.getIDNum(new Address("0x4d244c17d2a957f4a150408f8f4d19382ae8e2c8"));
        System.out.println(idNum);
        Assertions.assertTrue(idNum.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetIDs() throws Exception {
        List<BigInteger> ids = safe4.snvote.getIDs(new Address("0x4d244c17d2a957f4a150408f8f4d19382ae8e2c8"), BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(ids);
        Assertions.assertTrue(ids.size() > 0);
    }
}
