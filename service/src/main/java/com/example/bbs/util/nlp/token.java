package com.example.bbs.util.nlp;

public interface token {
    String crf = "8e3ac96f16e34cc88f27ccca71c5f4551614237649581token";

    String keywordExtract = "3c93f757decd4a6b9e5306527941eb931614242189892token";

    String textSuggest = "8b303b6dec5646eba6b4e7849b85a7381614242161774token";

       /*    // 得到分词后的结果
        ExtractWord extractWord = new ExtractWord();
        String segmentWord = extractWord.getSlice("visa");

        // 设定待推荐文本
        SentenceArray sentenceArray = new SentenceArray();
        sentenceArray.addElement("There are no such things as commissioners4 and heads of departments in the world");
        sentenceArray.addElement("tip for apply for a visa");
        sentenceArray.addElement("how to apply for a visa");
        sentenceArray.addElement("immigrate to ireland");
        String recommendText = sentenceArray.getSentenceArray();

       // System.out.println(recommendText);

        // 获取推荐结果
        List<String> suggestion=TextSuggest.textRecommend(segmentWord,recommendText,1);

        System.out.println(suggestion.toString());
*/

}
