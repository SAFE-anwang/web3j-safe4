package com.anwang.types.sysproperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.web3j.abi.Utils;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.reflection.Parameterized;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class UnconfirmedPropertyInfo extends DynamicStruct {
    private String name;
    private BigInteger val;
    private Address applicant;
    private List<Address> voters;
    private List<BigInteger> voteResults;
    private String reason;
    private BigInteger applyHeight;

    public UnconfirmedPropertyInfo() {
        this.name = "";
        this.val = BigInteger.ZERO;
        this.applicant = Address.DEFAULT;
        this.voters = new ArrayList<>();
        this.voteResults = new ArrayList<>();
        this.reason = "";
        this.applyHeight = BigInteger.ZERO;
    }

    public UnconfirmedPropertyInfo(String name, BigInteger val, Address applicant, List<Address> voters, List<BigInteger> voteResults, String reason, BigInteger applyHeight) {
        super(new Utf8String(name),
                new Uint256(val),
                applicant,
                new DynamicArray<>(Address.class, voters),
                new DynamicArray<>(Uint256.class, Utils.typeMap(voteResults, Uint256.class)),
                new Utf8String(reason),
                new Uint256(applyHeight));
        this.name = name;
        this.val = val;
        this.applicant = applicant;
        this.voters = voters;
        this.voteResults = voteResults;
        this.reason = reason;
        this.applyHeight = applyHeight;
    }

    public UnconfirmedPropertyInfo(@Parameterized(type = Utf8String.class) Utf8String name,
                                   @Parameterized(type = Uint256.class) Uint256 val,
                                   @Parameterized(type = Address.class) Address applicant,
                                   @Parameterized(type = Address.class) DynamicArray<Address> voters,
                                   @Parameterized(type = Uint256.class) DynamicArray<Uint256> voteResults,
                                   @Parameterized(type = Utf8String.class) Utf8String reason,
                                   @Parameterized(type = Uint256.class) Uint256 applyHeight) {
        super(name, val, applicant, voters, voteResults, reason, applyHeight);
        this.name = name.getValue();
        this.val = val.getValue();
        this.applicant = applicant;
        this.voters = voters.getValue();
        this.voteResults = voteResults.getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
        this.reason = reason.getValue();
        this.applyHeight = applyHeight.getValue();
    }
}
