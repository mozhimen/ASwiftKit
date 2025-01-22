# blurk
# Rendescript
-keepclasseswithmembernames class * {
   native <methods>;
}

-keep class androidx.renderscript.** { *; }
-keep class android.support.renderscript.** { *; }