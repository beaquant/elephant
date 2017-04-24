# include <string.h>
# include "com_slackerOne_JPP.h"
# include "api.h"


JNIEXPORT jint JNICALL Java_com_slackerOne_JPP_intMethod
  (JNIEnv * env, jobject obj, jint i)
{
    return i * i;
}

JNIEXPORT jboolean JNICALL Java_com_slackerOne_JPP_booleanMethod
  (JNIEnv * env, jobject obj, jboolean b)
{
    return !b;
}

JNIEXPORT jstring JNICALL Java_com_slackerOne_JPP_stringMethod
  (JNIEnv * env, jobject obj, jstring string)
{
    const char* str = (*env)->GetStringUTFChars(env, string, 0);
    char cap[128];
    strcpy(cap, str);
    (*env)->ReleaseStringUTFChars(env, string, 0);
    return (*env)->NewStringUTF(env, cap);
}
JNIEXPORT jstring JNICALL Java_com_slackerOne_JPP_getFirmwareVersion
  (JNIEnv * env, jobject obj)
{
    char* str = getFirmwareVersion();
    char cap[128];
    strcpy(cap, str);
    return (*env)->NewStringUTF(env, cap);
}
JNIEXPORT jint JNICALL Java_com_slackerOne_JPP_intArrayMethod
  (JNIEnv * env, jobject obj, jintArray array)
{
    int i, sum = 0;
    jsize len = (*env)->GetArrayLength(env, array);
    jint * body = (*env)->GetIntArrayElements(env, array, 0);
    for (i=0; i<len; i++)
    {
        sum += body[i];
    }
    (*env)->ReleaseIntArrayElements(env, array, body, 0);
    return sum;
}
