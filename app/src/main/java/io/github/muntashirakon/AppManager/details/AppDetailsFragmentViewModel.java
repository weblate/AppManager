/*
 * Copyright (C) 2020 Muntashir Al-Islam
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.muntashirakon.AppManager.details;

import androidx.lifecycle.ViewModel;
import io.github.muntashirakon.AppManager.details.AppDetailsFragment;

public class AppDetailsFragmentViewModel extends ViewModel {
    private @AppDetailsFragment.Property int neededProperty = 0;  // ACTIVITY
    private @AppDetailsFragment.SortOrder int sortBy = 0;  // SORT_BY_NAME

    public int getNeededProperty() {
        return neededProperty;
    }
    public void setNeededProperty(int neededProperty) {
        this.neededProperty = neededProperty;
    }
    public int getSortBy() {
        return sortBy;
    }
    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }
}
