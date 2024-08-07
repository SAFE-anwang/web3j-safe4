package com.anwang.types.accountmanager;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

public class RecordUseInfo extends StaticStruct {
    public Address frozenAddr;
    public BigInteger freezeHeight;
    public BigInteger unfreezeHeight;
    public Address votedAddr;
    public BigInteger voteHeight;
    public BigInteger releaseHeight;

    public RecordUseInfo(Address frozenAddr, BigInteger freezeHeight, BigInteger unfreezeHeight, Address votedAddr, BigInteger voteHeight, BigInteger releaseHeight) {
        super(frozenAddr, new Uint256(freezeHeight), new Uint256(unfreezeHeight), votedAddr, new Uint256(voteHeight), new Uint256(releaseHeight));
        this.frozenAddr = frozenAddr;
        this.freezeHeight = freezeHeight;
        this.unfreezeHeight = unfreezeHeight;
        this.votedAddr = votedAddr;
        this.voteHeight = voteHeight;
        this.releaseHeight = releaseHeight;
    }

    public RecordUseInfo(Address frozenAddr, Uint256 freezeHeight, Uint256 unfreezeHeight, Address votedAddr, Uint256 voteHeight, Uint256 releaseHeight) {
        super(frozenAddr, freezeHeight, unfreezeHeight, votedAddr, voteHeight, releaseHeight);
        this.frozenAddr = frozenAddr;
        this.freezeHeight = freezeHeight.getValue();
        this.unfreezeHeight = unfreezeHeight.getValue();
        this.votedAddr = votedAddr;
        this.voteHeight = voteHeight.getValue();
        this.releaseHeight = releaseHeight.getValue();
    }

    @Override
    public String toString() {
        return "RecordUseInfo{" +
                "frozenAddr=" + frozenAddr +
                ", freezeHeight=" + freezeHeight +
                ", unfreezeHeight=" + unfreezeHeight +
                ", votedAddr=" + votedAddr +
                ", voteHeight=" + voteHeight +
                ", releaseHeight=" + releaseHeight +
                '}';
    }
}
