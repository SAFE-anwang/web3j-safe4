package com.anwang.types.supernode;

import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.reflection.Parameterized;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SuperNodeInfo extends DynamicStruct {
    private BigInteger id;
    private String name;
    private Address addr;
    private Address creator;
    private String enode;
    private String description;
    private Boolean isOfficial;
    private BigInteger state;
    private List<SuperNodeMemberInfo> founders;
    private SuperNodeIncentivePlan incentivePlan;
    private BigInteger lastRewardHeight;
    private BigInteger createHeight;
    private BigInteger updateHeight;

    public SuperNodeInfo(BigInteger id, String name, Address addr, Address creator, String enode, String description, Boolean isOfficial, BigInteger state, List<SuperNodeMemberInfo> founders, SuperNodeIncentivePlan incentivePlan, BigInteger lastRewardHeight, BigInteger createHeight, BigInteger updateHeight) {
        super(new Uint256(id),
                new Utf8String(name),
                addr,
                creator,
                new Utf8String(enode),
                new Utf8String(description),
                new Bool(isOfficial),
                new Uint256(state),
                new DynamicArray<>(SuperNodeMemberInfo.class, founders),
                incentivePlan,
                new Uint256(lastRewardHeight),
                new Uint256(createHeight),
                new Uint256(updateHeight));
        this.id = id;
        this.name = name;
        this.addr = addr;
        this.creator = creator;
        this.enode = enode;
        this.description = description;
        this.isOfficial = isOfficial;
        this.state = state;
        this.founders = founders;
        this.incentivePlan = incentivePlan;
        this.lastRewardHeight = lastRewardHeight;
        this.createHeight = createHeight;
        this.updateHeight = updateHeight;
    }

    public SuperNodeInfo(@Parameterized(type = Uint256.class) Uint256 id,
                         @Parameterized(type = Utf8String.class) Utf8String name,
                         @Parameterized(type = Address.class) Address addr,
                         @Parameterized(type = Address.class) Address creator,
                         @Parameterized(type = Utf8String.class) Utf8String enode,
                         @Parameterized(type = Utf8String.class) Utf8String description,
                         @Parameterized(type = Bool.class) Bool isOfficial,
                         @Parameterized(type = Uint256.class) Uint256 state,
                         @Parameterized(type = SuperNodeMemberInfo.class) DynamicArray<SuperNodeMemberInfo> founders,
                         @Parameterized(type = SuperNodeIncentivePlan.class) SuperNodeIncentivePlan incentivePlan,
                         @Parameterized(type = Uint256.class) Uint256 lastRewardHeight,
                         @Parameterized(type = Uint256.class) Uint256 createHeight,
                         @Parameterized(type = Uint256.class) Uint256 updateHeight) {
        super(id, name, addr, creator, enode, description, isOfficial, state, founders, incentivePlan, lastRewardHeight, createHeight, updateHeight);
        this.id = id.getValue();
        this.name = name.getValue();
        this.addr = addr;
        this.creator = creator;
        this.enode = enode.getValue();
        this.description = description.getValue();
        this.isOfficial = isOfficial.getValue();
        this.state = state.getValue();
        this.founders = founders.getValue();
        this.incentivePlan = incentivePlan;
        this.lastRewardHeight = lastRewardHeight.getValue();
        this.createHeight = createHeight.getValue();
        this.updateHeight = updateHeight.getValue();
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddr() {
        return addr;
    }

    public void setAddr(Address addr) {
        this.addr = addr;
    }

    public Address getCreator() {
        return creator;
    }

    public void setCreator(Address creator) {
        this.creator = creator;
    }

    public String getEnode() {
        return enode;
    }

    public void setEnode(String enode) {
        this.enode = enode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getOfficial() {
        return isOfficial;
    }

    public void setOfficial(Boolean official) {
        isOfficial = official;
    }

    public BigInteger getState() {
        return state;
    }

    public void setState(BigInteger state) {
        this.state = state;
    }

    public List<SuperNodeMemberInfo> getFounders() {
        return founders;
    }

    public void setFounders(List<SuperNodeMemberInfo> founders) {
        this.founders = founders;
    }

    public SuperNodeIncentivePlan getIncentivePlan() {
        return incentivePlan;
    }

    public void setIncentivePlan(SuperNodeIncentivePlan incentivePlan) {
        this.incentivePlan = incentivePlan;
    }

    public BigInteger getLastRewardHeight() {
        return lastRewardHeight;
    }

    public void setLastRewardHeight(BigInteger lastRewardHeight) {
        this.lastRewardHeight = lastRewardHeight;
    }

    public BigInteger getCreateHeight() {
        return createHeight;
    }

    public void setCreateHeight(BigInteger createHeight) {
        this.createHeight = createHeight;
    }

    public BigInteger getUpdateHeight() {
        return updateHeight;
    }

    public void setUpdateHeight(BigInteger updateHeight) {
        this.updateHeight = updateHeight;
    }

    @Override
    public String toString() {
        return "SuperNodeInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addr=" + addr +
                ", creator=" + creator +
                ", enode='" + enode + '\'' +
                ", description='" + description + '\'' +
                ", isOfficial=" + isOfficial +
                ", state=" + state +
                ", founders=" + founders +
                ", incentivePlan=" + incentivePlan +
                ", lastRewardHeight=" + lastRewardHeight +
                ", createHeight=" + createHeight +
                ", updateHeight=" + updateHeight +
                '}';
    }
}
