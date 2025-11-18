package com.example.diaryapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.diaryapp.data.Mood
import com.example.diaryapp.viewmodel.DiaryDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryDetailScreen(
    viewModel: DiaryDetailViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    // 保存完了時に戻る
    LaunchedEffect(state.isSaved) {
        if (state.isSaved) {
            onNavigateBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (state.diary != null) "日記を編集" else "新しい日記") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "戻る")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.saveDiary() }) {
                        Icon(Icons.Default.Check, contentDescription = "保存")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 気分選択
            Text("今日の気分", style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Mood.entries.forEach { mood ->
                    MoodButton(
                        mood = mood,
                        isSelected = state.mood == mood,
                        onClick = { viewModel.updateMood(mood) }
                    )
                }
            }

            // タイトル入力
            OutlinedTextField(
                value = state.title,
                onValueChange = { viewModel.updateTitle(it) },
                label = { Text("タイトル") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // 内容入力
            OutlinedTextField(
                value = state.content,
                onValueChange = { viewModel.updateContent(it) },
                label = { Text("今日はどんな日でしたか？") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                minLines = 10
            )

            // エラー表示
            state.error?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoodButton(
    mood: Mood,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = {
            Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
                Text(mood.emoji, style = MaterialTheme.typography.headlineMedium)
                Text(mood.label, style = MaterialTheme.typography.bodySmall)
            }
        }
    )
}