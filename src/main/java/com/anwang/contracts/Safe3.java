package com.anwang.contracts;

import com.anwang.types.safe3.AvailableSafe3Info;
import com.anwang.types.safe3.LockedSafe3Info;
import com.anwang.utils.Safe3Util;
import com.anwang.utils.Safe4Contract;
import com.anwang.utils.Safe4Util;
import org.web3j.abi.TypeReference;
import org.web3j.abi.Utils;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Hash;
import org.web3j.protocol.Web3j;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Safe3 extends AbstractContract {

    public Safe3(Web3j web3j, long chainId) {
        super(web3j, chainId, Safe4Contract.Safe3ContractAddr);
    }

    // redeem for available & locked SAFE3
    public List<String> batchRedeemSafe3(String callerPrivateKey, List<String> privateKeys) throws Exception {
        BigInteger privKey;
        BigInteger pubKey;
        String safe3Addr;
        List<byte[]> availablePubKeys = new ArrayList<>();
        List<byte[]> availableSigs = new ArrayList<>();
        List<byte[]> lockedPubKeys = new ArrayList<>();
        List<byte[]> lockedSigs = new ArrayList<>();
        for (String privateKey : privateKeys) {
            privKey = Numeric.toBigInt(privateKey);
            pubKey = Safe3Util.getCompressedPublicKey(privKey);
            safe3Addr = Safe3Util.getSafe3Addr(pubKey);
            if (existAvailableNeedToRedeem(safe3Addr)) {
                availablePubKeys.add(pubKey.toByteArray());
                availableSigs.add(Safe4Util.signMessage(Hash.sha256(safe3Addr.getBytes()), privKey));
            }
            if (existLockedNeedToRedeem(safe3Addr)) {
                lockedPubKeys.add(pubKey.toByteArray());
                lockedSigs.add(Safe4Util.signMessage(Hash.sha256(safe3Addr.getBytes()), privKey));
            }

            pubKey = Safe3Util.getUncompressedPublicKey(privKey);
            safe3Addr = Safe3Util.getSafe3Addr(pubKey);
            if (existAvailableNeedToRedeem(safe3Addr)) {
                availablePubKeys.add(pubKey.toByteArray());
                availableSigs.add(Safe4Util.signMessage(Hash.sha256(safe3Addr.getBytes()), privKey));
            }
            if (existLockedNeedToRedeem(safe3Addr)) {
                lockedPubKeys.add(pubKey.toByteArray());
                lockedSigs.add(Safe4Util.signMessage(Hash.sha256(safe3Addr.getBytes()), privKey));
            }
        }

        List<String> txids = new ArrayList<>();
        if (availablePubKeys.size() != 0) {
            Function function = new Function("batchRedeemAvailable", Arrays.asList(new DynamicArray<>(DynamicBytes.class, Utils.typeMap(availablePubKeys, DynamicBytes.class)), new DynamicArray<>(DynamicBytes.class, Utils.typeMap(availableSigs, DynamicBytes.class))), Collections.emptyList());
            txids.add(call(callerPrivateKey, function));
        }

        if (lockedPubKeys.size() != 0) {
            Function function = new Function("batchRedeemLocked", Arrays.asList(new DynamicArray<>(DynamicBytes.class, Utils.typeMap(availablePubKeys, DynamicBytes.class)), new DynamicArray<>(DynamicBytes.class, Utils.typeMap(availableSigs, DynamicBytes.class))), Collections.emptyList());
            txids.add(call(callerPrivateKey, function));
        }
        return txids;
    }

    public List<String> redeemMasterNode(String callerPrivateKey, List<String> privateKeys, List<String> enodes) throws Exception {
        BigInteger privKey;
        BigInteger pubKey;
        String safe3Addr;
        List<byte[]> pubKeys = new ArrayList<>();
        List<byte[]> sigs = new ArrayList<>();
        List<byte[]> lockedPubKeys = new ArrayList<>();
        List<byte[]> lockedSigs = new ArrayList<>();
        for (String privateKey : privateKeys) {
            privKey = Numeric.toBigInt(privateKey);
            pubKey = Safe3Util.getCompressedPublicKey(privKey);
            safe3Addr = Safe3Util.getSafe3Addr(pubKey);
            if (existMasterNodeNeedToRedeem(safe3Addr)) {
                pubKeys.add(pubKey.toByteArray());
                sigs.add(Safe4Util.signMessage(Hash.sha256(safe3Addr.getBytes()), privKey));
            }

            pubKey = Safe3Util.getUncompressedPublicKey(privKey);
            safe3Addr = Safe3Util.getSafe3Addr(pubKey);
            if (existMasterNodeNeedToRedeem(safe3Addr)) {
                pubKeys.add(pubKey.toByteArray());
                sigs.add(Safe4Util.signMessage(Hash.sha256(safe3Addr.getBytes()), privKey));
            }
        }

        List<String> txids = new ArrayList<>();
        if (pubKeys.size() != 0) {
            Function function = new Function("batchRedeemMasterNode", Arrays.asList(new DynamicArray<>(DynamicBytes.class, Utils.typeMap(pubKeys, DynamicBytes.class)), new DynamicArray<>(DynamicBytes.class, Utils.typeMap(sigs, DynamicBytes.class)), new DynamicArray<>(Utf8String.class, Utils.typeMap(enodes, Utf8String.class))), Collections.emptyList());
            txids.add(call(callerPrivateKey, function));
        }
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

    public boolean existAvailableNeedToRedeem(String safe3Addr) throws Exception {
        Function function = new Function("existAvailableNeedToRedeem", Collections.singletonList(new Utf8String(safe3Addr)), Collections.singletonList(new TypeReference<Bool>() {}));
        List<Type> someTypes = query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public boolean existLockedNeedToRedeem(String safe3Addr) throws Exception {
        Function function = new Function("existLockedNeedToRedeem", Collections.singletonList(new Utf8String(safe3Addr)), Collections.singletonList(new TypeReference<Bool>() {}));
        List<Type> someTypes = query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public boolean existMasterNodeNeedToRedeem(String safe3Addr) throws Exception {
        Function function = new Function("existMasterNodeNeedToRedeem", Collections.singletonList(new Utf8String(safe3Addr)), Collections.singletonList(new TypeReference<Bool>() {}));
        List<Type> someTypes = query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }
}
