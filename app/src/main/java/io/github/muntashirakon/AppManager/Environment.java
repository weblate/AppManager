package io.github.muntashirakon.AppManager;

// Keep this in sync with https://cs.android.com/android/platform/superproject/+/master:frameworks/base/core/java/android/os/Environment.java
// Last snapshot https://cs.android.com/android/_/android/platform/frameworks/base/+/bc3d8b9071d4f0b2903d6836770d974e70366290

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Provides access to environment variables.
 */
@SuppressWarnings("unused")
public class Environment {
    private static final String TAG = "Environment";

    // NOTE: keep credential-protected paths in sync with StrictMode.java

    private static final String ENV_EXTERNAL_STORAGE = "EXTERNAL_STORAGE";
    private static final String ENV_ANDROID_ROOT = "ANDROID_ROOT";
    private static final String ENV_ANDROID_DATA = "ANDROID_DATA";
    private static final String ENV_ANDROID_EXPAND = "ANDROID_EXPAND";
    private static final String ENV_ANDROID_STORAGE = "ANDROID_STORAGE";
    private static final String ENV_DOWNLOAD_CACHE = "DOWNLOAD_CACHE";
    private static final String ENV_OEM_ROOT = "OEM_ROOT";
    private static final String ENV_ODM_ROOT = "ODM_ROOT";
    private static final String ENV_VENDOR_ROOT = "VENDOR_ROOT";
    private static final String ENV_PRODUCT_ROOT = "PRODUCT_ROOT";
    private static final String ENV_SYSTEM_EXT_ROOT = "SYSTEM_EXT_ROOT";
    private static final String ENV_APEX_ROOT = "APEX_ROOT";

    private static final String DIR_ANDROID = "Android";
    private static final String DIR_DATA = "data";
    private static final String DIR_MEDIA = "media";
    private static final String DIR_OBB = "obb";
    private static final String DIR_FILES = "files";
    private static final String DIR_CACHE = "cache";

    private static final File DIR_ANDROID_ROOT = getDirectory(ENV_ANDROID_ROOT, "/system");
    private static final File DIR_ANDROID_DATA = getDirectory(ENV_ANDROID_DATA, "/data");
    private static final File DIR_ANDROID_EXPAND = getDirectory(ENV_ANDROID_EXPAND, "/mnt/expand");
    private static final File DIR_ANDROID_STORAGE = getDirectory(ENV_ANDROID_STORAGE, "/storage");
    private static final File DIR_DOWNLOAD_CACHE = getDirectory(ENV_DOWNLOAD_CACHE, "/cache");
    private static final File DIR_OEM_ROOT = getDirectory(ENV_OEM_ROOT, "/oem");
    private static final File DIR_ODM_ROOT = getDirectory(ENV_ODM_ROOT, "/odm");
    private static final File DIR_VENDOR_ROOT = getDirectory(ENV_VENDOR_ROOT, "/vendor");
    private static final File DIR_PRODUCT_ROOT = getDirectory(ENV_PRODUCT_ROOT, "/product");
    private static final File DIR_SYSTEM_EXT_ROOT = getDirectory(ENV_SYSTEM_EXT_ROOT,
            "/system_ext");
    private static final File DIR_APEX_ROOT = getDirectory(ENV_APEX_ROOT,
            "/apex");

    private static UserEnvironment sCurrentUser;
    private static boolean sUserRequired;

    static {
        initForCurrentUser();
    }

