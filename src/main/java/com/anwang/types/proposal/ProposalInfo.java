package com.anwang.types.proposal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

@Getter
@Setter
@ToString
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

    public ProposalInfo() {
        this.id = BigInteger.ZERO;
        this.creator = Address.DEFAULT;
        this.title = "";
        this.payAmount = BigInteger.ZERO;
        this.payTimes = BigInteger.ZERO;
        this.startPayTime = BigInteger.ZERO;
        this.endPayTime = BigInteger.ZERO;
        this.description = "";
        this.state = BigInteger.ZERO;
        this.createHeight = BigInteger.ZERO;
        this.updateHeight = BigInteger.ZERO;
    }

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
}
