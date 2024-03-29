package com.example.myapplication

import MainViewModel
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import coil.compose.AsyncImage
import com.example.myapplication.repository.Country

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getData();

        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainView(viewModel = viewModel, onClick = { ccn3 -> navigateToDetailsActivity(ccn3)})
                }
            }
        }
    }

    fun navigateToDetailsActivity(ccn3: String) {
        val detailsIntent = Intent(this, DetailsActivity::class.java)
        detailsIntent.putExtra("CUSTOM_CCN3", ccn3)
        startActivity(detailsIntent)
    }
}

@Composable
fun MainView(viewModel: MainViewModel, onClick: (String) -> Unit) {
    val uiState by viewModel.immutableCountriesData.observeAsState(UIState())

    when {
        uiState.isLoading -> {
            LoadingView()
        }
        uiState.error != null -> {
            ErrorView()
        }
        uiState.data != null -> {
            uiState.data
                ?.let { CountriesListView(countries = it, onClick = { ccn3 -> onClick.invoke(ccn3)}) }
        }
    }
}

@Composable
fun LoadingView() {
    Column {
        Text(
            text = "Loading...",
            color = Color.Black
        )
    }
}

@Composable
fun ErrorView() {
    Column {
        Text(
            text = "Something went wrong! Try to restart application...",
            color = Color.Red
        )
    }
}

@Composable
fun CountriesListView(countries: List<Country>, onClick: (String) -> Unit) {
    if (countries.isNotEmpty()) {
        LazyColumn {
            items(countries) { country ->
                CountriesView(country.ccn3, country.name.common, country.population, country.flags.png, onClick = {ccn3 -> onClick.invoke(ccn3)})
            }
        }
    }
}

@Composable
fun CountriesView(ccn3: String, name: String, population: Int, flagUrl: String, onClick: (String) -> Unit) {
    Column (
        modifier = Modifier.clickable { onClick.invoke(ccn3) }
    ) {
        Row {
            AsyncImage(
                model = flagUrl,
                contentDescription = "Country flag",
                placeholder = painterResource(
                    id = R.drawable.placeholder
                ),
                modifier = Modifier.scale(0.5F)
            )
            Column {
                Text (
                    text = name,
                    color = Color.Black,
                    modifier = Modifier
                        .scale(1.4F)
                        .padding(10.dp)
                )
                Row {
                    Text (
                        text = "Population: ",
                        color = Color.Red,
                    )
                    Text (
                        text = population.toString(),
                        color = Color.Black
                    )
                }
            }
        }
    }
}
