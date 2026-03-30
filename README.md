# 📱 Photo App - Android Assignment

## 📌 Overview
This is an Android application developed using **Kotlin** that fetches and displays photos from a remote API.  
The app uses **Paging 3** for efficient pagination and follows the **MVVM (Model-View-ViewModel) architecture** to ensure clean, scalable, and maintainable code.

---

## 🚀 Features
- 📄 Pagination using Paging 3 (5 items per page)
- 🧠 MVVM Architecture
- 🌐 Retrofit API Integration
- 🖼 Image loading using Glide
- 🔄 Pull-to-Refresh functionality
- ⏳ Loading indicators (initial + refresh)
- ⚠️ Error handling with user feedback
- 📱 Smooth scrolling with RecyclerView

---

## 🛠 Tech Stack
- **Language:** Kotlin  
- **Architecture:** MVVM  
- **Networking:** Retrofit + OkHttp  
- **Image Loading:** Glide  
- **Pagination:** Paging 3  
- **Concurrency:** Kotlin Coroutines + Flow  
- **UI Components:** RecyclerView, SwipeRefreshLayout  

---

## 🧠 Architecture Explanation

The app follows the **MVVM (Model-View-ViewModel)** architecture pattern:

### 🔹 1. View (Activity)
- Responsible for UI rendering
- Observes data from ViewModel
- Handles user interactions (scroll, refresh)

---

### 🔹 2. ViewModel
- Acts as a bridge between UI and data layer
- Uses `Pager` and `PagingConfig` for pagination
- Exposes data as `Flow<PagingData<Photo>>`
- Uses `cachedIn(viewModelScope)` to persist data during configuration changes

---

### 🔹 3. Data Layer
- Handles API communication using Retrofit
- Uses `PagingSource` to fetch paginated data
- Controls pagination using `nextKey` and `prevKey`
- Implements manual page limit due to lack of total page info from API

---

## 🔄 Data Flow
API (Retrofit)
↓
PagingSource
↓
Pager
↓
ViewModel (Flow<PagingData>)
↓
PagingDataAdapter
↓
RecyclerView (UI)


---

## 🔗 API Used
https://jsonplaceholder.typicode.com/photos

---

## ⚙️ Setup Instructions

### 📌 Prerequisites
- Android Studio installed
- Android SDK configured
- Internet connection

---

### ▶️ Steps to Run the Project

1. Clone the repository:
```bash
git clone https://github.com/Adicoder24hr/PhotoApp
