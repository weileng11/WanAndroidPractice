package com.bruce.sx.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.Uri
import android.text.format.Time
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast

import java.io.*
import java.net.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

object StaticUtils {

    /**
     * 获取SDK版本
     */
    val sdkVersion: Int
        get() {
            var version = 0
            try {
                version = Integer.valueOf(android.os.Build.VERSION.SDK)
            } catch (e: NumberFormatException) {
            }

            return version
        }

    /**
     * 获取当前时间
     *
     * @return 时间字符串 24小时制
     * @author drowtram
     */
    val currentTime: Long
        get() = System.currentTimeMillis()

    /**
     * 获取当前日期，包含星期几
     *
     * @return 日期字符串 xx月xx号 星期x
     * @author drowtram
     */
    // 获取当前月份
    // 获取当前月份的日期号码
    val stringData: String
        get() {
            val c = Calendar.getInstance()
            c.timeZone = TimeZone.getTimeZone("GMT+8:00")
            val mMonth = (c.get(Calendar.MONTH) + 1).toString()
            val mDay = c.get(Calendar.DAY_OF_MONTH).toString()
            var mWay = c.get(Calendar.DAY_OF_WEEK).toString()
            if ("1" == mWay) {
                mWay = "日"
            } else if ("2" == mWay) {
                mWay = "一"
            } else if ("3" == mWay) {
                mWay = "二"
            } else if ("4" == mWay) {
                mWay = "三"
            } else if ("5" == mWay) {
                mWay = "四"
            } else if ("6" == mWay) {
                mWay = "五"
            } else if ("7" == mWay) {
                mWay = "六"
            }
            return mMonth + "月" + mDay + "日\n" + "星期" + mWay
        }


    private val DEF_ZH_PATTERN = "[\u4e00-\u9fa5]+"

    /**
     * @brief TAG
     */
    private val TAG = "Utils"

