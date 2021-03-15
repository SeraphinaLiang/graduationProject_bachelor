package com.example.bbs.util.hanlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.document.sentence.Sentence;
import com.hankcs.hanlp.corpus.document.sentence.word.IWord;
import com.hankcs.hanlp.model.crf.CRFLexicalAnalyzer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class WordSegment {
    HashSet<String> hashSet;

    public WordSegment() {
        hashSet = new HashSet<>();
    }

    public String sliceSentence(String sentence) {

        sentence=sentence.replace(" ","");

        try {
            crf(sentence);
            keyword(sentence);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hashSet.toString();
    }

    private void crf(String sentence) throws IOException {
        CRFLexicalAnalyzer analyzer = new CRFLexicalAnalyzer();
        Sentence result = analyzer.analyze(sentence);
        List<IWord> list = result.wordList;
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            IWord word = (IWord) iterator.next();
            hashSet.add(word.getValue());
        }
    }

    private void keyword(String sentence) {

        List<String> keywordList;

        if (sentence.length() > 15) {
            keywordList = HanLP.extractKeyword(sentence, 5);
        } else if (sentence.length() > 10) {
            keywordList = HanLP.extractKeyword(sentence, 3);
        } else if (sentence.length() > 5) {
            keywordList = HanLP.extractKeyword(sentence, 2);
        } else {
            keywordList = HanLP.extractKeyword(sentence, 1);
        }

        Iterator iterator = keywordList.iterator();
        while (iterator.hasNext()){
            String word = (String) iterator.next();
            if (word!=null){
                hashSet.add(word);
            }
        }

    }
}
