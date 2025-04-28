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
import java.util.*;
import java.util.stream.Collectors;

public class Safe3 extends AbstractContract {

    public Safe3(Web3j web3j, long chainId) {
        super(web3j, chainId, Safe4Contract.Safe3ContractAddr);
    }

    // add Safe3 record, just for testnet
    public List<String> addSafe3(String callerPrivateKey, String safe3Addr) throws Exception {
        if (chainId != 6666666) {
            throw new Exception("Just for testnet");
        }

        List<String> txids = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        BigInteger amount = new BigInteger(String.valueOf(random.nextInt(5) + 1)).multiply(new BigInteger("1000000000000000000"));
        Function function = new Function("addAvailable", Arrays.asList(new Utf8String(safe3Addr), new Uint256(amount)), Collections.emptyList());
        txids.add(call(callerPrivateKey, function));

        int count = random.nextInt(5) + 1;
        for (int i = 0; i < count; i++) {
            amount = new BigInteger(String.valueOf(random.nextInt(10) + 1)).multiply(new BigInteger("1000000000000000000"));
            function = new Function("addLocked", Arrays.asList(new Utf8String(safe3Addr), new Uint256(amount)), Collections.emptyList());
            txids.add(call(callerPrivateKey, function));
        }

        function = new Function("addMasterNode", Arrays.asList(new Utf8String(safe3Addr)), Collections.emptyList());
        txids.add(call(callerPrivateKey, function));
        return txids;
    }

    // reset Safe3 record, just for testnet
    public String resetSafe3(String callerPrivateKey, String safe3Addr) throws Exception {
        if (chainId != 6666666) {
            throw new Exception("Just for testnet");
        }
        Function function = new Function("reset", Collections.singletonList(new Utf8String(safe3Addr)), Collections.emptyList());
        return call(callerPrivateKey, function);
    }

    // redeem for available & locked SAFE3
    public List<String> batchRedeemSafe3(String callerPrivateKey, List<String> privateKeys, Address targetAddr) throws Exception {
        BigInteger privKey;
        BigInteger pubKey;
        String safe3Addr;
        byte[] buf;
        byte[] sig;
        List<byte[]> availablePubKeys = new ArrayList<>();
        List<byte[]> availableSigs = new ArrayList<>();
        List<byte[]> lockedPubKeys = new ArrayList<>();
        List<byte[]> lockedSigs = new ArrayList<>();
        for (String privateKey : privateKeys) {
            privKey = Numeric.toBigInt(privateKey);
            pubKey = Safe3Util.getCompressedPublicKey(privKey);
            safe3Addr = Safe3Util.getSafe3Addr(pubKey);
            buf = new byte[safe3Addr.getBytes().length + 20];
            System.arraycopy(safe3Addr.getBytes(), 0, buf, 0, safe3Addr.getBytes().length);
            System.arraycopy(Numeric.hexStringToByteArray(targetAddr.getValue()), 0, buf, safe3Addr.getBytes().length, 20);
            sig = Safe4Util.signMessage(Hash.sha256(buf), privKey);
            if (existAvailableNeedToRedeem(safe3Addr)) {
                availablePubKeys.add(pubKey.toByteArray());
                availableSigs.add(sig);
            }
            if (existLockedNeedToRedeem(safe3Addr)) {
                lockedPubKeys.add(pubKey.toByteArray());
                lockedSigs.add(sig);
            }

            pubKey = Safe3Util.getUncompressedPublicKey(privKey);
            safe3Addr = Safe3Util.getSafe3Addr(pubKey);
            buf = new byte[safe3Addr.getBytes().length + 20];
            System.arraycopy(safe3Addr.getBytes(), 0, buf, 0, safe3Addr.getBytes().length);
            System.arraycopy(Numeric.hexStringToByteArray(targetAddr.getValue()), 0, buf, safe3Addr.getBytes().length, 20);
            sig = Safe4Util.signMessage(Hash.sha256(buf), privKey);
            if (existAvailableNeedToRedeem(safe3Addr)) {
                availablePubKeys.add(pubKey.toByteArray());
                availableSigs.add(sig);
            }
            if (existLockedNeedToRedeem(safe3Addr)) {
                lockedPubKeys.add(pubKey.toByteArray());
                lockedSigs.add(sig);
            }
        }

        List<String> txids = new ArrayList<>();
        if (availablePubKeys.size() != 0) {
            Function function = new Function("batchRedeemAvailable", Arrays.asList(new DynamicArray<>(DynamicBytes.class, Utils.typeMap(availablePubKeys, DynamicBytes.class)), new DynamicArray<>(DynamicBytes.class, Utils.typeMap(availableSigs, DynamicBytes.class)), targetAddr), Collections.emptyList());
            txids.add(call(callerPrivateKey, function));
        }

        if (lockedPubKeys.size() != 0) {
            Function function = new Function("batchRedeemLocked", Arrays.asList(new DynamicArray<>(DynamicBytes.class, Utils.typeMap(lockedPubKeys, DynamicBytes.class)), new DynamicArray<>(DynamicBytes.class, Utils.typeMap(lockedSigs, DynamicBytes.class)), targetAddr), Collections.emptyList());
            txids.add(call(callerPrivateKey, function));
        }
        return txids;
    }

