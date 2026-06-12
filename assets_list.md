# DroidSSH & SFTP Pro 资源素材需求清单

为了将 HTML 原型转化为真实的安卓应用，您需要准备以下图片和图形素材。建议优先使用矢量图（SVG/Vector Drawable），以适配安卓多种屏幕分辨率。

## 1. 应用图标 (App Icons)
| 素材名称 | 用途 | 建议规格 | 格式 |
| :--- | :--- | :--- | :--- |
| **ic_launcher** | 应用启动图标 | 108x108 dp (安全区域 66x66) | Android Adaptive Icon (SVG/XML) |
| **ic_launcher_round** | 圆形启动图标 | 108x108 dp | Adaptive Icon |
| **ic_notification** | 通知栏状态图标 | 24x24 dp | 单色透明 (White-only PNG/SVG) |

## 2. 核心功能图标 (Functional Icons - 建议使用 Material Design 风格)
*这些图标通常内置在安卓资源中，但若需自定义，请按以下清单准备：*

| 素材名称 | 对应功能 | 视觉特征 |
| :--- | :--- | :--- |
| **ic_terminal** | SSH 终端 | 提示符 `>_` 或 方框符号 |
| **ic_sftp** | 文件传输 | 文件夹 + 左右双向箭头 |
| **ic_key** | 密钥管理 | 钥匙或盾牌带钥匙孔 |
| **ic_tunnel** | 端口转发/隧道 | 管道或两点连线图标 |
| **ic_server** | 服务器列表 | 刀片服务器机架形象 |
| **ic_shortcut** | 快捷命令 | 闪电或星标符号 |

## 3. 文件类型图标 (SFTP Browser Icons)
| 素材名称 | 对应文件类型 | 建议颜色 |
| :--- | :--- | :--- |
| **ic_folder** | 文件夹 | #FFB300 (Amber) |
| **ic_file_generic**| 未知文件 | #9E9E9E (Gray) |
| **ic_file_code** | 脚本/代码 (.sh, .py, .php) | #2196F3 (Blue) |
| **ic_file_config** | 配置文件 (.conf, .yaml, .json) | #607D8B (Blue Gray) |
| **ic_file_image** | 图片文件 | #4CAF50 (Green) |
| **ic_file_archive** | 压缩包 (.zip, .tar.gz) | #F44336 (Red) |

## 4. 插画/占位图 (Empty States & Onboarding)
| 素材名称 | 用途 | 建议风格 |
| :--- | :--- | :--- |
| **img_empty_connections** | 无连接时的背景图 | 极简线条感，服务器搜索雷达形象 |
| **img_security_hero** | 密钥管理页顶部装饰 | 盾牌与指纹的融合，科技感紫色调 |
| **img_welcome_1** | 欢迎页1：SSH 功能介绍 | 手持手机操作远程服务器的插画 |
| **img_welcome_2** | 欢迎页2：SFTP 功能介绍 | 云端与本地数据同步的抽象图 |

## 5. 交互元素 (Interaction Assets)
- **Splash Screen Logo**: 纯净的应用 Logo，用于 Android 12+ 启动动画。
- **Virtual Key Backgrounds**: 终端键盘按钮的九宫格图（或使用 XML Drawable 实现）。

## 技术规格说明
- **尺寸标注**: 所有图标应遵循 Material Design 的 24dp 基准。
- **色彩规范**:
  - Primary (主色): `#6750A4`
  - Secondary (次色): `#625B71`
  - Surface (表面色): `#FEF7FF`
- **导出路径建议**:
  - `res/drawable-anydpi/` (SVG/XML)
  - `res/mipmap-xxxhdpi/` (PNG 备份)
