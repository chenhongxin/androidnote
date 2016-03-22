jni配置环境
1.加载别人的库
(1).配置ndk环境
(2).配置libs
sourceSets {
            main {
                jniLibs.srcDirs = ['libs']
            }
        }
(3).在gradle.properties文件中配置android.useDeprecatedNdk=true