package com.cas.veritasapp.core.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by funmiayinde on 2019-10-10.
 */
public class AppConstant {

    private static final String TAG = AppConstant.class.getSimpleName();

    public static String[] MONTH_NAMES = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
            "Aug", "Sep", "Oct", "Nov", "Dec"};

    public static String[] RSAPIN_STATUS = {"Select RSP Status","Available", "Pending",};
    public static String[] REGISTRATION_STATUS = {"Select REG Status","Completed", "Incomplete",};

    public static final String TOKEN = "token";
    public static final String USER_ID = "userId";
    public static final String DATE = "date";
    public static final String DATE_PATTERN = "EEEE, MMMM dd, yyyy";
    public static final String DATE_PATTERN_2 = "YYYY-MM-DD";
    public static final String END_DATE = "end_date";
    public static final String FILTER_ENROLLMENT = "filter_enrollment";
    public static final String TIME = "time";
    public static final String AUTH_STAFF = "authenticated_staff";
    public static final String SHOW_BACK_BUTTON = "show_back_button";
    public static final String SOURCE_NAV_ID = "source_nav_id";
    public static final String EXTERNAL_SD_PATH = "/storage/sdcard0/VeritasDatabase";


    public static final String GET_ACTION = "get_action";
    public static final String EDIT_ACTION = "edit_action";
    public static final String CREATE_ACTION = "create_action";
    public static final String UPDATE_ACTION = "update_action";
    public static final String UPDATE_ENROLLMENT = "update_enrollment";
    public static final String SEND_GENERATE_PDF = "generate_pdf";
    public static final String SEND_BALANCE = "pension_balance";
    public static final String SHOW_ENROLLMENT_DETAILS = "show_enrollment_details";
    public static final String SHOW_EMPLOYER_DETAILS = "show_employer_details";
    public static final String CAPTURE_USER_IMAGE_ACTION = "capture_user_image_action";
    public static final String CAPTURE_USER_SIGNATURE_ACTION = "capture_user_signature_action";
    public static final String CAPTURE_AGENT_SIGNATURE_ACTION = "capture_agent_signature_action";
    public static final String DELETE_ACTION = "delete_action";
    public static final String UPLOAD_ACTION = "upload_action";

    public static final String CREATE_ENROLLMENT = "create_enrollment_action";
    public static final String ENROLLMENT_STATS = "enrollment_stats";
    public static final String GET_ENROLLMENT_ERROR = "get_enrollment_error";
    public static final String GET_SEARCHED_EMPLOYER = "get_searched_employer";


    public static final String PASSPORT = "passport";
    public static final String SIGNATURE = "signature";
    public static final String SAVE_USER_SIGNATURE = "save_user_signature";
    public static final String SAVE_AGENT_SIGNATURE = "save_agent_signature";
    public static final String AGENT_SIGNATURE = "agent_signature";
    public static final String ENROLLMENT = "enrollment";
    public static final String ENROLLMENT_ERRORS = "enrollment_errors";

    public static final String FIND_NIN_DATA = "find_nin_data";
    public static final String FIND_COUNTRIES = "find_countries";
    public static final String FIND_COUNTRY = "find_country";
    public static final String FIND_STATES = "find_state";
    public static final String FIND_STATE = "find_state";

    public static final String FIND_ENROLLMENT = "find_enrollment_data";
    public static final String POPULATE_ENROLLMENT = "[\"personal\", \"next_of_kin\", \"employment\", \"salary\", \"contribution_bio\", \"pfa_certification.signature\"]";

    public static final String[] COUNTRIES = new String[]{"Select Country","Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla",
            "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas",
            "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia",
            "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam",
            "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands",
            "Central African Republic", "Chad", "Chile", "China, People's republic of", "Christmas Island", "Cocos (Keeling) Islands", "Colombia",
            "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire",
            "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic",
            "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia",
            "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana",
            "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar",
            "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti",
            "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India",
            "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan",
            "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kosovo", "Kuwait",
            "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya",
            "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar",
            "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius",
            "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat",
            "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles",
            "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands",
            "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Palestine", "Peru", "Philippines", "Pitcairn",
            "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda",
            "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino",
            "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore",
            "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa",
            "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon",
            "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic",
            "Taiwan", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Tibet", "Togo", "Tokelau", "Tonga",
            "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine",
            "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay",
            "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)",
            "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"};

    public static final String[] STATES = {
            "Select State",
            "Abia",
            "Adamawa",
            "Akwa Ibom",
            "Anambra",
            "Bauchi",
            "Bayelsa",
            "Benue",
            "Borno",
            "Cross River",
            "Delta",
            "Ebonyi",
            "Edo",
            "Ekiti",
            "Enugu",
            "FCT - Abuja",
            "Gombe",
            "Imo",
            "Jigawa",
            "Kaduna",
            "Kano",
            "Katsina",
            "Kebbi",
            "Kogi",
            "Kwara",
            "Lagos",
            "Nasarawa",
            "Niger",
            "Ogun",
            "Ondo",
            "Osun",
            "Oyo",
            "Plateau",
            "Rivers",
            "Sokoto",
            "Taraba",
            "Yobe",
            "Zamfara"

    };

}