    public String batchRedeemMasterNode(String callerPrivateKey, List<String> privateKeys, List<String> enodes, Address targetAddr) throws Exception {
        BigInteger privKey;
        BigInteger pubKey;
        String safe3Addr;
        byte[] buf;
        byte[] sig;
        List<byte[]> pubKeys = new ArrayList<>();
        List<byte[]> sigs = new ArrayList<>();
        for (String privateKey : privateKeys) {
            privKey = Numeric.toBigInt(privateKey);
            pubKey = Safe3Util.getCompressedPublicKey(privKey);
            safe3Addr = Safe3Util.getSafe3Addr(pubKey);
            buf = new byte[safe3Addr.getBytes().length + 20];
            System.arraycopy(safe3Addr.getBytes(), 0, buf, 0, safe3Addr.getBytes().length);
            System.arraycopy(Numeric.hexStringToByteArray(targetAddr.getValue()), 0, buf, safe3Addr.getBytes().length, 20);
            sig = Safe4Util.signMessage(Hash.sha256(buf), privKey);
            if (existMasterNodeNeedToRedeem(safe3Addr)) {
                pubKeys.add(pubKey.toByteArray());
                sigs.add(sig);
            }

            pubKey = Safe3Util.getUncompressedPublicKey(privKey);
            safe3Addr = Safe3Util.getSafe3Addr(pubKey);
            buf = new byte[safe3Addr.getBytes().length + 20];
            System.arraycopy(safe3Addr.getBytes(), 0, buf, 0, safe3Addr.getBytes().length);
            System.arraycopy(Numeric.hexStringToByteArray(targetAddr.getValue()), 0, buf, safe3Addr.getBytes().length, 20);
            sig = Safe4Util.signMessage(Hash.sha256(buf), privKey);
            if (existMasterNodeNeedToRedeem(safe3Addr)) {
                pubKeys.add(pubKey.toByteArray());
                sigs.add(sig);
            }
        }

        List<String> txids = new ArrayList<>();
        if (pubKeys.size() != 0) {
            Function function = new Function("batchRedeemMasterNode", Arrays.asList(new DynamicArray<>(DynamicBytes.class, Utils.typeMap(pubKeys, DynamicBytes.class)), new DynamicArray<>(DynamicBytes.class, Utils.typeMap(sigs, DynamicBytes.class)), new DynamicArray<>(Utf8String.class, Utils.typeMap(enodes, Utf8String.class)), targetAddr), Collections.emptyList());
            return call(callerPrivateKey, function);
        }
        return "";
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
