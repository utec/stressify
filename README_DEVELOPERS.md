# Developer Mode (Commandline)

- Download or compile jar file
- Create data.csv
- Create script.txt
- Execute cmd

# Jar file

- Download it from

 [5minutes-stressor-tool.jar](https://github.com/utec/5minutes-stressor-tool/releases/download/v1.0.1/5minutes-stressor-tool.jar)

- Or compile from source code

```
mvn clean package -DskipTests=true
```

If no errors in log, go to your target folder and a jar file called **5minutes-stressor-tool-x.y.z.jar** will be ready to use.

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

# Execute Continuous mode

Use this mode if you want to execute invocations in an continuous way.

From command line
```
java -jar 5minutes-stressor-tool.jar "app_mode_cmd"  "/home/test/documents/data.csv" "/home/test/documents/script.txt" "/home/test/documents/report.csv" "date,start time,end time,http response code,custom logic, elapsed time" "continuous"
```

# Parallel mode

Use this mode if you want to execute invocations in an parallel way.

From command line
```
java -jar 5minutes-stressor-tool.jar "app_mode_cmd"  "/home/test/documents/data.csv" "/home/test/documents/script.txt" "/home/test/documents/report.csv" "date,start time,end time,http response code,custom logic, elapsed time" "parallel"
```

# Threads

Use this parameter if you want to increase the number of executions. If this parameter is not send, number of executions will be the same as data.csv rows (witouh header)

From command line
```
java -jar 5minutes-stressor-tool.jar "app_mode_cmd"  "/home/test/documents/data.csv" "/home/test/documents/script.txt" "/home/test/documents/report.csv" "date,start time,end time,http response code,custom logic, elapsed time" "continuous" 50
```
