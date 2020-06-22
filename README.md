
<p align="center">  
AirQuality is a small demo application based on modern Android application tech-stacks and MVVM architecture.<br>This project is for focusing especially on fetching data from the network and integrating persisted data in the database via repository pattern.
</p>
</br>



## Tech stack & Open-source libraries
- Minimum SDK level 23
- [Kotlin](https://kotlinlang.org/) 
- Dagger-Hilt (alpha) for dependency injection.
- JetPack
  - LiveData - notify domain layer data to views.
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository pattern
  - [RxJava](https://github.com/ReactiveX/RxJava)
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Timber](https://github.com/JakeWharton/timber) - logging.


## API

AirQuailit using the [Interfejs programistyczny aplikacji (API) - GIOŚ](http://powietrze.gios.gov.pl/pjp/content/api)  for constructing RESTful API.<br>

## Still TODO
* Fix custom graph view
* create unit tests
* add api error handling


# License
```xml
Designed and developed by 2020 jacieslak (Jacek Cieślak)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
