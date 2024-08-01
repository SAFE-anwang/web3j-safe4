package com.anwang.contracts;

import com.anwang.types.safe3.AvailableSafe3Info;
import com.anwang.types.safe3.LockedSafe3Info;
import com.anwang.utils.Safe3Util;
import com.anwang.utils.Safe4Contract;
import com.anwang.utils.Safe4Util;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Hash;
import org.web3j.protocol.Web3j;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Safe3 extends AbstractContract {

    public Safe3(Web3j web3j, long chainId) {
        super(web3j, chainId, Safe4Contract.Safe3ContractAddr);
    }

    // redeem for available & locked SAFE3
    public Map<String, List<String>> redeemSafe3(String privateKey) throws Exception {
        Map<String, List<String>> ret = new HashMap<>();
        List<String> availableTxids = new ArrayList<>();
        List<String> lockedTxids = new ArrayList<>();

        BigInteger privKey = Numeric.toBigInt(privateKey);
        BigInteger compressedPublicKey = Safe3Util.getCompressedPublicKey(privKey);
        String compressedSafe3Addr = Safe3Util.getSafe3Addr(compressedPublicKey);
        BigInteger uncompressedPublicKey = Safe3Util.getCompressedPublicKey(privKey);
        String uncompressedSafe3Addr = Safe3Util.getSafe3Addr(uncompressedPublicKey);

        // 1. Available Safe3
        // 1-1. compressed address
        AvailableSafe3Info info = getAvailableInfo(compressedSafe3Addr);
        if (!info.amount.equals(0) && info.redeemHeight.equals(0)) {
            byte[] hash = Hash.sha256(compressedSafe3Addr.getBytes());
            byte[] sig = Safe4Util.signMessage(hash, privKey);
            Function function = new Function("redeemAvailable", Arrays.asList(new DynamicBytes(compressedPublicKey.toByteArray()), new DynamicBytes(sig)), Collections.emptyList());
            availableTxids.add(call(privateKey, function));
        }
        // 1-2. uncompressed address
        info = getAvailableInfo(uncompressedSafe3Addr);
        if (!info.amount.equals(0) && info.redeemHeight.equals(0)) {
            byte[] hash = Hash.sha256(uncompressedSafe3Addr.getBytes());
            byte[] sig = Safe4Util.signMessage(hash, privKey);
            Function function = new Function("redeemAvailable", Arrays.asList(new DynamicBytes(uncompressedPublicKey.toByteArray()), new DynamicBytes(sig)), Collections.emptyList());
            availableTxids.add(call(privateKey, function));
        }

        // 2. Locked Safe3
        // 2-1. compressed address
        BigInteger lockedNum = getLockedNum(compressedSafe3Addr);
        if (!lockedNum.equals(0)) {
            byte[] hash = Hash.sha256(compressedSafe3Addr.getBytes());
            byte[] sig = Safe4Util.signMessage(hash, privKey);
            Function function = new Function("redeemLocked", Arrays.asList(new DynamicBytes(compressedPublicKey.toByteArray()), new DynamicBytes(sig)), Collections.emptyList());
            lockedTxids.add(call(privateKey, function));
        }

        // 2-2. uncompressed address
        lockedNum = getLockedNum(uncompressedSafe3Addr);
        if (!lockedNum.equals(0)) {
            byte[] hash = Hash.sha256(uncompressedSafe3Addr.getBytes());
            byte[] sig = Safe4Util.signMessage(hash, privKey);
            Function function = new Function("redeemLocked", Arrays.asList(new DynamicBytes(uncompressedPublicKey.toByteArray()), new DynamicBytes(sig)), Collections.emptyList());
            lockedTxids.add(call(privateKey, function));
        }

        if (availableTxids.size() > 0) {
            ret.put("available", availableTxids);
        }
        if (lockedTxids.size() > 0) {
            ret.put("locked", lockedTxids);
        }
        return ret;
    }

    public List<String> redeemMasterNode(String privateKey, String enode) throws Exception {
        List<String> txids = new ArrayList<>();
        BigInteger privKey = Numeric.toBigInt(privateKey);

        // 1. compressed address
        BigInteger pubKey = Safe3Util.getCompressedPublicKey(privKey);
        String safe3Addr = Safe3Util.getSafe3Addr(pubKey);
        byte[] hash = Hash.sha256(safe3Addr.getBytes());
        byte[] sig = Safe4Util.signMessage(hash, privKey);
        Function function = new Function("redeemMasterNode", Arrays.asList(new DynamicBytes(pubKey.toByteArray()), new DynamicBytes(sig), new Utf8String(enode)), Collections.emptyList());
        String txid = call(privateKey, function);
        txids.add(txid);

        // 2. uncompressed address
        pubKey = Safe3Util.getUncompressedPublicKey(privKey);
        safe3Addr = Safe3Util.getSafe3Addr(pubKey);
        hash = Hash.sha256(safe3Addr.getBytes());
        sig = Safe4Util.signMessage(hash, privKey);
        function = new Function("redeemMasterNode", Arrays.asList(new DynamicBytes(pubKey.toByteArray()), new DynamicBytes(sig), new Utf8String(enode)), Collections.emptyList());
        txid = call(privateKey, function);
        txids.add(txid);

        return txids;
    }

    public BigInteger getAllAvailableNum() throws Exception {
        Function function = new Function("getAllAvailableNum", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {}));
        List<Type> someTypes = query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<AvailableSafe3Info> getAvailableInfos(BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getAvailableInfos", Arrays.asList(new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<AvailableSafe3Info>>() {}));
        List<Type> someTypes = query(function);
        return ((DynamicArray<AvailableSafe3Info>) someTypes.get(0)).getValue();
    }

    public AvailableSafe3Info getAvailableInfo(String safe3Addr) throws Exception {
        Function function = new Function("getAvailableInfo", Collections.singletonList(new Utf8String(safe3Addr)), Collections.singletonList(new TypeReference<AvailableSafe3Info>() {}));
        List<Type> someTypes = query(function);
        return (AvailableSafe3Info) someTypes.get(0);
    }

    public BigInteger getAllLockedNum() throws Exception {
        Function function = new Function("getAllLockedNum", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {}));
        List<Type> someTypes = query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public BigInteger getLockedAddrNum() throws Exception {
        Function function = new Function("getLockedAddrNum", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {}));
        List<Type> someTypes = query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<String> getLockedAddrs(BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getLockedAddrs", Arrays.asList(new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Utf8String>>() {}));
        List<Type> someTypes = query(function);
        return ((DynamicArray<Utf8String>) someTypes.get(0)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public BigInteger getLockedNum(String safe3Addr) throws Exception {
        Function function = new Function("getLockedNum", Collections.singletonList(new Utf8String(safe3Addr)), Collections.singletonList(new TypeReference<Uint256>() {}));
        List<Type> someTypes = query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<LockedSafe3Info> getLockedInfo(String safe3Addr, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getLockedInfo", Arrays.asList(new Utf8String(safe3Addr), new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<LockedSafe3Info>>() {}));
        List<Type> someTypes = query(function);
        return ((DynamicArray<LockedSafe3Info>) someTypes.get(0)).getValue();
    }
}
