package com.example.kmpworldclock.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmpworldclock.model.Country
import org.jetbrains.compose.resources.painterResource

@Composable
fun CountrySelector(
    countries: List<Country>,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onCountrySelected: (Country) -> Unit,
    modifier: Modifier = Modifier,
) {
    var searchText by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        Row(modifier = Modifier.padding(start = 20.dp, top = 10.dp)) {
            DropdownMenu(expanded = expanded, onDismissRequest = { onExpandedChange(false) }) {
                CompactTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = "Search country",
                    modifier = Modifier
                        .padding(horizontal = 12.dp),
                )
                countries.filter { it.name.contains(searchText, ignoreCase = true) }
                    .forEach { country ->
                        DropdownMenuItem(text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painterResource(flagImage(country.flagCode)),
                                    modifier = Modifier.size(30.dp).padding(end = 10.dp),
                                    contentDescription = "${country.name} flag"
                                )
                                Text(country.name)
                            }
                        }, onClick = {
                            onCountrySelected(country)
                            onExpandedChange(false)
                        })
                    }
            }
        }
        Button(
            enabled = countries.isNotEmpty(),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { onExpandedChange(!expanded) }) {
            Text("Select Location")
        }
    }
}
