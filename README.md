# web3j-safe4
SAFE4 extension Web3 API for JAVA

## Compile

`mvn clean package -DskipTests`

## Publish

upload to maven central (artifact signing uses `-P release`; gpg on `PATH`)

`mvn clean deploy -DskipTests=true -Prelease -e`