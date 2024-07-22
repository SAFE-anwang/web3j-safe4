package com.anwang.types.masternode;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

public class MasterNodeMemberInfo extends StaticStruct {
    private BigInteger lockID;
    private Address addr;
    private BigInteger amount;
    private BigInteger height;

    public MasterNodeMemberInfo(BigInteger lockID, Address addr, BigInteger amount, BigInteger height) {
        super(new Uint256(lockID), addr, new Uint256(amount), new Uint256(height));
        this.lockID = lockID;
        this.addr = addr;
        this.amount = amount;
        this.height = height;
    }

    public MasterNodeMemberInfo(Uint256 lockID, Address addr, Uint256 amount, Uint256 height) {
        super(lockID, addr, amount, height);
        this.lockID = lockID.getValue();
        this.addr = addr;
        this.amount = amount.getValue();
        this.height = height.getValue();
    }

    public BigInteger getLockID() {
        return lockID;
    }

    public void setLockID(BigInteger lockID) {
        this.lockID = lockID;
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

    public BigInteger getHeight() {
        return height;
    }

    public void setHeight(BigInteger height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "MasterNodeMemberInfo{" +
                "lockID=" + lockID +
                ", addr=" + addr +
                ", amount=" + amount +
                ", height=" + height +
                '}';
    }
}
