package com.cas.veritasapp.main.home.rvvm.enrollment;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.data.entities.ContributionBio;
import com.cas.veritasapp.core.data.entities.Employment;
import com.cas.veritasapp.core.data.entities.Enrollment;
import com.cas.veritasapp.core.data.entities.NextOfKin;
import com.cas.veritasapp.core.data.entities.PFACertification;
import com.cas.veritasapp.core.data.entities.Personal;

public class EnrollmentValidator {

    private EnrollmentViewModel viewModel;

    public EnrollmentValidator(EnrollmentViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void reset() {
        viewModel.setCurrentValidation(new EnrollmentValidation());
        viewModel.getValidation().setValue(viewModel.getCurrentValidation());
    }

    public static String validatePersonal(Personal personal) {
        String message = "";
        if (personal != null) {
            if (personal.getTitle() == null || personal.getTitle().isEmpty()) {
                message = "Title in personal details cannot be empty";
            }
            if (personal.getFirstName() == null || personal.getFirstName().isEmpty()) {
                message = "First name in personal details cannot be empty";
            }
            if (personal.getSurname() == null || personal.getSurname().isEmpty()) {
                message = "Surname in personal details cannot be empty";
            }
            if (personal.getNationality() == null || personal.getNationality().isEmpty()) {
                message = "Nationality in personal details cannot be empty";
            }
            if (personal.getPhoneNumber() == null || personal.getPhoneNumber().isEmpty()) {
                message = "Phone number in personal details cannot be empty";
            }
            if (personal.getGender() == null || personal.getGender().isEmpty()) {
                message = "Select gender in personal details";
            }
            if (personal.getMaritalStatus() == null || personal.getMaritalStatus().isEmpty()) {
                message = "Select martial status in personal details";
            }
            if (personal.getNin() == null || personal.getNin().isEmpty()) {
                message = "NIN  in personal details";
            }
            if (personal.getDob() == null || personal.getDob().isEmpty()) {
                message = "Date of birth in personal details";
            }
            if (personal.getLga() == null || personal.getLga().isEmpty()) {
                message = "Select LGA in personal details";
            }
            if (personal.getState() == null || personal.getState().isEmpty()) {
                message = "Select State in personal details";
            }
            if (personal.getLocation() == null) {
                message = "Please fill residence address info in personal details";
            }
            if (personal.getHouseNumber() == null) {
                message = "Please fill house number info in personal details";
            }
        }
        return message;
    }

    public static String validateEmployment(Employment employment) {
        String message = "";
        if (employment != null) {
            if (employment.getSectorClassification()  == null || employment.getSectorClassification().isEmpty()) {
                message = "Select classification in employment cannot be empty";
            }
            if (employment.getEmployerCardId()  == null || employment.getEmployerCardId().isEmpty()) {
                message = "Employer Pencom number in employment cannot be empty";
            }
            if (employment.getLocation() == null) {
                message = "Please fill office address info in employment details";
            }
            if (employment.getLocation() != null) {
                if (employment.getLocation().getStreet() == null || employment.getLocation().getStreet().isEmpty()) {
                    message = "Please fill street address info in employment details";
                }
            }
            if (employment.getHouseNumber() == null || employment.getHouseNumber().isEmpty()) {
                message = "Please fill building no. in employment details";
            }
        }
        return message;
    }

    public static String validateNextOfKin(NextOfKin nextOfKin) {
        String message = "";
        if (nextOfKin != null) {
            if (nextOfKin.getTitle() == null || nextOfKin.getTitle().isEmpty()) {
                message = "Title in next of kin's details cannot be empty";
            }
            if (nextOfKin.getFirst_name() == null || nextOfKin.getFirst_name().isEmpty()) {
                message = "First name in next of kin's details cannot be empty";
            }
            if (nextOfKin.getSurname() == null || nextOfKin.getSurname().isEmpty()) {
                message = "Surname in next of kin's details cannot be empty";
            }
            if (nextOfKin.getMiddle_name() == null || nextOfKin.getMiddle_name().isEmpty()) {
                message = "Middle name in next of kin's details cannot be empty";
            }
            if (nextOfKin.getPhone_number() == null || nextOfKin.getPhone_number().isEmpty()) {
                message = "Phone number in next of kin's details cannot be empty";
            }
            if (nextOfKin.getGender() == null || nextOfKin.getGender().isEmpty()) {
                message = "Select gender in next of kin's details";
            }
            if (nextOfKin.getRelationship() == null || nextOfKin.getRelationship().isEmpty()) {
                message = "Relationship in next of kin's details";
            }
            if (nextOfKin.getLocation() == null) {
                message = "Next of Kin's address cannot be empty";
            }
            if (nextOfKin.getLocation() == null) {
                message = "Please fill location address info in next of kin's details";
            }
        }
        return message;
    }

    public static String validateContributionBio(ContributionBio contributionBio) {
        String message = "";
        if (contributionBio != null) {
            if (contributionBio.getPassport() == null) {
                message = "Please take a passport in contribution bio";
            }
            if (contributionBio.getSignature() == null) {
                message = "Please make sure you sign in contribution bio";
            }

        }
        return message;
    }
    public static String validatePFA(PFACertification pfaCertification) {
        String message = "";
        if (pfaCertification != null) {
            if (pfaCertification.getSignature() == null) {
                message = "Please make sure you sign in in PFA Certification";
            }
        }
        return message;
    }

    public void setEditTextChangeLister(EditText editText) {
        editText.addTextChangedListener(new EditTextChangeListener(editText));
    }

    public class EditTextChangeListener implements TextWatcher {
        private EditText editText;

        public EditTextChangeListener(EditText editText) {
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
            switch (editText.getId()) {
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
