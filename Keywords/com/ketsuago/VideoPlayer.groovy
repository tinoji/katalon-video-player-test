package com.ketsuago
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testdata.InternalData
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

public class VideoPlayer {
	static final int PLAY_DELAY = 20
	static final int PLAYED_DURATION_THRESHOLD = 5

	/**
	 * Verify video played
	 * @param dataFile data file name
	 */
	@Keyword
	public static void verifyPlayed(String dataFile) {
		InternalData urls = findTestData(dataFile)

		for (def index : (0..urls.getRowNumbers() - 1)) {
			String url = urls.internallyGetValue('URL', index)
			WebUI.setText(findTestObject('Object Repository/Page_hlsjs demo/input_here_streamURL'), url)
			WebUI.click(findTestObject('Object Repository/Page_hlsjs demo/div_Persist                                _235cfb'))
			WebUI.delay(PLAY_DELAY)

			Number playedDuration = WebUI.executeJavaScript('return document.getElementsByTagName("video")[0].played.end(0)', [])
			if (!WebUI.verifyLessThan(PLAYED_DURATION_THRESHOLD, playedDuration, FailureHandling.OPTIONAL)) {
				KeywordUtil.markFailedAndStop("Played duration is too short. Maybe failed to play the video: " + url)
			}
		}
	}
}