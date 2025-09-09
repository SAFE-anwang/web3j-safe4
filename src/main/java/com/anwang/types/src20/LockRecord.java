package com.anwang.types.src20;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

public class LockRecord extends StaticStruct {
    public BigInteger id;
    public Address addr;
    public BigInteger amount;
    public BigInteger lockDay;
    public BigInteger startHeight;
    public BigInteger unlockHeight;

    public LockRecord(BigInteger id, Address addr, BigInteger amount, BigInteger lockDay, BigInteger startHeight, BigInteger unlockHeight) {
        super(new Uint256(id), addr, new Uint256(amount), new Uint256(lockDay), new Uint256(startHeight), new Uint256(unlockHeight));
        this.id = id;
        this.addr = addr;
        this.amount = amount;
        this.lockDay = lockDay;
        this.startHeight = startHeight;
        this.unlockHeight = unlockHeight;
    }

    public LockRecord(Uint256 id, Address addr, Uint256 amount, Uint256 lockDay, Uint256 startHeight, Uint256 unlockHeight) {
        super(id, addr, amount, lockDay, startHeight, unlockHeight);
        this.id = id.getValue();
        this.addr = addr;
        this.amount = amount.getValue();
        this.lockDay = lockDay.getValue();
        this.startHeight = startHeight.getValue();
        this.unlockHeight = unlockHeight.getValue();
    }

    @Override
    public String toString() {
        return "LockRecord{" +
                "id=" + id +
                ", addr=" + addr +
                ", amount=" + amount +
                ", lockDay=" + lockDay +
                ", startHeight=" + startHeight +
                ", unlockHeight=" + unlockHeight +
                '}';
    }
}
