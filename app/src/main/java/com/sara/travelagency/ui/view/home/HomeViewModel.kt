package com.sara.travelagency.ui.view.home

import android.R.attr
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sara.travelagency.domain.LookUpRoom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val lookUppRoom: LookUpRoom
) : ViewModel() {

    fun allCountries(): List<String> {
        return  listOf(
            "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize",
            "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "British Indian Ocean Territory", "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burma (Myanmar)", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands",
            "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Cook Islands", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica",
            "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland", "France", "French Polynesia", "Gabon", "Gambia", "Gaza Strip", "Georgia", "Germany", "Ghana", "Gibraltar",
            "Greece", "Greenland", "Grenada", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Holy See (Vatican City)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Ivory Coast",
            "Jamaica", "Japan", "Jersey", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar",
            "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia", "Moldova", "Monaco",
            "Mongolia", "Montenegro", "Montserrat", "Morocco", "Mozambique", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "North Korea", "Northern Mariana Islands", "Norway",
            "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn Islands", "Poland", "Portugal", "Puerto Rico", "Qatar", "Republic of the Congo", "Romania", "Russia", "Rwanda", "Saint Barthelemy", "Saint Helena", "Saint Kitts and Nevis",
            "Saint Lucia", "Saint Martin", "Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia",
            "South Africa", "South Korea", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan",
            "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "US Virgin Islands", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Wallis and Futuna", "West Bank", "Yemen", "Zambia", "Zimbabwe")
    }

    fun lookUpRoom(city: String, capacity: String, price: String) {
        viewModelScope.launch {
            val rooms = lookUppRoom(city, capacity, price)
            Log.i("POTATO", rooms.toString())
        }
    }


}