package com.anwang.contracts;

import com.anwang.types.safe3.AvailableSafe3Info;
import com.anwang.types.safe3.LockedSafe3Info;
import com.anwang.utils.ContractUtil;
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

public class Safe3 {
    private final ContractUtil contractUtil;

    public Safe3(Web3j web3j, long chainId) {
        contractUtil = new ContractUtil(web3j, chainId, Safe4Contract.Safe3ContractAddr);
    }

    // add Safe3 record, just for testnet
    public List<String> addSafe3(String callerPrivateKey, String safe3Addr) throws Exception {
        if (contractUtil.chainId != 6666666) {
            throw new Exception("Just for testnet");
        }

        List<String> txids = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        BigInteger amount = new BigInteger(String.valueOf(random.nextInt(5) + 1)).multiply(new BigInteger("100000000"));
        Function function = new Function("addAvailable", Arrays.asList(new Utf8String(safe3Addr), new Uint256(amount)), Collections.emptyList());
        txids.add(contractUtil.call(callerPrivateKey, function));

        int count = random.nextInt(5) + 1;
        for (int i = 0; i < count; i++) {
            amount = new BigInteger(String.valueOf(random.nextInt(10) + 1)).multiply(new BigInteger("100000000"));
            function = new Function("addLocked", Arrays.asList(new Utf8String(safe3Addr), new Uint256(amount)), Collections.emptyList());
            txids.add(contractUtil.call(callerPrivateKey, function));
        }

        function = new Function("addMasterNode", Arrays.asList(new Utf8String(safe3Addr)), Collections.emptyList());
        txids.add(contractUtil.call(callerPrivateKey, function));

        amount = new BigInteger(String.valueOf(random.nextInt(30) + 1)).multiply(new BigInteger("100000000")).divide(BigInteger.TEN);
        function = new Function("addPetty", Arrays.asList(new Utf8String(safe3Addr), new Uint256(amount)), Collections.emptyList());
        txids.add(contractUtil.call(callerPrivateKey, function));
        return txids;
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
        List<BigInteger> lockedNums = new ArrayList<>();
        List<byte[]> pettyPubKeys = new ArrayList<>();
        List<byte[]> pettySigs = new ArrayList<>();
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
                lockedNums.add(getLockedNum(safe3Addr));
            }
            if (existPettyNeedToRedeem(safe3Addr)) {
                pettyPubKeys.add(pubKey.toByteArray());
                pettySigs.add(sig);
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
                lockedNums.add(getLockedNum(safe3Addr));
            }
            if (existPettyNeedToRedeem(safe3Addr)) {
                pettyPubKeys.add(pubKey.toByteArray());
                pettySigs.add(sig);
            }
        }

        List<String> txids = new ArrayList<>();
        if (availablePubKeys.size() != 0) {
            int i = 0;
            for (; i < availablePubKeys.size() / 20; i++) {
                Function function = new Function("batchRedeemAvailable", Arrays.asList(new DynamicArray<>(DynamicBytes.class, Utils.typeMap(availablePubKeys.subList(i * 20, (i + 1) * 20), DynamicBytes.class)), new DynamicArray<>(DynamicBytes.class, Utils.typeMap(availableSigs.subList(i * 20, (i + 1) * 20), DynamicBytes.class)), targetAddr), Collections.emptyList());
                txids.add(contractUtil.call(callerPrivateKey, function));
            }
            if (availablePubKeys.size() % 20 != 0) {
                Function function = new Function("batchRedeemAvailable", Arrays.asList(new DynamicArray<>(DynamicBytes.class, Utils.typeMap(availablePubKeys.subList(i * 20, availablePubKeys.size()), DynamicBytes.class)), new DynamicArray<>(DynamicBytes.class, Utils.typeMap(availableSigs.subList(i * 20, availableSigs.size()), DynamicBytes.class)), targetAddr), Collections.emptyList());
                txids.add(contractUtil.call(callerPrivateKey, function));
            }
        }

        if (lockedPubKeys.size() != 0) {
            // one batch: 20 address
            while (true) {
                int totalLockedNum = 0;
                List<byte[]> tempPubkeys = new ArrayList<>();
                List<byte[]> tempSigs = new ArrayList<>();
                int i = 0;
                for (; i < lockedNums.size(); i++) {
                    int remainLockedNum = lockedNums.get(i).intValue();
                    totalLockedNum += remainLockedNum;
                    if (totalLockedNum == 0) {
                        continue;
                    }
                    if (totalLockedNum >= 100) {
                        tempPubkeys.add(lockedPubKeys.get(i));
                        tempSigs.add(lockedSigs.get(i));
                        Function function = new Function("batchRedeemLocked", Arrays.asList(new DynamicArray<>(DynamicBytes.class, Utils.typeMap(tempPubkeys, DynamicBytes.class)), new DynamicArray<>(DynamicBytes.class, Utils.typeMap(tempSigs, DynamicBytes.class)), targetAddr), Collections.emptyList());
                        txids.add(contractUtil.call(callerPrivateKey, function));

                        if (totalLockedNum == 100) {
                            lockedNums.set(i, BigInteger.ZERO);
                        } else {
                            lockedNums.set(i, BigInteger.valueOf(totalLockedNum - 100));
                        }
                        break;
                    } else {
                        lockedNums.set(i, BigInteger.ZERO);
                        tempPubkeys.add(lockedPubKeys.get(i));
                        tempSigs.add(lockedSigs.get(i));
                        if (tempPubkeys.size() == 20) {
                            Function function = new Function("batchRedeemLocked", Arrays.asList(new DynamicArray<>(DynamicBytes.class, Utils.typeMap(tempPubkeys, DynamicBytes.class)), new DynamicArray<>(DynamicBytes.class, Utils.typeMap(tempSigs, DynamicBytes.class)), targetAddr), Collections.emptyList());
                            txids.add(contractUtil.call(callerPrivateKey, function));
                            break;
                        }
                    }
                }
                if (totalLockedNum == 0) {
                    break;
                }
                if (i == lockedNums.size()) {
                    Function function = new Function("batchRedeemLocked", Arrays.asList(new DynamicArray<>(DynamicBytes.class, Utils.typeMap(tempPubkeys, DynamicBytes.class)), new DynamicArray<>(DynamicBytes.class, Utils.typeMap(tempSigs, DynamicBytes.class)), targetAddr), Collections.emptyList());
                    txids.add(contractUtil.call(callerPrivateKey, function));
                    break;
                }
            }
        }

        if (pettyPubKeys.size() != 0) {
            int i = 0;
            for (; i < pettyPubKeys.size() / 20; i++) {
                Function function = new Function("batchRedeemPetty", Arrays.asList(new DynamicArray<>(DynamicBytes.class, Utils.typeMap(pettyPubKeys.subList(i * 20, (i + 1) * 20), DynamicBytes.class)), new DynamicArray<>(DynamicBytes.class, Utils.typeMap(pettySigs.subList(i * 20, (i + 1) * 20), DynamicBytes.class)), targetAddr), Collections.emptyList());
                txids.add(contractUtil.call(callerPrivateKey, function));
            }
            if (pettyPubKeys.size() % 20 != 0) {
                Function function = new Function("batchRedeemPetty", Arrays.asList(new DynamicArray<>(DynamicBytes.class, Utils.typeMap(pettyPubKeys.subList(i * 20, pettyPubKeys.size()), DynamicBytes.class)), new DynamicArray<>(DynamicBytes.class, Utils.typeMap(pettySigs.subList(i * 20, pettySigs.size()), DynamicBytes.class)), targetAddr), Collections.emptyList());
                txids.add(contractUtil.call(callerPrivateKey, function));
            }
        }
        return txids;
    }

    public List<String> batchRedeemMasterNode(String callerPrivateKey, List<String> privateKeys, List<String> enodes, Address targetAddr) throws Exception {
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
            int i = 0;
            for (; i < pubKeys.size() / 20; i++) {
                Function function = new Function("batchRedeemMasterNode", Arrays.asList(new DynamicArray<>(DynamicBytes.class, Utils.typeMap(pubKeys.subList(i * 20, (i + 1) * 20), DynamicBytes.class)), new DynamicArray<>(DynamicBytes.class, Utils.typeMap(sigs.subList(i * 20, (i + 1) * 20), DynamicBytes.class)), new DynamicArray<>(Utf8String.class, Utils.typeMap(enodes.subList(i * 20, (i + 1) * 20), Utf8String.class)), targetAddr), Collections.emptyList());
                txids.add(contractUtil.call(callerPrivateKey, function));
            }
            if (pubKeys.size() % 20 != 0) {
                Function function = new Function("batchRedeemMasterNode", Arrays.asList(new DynamicArray<>(DynamicBytes.class, Utils.typeMap(pubKeys.subList(i * 20, pubKeys.size()), DynamicBytes.class)), new DynamicArray<>(DynamicBytes.class, Utils.typeMap(sigs.subList(i * 20, sigs.size()), DynamicBytes.class)), new DynamicArray<>(Utf8String.class, Utils.typeMap(enodes.subList(i * 20, enodes.size()), Utf8String.class)), targetAddr), Collections.emptyList());
                txids.add(contractUtil.call(callerPrivateKey, function));
            }
        }
        return txids;
    }

    public BigInteger getAllAvailableNum() throws Exception {
        Function function = new Function("getAllAvailableNum", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<AvailableSafe3Info> getAvailableInfos(BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getAvailableInfos", Arrays.asList(new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<AvailableSafe3Info>>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((DynamicArray<AvailableSafe3Info>) someTypes.get(0)).getValue();
    }

    public AvailableSafe3Info getAvailableInfo(String safe3Addr) throws Exception {
        Function function = new Function("getAvailableInfo", Collections.singletonList(new Utf8String(safe3Addr)), Collections.singletonList(new TypeReference<AvailableSafe3Info>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return (AvailableSafe3Info) someTypes.get(0);
    }

    public BigInteger getAllLockedNum() throws Exception {
        Function function = new Function("getAllLockedNum", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public BigInteger getLockedAddrNum() throws Exception {
        Function function = new Function("getLockedAddrNum", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<String> getLockedAddrs(BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getLockedAddrs", Arrays.asList(new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<Utf8String>>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((DynamicArray<Utf8String>) someTypes.get(0)).getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
    }

    public BigInteger getLockedNum(String safe3Addr) throws Exception {
        Function function = new Function("getLockedNum", Collections.singletonList(new Utf8String(safe3Addr)), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<LockedSafe3Info> getLockedInfo(String safe3Addr, BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getLockedInfo", Arrays.asList(new Utf8String(safe3Addr), new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<LockedSafe3Info>>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((DynamicArray<LockedSafe3Info>) someTypes.get(0)).getValue();
    }

    public BigInteger getAllPettyNum() throws Exception {
        Function function = new Function("getAllPettyNum", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Uint256) someTypes.get(0)).getValue();
    }

    public List<AvailableSafe3Info> getPettyInfos(BigInteger start, BigInteger count) throws Exception {
        Function function = new Function("getPettyInfos", Arrays.asList(new Uint256(start), new Uint256(count)), Collections.singletonList(new TypeReference<DynamicArray<AvailableSafe3Info>>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((DynamicArray<AvailableSafe3Info>) someTypes.get(0)).getValue();
    }

    public AvailableSafe3Info getPettyInfo(String safe3Addr) throws Exception {
        Function function = new Function("getPettyInfo", Collections.singletonList(new Utf8String(safe3Addr)), Collections.singletonList(new TypeReference<AvailableSafe3Info>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return (AvailableSafe3Info) someTypes.get(0);
    }

    public boolean existAvailableNeedToRedeem(String safe3Addr) throws Exception {
        Function function = new Function("existAvailableNeedToRedeem", Collections.singletonList(new Utf8String(safe3Addr)), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public boolean existLockedNeedToRedeem(String safe3Addr) throws Exception {
        Function function = new Function("existLockedNeedToRedeem", Collections.singletonList(new Utf8String(safe3Addr)), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public boolean existMasterNodeNeedToRedeem(String safe3Addr) throws Exception {
        Function function = new Function("existMasterNodeNeedToRedeem", Collections.singletonList(new Utf8String(safe3Addr)), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }

    public boolean existPettyNeedToRedeem(String safe3Addr) throws Exception {
        Function function = new Function("existPettyNeedToRedeem", Collections.singletonList(new Utf8String(safe3Addr)), Collections.singletonList(new TypeReference<Bool>() {
        }));
        List<Type> someTypes = contractUtil.query(function);
        return ((Bool) someTypes.get(0)).getValue();
    }
}
