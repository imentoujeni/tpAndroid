package com.imen.tppremiereapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import com.imen.tppremiereapplication.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarImpl(viewModel: MainViewModel,
                  onSearchClick: (text: String) -> Unit,
) {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 2.dp)
    ) {

        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = -1f },
            query = text,
            onQueryChange = { text = it },
            onSearch = {
                active = false
                onSearchClick(it)
            },
            active = active,
            onActiveChange = { active = it },
            placeholder = { Text("Search ...") },
            trailingIcon = { Icon(Icons.Default.Search, contentDescription = "Search icon" ) },
        ){
        }
    }
}