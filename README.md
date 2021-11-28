# Receipt

## Table of Contents

* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Additional information](#additional-information)

## General info
This project is console application which print check in the store.

## Technologies
Project is created with:
* Java 11
* Maven 3.6.3
* JUnit 5

## Setup
```
git clone https://github.com/Gempton/Receipt.git
cd ../receiptMachine
mvn install
mvn clean package
```

If you want to run without discount card
```
mvn exec:java -Dexec.mainClass=by.java.testTask.Demo -Dexec.args="1-10 6-7 3-3 7-8 8-11"
mvn exec:java -Dexec.mainClass=by.java.testTask.Demo -Dexec.args="1-3 6-5 3-7 7-8 9-10"
mvn exec:java -Dexec.mainClass=by.java.testTask.Demo -Dexec.args="1-3"
mvn exec:java -Dexec.mainClass=by.java.testTask.Demo -Dexec.args="1-3 5-6"
```

If you want to run with discount card
```
mvn exec:java -Dexec.mainClass=by.java.testTask.Demo -Dexec.args="1-10 6-7 3-3 4-1 6-8 card-1234"
mvn exec:java -Dexec.mainClass=by.java.testTask.Demo -Dexec.args="1-3 6-5 3-7 7-8 9-10 card-1234"
mvn exec:java -Dexec.mainClass=by.java.testTask.Demo -Dexec.args="1-3 card-1234"
mvn exec:java -Dexec.mainClass=by.java.testTask.Demo -Dexec.args="1-3 5-6 card-1234"
```
Where for example "1-10 6-7 3-3":<br />
1, 6, 3 - productId(Total count of products shouldn't be more then 10)<br />
10, 7, 3 - count of product

### If you want to run tests:
```
mvn test
```

## Additional information
If count of product more then five - product has discount 10%.<br />
If customer has a card - total price of all products has discount 15%.

