package com.anwang.types.supernode;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

@Getter
@Setter
@ToString
public class SuperNodeIncentivePlan extends StaticStruct {
    private BigInteger creator;
    private BigInteger partner;
    private BigInteger voter;

    public SuperNodeIncentivePlan() {
        this.creator = BigInteger.ZERO;
        this.partner = BigInteger.ZERO;
        this.voter = BigInteger.ZERO;
    }

    public SuperNodeIncentivePlan(BigInteger creator, BigInteger partner, BigInteger voter) {
        super(new Uint256(creator), new Uint256(partner), new Uint256(voter));
        this.creator = creator;
        this.partner = partner;
        this.voter = voter;
    }

    public SuperNodeIncentivePlan(Uint256 creator, Uint256 partner, Uint256 voter) {
        super(creator, partner, voter);
        this.creator = creator.getValue();
        this.partner = partner.getValue();
        this.voter = voter.getValue();
    }
}