    public static void initForCurrentUser() {
        int userId = 0;
        try {
            Method myUserId = UserHandle.class.getMethod("myUserId");
            userId = (int) myUserId.invoke(null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        sCurrentUser = new UserEnvironment(userId);
    }

    public static class UserEnvironment {
        private final int mUserId;

        public UserEnvironment(int userId) {
            mUserId = userId;
        }

        public File[] getExternalDirs() {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                try {
                    Method getVolumeList = StorageManager.class.getMethod("getVolumeList", Integer.class, Integer.class);
                    final @NonNull StorageVolume[] volumes = (StorageVolume[]) Objects.requireNonNull(getVolumeList.invoke(null, mUserId, 1 << 8 /* FLAG_FOR_WRITE */));
                    final File[] files = new File[volumes.length];
                    for (int i = 0; i < volumes.length; i++) {
                        Method getPathFile = StorageVolume.class.getMethod("getPathFile");
                        files[i] = (File) getPathFile.invoke(volumes[i]);
                    }
                    return files;
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            String rawExternalStorage = System.getenv(ENV_EXTERNAL_STORAGE);
            String rawEmulatedTarget = System.getenv("EMULATED_STORAGE_TARGET");
            String rawMediaStorage = System.getenv("MEDIA_STORAGE");
            if (TextUtils.isEmpty(rawMediaStorage)) {
                rawMediaStorage = "/data/media";
            }
            List<File> externalForApp = new ArrayList<>();
            if (!TextUtils.isEmpty(rawEmulatedTarget)) {
                // Device has emulated storage; external storage paths should have
                // userId burned into them.
                final String rawUserId = Integer.toString(mUserId);
                final File emulatedTargetBase = new File(rawEmulatedTarget);
                final File mediaBase = new File(rawMediaStorage);

                // /storage/emulated/0
                externalForApp.add(buildPath(emulatedTargetBase, rawUserId));
            } else {
                // Device has physical external storage; use plain paths.
                if (TextUtils.isEmpty(rawExternalStorage)) {
                    Log.w(TAG, "EXTERNAL_STORAGE undefined; falling back to default");
                    rawExternalStorage = "/storage/sdcard0";
                }
                // /storage/sdcard0
                externalForApp.add(new File(rawExternalStorage));
            }
            return externalForApp.toArray(new File[0]);
        }

        @Deprecated
        public File getExternalStorageDirectory() {
            return getExternalDirs()[0];
        }

        @Deprecated
        public File getExternalStoragePublicDirectory(String type) {
            return buildExternalStoragePublicDirs(type)[0];
        }

        public File[] buildExternalStoragePublicDirs(String type) {
            return buildPaths(getExternalDirs(), type);
        }

        public File[] buildExternalStorageAndroidDataDirs() {
            return buildPaths(getExternalDirs(), DIR_ANDROID, DIR_DATA);
        }

        public File[] buildExternalStorageAndroidObbDirs() {
            return buildPaths(getExternalDirs(), DIR_ANDROID, DIR_OBB);
        }

        public File[] buildExternalStorageAppDataDirs(String packageName) {
            return buildPaths(getExternalDirs(), DIR_ANDROID, DIR_DATA, packageName);
        }

        public File[] buildExternalStorageAppMediaDirs(String packageName) {
            return buildPaths(getExternalDirs(), DIR_ANDROID, DIR_MEDIA, packageName);
        }

        public File[] buildExternalStorageAppObbDirs(String packageName) {
            return buildPaths(getExternalDirs(), DIR_ANDROID, DIR_OBB, packageName);
        }

        public File[] buildExternalStorageAppFilesDirs(String packageName) {
            return buildPaths(getExternalDirs(), DIR_ANDROID, DIR_DATA, packageName, DIR_FILES);
        }

        public File[] buildExternalStorageAppCacheDirs(String packageName) {
            return buildPaths(getExternalDirs(), DIR_ANDROID, DIR_DATA, packageName, DIR_CACHE);
        }
    }

    /**
     * Return root of the "system" partition holding the core Android OS.
     * Always present and mounted read-only.
     */
    public static @NonNull File getRootDirectory() {
        return DIR_ANDROID_ROOT;
    }

    public static @NonNull File getStorageDirectory() {
        return DIR_ANDROID_STORAGE;
    }

    /**
     * Return root directory of the "oem" partition holding OEM customizations,
     * if any. If present, the partition is mounted read-only.
     */
    public static @NonNull File getOemDirectory() {
        return DIR_OEM_ROOT;
    }

    /**
     * Return root directory of the "odm" partition holding ODM customizations,
     * if any. If present, the partition is mounted read-only.
     */
    public static @NonNull File getOdmDirectory() {
        return DIR_ODM_ROOT;
    }

    /**
     * Return root directory of the "vendor" partition that holds vendor-provided
     * software that should persist across simple reflashing of the "system" partition.
     */
    public static @NonNull File getVendorDirectory() {
        return DIR_VENDOR_ROOT;
    }

    /**
     * Return root directory of the "product" partition holding product-specific
     * customizations if any. If present, the partition is mounted read-only.
     */
    public static @NonNull File getProductDirectory() {
        return DIR_PRODUCT_ROOT;
    }

    /**
     * Return root directory of the "product_services" partition holding middleware
     * services if any. If present, the partition is mounted read-only.
     *
     * @deprecated This directory is not guaranteed to exist.
     *             Its name is changed to "system_ext" because the partition's purpose is changed.
     *             {@link #getSystemExtDirectory()}
     */
    @Deprecated
    public static @NonNull File getProductServicesDirectory() {
        return getDirectory("PRODUCT_SERVICES_ROOT", "/product_services");
    }

    /**
     * Return root directory of the "system_ext" partition holding system partition's extension
     * If present, the partition is mounted read-only.
     */
    public static @NonNull File getSystemExtDirectory() {
        return DIR_SYSTEM_EXT_ROOT;
    }

    /**
     * Return root directory of the apex mount point, where all the apex modules are made available
     * to the rest of the system.
     */
    public static @NonNull File getApexDirectory() {
        return DIR_APEX_ROOT;
    }

    /**
     * Return the system directory for a user. This is for use by system
     * services to store files relating to the user. This directory will be
     * automatically deleted when the user is removed.
     *
     * @deprecated This directory is valid and still exists, but but callers
     *             should <em>strongly</em> consider switching to using either
     *             {@link #getDataSystemCeDirectory(int)} or
     *             {@link #getDataSystemDeDirectory(int)}, both of which support
     *             fast user wipe.
     */
    @Deprecated
    public static File getUserSystemDirectory(int userId) {
        return new File(new File(getDataSystemDirectory(), "users"), Integer.toString(userId));
    }

    /**
     * Returns the config directory for a user. This is for use by system
     * services to store files relating to the user which should be readable by
     * any app running as that user.
     *
     * @deprecated This directory is valid and still exists, but callers should
     *             <em>strongly</em> consider switching to
     *             {@link #getDataMiscCeDirectory(int)} which is protected with
     *             user credentials or {@link #getDataMiscDeDirectory(int)}
     *             which supports fast user wipe.
     */
    @Deprecated
    public static File getUserConfigDirectory(int userId) {
        return new File(new File(new File(
                getDataDirectory(), "misc"), "user"), Integer.toString(userId));
    }

    /**
     * Return the user data directory.
     */
    public static File getDataDirectory() {
        return DIR_ANDROID_DATA;
    }

    public static File getDataDirectory(String volumeUuid) {
        if (TextUtils.isEmpty(volumeUuid)) {
            return DIR_ANDROID_DATA;
        } else {
            return new File("/mnt/expand/" + volumeUuid);
        }
    }

    public static File getExpandDirectory() {
        return DIR_ANDROID_EXPAND;
    }

    public static File getDataSystemDirectory() {
        return new File(getDataDirectory(), "system");
    }

    /**
     * Returns the base directory for per-user system directory, device encrypted.
     */
    public static File getDataSystemDeDirectory() {
        return buildPath(getDataDirectory(), "system_de");
    }

    /**
     * Returns the base directory for per-user system directory, credential encrypted.
     */
    public static File getDataSystemCeDirectory() {
        return buildPath(getDataDirectory(), "system_ce");
    }

    /**
     * Return the "credential encrypted" system directory for a user. This is
     * for use by system services to store files relating to the user. This
     * directory supports fast user wipe, and will be automatically deleted when
     * the user is removed.
     * <p>
     * Data stored under this path is "credential encrypted", which uses an
     * encryption key that is entangled with user credentials, such as a PIN or
     * password. The contents will only be available once the user has been
     * unlocked, as reported by {@code SystemService.onUnlockUser()}.
     * <p>
     * New code should <em>strongly</em> prefer storing sensitive data in these
     * credential encrypted areas.
     */
    public static File getDataSystemCeDirectory(int userId) {
        return buildPath(getDataDirectory(), "system_ce", String.valueOf(userId));
    }

    /**
     * Return the "device encrypted" system directory for a user. This is for
     * use by system services to store files relating to the user. This
     * directory supports fast user wipe, and will be automatically deleted when
     * the user is removed.
     * <p>
     * Data stored under this path is "device encrypted", which uses an
     * encryption key that is tied to the physical device. The contents will
     * only be available once the device has finished a {@code dm-verity}
     * protected boot.
     * <p>
     * New code should <em>strongly</em> avoid storing sensitive data in these
     * device encrypted areas.
     */
    public static File getDataSystemDeDirectory(int userId) {
        return buildPath(getDataDirectory(), "system_de", String.valueOf(userId));
    }

    public static File getDataMiscDirectory() {
        return new File(getDataDirectory(), "misc");
    }

    public static File getDataMiscCeDirectory() {
        return buildPath(getDataDirectory(), "misc_ce");
    }

    public static File getDataMiscCeDirectory(int userId) {
        return buildPath(getDataDirectory(), "misc_ce", String.valueOf(userId));
    }

    public static File getDataMiscDeDirectory(int userId) {
        return buildPath(getDataDirectory(), "misc_de", String.valueOf(userId));
    }

    private static File getDataProfilesDeDirectory(int userId) {
        return buildPath(getDataDirectory(), "misc", "profiles", "cur", String.valueOf(userId));
    }

    public static File getDataVendorCeDirectory(int userId) {
        return buildPath(getDataDirectory(), "vendor_ce", String.valueOf(userId));
    }

    public static File getDataVendorDeDirectory(int userId) {
        return buildPath(getDataDirectory(), "vendor_de", String.valueOf(userId));
    }

    public static File getDataRefProfilesDePackageDirectory(String packageName) {
        return buildPath(getDataDirectory(), "misc", "profiles", "ref", packageName);
    }

    public static File getDataProfilesDePackageDirectory(int userId, String packageName) {
        return buildPath(getDataProfilesDeDirectory(userId), packageName);
    }

    public static File getDataAppDirectory(String volumeUuid) {
        return new File(getDataDirectory(volumeUuid), "app");
    }

    public static File getDataStagingDirectory(String volumeUuid) {
        return new File(getDataDirectory(volumeUuid), "app-staging");
    }

    public static File getDataUserCeDirectory(String volumeUuid) {
        return new File(getDataDirectory(volumeUuid), "user");
    }

    public static File getDataUserCeDirectory(String volumeUuid, int userId) {
        return new File(getDataUserCeDirectory(volumeUuid), String.valueOf(userId));
    }

    public static File getDataUserCePackageDirectory(String volumeUuid, int userId,
                                                     String packageName) {
        // TODO: keep consistent with installd
        return new File(getDataUserCeDirectory(volumeUuid, userId), packageName);
    }

    public static File getDataUserDeDirectory(String volumeUuid) {
        return new File(getDataDirectory(volumeUuid), "user_de");
    }

    public static File getDataUserDeDirectory(String volumeUuid, int userId) {
        return new File(getDataUserDeDirectory(volumeUuid), String.valueOf(userId));
    }

    public static File getDataUserDePackageDirectory(String volumeUuid, int userId,
                                                     String packageName) {
        // TODO: keep consistent with installd
        return new File(getDataUserDeDirectory(volumeUuid, userId), packageName);
    }

    /**
     * Return preloads directory.
     * <p>This directory may contain pre-loaded content such as
     * {@link #getDataPreloadsDemoDirectory() demo videos} and
     * {@link #getDataPreloadsAppsDirectory() APK files} .
     */
    public static File getDataPreloadsDirectory() {
        return new File(getDataDirectory(), "preloads");
    }

    /**
     * @see #getDataPreloadsDirectory()
     */
    public static File getDataPreloadsDemoDirectory() {
        return new File(getDataPreloadsDirectory(), "demo");
    }

    /**
     * @see #getDataPreloadsDirectory()
     */
    public static File getDataPreloadsAppsDirectory() {
        return new File(getDataPreloadsDirectory(), "apps");
    }

    /**
     * @see #getDataPreloadsDirectory()
     */
    public static File getDataPreloadsMediaDirectory() {
        return new File(getDataPreloadsDirectory(), "media");
    }

    /**
     * Returns location of preloaded cache directory for package name
     * @see #getDataPreloadsDirectory()
     */
    public static File getDataPreloadsFileCacheDirectory(String packageName) {
        return new File(getDataPreloadsFileCacheDirectory(), packageName);
    }

    /**
     * Returns location of preloaded cache directory.
     * @see #getDataPreloadsDirectory()
     */
    public static File getDataPreloadsFileCacheDirectory() {
        return new File(getDataPreloadsDirectory(), "file_cache");
    }

    /**
     * Returns location of packages cache directory.
     */
    public static File getPackageCacheDirectory() {
        return new File(getDataSystemDirectory(), "package_cache");
    }

    /**
     * Return the primary shared/external storage directory. This directory may
     * not currently be accessible if it has been mounted by the user on their
     * computer, has been removed from the device, or some other problem has
     * happened. You can determine its current state with
     * getExternalStorageState().
     * <p>
     * <em>Note: don't be confused by the word "external" here. This directory
     * can better be thought as media/shared storage. It is a filesystem that
     * can hold a relatively large amount of data and that is shared across all
     * applications (does not enforce permissions). Traditionally this is an SD
     * card, but it may also be implemented as built-in storage in a device that
     * is distinct from the protected internal storage and can be mounted as a
     * filesystem on a computer.</em>
     * <p>
     * On devices with multiple users (as described by UserManager},
     * each user has their own isolated shared storage. Applications only have
     * access to the shared storage for the user they're running as.
     * <p>
     * In devices with multiple shared/external storage directories, this
     * directory represents the primary storage that the user will interact
     * with. Access to secondary storage is available through
     * {@link Context#getExternalFilesDirs(String)},
     * {@link Context#getExternalCacheDirs()}, and
     * {@link Context#getExternalMediaDirs()}.
     * <p>
     * Applications should not directly use this top-level directory, in order
     * to avoid polluting the user's root namespace. Any files that are private
     * to the application should be placed in a directory returned by
     * {@link android.content.Context#getExternalFilesDir
     * Context.getExternalFilesDir}, which the system will take care of deleting
     * if the application is uninstalled. Other shared files should be placed in
     * one of the directories returned by
     * {@link #getExternalStoragePublicDirectory}.
     * <p>
     * Writing to this path requires the
     * {@link android.Manifest.permission#WRITE_EXTERNAL_STORAGE} permission,
     * and starting in {@link android.os.Build.VERSION_CODES#KITKAT}, read
     * access requires the
     * {@link android.Manifest.permission#READ_EXTERNAL_STORAGE} permission,
     * which is automatically granted if you hold the write permission.
     * <p>
     * Starting in {@link android.os.Build.VERSION_CODES#KITKAT}, if your
     * application only needs to store internal data, consider using
     * {@link Context#getExternalFilesDir(String)},
     * {@link Context#getExternalCacheDir()}, or
     * {@link Context#getExternalMediaDirs()}, which require no permissions to
     * read or write.
     * <p>
     * This path may change between platform versions, so applications should
     * only persist relative paths.
     * <p>
     * Here is an example of typical code to monitor the state of external
     * storage:
     * <p>
     * {@sample development/samples/ApiDemos/src/com/example/android/apis/content/ExternalStorage.java
     * monitor_storage}
     *
     * @deprecated To improve user privacy, direct access to shared/external
     *             storage devices is deprecated. When an app targets
     *             {@link android.os.Build.VERSION_CODES#Q}, the path returned
     *             from this method is no longer directly accessible to apps.
     *             Apps can continue to access content stored on shared/external
     *             storage by migrating to alternatives such as
     *             {@link Context#getExternalFilesDir(String)},
     *             {@link MediaStore}, or {@link Intent#ACTION_OPEN_DOCUMENT}.
     */
    @Deprecated
    public static File getExternalStorageDirectory() {
        throwIfUserRequired();
        return sCurrentUser.getExternalDirs()[0];
    }

    public static File getLegacyExternalStorageDirectory() {
        return new File(System.getenv(ENV_EXTERNAL_STORAGE));
    }

    public static File getLegacyExternalStorageObbDirectory() {
        return buildPath(getLegacyExternalStorageDirectory(), DIR_ANDROID, DIR_OBB);
    }

    /**
     * Standard directory in which to place any audio files that should be
     * in the regular list of music for the user.
     * This may be combined with
     * {@link #DIRECTORY_PODCASTS}, {@link #DIRECTORY_NOTIFICATIONS},
     * {@link #DIRECTORY_ALARMS}, and {@link #DIRECTORY_RINGTONES} as a series
     * of directories to categories a particular audio file as more than one
     * type.
     */
    public static String DIRECTORY_MUSIC = "Music";

    /**
     * Standard directory in which to place any audio files that should be
     * in the list of podcasts that the user can select (not as regular
     * music).
     * This may be combined with {@link #DIRECTORY_MUSIC},
     * {@link #DIRECTORY_NOTIFICATIONS},
     * {@link #DIRECTORY_ALARMS}, and {@link #DIRECTORY_RINGTONES} as a series
     * of directories to categories a particular audio file as more than one
     * type.
     */
    public static String DIRECTORY_PODCASTS = "Podcasts";

    /**
     * Standard directory in which to place any audio files that should be
     * in the list of ringtones that the user can select (not as regular
     * music).
     * This may be combined with {@link #DIRECTORY_MUSIC},
     * {@link #DIRECTORY_PODCASTS}, {@link #DIRECTORY_NOTIFICATIONS}, and
     * {@link #DIRECTORY_ALARMS} as a series
     * of directories to categories a particular audio file as more than one
     * type.
     */
    public static String DIRECTORY_RINGTONES = "Ringtones";

    /**
     * Standard directory in which to place any audio files that should be
     * in the list of alarms that the user can select (not as regular
     * music).
     * This may be combined with {@link #DIRECTORY_MUSIC},
     * {@link #DIRECTORY_PODCASTS}, {@link #DIRECTORY_NOTIFICATIONS},
     * and {@link #DIRECTORY_RINGTONES} as a series
     * of directories to categories a particular audio file as more than one
     * type.
     */
    public static String DIRECTORY_ALARMS = "Alarms";

    /**
     * Standard directory in which to place any audio files that should be
     * in the list of notifications that the user can select (not as regular
     * music).
     * This may be combined with {@link #DIRECTORY_MUSIC},
     * {@link #DIRECTORY_PODCASTS},
     * {@link #DIRECTORY_ALARMS}, and {@link #DIRECTORY_RINGTONES} as a series
     * of directories to categories a particular audio file as more than one
     * type.
     */
    public static String DIRECTORY_NOTIFICATIONS = "Notifications";

    /**
     * Standard directory in which to place pictures that are available to
     * the user.  Note that this is primarily a convention for the top-level
     * public directory, as the media scanner will find and collect pictures
     * in any directory.
     */
    public static String DIRECTORY_PICTURES = "Pictures";

    /**
     * Standard directory in which to place movies that are available to
     * the user.  Note that this is primarily a convention for the top-level
     * public directory, as the media scanner will find and collect movies
     * in any directory.
     */
    public static String DIRECTORY_MOVIES = "Movies";

    /**
     * Standard directory in which to place files that have been downloaded by
     * the user.  Note that this is primarily a convention for the top-level
     * public directory, you are free to download files anywhere in your own
     * private directories.  Also note that though the constant here is
     * named DIRECTORY_DOWNLOADS (plural), the actual file name is non-plural for
     * backwards compatibility reasons.
     */
    public static String DIRECTORY_DOWNLOADS = "Download";

    /**
     * The traditional location for pictures and videos when mounting the
     * device as a camera.  Note that this is primarily a convention for the
     * top-level public directory, as this convention makes no sense elsewhere.
     */
    public static String DIRECTORY_DCIM = "DCIM";

    /**
     * Standard directory in which to place documents that have been created by
     * the user.
     */
    public static String DIRECTORY_DOCUMENTS = "Documents";

    /**
     * Standard directory in which to place screenshots that have been taken by
     * the user. Typically used as a secondary directory under
     * {@link #DIRECTORY_PICTURES}.
     */
    public static String DIRECTORY_SCREENSHOTS = "Screenshots";

    /**
     * Standard directory in which to place any audio files which are
     * audiobooks.
     */
    public static String DIRECTORY_AUDIOBOOKS = "Audiobooks";

    /**
     * List of standard storage directories.
     * <p>
     * Each of its values have its own constant:
     * <ul>
     *   <li>{@link #DIRECTORY_MUSIC}
     *   <li>{@link #DIRECTORY_PODCASTS}
     *   <li>{@link #DIRECTORY_ALARMS}
     *   <li>{@link #DIRECTORY_RINGTONES}
     *   <li>{@link #DIRECTORY_NOTIFICATIONS}
     *   <li>{@link #DIRECTORY_PICTURES}
     *   <li>{@link #DIRECTORY_MOVIES}
     *   <li>{@link #DIRECTORY_DOWNLOADS}
     *   <li>{@link #DIRECTORY_DCIM}
     *   <li>{@link #DIRECTORY_DOCUMENTS}
     *   <li>{@link #DIRECTORY_AUDIOBOOKS}
     * </ul>
     */
    public static final String[] STANDARD_DIRECTORIES = {
            DIRECTORY_MUSIC,
            DIRECTORY_PODCASTS,
            DIRECTORY_RINGTONES,
            DIRECTORY_ALARMS,
            DIRECTORY_NOTIFICATIONS,
            DIRECTORY_PICTURES,
            DIRECTORY_MOVIES,
            DIRECTORY_DOWNLOADS,
            DIRECTORY_DCIM,
            DIRECTORY_DOCUMENTS,
            DIRECTORY_AUDIOBOOKS,
    };

    public static boolean isStandardDirectory(String dir) {
        for (String valid : STANDARD_DIRECTORIES) {
            if (valid.equals(dir)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("PointlessBitwiseExpression")
    public static final int HAS_MUSIC = 1 << 0;
    public static final int HAS_PODCASTS = 1 << 1;
    public static final int HAS_RINGTONES = 1 << 2;
    public static final int HAS_ALARMS = 1 << 3;
    public static final int HAS_NOTIFICATIONS = 1 << 4;
    public static final int HAS_PICTURES = 1 << 5;
    public static final int HAS_MOVIES = 1 << 6;
    public static final int HAS_DOWNLOADS = 1 << 7;
    public static final int HAS_DCIM = 1 << 8;
    public static final int HAS_DOCUMENTS = 1 << 9;
    public static final int HAS_AUDIOBOOKS = 1 << 10;

    public static final int HAS_ANDROID = 1 << 16;
    public static final int HAS_OTHER = 1 << 17;

    /**
     * Classify the content types present on the given external storage device.
     * <p>
     * This is typically useful for deciding if an inserted SD card is empty, or
     * if it contains content like photos that should be preserved.
     */
    public static int classifyExternalStorageDirectory(File dir) {
        int res = 0;
        for (File f : FileUtils_listFilesOrEmpty(dir)) {
            if (f.isFile() && isInterestingFile(f)) {
                res |= HAS_OTHER;
            } else if (f.isDirectory() && hasInterestingFiles(f)) {
                final String name = f.getName();
                if (DIRECTORY_MUSIC.equals(name)) res |= HAS_MUSIC;
                else if (DIRECTORY_PODCASTS.equals(name)) res |= HAS_PODCASTS;
                else if (DIRECTORY_RINGTONES.equals(name)) res |= HAS_RINGTONES;
                else if (DIRECTORY_ALARMS.equals(name)) res |= HAS_ALARMS;
                else if (DIRECTORY_NOTIFICATIONS.equals(name)) res |= HAS_NOTIFICATIONS;
                else if (DIRECTORY_PICTURES.equals(name)) res |= HAS_PICTURES;
                else if (DIRECTORY_MOVIES.equals(name)) res |= HAS_MOVIES;
                else if (DIRECTORY_DOWNLOADS.equals(name)) res |= HAS_DOWNLOADS;
                else if (DIRECTORY_DCIM.equals(name)) res |= HAS_DCIM;
                else if (DIRECTORY_DOCUMENTS.equals(name)) res |= HAS_DOCUMENTS;
                else if (DIRECTORY_AUDIOBOOKS.equals(name)) res |= HAS_AUDIOBOOKS;
                else if (DIR_ANDROID.equals(name)) res |= HAS_ANDROID;
                else res |= HAS_OTHER;
            }
        }
        return res;
    }

    private static boolean hasInterestingFiles(File dir) {
        final LinkedList<File> explore = new LinkedList<>();
        explore.add(dir);
        while (!explore.isEmpty()) {
            dir = explore.pop();
            for (File f : FileUtils_listFilesOrEmpty(dir)) {
                if (isInterestingFile(f)) return true;
                if (f.isDirectory()) explore.add(f);
            }
        }
        return false;
    }

    // Taken from FileUtils
    private static @NonNull File[] FileUtils_listFilesOrEmpty(@Nullable File dir) {
        if (dir == null) return new File[0];
        File[] files = dir.listFiles();
        return (files != null) ? files : new File[0];
    }

    private static boolean isInterestingFile(File file) {
        if (file.isFile()) {
            final String name = file.getName().toLowerCase();
            if (name.endsWith(".exe") || name.equals("autorun.inf")
                    || name.equals("launchpad.zip") || name.equals(".nomedia")) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Get a top-level shared/external storage directory for placing files of a
     * particular type. This is where the user will typically place and manage
     * their own files, so you should be careful about what you put here to
     * ensure you don't erase their files or get in the way of their own
     * organization.
     * <p>
     * On devices with multiple users (as described by UserManager),
     * each user has their own isolated shared storage. Applications only have
     * access to the shared storage for the user they're running as.
     * </p>
     * <p>
     * Here is an example of typical code to manipulate a picture on the public
     * shared storage:
     * </p>
     * {@sample development/samples/ApiDemos/src/com/example/android/apis/content/ExternalStorage.java
     * public_picture}
     *
     * @param type The type of storage directory to return. Should be one of
     *            {@link #DIRECTORY_MUSIC}, {@link #DIRECTORY_PODCASTS},
     *            {@link #DIRECTORY_RINGTONES}, {@link #DIRECTORY_ALARMS},
     *            {@link #DIRECTORY_NOTIFICATIONS}, {@link #DIRECTORY_PICTURES},
     *            {@link #DIRECTORY_MOVIES}, {@link #DIRECTORY_DOWNLOADS},
     *            {@link #DIRECTORY_DCIM}, or {@link #DIRECTORY_DOCUMENTS}. May not be null.
     * @return Returns the File path for the directory. Note that this directory
     *         may not yet exist, so you must make sure it exists before using
     *         it such as with {@link File#mkdirs File.mkdirs()}.
     * @deprecated To improve user privacy, direct access to shared/external
     *             storage devices is deprecated. When an app targets
     *             {@link android.os.Build.VERSION_CODES#Q}, the path returned
     *             from this method is no longer directly accessible to apps.
     *             Apps can continue to access content stored on shared/external
     *             storage by migrating to alternatives such as
     *             {@link Context#getExternalFilesDir(String)},
     *             {@link MediaStore}, or {@link Intent#ACTION_OPEN_DOCUMENT}.
     */
    @Deprecated
    public static File getExternalStoragePublicDirectory(String type) {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStoragePublicDirs(type)[0];
    }

    /**
     * Returns the path for android-specific data on the SD card.
     */
    public static File[] buildExternalStorageAndroidDataDirs() {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStorageAndroidDataDirs();
    }

    /**
     * Generates the raw path to an application's data
     */
    public static File[] buildExternalStorageAppDataDirs(String packageName) {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStorageAppDataDirs(packageName);
    }

    /**
     * Generates the raw path to an application's media
     */
    public static File[] buildExternalStorageAppMediaDirs(String packageName) {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStorageAppMediaDirs(packageName);
    }

    /**
     * Generates the raw path to an application's OBB files
     */
    public static File[] buildExternalStorageAppObbDirs(String packageName) {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStorageAppObbDirs(packageName);
    }

    /**
     * Generates the path to an application's files.
     */
    public static File[] buildExternalStorageAppFilesDirs(String packageName) {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStorageAppFilesDirs(packageName);
    }

    /**
     * Generates the path to an application's cache.
     */
    public static File[] buildExternalStorageAppCacheDirs(String packageName) {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStorageAppCacheDirs(packageName);
    }

    public static File[] buildExternalStoragePublicDirs(@NonNull String dirType) {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStoragePublicDirs(dirType);
    }

    /**
     * Return the download/cache content directory.
     */
    public static File getDownloadCacheDirectory() {
        return DIR_DOWNLOAD_CACHE;
    }

    static File getDirectory(String variableName, String defaultPath) {
        String path = System.getenv(variableName);
        return path == null ? new File(defaultPath) : new File(path);
    }

    public static void setUserRequired(boolean userRequired) {
        sUserRequired = userRequired;
    }

    private static void throwIfUserRequired() {
        if (sUserRequired) {
            Log.wtf(TAG, "Path requests must specify a user by using UserEnvironment",
                    new Throwable());
        }
    }

    /**
     * Append path segments to each given base path, returning result.
     */
    @NonNull
    public static File[] buildPaths(@NonNull File[] base, String... segments) {
        File[] result = new File[base.length];
        for (int i = 0; i < base.length; i++) {
            result[i] = buildPath(base[i], segments);
        }
        return result;
    }

    /**
     * Append path segments to given base path, returning result.
     */
    public static File buildPath(File base, @NonNull String... segments) {
        File cur = base;
        for (String segment : segments) {
            if (cur == null) {
                cur = new File(segment);
            } else {
                cur = new File(cur, segment);
            }
        }
        return cur;
    }
}