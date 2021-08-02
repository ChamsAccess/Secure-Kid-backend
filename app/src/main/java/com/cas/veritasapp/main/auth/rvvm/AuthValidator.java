package com.cas.veritasapp.main.auth.rvvm;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.cas.veritasapp.R;


/**
 * Created by funmiayinde on 2019-10-14.
 */
public class AuthValidator {

    private AuthViewModel viewModel;

    public AuthValidator(AuthViewModel viewModel) {
        this.viewModel = viewModel;
    }

    private class EditTextChangeWatcher implements TextWatcher {

        private EditText editText;

        private EditTextChangeWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            AuthModel authModel = viewModel.getAuthModel();
            switch (editText.getId()) {
                case R.id.email:
                    authModel.setEmail(s.toString());
                    viewModel.authValidation.validateEmail(s.toString());
                    viewModel.validation.setValue(viewModel.authValidation);
                    break;
                case R.id.password:
                    authModel.setPassword(s.toString());
                    viewModel.authValidation.validatePassword(s.toString());
                    viewModel.validation.setValue(viewModel.authValidation);
                    break;
            }
            viewModel.setUserAuth(authModel);
        }

        @Override
        public void afterTextChanged(Editable e) {
        }
    }

    public void setEditTextChangeListener(EditText edit) {
        edit.addTextChangedListener(new EditTextChangeWatcher(edit));
    }
}
