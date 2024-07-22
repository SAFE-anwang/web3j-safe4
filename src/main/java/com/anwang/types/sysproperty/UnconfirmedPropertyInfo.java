package com.anwang.types.sysproperty;

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

public class UnconfirmedPropertyInfo extends DynamicStruct {
    private String name;
    private BigInteger val;
    private Address applicant;
    private List<Address> voters;
    private List<BigInteger> voteResults;
    private String reason;
    private BigInteger applyHeight;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getV() {
        return val;
    }

    // can't be setVal, otherwise panic
    public void setV(BigInteger val) {
        this.val = val;
    }

    public Address getApplicant() {
        return applicant;
    }

    public void setApplicant(Address applicant) {
        this.applicant = applicant;
    }

    public List<Address> getVoters() {
        return voters;
    }

    public void setVoters(List<Address> voters) {
        this.voters = voters;
    }

    public List<BigInteger> getVoteResults() {
        return voteResults;
    }

    public void setVoteResults(List<BigInteger> voteResults) {
        this.voteResults = voteResults;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public BigInteger getApplyHeight() {
        return applyHeight;
    }

    public void setApplyHeight(BigInteger applyHeight) {
        this.applyHeight = applyHeight;
    }

    @Override
    public String toString() {
        return "UnconfirmedPropertyInfo{" +
                "name='" + name + '\'' +
                ", val=" + val +
                ", applicant=" + applicant +
                ", voters=" + voters +
                ", voteResults=" + voteResults +
                ", reason='" + reason + '\'' +
                ", applyHeight=" + applyHeight +
                '}';
    }
}
