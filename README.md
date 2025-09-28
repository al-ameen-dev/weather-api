# 🌤️ Weather API

A Spring Boot RESTful API that aggregates **weather** and **location data** from multiple external sources.  
Includes rate limiting, response caching, logging, and Swagger UI for easy testing.

---

## 🚀 Features

- ✅ Current weather and forecast data
- ✅ Location search using geocoding
- ✅ External API integration: [Photon Komoot](https://photon.komoot.io/) & [Open-Meteo](https://open-meteo.com/)
- ✅ Caching with [Caffeine](https://github.com/ben-manes/caffeine)
- ✅ Rate limiting using [Bucket4j](https://github.com/bucket4j/bucket4j)
- ✅ Logging with Log4j2
- ✅ Health check endpoint
- ✅ OpenAPI / Swagger UI documentation

---

## 🛠️ Technologies Used

- Java 17+
- Spring Boot 3.x
- Maven
- Caffeine Cache
- Bucket4j
- Log4j2
- springdoc-openapi
- External APIs:
    - [Photon Komoot](https://photon.komoot.io/)
    - [Open-Meteo](https://open-meteo.com/)

---

## 📦 How to Run

### 🖥️ Clone the repository

```bash
git clone https://github.com/al-ameen-dev/weather-api.git
cd weather-api

./mvnw clean install   # ✅ Builds the project and downloads dependencies
./mvnw spring-boot:run # 🔥 Runs the application
```

---

## 📷 Screenshots

> Shows the full list of API endpoints with their request/response schemas, example parameters, and live testing interface.

---
### ✅ Postman – Current Weather Data
#### Current Weather for City
![currentweatherforcity1.JPG](..%2F..%2FweatherapiPics%2Fcurrentweatherforcity1.JPG)

> Demonstrates the `/weather/current?location=chennai` endpoint fetching real-time weather data.

![currentweatherforcity2.JPG](..%2F..%2FweatherapiPics%2Fcurrentweatherforcity2.JPG)

> Demonstrates the `/weather/current?location=nagercoil` endpoint fetching real-time weather data.

![currentweatherforcityerror1.JPG](..%2F..%2FweatherapiPics%2Fcurrentweatherforcityerror1.JPG)

> Demonstrates the `/weather/current?location=hjkfahfhafhakfdhh` endpoint error handling for invalid city names.

---

### ✅ Postman – Weather Forecasting Search Data
#### Weather forecasting for number of days for a particular city

![weatherforecast1.JPG](..%2F..%2FweatherapiPics%2Fweatherforecast1.JPG)

> Demonstrates the `/weather/forecast?location=chennai&days=3` endpoint returning forecasting data.

![weatherforecast2.JPG](..%2F..%2FweatherapiPics%2Fweatherforecast2.JPG)

> Demonstrates the `weather/forecast?location=alappuzha&days=10` endpoint returning forecasting data.

![weatherforecasterror1.JPG](..%2F..%2FweatherapiPics%2Fweatherforecasterror1.JPG)

> Demonstrates the `/weather/forecast?location=chennai&days=19` endpoint error handling invalid number of days.
---
### ✅ Postman – Location Search Data
#### Weahter data based fuzzy location search

![locationsearch1.JPG](..%2F..%2FweatherapiPics%2Flocationsearch1.JPG)

![locationsearch1_2.JPG](..%2F..%2FweatherapiPics%2Flocationsearch1_2.JPG)

> Demonstrates the `/locations/search?q=chen` endpoint returning weather data for locations based on fuzzy search.

![locationsearch2.JPG](..%2F..%2FweatherapiPics%2Flocationsearch2.JPG)

![locationsearch2_2.JPG](..%2F..%2FweatherapiPics%2Flocationsearch2_2.JPG)

> Demonstrates the `/locations/search?q=bangalo` endpoint returning weather data for locations based on fuzzy search.

![locationsearcherror.JPG](..%2F..%2FweatherapiPics%2Flocationsearcherror.JPG)

> Demonstrates the `/locations/search?q=943hkhjjsnhjs` endpoint error invalid names.

---

### ✅ Health Check Endpoint And Rate Limiting
#### Health check status and uptime of the application

![healthcheck.JPG](..%2F..%2FweatherapiPics%2Fhealthcheck.JPG)

> Simple `/health` endpoint showing the application is up and running status and uptime of the application.

---

### ✅ Rate Limiting Error

![ratelimiting.JPG](..%2F..%2FweatherapiPics%2Fratelimiting.JPG)

> API response when rate limit is exceeded (handled by Bucket4j), returning `429 Too Many Requests`.

---

### ✅ Swagger UI – API Documentation

![swaggerapi.JPG](..%2F..%2FweatherapiPics%2Fswaggerapi.JPG)

![swaggerapi2.JPG](..%2F..%2FweatherapiPics%2Fswaggerapi2.JPG)

---

All apis are optimized with rate limiting and caching with TTL.


