<h1> 
    <a href="https://magician-io.com">Magician-web3</a> ·
    <img src="https://img.shields.io/badge/licenes-MIT-brightgreen.svg"/>
    <img src="https://img.shields.io/badge/jdk-8+-brightgreen.svg"/>
    <img src="https://img.shields.io/badge/maven-3.5.4+-brightgreen.svg"/>
    <img src="https://img.shields.io/badge/release-master-brightgreen.svg"/>
</h1>

Magician-web3 is a blockchain development kit. 
It consists of two functions. It scans the blockchain to monitor transactions based on the needs of developers. 
It does some secondary encapsulation of web3j, which can reduce the workload of developers in some common scenarios. 
Plans to support ETH (BSC, XSC, POLYGAN, etc.), SOL, TRON three chains

## Running environment

JDK8+

## Documentation

[https://magician-io.com](https://magician-io.com)

## Example

### Importing dependencies
```xml
<dependency>
    <groupId>com.github.yuyenews</groupId>
    <artifactId>Magician-web3</artifactId>
    <version>1.0.0</version>
</dependency>

<!-- This is the logging package, you must have it or the console will not see anything, any logging package that can bridge with slf4j is supported -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-jdk14</artifactId>
    <version>1.7.12</version>
</dependency>
```

### Create a scan task

Create a MonitorEvent

```java
import java.math.BigInteger;

/**
 * Create an implementation class for EthMonitorEvent
 */
public class EthMonitorEventImpl implements EthMonitorEvent {

    /**
     * set filters
     *
     * Filter the transaction records according to these conditions and trigger the call method
     * @return
     */
    @Override
    public EthMonitorFilter ethMonitorFilter() {
        return EthMonitorFilter.builder()
                .setMinValue(BigInteger.valueOf(100))
                .setToAddress("0xasdasdasdasdasdasdasdasdas")
                .setFunctionCode("0x1s8d5j6j");
    }

    /**
     * This method is triggered when a transaction matching the above filter criteria is encountered
     * @param transactionModel
     */
    @Override
    public void call(TransactionModel transactionModel) {
        
    }
}
```

Start a monitoring task

```java
// Initialize the thread pool, the number of core threads must be >= the number of chains you want to scan, it is recommended to equal the number of chains to be scanned
EventThreadPool.init(1);

// Open a scan task, if you want to scan multiple chains, you can open multiple tasks, 
// by copying the following code and modifying the corresponding configuration you can open a new task
MagicianBlockchainScan.create()
        .setRpcUrl("https://data-seed-prebsc-1-s1.binance.org:8545/")
        .setChainType(ChainType.ETH)
        .setScanPeriod(5000)
        .setScanSize(1000)
        .setBeginBlockNumber(BigInteger.valueOf(24318610))
        .addEthMonitorEvent(new EventOne())
        .addEthMonitorEvent(new EventThree())
        .addEthMonitorEvent(new EventTwo())
        .start();
```

### Other utils

```java
// ABI codec
EthAbiCodec ethAbiCodec = MagicianWeb3.getEthBuilder().getEthAbiCodec();

// Encode the function as inputData
String inputData = ethAbiCodec.getInputData("mint",
    new Address("0xqwasdasd"),
    new Utf8String("https://asdasdasdsadasd.json")
);

// Get the function's signature
String funcCode = ethAbiCodec.getFunAbiCode("mint",
    new Address("0xqwasdasd"),
    new Utf8String("https://asdasdasdsadasd.json")
);

// Decode inputData into raw data
List<Type> result = ethAbiCodec.decoderInputData("0xasdasdas00000000adasd",
    new TypeReference<Address>(){},
    new TypeReference<Utf8String>(){}
);

// ------------------------ More, if you are interested, you can visit the official website----------------------

Web3j web3j = Web3j.build(new HttpService(""));
String privateKey = "";

EthHelper ethHelper = MagicianWeb3.getEthBuilder().getEth(web3j, privateKey);

BigInteger amount = ethHelper.balanceOf(fromAddress);

ethHelper.transfer(
        toAddress,
        BigDecimal.valueOf(1),
        Convert.Unit.ETHER
);

EthContract ethContract = MagicianWeb3.getEthBuilder().getEthContract(web3j, privateKey);

List<Type> result =  ethContract.select(
        contractAddress,
        ethAbiCodec.getInputData(
        "balanceOf",
        new Address(toAddress)),
        new TypeReference<Uint256>() {}
);

System.out.println(result.get(0).getValue());

ethContract.sendRawTransaction(
        fromAddress,
        contractAddress,
        ethAbiCodec.getInputData(
            "transfer",
            new Address(toAddress),
            new Uint256(new BigInteger("1000000000000000000"))
        )
);
```