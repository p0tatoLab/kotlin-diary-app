# Kotlin Diary App

Jetpack Composeを使った日記アプリ - モダンAndroid開発の学習プロジェクト

## 📝 概要

Jetpack Compose、Room Database、ViewModel、Hiltなどのモダンなアーキテクチャを学習するためのプロジェクトです。
MVVM アーキテクチャパターンに基づいて設計されています。

## ✨ 機能

### 基本機能
- ✅ 日記の作成・編集・削除
- ✅ 日付ごとの一覧表示
- ✅ 気分選択（絵文字：😊 🙂 😐 😞 😢）
- ✅ リアルタイム検索
- ✅ 統計表示

### UI/UX
- ✅ Material Design 3
- ✅ スワイプで削除
- ✅ スムーズなアニメーション
- ✅ ダークモード対応
- ✅ レスポンシブデザイン

### データ管理
- ✅ Room Database による永続化
- ✅ Flow によるリアクティブなデータ更新
- ✅ Coroutines による非同期処理

## 🛠 技術スタック

### UI
- **Jetpack Compose** - 宣言的UI
- **Material Design 3** - モダンなデザイン
- **Navigation Compose** - 画面遷移

### アーキテクチャ
- **MVVM** - Model-View-ViewModel
- **Repository Pattern** - データ層の抽象化
- **State Management** - StateFlow

### データベース
- **Room** - SQLiteラッパー
- **Flow** - リアクティブストリーム
- **Type Converters** - カスタム型の変換

### 依存性注入
- **Hilt** - Dependency Injection

### 非同期処理
- **Coroutines** - 非同期処理
- **Flow** - データストリーム

## 📁 プロジェクト構成
```
app/src/main/java/com/example/diaryapp/
├── data/
│   ├── entity/
│   │   └── DiaryEntry.kt          # Room Entity
│   ├── dao/
│   │   └── DiaryDao.kt             # Data Access Object
│   ├── database/
│   │   └── DiaryDatabase.kt        # Room Database
│   ├── repository/
│   │   └── DiaryRepository.kt      # データアクセス層
│   ├── Mood.kt                     # 気分Enum
│   └── Converters.kt               # Type Converters
├── di/
│   └── DatabaseModule.kt           # Hilt Module
├── ui/
│   ├── screens/
│   │   ├── DiaryListScreen.kt      # 一覧画面
│   │   ├── DiaryDetailScreen.kt    # 詳細・編集画面
│   │   └── StatisticsScreen.kt     # 統計画面
│   ├── navigation/
│   │   ├── Screen.kt               # 画面定義
│   │   └── NavGraph.kt             # Navigation設定
│   ├── state/
│   │   ├── DiaryListState.kt       # 一覧画面State
│   │   └── DiaryDetailState.kt     # 詳細画面State
│   └── theme/                      # テーマ設定
├── viewmodel/
│   ├── DiaryListViewModel.kt       # 一覧画面ViewModel
│   └── DiaryDetailViewModel.kt     # 詳細画面ViewModel
├── util/
│   └── DateUtils.kt                # 日付ユーティリティ
├── DiaryApplication.kt             # Application クラス
└── MainActivity.kt                 # メインActivity
```

## 🚀 実行方法

### 1. プロジェクトを開く
```bash
git clone https://github.com/YOUR_USERNAME/kotlin-diary-app.git
cd kotlin-diary-app
```

### 2. Android Studioで開く
1. Android Studioを起動
2. 「Open」から `kotlin-diary-app` フォルダを選択
3. Gradle Syncが完了するのを待つ

### 3. 実行
1. エミュレータまたは実機を接続
2. **Run → Run 'app'** でアプリを実行

## 📖 使い方

### メイン画面（日記一覧）
- 右下の**+ボタン**で新規日記作成
- 日記カードをタップで編集画面へ
- 日記を左にスワイプで削除
- 右上の**虫眼鏡アイコン**で検索
- 右上の**グラフアイコン**で統計表示

### 日記作成・編集画面
- 気分を選択（5段階）
- タイトルと内容を入力
- 右上の**✓ボタン**で保存
- 右上の**ゴミ箱アイコン**で削除（編集時のみ）

### 統計画面
- 総日記数の表示
- 気分別の内訳と割合

## 🎓 学習した技術

このプロジェクトで以下の概念を習得：

### Jetpack Compose
- ✅ Composable 関数
- ✅ State Management（remember, mutableStateOf）
- ✅ LazyColumn（リスト表示）
- ✅ Scaffold、TopAppBar、FAB
- ✅ TextField、Card、Dialog
- ✅ Navigation Compose
- ✅ Animation（AnimatedVisibility）
- ✅ Modifier（レイアウト調整）

### Room Database
- ✅ Entity、DAO、Database
- ✅ Type Converters
- ✅ Flow による監視
- ✅ suspend 関数
- ✅ CRUD操作

### ViewModel & State
- ✅ ViewModel の作成
- ✅ StateFlow と MutableStateFlow
- ✅ collectAsState
- ✅ viewModelScope

### Dependency Injection (Hilt)
- ✅ @HiltAndroidApp
- ✅ @AndroidEntryPoint
- ✅ @HiltViewModel
- ✅ @Module と @InstallIn
- ✅ @Provides

### Coroutines & Flow
- ✅ suspend 関数
- ✅ viewModelScope
- ✅ Flow.collect
- ✅ Flow.catch（エラーハンドリング）

### MVVMアーキテクチャ
- ✅ Repository パターン
- ✅ UI State パターン
- ✅ 単方向データフロー

## 📸 スクリーンショット

（スクリーンショットを追加予定）

## 🔄 開発履歴

### Week 1: 基礎実装
- [x] プロジェクトセットアップ
- [x] Room Database実装
- [x] Repository層実装
- [x] Hilt設定

### Week 2: UI実装
- [x] 一覧画面（LazyColumn）
- [x] 詳細・編集画面
- [x] Navigation設定
- [x] ViewModel実装

### Week 3: 追加機能
- [x] 削除機能（AlertDialog）
- [x] 検索機能
- [x] アニメーション
- [x] スワイプで削除

### Week 4: 最終調整
- [x] 統計画面
- [x] DateUtils実装
- [x] UI改善
- [x] 最終テスト

## 🚧 今後の拡張案

- [ ] カレンダービュー
- [ ] 画像添付機能
- [ ] タグ機能
- [ ] データのバックアップ・復元
- [ ] ウィジェット
- [ ] リマインダー機能
- [ ] データのエクスポート（CSV/JSON）
- [ ] ダークモードの最適化
- [ ] テーマカスタマイズ

## 📝 次のフェーズ

- **フェーズ4**: ネットワーク通信とAPI連携
- **フェーズ5**: テストとCI/CD

## 📄 ライセンス

このプロジェクトは学習目的で作成されています。

## 👤 作成者

学習プロジェクトとして作成

## 🙏 謝辞

Jetpack ComposeとモダンなAndroid開発の学習のために作成しました。