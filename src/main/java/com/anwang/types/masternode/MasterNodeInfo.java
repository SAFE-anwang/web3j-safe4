package com.anwang.types.masternode;

import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.reflection.Parameterized;

import java.math.BigInteger;
import java.util.List;

public class MasterNodeInfo extends DynamicStruct {
    public BigInteger id;
    public Address addr;
    public Address creator;
    public String enode;
    public String description;
    public Boolean isOfficial;
    public BigInteger state;
    public List<MasterNodeMemberInfo> founders;
    public MasterNodeIncentivePlan incentivePlan;
    public Boolean isUnion;
    public BigInteger lastRewardHeight;
    public BigInteger createHeight;
    public BigInteger updateHeight;

    public MasterNodeInfo(BigInteger id, Address addr, Address creator, String enode, String description, Boolean isOfficial, BigInteger state, List<MasterNodeMemberInfo> founders, MasterNodeIncentivePlan incentivePlan, Boolean isUnion, BigInteger lastRewardHeight, BigInteger createHeight, BigInteger updateHeight) {
        super(new Uint256(id),
                addr,
                creator,
                new Utf8String(enode),
                new Utf8String(description),
                new Bool(isOfficial),
                new Uint256(state),
                new DynamicArray<>(MasterNodeMemberInfo.class, founders),
                incentivePlan,
                new Bool(isUnion),
                new Uint256(lastRewardHeight),
                new Uint256(createHeight),
                new Uint256(updateHeight));
        this.id = id;
        this.addr = addr;
        this.creator = creator;
        this.enode = enode;
        this.description = description;
        this.isOfficial = isOfficial;
        this.state = state;
        this.founders = founders;
        this.incentivePlan = incentivePlan;
        this.isUnion = isUnion;
        this.lastRewardHeight = lastRewardHeight;
        this.createHeight = createHeight;
        this.updateHeight = updateHeight;
    }

    public MasterNodeInfo(@Parameterized(type = Uint256.class) Uint256 id,
                          @Parameterized(type = Address.class) Address addr,
                          @Parameterized(type = Address.class) Address creator,
                          @Parameterized(type = Utf8String.class) Utf8String enode,
                          @Parameterized(type = Utf8String.class) Utf8String description,
                          @Parameterized(type = Bool.class) Bool isOfficial,
                          @Parameterized(type = Uint256.class) Uint256 state,
                          @Parameterized(type = MasterNodeMemberInfo.class) DynamicArray<MasterNodeMemberInfo> founders,
                          @Parameterized(type = MasterNodeIncentivePlan.class) MasterNodeIncentivePlan incentivePlan,
                          @Parameterized(type = Bool.class) Bool isUnion,
                          @Parameterized(type = Uint256.class) Uint256 lastRewardHeight,
                          @Parameterized(type = Uint256.class) Uint256 createHeight,
                          @Parameterized(type = Uint256.class) Uint256 updateHeight) {
        super(id, addr, creator, enode, description, isOfficial, state, founders, incentivePlan, isUnion, lastRewardHeight, createHeight, updateHeight);
        this.id = id.getValue();
        this.addr = addr;
        this.creator = creator;
        this.enode = enode.getValue();
        this.description = description.getValue();
        this.isOfficial = isOfficial.getValue();
        this.state = state.getValue();
        this.founders = founders.getValue();
        this.incentivePlan = incentivePlan;
        this.isUnion = isUnion.getValue();
        this.lastRewardHeight = lastRewardHeight.getValue();
        this.createHeight = createHeight.getValue();
        this.updateHeight = updateHeight.getValue();
    }

    @Override
    public String toString() {
        return "MasterNodeInfo{" +
                "id=" + id +
                ", addr=" + addr +
                ", creator=" + creator +
                ", enode='" + enode + '\'' +
                ", description='" + description + '\'' +
                ", isOfficial=" + isOfficial +
                ", state=" + state +
                ", founders=" + founders +
                ", incentivePlan=" + incentivePlan +
                ", isUnion=" + isUnion +
                ", lastRewardHeight=" + lastRewardHeight +
                ", createHeight=" + createHeight +
                ", updateHeight=" + updateHeight +
                '}';
    }
}
