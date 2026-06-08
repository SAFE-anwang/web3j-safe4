package com.anwang.contracts.additions;

import com.anwang.types.dapp.DAppInfo;
import com.anwang.utils.ContractUtil;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DAppManager {
    private final ContractUtil contractUtil;

    public DAppManager(Web3j web3j, long chainId) {
        if (chainId == 6666666) {
            contractUtil = new ContractUtil(web3j, chainId, "0xD932a1638C8781171f7DE5b2F39E0EdEf93c5571");
        } else {
            contractUtil = new ContractUtil(web3j, chainId, "0x5A9CDa846D12e047d87c06f633f2c4f344b33C97");
        }
    }

    public String register(String privateKey, String name, Address contractAddr, String runUrl, String gitUrl, String officialUrl, String officialEmail) throws Exception {
        Function function = new Function("register", Arrays.asList(new Utf8String(name), contractAddr, new Utf8String(runUrl), new Utf8String(gitUrl), new Utf8String(officialUrl), new Utf8String(officialEmail)), Collections.emptyList());
        return contractUtil.call(privateKey, function);
    }

    public String setName(String privateKey, BigInteger id, String name) throws Exception {
        Function function = new Function("setName", Arrays.asList(new Uint256(id), new Utf8String(name)), Collections.emptyList());
        return contractUtil.call(privateKey, function);
    }

    public String setContractAddr(String privateKey, BigInteger id, Address contractAddr) throws Exception {
        Function function = new Function("setContractAddr", Arrays.asList(new Uint256(id), contractAddr), Collections.emptyList());
        return contractUtil.call(privateKey, function);
    }

    public String setRunUrl(String privateKey, BigInteger id, String runUrl) throws Exception {
        Function function = new Function("setRunUrl", Arrays.asList(new Uint256(id), new Utf8String(runUrl)), Collections.emptyList());
        return contractUtil.call(privateKey, function);
    }

    public String setGitUrl(String privateKey, BigInteger id, String gitUrl) throws Exception {
        Function function = new Function("setGitUrl", Arrays.asList(new Uint256(id), new Utf8String(gitUrl)), Collections.emptyList());
        return contractUtil.call(privateKey, function);
    }

    public String setOfficialUrl(String privateKey, BigInteger id, String officialUrl) throws Exception {
        Function function = new Function("setOfficialUrl", Arrays.asList(new Uint256(id), new Utf8String(officialUrl)), Collections.emptyList());
        return contractUtil.call(privateKey, function);
    }

    public String setOfficialEmail(String privateKey, BigInteger id, String officialEmail) throws Exception {
        Function function = new Function("setOfficialEmail", Arrays.asList(new Uint256(id), new Utf8String(officialEmail)), Collections.emptyList());
        return contractUtil.call(privateKey, function);
    }

    public String setOfficialAccouont(String privateKey, BigInteger id, Address account) throws Exception {
        Function function = new Function("setOfficialAccouont", Arrays.asList(new Uint256(id), account), Collections.emptyList());
        return contractUtil.call(privateKey, function);
    }

    public String setKeyword(String privateKey, BigInteger id, String keyword) throws Exception {
        Function function = new Function("setKeyword", Arrays.asList(new Uint256(id), new Utf8String(keyword)), Collections.emptyList());
        return contractUtil.call(privateKey, function);
    }

    public String setLogo(String privateKey, BigInteger id, byte[] logo) throws Exception {
        if (logo.length > 128 * 1024) {
            throw new Exception("oversize logo, max: 128 KB");
        }
        Function function = new Function("setLogo", Arrays.asList(new Uint256(id), new DynamicBytes(logo)), Collections.emptyList());
        return contractUtil.call(privateKey, getLogoPayAmount(), function);
    }

    public String remove(String privateKey, BigInteger id) throws Exception {
        Function function = new Function("remove", Collections.singletonList(new Uint256(id)), Collections.emptyList());
        return contractUtil.call(privateKey, function);
    }

    public String markFraud(String privateKey, BigInteger id, Boolean flag) throws Exception {
        Function function = new Function("markFraud", Arrays.asList(new Uint256(id), new Bool(flag)), Collections.emptyList());
        return contractUtil.call(privateKey, function);
    }

    public DAppInfo getInfo(BigInteger id) throws Exception {
        Function function = new Function("getInfo", Collections.singletonList(new Uint256(id)), Collections.singletonList(new TypeReference<DAppInfo>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return (DAppInfo) someTypes.get(0);
    }

    public DAppInfo getInfoByName(String name) throws Exception {
        Function function = new Function("getInfoByName", Collections.singletonList(new Utf8String(name)), Collections.singletonList(new TypeReference<DAppInfo>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return (DAppInfo) someTypes.get(0);
    }

    public DAppInfo getInfoByContractAddr(Address contractAddr) throws Exception {
        Function function = new Function("getInfoByContractAddr", Collections.singletonList(contractAddr), Collections.singletonList(new TypeReference<DAppInfo>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return (DAppInfo) someTypes.get(0);
    }

    public DAppInfo getInfoByRunUrl(String runUrl) throws Exception {
        Function function = new Function("getInfoByRunUrl", Collections.singletonList(new Utf8String(runUrl)), Collections.singletonList(new TypeReference<DAppInfo>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return (DAppInfo) someTypes.get(0);
    }

    public byte[] getLogo(BigInteger id) throws Exception {
        Function function = new Function("getLogo", Collections.singletonList(new Uint256(id)), Collections.singletonList(new TypeReference<DynamicBytes>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((DynamicBytes) someTypes.get(0)).getValue();
    }

    public BigInteger getNum() throws Exception {
        Function function = new Function("getNum", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<BigInteger> getIDs(BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getIDs", Arrays.asList(new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Uint256>>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((DynamicArray<Uint256>) someTypes.get(0)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public BigInteger getMineNum(Address account) throws Exception {
        Function function = new Function("getMineNum", Collections.singletonList(account), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<BigInteger> getMineIDs(Address account, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getMineIDs", Arrays.asList(account, new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Uint256>>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((DynamicArray<Uint256>) someTypes.get(0)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public Boolean existID(BigInteger id) throws Exception {
        Function function = new Function("existID", Collections.singletonList(new Uint256(id)), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public Boolean existName(String name) throws Exception {
        Function function = new Function("existName", Collections.singletonList(new Utf8String(name)), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public Boolean existContractAddr(Address contractAddr) throws Exception {
        Function function = new Function("existContractAddr", Collections.singletonList(contractAddr), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public Boolean existRunUrl(String runUrl) throws Exception {
        Function function = new Function("existRunUrl", Collections.singletonList(new Utf8String(runUrl)), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public Boolean isMarkedFraud(Address account, BigInteger id) throws Exception {
        Function function = new Function("isMarkedFraud", Arrays.asList(account, new Uint256(id)), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public Boolean isFrozen(BigInteger id) throws Exception {
        Function function = new Function("isFrozen", Collections.singletonList(new Uint256(id)), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public BigInteger getLogoPayAmount() throws Exception {
        Function function = new Function("getLogoPayAmount", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public Address getLogoPayAddress() throws Exception {
        Function function = new Function("getLogoPayAddress", Collections.emptyList(), Collections.singletonList(new TypeReference<Address>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return (Address) someTypes.get(0);
    }
}
