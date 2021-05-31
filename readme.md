<h1 align="center">My Movies</h1>
<p align="center">Aplicativo mobile exemplo para listagem de filmes utilizando api do [MovieDB](themoviedb.org)<br></p>

<h1 align="center">
  <img alt="MyMovie" title="#" src="images/logo2.png" />
</h1>

![Badge](https://img.shields.io/github/issues/Riicksouzaa/MyMovies?color=green)
![Badge](https://img.shields.io/github/forks/Riicksouzaa/MyMovies
![Badge](https://img.shields.io/github/stars/Riicksouzaa/MyMovies
![Badge](https://img.shields.io/apm/l/vim-mode)

### Features

<img alt="Consus" title="#" src="images/demo1.png" />

<img alt="Consus" title="#" src="images/demo2.png" />

- [x] Tela Inicial
- [x] Listagem de Filmes
- [x] Exibição de detalhes do filme
- [x] Paginação com pagin3

### 🛠 Tecnologias

```java
	// Navigation
	implementation "android.arch.navigation:navigation-fragment:$rootProject.nav_version"
	implementation "android.arch.navigation:navigation-ui:$rootProject.nav_version"
	implementation "android.arch.navigation:navigation-runtime-ktx:$rootProject.nav_version"
	implementation "android.arch.work:work-runtime-ktx:$rootProject.workVersion"

	//components
	implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"
	implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"
	implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.3.1"
	implementation "androidx.room:room-runtime:2.3.0"
	implementation "androidx.room:room-ktx:2.3.0"
	kapt "androidx.room:room-compiler:2.3.0"
	implementation "androidx.paging:paging-runtime-ktx:3.0.0"

	// Picasso
	implementation 'com.squareup.picasso:picasso:2.71828'

	// Anko
	implementation "org.jetbrains.anko:anko:$rootProject.anko_version"
	implementation "org.jetbrains.anko:anko-commons:$rootProject.anko_version"

	// Retrofit
	implementation 'com.squareup.retrofit2:retrofit:2.9.0'
	implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
	implementation 'com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2'


	kapt 'com.android.databinding:compiler:3.2.0-alpha10'
```