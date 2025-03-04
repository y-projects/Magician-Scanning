package com.blockchain.scanning.commons.config;

import com.blockchain.scanning.chain.RetryStrategy;
import com.blockchain.scanning.commons.enums.ChainType;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Configure the parameters required for this block scanning task
 */
public class BlockChainConfig {

    /**
     * ETH Node url
     */
    private List<HttpService> httpService;

    /**
     * Blockchain type (ETH, SOL, TRON, etc.)
     */
    private ChainType chainType;

    /**
     * Scan polling interval, milliseconds
     */
    private long scanPeriod = 5000;

    /**
     * Starting block height of the scan
     */
    private BigInteger beginBlockNumber = BigInteger.ONE;

    /**
     * Retry strategy
     */
    private RetryStrategy retryStrategy;

    public BlockChainConfig(){
        this.httpService = new ArrayList<>();
    }

    public List<HttpService> getHttpService() {
        return httpService;
    }

    public void addHttpService(HttpService httpService) {
        this.httpService.add(httpService);
    }

    public void setHttpService(List<HttpService> httpService) {
        this.httpService = httpService;
    }

    public ChainType getChainType() {
        return chainType;
    }

    public void setChainType(ChainType chainType) {
        this.chainType = chainType;
    }

    public long getScanPeriod() {
        return scanPeriod;
    }

    public void setScanPeriod(long scanPeriod) {
        this.scanPeriod = scanPeriod;
    }

    public BigInteger getBeginBlockNumber() {
        return beginBlockNumber;
    }

    public void setBeginBlockNumber(BigInteger beginBlockNumber) {
        this.beginBlockNumber = beginBlockNumber;
    }

    public RetryStrategy getRetryStrategy() {
        return retryStrategy;
    }

    public void setRetryStrategy(RetryStrategy retryStrategy) {
        this.retryStrategy = retryStrategy;
    }
}
