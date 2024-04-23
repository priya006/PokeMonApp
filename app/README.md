# Overview

This Android application provides users with an  experience to discover and interact with a diverse collection of Pokemon creatures. Engineered with robust Android architecture components and adhering to the MVVM (Model-View-ViewModel) architectural paradigm, the app offers a seamless navigation flow, intuitive user interface, and efficient data management. Through this app, users can seamlessly explore detailed information about each Pokemon

## Features

**Pokemon List:** The Pokemon List page, built with Jetpack Compose, acts as the main gateway for users to browse and explore a diverse range of Pokemon available within the application. 

**PokemonPaging:** The "PokemonPaging" feature utilizes the Paging library from Android Jetpack to efficiently load data from a remote data source, such as an API, in paginated chunks.

**Search Functionality:** When the toggle switch is turned ON, **auto-search** functionality is activated, leveraging the 'debounce' mechanism to enhance the search experience. This feature introduces a brief delay before initiating the search operation, effectively reducing redundant search requests triggered by rapid user interactions. By incorporating debounce, the app optimizes resource utilization and minimizes network traffic, ensuring efficient and responsive search functionality for users.

**Pokemon Details:** View detailed information about each Pokemon, including their `Sprite image`, `ID`, `height`, `weight`, `type`, and `stats`.

## Libraries Used

**Networking with Retrofit:** Retrofit is employed for handling network requests, making it effortless to communicate with remote servers and fetch data required for the app

**ViewModel and LiveData:** Android Architecture Components like ViewModel and LiveData are utilized for managing UI-related data in a lifecycle-conscious manner, ensuring smooth data updates and synchronization with the UI.

**Image Loading with Coil:** Coil is integrated for efficient and hassle-free image loading within the Compose UI, enabling the display of Pokemon images and other graphical content.

**Dependency Injection with Dagger-Hilt:** Dagger-Hilt facilitates dependency injection, promoting modularization, maintainability, and testability by managing dependencies and their lifecycles efficiently.

**Paging Library:** The Paging library assists in handling large datasets efficiently, enabling smooth pagination and data loading for the Pokemon list screen and other paginated views.

**Navigation Component:** Navigation Component simplifies navigation and deep linking within the app, allowing users to navigate between different screens seamlessly and ensuring a smooth user flow.

**Testing Dependencies:** Various testing dependencies including  Mockito, MockK, and AndroidX Test Runner are incorporated to enable comprehensive unit testing, integration testing, and UI testing, ensuring the reliability and quality of the app.

**Tablet Experience:**
![2024-04-23 13 47 17](https://github.com/priya006/PokeMonApp/assets/16076524/00acf585-bcbb-4012-a776-7db84e7b4cb6)

**Auto Search in Mobile**
No need to hit the search button to make a search. 




