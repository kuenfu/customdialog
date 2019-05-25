package com.example.customdialoglib

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.custom_dialog.*
class CustomDialog(context: Context) : Dialog(context, R.style.CustomDialogStyle) {


    private var message: String? = null
    private var title: String? = null
    private var positive: String? = null
    private var negtive: String? = null


    private var isSingle = false


    private var onClickBottomListener: OnClickBottomListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_dialog)

        setCanceledOnTouchOutside(false)

        refreshView()

        initEvent()
    }


    private fun initEvent() {

        cancel!!.setOnClickListener {
            if (onClickBottomListener != null) {
                onClickBottomListener!!.onPositiveClick()
            }
        }


        confirm!!.setOnClickListener {
            if (onClickBottomListener != null) {
                onClickBottomListener!!.onNegtiveClick()
            }
        }

    }


    private fun refreshView() {

        if (!TextUtils.isEmpty(title)) {
            titleshow!!.text = title
            titleshow!!.visibility = View.VISIBLE
        } else {
            titleshow!!.visibility = View.GONE
        }
        if (!TextUtils.isEmpty(message)) {
            messageshow!!.text = message
        }


        cancel!!.text = if (!TextUtils.isEmpty(positive)) positive else "确定"

        confirm!!.text = if (!TextUtils.isEmpty(negtive)) negtive else "取消"

        confirm!!.visibility = if (isSingle) View.GONE else View.VISIBLE


    }

    override fun show() {
        super.show()
        refreshView()
    }


    fun setOnClickBottomListener(onClickBottomListener: OnClickBottomListener): CustomDialog {
        this.onClickBottomListener = onClickBottomListener
        return this
    }

    interface OnClickBottomListener {

        fun onPositiveClick()

        fun onNegtiveClick()
    }

//    fun getMessage(): String? {
//        return message
//    }

    fun setMessage(message: String): CustomDialog {
        this.message = message
        return this
    }

//    fun getTitle(): String? {
//        return title
//    }

    fun setTitle(title: String): CustomDialog {
        this.title = title
        return this
    }

//    fun getPositive(): String? {
//        return positive
//    }

    fun setPositive(positive: String): CustomDialog {
        this.positive = positive
        return this
    }

//    fun getNegtive(): String? {
//        return negtive
//    }

    fun setNegtive(negtive: String): CustomDialog {
        this.negtive = negtive
        return this
    }


//    fun isSingle(): Boolean {
//        return isSingle
//    }

    fun setSingle(single: Boolean): CustomDialog {
        isSingle = single
        return this
    }

    override fun onBackPressed() {
        Log.i("clickBackPressed", "clickBackPressed")
    }


}
