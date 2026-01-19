package com.anwang.types.snvote;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

public class VoteRecord extends StaticStruct {
    public Address voterAddr;
    public Address dstAddr;
    public BigInteger amount;
    public BigInteger num;
    public BigInteger height;

    public VoteRecord(Address voterAddr, Address dstAddr, BigInteger amount, BigInteger num, BigInteger height) {
        super(voterAddr, dstAddr, new Uint256(amount), new Uint256(num), new Uint256(height));
        this.voterAddr = voterAddr;
        this.dstAddr = dstAddr;
        this.amount = amount;
        this.num = num;
        this.height = height;
    }

    public VoteRecord(Address voterAddr,
                      Address dstAddr,
                      Uint256 amount,
                      Uint256 num,
                      Uint256 height) {
        super(voterAddr, dstAddr, amount, num, height);
        this.voterAddr = voterAddr;
        this.dstAddr = dstAddr;
        this.amount = amount.getValue();
        this.num = num.getValue();
        this.height = height.getValue();
    }

    @Override
    public String toString() {
        return "VoteRecord{" +
                "voterAddr=" + voterAddr +
                ", dstAddr=" + dstAddr +
                ", amount=" + amount +
                ", num=" + num +
                ", height=" + height +
                '}';
    }
}
