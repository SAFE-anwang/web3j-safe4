package com.anwang.contracts;

import com.anwang.types.accountmanager.AccountAmountInfo;
import com.anwang.types.accountmanager.AccountRecord;
import com.anwang.types.accountmanager.RecordUseInfo;
import com.anwang.utils.Safe4Contract;
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

public class AccountManager extends AbstractContract {

    public AccountManager(Web3j web3j, long chainId) {
        super(web3j, chainId, Safe4Contract.AccountManagerContractAddr);
    }

    public String deposit(String privateKey, BigInteger value, Address to, BigInteger lockDay) throws Exception {
        Function function = new Function("deposit", Arrays.asList(to, new Uint256(lockDay)), Collections.singletonList(new TypeReference<Uint256>() {}));
        return call(privateKey, value, function);
    }

    public String batchDeposit4One(String privateKey, BigInteger value, Address to, BigInteger times, BigInteger spaceDay, BigInteger startDay) throws Exception {
        Function function = new Function("batchDeposit4One", Arrays.asList(to, new Uint256(times), new Uint256(spaceDay), new Uint(startDay)), Collections.singletonList(new TypeReference<DynamicArray<Uint256>>() {}));
        return call(privateKey, value, function);
    }

    public String batchDeposit4Multi(String privateKey, BigInteger value, List<Address> addrs, BigInteger times, BigInteger spaceDay, BigInteger startDay) throws Exception {
        Function function = new Function("batchDeposit4Multi", Arrays.asList(new DynamicArray<>(Address.class, addrs), new Uint256(times), new Uint256(spaceDay), new Uint256(startDay)), Collections.singletonList(new TypeReference<DynamicArray<Uint256>>() {}));
        return call(privateKey, value, function);
    }

    public String withdraw(String privateKey) throws Exception {
        Function function = new Function("withdraw", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {}));
        return call(privateKey, function);
    }

    public String withdrawByID(String privateKey, List<BigInteger> ids) throws Exception {
        Function function = new Function("withdrawByID", Collections.singletonList(new DynamicArray<>(Uint256.class, Utils.typeMap(ids, Uint256.class))), Collections.singletonList(new TypeReference<Uint256>() {}));
        return call(privateKey, function);
    }

    public String transfer(String privateKey, Address to, BigInteger amount, BigInteger lockDay) throws Exception {
        Function function = new Function("transfer", Arrays.asList(to, new Uint256(amount), new Uint256(lockDay)), Collections.singletonList(new TypeReference<Uint256>() {}));
        return call(privateKey, function);
    }

    public String addLockDay(String privateKey, BigInteger id, BigInteger day) throws Exception {
        Function function = new Function("addLockDay", Arrays.asList(new Uint256(id), new Uint256(day)), Collections.emptyList());
        return call(privateKey, function);
    }

    public BigInteger getImmatureAmount(Address addr) throws Exception {
        Function function = new Function("getImmatureAmount", Collections.singletonList(addr), Collections.singletonList(new TypeReference<Uint256>() {}));
        List<Type> someTypes = query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public AccountAmountInfo getTotalAmount(Address addr) throws Exception {
        Function function = new Function("getTotalAmount", Collections.singletonList(addr), Collections.singletonList(new TypeReference<AccountAmountInfo>() {}));
        List<Type> someTypes = query(function);
        return (AccountAmountInfo) someTypes.get(0);
    }

    public List<BigInteger> getTotalIDs(Address addr, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getTotalIDs", Arrays.asList(addr, new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Uint256>>() {}));
        List<Type> someTypes = query(function);
        return ((DynamicArray<Uint256>) someTypes.get(0)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public AccountAmountInfo getAvailableAmount(Address addr) throws Exception {
        Function function = new Function("getAvailableAmount", Collections.singletonList(addr), Collections.singletonList(new TypeReference<AccountAmountInfo>() {}));
        List<Type> someTypes = query(function);
        return (AccountAmountInfo) someTypes.get(0);
    }

    public List<BigInteger> getAvailableIDs(Address addr, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getAvailableIDs", Arrays.asList(addr, new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Uint256>>() {}));
        List<Type> someTypes = query(function);
        return ((DynamicArray<Uint256>) someTypes.get(0)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public AccountAmountInfo getLockedAmount(Address addr) throws Exception {
        Function function = new Function("getLockedAmount", Collections.singletonList(addr), Collections.singletonList(new TypeReference<AccountAmountInfo>() {}));
        List<Type> someTypes = query(function);
        return (AccountAmountInfo) someTypes.get(0);
    }

    public List<BigInteger> getLockedIDs(Address addr, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getLockedIDs", Arrays.asList(addr, new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Uint256>>() {}));
        List<Type> someTypes = query(function);
        return ((DynamicArray<Uint256>) someTypes.get(0)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public AccountAmountInfo getUsedAmount(Address addr) throws Exception {
        Function function = new Function("getUsedAmount", Collections.singletonList(addr), Collections.singletonList(new TypeReference<AccountAmountInfo>() {}));
        List<Type> someTypes = query(function);
        return (AccountAmountInfo) someTypes.get(0);
    }

    public List<BigInteger> getUsedIDs(Address addr, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getUsedIDs", Arrays.asList(addr, new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Uint256>>() {}));
        List<Type> someTypes = query(function);
        return ((DynamicArray<Uint256>) someTypes.get(0)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public AccountRecord getRecord0(Address addr) throws Exception {
        Function function = new Function("getRecord0", Collections.singletonList(addr), Collections.singletonList(new TypeReference<AccountRecord>() {}));
        List<Type> someTypes = query(function);
        return (AccountRecord) someTypes.get(0);
    }

    public AccountRecord getRecordByID(BigInteger id) throws Exception {
        Function function = new Function("getRecordByID", Collections.singletonList(new Uint256(id)), Collections.singletonList(new TypeReference<AccountRecord>() {}));
        List<Type> someTypes = query(function);
        return (AccountRecord) someTypes.get(0);
    }

    public RecordUseInfo getRecordUseInfo(BigInteger id) throws Exception {
        Function function = new Function("getRecordUseInfo", Collections.singletonList(new Uint256(id)), Collections.singletonList(new TypeReference<RecordUseInfo>() {}));
        List<Type> someTypes = query(function);
        return (RecordUseInfo) someTypes.get(0);
    }
}
