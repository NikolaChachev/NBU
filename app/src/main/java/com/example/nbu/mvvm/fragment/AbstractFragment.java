package com.example.nbu.mvvm.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.nbu.mvvm.AbstractViewModel;
import com.example.nbu.mvvm.activity.AbstractActivity;

public abstract class AbstractFragment<B extends ViewDataBinding, VM extends AbstractViewModel> extends Fragment {


    protected B binding;

    protected VM viewModel;

    //region base lifecycle methods

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        // abstract fragments should only be used through pre-created abstract activity, due to dependency
        if (!(getActivity() instanceof AbstractActivity)) {
            throw new IllegalStateException("Activity has to inherit AbstractActivity!");
        }
        int layoutId = getLayoutResId();
        int bindResId = getViewModelResId();
        viewModel = new ViewModelProvider(this.getActivity()).get(getViewModelClass());
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false);
        binding.setLifecycleOwner(this);
        binding.setVariable(bindResId, viewModel);
        return binding.getRoot();
    }

    //endregion

    protected void createShortToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }

    protected void createShortToast(@StringRes int stringRes) {
        Toast.makeText(getContext(), stringRes, Toast.LENGTH_SHORT).show();
    }

    //region navigation methods

    protected <T extends AbstractFragment<?,?>> void navigateToView(Class<T> clazz,
            Bundle args) {
        FragmentActivity activity = getActivity();
        if(activity instanceof AbstractActivity){
            Bundle navArgs = viewModel.postNavigationArgs();
            if(args != null){
                args.putAll(navArgs);
            }
            ((AbstractActivity)activity).openView(clazz, args);
        }
    }

     public <T extends AbstractActivity<?,?>>void navigateToActivity(
            Class<T> clazz,
            Bundle args
    ) {
         FragmentActivity activity = getActivity();
        if(activity instanceof AbstractActivity){
            ((AbstractActivity)activity).openActivity(clazz, args);
        }
    }

    void navigateBack() {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

    protected abstract int getViewModelResId();

    protected abstract int getLayoutResId();

    protected abstract Class<VM> getViewModelClass();

    //endregion

}
