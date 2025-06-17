package com.anwang.contracts;

import com.anwang.types.proposal.ProposalInfo;
import com.anwang.types.proposal.ProposalVoteInfo;
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

public class Proposal {
    private final ContractUtil contractUtil;

    public Proposal(Web3j web3j, long chainId) {
        contractUtil = new ContractUtil(web3j, chainId, Safe4Contract.ProposalContractAddr);
    }

    public BigInteger getBalance() throws Exception {
        Function function = new Function("getBalance", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public BigInteger getImmatureBalance() throws Exception {
        Function function = new Function("getImmatureBalance", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public String create(String privateKey, String title, BigInteger payAmount, BigInteger payTimes, BigInteger startPayTime, BigInteger endPayTime, String description) throws Exception {
        Function function = new Function("create", Arrays.asList(new Utf8String(title), new Uint256(payAmount), new Uint256(payTimes), new Uint256(startPayTime), new Uint256(endPayTime), new Utf8String(description)), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        return contractUtil.call(privateKey, new BigInteger("1000000000000000000"), function);
    }

    public String vote(String privateKey, BigInteger id, BigInteger voteResult) throws Exception {
        Function function = new Function("vote", Arrays.asList(new Uint256(id), new Uint256(voteResult)), Collections.emptyList());
        return contractUtil.call(privateKey, function);
    }

    public String changeTitle(String privateKey, BigInteger id, String title) throws Exception {
        Function function = new Function("changeTitle", Arrays.asList(new Uint256(id), new Utf8String(title)), Collections.emptyList());
        return contractUtil.call(privateKey, function);
    }

    public String changePayAmount(String privateKey, BigInteger id, BigInteger payAmount) throws Exception {
        Function function = new Function("changePayAmount", Arrays.asList(new Uint256(id), new Uint256(payAmount)), Collections.emptyList());
        return contractUtil.call(privateKey, function);
    }

    public String changePayTimes(String privateKey, BigInteger id, BigInteger payTimes) throws Exception {
        Function function = new Function("changePayTimes", Arrays.asList(new Uint256(id), new Uint256(payTimes)), Collections.emptyList());
        return contractUtil.call(privateKey, function);
    }

    public String changeStartPayTime(String privateKey, BigInteger id, BigInteger startPayTime) throws Exception {
        Function function = new Function("changeStartPayTime", Arrays.asList(new Uint256(id), new Uint256(startPayTime)), Collections.emptyList());
        return contractUtil.call(privateKey, function);
    }

    public String changeEndPayTime(String privateKey, BigInteger id, BigInteger endPayTime) throws Exception {
        Function function = new Function("changeEndPayTime", Arrays.asList(new Uint256(id), new Uint256(endPayTime)), Collections.emptyList());
        return contractUtil.call(privateKey, function);
    }

    public String changeDescription(String privateKey, BigInteger id, String description) throws Exception {
        Function function = new Function("changeDescription", Arrays.asList(new Uint256(id), new Utf8String(description)), Collections.emptyList());
        return contractUtil.call(privateKey, function);
    }

    public ProposalInfo getInfo(BigInteger id) throws Exception {
        Function function = new Function("getInfo", Collections.singletonList(new Uint256(id)), Collections.singletonList(new TypeReference<ProposalInfo>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return (ProposalInfo) someTypes.get(0);
    }

    public BigInteger getVoterNum(BigInteger id) throws Exception {
        Function function = new Function("getVoterNum", Collections.singletonList(new Uint256(id)), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<ProposalVoteInfo> getVoteInfo(BigInteger id, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getVoteInfo", Arrays.asList(new Uint256(id), new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<ProposalVoteInfo>>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((DynamicArray<ProposalVoteInfo>) someTypes.get(0)).getValue();
    }

    public List<BigInteger> getRewardIDs(BigInteger id) throws Exception {
        Function function = new Function("getRewardIDs", Collections.singletonList(new Uint256(id)), Collections.singletonList(new TypeReference<DynamicArray<Uint256>>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((DynamicArray<Uint256>) someTypes.get(0)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public BigInteger getNum() throws Exception {
        Function function = new Function("getNum", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<BigInteger> getAll(BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getAll", Arrays.asList(new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Uint256>>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((DynamicArray<Uint256>) someTypes.get(0)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public BigInteger getMineNum(Address creator) throws Exception {
        Function function = new Function("getMineNum", Collections.singletonList(creator), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<BigInteger> getMines(Address creator, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getMines", Arrays.asList(creator, new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Uint256>>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((DynamicArray<Uint256>) someTypes.get(0)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public Boolean exist(BigInteger id) throws Exception {
        Function function = new Function("exist", Collections.singletonList(new Uint256(id)), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }
}
