package com.anwang.types.src721;

import org.web3j.abi.Utils;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.reflection.Parameterized;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class AllowInfo extends DynamicStruct {
    public List<Address> addrs;
    public List<BigInteger> amounts;

    public AllowInfo(List<Address> addrs, List<BigInteger> amounts) {
        super(new DynamicArray<>(Address.class, addrs),
                new DynamicArray<>(Uint256.class, Utils.typeMap(amounts, Uint256.class)));
        this.addrs = addrs;
        this.amounts = amounts;
    }

    public AllowInfo(@Parameterized(type = Address.class) DynamicArray<Address> addrs,
                         @Parameterized(type = Uint256.class) DynamicArray<Uint256> amounts) {
        super(addrs, amounts);
        this.addrs = addrs.getValue();
        this.amounts = amounts.getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "AllowInfo{" +
                "addrs=" + addrs +
                ", amounts=" + amounts +
                '}';
    }
}
