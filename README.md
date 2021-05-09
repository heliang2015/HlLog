# Android 超级实用日志调式工具-HlLog
🔥 Hllog 超级实用的android 日志打印工具，支持文件打印，app上视图日志打印，控制台日志打印；Hllog具有高可扩展性，可以自己实现自己的打印器。

[![](https://jitpack.io/v/heliang2015/HlLog.svg)](https://jitpack.io/#heliang2015/HlLog)
### 特点功能：
- 支持在手机app界面上直接实时查看日志；
- 支持控制台查看日志；
- 支持打印文件日志；
- 支持打印堆栈调用信息及深度；
- 支持配置打印线程信息；
- 高可扩展性，可定义自己的打印器；
### HlLog架构图：
### 项目示例：
![20210509_190403.gif](https://upload-images.jianshu.io/upload_images/1928063-fd6f94ca5d3bf467.gif?imageMogr2/auto-orient/strip)
### 使用介绍：
#### 1.在项目中添加依赖；
```java
在项目的build.gradle中：
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
 }
  
  
在app的build.gradle中加入库依赖：
dependencies {
   implementation 'androidx.recyclerview:recyclerview:1.2.0'
	 implementation 'com.github.heliang2015:HlLog:1.0.3'
}
```
#### 2.添加权限；
```java
添加文件读写权限
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```
#### 3.初始化；
```java

自定义Application,在Application中初始化HLlog

HlLogManager.getInstance().init(new HlLogConfig().setOpen(true).setTag("HlLog").setTraceDeep(3),
        new IHlLogPrinter[]{new HlconSolePrinter(), new HlLogFilePrinter()});
        
        
/* 方法说明 */        
setOpen() // 是否打开
setTag()  // 全局TAG
setTraceDeep() // 堆栈调用信息深度 传0 不打印堆栈
HlconSolePrinter() // 控制台打印器
HlLogFilePrinter（） // 文件打印器
```

#### 4.开始使用；
```java
// 使用app HlViewPrinter 需要在activity进行初始化，可以定义在基类中；
HlViewPrinter hlViewPrinter = new HlViewPrinter(this);
HlLogManager.getInstance().addPrinter(hlViewPrinter);

最后调用,即可在文件，控制台，app上查看日志了
 HlLog.d("测试一下");
```
### 讨论：
### 其他作品




