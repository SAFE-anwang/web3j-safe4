package com.anwang.types.snvote;

import org.web3j.abi.Utils;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.reflection.Parameterized;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class SNVoteRetInfo extends DynamicStruct {
    private List<Address> addrs;
    private List<BigInteger> voteNums;

    public SNVoteRetInfo(List<Address> addrs, List<BigInteger> voteNums) {
        super(new DynamicArray<>(Address.class, addrs),
                new DynamicArray<>(Uint256.class, Utils.typeMap(voteNums, Uint256.class)));
        this.addrs = addrs;
        this.voteNums = voteNums;
    }

    public SNVoteRetInfo(@Parameterized(type = Address.class) DynamicArray<Address> addrs,
                         @Parameterized(type = Uint256.class) DynamicArray<Uint256> voteNums) {
        super(addrs, voteNums);
        this.addrs = addrs.getValue();
        this.voteNums = voteNums.getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public List<Address> getAddrs() {
        return addrs;
    }

    public void setAddrs(List<Address> addrs) {
        this.addrs = addrs;
    }

    public List<BigInteger> getVoteNums() {
        return voteNums;
    }

    public void setVoteNums(List<BigInteger> voteNums) {
        this.voteNums = voteNums;
    }

    @Override
    public String toString() {
        return "SNVoteRetInfo{" +
                "addrs=" + addrs +
                ", voteNums=" + voteNums +
                '}';
    }
}
