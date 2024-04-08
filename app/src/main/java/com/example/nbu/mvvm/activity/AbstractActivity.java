package com.example.nbu.mvvm.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.nbu.mvvm.AbstractViewModel;
import com.example.nbu.mvvm.fragment.AbstractFragment;


public abstract class AbstractActivity<B extends ViewDataBinding, VM extends AbstractViewModel> extends AppCompatActivity {

    private int numOfBackPressed = 0;

    private AbstractFragment<?,?> currentView;

    protected B binding;

    protected VM viewModel;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(getViewModelClass());
        binding = DataBindingUtil.setContentView(this, getLayoutId());
//        makeStatusBarTransparent();
    }

    protected abstract int getLayoutId();

    protected abstract Class<VM> getViewModelClass();

    protected abstract int getContainerViewId();

    private void makeStatusBarTransparent() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    public <T extends AbstractActivity<?,?>>void openActivity(Class<T> clazz, Bundle args) {
        hideKeyboard();

        Bundle resolvedArgs = resolveArgs(args, viewModel);
        //pass current activity stack request
        boolean keepInStack = keepInStack();
        resolvedArgs.putBoolean(KEEP_IN_STACK_KEY, keepInStack);

        //start the new activity
        Intent intent = new Intent(this, clazz);
        intent.putExtra(ACTIVITY_BUNDLE_EXTRA_KEY, args);
        startActivity(intent);

        //finish current activity
        if (!keepInStack) {
            finish();
        }
    }

    public <T extends AbstractFragment<?,?>> void openView(Class<T> viewClass, Bundle args) {
        hideKeyboard();

        int containerViewId = getContainerViewId();
        if (containerViewId == 0) {
            throw new RuntimeException("MISSING VIEW CONTAINER ID");
        }

        String viewName = viewClass.getName();
        String currentViewClassName = null;
        if (currentView != null) {
            currentViewClassName = currentView.getClass().getName();
        }

        if (currentViewClassName == null || !currentViewClassName.equals(viewName)) {
            FragmentManager fm = getSupportFragmentManager();
            //todo if i add a spash screen, i need these lines
//            if(currentView is SplashFragment){
//                fm.popBackStack()
//            }
            Fragment existing = fm.findFragmentByTag(viewName);
            AbstractFragment<?,?> newView;
            if (existing != null) {
                newView = popBackStackTo(fm, viewName, args);
            } else {
                newView = attachNewView(fm, containerViewId, viewName, args);
            }
            currentView = newView;
        }
    }

    private AbstractFragment<?,?> attachNewView(
            FragmentManager fragmentManager,
            int containerId,
            String viewClassName,
            Bundle args) {
        FragmentFactory fact = fragmentManager.getFragmentFactory();

        Fragment newFragment = fact.instantiate(getClassLoader(), viewClassName);
        if (args != null) {
            newFragment.setArguments(args);
        }
        FragmentTransaction ftx = fragmentManager.beginTransaction();

        ftx.replace(containerId, newFragment, viewClassName);
        ftx.addToBackStack(viewClassName);
        ftx.commit();
        fragmentManager.executePendingTransactions();

        return (AbstractFragment<?,?>) newFragment;
    }


    private void navigateBack() {
        hideKeyboard();

        FragmentManager fm = getSupportFragmentManager();
        int lastBackStackEntryIndex = fm.getBackStackEntryCount() - 1;
        if (lastBackStackEntryIndex <= 0) {
            handleFinishAfterTransition();
        } else {
            int lastTaggedEntryIndex = lastBackStackEntryIndex - 1;
            FragmentManager.BackStackEntry backStackEntry;

            do {
                // search for a non-null tag for entries above the bottom one
                backStackEntry = fm.getBackStackEntryAt(lastTaggedEntryIndex);
            } while ((backStackEntry.getName() == null) && ((lastTaggedEntryIndex--) > 0));

            String viewClassName = backStackEntry.getName();
            if (viewClassName != null) {
                currentView = popBackStackTo(fm, viewClassName, null);
            }
        }
    }

    private AbstractFragment<ViewDataBinding, AbstractViewModel> popBackStackTo(
            FragmentManager fragmentManager,
            String viewClassName,
            Bundle args) {

        Fragment view = fragmentManager.findFragmentByTag(viewClassName);
        if (view == null) {
            return null;
        }
        Bundle existingArgs = view.getArguments();
        if (existingArgs != null && args != null) {
            existingArgs.putAll(args);
        } else {
            view.setArguments(args);
        }

        fragmentManager.popBackStack(viewClassName, 0);
        fragmentManager.executePendingTransactions();
        return (AbstractFragment<ViewDataBinding, AbstractViewModel>) view;
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        if (currentView == null) {
            handleBackPress();
        } else {
            navigateBack();
        }
    }

    private void hideKeyboard() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            IBinder windowToken = currentFocus.getWindowToken();
            inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
        }
    }

    private void handleBackPress() {
        ++numOfBackPressed;
        if (numOfBackPressed == MAX_NUM_OF_BACK_PRESSES) {

            super.onBackPressed();
        } else {
            handleExitMsg();
        }
    }

    //handles back presses while the activity has only one fragment in the backstack
    private void handleFinishAfterTransition() {
        ++numOfBackPressed;
        if (numOfBackPressed == MAX_NUM_OF_BACK_PRESSES) {
            supportFinishAfterTransition();
        } else {
            handleExitMsg();
        }
    }

    private void handleExitMsg() {
        String exitMsg = "press again to exit";
        Toast.makeText(this, exitMsg, Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).postDelayed(() -> numOfBackPressed = 0, EXIT_MSG_DELAY_TIME);
    }

    private Bundle resolveArgs(Bundle viewArgs, AbstractViewModel vm) {
        Bundle args = new Bundle();
        if (viewArgs != null) {
            args.putAll(viewArgs);
        }
        Bundle vmArgs = vm.postNavigationArgs();
        if (vmArgs != null) {
            args.putAll(vmArgs);
        }
        return args;
    }

    boolean keepInStack() {
        return false;
    }

    private static final String KEEP_IN_STACK_KEY = "keep_in_stack_key";

    static final String ACTIVITY_BUNDLE_EXTRA_KEY = "activity_bundle_extra_key";

    private static final int MAX_NUM_OF_BACK_PRESSES = 2;

    private static final long EXIT_MSG_DELAY_TIME = 2000; // time in milliseconds
}
