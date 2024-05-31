package com.anwang.types.accountmanager;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

@Getter
@Setter
@ToString
public class AccountAmountInfo extends StaticStruct {
    private BigInteger amount;
    private BigInteger num;

    public AccountAmountInfo() {
        this.amount = BigInteger.ZERO;
        this.num = BigInteger.ZERO;
    }

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
}
