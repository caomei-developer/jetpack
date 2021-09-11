package com.jetpack.base

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.jetpack.base.databinding.ActivityCustomBrowserBinding


class CustomBrowserActivity : BaseActivity() {

    private var url: String? = null

    private var binding: ActivityCustomBrowserBinding? = null

    override fun initViewModel() {
        super.initViewModel()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustomBrowserBinding.inflate(layoutInflater)

        binding?.root?.let {
            setContentView(it)
        }


        if (intent !=null && intent.getStringExtra(Constants.CUSTOM_BROWSER_URL_KEY).isNullOrEmpty()) {
            url = intent.getStringExtra(Constants.CUSTOM_BROWSER_URL_KEY)
            binding?.webView?.loadUrl(url!!)
        }

        binding?.webView?.webChromeClient = webChromeClient

        binding?.webView?.webViewClient = webViewClient
    }


    var webChromeClient =object: WebChromeClient(){

    }

    var webViewClient = object : WebViewClient(){

    }

}