package com.example.myapplication

import DetailsViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.repository.Country
import com.example.myapplication.ui.theme.MyApplicationTheme

class DetailsActivity : ComponentActivity() {
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ccn3 = intent.getStringExtra("CUSTOM_CCN3") ?: throw Error("cnn3 is null")
        viewModel.getData(ccn3);

        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    DetailsView(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun DetailsView(viewModel: DetailsViewModel) {
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
                ?.let { CountryDetailsListView(countries = it)}
        }
    }
}

@Composable
fun CountryDetailsListView(countries: List<Country>) {
    if (countries.isNotEmpty()) {
        LazyColumn {
            items(countries) { country ->
                CountryDetailsView(country)
            }
        }
    }
}

@Composable
fun CountryDetailsView(country: Country) {
    Column (
        modifier = Modifier.padding(10.dp)
    ){
        AsyncImage(
            model = country.flags.png,
            contentDescription = "Country flag",
            placeholder = painterResource(
                id = R.drawable.placeholder
            ),
        )
        Row {
            Column {
                Text (
                    text = country.name.common,
                    color = Color.Black,
                    modifier = Modifier
                        .scale(1.4F)
                        .padding(10.dp)
                )
                Row {
                    Text (
                        text = "Official name: ",
                        color = Color.Red,
                    )
                    Text (
                        text = country.name.official,
                        color = Color.Black
                    )
                }
                Row {
                    Text (
                        text = "Population: ",
                        color = Color.Red,
                    )
                    Text (
                        text = country.population.toString(),
                        color = Color.Black
                    )
                }
                Row {
                    Text (
                        text = "Capital: ",
                        color = Color.Red,
                    )
                    Text (
                        text = country.capital[0],
                        color = Color.Black
                    )
                }
                Row {
                    Text (
                        text = "Region: ",
                        color = Color.Red,
                    )
                    Text (
                        text = country.region,
                        color = Color.Black
                    )
                }
                Row {
                    Text (
                        text = "Area: ",
                        color = Color.Red,
                    )
                    Text (
                        text = country.area.toString(),
                        color = Color.Black
                    )
                }
            }
        }
    }
}