TVShow
===================================
![Screenshot](/image/main_screen.jpg)
![Screenshot](/image/detailscreen.jpg)


Main architecture
===================================
MVVM “One-Activity-Multiple-Fragments” or Fragment Navigation Pattern, where every screen in the application is a full screen Fragment and all or most of these fragments are contained in one Activity. 

![MVVM](/image/mvvm.png)
![UMLTvShow](/image/UMLTvShow.png)


Library Used
===================================
* Retrofit: for network request
* Kodein : for dependency Injection
* Glide : for Loading and Cashing Images
* Navigation: for easier navigation support
* Room : for data persistence
* ViewModel & LiveData : for handling data in a lifecycle-aware fashion
