package com.bruce.projectkt.url;

import rxhttp.wrapper.annotation.DefaultDomain;
import rxhttp.wrapper.annotation.Domain;

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.projectkt.url
 * @description:
 * @date: 2020/4/7
 * @time: 18:29
 */
public class ApiConstants {

    @Domain(name = "Update")
    public static String update = "http://update.9158.com";

    @DefaultDomain //设置为默认域名
    public static String baseUrl = "https://www.wanandroid.com/";
}
