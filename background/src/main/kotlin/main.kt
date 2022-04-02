import chrome.Chrome
import chrome.download.DownloadOptions
import chrome.download.FilenameConflictAction
import chrome.download.download
import chrome.tabs.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun main() {
    Chrome.omnibox.onInputEntered.addListener { text, _ ->
        GlobalScope.launch {
            Chrome.tabs.create(CreateProperties(url = "https://forvo.com/word/$text/#ja"))
            Chrome.tabs.create(CreateProperties(url = "https://www.google.com/search?as_st=y&tbm=isch&hl=en&as_q=$text&as_epq=&as_oq=&as_eq=&imgsz=&imgar=&imgc=&imgcolor=&imgtype=&cr=countryJP&as_sitesearch=&safe=images&as_filetype=&as_rights="))
            Chrome.tabs.create(CreateProperties(url = "https://jisho.org/search/$text"))
            text.toList()
                .map { "http://www.kanjidamage.com/kanji/search?utf8=✓&q=$it" }
                .forEach { url -> Chrome.tabs.create(CreateProperties(url = url)) }
        }
    }

    Chrome.runtime.onMessage.addListener { message, _, _ ->
        if (message.type == "JishoDownload") {
            println(message.filename.unsafeCast<String>())
            println(message.url.unsafeCast<String>())
            // crashes all of chrome for some reason
            GlobalScope.launch {
                val downloadId = Chrome.downloads.download(DownloadOptions(
                    conflictAction = FilenameConflictAction.uniquify,
                    filename = message.filename.unsafeCast<String>(),
                    url = message.url.unsafeCast<String>())
                )

                if (downloadId == undefined) {
                    println(Chrome.runtime.lastError)
                }
            }
        }
    }
}
