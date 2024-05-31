package com.anwang.types.sysproperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

@Getter
@Setter
@ToString
public class PropertyInfo extends DynamicStruct {
    private String name;
    private BigInteger val;
    private String description;
    private BigInteger createHeight;
    private BigInteger updateHeight;

    public PropertyInfo() {
        this.name = "";
        this.val = BigInteger.ZERO;
        this.description = "";
        this.createHeight = BigInteger.ZERO;
        this.updateHeight = BigInteger.ZERO;
    }

    public PropertyInfo(String name, BigInteger val, String description, BigInteger createHeight, BigInteger updateHeight) {
        super(new Utf8String(name), new Uint256(val), new Utf8String(description), new Uint256(createHeight), new Uint256(updateHeight));
        this.name = name;
        this.val = val;
        this.description = description;
        this.createHeight = createHeight;
        this.updateHeight = updateHeight;
    }

    public PropertyInfo(Utf8String name, Uint256 val, Utf8String description, Uint256 createHeight, Uint256 updateHeight) {
        super(name, val, description, createHeight, updateHeight);
        this.name = name.getValue();
        this.val = val.getValue();
        this.description = description.getValue();
        this.createHeight = createHeight.getValue();
        this.updateHeight = updateHeight.getValue();
    }
}
