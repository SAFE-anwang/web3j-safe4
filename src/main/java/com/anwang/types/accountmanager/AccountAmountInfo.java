package com.anwang.types.accountmanager;

import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

public class AccountAmountInfo extends StaticStruct {
    public BigInteger amount;
    public BigInteger num;

    public AccountAmountInfo(BigInteger amount, BigInteger num) {
        super(new Uint256(amount), new Uint256(num));
        this.amount = amount;
        this.num = num;
    }

    public AccountAmountInfo(Uint256 amount, Uint256 num) {
        super(amount, num);
        this.amount = amount.getValue();
        this.num = num.getValue();
    }

    @Override
    public String toString() {
        return "AccountAmountInfo{" +
                "amount=" + amount +
                ", num=" + num +
                '}';
    }
}
