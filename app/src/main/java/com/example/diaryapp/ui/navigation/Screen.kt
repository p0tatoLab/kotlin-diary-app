package com.example.diaryapp.ui.navigation

sealed class Screen(val route: String) {
    object DiaryList : Screen("diary_list")
    object DiaryDetail : Screen("diary_detail/{diaryId}") {
        fun createRoute(diaryId: Long? = null): String {
            return if (diaryId != null) {
                "diary_detail/$diaryId"
            } else {
                "diary_detail/0"
            }
        }
    }
}