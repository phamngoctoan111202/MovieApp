# Application Scope

MovieApp is a user-friendly platform for movie enthusiasts. It offers the following features:

- **Login and Registration**: Users can securely log in to their accounts or register for new accounts to access the app's full functionality.
  
- **Browse and Search Movies**: Users can explore a diverse selection of movies based on various criteria, making it easy to find movies tailored to their preferences.

- **Watch Movies**: Users can select movies from the available list and watch them directly within the app, providing a seamless viewing experience.

- **Favorite Movies**: Users can add movies to their favorites list for quick access and personalized recommendations.

The app is developed following the Clean Architecture, Modularization, and MVVM design principles, ensuring a well-structured and maintainable codebase.

# Tech-Stack

The project utilizes a robust tech-stack comprised of various libraries and tools commonly used in modern Android development. Here are the key technologies employed:

- **Retrofit**: Networking library for handling API requests.
- **Coroutine**: Facilitates background operations and asynchronous programming.
- **Koin**: Dependency injection framework for managing dependencies.
- **Coil**: Image loading library for displaying images efficiently.
- **Firebase**: Backend-as-a-Service platform for user authentication and data storage.
- **LiveData**: Part of Android Architecture Components for observing data changes.
  
# Architecture

The application follows the Clean Architecture principles, promoting modularity, scalability, and maintainability. It is structured using a modular architecture with MVVM (Model-View-ViewModel) pattern. Here's an overview of the architecture:

- **Presentation Layer**: Implements MVVM pattern. Activities/ Fragments act as views, ViewModel manages UI-related data.
- **Domain Layer**: Contains business logic and domain models independent of other layers.
- **Data Layer**: Encapsulates data access and storage, utilizing repositories to interact with external data sources such as APIs, databases, and Firebase.

# Dependency Management

Dependency management is centralized using Gradle versions catalog. This ensures consistency across modules and facilitates easier dependency updates. Dependencies are shared between modules, reducing the need for redundant declarations.

# Demo Link

To experience MovieApp in action, please visit [Demo MovieApp]( https://youtu.be/BG5F8tPZTyw).
