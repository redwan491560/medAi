package com.example.medai.user

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.medai.R
import com.example.medai.admin.crud.launchToast
import com.example.medai.db.volkorn
import com.example.medai.ui.theme.ComposablesDesign.Companion.IconsDesign
import com.example.medai.ui.theme.components.ArticleDesign
import com.example.medai.ui.theme.components.DrawerSheetComposable
import com.example.medai.ui.theme.components.MedicineDesign
import com.example.medai.ui.theme.drawerColor
import com.example.medai.util.Routes
import com.example.medai.viewmodels.AuthViewModel
import com.example.medai.viewmodels.DatabaseViewModel
import com.example.medai.viewmodels.item.ArticleViewmodel
import com.example.medai.viewmodels.item.MedicineViewmodel
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController,
    medicineViewmodel: MedicineViewmodel,
    articleViewmodel: ArticleViewmodel,
    databaseViewModel: DatabaseViewModel,
    authViewmodel: AuthViewModel
) {


    LaunchedEffect(Unit) { medicineViewmodel.readAllMedicines() }
    val medicineList = medicineViewmodel.medicineList.collectAsState()

    var sortedList by remember {
        mutableStateOf(medicineList.value)
    }

    val searchList = medicineViewmodel.searchMedicineList.collectAsState()

    LaunchedEffect(Unit) { articleViewmodel.readAllArticles() }
    val articleList = articleViewmodel.articleList.collectAsState()


    var expanded by remember {
        mutableStateOf(false)
    }
    var search by remember {
        mutableStateOf("")
    }

    var chipsState by remember { mutableIntStateOf(0) }
    val chipsMain = listOf("News", "Articles", "Medicine", "Doctors")

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    val context = LocalContext.current


    ModalNavigationDrawer(drawerState = drawerState, gesturesEnabled = true, drawerContent = {
        ModalDrawerSheet(
            modifier = Modifier
                .fillMaxHeight()
                .systemBarsPadding()
                .width(320.dp)
                .padding(10.dp, 0.dp),
            drawerTonalElevation = DrawerDefaults.ModalDrawerElevation,
            drawerContainerColor = Color(drawerColor.value),
            drawerShape = RoundedCornerShape(10.dp),
        ) {
            DrawerSheetComposable(onMaintainClick = { navController.navigate(Routes.CRUD) },
                onQNAClick = { /*TODO*/ },
                onProfileClick = { /*TODO*/ },
                onMedicalHistoryClick = { /*TODO*/ },
                onICUClick = { /*TODO*/ },
                onSettingClick = { /*TODO*/ },
                onBloodFindClick = { /*TODO*/ },
                onBloodDonateClick = { /*TODO*/ },
                onRoutineClick = { /*TODO*/ },
                onSecurityClick = { /*TODO*/ },
                onDevopsClick = { /*TODO*/ },
                onReportClick = { /*TODO*/ },
                onSignOut = { authViewmodel.signOut() })
        }
    }) {
        Scaffold(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding()
            .padding(5.dp), floatingActionButton = {
            Card(shape = RoundedCornerShape(6.dp), elevation = CardDefaults.cardElevation(
                hoveredElevation = 6.dp, defaultElevation = 4.dp, pressedElevation = 10.dp
            ), modifier = Modifier.clickable {
                navController.navigate(Routes.AiScreen)
            }) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .size(35.dp)
                )
            }
        }, topBar = {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .weight(6f)
                        .height(50.dp)
                        .padding(start = 5.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xD5B1F7F7)),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (search.isEmpty()) {
                        Text(
                            text = "Search medicine",
                            fontFamily = volkorn,
                            fontSize = 16.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 15.dp)
                        )
                    }
                    BasicTextField(
                        value = search,
                        onValueChange = {
                            search = it
                        },
                        modifier = Modifier
                            .padding(horizontal = 15.dp, vertical = 10.dp)
                            .fillMaxWidth(),
                        textStyle = TextStyle(
                            fontFamily = volkorn, fontSize = 18.sp, color = Color.Black
                        ),
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            search = search.trim()
                            if (search.isNotEmpty()) {
                                medicineViewmodel.searchMedicineByName(search) { itemList ->
                                    if (itemList.isNotEmpty()) {
                                        expanded = true
                                    } else {
                                        launchToast(context = context, "Medicine not found")
                                    }
                                }
                            } else {
                                launchToast(context = context, "Search is empty")
                            }
                        })
                    )

                    Image(painter = painterResource(id = R.drawable.search),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(horizontal = 15.dp, vertical = 4.dp)
                            .size(20.dp)
                            .rotate(270f)
                            .clickable {
                                search = search.trim()
                                if (search.isNotEmpty()) {
                                    medicineViewmodel.searchMedicineByName(search) { itemList ->
                                        if (itemList.isNotEmpty()) {
                                            expanded = true
                                        } else {
                                            launchToast(context = context, "medicine not found")
                                        }
                                    }
                                } else {
                                    launchToast(context = context, "Search is empty")
                                }
                            })
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                ) {
                    Image(painter = painterResource(id = R.drawable.cart),
                        contentDescription = null,
                        modifier = Modifier
                            .size(28.dp)
                            .clickable {
                                navController.navigate(Routes.Cart)
                            })
                    Image(painter = painterResource(id = R.drawable.notification),
                        contentDescription = null,
                        modifier = Modifier
                            .size(28.dp)
                            .clickable {
                                // authViewmodel.updateAdmin(authViewmodel.currentUser)
                            })
                    Image(painter = painterResource(id = R.drawable.menubar),
                        contentDescription = null,
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                scope.launch {
                                    drawerState.open()
                                }
                            })
                }
            }
        }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(top = 60.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconsDesign(
                        src = painterResource(id = R.drawable.bookapp), size = 85
                    ) {
                        navController.navigate(Routes.BookAppointmentScreen)
                    }
                    IconsDesign(
                        src = painterResource(id = R.drawable.booktest), size = 85
                    ) {
                        navController.navigate(Routes.BookLabTestScreen)
                    }
                    IconsDesign(
                        src = painterResource(id = R.drawable.searchprod), size = 85
                    ) {
                        navController.navigate(Routes.SearchProductsScreen)
                    }
                    IconsDesign(
                        src = painterResource(id = R.drawable.record), size = 85
                    ) {
                        navController.navigate(Routes.RecordActivities)
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                ) {


                    if (expanded) {
                        LazyColumn {
                            items(searchList.value) { item ->
                                MedicineDesign(
                                    name = item.name,
                                    group = item.group,
                                    price = item.price,
                                    usage = item.usage
                                ) {
                                    navController.navigate(
                                        Routes.MedicineViewer(
                                            name = item.name,
                                            group = item.group,
                                            price = item.price,
                                            description = item.description,
                                            usage = item.usage,
                                            dosage = item.dosage,
                                            dosageTime = item.dosageTime,
                                            manufacturer = item.manufacturer,
                                            advice = item.advice,
                                            precautions = item.precautions,
                                            prerequisiteMeds = item.prerequisiteMeds,
                                            supplier = item.supplier,
                                            image = item.image,
                                            uid = item.uid
                                        )
                                    )
                                }
                            }
                        }
                    }



                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Row {
                            chipsMain.forEachIndexed { index, string ->
                                Text(text = string,
                                    fontFamily = volkorn,
                                    fontSize = 14.sp,
                                    color = if (chipsState == index) {
                                        Color.Blue
                                    } else {
                                        Color.Black
                                    },
                                    textDecoration = if (chipsState == index) {
                                        TextDecoration.Underline
                                    } else {
                                        TextDecoration.None
                                    },
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .clickable {
                                            chipsState = index
                                        }
                                )
                            }
                        }
                        Row {
                            Image(
                                painter = painterResource(id = R.drawable.sort),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(end = 10.dp)
                                    .size(25.dp)
                                    .clickable {
                                        sortedList = medicineList.value.sortedBy { it.price }
                                    },
                                alignment = Alignment.CenterEnd
                            )
                        }
                    }
                    Column(
                        Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        when (chipsState) {
                            1 -> {
                                LazyColumn {
                                    items(articleList.value) { item ->
                                        val timeStamp =
                                            articleViewmodel.convertTimestampToDate(item.timeStamp)
                                        ArticleDesign(item, timeStamp = timeStamp) {
                                            navController.navigate(
                                                Routes.ArticleViewer(
                                                    title = item.title,
                                                    description = item.description,
                                                    author = item.author,
                                                    timeStamp = item.timeStamp,
                                                    category = "",
                                                    view = 0,
                                                    image = 0,
                                                    rating = 0.2,
                                                    story = item.story
                                                )
                                            )
                                        }
                                    }
                                }
                            }

                            2 -> {
                                LazyColumn {
                                    items(sortedList) { item ->
                                        MedicineDesign(
                                            name = item.name,
                                            group = item.group,
                                            price = item.price,
                                            usage = item.usage
                                        ) {
                                            navController.navigate(
                                                Routes.MedicineViewer(
                                                    name = item.name,
                                                    group = item.group,
                                                    price = item.price,
                                                    description = item.description,
                                                    usage = item.usage,
                                                    dosage = item.dosage,
                                                    dosageTime = item.dosageTime,
                                                    manufacturer = item.manufacturer,
                                                    advice = item.advice,
                                                    precautions = item.precautions,
                                                    prerequisiteMeds = item.prerequisiteMeds,
                                                    supplier = item.supplier,
                                                    image = item.image,
                                                    uid = item.uid
                                                )
                                            )
                                        }

                                    }
                                }
                            }

                            3 -> {

                            }

                            else -> {

                            }
                        }
                    }
                }
            }
        }
    }
}

