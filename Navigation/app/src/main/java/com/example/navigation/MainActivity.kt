package com.example.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

data class Person(
    val id: Int,
    val name: String,
    val age: Int
)


// Observable snapshot list so edits instantly recompose across the app
val people = mutableStateListOf(
    Person(id = 1, name = "Ada", age = 36),
    Person(id = 2, name = "Bob", age = 24),
    Person(id = 3, name = "Cleo", age = 41),
    Person(id = 4, name = "Dan", age = 19)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PeopleNavHost()
        }
    }
}

@Composable
fun PeopleNavHost() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "peopleList") {
        composable(route = "peopleList") {
            PeopleListScreen(
                people = people,
                onPersonClick = { person ->
                    navController.navigate("personDetails/${person.id}")
                }
            )
        }
        composable(
            route = "personDetails/{personId}",
            arguments = listOf(navArgument("personId") { type = NavType.IntType })
        ) { backStackEntry ->
            val personId = backStackEntry.arguments?.getInt("personId") ?: -1
            val person = people.first { it.id == personId }
            PersonDetailsScreen(
                person = person,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun PeopleListScreen(people: List<Person>, onPersonClick: (Person) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = people, key = { person -> person.id }) { person ->
            Text(
                text = "${person.name} (${person.age})",
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onPersonClick(person) }
                    .padding(16.dp)
            )
            HorizontalDivider()
        }
    }
}

@Composable
fun PersonDetailsScreen(person: Person, onBackClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Name: ${person.name}", fontSize = 24.sp)
        Text(text = "Age: ${person.age}", fontSize = 24.sp, modifier = Modifier.padding(top = 8.dp))
        Button(onClick = onBackClick, modifier = Modifier.padding(top = 24.dp)) {
            Text("Back")
        }
    }
}