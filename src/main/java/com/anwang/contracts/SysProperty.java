package com.anwang.contracts;

import com.anwang.types.sysproperty.PropertyInfo;
import com.anwang.types.sysproperty.UnconfirmedPropertyInfo;
import com.anwang.utils.ContractUtil;
import com.anwang.utils.Safe4Contract;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SysProperty {
    private final ContractUtil contractUtil;

    public SysProperty(Web3j web3j, long chainId) {
        contractUtil = new ContractUtil(web3j, chainId, Safe4Contract.PropertyContractAddr);
    }

    public String applyUpdate(String privateKey, String name, BigInteger val, String reason) throws Exception {
        Function function = new Function("applyUpdate", Arrays.asList(new Utf8String(name), new Uint256(val), new Utf8String(reason)), Collections.emptyList());
        return contractUtil.call(privateKey, function);
    }

    public String vote4Update(String privateKey, String name, BigInteger voteResult) throws Exception {
        Function function = new Function("vote4Update", Arrays.asList(new Utf8String(name), new Uint256(voteResult)), Collections.emptyList());
        return contractUtil.call(privateKey, function);
    }

    public PropertyInfo getInfo(String name) throws Exception {
        Function function = new Function("getInfo", Collections.singletonList(new Utf8String(name)), Collections.singletonList(new TypeReference<PropertyInfo>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return (PropertyInfo) someTypes.get(0);
    }

    public UnconfirmedPropertyInfo getUnconfirmedInfo(String name) throws Exception {
        Function function = new Function("getUnconfirmedInfo", Collections.singletonList(new Utf8String(name)), Collections.singletonList(new TypeReference<UnconfirmedPropertyInfo>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return (UnconfirmedPropertyInfo) someTypes.get(0);
    }

    public BigInteger getValue(String name) throws Exception {
        Function function = new Function("getValue", Collections.singletonList(new Utf8String(name)), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public BigInteger getNum() throws Exception {
        Function function = new Function("getNum", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<String> getAll(BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getAll", Arrays.asList(new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Utf8String>>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((DynamicArray<Utf8String>) someTypes.get(0)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public BigInteger getUnconfirmedNum() throws Exception {
        Function function = new Function("getUnconfirmedNum", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<String> getAllUnconfirmed(BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getAllUnconfirmed", Arrays.asList(new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Utf8String>>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((DynamicArray<Utf8String>) someTypes.get(0)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public Boolean exist(String name) throws Exception {
        Function function = new Function("exist", Collections.singletonList(new Utf8String(name)), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public Boolean existUnconfirmed(String name) throws Exception {
        Function function = new Function("existUnconfirmed", Collections.singletonList(new Utf8String(name)), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }
}
