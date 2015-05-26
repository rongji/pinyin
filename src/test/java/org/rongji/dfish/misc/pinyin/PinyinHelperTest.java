package org.rongji.dfish.misc.pinyin;

import static org.junit.Assert.*;

import org.junit.Test;

public class PinyinHelperTest {

	@Test
	public void test() {
//		fail("Not yet implemented");
		String s=PinyinHelper.convertToPinyinString("厦门市少年有不少参加了马拉松比赛","",PinyinHelper.WITH_TONE_MARK);
		assertEquals("xiàménshìshàoniányoǔbùshǎocānjiālemǎlāsōngbǐsài", s);
	}

}
