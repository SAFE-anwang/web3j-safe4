package com.anwang.types.supernode;

import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

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

    public BigInteger getCreator() {
        return creator;
    }

    public void setCreator(BigInteger creator) {
        this.creator = creator;
    }

    public BigInteger getPartner() {
        return partner;
    }

    public void setPartner(BigInteger partner) {
        this.partner = partner;
    }

    public BigInteger getVoter() {
        return voter;
    }

    public void setVoter(BigInteger voter) {
        this.voter = voter;
    }

    @Override
    public String toString() {
        return "SuperNodeIncentivePlan{" +
                "creator=" + creator +
                ", partner=" + partner +
                ", voter=" + voter +
                '}';
    }
}
