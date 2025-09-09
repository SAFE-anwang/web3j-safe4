package com.anwang.src20;

import com.anwang.types.src20.LockRecord;
import com.anwang.utils.ContractUtil;
import org.web3j.abi.TypeReference;
import org.web3j.abi.Utils;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SRC20LockFactory {
    private final ContractUtil contractUtil;

    public SRC20LockFactory(Web3j web3j, long chainId, String contractAddr) {
        contractUtil = new ContractUtil(web3j, chainId, contractAddr);
    }

    public String lock(String privateKey, Address token, Address to, BigInteger amount, BigInteger lockDay) throws Exception {
        Function function = new Function("lock", Arrays.asList(token, to, new Uint256(amount), new Uint256(lockDay)), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        return contractUtil.call(privateKey, function);
    }

    public String batchLock(String privateKey, Address token, Address to, BigInteger amount, BigInteger times, BigInteger spaceDay, BigInteger startDay) throws Exception {
        Function function = new Function("batchLock", Arrays.asList(token, to, new Uint256(amount), new Uint256(times), new Uint256(spaceDay), new Uint256(startDay)), Collections.singletonList(new TypeReference<DynamicArray<Uint256>>() {
        }));
        return contractUtil.call(privateKey, function);
    }

    public String withdrawByID(String privateKey, Address token, List<BigInteger> ids) throws Exception {
        Function function = new Function("withdrawByID", Arrays.asList(token, new DynamicArray<>(Uint256.class, Utils.typeMap(ids, Uint256.class))), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        return contractUtil.call(privateKey, function);
    }

    public BigInteger getTotalIDNum(Address token, Address addr) throws Exception {
        Function function = new Function("getTotalIDNum", Arrays.asList(token, addr), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<BigInteger> getTotalIDs(Address token, Address addr, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getTotalIDs", Arrays.asList(token, addr, new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Uint256>>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((DynamicArray<Uint256>) someTypes.get(0)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public BigInteger getAvailableIDNum(Address token, Address addr) throws Exception {
        Function function = new Function("getAvailableIDNum", Arrays.asList(token, addr), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<BigInteger> getAvailableIDs(Address token, Address addr, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getAvailableIDs", Arrays.asList(token, addr, new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Uint256>>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((DynamicArray<Uint256>) someTypes.get(0)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public BigInteger getLockedIDNum(Address token, Address addr) throws Exception {
        Function function = new Function("getLockedIDNum", Arrays.asList(token, addr), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<BigInteger> getLockedIDs(Address token, Address addr, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getLockedIDs", Arrays.asList(token, addr, new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Uint256>>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((DynamicArray<Uint256>) someTypes.get(0)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public LockRecord getRecordByID(Address token, BigInteger id) throws Exception {
        Function function = new Function("getRecordByID", Arrays.asList(token, new Uint256(id)), Collections.singletonList(new TypeReference<LockRecord>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return (LockRecord) someTypes.get(0);
    }

    public Address getLock(Address token) throws Exception {
        Function function = new Function("getLock", Collections.singletonList(token), Collections.singletonList(new TypeReference<Address>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return (Address) someTypes.get(0);
    }

    public Address getToken(Address lock) throws Exception {
        Function function = new Function("getToken", Collections.singletonList(lock), Collections.singletonList(new TypeReference<Address>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return (Address) someTypes.get(0);
    }
}
