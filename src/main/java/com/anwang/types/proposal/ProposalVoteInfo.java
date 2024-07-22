package com.anwang.types.proposal;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

public class ProposalVoteInfo extends StaticStruct {
    private Address voter;
    private BigInteger voteResult;

    public ProposalVoteInfo(Address voter, BigInteger voteResult) {
        super(voter, new Uint256(voteResult));
        this.voter = voter;
        this.voteResult = voteResult;
    }

    public ProposalVoteInfo(Address voter, Uint256 voteResult) {
        super(voter, voteResult);
        this.voter = voter;
        this.voteResult = voteResult.getValue();
    }

    public Address getVoter() {
        return voter;
    }

    public void setVoter(Address voter) {
        this.voter = voter;
    }

    public BigInteger getVoteResult() {
        return voteResult;
    }

    public void setVoteResult(BigInteger voteResult) {
        this.voteResult = voteResult;
    }

    @Override
    public String toString() {
        return "ProposalVoteInfo{" +
                "voter=" + voter +
                ", voteResult=" + voteResult +
                '}';
    }
}
