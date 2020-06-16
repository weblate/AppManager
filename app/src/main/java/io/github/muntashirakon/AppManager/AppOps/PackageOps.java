/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.muntashirakon.AppManager.AppOps;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * Class holding all of the operation information associated with an app.
 */
@SuppressWarnings("unused")
public class PackageOps implements Parcelable {
    private final String mPackageName;
    private final int mUid;
    private final List<OpEntry> mEntries;

    public PackageOps(String packageName, int uid, List<OpEntry> entries) {
        mPackageName = packageName;
        mUid = uid;
        mEntries = entries;
    }

    /**
     * @return The name of the package.
     */
    public String getPackageName() {
        return mPackageName;
    }

    /**
     * @return The uid of the package.
     */
    public int getUid() {
        return mUid;
    }

    /**
     * @return The ops of the package.
     */
    public List<OpEntry> getOps() {
        return mEntries;
    }

    @NonNull
    @Override
    public String toString() {
        return "PackageOps{" +
                "mPackageName='" + mPackageName + '\'' +
                ", mUid=" + mUid +
                ", mEntries=" + mEntries +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(mPackageName);
        dest.writeInt(mUid);
        dest.writeInt(mEntries.size());
        for (int i=0; i<mEntries.size(); i++) {
            mEntries.get(i).writeToParcel(dest, flags);
        }
    }

    PackageOps(@NonNull Parcel source) {
        mPackageName = source.readString();
        mUid = source.readInt();
        mEntries = new ArrayList<>();
        final int N = source.readInt();
        for (int i=0; i<N; i++) {
            mEntries.add(OpEntry.CREATOR.createFromParcel(source));
        }
    }

    public static final @NonNull Creator<PackageOps> CREATOR = new Creator<PackageOps>() {
        @Override
        public PackageOps createFromParcel(Parcel source) {
            return new PackageOps(source);
        }
        @Override
        public PackageOps[] newArray(int size) {
            return new PackageOps[size];
        }
    };
}