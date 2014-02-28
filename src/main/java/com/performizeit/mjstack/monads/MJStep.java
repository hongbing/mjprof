package com.performizeit.mjstack.monads;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by life on 27/2/14.
 */
public class MJStep {
    String stepName;
    List<String> stepArgs;
    String stepVal;

    public MJStep(String stepString) {

        stepArgs = new ArrayList<String>();


        Pattern p = Pattern.compile("(.*)/(.*)/");
        Matcher m = p.matcher(stepString);
        if (m.find()) {
            stepName = m.group(1);
            MJStep mjStep = new MJStep(m.group(1));
            // System.out.println((m.group(1)));
            String params = m.group(2);
            if (params.trim().length() > 0) {
                params = params.replaceAll(",,", "__DOUBLE_COMMA__xxxxxxx");
                for (String q : params.split(",")) {
                    //    System.out.println(q);
                    addStepArg(q.replaceAll("__DOUBLE_COMMA__xxxxxxx", ","));
                }
            }

        } else {
            stepName = stepString;
        }
    }

    public String getStepName() {
        return stepName;
    }

    public List<String> getStepArgs() {
        return stepArgs;
    }

    public String getStepArg(int i) {
        return stepArgs.get(i);
    }

    public void addStepArg(String arg) {
        stepArgs.add(arg);
    }



}
