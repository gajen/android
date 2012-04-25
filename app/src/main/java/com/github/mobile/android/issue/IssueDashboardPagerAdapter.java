package com.github.mobile.android.issue;

import static com.github.mobile.android.ui.issue.DashboardIssueFragment.ARG_FILTER;
import static org.eclipse.egit.github.core.service.IssueService.DIRECTION_DESCENDING;
import static org.eclipse.egit.github.core.service.IssueService.FIELD_DIRECTION;
import static org.eclipse.egit.github.core.service.IssueService.FIELD_FILTER;
import static org.eclipse.egit.github.core.service.IssueService.FIELD_SORT;
import static org.eclipse.egit.github.core.service.IssueService.FILTER_ASSIGNED;
import static org.eclipse.egit.github.core.service.IssueService.FILTER_CREATED;
import static org.eclipse.egit.github.core.service.IssueService.FILTER_MENTIONED;
import static org.eclipse.egit.github.core.service.IssueService.FILTER_SUBSCRIBED;
import static org.eclipse.egit.github.core.service.IssueService.SORT_UPDATED;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.github.mobile.android.R.string;
import com.github.mobile.android.ui.issue.DashboardIssueFragment;
import com.viewpagerindicator.TitleProvider;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Pager adapter for the issues dashboard
 */
public class IssueDashboardPagerAdapter extends FragmentPagerAdapter implements TitleProvider {

    private final Resources resources;

    /**
     * Create pager adapter
     *
     * @param resources
     * @param fragmentManager
     */
    public IssueDashboardPagerAdapter(Resources resources, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.resources = resources;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int position) {
        String filter = null;
        switch (position) {
        case 0:
            filter = FILTER_SUBSCRIBED;
            break;
        case 1:
            filter = FILTER_ASSIGNED;
            break;
        case 2:
            filter = FILTER_CREATED;
            break;
        case 3:
            filter = FILTER_MENTIONED;
            break;
        default:
            return null;
        }
        final Map<String, String> filterData = new HashMap<String, String>();
        filterData.put(FIELD_FILTER, filter);
        filterData.put(FIELD_SORT, SORT_UPDATED);
        filterData.put(FIELD_DIRECTION, DIRECTION_DESCENDING);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_FILTER, (Serializable) filterData);
        DashboardIssueFragment fragment = new DashboardIssueFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public String getTitle(int position) {
        switch (position) {
        case 0:
            return resources.getString(string.dasbhoard_watched);
        case 1:
            return resources.getString(string.dasbhoard_assigned);
        case 2:
            return resources.getString(string.dasbhoard_created);
        case 3:
            return resources.getString(string.dasbhoard_mentioned);
        default:
            return null;
        }
    }
}
