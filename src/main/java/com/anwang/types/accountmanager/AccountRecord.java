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
}
