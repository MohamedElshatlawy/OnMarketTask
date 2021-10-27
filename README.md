# MovieAppCLean
<h1 align="center">MovieAppClean</h1>


<p align="center">  
MovieAppClean is a demo application based on modern Android application tech-stacks and Clean architecture.<br>
Fetching data from the network via repository pattern.
</p>
</br>
<p>  
Notice : For the application to fetch data   navigate to the app level Build.gradle file found here(MovieAppCLean/app/) and replace "YOUR_API_KEY" with your api key from tmdb website(https://www.themoviedb.org) 
</p>
</br>

## Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- JetPack
  - Kotlin flows  - notify domain layer data to views.
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository pattern
  - [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - dependency injection.
- [Retrofit2 & Gson](https://github.com/square/retrofit) - construct the REST APIs.
- [OkHttp3](https://github.com/square/okhttp) - implementing interceptor, logging and mocking web server.
- [Coil](https://github.com/coil-kt/coil) - loading images.
- [ShimmerLayout](https://facebook.github.io/shimmer-android/) -  an Android library that provides an easy way to add a shimmer effect to any view in your Android app.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.
- [PagingLibrary-V3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) - fetch paged data .

## Architecture
MovieAppClean is based on Clean architecture and a repository pattern.

![architecture](https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)

