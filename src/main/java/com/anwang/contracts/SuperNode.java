package com.anwang.contracts;

import com.anwang.types.supernode.SuperNodeInfo;
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

public class SuperNode {
    private final SuperNodeLogic logicContract;
    private final SuperNodeStorage storageContract;

    public SuperNode(Web3j web3j, long chainId) {
        this.logicContract = new SuperNodeLogic(web3j, chainId);
        this.storageContract = new SuperNodeStorage(web3j, chainId);
    }

    public String register(String privateKey, BigInteger value, Boolean isUnion, Address addr, BigInteger lockDay, String name, String enode, String description, BigInteger creatorIncentive, BigInteger partnerIncentive, BigInteger voterIncentive) throws Exception {
        Function function = new Function("register",
                Arrays.asList(new Bool(isUnion), addr, new Uint256(lockDay), new Utf8String(name), new Utf8String(enode), new Utf8String(description), new Uint256(creatorIncentive), new Uint256(partnerIncentive), new Uint256(voterIncentive)),
                Collections.emptyList());
        return logicContract.contractUtil.call(privateKey, value, function);
    }

    public String appendRegister(String privateKey, BigInteger value, Address addr, BigInteger lockDay) throws Exception {
        Function function = new Function("appendRegister", Arrays.asList(addr, new Uint256(lockDay)), Collections.emptyList());
        return logicContract.contractUtil.call(privateKey, value, function);
    }

    public String turnRegister(String privateKey, Address addr, BigInteger lockID) throws Exception {
        Function function = new Function("turnRegister", Arrays.asList(addr, new Uint256(lockID)), Collections.emptyList());
        return logicContract.contractUtil.call(privateKey, function);
    }

    public String changeAddress(String privateKey, Address addr, Address newAddr) throws Exception {
        Function function = new Function("changeAddress", Arrays.asList(addr, newAddr), Collections.emptyList());
        return logicContract.contractUtil.call(privateKey, function);
    }

    public String changeName(String privateKey, Address addr, String name) throws Exception {
        Function function = new Function("changeName", Arrays.asList(addr, new Utf8String(name)), Collections.emptyList());
        return logicContract.contractUtil.call(privateKey, function);
    }

    public String changeNameByID(String privateKey, BigInteger id, String name) throws Exception {
        Function function = new Function("changeNameByID", Arrays.asList(new Uint256(id), new Utf8String(name)), Collections.emptyList());
        return logicContract.contractUtil.call(privateKey, function);
    }

    public String changeEnode(String privateKey, Address addr, String enode) throws Exception {
        Function function = new Function("changeEnode", Arrays.asList(addr, new Utf8String(enode)), Collections.emptyList());
        return logicContract.contractUtil.call(privateKey, function);
    }

    public String changeEnodeByID(String privateKey, BigInteger id, String enode) throws Exception {
        Function function = new Function("changeEnodeByID", Arrays.asList(new Uint256(id), new Utf8String(enode)), Collections.emptyList());
        return logicContract.contractUtil.call(privateKey, function);
    }

    public String changeDescription(String privateKey, Address addr, String description) throws Exception {
        Function function = new Function("changeDescription", Arrays.asList(addr, new Utf8String(description)), Collections.emptyList());
        return logicContract.contractUtil.call(privateKey, function);
    }

    public String changeDescriptionByID(String privateKey, BigInteger id, String description) throws Exception {
        Function function = new Function("changeDescriptionByID", Arrays.asList(new Uint256(id), new Utf8String(description)), Collections.emptyList());
        return logicContract.contractUtil.call(privateKey, function);
    }

    public String changeIncentivePlan(String privateKey, BigInteger id, BigInteger creatorIncentive, BigInteger partnerIncentive, BigInteger voterIncentive) throws Exception {
        Function function = new Function("changeIncentivePlan", Arrays.asList(new Uint256(id), new Uint256(creatorIncentive), new Uint256(partnerIncentive), new Uint256(voterIncentive)), Collections.emptyList());
        return logicContract.contractUtil.call(privateKey, function);
    }

