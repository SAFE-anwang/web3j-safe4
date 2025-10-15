package com.anwang.contracts;

import com.anwang.Safe4;
import com.anwang.types.safe3.AvailableSafe3Info;
import com.anwang.types.safe3.LockedSafe3Info;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Safe3Test {

    private Web3j web3j;
    private Safe4 safe4;

    @BeforeEach
    public void init() {
        web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
        safe4 = new Safe4(web3j, 6666666);
    }

    @Test
    public void testAddSafe3() throws Exception {
//        String callerPrivateKey = "0x78cc1725d7e8ce249d6849b36785bb7bae695b667da24240cf29caf10c14473a";
//        List<String> txids = new ArrayList<>();
//        int count = 1;
//        String str = "";
//        for (int k = 0; k < count; k++) {
//            ECKeyPair ecKeyPair = Keys.createEcKeyPair();
//            String privateKey = Numeric.toHexStringWithPrefixZeroPadded(ecKeyPair.getPrivateKey(), 64);
//            BigInteger privKey = Numeric.toBigInt(privateKey);
//            BigInteger compressedPublicKey = Safe3Util.getCompressedPublicKey(privKey);
//            String safe3Addr = Safe3Util.getSafe3Addr(compressedPublicKey);
//            String safe4Addr = new Address(getAddress(Sign.publicKeyFromPrivate(privKey))).toString();
//
//            System.out.println("\n" + (k + 1) + ". " + privateKey + ", Bitcoin Secret Key: " + Safe3Util.getSecretKey(privKey, true));
//            System.out.println("safe3 addr: " + safe3Addr);
//            System.out.println("safe4 addr: " + safe4Addr);
//            txids.addAll(safe4.safe3.addSafe3(callerPrivateKey, safe3Addr));
//
//            str += Safe3Util.getSecretKey(privKey, true) + " 2025-04-26T11:00:00Z change=1 # addr=" + safe3Addr + "\n";
//        }
//        System.out.println("\n\n" + str);
//        Assertions.assertTrue(txids.size() > 0);
    }

    @Test
    public void testRedeemSafe() throws Exception {
        List<String> privateKeys = new ArrayList<>();
        privateKeys.add("0x7f5eb9b7027b6c138caf1c48317d75589101956a9cf370258737bed0f5613a9a");
        privateKeys.add("0xeb8d00dc153abb29a37ecd479692263881b1abd898215b285cbd6eef4d7b9701");
        privateKeys.add("0xace76e6e87eb678eb4a596f40f9463ca71e9f3c8589cd2ed8bcaae180ed8f553");
        privateKeys.add("0xcfd696963c7be9c27c070fde26f71868d5872b0cb8489336fc54f3aac064b1bb");
        privateKeys.add("0xc85843e370c0956187fd1224cb06154f9965e28751829abedd3c2df5ad4762c7");
        privateKeys.add("0xdb41b9dd82694edaf05fea9743713e3fa73540f54e3a046651a5732ec936a720");
        privateKeys.add("0xd3d0061fe46ae70f6df51d48d6cc69abe1b316347b3ead4018f1e34ca543373d");
        privateKeys.add("0xad82becdcf2f6eeabc535c5c9a37ea45df93192d20ba3243a1a1b66a6ebb35bb");
        privateKeys.add("0xb436b293e8d2fce2d67ff22e1067c804da1b2d20686de111762d9f93e5e66449");
        privateKeys.add("0xcdd21cf48b6e3f3accf57bf8e00dccb67db9902a0abb6172ab47c0707f384205");
        privateKeys.add("0x24793cf4a1dad93413592df26b4be89857f1fa483448f7c36690908659f83455");
        List<String> txids = safe4.safe3.batchRedeemSafe3("0x020274d1ddb0d006eb9a3c4871091c191c46a01c3fb8f09cfd1ae9192f893712", privateKeys, new Address("0x9432920f31f9f81b8d0002231c111d7e5eb1e4e1"));
        System.out.println(txids);
        Assertions.assertTrue(txids.size() > 0);
    }

    @Test
    public void testRedeemMasterNode() throws Exception {
        List<String> privateKeys = new ArrayList<>();
        privateKeys.add("0x7f5eb9b7027b6c138caf1c48317d75589101956a9cf370258737bed0f5613a9a");
        privateKeys.add("0xeb8d00dc153abb29a37ecd479692263881b1abd898215b285cbd6eef4d7b9701");
        privateKeys.add("0xace76e6e87eb678eb4a596f40f9463ca71e9f3c8589cd2ed8bcaae180ed8f553");
        privateKeys.add("0xcfd696963c7be9c27c070fde26f71868d5872b0cb8489336fc54f3aac064b1bb");
        privateKeys.add("0xc85843e370c0956187fd1224cb06154f9965e28751829abedd3c2df5ad4762c7");
        privateKeys.add("0xdb41b9dd82694edaf05fea9743713e3fa73540f54e3a046651a5732ec936a720");
        privateKeys.add("0xd3d0061fe46ae70f6df51d48d6cc69abe1b316347b3ead4018f1e34ca543373d");
        privateKeys.add("0xad82becdcf2f6eeabc535c5c9a37ea45df93192d20ba3243a1a1b66a6ebb35bb");
        privateKeys.add("0xb436b293e8d2fce2d67ff22e1067c804da1b2d20686de111762d9f93e5e66449");
        privateKeys.add("0xcdd21cf48b6e3f3accf57bf8e00dccb67db9902a0abb6172ab47c0707f384205");
        privateKeys.add("0x24793cf4a1dad93413592df26b4be89857f1fa483448f7c36690908659f83455");
        List<String> enodes = new ArrayList<>();
        for (int i = 0; i < privateKeys.size(); i++) {
            enodes.add("");
        }
        List<String> txids = safe4.safe3.batchRedeemMasterNode("0x020274d1ddb0d006eb9a3c4871091c191c46a01c3fb8f09cfd1ae9192f893712", privateKeys, enodes, new Address("0x9432920f31f9f81b8d0002231c111d7e5eb1e4e1"));
        System.out.println(txids);
        Assertions.assertTrue(txids.size() > 0);
    }

    @Test
    public void testGetAllAvailableNum() throws Exception {
        BigInteger num = safe4.safe3.getAllAvailableNum();
        System.out.println(num);
        Assertions.assertTrue(num.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetAvailableInfos() throws Exception {
        List<AvailableSafe3Info> infos = safe4.safe3.getAvailableInfos(BigInteger.ZERO, BigInteger.TEN);
        System.out.println(infos);
        Assertions.assertTrue(infos.size() > 0);
    }

    @Test
    public void testGetAvailableInfo() throws Exception {
        AvailableSafe3Info info = safe4.safe3.getAvailableInfo("XuPmDoaNb6rbNywefkTbESHXiYqNpYvaPU");
        System.out.println(info);
        Assertions.assertTrue(info.safe3Addr.length() > 0);
    }

    @Test
    public void testGetAllLockedNum() throws Exception {
        BigInteger num = safe4.safe3.getAllLockedNum();
        System.out.println(num);
        Assertions.assertTrue(num.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetLockedAddrNum() throws Exception {
        BigInteger num = safe4.safe3.getLockedAddrNum();
        System.out.println(num);
        Assertions.assertTrue(num.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetLockedAddrs() throws Exception {
        List<String> safe3Addrs = safe4.safe3.getLockedAddrs(BigInteger.ZERO, BigInteger.TEN);
        System.out.println(safe3Addrs);
        Assertions.assertTrue(safe3Addrs.size() > 0);
    }

    @Test
    public void testGetLockedNum() throws Exception {
        BigInteger num = safe4.safe3.getLockedNum("XuP9HFUwY37bAQ2cVsVv3T2BFBine6CbT9");
        System.out.println(num);
        Assertions.assertTrue(num.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetLockedInfo() throws Exception {
        List<LockedSafe3Info> infos = safe4.safe3.getLockedInfo("XuP9HFUwY37bAQ2cVsVv3T2BFBine6CbT9", BigInteger.ZERO, BigInteger.TEN);
        System.out.println(infos);
        Assertions.assertTrue(infos.size() > 0);
    }

    @Test
    public void testGetAllPettyNum() throws Exception {
        BigInteger num = safe4.safe3.getAllPettyNum();
        System.out.println(num);
        Assertions.assertTrue(num.compareTo(BigInteger.ZERO) > 0);
    }

    @Test
    public void testGetPettyInfos() throws Exception {
        List<AvailableSafe3Info> infos = safe4.safe3.getPettyInfos(BigInteger.ZERO, BigInteger.TEN);
        System.out.println(infos);
        Assertions.assertTrue(infos.size() > 0);
    }

    @Test
    public void testGetPettyInfo() throws Exception {
        AvailableSafe3Info info = safe4.safe3.getPettyInfo("XbdnSBZKFhKjjXj95xcwQMmexA6sB3pgEp");
        System.out.println(info);
        Assertions.assertTrue(info.safe3Addr.length() > 0);
    }

    @Test
    public void testExistAvailableNeedToRedeem() throws Exception {
        Boolean flag = safe4.safe3.existAvailableNeedToRedeem("XuPmDoaNb6rbNywefkTbESHXiYqNpYvaPU");
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testExistLockedNeedToRedeem() throws Exception {
        Boolean flag = safe4.safe3.existLockedNeedToRedeem("XuP9HFUwY37bAQ2cVsVv3T2BFBine6CbT9");
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testExistMasterNodeNeedToRedeem() throws Exception {
        Boolean flag = safe4.safe3.existMasterNodeNeedToRedeem("Xm7bqZeKBooWuQxb2EWjJxN2qjQVgN4AuU");
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testExistPettyNeedToRedeem() throws Exception {
        Boolean flag = safe4.safe3.existPettyNeedToRedeem("XbdnSBZKFhKjjXj95xcwQMmexA6sB3pgEp");
        System.out.println(flag);
        Assertions.assertTrue(flag);
    }
}
