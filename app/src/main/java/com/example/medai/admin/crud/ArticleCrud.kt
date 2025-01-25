package com.example.medai.admin.crud

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.medai.R
import com.example.medai.db.volkorn
import com.example.medai.ui.theme.ComposablesDesign.Companion.TextDesign
import com.example.medai.util.Routes
import com.example.medai.viewmodels.item.ArticleViewmodel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ArticleCrud(articleViewmodel: ArticleViewmodel, navController: NavHostController) {


    var title by remember { mutableStateOf("") }
    var articleId by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var paragraph by remember { mutableStateOf("") }


    val context = LocalContext.current
    var modifyArticle by remember {
        mutableStateOf<Routes.ArticleViewer?>(null)
    }


    var editState by remember {
        mutableStateOf(false)
    }


    val chips = listOf("Create", "Update", "Delete")
    var chipState by remember {
        mutableIntStateOf(0)
    }


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)

        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextDesign(name = "Article Database", font = 22)
                Image(
                    painter = painterResource(id = R.drawable.server),
                    contentDescription = null,
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            navController.navigate(Routes.ViewArticle)
                        })
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row {
                chips.forEachIndexed { index, string ->
                    Text(text = string,
                        fontFamily = volkorn,
                        fontSize = 18.sp,
                        color = if (chipState == index) {
                            Color.Blue
                        } else {
                            Color.Black
                        },
                        textDecoration = if (chipState == index) {
                            TextDecoration.Underline
                        } else {
                            TextDecoration.None
                        },
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .clickable {
                                chipState = index
                                if (chipState == 0) editState = false
                            })
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 10.dp)
            ) {

                if (chipState == 1 || chipState == 2) {
                    // article id
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xD5C0D0F7)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (articleId.isEmpty()) {
                            Text(
                                text = "Article ID",
                                fontFamily = volkorn,
                                fontSize = 16.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 15.dp)
                            )
                        }
                        BasicTextField(
                            value = articleId,
                            onValueChange = {
                                articleId = it
                            },
                            modifier = Modifier
                                .padding(horizontal = 15.dp, vertical = 10.dp)
                                .fillMaxWidth(),
                            textStyle = TextStyle(
                                fontFamily = volkorn,
                                fontSize = 18.sp,
                                color = Color.Black
                            ),
                            maxLines = 3,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            )
                        )
                    }
                }

                if (chipState == 0) {
                    // title
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xD5C0D0F7)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (title.isEmpty()) {
                            Text(
                                text = "Article title",
                                fontFamily = volkorn,
                                fontSize = 16.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 15.dp)
                            )
                        }
                        BasicTextField(
                            value = title,
                            onValueChange = {
                                title = it
                            },
                            modifier = Modifier
                                .padding(horizontal = 15.dp, vertical = 10.dp)
                                .fillMaxWidth(),
                            textStyle = TextStyle(
                                fontFamily = volkorn,
                                fontSize = 18.sp,
                                color = Color.Black
                            ),
                            maxLines = 3,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            )
                        )
                    }

                    // author
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xD5C0D0F7)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (author.isEmpty()) {
                            Text(
                                text = "Author",
                                fontFamily = volkorn,
                                fontSize = 16.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 15.dp)
                            )
                        }
                        BasicTextField(
                            value = author,
                            onValueChange = {
                                author = it
                            },
                            modifier = Modifier
                                .padding(horizontal = 15.dp, vertical = 10.dp)
                                .fillMaxWidth(),
                            textStyle = TextStyle(
                                fontFamily = volkorn,
                                fontSize = 18.sp,
                                color = Color.Black
                            ),
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            )
                        )
                    }

                    // category
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xD5C0D0F7)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (category.isEmpty()) {
                            Text(
                                text = "Category",
                                fontFamily = volkorn,
                                fontSize = 16.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 15.dp)
                            )
                        }
                        BasicTextField(
                            value = category,
                            onValueChange = {
                                category = it
                            },
                            modifier = Modifier
                                .padding(horizontal = 15.dp, vertical = 10.dp)
                                .fillMaxWidth(),
                            textStyle = TextStyle(
                                fontFamily = volkorn,
                                fontSize = 18.sp,
                                color = Color.Black
                            ),
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            )
                        )
                    }

                    // description
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xD5C0D0F7)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (description.isEmpty()) {
                            Text(
                                text = "Article description",
                                fontFamily = volkorn,
                                fontSize = 16.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 15.dp)
                            )
                        }
                        BasicTextField(
                            value = description,
                            onValueChange = {
                                description = it
                            },
                            modifier = Modifier
                                .padding(horizontal = 15.dp, vertical = 10.dp)
                                .fillMaxWidth(),
                            textStyle = TextStyle(
                                fontFamily = volkorn,
                                fontSize = 18.sp,
                                color = Color.Black
                            ),
                            maxLines = 4,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            )
                        )
                    }
                    // paragraph
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xD5C0D0F7)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (paragraph.isEmpty()) {
                            Text(
                                text = "Paragraph",
                                fontFamily = volkorn,
                                fontSize = 16.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 15.dp)
                            )
                        }
                        BasicTextField(
                            value = paragraph,
                            onValueChange = {
                                paragraph = it
                            },
                            modifier = Modifier
                                .padding(horizontal = 15.dp, vertical = 10.dp)
                                .fillMaxWidth(),
                            textStyle = TextStyle(
                                fontFamily = volkorn,
                                fontSize = 18.sp,
                                color = Color.Black
                            ),
                            maxLines = 8,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            )
                        )
                    }

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp), horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF5FCFA2)),
                        shape = RoundedCornerShape(8.dp),
                        onClick = {
                            when (chipState) {
                                0 -> {
                                    editState = false
                                    articleViewmodel.createArticle(
                                        title, description, author, paragraph, category
                                    ) {
                                        Toast.makeText(
                                            context,
                                            "Article created",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    title = ""
                                    description = ""
                                    author = ""
                                    paragraph = ""
                                    category = ""
                                }

                                1 -> {
                                    editState = true
                                    if (articleId.isNotEmpty()) {
                                        articleViewmodel.getArticleById(articleId) { article ->
                                            article?.let {
                                                modifyArticle = it
                                            } ?: let {
                                                modifyArticle = null
                                                Toast.makeText(
                                                    context,
                                                    "Article not found",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }

                                    } else {
                                        Toast.makeText(
                                            context,
                                            "ArticleId cannot be empty",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                }

                                else -> {
                                    editState = true


                                }
                            }
                        }
                    ) {
                        Text(
                            text = when (chipState) {
                                0 -> "Create"
                                1 -> "Search"
                                else -> "Search"
                            }, color = Color.Black,
                            fontSize = 22.sp,
                            fontFamily = volkorn
                        )
                    }
                }



                if (editState && chipState == 1) {

                    Spacer(modifier = Modifier.padding(top = 30.dp))
                    modifyArticle?.title?.let { it1 ->
                        ViewArticleComposable(
                            title = it1,
                            author = modifyArticle!!.author
                        )
                    }


                    // title
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xD5C0D0F7)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (title.isEmpty()) {
                            Text(
                                text = "Article title",
                                fontFamily = volkorn,
                                fontSize = 16.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 15.dp)
                            )
                        }
                        BasicTextField(
                            value = title,
                            onValueChange = {
                                title = it
                            },
                            modifier = Modifier
                                .padding(horizontal = 15.dp, vertical = 10.dp)
                                .fillMaxWidth(),
                            textStyle = TextStyle(
                                fontFamily = volkorn,
                                fontSize = 18.sp,
                                color = Color.Black
                            ),
                            maxLines = 3,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            )
                        )
                    }

                    // description
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xD5C0D0F7)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (description.isEmpty()) {
                            Text(
                                text = "Description",
                                fontFamily = volkorn,
                                fontSize = 16.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 15.dp)
                            )
                        }
                        BasicTextField(
                            value = description,
                            onValueChange = {
                                description = it
                            },
                            modifier = Modifier
                                .padding(horizontal = 15.dp, vertical = 10.dp)
                                .fillMaxWidth(),
                            textStyle = TextStyle(
                                fontFamily = volkorn,
                                fontSize = 18.sp,
                                color = Color.Black
                            ),
                            maxLines = 3,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            )
                        )
                    }

                    // author
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xD5C0D0F7)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (author.isEmpty()) {
                            Text(
                                text = "Author",
                                fontFamily = volkorn,
                                fontSize = 16.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 15.dp)
                            )
                        }
                        BasicTextField(
                            value = author,
                            onValueChange = {
                                author = it
                            },
                            modifier = Modifier
                                .padding(horizontal = 15.dp, vertical = 10.dp)
                                .fillMaxWidth(),
                            textStyle = TextStyle(
                                fontFamily = volkorn,
                                fontSize = 18.sp,
                                color = Color.Black
                            ),
                            maxLines = 3,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            )
                        )
                    }

                    // paragraph
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xD5C0D0F7)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (paragraph.isEmpty()) {
                            Text(
                                text = "Paragraph",
                                fontFamily = volkorn,
                                fontSize = 16.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 15.dp)
                            )
                        }
                        BasicTextField(
                            value = paragraph,
                            onValueChange = {
                                paragraph = it
                            },
                            modifier = Modifier
                                .padding(horizontal = 15.dp, vertical = 10.dp)
                                .fillMaxWidth(),
                            textStyle = TextStyle(
                                fontFamily = volkorn,
                                fontSize = 18.sp,
                                color = Color.Black
                            ),
                            maxLines = 3,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            )
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp), horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = when (chipState) {
                            0 -> ""
                            1 -> "Update"
                            else -> "Delete"
                        }, color = Color.Black,
                        fontSize = 22.sp,
                        textDecoration = TextDecoration.Underline,
                        fontFamily = volkorn,
                        modifier = Modifier.clickable {
                            when (chipState) {

                                1 -> {
                                    modifyArticle?.let {
                                        articleViewmodel.updateArticle(
                                            articleId = it.uid,
                                            title = title,
                                            description = description,
                                            author = author,
                                            paragraph = paragraph,
                                            category = category
                                        )
                                        title = ""
                                        description = ""
                                        author = ""
                                        paragraph = ""
                                        category = ""
                                    } ?: let {
                                        Toast.makeText(
                                            context,
                                            "Article not available",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                                2 -> {
                                    articleViewmodel.getArticleById(articleId) { article ->
                                        if (article == null) {
                                            Toast.makeText(
                                                context,
                                                "Article not found",
                                                Toast.LENGTH_SHORT
                                            )
                                                .show()
                                        } else {
                                            article.let {
                                                modifyArticle = it
                                                articleViewmodel.deleteArticle(
                                                    articleId = article.uid
                                                )
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun ViewArticleComposable(title: String, author: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            TextDesign(name = title, font = 20)
            TextDesign(name = author, font = 14)
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun DDHFDGFHD() {
    ArticleCrud(articleViewmodel = ArticleViewmodel(), rememberNavController())
}