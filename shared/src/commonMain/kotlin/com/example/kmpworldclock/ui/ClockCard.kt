package com.example.kmpworldclock.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmpworldclock.model.Country
import com.example.kmpworldclock.util.formatTime
import org.jetbrains.compose.resources.painterResource
import kotlin.time.Instant

@Composable
fun ClockCard(
    country: Country,
    now: Instant,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painterResource(flagImage(country.flagCode)),
                    modifier = Modifier.size(30.dp).padding(end = 10.dp),
                    contentDescription = "${country.name} flag",
                )
                Text(country.name)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(formatTime(now, country.zone))
                IconButton(onClick = onRemove) {
                    Text("✕")
                }
            }
        }
    }
}
