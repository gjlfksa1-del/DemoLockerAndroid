#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_cart3r_demolocker_MainActivity_nativeCheckPin(
    JNIEnv* env,
    jobject,
    jstring input
) {
    if (input == nullptr) {
        return JNI_FALSE;
    }

    const char* chars =
        env->GetStringUTFChars(input, nullptr);

    if (chars == nullptr) {
        return JNI_FALSE;
    }

    std::string supplied(chars);

    env->ReleaseStringUTFChars(input, chars);

    constexpr char demoPin[] = "1234";

    return supplied == demoPin
        ? JNI_TRUE
        : JNI_FALSE;
}
