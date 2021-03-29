import chrome.Chrome
import chrome.download.DownloadOptions
import chrome.download.download
import chrome.runtime.sendMessage
import kotlinx.browser.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.html.*
import kotlinx.html.dom.*
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.HTMLAudioElement
import org.w3c.dom.HTMLSourceElement
import org.w3c.dom.asList
import kotlin.js.json

fun main() {
    val playAudioButtons = document.getElementsByClassName("concept_audio")
        .asList()
        .map { it as HTMLAnchorElement }

    val audioContainers = document.getElementsByTagName("audio")
        .asList()
        .map { it as HTMLAudioElement }

    playAudioButtons.zip(audioContainers).forEach { (playButton, audioContainer) ->
        val downloadAudioButton = document.create.a(classes = "concept_light-status_link") {
            text("Download Audio")
            onClickFunction = {
                GlobalScope.launch {
                    val url = (audioContainer.firstElementChild as HTMLSourceElement).src
                    val filename = audioContainer.id + ".mp3"
                    Chrome.runtime.sendMessage(message = json(
                        "type" to "JishoDownload",
                        "url" to url,
                        "filename" to filename
                    ))
                }
            }
        }
        playButton.parentNode?.insertBefore(downloadAudioButton, playButton.nextSibling)
    }
}
