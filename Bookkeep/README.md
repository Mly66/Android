# Bookkeep - 简单记账应用

Bookkeep 是一个用户友好的 Android 记账应用程序，旨在帮助您轻松管理个人收支。它提供直观的界面，用于记录日常交易、查看统计数据和管理个人资料。

## 功能特点

-   **用户认证**: 安全的用户注册和登录系统。
-   **账单管理**: 方便地添加、编辑和删除支出/收入记录。
-   **个人资料**: 查看和更新个人信息，包括修改密码（需旧密码验证，修改后强制重新登录）。
-   **收支统计**: 提供按类别和按月份的详细支出和收入统计（饼图和柱状图）。
-   **导航抽屉**: 简洁的侧滑导航菜单，方便切换不同功能模块。
-   **自定义图标**: 支持自定义应用桌面图标。

## 技术栈

-   **开发语言**: Java
-   **平台**: Android
-   **数据库**: SQLite (通过 `android.database.sqlite`)
-   **图表库**: [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) (用于数据可视化)
-   **UI/UX**: Material Design 组件

## 环境设置

1.  **Android Studio**: 确保您已安装最新版本的 Android Studio。
2.  **Gradle**: 项目使用 Gradle 构建系统，Android Studio 会自动处理 Gradle 的设置。
3.  **SDK 版本**: 请确保您的 Android Studio 已安装项目所需的 Android SDK 版本。

## 如何运行

1.  **克隆或下载项目**: 如果您是通过版本控制系统获取项目，请克隆它；否则，请下载项目文件并解压。
2.  **在 Android Studio 中打开**: 启动 Android Studio，选择 "Open an existing Android Studio project"，然后导航到项目的根目录并打开它。
3.  **同步 Gradle**: Android Studio 会自动同步 Gradle。如果遇到问题，请手动点击 "Sync Project with Gradle Files" 按钮（通常在工具栏上有一个大象图标）。
4.  **构建项目**: 点击 "Build" -> "Rebuild Project" 以确保所有依赖项都已正确编译。
5.  **运行应用**: 
    *   连接一个 Android 设备，并确保已启用 USB 调试。
    *   或者，创建一个 Android 模拟器。
    *   点击工具栏上的 "Run" 按钮（绿色的播放图标），选择您的设备或模拟器，即可运行应用。

## 贡献

欢迎贡献！如果您有任何建议或改进，请随时提交 Pull Request 或创建 Issue。

## 许可证

[此处可以添加您的许可证信息，例如 MIT, Apache 2.0 等。如果未指定，可以留空或删除此部分。] 