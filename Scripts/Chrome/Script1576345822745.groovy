import com.ketsuago.VideoPlayer
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('https://hls-js.netlify.com/demo/')

VideoPlayer.verifyPlayed('test_urls')

WebUI.closeBrowser()

