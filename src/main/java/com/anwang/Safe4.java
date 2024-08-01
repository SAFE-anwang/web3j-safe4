package com.anwang;

import com.anwang.contracts.*;
import org.web3j.protocol.Web3j;

public class Safe4 {
    public final AccountManager account;
    public final SysProperty sysproperty;
    public final MasterNode masternode;
    public final SuperNode supernode;
    public final SNVote snvote;
    public final Proposal proposal;
    public final Safe3 safe3;

    public Safe4(Web3j web3j, long chainId) {
        this.account = new AccountManager(web3j, chainId);
        this.sysproperty = new SysProperty(web3j, chainId);
        this.masternode = new MasterNode(web3j, chainId);
        this.supernode = new SuperNode(web3j, chainId);
        this.snvote = new SNVote(web3j, chainId);
        this.proposal = new Proposal(web3j, chainId);
        this.safe3 = new Safe3(web3j, chainId);
    }
}
