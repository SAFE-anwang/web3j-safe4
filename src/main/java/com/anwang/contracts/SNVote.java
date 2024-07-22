package com.anwang.contracts;

import com.anwang.types.snvote.SNVoteRetInfo;
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

public class SNVote extends AbstractContract {

    public SNVote(Web3j web3j, long chainId) {
        super(web3j, chainId, Safe4Contract.SNVoteContractAddr);
    }

    public String voteOrApproval(String privateKey, Boolean isVote, Address dstAddr, List<BigInteger> recordIDs) throws Exception {
        Function function = new Function("voteOrApproval", Arrays.asList(new Bool(isVote), dstAddr, new DynamicArray<>(Uint256.class, Utils.typeMap(recordIDs, Uint256.class))), Collections.emptyList());
        return call(privateKey, function);
    }

    public String voteOrApprovalWithAmount(String privateKey, BigInteger value, Boolean isVote, Address dstAddr) throws Exception {
        Function function = new Function("voteOrApprovalWithAmount", Arrays.asList(new Bool(isVote), dstAddr), Collections.emptyList());
        return call(privateKey, value, function);
    }

    public String removeVoteOrApproval(String privateKey, List<BigInteger> recordIDs) throws Exception {
        Function function = new Function("removeVoteOrApproval", Collections.singletonList(new DynamicArray<>(Uint256.class, Utils.typeMap(recordIDs, Uint256.class))), Collections.emptyList());
        return call(privateKey, function);
    }

    public String proxyVote(String privateKey, Address snAddr) throws Exception {
        Function function = new Function("proxyVote", Collections.singletonList(snAddr), Collections.emptyList());
        return call(privateKey, function);
    }

    public BigInteger getAmount4Voter(Address voterAddr) throws Exception {
        Function function = new Function("getAmount4Voter", Collections.singletonList(voterAddr), Collections.singletonList(new TypeReference<Uint256>() {}));
        List<Type> someTypes = query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public BigInteger getVoteNum4Voter(Address voterAddr) throws Exception {
        Function function = new Function("getVoteNum4Voter", Collections.singletonList(voterAddr), Collections.singletonList(new TypeReference<Uint256>() {}));
        List<Type> someTypes = query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public BigInteger getSNNum4Voter(Address voterAddr) throws Exception {
        Function function = new Function("getSNNum4Voter", Collections.singletonList(voterAddr), Collections.singletonList(new TypeReference<Uint256>() {}));
        List<Type> someTypes = query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public SNVoteRetInfo getSNs4Voter(Address voterAddr, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getSNs4Voter", Arrays.asList(voterAddr, new Uint256(start), new Uint256(count)), Arrays.asList(new TypeReference<DynamicArray<Address>>() {
        }, new TypeReference<DynamicArray<Uint256>>() {}));
        List<Type> someTypes = query(function);
        return new SNVoteRetInfo(((DynamicArray<Address>) someTypes.get(0)).getValue(), ((DynamicArray<Uint256>) someTypes.get(1)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList()));
    }

    public BigInteger getProxyNum4Voter(Address voterAddr) throws Exception {
        Function function = new Function("getProxyNum4Voter", Collections.singletonList(voterAddr), Collections.singletonList(new TypeReference<Uint256>() {}));
        List<Type> someTypes = query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public SNVoteRetInfo getProxies4Voter(Address voterAddr, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getProxies4Voter", Arrays.asList(voterAddr, new Uint256(start), new Uint256(count)), Arrays.asList(new TypeReference<DynamicArray<Address>>() {
        }, new TypeReference<DynamicArray<Uint256>>() {}));
        List<Type> someTypes = query(function);
        return new SNVoteRetInfo(((DynamicArray<Address>) someTypes.get(0)).getValue(), ((DynamicArray<Uint256>) someTypes.get(1)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList()));
    }

    public BigInteger getVotedIDNum4Voter(Address voterAddr) throws Exception {
        Function function = new Function("getVotedIDNum4Voter", Collections.singletonList(voterAddr), Collections.singletonList(new TypeReference<Uint256>() {}));
        List<Type> someTypes = query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<BigInteger> getVotedIDs4Voter(Address voterAddr, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getVotedIDs4Voter", Arrays.asList(voterAddr, new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Uint256>>() {}));
        List<Type> someTypes = query(function);
        return ((DynamicArray<Uint256>) someTypes.get(0)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public BigInteger getProxiedIDNum4Voter(Address voterAddr) throws Exception {
        Function function = new Function("getProxiedIDNum4Voter", Collections.singletonList(voterAddr), Collections.singletonList(new TypeReference<Uint256>() {}));
        List<Type> someTypes = query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<BigInteger> getProxiedIDs4Voter(Address voterAddr, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getProxiedIDs4Voter", Arrays.asList(voterAddr, new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Uint256>>() {}));
        List<Type> someTypes = query(function);
        return ((DynamicArray<Uint256>) someTypes.get(0)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public BigInteger getTotalAmount(Address addr) throws Exception {
        Function function = new Function("getTotalAmount", Collections.singletonList(addr), Collections.singletonList(new TypeReference<Uint256>() {}));
        List<Type> someTypes = query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public BigInteger getTotalVoteNum(Address addr) throws Exception {
        Function function = new Function("getTotalVoteNum", Collections.singletonList(addr), Collections.singletonList(new TypeReference<Uint256>() {}));
        List<Type> someTypes = query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public BigInteger getVoterNum(Address addr) throws Exception {
        Function function = new Function("getVoterNum", Collections.singletonList(addr), Collections.singletonList(new TypeReference<Uint256>() {}));
        List<Type> someTypes = query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public SNVoteRetInfo getVoters(Address addr, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getVoters", Arrays.asList(addr, new Uint256(start), new Uint256(count)), Arrays.asList(new TypeReference<DynamicArray<Address>>() {
        }, new TypeReference<DynamicArray<Uint256>>() {}));
        List<Type> someTypes = query(function);
        return new SNVoteRetInfo(((DynamicArray<Address>) someTypes.get(0)).getValue(), ((DynamicArray<Uint256>) someTypes.get(1)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList()));
    }

    public BigInteger getIDNum(Address addr) throws Exception {
        Function function = new Function("getIDNum", Collections.singletonList(addr), Collections.singletonList(new TypeReference<Uint256>() {}));
        List<Type> someTypes = query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<BigInteger> getIDs(Address addr, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getIDs", Arrays.asList(addr, new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Uint256>>() {}));
        List<Type> someTypes = query(function);
        return ((DynamicArray<Uint256>) someTypes.get(0)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public BigInteger getAllAmount() throws Exception {
        Function function = new Function("getAllAmount", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {}));
        List<Type> someTypes = query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public BigInteger getAllVoteNum() throws Exception {
        Function function = new Function("getAllVoteNum", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {}));
        List<Type> someTypes = query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public BigInteger getAllProxiedAmount() throws Exception {
        Function function = new Function("getAllProxiedAmount", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {}));
        List<Type> someTypes = query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public BigInteger getAllProxiedVoteNum() throws Exception {
        Function function = new Function("getAllProxiedVoteNum", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {}));
        List<Type> someTypes = query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }
}
