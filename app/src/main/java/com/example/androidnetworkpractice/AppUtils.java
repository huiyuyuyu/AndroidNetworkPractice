package com.example.androidnetworkpractice;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * 功能描述
 *
 * @author Kobe Teacher
 * @create 2020-10-16 23:37
 **/
public class AppUtils {
    // 缺点：查找速度太慢
    public static boolean isApplicationAvilible(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        if (installedPackages != null) {
            for (PackageInfo packageInfo : installedPackages) {
                if (packageInfo.packageName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
