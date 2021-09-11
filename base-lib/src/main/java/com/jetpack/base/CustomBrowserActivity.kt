package com.jetpack.base

import android.os.Bundle
import android.view.View
import android.webkit.*
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

        binding?.webView?.addJavascriptInterface(getHtmlObject(),Constants.CUSTOM_BROWSER_JS_KEY)
    }


     var webChromeClient =object: WebChromeClient(){
         override fun onProgressChanged(view: WebView?, newProgress: Int) {
             super.onProgressChanged(view, newProgress)
             binding?.progressBar?.progress = newProgress
             if (newProgress >= 80){
                 binding?.progressBar?.visibility = View.GONE
             }
         }

         override fun onReceivedTitle(view: WebView?, title: String?) {
             super.onReceivedTitle(view, title)
             if (title.isNullOrEmpty()){
                 baseBinding?.titleTv?.text = title
             }
         }
    }

     var webViewClient = object : WebViewClient(){
         override fun shouldOverrideUrlLoading(
             view: WebView?,
             request: WebResourceRequest?
         ): Boolean {
             return super.shouldOverrideUrlLoading(view, request)
         }


    }

    inner class getHtmlObject{
        @JavascriptInterface
        fun getToken(token:String):String{
            return ""
        }
    }
}