    /**
     * 随机生成uuid
     */
    fun GetRandomUUID(): String {
        val s = UUID.randomUUID().toString()
        //去掉“-”符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(
            19,
            23
        ) + s.substring(24)
    }

    /**
     * 判断当前Context是否合法
     */
    fun isValidContext(c: Context): Boolean {
        return if (c is Activity) {

            if (c.isFinishing) {
                false
            } else {
                true
            }
        } else true
    }

    /**
     * 最省内存的方式读取本地图片sp
     *
     * @param context
     * @param resId
     * @return
     */
    fun readBitMap(context: Context, resId: Int): Bitmap? {
        val opt = BitmapFactory.Options()
        opt.inPreferredConfig = Bitmap.Config.RGB_565
        opt.inPurgeable = true
        opt.inInputShareable = true
        // 获取资源图片
        val `is` = context.resources.openRawResource(resId)
        return BitmapFactory.decodeStream(`is`, null, opt)
    }


    /**
     * 获得网络连接是否可用
     *
     * @param context
     * @return
     */
    fun hasNetwork(context: Context): Boolean {
        val con = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val workinfo = con.activeNetworkInfo
        return if (workinfo == null || !workinfo.isAvailable) {
            //showNetDialog(context);
            false
        } else true
    }

    /**
     * 安装一个apk文件
     *
     * @param file
     */
    fun installApk(file: File, context: Context) {
        val intent = Intent()
        intent.action = "android.intent.action.VIEW"
        intent.addCategory("android.intent.category.DEFAULT")
        intent.setDataAndType(
            Uri.fromFile(file),
            "application/vnd.android.package-archive"
        )
        context.startActivity(intent)
    }

    /**
     * 获取当前应用版本号
     *
     * @return
     */
    fun getVersion(context: Context): String {
        val pm = context.packageManager
        try {
            val info = pm.getPackageInfo(context.packageName, 0)
            return info.versionName
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }

    }


    /**
     * 获取当前时间
     *
     * @return 时间字符串 24小时制
     * @author drowtram
     */
    fun getStringTime(type: String): String {
        val t = Time()
        t.setToNow() // 取得系统时间。
        val hour = if (t.hour < 10) "0" + t.hour else t.hour.toString() + "" // 默认24小时制
        val minute = if (t.minute < 10) "0" + t.minute else t.minute.toString() + ""
        return hour + type + minute
    }


    /**
     * 转换播放时间
     *
     * @param milliseconds 传入毫秒值
     * @return 返回 hh:mm:ss或mm:ss格式的数据
     */
    fun getShowTime(milliseconds: Long): String {
        // 获取日历函数
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliseconds
        var dateFormat: SimpleDateFormat? = null
        // 判断是否大于60分钟，如果大于就显示小时。设置日期格式
        if (milliseconds / 60000 > 60) {
            dateFormat = SimpleDateFormat("hh:mm:ss")
        } else {
            dateFormat = SimpleDateFormat("mm:ss")
        }
        return dateFormat.format(calendar.time)
    }

    /**************************回看工具函数 */


    /**
     * @param strLink 链接地址
     * @return false     该地址非空
     * @brief 判断该链接是否为空
     * @author joychang
     */
    fun isValidLink(strLink: String?): Boolean {
        Log.d("UiUtil", "isValidLink() start.")
        var result = false
        if (strLink != null && strLink.length > 0) {
            val url: URL
            try {
                url = URL(strLink)
                val connt = url.openConnection() as HttpURLConnection
                connt.connectTimeout = 5 * 1000
                connt.requestMethod = "HEAD"
                val code = connt.responseCode
                if (code == 404) {
                    result = true
                }
                connt.disconnect()
            } catch (e: Exception) {
                result = true
            }

        } else {
            result = true
        }
        Log.d("UiUtil", "isValidLink() end.")
        return result
    }


    /**
     * @return UTF8形式字符串。
     * @throws UnsupportedEncodingException 不支持的字符集
     * @brief 中文字符串转换函数。
     * @author joychang
     * @param[in] str 要转换的字符串。
     * @param[in] charset 字符串编码。
     * @note 将str中的中文字符转换为UTF8编码形式。
     */
    @Throws(UnsupportedEncodingException::class)
    fun encode(str: String?, charset: String?): String? {
        Log.d(TAG, "_encode() start")

        var result: String? = null

        if (str != null && charset != null) {
            try {
                val p = Pattern.compile(DEF_ZH_PATTERN, 0)
                val m = p.matcher(str)

                val b = StringBuffer()
                while (m.find()) {
                    m.appendReplacement(b, URLEncoder.encode(m.group(0), charset))
                }

                m.appendTail(b)

                result = b.toString()
            } catch (e: PatternSyntaxException) {
                e.printStackTrace()
            }

        } else {
            if (str == null) {
                Log.e(TAG, "encode(): str is null")
            }

            if (charset == null) {
                Log.e(TAG, "encode(): charset is null")
            }
        }

        Log.d(TAG, "encode() end")

        return result
    }


    /**
     * 根据日期获取对应的星期
     *
     * @param mdate
     * @return 星期
     */
    fun getWeekToDate(mdate: String): String? {
        var mdate = mdate
        var week: String? = null
        mdate = mdate.replace("/", "-")
        //    	mdate = mdate.replace("月", "-");
        //    	mdate = mdate.replace("日", "");
        //    	mdate = "2014-"+mdate;
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        try {
            val date = sdf.parse(mdate)
            week = getWeek(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return week
    }

    fun getWeek(date: Date): String {
        val sdf = SimpleDateFormat("EEEE")
        return sdf.format(date)
    }


    /**
     * 从Assets目录下拷贝文件到指定目录
     *
     * @param context  上下文对象
     * @param fileName Assets目录下的指定文件名
     * @param path     要拷贝到的目录
     * @return true 拷贝成功  false 拷贝失败
     * @author drowtram
     */
//        fun copyApkFromAssets(context: Context, fileName: String, path: String): Boolean {
//        var copyIsFinish = false
//        try {
//            val f = File(path)
//            if (f.exists()) {
//                f.delete() //如果存在这个文件，则删除重新拷贝
//            }
//            val iss = context.assets.open(fileName)
//            val file = File(path)
//            file.createNewFile()
//            val fos = FileOutputStream(file)
//            val temp = ByteArray(1024)
//            var i = 0
//            while ((i = iss.read(temp)) > 0) {
//                fos.write(temp, 0, i)
//            }
//            fos.close()
//            iss.close()
//            copyIsFinish = true
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//        return copyIsFinish
//    }

    /**
     * 获取一个路径的文件名
     *
     * @param urlpath
     * @return
     */
    fun getFilename(urlpath: String): String {
        return urlpath
            .substring(urlpath.lastIndexOf("/") + 1, urlpath.length)
    }


    /***************************点播工具 */


    /**
     * 筛选条件编码
     *
     * @param filter
     * @return
     */
    fun getEcodString(filter: String): String {
        var s = ""
        try {
            s = URLEncoder.encode(filter, "utf-8")
        } catch (e1: UnsupportedEncodingException) {
            e1.printStackTrace()
        }

        return s
    }

    /**
     * 删除指定目录下所有apk文件
     *
     * @param dir
     * @author drowtram
     */
    fun deleteAppApks(dir: String) {
        val file = File(dir)
        try {
            if (file.exists() && file.isDirectory) {
                val files = file.listFiles()
                for (f in files!!) {
                    val fileName = f.name
                    if (f.isFile && fileName.endsWith(".apk")) {
                        if (f.delete()) {
                            Log.d("zhouchuan", "delete the $fileName")
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    /**
     * 安装apk文件
     *
     * @param fileName
     * @author drowtram
     */
    fun installApk(context: Context, fileName: String) {
        if (getUninatllApkInfo(context, fileName)) {
            val updateFile = File(fileName)
            try {
                val args2 = arrayOf("chmod", "604", updateFile.path)
                Runtime.getRuntime().exec(args2)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            /*------------------------*/
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(
                Uri.fromFile(updateFile),
                "application/vnd.android.package-archive"
            )
            context.startActivity(intent)
            //			File file = new File(fileName);
            //			Intent intent = new Intent();
            //			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //			intent.setAction(Intent.ACTION_VIEW);     //浏览网页的Action(动作)
            //			String type = "application/vnd.android.package-archive";
            //			intent.setDataAndType(Uri.fromFile(file), type);  //设置数据类型
            //			context.startActivity(intent);
        } else {
            Toast.makeText(context, "文件还没下载完成，请耐心等待。", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 判断apk文件是否可以安装
     *
     * @param context
     * @param filePath
     * @return
     */
    fun getUninatllApkInfo(context: Context, filePath: String): Boolean {
        var result = false
        try {
            val pm = context.packageManager
            val info = pm.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES)
            if (info != null) {
                result = true
            }
        } catch (e: Exception) {
            result = false
            Log.e("zhouchuan", "*****  解析未安装的 apk 出现异常 *****" + e.message, e)
        }

        return result
    }

    /**
     * 获取IP
     *
     * @return
     */
    fun localipget(): String? {
        try {
            val en = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val intf = en.nextElement()
                val enumIpAddr = intf.inetAddresses
                while (enumIpAddr.hasMoreElements()) {
                    val inetAddress = enumIpAddr.nextElement()
                    if (!inetAddress.isLoopbackAddress && !inetAddress.isLinkLocalAddress) {
                        return inetAddress.hostAddress.toString()
                    }
                }
            }
        } catch (e: SocketException) {
        }

        return null
    }

    /**
     * 根据apk路径获取包名
     *
     * @param context
     * @param strFile apk路径
     * @return apk包名
     */
    fun getPackageName(context: Context, strFile: String): String? {
        val pm = context.packageManager
        val packageInfo = pm.getPackageArchiveInfo(strFile, PackageManager.GET_ACTIVITIES)
        var mPackageName: String? = null
        if (packageInfo != null) {
            val applicationInfo = packageInfo.applicationInfo
            mPackageName = applicationInfo.packageName
        }
        return mPackageName
    }

    /**
     * 获取包名
     *
     * @param context
     * @return
     */
    fun getAppInfo(context: Context): String? {
        try {
            return context.packageName
        } catch (e: Exception) {
        }

        return null
    }


    /**
     * 根据包名获取apk名称
     *
     * @param context
     * @param packageName 包名
     * @return apk名称
     */
    fun getApkName(context: Context, packageName: String): String? {
        var apkName: String? = null
        val pm = context.packageManager
        try {
            val packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            if (packageInfo != null) {
                val applicationInfo = packageInfo.applicationInfo
                apkName = pm.getApplicationLabel(applicationInfo).toString()
                // int lable = applicationInfo.labelRes;
                // apkName = context.getResources().getString(lable);
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return apkName
    }

    /**
     * 根据apk文件获取app应用名称
     *
     * @param context
     * @param apkFilePath apk文件路径
     * @return
     */
    fun getAppNameByApkFile(context: Context, apkFilePath: String): String? {
        var apkName: String? = null
        val pm = context.packageManager
        val packageInfo = pm.getPackageArchiveInfo(apkFilePath, PackageManager.GET_ACTIVITIES)
        if (packageInfo != null) {
            apkName = pm.getApplicationLabel(packageInfo.applicationInfo).toString()
        }
        return apkName
    }

    fun getOwnCacheDirectory(context: Context, path: String): File {
        val filesDir = context.externalCacheDir
        val cacheFile = File(filesDir, path)
        if (!cacheFile.exists()) {
            Log.i(TAG, "getOwnCacheDirectory: 创建缓存目录")
            cacheFile.mkdir()
        }
        return cacheFile

    }

    /**
     * 获取屏幕的宽度 像素
     *
     * @param context
     * @return
     */
    fun getDisplayMetricsWidth(context: Context): Int {
        val manager = context
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        manager.defaultDisplay.getMetrics(dm)
        manager.defaultDisplay.width
        return dm.widthPixels
    }

    /**
     * 获取屏幕的高度,像素
     *
     * @param context
     * @return
     */
    fun getDisplayMetricsHeight(context: Context): Int {
        val manager = context
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        manager.defaultDisplay.getMetrics(dm)
        return dm.heightPixels
    }

    /**
     * 获取屏幕的高度,像素
     *
     * @param context
     * @return
     */
    fun getDisplay(context: Context): Float {
        val manager = context
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        manager.defaultDisplay.getMetrics(dm)
        return dm.density
    }


    fun setDefaultfocus(view: View) {
        view.isFocusable = true
        view.isFocusableInTouchMode = true
        view.requestFocus()
        view.requestFocusFromTouch()
    }
}
