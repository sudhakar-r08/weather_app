# Weather App 🌤️

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue.svg)](https://kotlinlang.org)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)

A beautiful and intuitive weather application for Android that provides real-time weather information with a clean, elegant user interface. Get accurate weather forecasts for any location worldwide with just a few taps!

## ✨ Features

- 🆓 **Completely Free** - No ads, no premium subscriptions
- 🌍 **Worldwide Coverage** - Check weather for any city (Chennai, Mumbai, London, Paris, San Francisco, Houston, etc.)
- 📍 **Current Location** - Automatic weather detection for your current location
- 🔄 **Live Updates** - Real-time weather forecasts and conditions
- 📅 **5-Day Forecast** - Weekly weather information to plan ahead
- 🎨 **Elegant UI** - Simple, clean, and user-friendly design
- ⚡ **Fast & Reliable** - Quick loading with offline caching
- 🏗️ **MVVM Architecture** - Modern Android architecture for better performance

## 📱 Screenshots

<!-- Add your app screenshots here -->

<img src="https://user-images.githubusercontent.com/17586972/155201802-c780b810-4418-4863-bc16-aeab4ddd3a7f.jpg" width="320"/> <img src="https://user-images.githubusercontent.com/17586972/155201825-b59afa41-5f0a-43f1-a4cc-8a6bcca1d737.jpg" width="320"/> <img src="https://user-images.githubusercontent.com/17586972/155201843-cd7abc23-cbbc-4a66-a516-ba234fdbbd70.jpg" width="320"/>


## 🛠️ Tech Stack

- **Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **API**: OpenWeatherMap API
- **Libraries**:
  - Retrofit for API calls
  - ViewModel & LiveData
  - Location Services
  - Coroutines for asynchronous operations
  - Data Binding
  - Navigation Component

## 🏗️ Architecture

The app follows **MVVM (Model-View-ViewModel)** architecture pattern which provides:

- ✅ **Unidirectional Data Flow**
- ✅ **Separation of Concerns**
- ✅ **Better Testability**
- ✅ **Lifecycle Awareness**
- ✅ **Configuration Change Handling**

```
┌─────────────┐    ┌─────────────┐    ┌─────────────┐
│    View     │────│  ViewModel  │────│    Model    │
│ (Activity/  │    │             │    │ (Repository)│
│  Fragment)  │    │             │    │             │
└─────────────┘    └─────────────┘    └─────────────┘
```

## 🚀 Installation

### Prerequisites
- Android Studio Arctic Fox or newer
- Android SDK 21 or higher
- OpenWeatherMap API Key

### Setup Instructions

1. **Clone the repository**
```bash
git clone https://github.com/sudhakar-r08/weather_app.git
cd weather_app
```

2. **Get OpenWeatherMap API Key**
   - Visit [OpenWeatherMap](https://openweathermap.org/api)
   - Create a free account
   - Generate your API key

3. **Add API Key**
   - Create `local.properties` file in root directory (if not exists)
   - Add your API key:
```properties
API_KEY="your_openweathermap_api_key_here"
```

4. **Build and Run**
   - Open project in Android Studio
   - Sync project with Gradle files
   - Run the app on device or emulator

## 📖 Usage

### Get Current Location Weather
1. Launch the app
2. Grant location permission when prompted
3. Your current weather will be displayed automatically

### Search for Any City
1. Tap the search icon
2. Enter city name (e.g., "Chennai", "London", "Tokyo")
3. Select from suggestions or press search
4. View detailed weather information

### View 5-Day Forecast
1. Scroll down on the main screen
2. View detailed daily forecasts
3. Tap on any day for more details

## 📡 Weather Data Source

Weather data is powered by **[OpenWeatherMap](https://openweathermap.org/)** - one of the most reliable weather data providers.

**Data Includes:**
- Current temperature and conditions
- Humidity and wind speed
- Visibility and pressure
- UV index and feels-like temperature
- 5-day detailed forecast
- Weather icons and descriptions

## 🎯 Key Features Breakdown

### 🌡️ Current Weather
- Real-time temperature
- Weather conditions with icons
- Humidity, wind speed, pressure
- Sunrise and sunset times

### 📍 Location Services
- GPS-based current location
- Manual city search
- Recent locations history
- Favorite locations (if implemented)

### 📊 Extended Forecast
- 5-day weather outlook
- Daily high/low temperatures
- Weather condition predictions
- Hourly forecasts (if available)

## 🔒 Permissions

The app requires the following permissions:

- **ACCESS_FINE_LOCATION**: To get precise location for accurate weather data
- **ACCESS_COARSE_LOCATION**: Fallback for approximate location
- **INTERNET**: To fetch weather data from API

## 🧪 Testing

Run the tests using:

```bash
# Unit Tests
./gradlew test

# Instrumentation Tests
./gradlew connectedAndroidTest
```

## 🤝 Contributing

Contributions are welcome! Please feel free to submit issues, fork the repository, and create pull requests.

### Development Guidelines

1. Follow Kotlin coding standards
2. Maintain MVVM architecture pattern
3. Write unit tests for new features
4. Update documentation as needed

### Development Setup

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📋 TODO / Future Enhancements

- [ ] Weather widgets for home screen
- [ ] Weather alerts and notifications
- [ ] Multiple location management
- [ ] Weather maps integration
- [ ] Dark mode support
- [ ] Weather sharing functionality
- [ ] Historical weather data

## 📄 License

```
MIT License

Copyright (c) 2025 Sudhakar Raju

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## 👤 Developer

**Sudhakar Raju**
- GitHub: [@sudhakar-r08](https://github.com/sudhakar-r08)
- LinkedIn: [sudhakar-raju](https://www.linkedin.com/in/sudhakar-raju/)

## 🙏 Acknowledgments

- [OpenWeatherMap](https://openweathermap.org/) for providing reliable weather data
- Android community for excellent documentation and resources
- Contributors and users who provide feedback

## 📞 Support

If you encounter any issues or have questions:

- 🐛 **Report Issues**: [GitHub Issues](https://github.com/sudhakar-r08/weather_app/issues)
- 💬 **Discussions**: [GitHub Discussions](https://github.com/sudhakar-r08/weather_app/discussions)
- 📧 **Email**: [sudhakar.r08@gmail.com](sudhakar.r08@gmail.com)

---

⭐ **If you found this project helpful, please give it a star!** ⭐

Made with ❤️ in Bengaluru, India
