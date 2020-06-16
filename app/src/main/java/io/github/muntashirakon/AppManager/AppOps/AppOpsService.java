package io.github.muntashirakon.AppManager.AppOps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.Parcelable;

import com.android.internal.app.IAppOpsService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Reimplementation of IAppOpsService, every methods reroute to their respective methods
 */
public class AppOpsService {
    @SuppressLint("StaticFieldLeak")
    private static AppOpsService appOpsService = null;
    public static AppOpsService getInstance(Context context) throws Exception {
        if (appOpsService == null) {
            appOpsService = new AppOpsService(context.getApplicationContext());
        }
        return appOpsService;
    }

    Context context;
    IAppOpsService iAppOpsService;

    private AppOpsService() {}

    private AppOpsService(Context ctx) throws Exception {
        context = ctx;
        if (context.checkCallingOrSelfPermission("android.permission.GET_APP_OPS_STATS") == PackageManager.PERMISSION_GRANTED) {
            init();
        } else {
            // TODO: Ask for permissions
            throw new Exception("Permission not granted.");
        }
    }

    private void init() throws Exception {
        try {
            // Invoke function android.os.ServiceManager.getService(Context.APP_OPS_SERVICE)
            @SuppressLint("PrivateApi")
            @SuppressWarnings("rawtypes")
            Class localClass = Class.forName("android.os.ServiceManager");
            @SuppressWarnings("unchecked")
            Method getService = localClass.getMethod("getService", String.class);
            Object result = getService.invoke(localClass, Context.APP_OPS_SERVICE);
            if(result != null) {
                iAppOpsService = IAppOpsService.Stub.asInterface((IBinder) result);
                if (iAppOpsService == null) {
                    throw new Exception("AppOpsService cannot be null");
                }
            }
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException
                | InvocationTargetException | PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int checkOperation(int code, int uid, String packageName) {
        return iAppOpsService.checkOperation(code, uid, packageName);
    }

    public int noteOperation(int code, int uid, String packageName) {
        return iAppOpsService.noteOperation(code, uid, packageName);
    }

    public int permissionToOpCode(String permission) {
        return iAppOpsService.permissionToOpCode(permission);
    }

    // Remaining methods are only used in Java.
    public int checkPackage(int uid, String packageName) {
        return iAppOpsService.checkPackage(uid, packageName);
    }

    @NonNull
    public List<PackageOps> getPackagesForOps(@Nullable int[] ops) {
        List<Parcelable> opsForPackage = iAppOpsService.getPackagesForOps(ops);
        List<PackageOps> packageOpsList = new ArrayList<>();
        if (opsForPackage != null) {
            for (Object o : opsForPackage) {
                PackageOps packageOps = ReflectUtils.opsConvert(o);
                packageOpsList.add(packageOps);
            }
        }
        return packageOpsList;
    }

    @NonNull
    public List<PackageOps> getOpsForPackage(int uid, @NonNull String packageName, @Nullable int[] ops) {
        List<Parcelable> parcelablePackageOps = iAppOpsService.getOpsForPackage(uid, packageName, ops);
        List<PackageOps> packageOpsList = new ArrayList<>();
        if (parcelablePackageOps != null) {
            // Convert parcelable to PackageOps
            for (Parcelable o : parcelablePackageOps) {
                PackageOps packageOps = ReflectUtils.opsConvert(o);
                packageOpsList.add(packageOps);
            }
        } else {
            // Add an empty array
            PackageOps packageOps = new PackageOps(packageName, uid, new ArrayList<>());
            packageOpsList.add(packageOps);
        }
        return packageOpsList;
    }

    public void setUidMode(int code, int uid, int mode) {
        iAppOpsService.setUidMode(code, uid, mode);
    }

    public void setMode(int code, int uid, String packageName, int mode) {
        iAppOpsService.setMode(code, uid, packageName, mode);
    }

    public void resetAllModes(int reqUserId, String reqPackageName) {
        iAppOpsService.resetAllModes(reqUserId, reqPackageName);
    }
}

//// Test Begins //
////  adb shell pm grant io.github.muntashirakon.AppManager android.permission.GET_APP_OPS_STATS
//        try {
//                AppOpsService appOpsService = AppOpsService.getInstance(this);
//                String packageName = "com.pitchedapps.frost";
//                int myUid = Process.myUid()/100000;
////            ApplicationInfo ai = getPackageManager().getApplicationInfo(packageName, 0);
//                int uid = getPackageUid(packageName, myUid);
//                Log.d("TestAPI", "Flag=" + (getPackageManager().checkPermission(Manifest.permission.INTERNET, packageName) == PackageManager.PERMISSION_GRANTED));
////            Log.d("TestAPI", "UID=" + ai.uid + ", " + uid);
////            AppOpsManager appOpsManager = (AppOpsManager) getSystemService(APP_OPS_SERVICE);
//                Log.d("TestAPI", "Mode: " + appOpsService.checkOperation(OP_GPS, uid, packageName));
//
//                List<PackageOps> packageOpsList = appOpsService.getOpsForPackage(uid, packageName, null);
//        for(PackageOps packageOps: packageOpsList) {
//        List<OpEntry> opEntries = packageOps.getOps();
//        for(OpEntry opEntry: opEntries) {
//        OpEntryReadable opEntryReadable = new OpEntryReadable(opEntry, getPackageManager());
//        Log.d("TestAPI", opEntryReadable.toString());
//        }
//        }
////            Log.d("TestAPI", "PermissionToOpcode" + appOpsService.permissionToOpCode(Manifest.permission.INTERNET));
//        } catch (Exception e) {
//        e.printStackTrace();
//        }
//// Test Ends //

