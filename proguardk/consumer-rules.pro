# proguardk
#----------------------基本混淆指令的设置----------------------
# configuration
# 不混淆指定的包名。有多个包名可以用逗号隔开。包名可以包含 ？、*、** 通配符，还可以在包名前加上 ! 否定符。只有开启混淆时可用。如果你使用了 mypackage.MyCalss.class.getResource(""); 这些代码获取类目录的代码，就会出现问题。需要使用 -keeppackagenames 保留包名。
#-keeppackagenames com.yuanxiaocai.androidproguard
# 指定类、方法及字段混淆后时用的混淆字典。默认使用 ‘a’，’b’ 等短名称作为混淆后的名称。
#-obfuscationdictionary dictionary.txt
# 这个规则可能用于将混淆工具的使用信息输出到指定的文件 usage.txt 中。这通常是用来帮助用户了解工具的用法和参数选项。
-printusage ../mapping/usage.txt
# 此规则可能用于将混淆后的代码映射输出到指定的文件 mapping.txt 中。代码映射文件通常用于将混淆后的代码与原始代码之间的映射关系，这在调试和排查问题时非常有用。
-printmapping ../mapping/mapping.txt
# 这个规则可能用于将混淆工具生成的种子文件输出到指定的文件 seeds.txt 中。种子文件通常包含一些不需要进行混淆处理的代码或类名，以确保它们保持原样而不被混淆。
-printseeds ../mapping/seeds.txt
# 此规则可能用于将混淆工具的配置信息输出到指定的文件 configuration 中。这个文件可能包含有关混淆工具当前配置的详细信息，可以帮助用户了解混淆过程中使用的参数和设置。
-printconfiguration ../mapping/configuration
# 输出所有规则叠加后的混淆规则
-printconfiguration ./build/outputs/mapping/full-config.txt
#--------------------------------分割线----------------------------------
# 支持对应用的堆栈轨迹进行轨迹还原
-renamesourcefileattribute SourceFile
# 关闭优化功能。默认情况下启用优化。
-dontoptimize
# 关闭压缩功能。默认情况下，会开启压缩;
#-dontshrink
# 代码混淆压缩比，在0~7之间，默认为5，一般不做修改
-optimizationpasses 5
# 混合时不使用大小写混合，混合后的类名为小写
-dontusemixedcaseclassnames
# 优化时允许访问并修改有修饰符的类和类的成员
-allowaccessmodification
# 指定不忽略非公共库的类
-dontskipnonpubliclibraryclasses
# 指定不忽略非公共库的类成员
-dontskipnonpubliclibraryclassmembers
# 记录日志，使我们的项目混淆后产生映射文件（类名->混淆后类名）
-verbose
# 忽略警告，避免打包时某些警告出现，没有这个的话，构建报错
-ignorewarnings
# 不做预校验，preverify是proguard的四个步骤之一，Android不需要preverify，去掉这一步能够加快混淆速度。
-dontpreverify
# 不混淆Annotation(保留注解)
-keepattributes *Annotation*,InnerClasses,EnclosingMethod
# 避免混淆泛型
-keepattributes Signature
# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
# 指定混淆是采用的算法，后面的参数是一个过滤器
# 这个过滤器是谷歌推荐的算法，一般不做更改
-optimizations !code/simplification/cast,!field/*,!class/merging/*
#--------------------------------分割线----------------------------------

#----------------------Android开发中需要保留的公共部分----------------------
# 对于带有回调函数的onXXEvent、**On*Listener的，不能被混淆
-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
    void *(**I*Listener);
}
#--------------------------------分割线----------------------------------
# java
-dontwarn javax.annotation.**
-dontwarn java.lang.invoke.**
-dontwarn **$$Lambda$*
# 保留枚举类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
# 保留Parcelable序列化类不被混淆
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
# 保留Serializable序列化的类不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
#--------------------------------分割线----------------------------------
# android
# 保留R下面的资源
-keep class **.R$* {*;}
# 保留本地native方法不被混淆
# 保留本地 native 方法不被混淆
# 方法名中含有 JNI 字符的，认定是Java Native Interface方法 或 Java Reflection Interface 方法，自动排除
#-keepclasseswithmembers class * {... *JNI*(...);}
#-keepclasseswithmembernames class * {... *JRI*(...);}
-keep class **JNI* {*;}
-keepclasseswithmembernames class * {native <methods>;}
-keepclasseswithmembernames class * {
    native <methods>;
}
# 保留Activity中参数类型为View的所有方法
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
# 保留四大组件，自定义的Application等这些类不被混淆
-keep public class * extends android.app.Activity
# 保留在Activity中的方法参数是view的方法，
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Appliction
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep public class com.google.vending.licensing.ILicensingService
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.view.View
# 保留我们自定义控件（继承自View）不被混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public <init>(android.content.Context, android.util.AttributeSet, int,int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
# webview 还要注意native接口
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String);
}
# webview js
-keepattributes JavascriptInterface
-keep class android.webkit.JavascriptInterface { *; }
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
# -assumenosideeffects：告诉混淆器这些方法没有副作用，可以删除它们的调用。删除log.d的日志
#-assumenosideeffects class android.util.Log {
#    public static boolean isLoggable(java.lang.String, int);
#    public static int v(...);
#    public static int i(...);
#    public static int w(...);
#    public static int d(...);
#    public static int e(...);
#}
#--------------------------------分割线----------------------------------
# support
# 保留support下的所有类及其内部类
-keep class android.support.** {*;}
-dontnote android.support.**
-dontwarn android.support.**
# 保留继承的support类
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**
# 保留Keep注解的类名和方法
-keep,allowobfuscation @interface android.support.annotation.Keep
-keep @android.support.annotation.Keep class *
-keep class android.support.annotation.Keep
-keepclassmembers class * {
    @android.support.annotation.Keep *;
}
-keep @android.support.annotation.Keep class * {*;}
-keepclasseswithmembers class * {
    @android.support.annotation.Keep <methods>;
}
-keepclasseswithmembers class * {
    @android.support.annotation.Keep <fields>;
}
-keepclasseswithmembers class * {
    @android.support.annotation.Keep <init>(...);
}
-keepclasseswithmembers class *{
      <init>(android.content.Context);
}
#--------------------------------分割线----------------------------------
# androidx
# androidx混淆
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-dontwarn androidx.**
#--------------------------------分割线----------------------------------
# google
-keep class com.google.** {
    <fields>;
    <methods>;
}
#-keep class com.xx.xxx.fun_data.mos.** { *; }