# はじめに
## アプリの概要
ユーザーが自由に設定したものの数をカウントして保存することができるアプリ

## 目的
- git, GitHub の使い方の学習
- Android アプリの開発の流れの学習
- MVVMアーキテクチャへの理解を深める

# アプリの詳細
## 画面構成
以下の2つの画面を持つ
- カウント画面
- 設定画面

### カウント画面
- 複数の物をカウントできるようにする
- 対象物1つにつき+ボタンと-ボタンを作成し、カウントのインクリメントとデクリメントができるようにする
- 対象物の追加、削除ができるようにする
- [カウント対象名] + [数] - というUIでカウントを行う
- TopAppBar に設定画面へ行くボタンを配置する
- ダークテーマに対応させる

### 設定画面
- ダークテーマの切り替えスイッチを配置する
- その他の設定は未定...

## 実装について
### UI
Jetpack Compose で作成する

### アーキテクチャ
MVVM を採用する

UI の状態は UIState としてまとめ、Flow で流す

### データの保存について
DataStore を使用する
