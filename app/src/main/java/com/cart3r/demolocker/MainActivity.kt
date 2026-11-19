package com.cart3r.demolocker

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*

class MainActivity : Activity() {

    companion object {
        init {
            System.loadLibrary("demolocker")
        }
    }

    private external fun nativeCheckPin(
        pin: String
    ): Boolean

    private val bg = Color.rgb(7, 7, 9)

    private val panel = Color.rgb(20, 13, 15)

    private val red = Color.rgb(239, 48, 68)

    private val muted = Color.rgb(175, 165, 168)

    private fun dp(v: Int): Int {
        return (
            v * resources.displayMetrics.density
        ).toInt()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = bg
        window.navigationBarColor = bg

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL

            gravity = Gravity.CENTER_HORIZONTAL

            setPadding(
                dp(24),
                dp(28),
                dp(24),
                dp(20)
            )

            setBackgroundColor(bg)
        }

        root.addView(
            createText(
                "CART3R.JAILBREAK / LAB",
                12,
                red,
                true
            ),
            matchWrap()
        )

        val title = createText(
            "DEMO LOCKER",
            30,
            Color.WHITE,
            true
        )

        title.setPadding(0, dp(24), 0, dp(6))

        root.addView(title, matchWrap())

        root.addView(
            createText(
                "ANDROID SECURITY SIMULATION",
                11,
                muted,
                true
            ),
            matchWrap()
        )

        val card = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL

            setPadding(
                dp(20),
                dp(22),
                dp(20),
                dp(20)
            )

            setBackgroundColor(panel)
        }

        val status = createText(
            "DEMONSTRATION MODE",
            13,
            red,
            true
        )

        card.addView(status, matchWrap())

        val message = createText(
            "This is a safe local simulation. " +
            "It does not lock Android or prevent uninstalling.",
            14,
            muted,
            false
        )

        message.setPadding(
            0,
            dp(14),
            0,
            dp(18)
        )

        card.addView(message, matchWrap())

        val pin = EditText(this).apply {
            hint = "Enter demo PIN"

            setHintTextColor(Color.GRAY)

            setTextColor(Color.WHITE)

            gravity = Gravity.CENTER

            inputType = 2 or 16

            setSingleLine(true)

            setBackgroundColor(
                Color.rgb(38, 25, 29)
            )
        }

        card.addView(
            pin,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dp(54)
            )
        )

        val unlock = createButton("UNLOCK DEMO")

        unlock.setOnClickListener {

            if (nativeCheckPin(pin.text.toString())) {

                Toast.makeText(
                    this,
                    "Demo unlocked",
                    Toast.LENGTH_SHORT
                ).show()

                status.text = "STATUS: UNLOCKED"

                status.setTextColor(Color.GREEN)

                pin.text.clear()

            } else {

                Toast.makeText(
                    this,
                    "Incorrect PIN",
                    Toast.LENGTH_SHORT
                ).show()

                pin.text.clear()
            }
        }

        card.addView(
            unlock,
            buttonParams()
        )

        val exit = createButton("EXIT DEMO")

        exit.setOnClickListener {
            finish()
        }

        card.addView(
            exit,
            buttonParams()
        )

        root.addView(
            card,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                topMargin = dp(34)
            }
        )

        root.addView(
            createText(
                "SAFE LOCAL SIMULATION - v1.0",
                10,
                muted,
                true
            ),
            matchWrap()
        )

        setContentView(root)
    }

    private fun createText(
        value: String,
        size: Int,
        color: Int,
        bold: Boolean
    ): TextView {

        return TextView(this).apply {

            text = value

            textSize = size.toFloat()

            setTextColor(color)

            gravity = Gravity.CENTER

            if (bold) {
                setTypeface(
                    typeface,
                    Typeface.BOLD
                )
            }
        }
    }

    private fun createButton(
        label: String
    ): Button {

        return Button(this).apply {

            text = label

            isAllCaps = false

            textSize = 14f

            setTextColor(Color.WHITE)

            setBackgroundColor(red)
        }
    }

    private fun matchWrap() =
        LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

    private fun buttonParams() =
        LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            dp(52)
        ).apply {
            topMargin = dp(12)
        }
}
