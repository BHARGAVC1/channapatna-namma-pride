Channapatna Namma Pride 🎨🧸

An Android application focused on preserving the authenticity and cultural identity of traditional Channapatna toys through digital verification and artisan discovery.

📖 Overview

Channapatna Namma Pride is a modern Android application developed as part of the MindMatrix Industry Readiness Programme under VTU. The application helps users verify authentic Channapatna toys using Toy IDs or QR codes while also providing detailed information about artisans, handcrafted products, and workshop locations.

The project aims to support local artisans by improving product trust, digital visibility, and cultural awareness through a clean and user-friendly mobile experience.

✨ Features
🔍 Toy Verification
Verify toy authenticity using:
Manual Toy ID input
QR Code scanning
Invalid or unregistered products are detected instantly
👨‍🎨 Artisan Profiles
View artisan information including:
Name
Location
Experience
Craft specialization
🧸 Product Catalog
Browse a catalog of handcrafted Channapatna toys
View product details such as:
Material
Manufacturing process
Product images
🗺️ Map Integration
Explore artisan workshop locations using Google Maps
Simple and interactive map interface
❤️ Favorites System
Save favorite products locally
Quick access to preferred items
📶 Offline Support
Local data persistence using Room Database
Offline-first architecture for smoother user experience
🏗️ Architecture

The application follows MVVM (Model-View-ViewModel) architecture with proper separation of concerns.

UI Layer (Jetpack Compose)
        ↓
ViewModel Layer
        ↓
Repository Layer
        ↓
Room Database / Firebase
Architecture Highlights
Clean code structure
Repository pattern
State management using ViewModel
Dependency Injection using Hilt
Modular and scalable design
🛠️ Tech Stack
Technology	Purpose
Kotlin	Android Development
Jetpack Compose	Modern UI Toolkit
Room Database	Local Offline Storage
Firebase	Cloud & Backend Services
Hilt	Dependency Injection
Google Maps API	Location & Map Services
ML Kit	QR Code Scanning
MVVM Architecture	Clean Architecture Pattern
📂 Project Structure
com.channapatna.nammapride
│
├── data
│   ├── local
│   ├── remote
│   ├── repository
│
├── domain
│   ├── model
│   ├── usecase
│
├── di
│
├── navigation
│
├── ui
│   ├── screens
│   ├── components
│   ├── theme
│
├── viewmodel
│
└── util
📱 Screens Included
Home Screen
Toy Verification Screen
QR Scanner Screen
Product Catalog Screen
Product Detail Screen
Artisan Profile Screen
Favorites Screen
Map Screen
🚀 Getting Started
Prerequisites
Android Studio Hedgehog or above
Android SDK 28+
Kotlin support enabled
Google Maps API Key
Installation
1️⃣ Clone the Repository
git clone https://github.com/BHARGAVC1/channapatna-namma-pride.git
2️⃣ Open in Android Studio
Open Android Studio → Open Project → Select Project Folder
3️⃣ Add Google Maps API Key

Add your API key inside:

local.properties
MAPS_API_KEY=YOUR_API_KEY
4️⃣ Run the Application
Connect Emulator/Device → Click Run ▶
📊 Functional Modules
Module	Description
Verification Module	Validates toy authenticity
Catalog Module	Displays available toys
Artisan Module	Shows artisan details
Favorites Module	Saves favorite products
Maps Module	Displays artisan locations
⚡ Non-Functional Features
Fast UI rendering
Offline support
Responsive navigation
Maintainable MVVM structure
Android 9+ compatibility
🧪 Testing Focus

The project was tested for:

Navigation flow
Database operations
QR scanning
Offline data handling
Screen responsiveness
🎯 Project Goals
Promote authentic Channapatna craftsmanship
Improve trust in handcrafted products
Provide digital identity to artisans
Build a practical Android application using modern development practices
📸 Screenshots

Add screenshots inside the /screenshots folder and reference them here.

screenshots/
├── home_screen.png
├── catalog_screen.png
├── verification_screen.png
├── map_screen.png
└── artisan_profile.png
📚 Documentation

Project documents included:

Project Charter
Software Requirements Document (SRD)
Final Report
UI Design References
🔮 Future Improvements

Possible future enhancements:

Multi-language support
Advanced search filters
Cloud synchronization
Marketplace integration
Enhanced analytics dashboard
👨‍💻 Developed By
Bhargav C

VTU — Android App Development using Gen AI
Team Mavericks · Group G5

📧 bhargav27love@gmail.com

GitHub: BHARGAVC1

📜 License

This project is developed for educational and internship purposes under the MindMatrix Industry Readiness Programme.
