package com.example.diaryapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.diaryapp.ui.screens.DiaryDetailScreen
import com.example.diaryapp.ui.screens.DiaryListScreen
import com.example.diaryapp.ui.screens.StatisticsScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.DiaryList.route
    ) {
        // 日記一覧画面
        composable(route = Screen.DiaryList.route) {
            DiaryListScreen(
                onDiaryClick = { diaryId ->
                    navController.navigate(Screen.DiaryDetail.createRoute(diaryId))
                },
                onAddClick = {
                    navController.navigate(Screen.DiaryDetail.createRoute(null))
                },
                onStatisticsClick = {
                    navController.navigate(Screen.Statistics.route)
                }
            )
        }

        // 日記詳細・編集画面
        composable(
            route = Screen.DiaryDetail.route,
            arguments = listOf(
                navArgument("diaryId") {
                    type = NavType.LongType
                    defaultValue = 0L
                }
            )
        ) {
            DiaryDetailScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        // 統計画面
        composable(route = Screen.Statistics.route) {
            StatisticsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}