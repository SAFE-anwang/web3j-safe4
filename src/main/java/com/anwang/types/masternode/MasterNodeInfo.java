package com.anwang.types.masternode;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.reflection.Parameterized;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class MasterNodeInfo extends DynamicStruct {
    private BigInteger id;
    private Address addr;
    private Address creator;
    private String enode;
    private String description;
    private Boolean isOfficial;
    private BigInteger state;
    private List<MasterNodeMemberInfo> founders;
    private MasterNodeIncentivePlan incentivePlan;
    private BigInteger lastRewardHeight;
    private BigInteger createHeight;
    private BigInteger updateHeight;

    public MasterNodeInfo() {
        this.id = BigInteger.ZERO;
        this.addr = Address.DEFAULT;
        this.creator = Address.DEFAULT;
        this.enode = "";
        this.description = "";
        this.isOfficial = false;
        this.state = BigInteger.ZERO;
        this.founders = new ArrayList<>();
        this.incentivePlan = new MasterNodeIncentivePlan();
        this.lastRewardHeight = BigInteger.ZERO;
        this.createHeight = BigInteger.ZERO;
        this.updateHeight = BigInteger.ZERO;
    }

    public MasterNodeInfo(BigInteger id, Address addr, Address creator, String enode, String description, Boolean isOfficial, BigInteger state, List<MasterNodeMemberInfo> founders, MasterNodeIncentivePlan incentivePlan, BigInteger lastRewardHeight, BigInteger createHeight, BigInteger updateHeight) {
        super(new Uint256(id),
                addr,
                creator,
                new Utf8String(enode),
                new Utf8String(description),
                new Bool(isOfficial),
                new Uint256(state),
                new DynamicArray<>(MasterNodeMemberInfo.class, founders),
                incentivePlan,
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
                          @Parameterized(type = Uint256.class) Uint256 lastRewardHeight,
                          @Parameterized(type = Uint256.class) Uint256 createHeight,
                          @Parameterized(type = Uint256.class) Uint256 updateHeight) {
        super(id, addr, creator, enode, description, isOfficial, state, founders, incentivePlan, lastRewardHeight, createHeight, updateHeight);
        this.id = id.getValue();
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
}
