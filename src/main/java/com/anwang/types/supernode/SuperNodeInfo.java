package com.anwang.types.supernode;

import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.reflection.Parameterized;

import java.math.BigInteger;
import java.util.List;

public class SuperNodeInfo extends DynamicStruct {
    public BigInteger id;
    public String name;
    public Address addr;
    public Address creator;
    public String enode;
    public String description;
    public Boolean isOfficial;
    public BigInteger state;
    public List<SuperNodeMemberInfo> founders;
    public SuperNodeIncentivePlan incentivePlan;
    public BigInteger lastRewardHeight;
    public BigInteger createHeight;
    public BigInteger updateHeight;

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
