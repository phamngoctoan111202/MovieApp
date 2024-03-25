package com.noatnoat.feature_favourite.data.datasource.firestore.service

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.noatnoat.feature_favourite.data.datasource.firestore.model.FavouriteMovieCardFirestoreModel
import com.noatnoat.feature_base.data.firebase.constants.FirebaseConstants
import com.noatnoat.feature_favourite.data.datasource.firestore.model.FavouriteMovieDetailFirestoreModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FavouriteMovieFirestoreServiceImpl(val context : Context) : FavouriteMovieFirestoreService {
    private val db = FirebaseFirestore.getInstance()
    private val movieCollection = db.collection(FirebaseConstants.MOVIES_COLLECTION)
    private val userCollection = db.collection(FirebaseConstants.USERS_COLLECTION)

    override suspend fun fetchFavouriteMoviesFromFirestore() : List<FavouriteMovieCardFirestoreModel> {
        val sharedPref = context.getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        val userId = sharedPref.getString("userId", null)
        val favouriteMovies = mutableListOf<FavouriteMovieCardFirestoreModel>()
        if (userId != null) {
            val movieIds = getIdFavouriteMovies(userId)
            for (movieId in movieIds) {
                val query = movieCollection.whereEqualTo("_id", movieId)
                val querySnapshot = query.get().await()
                for (document in querySnapshot.documents) {
                    val movie = document.toObject(FavouriteMovieCardFirestoreModel::class.java)
                    if (movie != null) {
                        favouriteMovies.add(movie)
                    }
                }
            }

            Log.d("UserFavouriteMovies", "Danh sách các bộ phim yêu thích của người dùng $userId: $movieIds")

        }
        return favouriteMovies
    }


    private suspend fun getIdFavouriteMovies(userId: String): List<String> = withContext(Dispatchers.IO) {
        val favouriteMovies = mutableListOf<String>()
        val docRef = db.collection("users").document(userId)
        val document = docRef.get().await()
        if (document != null) {
            val movies = document.get("favouriteMovies") as List<String>
            favouriteMovies.addAll(movies)
        }
        favouriteMovies
    }

    override suspend fun fetchDetailedMovieInfoFromFirestore(movieId: String): FavouriteMovieDetailFirestoreModel {
        val query = movieCollection.whereEqualTo("_id", movieId)

        return try {
            val snapshot = query.get().await()

            if (!snapshot.isEmpty) {
                val document = snapshot.documents[0]

                return document.toObject(FavouriteMovieDetailFirestoreModel::class.java)
                    ?: throw Exception("Failed to convert snapshot to MovieDetailFirestoreModel")
            } else {
                throw Exception("Movie not found")
            }
        } catch (e: Exception) {
            throw e
        }
    }


    override suspend fun addMovietoFavouriteList(movieId: String) {
        val sharedPref = context.getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        val userId = sharedPref.getString("userId", null)
        val docRef = userId?.let { db.collection("users").document(it) }
        val document = docRef?.get()?.await()
        var query: Query = userCollection

        val favouriteMovies = document?.get("favouriteMovies") as? MutableList<String> ?: mutableListOf()

        favouriteMovies.add(movieId)

        docRef?.update("favouriteMovies", favouriteMovies)
    }

    override suspend fun removeMovieFromFavouriteList(movieId: String) {
        val sharedPref = context.getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        val userId = sharedPref.getString("userId", null)
        val docRef = userId?.let { db.collection("users").document(it) }
        val document = docRef?.get()?.await()

        val favouriteMovies = document?.get("favouriteMovies") as? MutableList<String> ?: mutableListOf()

        favouriteMovies.remove(movieId)

        docRef?.update("favouriteMovies", favouriteMovies)
    }

    override suspend fun isMovieInFavouriteList(movieId: String): Boolean {
        val sharedPref = context.getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        val userId = sharedPref.getString("userId", null)
        val docRef = userId?.let { db.collection("users").document(it) }
        val document = docRef?.get()?.await()

        val favouriteMovies = document?.get("favouriteMovies") as? MutableList<String> ?: mutableListOf()

        return favouriteMovies.contains(movieId)
    }



}

