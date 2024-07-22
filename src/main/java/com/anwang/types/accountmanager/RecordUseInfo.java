package com.anwang.types.accountmanager;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

public class RecordUseInfo extends StaticStruct {
    private Address frozenAddr;
    private BigInteger freezeHeight;
    private BigInteger unfreezeHeight;
    private Address votedAddr;
    private BigInteger voteHeight;
    private BigInteger releaseHeight;

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

    public Address getFrozenAddr() {
        return frozenAddr;
    }

    public void setFrozenAddr(Address frozenAddr) {
        this.frozenAddr = frozenAddr;
    }

    public BigInteger getFreezeHeight() {
        return freezeHeight;
    }

    public void setFreezeHeight(BigInteger freezeHeight) {
        this.freezeHeight = freezeHeight;
    }

    public BigInteger getUnfreezeHeight() {
        return unfreezeHeight;
    }

    public void setUnfreezeHeight(BigInteger unfreezeHeight) {
        this.unfreezeHeight = unfreezeHeight;
    }

    public Address getVotedAddr() {
        return votedAddr;
    }

    public void setVotedAddr(Address votedAddr) {
        this.votedAddr = votedAddr;
    }

    public BigInteger getVoteHeight() {
        return voteHeight;
    }

    public void setVoteHeight(BigInteger voteHeight) {
        this.voteHeight = voteHeight;
    }

    public BigInteger getReleaseHeight() {
        return releaseHeight;
    }

    public void setReleaseHeight(BigInteger releaseHeight) {
        this.releaseHeight = releaseHeight;
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
