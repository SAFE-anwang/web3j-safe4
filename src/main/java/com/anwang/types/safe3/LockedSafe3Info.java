package com.anwang.types.safe3;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

public class LockedSafe3Info extends DynamicStruct {
    public String safe3Addr;
    public BigInteger amount;
    public String txid;
    public BigInteger lockHeight;
    public BigInteger unlockHeight;
    public BigInteger remainLockHeight;
    public BigInteger lockDay;
    public Boolean isMN;
    public Address safe4Addr;
    public BigInteger redeemHeight;

    public LockedSafe3Info(String safe3Addr, BigInteger amount, String txid, BigInteger lockHeight, BigInteger unlockHeight, BigInteger remainLockHeight, BigInteger lockDay, Boolean isMN, Address safe4Addr, BigInteger redeemHeight) {
        super(new Utf8String(safe3Addr),
                new Uint256(amount),
                new Utf8String(txid),
                new Uint256(lockHeight),
                new Uint256(unlockHeight),
                new Uint256(remainLockHeight),
                new Uint256(lockDay),
                new Bool(isMN),
                safe4Addr,
                new Uint256(redeemHeight));
        this.safe3Addr = safe3Addr;
        this.amount = amount;
        this.txid = txid;
        this.lockHeight = lockHeight;
        this.unlockHeight = unlockHeight;
        this.remainLockHeight = remainLockHeight;
        this.lockDay = lockDay;
        this.isMN = isMN;
        this.safe4Addr = safe4Addr;
        this.redeemHeight = redeemHeight;
    }

    public LockedSafe3Info(Utf8String safe3Addr, Uint256 amount, Utf8String txid, Uint256 lockHeight, Uint256 unlockHeight, Uint256 remainLockHeight, Uint256 lockDay, Bool isMN, Address safe4Addr, Uint256 redeemHeight) {
        super(safe3Addr, amount, safe4Addr, redeemHeight);
        this.safe3Addr = safe3Addr.getValue();
        this.amount = amount.getValue();
        this.txid = txid.getValue();
        this.lockHeight = lockHeight.getValue();
        this.unlockHeight = unlockHeight.getValue();
        this.remainLockHeight = remainLockHeight.getValue();
        this.lockDay = lockDay.getValue();
        this.isMN = isMN.getValue();
        this.safe4Addr = safe4Addr;
        this.redeemHeight = redeemHeight.getValue();
    }

    @Override
    public String toString() {
        return "LockedSafe3Info{" +
                "safe3Addr='" + safe3Addr + '\'' +
                ", amount=" + amount +
                ", txid='" + txid + '\'' +
                ", lockHeight=" + lockHeight +
                ", unlockHeight=" + unlockHeight +
                ", remainLockHeight=" + remainLockHeight +
                ", lockDay=" + lockDay +
                ", isMN=" + isMN +
                ", safe4Addr=" + safe4Addr +
                ", redeemHeight=" + redeemHeight +
                '}';
    }
}
