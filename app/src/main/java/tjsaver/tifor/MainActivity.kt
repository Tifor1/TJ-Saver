package tjsaver.tifor

import android.Manifest
import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.monstertechno.adblocker.AdBlockerWebView
import com.monstertechno.adblocker.AdBlockerWebView.init
import com.monstertechno.adblocker.util.AdBlocker
import tjsaver.tifor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var analytics: FirebaseAnalytics

    @SuppressLint("SetJavaScriptEnabled", "ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        analytics = Firebase.analytics

        perm3()
        perm()
        perm2()

        binding.progressBar.max = 100


        val webSettings: WebSettings = binding.webView.settings
        val isFileAccessAllowed = webSettings.allowFileAccess
        val isFileAccessFromFileURLsAllowed = webSettings.allowFileAccessFromFileURLs
        val defaultFontSize = webSettings.defaultFontSize
        val areImagesLoadedAutomatically = webSettings.loadsImagesAutomatically


        init(this@MainActivity).initializeWebView(binding.webView)


        webSettings.javaScriptEnabled = true
        webSettings.loadsImagesAutomatically = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.displayZoomControls = true
        webSettings.builtInZoomControls = true
        webSettings.setSupportMultipleWindows(true)
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true
        webSettings.allowFileAccess = true
        webSettings.allowFileAccessFromFileURLs = true
        webSettings.defaultFixedFontSize.compareTo(defaultFontSize)
        webSettings.loadsImagesAutomatically.compareTo(areImagesLoadedAutomatically)
        webSettings.allowFileAccessFromFileURLs.compareTo(isFileAccessFromFileURLsAllowed)
        webSettings.allowFileAccess.compareTo(isFileAccessAllowed)
        webSettings.allowUniversalAccessFromFileURLs = true
        // Set network availability
        binding.webView.setNetworkAvailable(true)
        binding.webView.setNetworkAvailable(isNetworkAvailable(this))




        binding.webViewBack.setOnClickListener {

            try {

                if (binding.webView.canGoBack()) {
                    binding.webView.goBack()
                } else {
                    Toast.makeText(this, "Cannot go back", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()

            }


        }

        binding.webViewforward.setOnClickListener {


            try {

                if (binding.webView.canGoForward()) {
                    binding.webView.goForward()
                } else {
                    Toast.makeText(this, "Cannot go forward", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()

            }


        }

        binding.youtube.setOnClickListener {

            try {


                Toast.makeText(this, "This Add Later", Toast.LENGTH_SHORT).show()

                binding.webView.loadUrl("https://en.savefrom.net/391GA/")
                binding.progressBar.visibility = View.VISIBLE
                binding.webView.visibility = View.VISIBLE
                binding.youtube.visibility = View.GONE
                binding.facebook.visibility = View.GONE
                binding.facebookReels.visibility = View.GONE
                binding.tiktok.visibility = View.GONE
                binding.text2.visibility = View.GONE
                binding.ytText.visibility = View.GONE
                binding.facebook.visibility = View.GONE
                binding.facebookReels.visibility = View.GONE
                binding.tikText.visibility = View.GONE

            } catch (e: Exception) {

                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()

            }


        }

        binding.facebook.setOnClickListener {

            try {

                binding.webView.loadUrl("https://fdown.net/")
                binding.progressBar.visibility = View.VISIBLE
                binding.webView.visibility = View.VISIBLE
                binding.youtube.visibility = View.GONE
                binding.facebook.visibility = View.GONE
                binding.facebookReels.visibility = View.GONE
                binding.tiktok.visibility = View.GONE
                binding.text2.visibility = View.GONE
                binding.ytText.visibility = View.GONE
                binding.facebook.visibility = View.GONE
                binding.facebookReels.visibility = View.GONE
                binding.tikText.visibility = View.GONE

            } catch (e: Exception) {

                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()

            }

        }

        binding.facebookReels.setOnClickListener {

            try {

                binding.webView.loadUrl("https://fdownloader.net/en/facebook-reels-downloader")
                binding.progressBar.visibility = View.VISIBLE
                binding.webView.visibility = View.VISIBLE
                binding.youtube.visibility = View.GONE
                binding.facebook.visibility = View.GONE
                binding.facebookReels.visibility = View.GONE
                binding.tiktok.visibility = View.GONE
                binding.text2.visibility = View.GONE
                binding.ytText.visibility = View.GONE
                binding.facebook.visibility = View.GONE
                binding.facebookReels.visibility = View.GONE
                binding.tikText.visibility = View.GONE

            } catch (e: Exception) {

                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()

            }

        }

        binding.tiktok.setOnClickListener {

            try {

                binding.webView.loadUrl("https://savetik.co/en")
                binding.progressBar.visibility = View.VISIBLE
                binding.webView.visibility = View.VISIBLE
                binding.youtube.visibility = View.GONE
                binding.facebook.visibility = View.GONE
                binding.facebookReels.visibility = View.GONE
                binding.tiktok.visibility = View.GONE
                binding.text2.visibility = View.GONE
                binding.ytText.visibility = View.GONE
                binding.facebook.visibility = View.GONE
                binding.facebookReels.visibility = View.GONE
                binding.tikText.visibility = View.GONE

            } catch (e: Exception) {

                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()

            }

        }

        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?, request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            @Deprecated("Deprecated in Java")
            @Suppress("deprecation")
            override fun shouldInterceptRequest(view: WebView, url: String): WebResourceResponse? {
                return if (AdBlockerWebView.blockAds(view, url)) {
                    AdBlocker.createEmptyResource()
                } else {
                    super.shouldInterceptRequest(view, url)
                }
            }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        }

        binding.progressBar.progress = 0
        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                binding.progressBar.progress = newProgress

                if (newProgress == 100) binding.progressBar.visibility = View.INVISIBLE
                else binding.progressBar.visibility = View.VISIBLE

                super.onProgressChanged(view, newProgress)
            }
        }

        try {
            binding.webView.setDownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
                val request = DownloadManager.Request(Uri.parse(url))
                request.setMimeType(mimetype)
                val cookies = CookieManager.getInstance().getCookie(url)
                request.addRequestHeader("cookies", cookies)

                request.addRequestHeader("User-Agent", userAgent)
                request.setDescription("Download Started...")
                request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimetype))
                request.allowScanningByMediaScanner()
                request.setShowRunningNotification(true)
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                request.setNotificationVisibility(DownloadManager.Request.NETWORK_MOBILE)
                request.setNotificationVisibility(DownloadManager.Request.NETWORK_WIFI)
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                request.setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,
                    URLUtil.guessFileName(url, contentDisposition, mimetype)
                )

                val dm = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
                dm.enqueue(request)

                Toast.makeText(this, "Download Started...", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }

        // Handle incoming intent
        if (intent.action == Intent.ACTION_SEND && intent.type == "text/plain") {
            val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)
            if (!sharedText.isNullOrBlank() && sharedText.startsWith("http")) {
                // If the shared text is a URL, copy it to the clipboard
                copyToClipboard(sharedText)
            }
        }


    }

    // Function to check network availability
    @SuppressLint("ObsoleteSdkInt")
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkCapabilities = it.getNetworkCapabilities(it.activeNetwork)
                networkCapabilities?.let { capabilities ->
                    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                }
            } else {
                val networkInfo = it.activeNetworkInfo
                networkInfo?.let { info ->
                    return info.isConnectedOrConnecting
                }
            }
        }
        return false
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {

        binding.webView.loadUrl("")
        binding.progressBar.visibility = View.GONE
        binding.webView.visibility = View.GONE
        binding.youtube.visibility = View.VISIBLE
        binding.facebook.visibility = View.VISIBLE
        binding.facebookReels.visibility = View.VISIBLE
        binding.tiktok.visibility = View.VISIBLE
        binding.text2.visibility = View.VISIBLE
        binding.ytText.visibility = View.VISIBLE
        binding.facebook.visibility = View.VISIBLE
        binding.facebookReels.visibility = View.VISIBLE
        binding.tikText.visibility = View.VISIBLE
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun perm() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            val permissionNotif = ActivityCompat.checkSelfPermission(
                this@MainActivity, Manifest.permission.POST_NOTIFICATIONS
            )
            if (permissionNotif != PackageManager.PERMISSION_GRANTED) {
                val NOTIF_PERM = arrayOf(Manifest.permission.POST_NOTIFICATIONS)
                ActivityCompat.requestPermissions(this, NOTIF_PERM, 10)
            }
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun perm3() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            val permissionNotif = ActivityCompat.checkSelfPermission(
                this@MainActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            if (permissionNotif != PackageManager.PERMISSION_GRANTED) {
                val NOTIF_PERM = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                ActivityCompat.requestPermissions(this, NOTIF_PERM, 12)
            }
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun perm2() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            val permissionNotif = ActivityCompat.checkSelfPermission(
                this@MainActivity, Manifest.permission.INTERNET
            )
            if (permissionNotif != PackageManager.PERMISSION_GRANTED) {
                val NOTIF_PERM = arrayOf(Manifest.permission.INTERNET)
                ActivityCompat.requestPermissions(this, NOTIF_PERM, 11)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        perm3()
        perm()
        perm2()
    }

    // Method to copy the URL to the clipboard
    private fun copyToClipboard(text: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("URL", text)
        clipboard.setPrimaryClip(clip)
    }

}