package io.github.muntashirakon.AppManager.AppOps;

import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.github.muntashirakon.AppManager.utils.Utils;

/**
 * Class holding the information about one unique operation of an application.
 *
 * This class is similar to {@link OpEntry} but it's more readable
 */
public class OpEntryReadable {
    /**
     * Original {@link OpEntry} object
     */
    public OpEntry opEntry;
    /**
     * Name of the Op (not to be confused with permission name, ie. {@link #opPermName})
     */
    public String opName;
    /**
     * Permission name for the Op (if available)
     */
    public @Nullable String opPermName;
    /**
     * Permission label for the Op (if available)
     */
    public @Nullable CharSequence opPermLabel = null;
    /**
     * Permission description for the Op (if available)
     */
    public @Nullable CharSequence opPermDescription = null;
    /**
     * Permission package name (if available)
     */
    public @Nullable String opPermPackageName = null;
    /**
     * Permission group name (if available)
     */
    public @Nullable String opPermGroupName = null;
    /**
     * Permission protection level (if available)
     */
    public @Nullable String opPermProtectionLevel = null;
    /**
     * App Op mode
     */
    public @AppOpsManager.Mode int mode;

    public OpEntryReadable(OpEntry opEntry, PackageManager pm) {
        if (opEntry != null) {
            this.opEntry = opEntry;
            this.opName = AppOpsManager.sOpNames[opEntry.getOp()];
            this.opPermName = AppOpsManager.sOpPerms[opEntry.getOp()];
            this.mode = opEntry.getMode();
            if (opPermName != null) {
                try {
                    PermissionInfo permissionInfo = pm.getPermissionInfo(this.opPermName, 0);
                    this.opPermLabel = permissionInfo.loadLabel(pm);
                    this.opPermDescription = permissionInfo.loadDescription(pm);
                    this.opPermPackageName = permissionInfo.packageName;
                    this.opPermGroupName = permissionInfo.group;
                    this.opPermProtectionLevel = Utils.getProtectionLevelString(permissionInfo.protectionLevel);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isAllowOrForeground() {
        return this.mode == AppOpsManager.MODE_ALLOWED || this.mode == AppOpsManager.MODE_FOREGROUND;
    }

    @NonNull
    @Override
    public String toString() {
        return "OpEntryReadable{" +
                "opName='" + opName + '\'' +
                ", opPermName='" + opPermName + '\'' +
                ", opPermLabel='" + opPermLabel + '\'' +
                ", mode=" + mode +
                '}';
    }
}
