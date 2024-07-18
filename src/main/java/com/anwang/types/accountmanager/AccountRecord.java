package com.anwang.types.accountmanager;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

public class AccountRecord extends StaticStruct {
    private BigInteger id;
    private Address addr;
    private BigInteger amount;
    private BigInteger lockDay;
    private BigInteger startHeight;
    private BigInteger unlockHeight;

    public AccountRecord() {
        this.id = BigInteger.ZERO;
        this.addr = Address.DEFAULT;
        this.amount = BigInteger.ZERO;
        this.lockDay = BigInteger.ZERO;
        this.startHeight = BigInteger.ZERO;
        this.unlockHeight = BigInteger.ZERO;
    }

    public AccountRecord(BigInteger id, Address addr, BigInteger amount, BigInteger lockDay, BigInteger startHeight, BigInteger unlockHeight) {
        super(new Uint256(id), addr, new Uint256(amount), new Uint256(lockDay), new Uint256(startHeight), new Uint256(unlockHeight));
        this.id = id;
        this.addr = addr;
        this.amount = amount;
        this.lockDay = lockDay;
        this.startHeight = startHeight;
        this.unlockHeight = unlockHeight;
    }

    public AccountRecord(Uint256 id, Address addr, Uint256 amount, Uint256 lockDay, Uint256 startHeight, Uint256 unlockHeight) {
        super(id, addr, amount, lockDay, startHeight, unlockHeight);
        this.id = id.getValue();
        this.addr = addr;
        this.amount = amount.getValue();
        this.lockDay = lockDay.getValue();
        this.startHeight = startHeight.getValue();
        this.unlockHeight = unlockHeight.getValue();
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Address getAddr() {
        return addr;
    }

    public void setAddr(Address addr) {
        this.addr = addr;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public BigInteger getLockDay() {
        return lockDay;
    }

    public void setLockDay(BigInteger lockDay) {
        this.lockDay = lockDay;
    }

    public BigInteger getStartHeight() {
        return startHeight;
    }

    public void setStartHeight(BigInteger startHeight) {
        this.startHeight = startHeight;
    }

    public BigInteger getUnlockHeight() {
        return unlockHeight;
    }

    public void setUnlockHeight(BigInteger unlockHeight) {
        this.unlockHeight = unlockHeight;
    }

    @Override
    public String toString() {
        return "AccountRecord{" +
                "id=" + id +
                ", addr=" + addr +
                ", amount=" + amount +
                ", lockDay=" + lockDay +
                ", startHeight=" + startHeight +
                ", unlockHeight=" + unlockHeight +
                '}';
    }
}
