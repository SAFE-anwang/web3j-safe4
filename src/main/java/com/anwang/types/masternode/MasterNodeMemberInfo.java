package com.anwang.types.masternode;

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
public class MasterNodeMemberInfo extends StaticStruct {
    private BigInteger lockID;
    private Address addr;
    private BigInteger amount;
    private BigInteger height;

    public MasterNodeMemberInfo() {
        this.lockID = BigInteger.ZERO;
        this.addr = Address.DEFAULT;
        this.amount = BigInteger.ZERO;
        this.height = BigInteger.ZERO;
    }

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
}
