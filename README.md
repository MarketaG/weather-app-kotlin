# WeatherFlow - Android Weather Application

Weather forecast application built with Kotlin and Jetpack Compose, following MVVM architecture pattern.

*This project was created as a school assignment.*


## Technical Stack

### Architecture & Design
- **MVVM (Model-View-ViewModel)**
- **Jetpack Compose**
- **Material Design 3**
- **Kotlin Coroutines**
- **Navigation Component**

### Data & Storage
- **Room Database**
- **SharedPreferences**
- **Repository Pattern**

### Network & APIs
- **Retrofit**
- **OpenWeather API**
    - Current weather endpoint (`/weather`)
    - 5-day forecast endpoint (`/forecast`)
    - Geocoding API (`/geo/1.0/direct`)
- **Coil**

### Location Services
- **Google Play Services Location** - Device GPS/network location
- **Runtime Permissions** - ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION

## Setup Instructions

### Installation Steps

1. **Clone the repository**
```bash
   git clone https://github.com/MarketaG/weather-app-kotlin.git
   cd weather-app-kotlin
```

2. **Get OpenWeather API Key**
    - Visit [OpenWeather API](https://openweathermap.org/api)
    - Sign up for a free account
    - Navigate to API Keys section
    - Copy your API key

3. **Configure API Key**
    - Add your API key to the `gradle.properties` file:
```properties
     WEATHER_API_KEY=your_api_key_here
```

4. **Build and Run**
    - Open project in Android Studio
    - Sync Gradle file
    - Run on emulator or physical device

## Screens

- **Home** - Current weather with hourly forecast
- **Forecast** - 5-day weather forecast
- **Favorites** - Saved cities list
- **Search Dialog** - City search with current location option

## API Endpoints Used
```
GET /data/2.5/weather           # Current weather by city name
GET /data/2.5/weather           # Current weather by coordinates
GET /data/2.5/forecast          # 5-day forecast by city name
GET /data/2.5/forecast          # 5-day forecast by coordinates
GET /geo/1.0/direct             # Geocoding - city search
```

## Permissions

- `INTERNET` - Required for API calls
- `ACCESS_FINE_LOCATION` - GPS location for current weather
- `ACCESS_COARSE_LOCATION` - Network-based location fallback

## License

This project is licensed under the MIT License.

## Author

Markéta Grácová