    public SuperNodeInfo getInfo(Address addr) throws Exception {
        Function function = new Function("getInfo", Collections.singletonList(addr), Collections.singletonList(new TypeReference<SuperNodeInfo>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return (SuperNodeInfo) someTypes.get(0);
    }

    public SuperNodeInfo getInfoByID(BigInteger id) throws Exception {
        Function function = new Function("getInfoByID", Collections.singletonList(new Uint256(id)), Collections.singletonList(new TypeReference<SuperNodeInfo>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return (SuperNodeInfo) someTypes.get(0);
    }

    public BigInteger getDisableHeight(BigInteger id) throws Exception {
        Function function = new Function("getDisableHeight", Collections.singletonList(new Uint256(id)), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public BigInteger getNum() throws Exception {
        Function function = new Function("getNum", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<Address> getAll(BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getAll", Arrays.asList(new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Address>>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return ((DynamicArray<Address>) someTypes.get(0)).getValue();
    }

    public BigInteger getAddrNum4Creator(Address creator) throws Exception {
        Function function = new Function("getAddrNum4Creator", Collections.singletonList(creator), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<Address> getAddrs4Creator(Address creator, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getAddrs4Creator", Arrays.asList(creator, new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Address>>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return ((DynamicArray<Address>) someTypes.get(0)).getValue();
    }

    public BigInteger getAddrNum4Partner(Address partner) throws Exception {
        Function function = new Function("getAddrNum4Partner", Collections.singletonList(partner), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<Address> getAddrs4Partner(Address partner, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getAddrs4Partner", Arrays.asList(partner, new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Address>>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return ((DynamicArray<Address>) someTypes.get(0)).getValue();
    }

    public List<Address> getTops() throws Exception {
        Function function = new Function("getTops", Collections.emptyList(), Collections.singletonList(new TypeReference<DynamicArray<Address>>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return ((DynamicArray<Address>) someTypes.get(0)).getValue();
    }

    public List<Address> getTops4Creator(Address creator) throws Exception {
        Function function = new Function("getTops4Creator", Collections.singletonList(creator), Collections.singletonList(new TypeReference<DynamicArray<Address>>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return ((DynamicArray<Address>) someTypes.get(0)).getValue();
    }

    public List<Address> getOfficials() throws Exception {
        Function function = new Function("getOfficials", Collections.emptyList(), Collections.singletonList(new TypeReference<DynamicArray<Address>>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return ((DynamicArray<Address>) someTypes.get(0)).getValue();
    }

    public Boolean exist(Address addr) throws Exception {
        Function function = new Function("exist", Collections.singletonList(addr), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public Boolean existID(BigInteger id) throws Exception {
        Function function = new Function("existID", Collections.singletonList(new Uint256(id)), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public Boolean existName(String name) throws Exception {
        Function function = new Function("existName", Collections.singletonList(new Utf8String(name)), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public Boolean existEnode(String enode) throws Exception {
        Function function = new Function("existEnode", Collections.singletonList(new Utf8String(enode)), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public Boolean existLockID(Address addr, BigInteger lockID) throws Exception {
        Function function = new Function("existLockID", Arrays.asList(addr, new Uint256(lockID)), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public Boolean existFounder(Address founder) throws Exception {
        Function function = new Function("existFounder", Collections.singletonList(founder), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public Boolean isValid(Address addr) throws Exception {
        Function function = new Function("isValid", Collections.singletonList(addr), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public Boolean isFormal(Address addr) throws Exception {
        Function function = new Function("isFormal", Collections.singletonList(addr), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public Boolean isUnion(Address addr) throws Exception {
        Function function = new Function("isUnion", Collections.singletonList(addr), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public Boolean existNodeAddress(Address addr) throws Exception {
        Function function = new Function("existNodeAddress", Collections.singletonList(addr), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public Boolean existNodeEnode(String enode) throws Exception {
        Function function = new Function("existNodeEnode", Collections.singletonList(new Utf8String(enode)), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public Boolean existNodeFounder(Address addr) throws Exception {
        Function function = new Function("existNodeFounder", Collections.singletonList(addr), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = storageContract.contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    private class SuperNodeLogic {
        private final ContractUtil contractUtil;

        public SuperNodeLogic(Web3j web3j, long chainId) {
            contractUtil = new ContractUtil(web3j, chainId, Safe4Contract.SuperNodeLogicContractAddr);
        }
    }

    private class SuperNodeStorage {
        private final ContractUtil contractUtil;

        public SuperNodeStorage(Web3j web3j, long chainId) {
            contractUtil = new ContractUtil(web3j, chainId, Safe4Contract.SuperNodeStorageContractAddr);
        }
    }
}
