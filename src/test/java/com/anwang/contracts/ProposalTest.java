package com.anwang.contracts;

import com.anwang.Safe4;
import com.anwang.types.proposal.ProposalInfo;
import com.anwang.types.proposal.ProposalVoteInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.List;

public class ProposalTest {

    private Web3j web3j;
    private Safe4 safe4;

    @BeforeEach
    public void init() {
        web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
        safe4 = new Safe4(web3j, 6666666);
    }

    @Test
    public void testGetBalance() throws Exception {
        BigInteger balance = safe4.proposal.getBalance();
        System.out.println(balance);
        Assertions.assertTrue(balance.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testCreate() throws Exception {
        // addr: 0xd52114c4071b5bfbd06a657a3db538bfd559a481, privateKey: 0xe171bcc091332eaa8fd76f529f880bd210b3187354706599b033df8155a94d84
        String title = "propsal-2";
        BigInteger payAmount = new BigInteger("10000000000000000000");
        BigInteger payTimes = BigInteger.valueOf(2);
        BigInteger startPayTime = BigInteger.valueOf(1718012958);
        BigInteger endPayTime = BigInteger.valueOf(1720604958);
        String description = "proposal-2 for test";
        String txid = safe4.proposal.create("0xe171bcc091332eaa8fd76f529f880bd210b3187354706599b033df8155a94d84", title, payAmount, payTimes, startPayTime, endPayTime, description);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testVote() throws Exception {
        // sn: 0x918b8ed234e235a2ea5756fd5d9c80850ba3bb6d, privateKey: 0xfb5ae9ce44b96a5cb71f405806ec90493f18641776b44e9f267bed17c45a7874
        // sn: 0xd57574369a6c90a5622f17fbed30c891b9d70c3b, privateKey: 0x5bd818c9dd4d05351e9c7ff830d9ee7d556181a6473c84363ff7edb1dfc7e34f
        // sn: 0x556b5868919008607ef24f4eb2bde6feda3e42f2, privateKey: 0x73fc4be2c4a1d44f41c09ef138717a7994726461c9c256f25b36c3f3b570d9dd
        String txid = safe4.proposal.vote("0x73fc4be2c4a1d44f41c09ef138717a7994726461c9c256f25b36c3f3b570d9dd", BigInteger.ONE, BigInteger.ONE);
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testChangeTitle() throws Exception {
        String txid = safe4.proposal.changeTitle("0xe171bcc091332eaa8fd76f529f880bd210b3187354706599b033df8155a94d84", BigInteger.valueOf(2), "proposal 2");
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testChangePayAmount() throws Exception {
        String txid = safe4.proposal.changePayAmount("0xe171bcc091332eaa8fd76f529f880bd210b3187354706599b033df8155a94d84", BigInteger.valueOf(2), new BigInteger("10000000000000000000"));
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testChangePayTimes() throws Exception {
        String txid = safe4.proposal.changePayTimes("0xe171bcc091332eaa8fd76f529f880bd210b3187354706599b033df8155a94d84", BigInteger.valueOf(2), BigInteger.valueOf(1));
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testChangeStartPayTime() throws Exception {
        String txid = safe4.proposal.changeStartPayTime("0xe171bcc091332eaa8fd76f529f880bd210b3187354706599b033df8155a94d84", BigInteger.valueOf(2), BigInteger.valueOf(1725184158));
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testChangeEndPayTime() throws Exception {
        String txid = safe4.proposal.changeEndPayTime("0xe171bcc091332eaa8fd76f529f880bd210b3187354706599b033df8155a94d84", BigInteger.valueOf(2), BigInteger.valueOf(1725184158));
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testChangeDescription() throws Exception {
        String txid = safe4.proposal.changeDescription("0xe171bcc091332eaa8fd76f529f880bd210b3187354706599b033df8155a94d84", BigInteger.valueOf(2), "proposal 2 for test");
        System.out.println(txid);
        Assertions.assertTrue((txid.length() > 0));
    }

    @Test
    public void testGetInfo() throws Exception {
        ProposalInfo info = safe4.proposal.getInfo(BigInteger.valueOf(2));
        System.out.println(info.toString());
        Assertions.assertTrue(info.title.length() > 0);
    }

    @Test
    public void testGetVoterNum() throws Exception {
        BigInteger voterNum = safe4.proposal.getVoterNum(BigInteger.ONE);
        System.out.println(voterNum);
        Assertions.assertTrue(voterNum.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetVoteInfo() throws Exception {
        List<ProposalVoteInfo> infos = safe4.proposal.getVoteInfo(BigInteger.ONE, BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(infos);
        Assertions.assertTrue(infos.size() > 0);
    }

    @Test
    public void testGetNum() throws Exception {
        BigInteger num = safe4.proposal.getNum();
        System.out.println(num);
        Assertions.assertTrue(num.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetAll() throws Exception {
        List<BigInteger> ids = safe4.proposal.getAll(BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(ids);
        Assertions.assertTrue(ids.size() > 0);
    }

    @Test
    public void testGetMineNum() throws Exception {
        BigInteger num = safe4.proposal.getMineNum(new Address("0xd52114c4071b5bfbd06a657a3db538bfd559a481"));
        System.out.println(num);
        Assertions.assertTrue(num.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetMines() throws Exception {
        List<BigInteger> ids = safe4.proposal.getMines(new Address("0xd52114c4071b5bfbd06a657a3db538bfd559a481"), BigInteger.ZERO, BigInteger.valueOf(100));
        System.out.println(ids);
        Assertions.assertTrue(ids.size() > 0);
    }

    @Test
    public void testExist() throws Exception {
        Boolean flag = safe4.proposal.exist(BigInteger.ONE);
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }
}
