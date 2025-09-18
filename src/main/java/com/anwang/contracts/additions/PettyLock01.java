package com.anwang.contracts.additions;

import com.anwang.types.accountmanager.AccountAmountInfo;
import com.anwang.types.accountmanager.AccountRecord;
import com.anwang.utils.ContractUtil;
import org.web3j.abi.TypeReference;
import org.web3j.abi.Utils;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PettyLock01 {
    private final ContractUtil contractUtil;

    public PettyLock01(Web3j web3j, long chainId) {
        if (chainId == 6666666) {
            contractUtil = new ContractUtil(web3j, chainId, "0xF6A2C019beF11825E73ed219c7b0582324dE91b2");
        } else {
            contractUtil = new ContractUtil(web3j, chainId, "0x5A9CDa846D12e047d87c06f633f2c4f344b33C97");
        }
    }

    public String batchDeposit4One(String privateKey, BigInteger value, Address to, BigInteger times, BigInteger spaceDay, BigInteger startDay) throws Exception {
        Function function = new Function("batchDeposit4One", Arrays.asList(to, new Uint256(times), new Uint256(spaceDay), new Uint(startDay)), Collections.singletonList(new TypeReference<DynamicArray<Uint256>>() {
        }));
        return contractUtil.call(privateKey, value, function);
    }

    public String withdrawByID(String privateKey, List<BigInteger> ids) throws Exception {
        Function function = new Function("withdrawByID", Collections.singletonList(new DynamicArray<>(Uint256.class, Utils.typeMap(ids, Uint256.class))), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        return contractUtil.call(privateKey, function);
    }

    public AccountAmountInfo getTotalAmount(Address addr) throws Exception {
        Function function = new Function("getTotalAmount", Collections.singletonList(addr), Collections.singletonList(new TypeReference<AccountAmountInfo>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return (AccountAmountInfo) someTypes.get(0);
    }

    public List<BigInteger> getTotalIDs(Address addr, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getTotalIDs", Arrays.asList(addr, new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Uint256>>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((DynamicArray<Uint256>) someTypes.get(0)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public AccountAmountInfo getAvailableAmount(Address addr) throws Exception {
        Function function = new Function("getAvailableAmount", Collections.singletonList(addr), Collections.singletonList(new TypeReference<AccountAmountInfo>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return (AccountAmountInfo) someTypes.get(0);
    }

    public List<BigInteger> getAvailableIDs(Address addr, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getAvailableIDs", Arrays.asList(addr, new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Uint256>>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((DynamicArray<Uint256>) someTypes.get(0)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public AccountAmountInfo getLockedAmount(Address addr) throws Exception {
        Function function = new Function("getLockedAmount", Collections.singletonList(addr), Collections.singletonList(new TypeReference<AccountAmountInfo>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return (AccountAmountInfo) someTypes.get(0);
    }

    public List<BigInteger> getLockedIDs(Address addr, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getLockedIDs", Arrays.asList(addr, new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Uint256>>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((DynamicArray<Uint256>) someTypes.get(0)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public AccountRecord getRecordByID(BigInteger id) throws Exception {
        Function function = new Function("getRecordByID", Collections.singletonList(new Uint256(id)), Collections.singletonList(new TypeReference<AccountRecord>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return (AccountRecord) someTypes.get(0);
    }
}
