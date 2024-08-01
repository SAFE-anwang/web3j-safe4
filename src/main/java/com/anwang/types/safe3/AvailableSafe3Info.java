package com.anwang.types.safe3;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

public class AvailableSafe3Info extends DynamicStruct {
    public String safe3Addr;
    public BigInteger amount;
    public Address safe4Addr;
    public BigInteger redeemHeight;

    public AvailableSafe3Info(String safe3Addr, BigInteger amount, Address safe4Addr, BigInteger redeemHeight) {
        super(new Utf8String(safe3Addr),
                new Uint256(amount),
                safe4Addr,
                new Uint256(redeemHeight));
        this.safe3Addr = safe3Addr;
        this.amount = amount;
        this.safe4Addr = safe4Addr;
        this.redeemHeight = redeemHeight;
    }

    public AvailableSafe3Info(Utf8String safe3Addr, Uint256 amount, Address safe4Addr, Uint256 redeemHeight) {
        super(safe3Addr, amount, safe4Addr, redeemHeight);
        this.safe3Addr = safe3Addr.getValue();
        this.amount = amount.getValue();
        this.safe4Addr = safe4Addr;
        this.redeemHeight = redeemHeight.getValue();
    }

    @Override
    public String toString() {
        return "AvailableSafe3Info{" +
                "safe3Addr='" + safe3Addr + '\'' +
                ", amount=" + amount +
                ", safe4Addr=" + safe4Addr +
                ", redeemHeight=" + redeemHeight +
                '}';
    }
}
