package com.anwang.types.proposal;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

public class ProposalInfo extends DynamicStruct {
    private BigInteger id;
    private Address creator;
    private String title;
    private BigInteger payAmount;
    private BigInteger payTimes;
    private BigInteger startPayTime;
    private BigInteger endPayTime;
    private String description;
    private BigInteger state;
    private BigInteger createHeight;
    private BigInteger updateHeight;

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

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Address getCreator() {
        return creator;
    }

    public void setCreator(Address creator) {
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigInteger getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigInteger payAmount) {
        this.payAmount = payAmount;
    }

    public BigInteger getPayTimes() {
        return payTimes;
    }

    public void setPayTimes(BigInteger payTimes) {
        this.payTimes = payTimes;
    }

    public BigInteger getStartPayTime() {
        return startPayTime;
    }

    public void setStartPayTime(BigInteger startPayTime) {
        this.startPayTime = startPayTime;
    }

    public BigInteger getEndPayTime() {
        return endPayTime;
    }

    public void setEndPayTime(BigInteger endPayTime) {
        this.endPayTime = endPayTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigInteger getState() {
        return state;
    }

    public void setState(BigInteger state) {
        this.state = state;
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
