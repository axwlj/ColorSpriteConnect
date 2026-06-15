# DroidSSH & SFTP Pro

DroidSSH & SFTP Pro 是一款基于现代 Material Design 3 (Material You) 设计的安卓专业级 SSH 客户端。它旨在为系统管理员和开发者提供一个安全、高效、且具备极致交互体验的移动运维终端。

## 🚀 核心功能

- **全功能终端**：支持 ANSI 全彩显示、Xterm-256 色、虚拟功能键及自定义快捷指令。
- **双重文件管理**：内置 SFTP 浏览器与本地文件管理器，支持 Zmodem (rz/sz) 文件传输协议。
- **工业级安全**：集成 Android Keystore 硬件级加密、生物识别安全锁 (指纹/面部)，支持 RSA/Ed25519 密钥生成。
- **生产力套件**：支持后台持久连接 (Foreground Service)、端口转发 (SSH Tunneling)、多会话任务切换。
- **极致设计**：适配 Android 12+ 动态取色、折叠屏/平板分栏布局、暗色模式及国际化。

## 🛠 技术栈

- **语言**: Kotlin 1.9.0
- **UI 框架**: Jetpack Compose (Material 3)
- **架构**: MVVM + Dagger Hilt (DI)
- **数据库**: Room (SQLite)
- **网络**: sshj (SSH2 Protocol Implementation)
- **异步**: Kotlin Coroutines & Flow

## 💻 部署与运行环境

- **Android Studio**: Iguana (2023.2.1) 或更高版本。
- **JDK**: JDK 17 (必须，以便支持 Gradle 8+)。
- **Android SDK**: Min SDK 26 (Android 8.0), Target SDK 34 (Android 14)。

## 📥 导入项目

1. 克隆或下载本代码库。
2. 打开 Android Studio，选择 `Open` 并定位到项目根目录。
3. 等待 Gradle 同步完成（需联网下载依赖库）。

> **注意 (常见问题)**: 如果您在中国大陆地区遇到 `Remote host terminated the handshake` 或 TLS 相关下载错误，本项目已在 `build.gradle` 中预置了阿里云镜像。如果问题依然存在，请检查您的代理设置或在 `gradle.properties` 中添加：
> ```properties
> systemProp.https.protocols=TLSv1.2,TLSv1.3
> ```

## 📦 打包与发布流程

### 1. 生成调试包 (Debug APK)
在 Android Studio 终端执行：
```bash
./gradlew assembleDebug
```
生成的 APK 位于 `app/build/outputs/apk/debug/`。

### 2. 生成正式签名包 (Release APK/AAB)

1. **创建签名文件**:
   - `Build` -> `Generate Signed Bundle / APK` -> `APK`。
   - 创建新的 `.jks` 密钥库文件并保存密码。
2. **配置 Gradle (推荐)**:
   - 在 `app/build.gradle` 中配置 `signingConfigs` 引用上述文件。
3. **执行打包**:
   - 在右侧 Gradle 面板中，执行 `app` -> `Tasks` -> `build` -> `assembleRelease`。
   - 或者使用命令行：`./gradlew assembleRelease`。
4. **获取产物**:
   - Release APK 位于 `app/build/outputs/apk/release/`。
   - 建议发布 Google Play 时选择 `bundleRelease` 生成 AAB 文件。

## 📂 项目结构

- `app/src/main/java/com/example/droidssh/ui`: Compose UI 界面与主题。
- `app/src/main/java/com/example/droidssh/service`: SSH 协议处理与前台服务。
- `app/src/main/java/com/example/droidssh/data`: Room 数据库与 DAO。
- `assets/`: 包含本项目所使用的所有矢量资源及 HTML 原型。

---
*本项目遵循 Material Design 3 设计指南开发。*
