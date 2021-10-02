package com.cas.veritasapp.main.home.rvvm.enrollment;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.data.entities.Enrollment;
import com.cas.veritasapp.core.data.entities.Personal;

public class EnrollmentValidator {

    private EnrollmentViewModel viewModel;

    public EnrollmentValidator(EnrollmentViewModel viewModel){
        this.viewModel = viewModel;
    }

    public void reset(){
        viewModel.setCurrentValidation(new EnrollmentValidation());
        viewModel.getValidation().setValue(viewModel.getCurrentValidation());
    }

    public void setEditTextChangeLister(EditText editText){
        editText.addTextChangedListener(new EditTextChangeListener(editText));
    }

    public class EditTextChangeListener implements TextWatcher{
        private EditText editText;

        public EditTextChangeListener(EditText editText){
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Personal personal = new Personal();
            Enrollment enrollment = viewModel.getCurrent();
            switch (editText.getId()){
                case R.id.firstNameEditText:
                    personal.setFirstName(s.toString());
                    break;
            }
            viewModel.setCurrent(enrollment);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
