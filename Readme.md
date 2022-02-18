# Narvar URL Shortner

# Design Decision
## Storage Choice
* Chose the MongoDB to store the mapping of short URL to actual URL
* Being a NoSQL database, MongoDB is faster to handle the read and write data which doesn't have relationships.

## Framework Choice
* Spring Boot with Java 11
* Though Spring Boot is not an event driven framework and doesn't scale really well for extreme traffic, but it is developer friendly to build a prototype in very short time.

## Length of Short Url
* 7 Digits with character class [0-9a-bA-B] of 62 values
* Possible number of URLs possible is ~3521 Billions
* For traffic of 50M requests per month and persistence of short URL for 10 years, Maximum combinations required are 50 * 10 * 12 = 6000 Millions(6 Billions)
* Hence, 7 digits of BASE-62 is more than sufficient to handle the traffic.

# Guide to run the application
* Execute the commands in the following order after opening the terminal in the codebase
* `./mvnw clean install`
* `cd target/`
* `export SPRING_MONGO_URI="mongodb://localhost:27017/narvar?readPreference=primary&appname=MongoDB%20Compass&ssl=false"` Replace with your Mongo URI and database name
* `echo $SPRING_MONGO_URI` Confirm the environment variable set
* `java -jar narvar-0.0.1-SNAPSHOT.jar`

# EndPoints
## Shorten URL
* API to shorten the url. It returns a unique key mapped to the given URL.
### Request curl
```
curl --location --request POST 'http://localhost:8080/url/shorten' \
--header 'Content-Type: application/json' \
--data-raw '{
    "url": "https://www.google.com/"
}'
```
### Response
```
{
    "success": true,
    "payload": {
        "longUrl": "https://www.google.com/",
        "shortKey": "ha"
    }
}
```

## Fetch Redirect URL
* API to fetch the original URL by providing the short key. It returns the original url mapped to the short key.
* The front end can integrate this API to read the short key as the path parameter and call this API on load and then redirect to the actual URL on getting the response. 
### Request curl
```
curl --location --request POST 'http://localhost:8080/url/redirect' \
--header 'Content-Type: application/json' \
--data-raw '{
    "shortKey": "ha"
}'
```
### Response
```
{
    "success": true,
    "payload": {
        "longUrl": "https://www.google.com/",
        "shortKey": "ha"
    }
}
```
