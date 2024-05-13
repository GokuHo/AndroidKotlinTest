package com.androidkotlintest.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.androidkotlintest.R
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class EditTextActivity : AppCompatActivity() {

    private lateinit var textInputLayout2: TextInputLayout
    private lateinit var editText2: EditText
    private lateinit var autoCompleteTextView3: AutoCompleteTextView
    private lateinit var editText4: EditText

    private var menuItem = arrayOf("aaa", "bbb", "ccc")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_text)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            textInputLayout2 = findViewById(R.id.textInputLayout2)
            editText2 = findViewById(R.id.editText2)
            autoCompleteTextView3 = findViewById(R.id.autoCompleteTextView3)
            editText4 = findViewById(R.id.editText4)

            // password
            editText2.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val password = editText2.text.toString()
                    if(password.length < 8) {
                        textInputLayout2.helperText = ""
                        textInputLayout2.error = "enter minimun 8 chars"
                        return
                    }

                    if(!Pattern.compile("[0-9]").matcher(password).find()) {
                        textInputLayout2.helperText = ""
                        textInputLayout2.error = "enter minimun 1 number"
                        return
                    }

                    textInputLayout2.helperText = "strong password"
                    textInputLayout2.error = ""
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })

            // menu
            autoCompleteTextView3.setAdapter(ArrayAdapter<String>(this, R.layout.item_edit_text_menu, menuItem))
            autoCompleteTextView3.setOnItemClickListener(object: AdapterView.OnItemClickListener {
                override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    Toast.makeText(this@EditTextActivity, "position: ${position}, item: ${menuItem[position]}", Toast.LENGTH_SHORT).show()
                }
            })

            editText4.setOnKeyListener(object: View.OnKeyListener{
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                    if(keyCode == KeyEvent.KEYCODE_ENTER) {
                        if(event?.action == KeyEvent.ACTION_UP) {
                            
                        }
                        return true
                    }
                    return false
                }
            })

            insets
        }
    }
}