TVShow
===================================
![Screenshot](/image/main_screen.jpg width="200" height="400" ) ![Screenshot](/image/detailscreen.jpg)



Main architecture
===================================
MVVM “One-Activity-Multiple-Fragments” or Fragment Navigation Pattern, where every screen in the application is a full screen Fragment and all or most of these fragments are contained in one Activity. 

![UMLTvShow](/image/UMLTvShow.png width="200" height="400" ) ![MVVM](/image/mvvm.png =25x10) 



Library Used
===================================
* Retrofit: for network request
* Kodein : for dependency Injection
* Glide : for Loading and Cashing Images
* Navigation: for easier navigation support
* Room : for data persistence
* ViewModel & LiveData : for handling data in a lifecycle-aware fashion
