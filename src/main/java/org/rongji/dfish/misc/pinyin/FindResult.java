package org.rongji.dfish.misc.pinyin;

class FindResult{
	public FindResult(int state, int begin, int end) {
		this.state=state;
		this.begin=begin;
		this.end=end;
	}

	int begin;
	int end;
	int state;
	String replaceTo;
	static final int STATE_MATCH=1;
	static final int STATE_UNMATCH=0;
	static final int STATE_PREFIX=2;
}
