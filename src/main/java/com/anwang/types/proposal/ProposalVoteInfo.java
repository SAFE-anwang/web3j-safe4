package com.anwang.types.proposal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

@Getter
@Setter
@ToString
public class ProposalVoteInfo extends StaticStruct {
    private Address voter;
    private BigInteger voteResult;

    public ProposalVoteInfo() {
        this.voter = Address.DEFAULT;
        this.voteResult = BigInteger.ZERO;
    }

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
}
