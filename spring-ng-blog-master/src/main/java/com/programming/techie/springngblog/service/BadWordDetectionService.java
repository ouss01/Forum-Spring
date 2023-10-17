package com.programming.techie.springngblog.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BadWordDetectionService {

    public String repeatString(String str, int count) {
        String repeated = "";
        for (int i = 0; i < count; i++) {
            repeated += str;
        }
        return repeated;
    }


    private static final String BAD_WORD_REPLACEMENT = "*";
    private static final List<String> BAD_WORDS = Arrays.asList("fuck", "hell", "shit","fuck you","stfu","crap","idiot","stupid","pussy","virgin","burn in hell","damn you ","damn you to hell","motherfucker","bitch","dick","cum","dumb","hoe","hooker");

    public String detectAndReplaceBadWords(String content) {
        for (String badWord : BAD_WORDS) {
            Pattern pattern = Pattern.compile("\\b" + badWord + "\\b", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                int length = end - start;
                String replacement = repeatString(BAD_WORD_REPLACEMENT, length);
                content = content.substring(0, start) + replacement + content.substring(end);
            }
        }
        return content;
    }





}