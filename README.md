## Overview ##
<pre>
└── <a href="#id1" title="cn.mutils.app.droid.utils"><b>cn.mutils.app.droid.utils</b></a>
    ├── <a href="#id2" title="cn.mutils.app.droid.utils.device"><b>device</b></a>
    │   ├── <a href="#id3" title="cn.mutils.app.droid.utils.device.ConnectivityMonitor">ConnectivityMonitor</a>  网络状态监听器
    │   ├── <a href="#id4" title="cn.mutils.app.droid.utils.device.ConnectivityMonitor.ConnectivityObserver">ConnectivityMonitor.ConnectivityObserver</a>  网络变化观察者
    │   ├── <a href="#id5" title="cn.mutils.app.droid.utils.device.DimenUtil">DimenUtil</a>  常用单位转换
    │   ├── <a href="#id6" title="cn.mutils.app.droid.utils.device.HardwareUtil">HardwareUtil</a>  硬件检测
    │   ├── <a href="#id7" title="cn.mutils.app.droid.utils.device.KeyboardUtil">KeyboardUtil</a>  键盘工具
    │   ├── <a href="#id8" title="cn.mutils.app.droid.utils.device.NetworkUtil">NetworkUtil</a>  手机网络信息
    │   └── <a href="#id9" title="cn.mutils.app.droid.utils.device.ScreenUtil">ScreenUtil</a>  屏幕工具类
    ├── <a href="#id10" title="cn.mutils.app.droid.utils.encrypt"><b>encrypt</b></a>
    │   ├── <a href="#id11" title="cn.mutils.app.droid.utils.encrypt.Base64Util">Base64Util</a>  Base64工具类
    │   ├── <a href="#id12" title="cn.mutils.app.droid.utils.encrypt.DesUtil">DesUtil</a>  DES工具类
    │   ├── <a href="#id13" title="cn.mutils.app.droid.utils.encrypt.HexUtil">HexUtil</a>  十六进制实用类
    │   ├── <a href="#id14" title="cn.mutils.app.droid.utils.encrypt.MD5Util">MD5Util</a>  MD5实用类
    │   └── <a href="#id15" title="cn.mutils.app.droid.utils.encrypt.RsaUtil">RsaUtil</a>  SHA1WithRSA签名校验工具
    ├── <a href="#id16" title="cn.mutils.app.droid.utils.graphics"><b>graphics</b></a>
    │   └── <a href="#id17" title="cn.mutils.app.droid.utils.graphics.BitmapUtil">BitmapUtil</a>  位图工具
    ├── <a href="#id18" title="cn.mutils.app.droid.utils.io"><b>io</b></a>
    │   ├── <a href="#id19" title="cn.mutils.app.droid.utils.io.FileUtil">FileUtil</a>  文件工具类
    │   ├── <a href="#id20" title="cn.mutils.app.droid.utils.io.IOUtil">IOUtil</a>  I/O操作工具类
    │   └── <a href="#id21" title="cn.mutils.app.droid.utils.io.ZipUtil">ZipUtil</a>  Zip压缩工具
    ├── <a href="#id22" title="cn.mutils.app.droid.utils.os"><b>os</b></a>
    │   ├── <a href="#id23" title="cn.mutils.app.droid.utils.os.AppUtil">AppUtil</a>  应用相关实用类
    │   ├── <a href="#id24" title="cn.mutils.app.droid.utils.os.SDCardUtil">SDCardUtil</a>  SD卡辅助类
    │   ├── <a href="#id25" title="cn.mutils.app.droid.utils.os.TaskExecutor">TaskExecutor</a>  任务执行，支持回调
    │   ├── <a href="#id26" title="cn.mutils.app.droid.utils.os.TaskExecutor.Task">TaskExecutor.Task</a>  任务
    │   ├── <a href="#id27" title="cn.mutils.app.droid.utils.os.ThreadExecutor">ThreadExecutor</a>  子线程执行工具
    │   ├── <a href="#id28" title="cn.mutils.app.droid.utils.os.ThreadPool">ThreadPool</a>  线程池
    │   └── <a href="#id29" title="cn.mutils.app.droid.utils.os.UiExecutor">UiExecutor</a>  主线程执行工具
    ├── <a href="#id30" title="cn.mutils.app.droid.utils.runtime"><b>runtime</b></a>
    │   ├── <a href="#id31" title="cn.mutils.app.droid.utils.runtime.ReflectUtil">ReflectUtil</a>  反射工具
    │   └── <a href="#id32" title="cn.mutils.app.droid.utils.runtime.StackTraceUtil">StackTraceUtil</a>  堆栈实用类
    ├── <a href="#id33" title="cn.mutils.app.droid.utils.sort"><b>sort</b></a>
    │   └── <a href="#id34" title="cn.mutils.app.droid.utils.sort.SortUtil">SortUtil</a>  排序实用类
    ├── <a href="#id35" title="cn.mutils.app.droid.utils.time"><b>time</b></a>
    │   └── <a href="#id36" title="cn.mutils.app.droid.utils.time.CalendarUtil">CalendarUtil</a>  日期工具类
    └── <a href="#id37" title="cn.mutils.app.droid.utils.ui"><b>ui</b></a>
        └── <a href="#id38" title="cn.mutils.app.droid.utils.ui.NoDBClickUtil">NoDBClickUtil</a>  避免快速双击实用类
</pre>

<a name="id2"></a>

## cn.mutils.app.droid.utils.device ##

<a name="id3"></a>

### ConnectivityMonitor ###

>  网络状态监听器<br/>
> <br/>
>  Created by wenhua.ywh on 2017/3/9.

<br/><b>NETWORK\_TYPE\_2G</b> : int

>  2G网络

<br/><b>NETWORK\_TYPE\_3G</b> : int

>  3G网络

<br/><b>NETWORK\_TYPE\_4G</b> : int

>  4G网络

<br/><b>NETWORK\_TYPE\_UNKNOWN</b> : int

>  未知网络

<br/><b>NETWORK\_TYPE\_WIFI</b> : int

>  Wifi网络

