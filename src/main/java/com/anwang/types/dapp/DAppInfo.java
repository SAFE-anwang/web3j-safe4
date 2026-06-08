package com.anwang.types.dapp;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.reflection.Parameterized;

import java.math.BigInteger;

public class DAppInfo extends DynamicStruct {
    public BigInteger id;
    public String name;
    public Address contractAddr;
    public String runUrl;
    public String gitUrl;
    public String officialUrl;
    public String officialEmail;
    public Address officialAccount;
    public String keyword;
    public BigInteger fraudNum;
    public Boolean isFrozen;

    public DAppInfo(BigInteger id, String name, Address contractAddr, String runUrl, String gitUrl, String officialUrl, String officialEmail, Address officialAccount, String keyword, BigInteger fraudNum, Boolean isFrozen) {
        super(new Uint256(id),
                new Utf8String(name),
                contractAddr,
                new Utf8String(runUrl),
                new Utf8String(gitUrl),
                new Utf8String(officialUrl),
                new Utf8String(officialEmail),
                officialAccount,
                new Utf8String(keyword),
                new Uint256(fraudNum),
                new Bool(isFrozen));
        this.id = id;
        this.name = name;
        this.contractAddr = contractAddr;
        this.runUrl = runUrl;
        this.gitUrl = gitUrl;
        this.officialUrl = officialUrl;
        this.officialEmail = officialEmail;
        this.officialAccount = officialAccount;
        this.keyword = keyword;
        this.fraudNum = fraudNum;
        this.isFrozen = isFrozen;
    }

    public DAppInfo(@Parameterized(type = Uint256.class) Uint256 id,
                    @Parameterized(type = Utf8String.class) Utf8String name,
                    @Parameterized(type = Address.class) Address contractAddr,
                    @Parameterized(type = Utf8String.class) Utf8String runUrl,
                    @Parameterized(type = Utf8String.class) Utf8String gitUrl,
                    @Parameterized(type = Utf8String.class) Utf8String officialUrl,
                    @Parameterized(type = Utf8String.class) Utf8String officialEmail,
                    @Parameterized(type = Address.class) Address officialAccount,
                    @Parameterized(type = Utf8String.class) Utf8String keyword,
                    @Parameterized(type = Uint256.class) Uint256 fraudNum,
                    @Parameterized(type = Bool.class) Bool isFrozen) {
        super(id, name, contractAddr, runUrl, gitUrl, officialUrl, officialEmail, officialAccount, keyword, fraudNum, isFrozen);
        this.id = id.getValue();
        this.name = name.getValue();
        this.contractAddr = contractAddr;
        this.runUrl = runUrl.getValue();
        this.gitUrl = gitUrl.getValue();
        this.officialUrl = officialUrl.getValue();
        this.officialEmail = officialEmail.getValue();
        this.officialAccount = officialAccount;
        this.keyword = keyword.getValue();
        this.fraudNum = fraudNum.getValue();
        this.isFrozen = isFrozen.getValue();
    }

    @Override
    public String toString() {
        return "DAppInfo{" +
                "id=" + id +
                ", name=" + name +
                ", contractAddr=" + contractAddr +
                ", runUrl='" + runUrl + '\'' +
                ", gitUrl='" + gitUrl + '\'' +
                ", officialUrl=" + officialUrl +
                ", officialEmail=" + officialEmail +
                ", officialAccount=" + officialAccount +
                ", keyword=" + keyword +
                ", fraudNum=" + fraudNum +
                ", isFrozen=" + isFrozen +
                '}';
    }
}
