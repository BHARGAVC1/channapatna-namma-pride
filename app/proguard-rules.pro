-keep class com.channapatna.nammapride.data.model.** { *; }
-keep class com.channapatna.nammapride.data.local.entity.** { *; }
-keepclassmembers class com.channapatna.nammapride.data.local.entity.** { *; }
-keep class com.channapatna.nammapride.data.remote.dto.** { *; }
-keepclassmembers class com.channapatna.nammapride.data.remote.dto.** { *; }

# Firestore needs annotations and generics for reflection
-keepattributes Signature
-keepattributes *Annotation*
-keepattributes EnclosingMethod

# Firebase Auth
-keepattributes InnerClasses
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }

# Kotlin data classes — keep component functions and copy()
-keepclassmembers class * {
    public synthetic bridge *(...);
}
-keep class kotlin.Metadata { *; }

# Coroutines
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

# Hilt — generated component classes
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep @dagger.hilt.android.lifecycle.HiltViewModel class * { *; }
