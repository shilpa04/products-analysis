# Products Analysis 

Run `ProductAnalysisApplication.java` for boot up the server. Application will start running on http://localhost:8080/


## To Check Swagger Documentation

Open http://localhost:8080/products-analysis/swagger-ui/index.html on browser.


## To Execute test cases

`gradle test` Command needs to be run to execute the test cases for the all the APIs.

## Assumptions 

- This application can analyse multiple products.
-- To register a product: Open `product-controller` and use `POST /products` API
- User has to download the Product before Subscribing it.
-- To download: Open `download-details-controller` and use `POST /downloadDetails` API
- Objective 1 : No of times product is downloaded (assumed unique users here)
-- Open `download-details-controller` and use `GET /uniqueDownloads/{productId}` API
- Added Functionality -> To fetch Volume downloads i.e. average download per user -> total no of downloads/ total unique users
-- Open `download-details-controller` and use `GET /volumeDownloads/{productId}` API
- Now to subscribe user has to be present and should have already downloaded the product
-- Open `subscriber-controller` and use `POST /subscribe` API
- Objective 2: To fetch Total no of subscribers
-- Open `subscriber-controller` and use `GET /subscribers/{productId}` API
