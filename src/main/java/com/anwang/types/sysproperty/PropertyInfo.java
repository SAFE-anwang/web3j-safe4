package com.anwang.types.sysproperty;

import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

public class PropertyInfo extends DynamicStruct {
    public String name;
    public BigInteger val;
    public String description;
    public BigInteger createHeight;
    public BigInteger updateHeight;

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

    @Override
    public String toString() {
        return "PropertyInfo{" +
                "name='" + name + '\'' +
                ", val=" + val +
                ", description='" + description + '\'' +
                ", createHeight=" + createHeight +
                ", updateHeight=" + updateHeight +
                '}';
    }
}
