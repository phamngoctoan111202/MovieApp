package com.noatnoat.feature_home.data.datasource.firestore.service

import android.content.Context
import androidx.compose.runtime.snapshots.Snapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.noatnoat.feature_home.data.datasource.firestore.model.MovieDetailFirestoreModel
import com.noatnoat.feature_base.data.firebase.constants.FirebaseConstants
import com.noatnoat.feature_home.data.datasource.firestore.model.BasicMovieFirestoreModel
import com.noatnoat.feature_home.presentation.screen.detailscreen.DetailFragment
import kotlinx.coroutines.tasks.await

internal class MovieFirestoreServiceImpl(val context: Context) : MovieFirestoreService {
    private val db = FirebaseFirestore.getInstance()
    private val movieCollection = db.collection(FirebaseConstants.MOVIES_COLLECTION)
    private val userCollection = db.collection(FirebaseConstants.USERS_COLLECTION)

    override suspend fun fetchBasicMovieInfoFromFirestore(movieType: String?,
                                   year: Int?,
                                   categories: String?,
                                   country: String?,
                                   exclusiveSub: Boolean?,
                                   minViews: Int?,
                                   limit: Long?): List<BasicMovieFirestoreModel> {

        var query: Query = movieCollection

        movieType?.let {
            query = query.whereEqualTo(FirebaseConstants.FIELD_TYPE, it)
        }

        year?.let {
            query = query.whereEqualTo(FirebaseConstants.FIELD_YEAR, it)
        }

        categories?.let {
            query = query.whereArrayContains(FirebaseConstants.FIELD_CATEGORY, it)
        }

        country?.let {
            query = query.whereEqualTo(FirebaseConstants.FIELD_COUNTRY, it)
        }
        exclusiveSub?.let {
            query = query.whereEqualTo(FirebaseConstants.FIELD_SUB_DOCQUYEN, it)
        }

        minViews?.let {
            query = query.whereGreaterThanOrEqualTo(FirebaseConstants.FIELD_VIEW, it)
        }

        limit?.let {
            query = query.limit(10)
        }

        return try {
            val snapshot = query.get().await()

            if (!snapshot.isEmpty) {
                return snapshot.toObjects(BasicMovieFirestoreModel::class.java)
            } else {
                return emptyList()
            }
        } catch (e: Exception) {
            return emptyList()
        }
    }



    override suspend fun fetchDetailedMovieInfoFromFirestore(movieId: String): MovieDetailFirestoreModel {
        val query = movieCollection.whereEqualTo("_id", movieId)

        return try {
            val snapshot = query.get().await()

            if (!snapshot.isEmpty) {
                val document = snapshot.documents[0]

                return document.toObject(MovieDetailFirestoreModel::class.java)
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
