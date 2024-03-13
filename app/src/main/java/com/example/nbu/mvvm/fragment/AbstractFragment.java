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
import com.example.nbu.mvvm.AbstractViewModel;
import com.example.nbu.mvvm.activity.AbstractActivity;

public abstract class AbstractFragment extends Fragment {

    protected ViewDataBinding binding;

    protected AbstractViewModel viewModel;

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

        binding = DataBindingUtil.inflate(inflater, layoutId, container, false);
        binding.setVariable(bindResId, viewModel);
        return binding.getRoot();
    }

    //endregion

    void createShortToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    void createShortToast(@StringRes int stringRes) {
        Toast.makeText(getContext(), stringRes, Toast.LENGTH_SHORT).show();
    }

    //region navigation methods

    void navigateToView(Class<AbstractActivity> clazz,
            Bundle args) {
        FragmentActivity activity = getActivity();
        if(activity instanceof AbstractActivity){
            Bundle navArgs = viewModel.postNavigationArgs();
            if(args != null){
                args.putAll(navArgs);
            }
//            activity.openView(clazz, args);
        }
    }

     public void navigateToActivity(
            Class<AbstractActivity> clazz,
            Bundle args
    ) {
         FragmentActivity activity = getActivity();
        if(activity instanceof AbstractActivity){
//            it.openActivity(clazz, args);
        }
    }

    void navigateBack() {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

    abstract int getViewModelResId();

    abstract int getLayoutResId();

    abstract Class<AbstractViewModel> getViewModelClass();

    //endregion

}
