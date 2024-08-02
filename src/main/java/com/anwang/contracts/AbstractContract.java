package com.anwang.contracts;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.Sign;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.List;

import static org.web3j.crypto.Keys.getAddress;

public abstract class AbstractContract {
    protected Web3j web3j;
    protected long chainId;
    protected String contractAddr;

    AbstractContract(Web3j web3j, long chainId, String contractAddr) {
        this.web3j = web3j;
        this.chainId = chainId;
        this.contractAddr = contractAddr;
    }

    public BigInteger getNonce(String address) throws Exception {
        EthGetTransactionCount ethGetTransactionCount =
                web3j.ethGetTransactionCount(address, DefaultBlockParameterName.PENDING)
                        .sendAsync()
                        .get();
        return ethGetTransactionCount.getTransactionCount();
    }

    public BigInteger getGasPrice() throws Exception {
        EthGasPrice ethGasPrice = web3j.ethGasPrice().send();
        return ethGasPrice.getGasPrice();
    }

    public static String getAddressFromPrivateKey(String privateKey) {
        BigInteger privKey = Numeric.toBigInt(privateKey);
        final BigInteger publicKey = Sign.publicKeyFromPrivate(privKey);
        return new Address(getAddress(publicKey)).toString();
    }

    public String call(String privateKey, Function function) throws Exception {
        return call(privateKey, BigInteger.ZERO, function);
    }

    public String call(String privateKey, BigInteger value, Function function) throws Exception {
        Credentials credentials = Credentials.create(privateKey);
        String address = getAddressFromPrivateKey(privateKey);
        BigInteger nonce = getNonce(address);
        BigInteger gasPrice = getGasPrice();
        String data = FunctionEncoder.encode(function);
        EthEstimateGas ethEstimateGas = web3j.ethEstimateGas(Transaction.createFunctionCallTransaction(address, nonce, gasPrice, BigInteger.ZERO, contractAddr, value, data)).send();
        if (ethEstimateGas.getError() != null) {
            throw new Exception(ethEstimateGas.getError().getMessage());
        }
        BigInteger gasLimit = ethEstimateGas.getAmountUsed();
        RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, contractAddr, value, data);
        final String signedTransactionData = Numeric.toHexString(TransactionEncoder.signMessage(rawTransaction, chainId, credentials));
        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(signedTransactionData).sendAsync().get();
        if (ethSendTransaction.hasError()) {
            throw new Exception(ethSendTransaction.getError().getMessage());
        }
        return ethSendTransaction.getTransactionHash();
    }

    public List<Type> query(Function function) throws Exception {
        return query(function, Address.DEFAULT.getValue());
    }

    public List<Type> query(Function function, String from) throws Exception {
        EthCall response = web3j.ethCall(Transaction.createEthCallTransaction(from, contractAddr, FunctionEncoder.encode(function)), DefaultBlockParameterName.LATEST).sendAsync().get();
        if (response.getError() != null) {
            throw new Exception(response.getError().getMessage());
        }
        return FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters());
    }
}
