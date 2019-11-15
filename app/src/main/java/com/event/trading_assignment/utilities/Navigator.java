package com.event.trading_assignment.utilities;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/***/
public class Navigator {

    @NonNull
    protected final FragmentManager mFragmentManager;
    @IdRes
    protected final int mDefaultContainer;

    private GetFragNameCallback getFragNameCallback;

    /**
     * This constructor should be only called once per
     *
     * @param fragmentManager  Your FragmentManger
     * @param defaultContainer Your container id where the fragments should be placed
     */
    public Navigator(@NonNull final FragmentManager fragmentManager, @IdRes final int defaultContainer) {
        mFragmentManager = fragmentManager;
        mDefaultContainer = defaultContainer;

        mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (getFragNameCallback != null)
                    getFragNameCallback.getFragName(mFragmentManager.getClass().getName());
            }
        });

    }

    public void setFragmentChangeListener(GetFragNameCallback getFragNameCallback) {
        this.getFragNameCallback = getFragNameCallback;
    }

    /**
     * @return the current active fragment. If no fragment is active it return null.
     */
    public Fragment getActiveFragment() {
        if (mFragmentManager.getBackStackEntryCount() == 0) {
            return null;
        }
        String tag = mFragmentManager
                .getBackStackEntryAt(mFragmentManager.getBackStackEntryCount() - 1).getName();
        return mFragmentManager.findFragmentByTag(tag);
    }

    /**
     * Pushes the fragment, and add it to the history (BackStack)
     *
     * @param fragment the fragment which
     */
    public void goTo(final Fragment fragment) {
        mFragmentManager.beginTransaction()
                .addToBackStack(getName(fragment))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(mDefaultContainer, fragment, getName(fragment))
                .commit();
        /*mFragmentManager.executePendingTransactions();*/
    }

    /**
     * This is just a helper method which returns the simple name of
     * the fragment.
     *
     * @param fragment that get added to the history (BackStack)
     * @return the simple name of the given fragment
     */
    protected String getName(final Fragment fragment) {
        return fragment.getClass().getSimpleName();
    }

    /**
     * Set the new root fragment. If there is any entry in the history (BackStack)
     * it will be deleted.
     *
     * @param startFragment the new root fragment
     */
    public void setRootFragment(final Fragment startFragment) {
        if (getSize() > 0) {
            this.clearHistory();
        }
        this.replaceFragment(startFragment);
    }

    /**
     * Replace the current fragment with the given one, without to add it to backstack.
     * So when the users navigates away from the given fragment it will not appaer in
     * the history.
     *
     * @param fragment the new fragment
     */
    public void replaceFragment(final Fragment fragment) {
        mFragmentManager.beginTransaction()
                .replace(mDefaultContainer, fragment, getName(fragment))
                .commit();
        /*mFragmentManager.executePendingTransactions();*/
    }

    /**
     * Goes one entry back in the history
     */
    public void goOneBack() {
        mFragmentManager.popBackStackImmediate();
    }

    //    DMRR
    public void goTwoBack() {
        mFragmentManager.popBackStackImmediate();
        mFragmentManager.popBackStackImmediate();
    }

    /**
     * @return The current size of the history (BackStack)
     */
    public int getSize() {
        return mFragmentManager.getBackStackEntryCount();
    }

    /**
     * @return True if no Fragment is in the History (BackStack)
     */
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * Goes the whole history back until to the first fragment in the history.
     * It would be the same if the user would click so many times the back button until
     * he reach the first fragment of the app.
     */
    public void gotToTheRootFragmentBack() {
        for (int i = 0; i <= mFragmentManager.getBackStackEntryCount(); ++i) {
            goOneBack();
        }
    }

    /**
     * Clears the whole history so it will no BackStack entry there any more.
     */
    public void clearHistory() {
        //noinspection StatementWithEmptyBody - it works as wanted
        while (mFragmentManager.popBackStackImmediate()) ;
    }

    public void clearSingleHistory() {
        mFragmentManager.popBackStackImmediate();
    }


    public interface GetFragNameCallback {
        void getFragName(String fragment);
    }
}
