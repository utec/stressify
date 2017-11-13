# Smart Stressor
100% pure Java application designed to stress http endpoints and  measure its performance simpler and powerful.

# Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

# Prerequisites

What things you need to install the software and how to install them

```
java 1.7
```

End with an example of getting some data out of the system or using it for a little demo

# Steps

- Download or compile jar file
- Create data.csv
- Create script.txt
- Execute this from command line for continuous mode and 50 threads, just execute this:

```
java -jar smart-stressor.jar "data.csv" "script.txt" "report.csv" "date,start time,end time,http response code,custom logic,elapsed time" "continuous" 50
```


# Jar file

- Download it from

 [https://github.com/utec-open-source/dist-artifacts/blob/master/smart-stressor/1.0.0/smart-stressor.jar?raw=true](https://github.com/utec-open-source/dist-artifacts/blob/master/smart-stressor/1.0.0/smart-stressor.jar?raw=true)

- Or compile from source code

```
mvn clean package -DskipTests=true
```

If no errors in log, go to your target folder and a jar file called **smart-stressor.jar** will be ready to use.

# Data csv

This file is a simple csv, with data to test. Each column could be use as var inside groovy script.

Example:

```
userid
1
2
3
4
5
6
```

# Script groovy

This script contains the code whose objective is consume some http endpoint and create data for report.

```
//PARAMETERS

def url = "https://jsonplaceholder.typicode.com/posts?userId="+userid;
def headers = ['Accept':'application/json']


// ################
// DONT TOUCH THIS
// ################

Date start  = new Date()
long startMillis = start.getTime();

// set url
def connection = new URL(url).openConnection() as HttpURLConnection

// set some headers
headers.each{ k, v -> connection.setRequestProperty( k, v )}

connection.connect()

Date now  = new Date()
long nowMillis = now.getTime();

// prepare report

def output = new String[6]

output[0] = start.format( 'yyyy-M-d' )

output[1] = start.format( 'HH-m-ss-SSS' )

output[2] = now.format( 'HH-m-ss-SSS' )

output[3] = connection.responseCode

output[4] = JsonPath.parse(connection.inputStream.text).read('$[0].title') != null ? 'true' : 'false'

output[5] = nowMillis - startMillis

return output;
```

# Report csv

This file will contain an important report with this information for each endpoint consumed

```
date,start time,end time,http response code,custom logic, elapsed time
2017-11-14,15-13-55-255,15-13-56-639,200,true,1384
2017-11-14,15-13-57-342,15-13-57-345,200,true,3
2017-11-14,15-13-57-649,15-13-57-654,200,true,5
2017-11-14,15-13-57-995,15-13-57-998,200,true,3
2017-11-14,15-13-58-369,15-13-58-372,200,true,3
2017-11-14,15-13-58-704,15-13-58-707,200,true,3
```

# Continuous mode

Use this mode if you want to execute invocations in an continuous way.

From command line
```
java -jar smart-stressor.jar "/home/test/documents/data.csv" "/home/test/documents/script.txt" "/home/test/documents/report.csv" "date,start time,end time,http response code,custom logic, elapsed time" "continuous"
```

# Parallel mode

Use this mode if you want to execute invocations in an parallel way.

From command line
```
java -jar smart-stressor.jar "/home/test/documents/data.csv" "/home/test/documents/script.txt" "/home/test/documents/report.csv" "date,start time,end time,http response code,custom logic, elapsed time" "parallel"
```

# Threads

Use this parameter if you want to increase the number of executions. If this parameter is not send, number of executions will be the same as data.csv rows (witouh header)

From command line
```
java -jar smart-stressor.jar "/home/test/documents/data.csv" "/home/test/documents/script.txt" "/home/test/documents/report.csv" "date,start time,end time,http response code,custom logic, elapsed time" "continuous" 50
```

## Versioning

1.0.0

## Authors

* **Richard Leon Ingaruca** - *Initial work* - [JRichardsz](https://github.com/jrichardsz)


## License

This project is licensed under the Apache License V2 License - see the [LICENSE](LICENSE.md) file for details