<br/><b>addObserver</b>(ConnectivityMonitor.ConnectivityObserver observer)

>  添加网络变化观察者<br/>
> <br/>
>  @param observer 观察者

<br/><b>clearObservers</b>()

>  清空网络变化观察者

<br/><b>getInstance</b>()

>  单例方式获取实例<br/>
> <br/>
>  @return 监听器

<br/><b>getNetworkType</b>()

>  获取当前网络类型<br/>
> <br/>
>  @return 网络类型

<br/><b>install</b>(Context context)

>  安装监听器<br/>
> <br/>
>  @param context 上下文

<br/><b>removeObserver</b>(ConnectivityMonitor.ConnectivityObserver observer)

>  移除网络变化观察者<br/>
> <br/>
>  @param observer 观察者

<br/><b>uninstall</b>(Context context)

>  移除监听器

------
<a name="id4"></a>

### ConnectivityMonitor.ConnectivityObserver ###

>  网络变化观察者

<br/><b>onConnectivityChanged</b>(int newNetworkType, int oldNetworkType)

>  网络发送变化<br/>
> <br/>
>  由监听器内部保证在主线程回调<br/>
> <br/>
>  @param newNetworkType 新网络类型<br/>
>  @param oldNetworkType 旧网络类型

------
<a name="id5"></a>

### DimenUtil ###

>  常用单位转换

<br/><b>dp2px</b>(Context context, float dpValue)

>  将dp转换为px<br/>
> <br/>
>  @param context 上下文<br/>
>  @param dpValue dp<br/>
>  @return px

<br/><b>dp2px</b>(DisplayMetrics metrics, float dpValue)

>  将dp转换为px<br/>
> <br/>
>  @param metrics 显示指标<br/>
>  @param dpValue dp<br/>
>  @return px

<br/><b>px2dp</b>(Context context, float pxValue)

>  将px转换为dp<br/>
> <br/>
>  @param context 上下文<br/>
>  @param pxValue px<br/>
>  @return dp

<br/><b>px2dp</b>(DisplayMetrics metrics, float pxValue)

>  将px转换为dp<br/>
> <br/>
>  @param metrics 显示指标<br/>
>  @param pxValue px<br/>
>  @return dp

<br/><b>px2sp</b>(Context context, float pxValue)

>  将px转换为sp<br/>
> <br/>
>  @param context 上下文<br/>
>  @param pxValue px<br/>
>  @return sp

<br/><b>px2sp</b>(DisplayMetrics metrics, float pxValue)

>  将px转换为sp<br/>
> <br/>
>  @param metrics 显示指标<br/>
>  @param pxValue px<br/>
>  @return sp

<br/><b>sp2px</b>(Context context, float spValue)

>  将sp转换为px<br/>
> <br/>
>  @param context 上下文<br/>
>  @param spValue sp<br/>
>  @return px

<br/><b>sp2px</b>(DisplayMetrics metrics, float spValue)

>  将sp转换为px<br/>
> <br/>
>  @param metrics 显示指标<br/>
>  @param spValue sp<br/>
>  @return px

------
<a name="id6"></a>

### HardwareUtil ###

>  硬件检测

<br/><b>hasGpsDevice</b>(Context context)

>  判断是否存在GPS设备<br/>
> <br/>
>  @param context 上下文<br/>
>  @return 是否存在

<br/><b>isAllowMockLocation</b>(Context context)

>  是否使用模拟定位<br/>
> <br/>
>  @param context 上下文<br/>
>  @return 是否使用

<br/><b>isGpsEnabled</b>(Context context)

>  是否开启gps<br/>
> <br/>
>  @param context 上下文<br/>
>  @return 是否开启

<br/><b>isWifiEnabled</b>(Context context)

>  是否开启wifi<br/>
> <br/>
>  @param context 上下文<br/>
>  @return 是否开启

------
<a name="id7"></a>

### KeyboardUtil ###

>  键盘工具

<br/><b>forceHideKeyboard</b>(EditText editText)

>  强制影藏键盘<br/>
> <br/>
>  @param editText 输入组件

<br/><b>hideKeyboard</b>(EditText editText)

>  影藏键盘<br/>
> <br/>
>  @param editText 输入组件

<br/><b>showKeyboard</b>(EditText editText)

>  显示键盘<br/>
> <br/>
>  @param editText 输入组件<br/>
>  @return 是否成功

------
<a name="id8"></a>

### NetworkUtil ###

>  手机网络信息

<br/><b>NETWORK\_TYPE\_2G</b> : int

>  2G网络

<br/><b>NETWORK\_TYPE\_3G</b> : int

>  3G网络

<br/><b>NETWORK\_TYPE\_4G</b> : int

>  4G网络

<br/><b>NETWORK\_TYPE\_UNKNOWN</b> : int

>  未知网络

<br/><b>NETWORK\_TYPE\_WIFI</b> : int

>  Wifi网络

<br/><b>getMobileNetType</b>(Context context, NetworkInfo info)

