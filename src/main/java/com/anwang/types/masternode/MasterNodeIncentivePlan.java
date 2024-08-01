package com.anwang.types.masternode;

import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

public class MasterNodeIncentivePlan extends StaticStruct {
    public BigInteger creator;
    public BigInteger partner;
    public BigInteger voter;

    public MasterNodeIncentivePlan(BigInteger creator, BigInteger partner, BigInteger voter) {
        super(new Uint256(creator), new Uint256(partner), new Uint256(voter));
        this.creator = creator;
        this.partner = partner;
        this.voter = voter;
    }

    public MasterNodeIncentivePlan(Uint256 creator, Uint256 partner, Uint256 voter) {
        super(creator, partner, voter);
        this.creator = creator.getValue();
        this.partner = partner.getValue();
        this.voter = voter.getValue();
    }

    @Override
    public String toString() {
        return "MasterNodeIncentivePlan{" +
                "creator=" + creator +
                ", partner=" + partner +
                ", voter=" + voter +
                '}';
    }
}
