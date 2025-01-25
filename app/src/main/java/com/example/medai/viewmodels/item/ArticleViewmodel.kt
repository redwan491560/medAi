package com.example.medai.viewmodels.item

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medai.util.Routes
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ArticleViewmodel : ViewModel() {


    private val articleRef = FirebaseDatabase.getInstance().getReference("articles")

    private val _articleList = MutableStateFlow<List<Routes.ArticleViewer>>(emptyList())
    val articleList = _articleList


    fun createArticle(
        title: String,
        description: String,
        author: String,
        paragraph: String,
        category: String, onCreate: () -> Unit
    ) {

        if (title.isBlank() || description.isBlank() || author.isBlank()) return

        viewModelScope.launch {
            val articleId = articleRef.push().key ?: return@launch
            val timeStamp = System.currentTimeMillis()

            val articleData = Routes.ArticleViewer(
                uid = articleId,
                title = title,
                description = description,
                author = author,
                category = category,
                timeStamp = timeStamp,
                story = paragraph,
                view = 0,
                rating = 0.0,
            )

            articleRef.child(articleId).setValue(articleData)
                .addOnSuccessListener {
                    onCreate()
                    Log.d("article", "createArticle: success")
                }.addOnFailureListener {
                    Log.d("article", "createArticle: failed")
                }
        }
    }


    fun readAllArticles() {

        viewModelScope.launch {
            articleRef.get().addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot.exists()) {
                    val articles = mutableListOf<Routes.ArticleViewer>()
                    dataSnapshot.children.forEach { childSnapshot ->
                        val article = childSnapshot.getValue(Routes.ArticleViewer::class.java)
                        article?.let { articles.add(it) }
                    }
                    _articleList.value = articles // Update _articleList here
                } else {
                    _articleList.value = emptyList()
                }
            }.addOnFailureListener { error ->
                Log.e("article", "readAllArticles: failed", error)
                _articleList.value = emptyList()
            }
        }
    }


    fun getArticleById(articleId: String, onResult: (Routes.ArticleViewer?) -> Unit) {
        articleRef.child(articleId).get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val article = snapshot.getValue(Routes.ArticleViewer::class.java)
                    onResult(article)
                } else {
                    Log.d("RealtimeDB", "No article found with ID: $articleId")
                    onResult(null)
                }
            }
            .addOnFailureListener { e ->
                Log.e("RealtimeDB", "Error fetching article", e)
                onResult(null)
            }
    }


    fun deleteArticle(articleId: String) {
        // Check if articleId is not empty
        if (articleId.isEmpty()) {
            Log.e("RealtimeDB", "deleteArticle: Article ID cannot be empty")
            return
        }

        // Reference the article node and remove it
        articleRef.child(articleId).removeValue()
            .addOnSuccessListener {
                Log.d("RealtimeDB", "Article deleted successfully!")
            }
            .addOnFailureListener { e ->
                Log.e("RealtimeDB", "Error deleting article", e)
            }
    }


    fun updateArticle(
        articleId: String,
        title: String = "",
        description: String = "",
        author: String = "",
        paragraph: String = "",
        category: String = ""
    ) {


        val updatedFields = mutableMapOf<String, Any>() // Map to hold only the updated fields

        if (title.isNotEmpty()) updatedFields["title"] = title
        if (description.isNotEmpty()) updatedFields["description"] = description
        if (author.isNotEmpty()) updatedFields["author"] = author
        if (paragraph.isNotEmpty()) updatedFields["story"] =
            paragraph // Assuming "story" corresponds to "paragraph"
        if (category.isNotEmpty()) updatedFields["category"] = category

        if (updatedFields.isEmpty()) {
            Log.d("RealtimeDB", "No fields to update.")
            return
        }

        articleRef.child(articleId).updateChildren(updatedFields)
            .addOnSuccessListener {
                Log.d("RealtimeDB", "Article updated successfully!")
            }
            .addOnFailureListener { e ->
                Log.e("RealtimeDB", "Error updating article", e)
            }
    }

    fun convertTimestampToDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }
}