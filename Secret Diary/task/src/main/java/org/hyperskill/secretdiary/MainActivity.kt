package org.hyperskill.secretdiary

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.app.AlertDialog
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import kotlinx.datetime.Clock
import java.text.SimpleDateFormat
import java.util.*

const val PREFERENCES_NAME = "PREF_DIARY"

class MainActivity : AppCompatActivity() {

    private lateinit var etNewWriting: EditText
    private lateinit var btnSave: Button
    private lateinit var tvDiary: TextView
    private lateinit var btnUndo: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

        etNewWriting = findViewById(R.id.etNewWriting)
        btnSave = findViewById(R.id.btnSave)
        tvDiary = findViewById(R.id.tvDiary)
        btnUndo = findViewById(R.id.btnUndo)

        tvDiary.text = sharedPreferences.getString("KEY_DIARY_TEXT", "")

        btnSave.setOnClickListener {
            val text = etNewWriting.text.trim()

            if (text.isNotBlank()) {
                val date = Clock.System.now().toEpochMilliseconds()
                val formattedDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                    .format(Date(date)).toString()
                val note = "$formattedDate\n$text\n\n${tvDiary.text}"
                tvDiary.text = note.trim()
                etNewWriting.text.clear()
                saveNotesToSharedPreferences(sharedPreferences)
            } else {
                Toast.makeText(
                    this, "Empty or blank input cannot be saved",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        btnUndo.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Remove last note")
            builder.setMessage(
                "Do you really want to remove the last writing?" +
                        " This operation cannot be undone!"
            )
                .setPositiveButton("Yes") { _, _ ->
                    // delete last note, if yes button is clicked
                    if (tvDiary.text.contains("\n\n")) {
                        tvDiary.text = (tvDiary.text as String?)?.substringAfter("\n\n")?.trim()
                    } else {
                        tvDiary.text = ""
                    }
                    saveNotesToSharedPreferences(sharedPreferences)
                }
                .setNegativeButton("No") { dialog, _ ->
                    // close dialog, if no button is clicked
                    dialog.dismiss()
                }
                .show()
        }
    }

    private fun saveNotesToSharedPreferences(sharedPreferences: SharedPreferences) {
        sharedPreferences.edit()
            .putString("KEY_DIARY_TEXT", tvDiary.text.toString().trim())
            .apply()
    }
}