>  获取手机网络类型<br/>
> <br/>
>  {@link #NETWORK_TYPE_UNKNOWN} {@link #NETWORK_TYPE_2G} {@link #NETWORK_TYPE_3G} {@link<br/>
>  #NETWORK_TYPE_4G}<br/>
> <br/>
>  @param context 上下文<br/>
>  @param info    网络信息描述<br/>
>  @return 类型

<br/><b>getNetworkSubTypeName</b>(Context context)

>  获取手机通信协议的网络制式<br/>
> <br/>
>  @param context 上下文<br/>
>  @return CDMA2000 WCDMA TD-SCDMA

<br/><b>getNetworkType</b>(Context context)

>  获取网络类型<br/>
> <br/>
>  {@link #NETWORK_TYPE_UNKNOWN} {@link #NETWORK_TYPE_WIFI} {@link #NETWORK_TYPE_2G} {@link<br/>
>  #NETWORK_TYPE_3G} {@link #NETWORK_TYPE_4G}<br/>
> <br/>
>  @param context 上下文<br/>
>  @return 类型

<br/><b>getNetworkTypeName</b>(Context context)

>  获取网络连接状态<br/>
> <br/>
>  @param context 上下文<br/>
>  @return wifi mobile

<br/><b>getOperatorName</b>(Context context)

>  获取运营商名称<br/>
> <br/>
>  @param context 上下文<br/>
>  @return 运营商

<br/><b>isHttpProtocol</b>(String url)

>  判断是否是http协议<br/>
> <br/>
>  @param url 地址<br/>
>  @return 是否是http

<br/><b>isNetworkConnected</b>(Context context)

>  判断网络是否连接<br/>
> <br/>
>  @param context 上下文<br/>
>  @return 是否连接

------
<a name="id9"></a>

### ScreenUtil ###

>  屏幕工具类

<br/><b>getScreenBrightness</b>(Context context)

>  获取屏幕亮度<br/>
> <br/>
>  @param context 上下文<br/>
>  @return 亮度

<br/><b>getScreenSize</b>(Context context)

>  获取屏幕宽高<br/>
> <br/>
>  @param context 上下文<br/>
>  @return 宽高

<br/><b>getStatusBarHeight</b>(Context context)

>  获取状态栏高度<br/>
> <br/>
>  @param context 上下文<br/>
>  @return 状态栏高度

<br/><b>isScreenBrightnessAutoMode</b>(Context context)

>  屏幕是否自动调节亮度<br/>
> <br/>
>  @param context 上下文<br/>
>  @return 是否自动调节

<br/><b>setScreenBrightness</b>(Context context, int brightness)

>  设置亮度<br/>
> <br/>
>  @param brightness 亮度

------
<a name="id10"></a>

## cn.mutils.app.droid.utils.encrypt ##

<a name="id11"></a>

### Base64Util ###

>  Base64工具类

<br/><b>fromBytes</b>(byte bytes)

>  将二进制编码为base64字符串<br/>
> <br/>
>  @param bytes 二进制数据<br/>
>  @return base64编码

<br/><b>toBytes</b>(String base64)

>  将base64编码的字符串解码<br/>
> <br/>
>  @param base64 已编码的字符串<br/>
>  @return 二进制数据

------
<a name="id12"></a>

### DesUtil ###

>  DES工具类

<br/><b>decrypt</b>(String cipherText, String key)

>  解密<br/>
> <br/>
>  @param cipherText 密文<br/>
>  @param key        密码<br/>
>  @return 明文

<br/><b>encrypt</b>(String plainText, String key)

>  加密<br/>
> <br/>
>  @param plainText 明文<br/>
>  @param key       密钥<br/>
>  @return 密文

------
<a name="id13"></a>

### HexUtil ###

>  十六进制实用类

<br/><b>toHex</b>(byte data)

>  将二进制转换为十六进制字符串<br/>
> <br/>
>  @param data 二进制数据<br/>
>  @return 大写格式十六进制字符串

<br/><b>toHex</b>(byte data, boolean lowerCaseFormat)

>  将二进制转换为十六进制字符串<br/>
> <br/>
>  @param data            二进制数据<br/>
>  @param lowerCaseFormat 是否是小写格式<br/>
>  @return 十六进制字符串

------
<a name="id14"></a>

### MD5Util ###

>  MD5实用类<br/>
> <br/>
>  默认返回大写字母形式，如果调用过程中发生异常返回空字符串

<br/><b>getByteArrayMD5</b>(byte data)

>  获取二进制MD5<br/>
> <br/>
>  @param data 数据<br/>
>  @return 大写格式摘要

<br/><b>getByteArrayMD5</b>(byte data, String defValue, boolean lowerCaseFormat)

>  获取二进制MD5<br/>
> <br/>
>  @param data            数据<br/>
>  @param defValue        默认值<br/>
>  @param lowerCaseFormat 是否是小写格式<br/>
>  @return 摘要

<br/><b>getByteArrayMD5</b>(byte data, int maxSize)

>  获取数据MD5，如果数据长度超出最大长度则取最大长度之前的二进制数据的MD5<br/>
> <br/>
>  @param data    数据<br/>
>  @param maxSize 最大数据长度<br/>
>  @return 大写格式摘要

<br/><b>getByteArrayMD5</b>(byte data, int maxSize, String defValue, boolean lowerCaseFormat)

>  获取数据MD5，如果数据长度超出最大长度则取最大长度之前的二进制数据的MD5<br/>
> <br/>
>  @param data            数据<br/>
>  @param maxSize         最大数据长度<br/>
>  @param defValue        默认值<br/>
>  @param lowerCaseFormat 是否是小写格式<br/>
>  @return 摘要

<br/><b>getFileMD5</b>(File file)

>  获取文件MD5<br/>
> <br/>
>  @param file 文件<br/>
>  @return 大写格式摘要

<br/><b>getFileMD5</b>(File file, String defValue, boolean lowerCaseFormat)

>  获取文件MD5<br/>
> <br/>
>  @param file            文件<br/>
>  @param defValue        默认值<br/>
>  @param lowerCaseFormat 是否是小写格式<br/>
>  @return 摘要

<br/><b>getStreamMD5</b>(InputStream input, boolean lowerCaseFormat)

>  获取数据流MD5<br/>
> <br/>
>  @param input           数据流<br/>
>  @param lowerCaseFormat 是否是小写格式<br/>
>  @return 摘要

<br/><b>getStreamMD5Digest</b>(InputStream input)

>  获取数据流摘要

<br/><b>getStringMD5</b>(String data)

>  获取字符串MD5<br/>
> <br/>
>  @param data 字符串<br/>
>  @return 大写格式摘要

<br/><b>getStringMD5</b>(String data, String defValue, boolean lowerCaseFormat)

>  获取字符串MD5<br/>
> <br/>
>  @param data            字符串<br/>
>  @param defValue        默认值<br/>
>  @param lowerCaseFormat 是否是小写格式<br/>
>  @return 摘要

------
<a name="id15"></a>

### RsaUtil ###

>  SHA1WithRSA签名校验工具

<br/><b>generateKeyPair</b>(File publicKeyFile, File privateKeyFile)

>  自动生成成对公钥与私钥<br/>
> <br/>
>  @param publicKeyFile  公钥文件<br/>
>  @param privateKeyFile 私钥文件<br/>
>  @return 死否成功

<br/><b>sign</b>(File file, String privateKeyStr)

>  使用私钥对文件进行签名<br/>
> <br/>
>  @param file          文件<br/>
>  @param privateKeyStr base64编码的私钥<br/>
>  @return base64编码格式的签名

<br/><b>verify</b>(File file, String sign, String publicKeyStr)

>  使用公钥校验文件签名是否有效<br/>
> <br/>
>  @param file         文件<br/>
>  @param sign         bas64编码格式的签名<br/>
>  @param publicKeyStr base64编码格式的公钥<br/>
>  @return 是否有效

------
<a name="id16"></a>

## cn.mutils.app.droid.utils.graphics ##

<a name="id17"></a>

### BitmapUtil ###

>  位图工具

<br/><b>save</b>(Bitmap bitmap, String path)

>  保存位图到指定路径<br/>
> <br/>
>  @param bitmap 位图<br/>
>  @param path   路径<br/>
>  @return 是否保存成功

<br/><b>toBitmap</b>(Drawable drawable)

>  将显示资源转换为位图<br/>
> <br/>
>  @param drawable 显示资源<br/>
>  @return 位图

<br/><b>toBitmap</b>(View v)

>  将视图转换为位图<br/>
> <br/>
>  @param v 视图<br/>
>  @return 位图

<br/><b>toByteArray</b>(Bitmap bitmap)

>  将位图转换为byte数组<br/>
> <br/>
>  @param bitmap 位图<br/>
>  @return byte数组

<br/><b>toGreyBitmap</b>(Bitmap bitmap)

>  将位图变灰<br/>
> <br/>
>  @param bitmap 位图<br/>
>  @return 变灰的位图

<br/><b>toGreyDrawable</b>(Drawable drawable)

>  将显示资源变灰<br/>
> <br/>
>  @param drawable 显示资源<br/>
>  @return 灰色资源

------
<a name="id18"></a>

## cn.mutils.app.droid.utils.io ##

<a name="id19"></a>

### FileUtil ###

>  文件工具类

<br/><b>cleanUpFilesAsync</b>(List paths)

>  异步删除文件<br/>
> <br/>
>  @param paths 问价列表

<br/><b>copyAssetFile</b>(Context context, String assetName, File destFile)

>  拷贝asset资源文件<br/>
> <br/>
>  @param context   上下文<br/>
>  @param assetName asset资源名称<br/>
>  @param destFile  目标文件<br/>
>  @return 是否拷贝成功

<br/><b>copyFile</b>(File src, File dest)

>  拷贝文件<br/>
> <br/>
>  @param src  源文件<br/>
>  @param dest 目标文件<br/>
>  @return 是否拷贝成功

<br/><b>copyFolder</b>(File src, File dest)

>  拷贝文件夹<br/>
> <br/>
>  @param src  源文件夹<br/>
>  @param dest 目标文件夹<br/>
>  @return 是否拷贝成功

<br/><b>copyRawFile</b>(Context context, int resId, File destFile)

>  根据图片的资源id，生成图片文件<br/>
> <br/>
>  @param context  上下文<br/>
>  @param resId    要打开的资源id<br/>
>  @param destFile 生成的目标文件

<br/><b>deleteFile</b>(File file)

>  循环删除文件及文件夹<br/>
> <br/>
>  @param file 文件或者文件夹

<br/><b>deleteFolder</b>(File src)

>  删除文件夹<br/>
> <br/>
>  @param src 文件夹

<br/><b>getAllIntent</b>(String path)

>  获取一个用于打开文件的intent<br/>
> <br/>
>  @param path 路径

<br/><b>getApkFileIntent</b>(String path)

>  获取一个用于打开APK文件的intent<br/>
> <br/>
>  @param path 路径

<br/><b>getAssetFileData</b>(Context context, String assetName)

>  获取asset下的资源文件数据<br/>
> <br/>
>  @param context   上下文<br/>
>  @param assetName 资源文件名

<br/><b>getExtension</b>(String fileName)

>  获取文件扩展名<br/>
> <br/>
>  @param fileName 文件名<br/>
>  @return 扩展名

<br/><b>getFileData</b>(String fileName)

>  获取文件数据<br/>
> <br/>
>  @param fileName 文件名

<br/><b>getFolderSize</b>(File folder)

>  获取文件夹大小<br/>
> <br/>
>  @param folder 文件夹

<br/><b>getParentPath</b>(String path)

>  获取当前路径path的上级目录.<br/>
> <br/>
>  @param path 当前路径<br/>
>  @return path的上级目录 e.x. hello/hello => hello/

<br/><b>getSpaceSizes</b>(String path)

>  获取可用的存储空间<br/>
> <br/>
>  @return 返回long的数组 下标0为剩余空间 下标1为已用空间 下标2为总空间

<br/><b>getString</b>(File file)

>  获取文件内容<br/>
> <br/>
>  @param file 文件<br/>
>  @return 内容

<br/><b>installFile</b>(Context context, File apk)

>  安装文件<br/>
> <br/>
>  @param context 上下文<br/>
>  @param apk     apk文件

<br/><b>isFileExists</b>(String fileName)

>  检测文件是否存在<br/>
> <br/>
>  @param fileName 文件<br/>
>  @return boolean

<br/><b>isFileLocked</b>(String file)

>  判断文件是否被锁<br/>
> <br/>
>  @param file 文件

<br/><b>openInputStream</b>(File file)

>  打开文件输入流<br/>
> <br/>
>  @param file 对应的文件<br/>
>  @return 文件对应的输出入流<br/>
>  @throws IOException 文件为目录、没有写权限、创建上级目录失败等

<br/><b>openOutputStream</b>(File file)

>  打开文件输出流<br/>
> <br/>
>  @param file 对应的文件<br/>
>  @return 文件对应的输出流<br/>
>  @throws IOException 文件为目录、没有写权限、创建上级目录失败等

<br/><b>openOutputStream</b>(File file, boolean append)

>  以追加方式打开文件输出流<br/>
> <br/>
>  @param file   对应的文件<br/>
>  @param append true：以追加方式打开，相关bytes会被追加到文件最后，false：反之<br/>
>  @return 文件对应的输出流<br/>
>  @throws IOException 文件为目录、没有写权限、创建上级目录失败等

<br/><b>permissionAll</b>(File file)

>  放开文件的读写运行权限<br/>
> <br/>
>  @param file 文件

<br/><b>readAssertFileToString</b>(Context context, String fileName)

>  读取assert文件夹中文件到字符串,使用默认编码<br/>
> <br/>
>  @param context  上下文，用户获取<code>AssetManager</code><br/>
>  @param fileName 文件名称<br/>
>  @return 内容

<br/><b>readAssertFileToString</b>(Context context, String fileName, Charset charset)

>  读取assert文件夹中文件到字符串<br/>
> <br/>
>  @param context  上下文，用户获取<code>AssetManager</code><br/>
>  @param fileName 文件名称<br/>
>  @param charset  编码格式<br/>
>  @return 内容

<br/><b>readFileToString</b>(File file)

>  从文件读取字符串,使用默认编码格式<br/>
> <br/>
>  @param file 对应文件<br/>
>  @return 文件对应的内容

<br/><b>readFileToString</b>(File file, Charset encoding)

>  从文件读取字符串<br/>
> <br/>
>  @param file     对应文件<br/>
>  @param encoding 编码格式、传null是使用<code>Charset.defaultCharset()</code><br/>
>  @return 文件对应的内容

<br/><b>readFileToString</b>(File file, String encoding)

>  从文件读取字符串<br/>
> <br/>
>  @param file     对应文件<br/>
>  @param encoding 编码格式、传null是使用<code>Charset.defaultCharset()</code><br/>
>  @return 文件对应的内容

<br/><b>rename</b>(String sourceFile, String targetFile)

>  重命名文件<br/>
> <br/>
>  @param sourceFile 源文件<br/>
>  @param targetFile 目标文件

<br/><b>writeDataToFile</b>(String fileName, byte data)

>  将byte数据保存到文件<br/>
> <br/>
>  @param fileName 文件名<br/>
>  @param data     数据<br/>
>  @return 是否写入成功

<br/><b>writeString</b>(File file, String string)

>  将字符串写入文件<br/>
> <br/>
>  @param file   文件<br/>
>  @param string 字符串<br/>
>  @return 是否成功

<br/><b>writeStringToFile</b>(File file, String data)

>  将字符串写入文件，使用系统默认编码<br/>
> <br/>
>  @param file 需要写入的文件<br/>
>  @param data 对应字符串<br/>
>  @return true:写入成功

<br/><b>writeStringToFile</b>(File file, String data, Charset encoding)

>  将字符串写入文件<br/>
> <br/>
>  @param file     需要写入的文件<br/>
>  @param data     对应字符串<br/>
>  @param encoding 字符串编码方式,传null时使用系统默认<br/>
>  @return true:写入成功

<br/><b>writeStringToFile</b>(File file, String data, Charset encoding, boolean append)

>  将字符串写入文件<br/>
> <br/>
>  @param file     需要写入的文件<br/>
>  @param data     对应字符串<br/>
>  @param encoding 字符串编码方式,传null时使用系统默认<br/>
>  @param append   true：以追加方式写入;false:覆盖<br/>
>  @return true:写入成功

<br/><b>writeStringToFile</b>(File file, String data, String encoding)

>  将字符串写入文件<br/>
> <br/>
>  @param file     需要写入的文件<br/>
>  @param data     对应字符串<br/>
>  @param encoding 字符串编码方式,传null时使用系统默认<br/>
>  @return true:写入成功

<br/><b>writeStringToFile</b>(File file, String data, String encoding, boolean append)

>  将字符串写入文件<br/>
> <br/>
>  @param file     需要写入的文件<br/>
>  @param data     对应字符串<br/>
>  @param encoding 字符串编码方式,传null时使用系统默认<br/>
>  @param append   true：以追加方式写入;false:覆盖<br/>
>  @return true:写入成功

<br/><b>writeStringToFile</b>(File file, String data, boolean append)

>  将字符串写入文件，使用系统默认编码<br/>
> <br/>
>  @param file   需要写入的文件<br/>
>  @param data   对应字符串<br/>
>  @param append true：追加方式写入<br/>
>  @return true:写入成功

<br/><b>writeStringToFile</b>(String file, String data, Charset encoding, boolean append)

>  将字符串写入文件<br/>
> <br/>
>  @param file     需要写入的文件<br/>
>  @param data     对应字符串<br/>
>  @param encoding 字符串编码方式,传null时使用系统默认<br/>
>  @param append   true：以追加方式写入;false:覆盖<br/>
>  @return true:写入成功

------
<a name="id20"></a>

### IOUtil ###

>  I/O操作工具类<br/>
> <br/>
>  流的关闭、读写、复制等

<br/><b>DEFAULT\_BUFFER\_SIZE</b> : int

<br/><b>EOF</b> : int

<br/><b>closeQuietly</b>(Closeable closeable)

>  无条件关闭可关闭对象<br/>
> <br/>
>  @param closeable 需要关闭的对象

<br/><b>copy</b>(InputStream input, OutputStream output)

>  复制输入流bytes数据到输出流<br/>
> <br/>
>  @param input  输入流<br/>
>  @param output 输出流<br/>
>  @return 返回实际复制数据长度<br/>
>  @throws IOException 出现IO异常

<br/><b>copy</b>(Reader input, Writer output)

>  复制输入chars数据到输出流<br/>
> <br/>
>  @param input  输入<br/>
>  @param output 输出<br/>
>  @return 返回实际输入长度<br/>
>  @throws IOException 出现IO异常

<br/><b>copyLarge</b>(InputStream input, OutputStream output)

>  复制bytes数据，支持超过2GB数据流 <p/> 内部创建缓冲区，因此不需要使用 <code>BufferedInputStream</code>.<br/>
> <br/>
>  @param input  输入流<br/>
>  @param output 输出流<br/>
>  @return 实际复制的数据长度<br/>
>  @throws IOException 出现IO异常

<br/><b>copyLarge</b>(InputStream input, OutputStream output, byte buffer)

>  复制bytes数据，支持超过2GB数据流 <p/> 使用提供的缓冲区，因此不需要使用 <code>BufferedInputStream</code>.<br/>
> <br/>
>  @param input  输入流<br/>
>  @param output 输出流<br/>
>  @param buffer 复制使用的缓冲区<br/>
>  @return 实际复制的数据长度<br/>
>  @throws IOException 出现IO异常

<br/><b>copyLarge</b>(Reader input, Writer output)

>  复制chars数据，支持超过2GB数据 <p/> 内部会创建缓冲区，因此不需要使用 <code>BufferedReader</code>.<br/>
> <br/>
>  @param input  输入<br/>
>  @param output 输出<br/>
>  @return 实际复制数据长度<br/>
>  @throws IOException 出现IO异常

<br/><b>copyLarge</b>(Reader input, Writer output, char buffer)

>  复制chars数据，支持超过2GB数据 <p/> 使用提供的缓冲区，因此不需要使用 <code>BufferedReader</code>.<br/>
> <br/>
>  @param input  输入<br/>
>  @param output 输出<br/>
>  @return 实际复制数据长度<br/>
>  @throws IOException 出现IO异常

<br/><b>read</b>(InputStream input)

>  输入流转bytes<br/>
> <br/>
>  @param input 输入流<br/>
>  @return 对应<code>byte[]</code><br/>
>  @throws IOException 出现IO异常

<br/><b>read</b>(Reader input)

<br/><b>readString</b>(InputStream input)

>  读取字符串<br/>
> <br/>
>  @param input 输入流<br/>
>  @return 字符串<br/>
>  @throws IOException 出现异常

<br/><b>readString</b>(InputStream input, Charset encoding)

>  读取字符串<br/>
> <br/>
>  @param input    输入流<br/>
>  @param encoding 编码方式<br/>
>  @return 字符串<br/>
>  @throws IOException 出现异常

<br/><b>readString</b>(InputStream input, String encoding)

>  读取字符串<br/>
> <br/>
>  @param input    输入流<br/>
>  @param encoding 编码方式<br/>
>  @return 字符串<br/>
>  @throws IOException 出现异常

<br/><b>readString</b>(Reader input)

>  读取字符串<br/>
> <br/>
>  @param input 输入<br/>
>  @return 字符串<br/>
>  @throws IOException 出现IO异常

<br/><b>startsWith</b>(InputStream input, byte data)

>  判断输入流是否为以指定<code>byte[]</code>开始<br/>
> <br/>
>  @param input 输入流<br/>
>  @param data  指定<code>byte[]</code><br/>
>  @return 返回true如果以指定数组开始<br/>
>  @throws IOException 出现IO异常

<br/><b>write</b>(byte data, OutputStream output)

>  将bytes数据写入对应输出流<br/>
> <br/>
>  @param data   需要写入的bytes，写入期间不要对<code>byte[]</code>做操作<br/>
>  @param output 输出流<br/>
>  @throws IOException 出现IO异常

<br/><b>writeString</b>(String data, OutputStream output)

>  将字符串写入输出流,使用默认编码方式<br/>
> <br/>
>  @param data   字符串<br/>
>  @param output 输出流<br/>
>  @throws IOException 写入IO异常

<br/><b>writeString</b>(String data, OutputStream output, Charset encoding)

>  将字符串写入输出流<br/>
> <br/>
>  @param data     字符串<br/>
>  @param output   输出流<br/>
>  @param encoding 编码方式<br/>
>  @throws IOException 写入IO异常

<br/><b>writeString</b>(String data, OutputStream output, String encoding)

>  将字符串写入输出流<br/>
> <br/>
>  @param data     字符串<br/>
>  @param output   输出流<br/>
>  @param encoding 编码方式<br/>
>  @throws IOException 写入IO异常

<br/><b>writeString</b>(String data, Writer output)

>  将字符串写入输出流<br/>
> <br/>
>  @param data   字符串<br/>
>  @param output 输出<br/>
>  @throws IOException 写入IO异常

------
<a name="id21"></a>

### ZipUtil ###

>  Zip压缩工具

<br/><b>unzipToDir</b>(File zipFile, File dir)

>  将zip文件解压到指定目录<br/>
>  @param zipFile zip文件<br/>
>  @param dir 解压目录<br/>
>  @return 是否成功

<br/><b>writeFilesToZip</b>(File zipFile, List files)

>  将文件数组写入压缩包<br/>
>  @param zipFile 压缩包文件<br/>
>  @param files 文件数组<br/>
>  @return 是否成功

------
<a name="id22"></a>

## cn.mutils.app.droid.utils.os ##

<a name="id23"></a>

### AppUtil ###

>  应用相关实用类

<br/><b>getBuildName</b>(Context context)

>  获取4位版本信息的最后一位  例如7.8.2.1005版本则返回1005<br/>
> <br/>
>  @param context 上下文<br/>
>  @return 打包build版本

<br/><b>getCacheDir</b>(Context context)

>  获取缓存目录<br/>
> <br/>
>  @param context 上下文<br/>
>  @return 缓存目录

<br/><b>getFilesDir</b>(Context context)

>  获取files目录<br/>
> <br/>
>  @param context 上下文<br/>
>  @return files目录

<br/><b>getPackageInfo</b>(Context context)

>  获取程序包信息<br/>
> <br/>
>  @param context 上下文<br/>
>  @return 程序包信息

------
<a name="id24"></a>

### SDCardUtil ###

>  SD卡辅助类

<br/><b>getSDCardPath</b>()

>  获取SD卡路径<br/>
> <br/>
>  @return 路径

<br/><b>isSDCardEnabled</b>()

>  判断SD卡是否可用<br/>
> <br/>
>  @return 是否可用

------
<a name="id25"></a>

### TaskExecutor ###

>  任务执行，支持回调

<br/><b>start</b>(TaskExecutor.Task task)

>  启动任务<br/>
> <br/>
>  @param task 任务<br/>
>  @param <T>  返回类型<br/>
>  @return 任务

<br/><b>start</b>(TaskExecutor.Task task, Executor executor)

>  使用自定义线程池启动任务<br/>
> <br/>
>  @param task     任务<br/>
>  @param executor 线程池<br/>
>  @return 任务

<br/><b>startByPool</b>(TaskExecutor.Task task)

>  通过ThreadPool封装的默认线程池启动<br/>
> <br/>
>  @param task 任务<br/>
>  @return 任务

<br/><b>startByPool</b>(TaskExecutor.Task task, ThreadPool pool)

>  通过ThreadPool线程池启动<br/>
> <br/>
>  @param task 任务<br/>
>  @param pool 线程池<br/>
>  @return 任务

<br/><b>startByPool</b>(TaskExecutor.Task task, int priority, ThreadPool pool)

>  通过ThreadPool线程池指定优先级启动<br/>
> <br/>
>  @param task     任务<br/>
>  @param priority 优先级<br/>
>  @param pool     线程池<br/>
>  @return 任务

------
<a name="id26"></a>

### TaskExecutor.Task ###

>  任务

<br/><b>STATE\_CANCELLED</b> : int

>  执行取消状态

<br/><b>STATE\_ERROR</b> : int

>  执行失败状态

<br/><b>STATE\_FINISHED</b> : int

>  执行成功状态

<br/><b>STATE\_NULL</b> : int

>  没有状态

<br/><b>STATE\_RUNNING</b> : int

>  执行状态

<br/><b>STATE\_WAITING</b> : int

>  等待执行状态

<br/><b>cancel</b>()

>  取消任务

<br/><b>doBackground</b>()

>  子线程执行回调<br/>
> <br/>
>  @return 结果

<br/><b>isCancelled</b>()

>  任务是否已经取消

<br/><b>isStarted</b>()

>  是否任务已经启动

<br/><b>isStopped</b>()

>  判断任务是否终止

<br/><b>obtainThreadContext</b>()

>  获取线程执行上下文，支持重复启动

<br/><b>onCancelled</b>()

>  任务取消回调

<br/><b>onError</b>(Throwable error)

>  执行失败回调<br/>
> <br/>
>  @param error 异常

<br/><b>onFinished</b>(Object result)

>  执行完成回调<br/>
> <br/>
>  @param result 结果

<br/><b>onStart</b>()

>  任务开始回调

------
<a name="id27"></a>

### ThreadExecutor ###

>  子线程执行工具<br/>
> <br/>
>  @author 堇花 Created by wenhua.ywh on 2016/12/14.

<br/><b>post</b>(Runnable action)

>  post到子线程<br/>
> <br/>
>  @param action 回调

<br/><b>postDelayed</b>(Runnable action, long delayMills)

>  post到子线程延时处理<br/>
> <br/>
>  @param action     回调<br/>
>  @param delayMills 延时

<br/><b>removeCallbacks</b>(Runnable action)

>  移除回调<br/>
> <br/>
>  @param action 回调

------
<a name="id28"></a>

### ThreadPool ###

>  线程池

<br/><b>HIGH\_PRIORITY</b> : int

>  高优先级

<br/><b>LOW\_PRIORITY</b> : int

>  低优先级

<br/><b>NORMAL\_PRIORITY</b> : int

>  默认优先级

<br/><b>defaultPool</b>()

>  获取默认线程池<br/>
> <br/>
>  @return 默认线程池

<br/><b>execute</b>(Runnable command)

>  执行任务<br/>
> <br/>
>  @param command 任务

<br/><b>execute</b>(Runnable command, int priority)

>  根据优先级执行任务<br/>
> <br/>
>  @param command  任务<br/>
>  @param priority 优先级

<br/><b>getPoolSize</b>()

>  获取线程池运行并行线程数目

<br/><b>isBusy</b>()

>  线程池是否满负荷运行

<br/><b>setPoolSize</b>(int poolSize)

>  设置线程池并行线程数目

<br/><b>shutdown</b>()

>  停止线程池

------
<a name="id29"></a>

### UiExecutor ###

>  主线程执行工具<br/>
> <br/>
>  @author 堇花 Created by wenhua.ywh on 2016/12/14.

<br/><b>post</b>(Runnable action)

>  post到主线程<br/>
> <br/>
>  @param action 回调

<br/><b>postDelayed</b>(Runnable action, long delayMills)

>  post到主线程延时处理<br/>
> <br/>
>  解耦<code>TaskManager#postDelayed(Runnable,long)</code><br/>
> <br/>
>  @param action     回调<br/>
>  @param delayMills 延时

<br/><b>removeCallbacks</b>(Runnable action)

>  删除回调<br/>
> <br/>
>  @param action 回调

------
<a name="id30"></a>

## cn.mutils.app.droid.utils.runtime ##

<a name="id31"></a>

### ReflectUtil ###

>  反射工具

<br/><b>getDeclaredField</b>(Class c, String fieldName)

>  根据成员变量名字获取成员变量反射描述<br/>
> <br/>
>  @param c         类<br/>
>  @param fieldName 成员变量描述

<br/><b>getDeclaredMethod</b>(Class c, String methodName, Class argTypes)

>  根据成员变量名字获取成员变量反射描述<br/>
> <br/>
>  @param c          类<br/>
>  @param methodName 成员变量描述

<br/><b>getFieldValue</b>(Class clazz, String fieldName)

>  获取静态成员变量的值<br/>
> <br/>
>  @param clazz     类<br/>
>  @param fieldName 静态成员变量名称<br/>
>  @return 值

<br/><b>getFieldValue</b>(Object obj, String fieldName)

>  获取对象成员变量的值<br/>
> <br/>
>  @param obj       对象<br/>
>  @param fieldName 成员变量名称<br/>
>  @return 值

<br/><b>invokeMethod</b>(Class clazz, Object object, String methodName, Class argTypes, Object args)

>  动态调用成员方法<br/>
> <br/>
>  @param clazz      类<br/>
>  @param object     对象<br/>
>  @param methodName 方法名称<br/>
>  @param argTypes   方法参数类型<br/>
>  @param args       方法参数<br/>
>  @return 返回值

<br/><b>invokeMethod</b>(Class clazz, String methodName, Class argTypes, Object args)

>  动态调用静态方法<br/>
> <br/>
>  @param clazz      类<br/>
>  @param methodName 方法名称<br/>
>  @param argTypes   方法参数类型<br/>
>  @param args       方法参数<br/>
>  @return 返回值

<br/><b>invokeMethod</b>(Object object, String methodName, Class argTypes, Object args)

>  动态调用对象方法<br/>
> <br/>
>  @param object     对象<br/>
>  @param methodName 方法名称<br/>
>  @param argTypes   方法参数类型<br/>
>  @param args       方法参数<br/>
>  @return 返回值

<br/><b>setFieldValue</b>(Class clazz, String fieldName, Object value)

>  设置静态成员变量的值<br/>
> <br/>
>  @param clazz     类<br/>
>  @param fieldName 静态成员变量名称<br/>
>  @param value     值

<br/><b>setFieldValue</b>(Object obj, String fieldName, Object value)

>  设置对象成员变量的值<br/>
> <br/>
>  @param obj       对象<br/>
>  @param fieldName 成员变量名称<br/>
>  @param value     值

------
<a name="id32"></a>

### StackTraceUtil ###

>  堆栈实用类<br/>
> <br/>
>  Thread.currentThread().getStackTrace() 多端不一致<br><br/>
>  <p><br/>
>  Android environment stack trace:<br><br/>
>  dalvik.system.VMStack.getThreadStackTrace<br><br/>
>  java.lang.Thread.getStackTrace<br><br/>
>  xxx.StackTraceUtil.getCallerElement<br><br/>
>  ... ....<br><br/>
>  <p><br/>
>  <p><br/>
>  Java standard environment stack trace:<br><br/>
>  java.lang.Thread.getStackTrace<br><br/>
>  xxx.StackTraceUtil.getCallerElement<br><br/>
>  ... ...

<br/><b>getCallerElement</b>()

>  获取调用当前方法的方法对应的堆栈

<br/><b>getCurrentElement</b>()

>  获取当前方法对应堆栈信息

<br/><b>printStackTrace</b>(Throwable e)

>  打印堆栈信息<br/>
>  @param e 异常<br/>
>  @return 堆栈信息

------
<a name="id33"></a>

## cn.mutils.app.droid.utils.sort ##

<a name="id34"></a>

### SortUtil ###

>  排序实用类

<br/><b>sortByLength</b>(List list)

>  根据文件大小进行升序排序<br/>
> <br/>
>  @param list 文件列表<br/>
>  @return 排序完成的列表

<br/><b>sortByLength</b>(List list, boolean asc)

>  根据文件大小进行升序排序<br/>
> <br/>
>  @param list 文件列表<br/>
>  @param asc  是否是升序排序<br/>
>  @return 排序完成的列表

<br/><b>sortByModified</b>(List list)

>  根据文件修改时间进行升序排序<br/>
> <br/>
>  @param list 文件列表<br/>
>  @return 排序完成的列表

<br/><b>sortByModified</b>(List list, boolean asc)

>  根据文件修改时间进行排序<br/>
> <br/>
>  @param list 文件列表<br/>
>  @param asc  是否是升序排序<br/>
>  @return 排序完成的列表

------
<a name="id35"></a>

## cn.mutils.app.droid.utils.time ##

<a name="id36"></a>

### CalendarUtil ###

>  日期工具类

<br/><b>TEMPLATE\_ALL\_12</b> : String

>  12小时格式化

<br/><b>TEMPLATE\_ALL\_24</b> : String

>  24小时格式化

<br/><b>TEMPLATE\_DAY</b> : String

>  精确到天格式化

<br/><b>format</b>(Date date, String template)

>  格式化<br/>
> <br/>
>  @param date     日期<br/>
>  @param template 模板格式<br/>
>  @return 格式化输出

<br/><b>format</b>(long time, String template)

>  格式化<br/>
> <br/>
>  @param time     时间<br/>
>  @param template 模板代码<br/>
>  @return 格式化输出

<br/><b>getDayOfMonth</b>(Date date)

>  获取当前月份中的日期<br/>
> <br/>
>  @param date 时间<br/>
>  @return 日期

<br/><b>getDayOfWeek</b>(Date date)

>  获取星期对应数字<br/>
> <br/>
>  @param date 时间<br/>
>  @return 星期一返回1 星期二返回2 ... 星期日返回7

<br/><b>getDayOfWeek</b>(Date date, int defSunday)

>  获取星期对应数字<br/>
> <br/>
>  @param date      时间<br/>
>  @param defSunday 星期日数字<br/>
>  @return 星期一返回1 星期二返回2 ...

<br/><b>getDays</b>(Date start, Date end)

>  获取两个时间之间的天数<br/>
> <br/>
>  @param start 开始时间<br/>
>  @param end   结束时间<br/>
>  @return 天数

<br/><b>getMonth</b>(Date date)

>  获取当前月份 一月返回1<br/>
> <br/>
>  @param date 时间<br/>
>  @return 月份

------
<a name="id37"></a>

## cn.mutils.app.droid.utils.ui ##

<a name="id38"></a>

### NoDBClickUtil ###

>  避免快速双击实用类

<br/><b>DOUBLE\_CLICK\_SLOP</b> : int

<br/><b>setOnClickListener</b>(View view, View.OnClickListener listener)

>  设置点击监听<br/>
>  @param view 视图<br/>
>  @param listener 监听

<br/><b>setOnItemClickListener</b>(<any> adapterView, AdapterView.OnItemClickListener listener)

>  设置子项点击监听<br/>
>  @param adapterView 视图<br/>
>  @param listener 监听

------