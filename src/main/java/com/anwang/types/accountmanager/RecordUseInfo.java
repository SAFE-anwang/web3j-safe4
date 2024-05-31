package com.anwang.types.accountmanager;

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
public class RecordUseInfo extends StaticStruct {
    private Address frozenAddr;
    private BigInteger freezeHeight;
    private BigInteger unfreezeHeight;
    private Address votedAddr;
    private BigInteger voteHeight;
    private BigInteger releaseHeight;

    public RecordUseInfo() {
        this.frozenAddr = Address.DEFAULT;
        this.freezeHeight = BigInteger.ZERO;
        this.unfreezeHeight = BigInteger.ZERO;
        this.votedAddr = Address.DEFAULT;
        this.voteHeight = BigInteger.ZERO;
        this.releaseHeight = BigInteger.ZERO;
    }

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
}
