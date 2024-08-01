package com.anwang.types.proposal;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

public class ProposalInfo extends DynamicStruct {
    public BigInteger id;
    public Address creator;
    public String title;
    public BigInteger payAmount;
    public BigInteger payTimes;
    public BigInteger startPayTime;
    public BigInteger endPayTime;
    public String description;
    public BigInteger state;
    public BigInteger createHeight;
    public BigInteger updateHeight;

    public ProposalInfo(BigInteger id, Address creator, String title, BigInteger payAmount, BigInteger payTimes, BigInteger startPayTime, BigInteger endPayTime, String description, BigInteger state, BigInteger createHeight, BigInteger updateHeight) {
        super(new Uint256(id),
                creator,
                new Utf8String(title),
                new Uint256(payAmount),
                new Uint256(payTimes),
                new Uint256(startPayTime),
                new Uint256(endPayTime),
                new Utf8String(description),
                new Uint256(state),
                new Uint256(createHeight),
                new Uint256(updateHeight));
        this.id = id;
        this.creator = creator;
        this.title = title;
        this.payAmount = payAmount;
        this.payTimes = payTimes;
        this.startPayTime = startPayTime;
        this.endPayTime = endPayTime;
        this.description = description;
        this.state = state;
        this.createHeight = createHeight;
        this.updateHeight = updateHeight;
    }

    public ProposalInfo(Uint256 id, Address creator, Utf8String title, Uint256 payAmount, Uint256 payTimes, Uint256 startPayTime, Uint256 endPayTime, Utf8String description, Uint256 state, Uint256 createHeight, Uint256 updateHeight) {
        super(id, creator, title, payAmount, payTimes, startPayTime, endPayTime, description, state, createHeight, updateHeight);
        this.id = id.getValue();
        this.creator = creator;
        this.title = title.getValue();
        this.payAmount = payAmount.getValue();
        this.payTimes = payTimes.getValue();
        this.startPayTime = startPayTime.getValue();
        this.endPayTime = endPayTime.getValue();
        this.description = description.getValue();
        this.state = state.getValue();
        this.createHeight = createHeight.getValue();
        this.updateHeight = updateHeight.getValue();
    }

    @Override
    public String toString() {
        return "ProposalInfo{" +
                "id=" + id +
                ", creator=" + creator +
                ", title='" + title + '\'' +
                ", payAmount=" + payAmount +
                ", payTimes=" + payTimes +
                ", startPayTime=" + startPayTime +
                ", endPayTime=" + endPayTime +
                ", description='" + description + '\'' +
                ", state=" + state +
                ", createHeight=" + createHeight +
                ", updateHeight=" + updateHeight +
                '}';
    }
}
