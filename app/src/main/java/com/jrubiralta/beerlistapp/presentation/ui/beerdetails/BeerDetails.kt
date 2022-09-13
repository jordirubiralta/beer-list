package com.jrubiralta.beerlistapp.presentation.ui.beerdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jrubiralta.beerlistapp.R
import com.jrubiralta.beerlistapp.domain.model.BeerModel


@Composable
fun BeerDetails(model: BeerModel, onBackClick: () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onBackClick.invoke() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                title = {
                    Text(text = model.name)
                },
            )
        }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            Text(
                text = model.name,
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp,top = 8.dp)
            )
            model.abv?.let {
                Text(
                    text = "($it% vol.)",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            model.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
                )
            }

            AsyncImage(
                model = "${model.img}",
                contentDescription = model.name,
                placeholder = painterResource(R.drawable.placeholder_image),
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
private fun CharacterDetailPreview() {
    MaterialTheme {
        BeerDetails(
            BeerModel(
                2,
                "Estrella Damm",
                "Catalan beer",
                4.5f,
                "https://images.punkapi.com/v2/keg.png",
                listOf("Paella", "Tapas")
            )
        ) { /* do nothing */ }
    }
}