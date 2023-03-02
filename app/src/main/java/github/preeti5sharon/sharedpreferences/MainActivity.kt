package github.preeti5sharon.sharedpreferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import github.preeti5sharon.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(_binding?.root)

        val sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)
        val editor = sharedPref.edit()
        val theme = sharedPref.getInt("App Theme", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        AppCompatDelegate.setDefaultNightMode(theme)

        _binding?.btn2?.setOnClickListener {
            val name = _binding?.editText1?.text.toString()
            val country = _binding?.editText2?.text.toString()
            val age = _binding?.editText3?.text.toString().toInt()
            val isAdult = _binding?.checkbox?.isChecked

            editor.apply {
                putString("name", name)
                putString("country", country)
                putInt("age", age)
                putBoolean("isAdult", isAdult == true)
                putInt("App Theme", AppCompatDelegate.MODE_NIGHT_YES)
                apply()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

        _binding?.btn1?.setOnClickListener {
            val name = sharedPref.getString("name", null)
            val country = sharedPref.getString("country", null)
            val age = sharedPref.getInt("age", 0)
            val isAdult = sharedPref.getBoolean("isAdult", false)

            editor.run {
                putInt("App Theme", AppCompatDelegate.MODE_NIGHT_NO)
                apply()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            _binding?.editText1?.setText(name)
            _binding?.editText2?.setText(country)
            _binding?.editText3?.setText(age.toString())
            _binding?.checkbox?.isChecked = isAdult
        }
    }
}
