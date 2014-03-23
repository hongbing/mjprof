/*
       This file is part of mjstack.

        mjstack is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.

        mjstack is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.performizeit.mjstack.monads;

import java.util.EnumSet;
import java.util.HashMap;

/**
 * Created by life on 28/2/14.
 */
public enum StepProps {
    CONTAINS(StepType.FILTER, 2),
    NOT_CONTAINS(StepType.FILTER, 2),
    ELIMINATE(StepType.MAPPER, 1),
    SORT(StepType.MAPPER, 1),
    SORT_DESC("sortd", StepType.MAPPER, 1),
    KEEP_TOP("keeptop", StepType.MAPPER, 1),
    KEEP_BOT("keepbot", StepType.MAPPER, 1),
    STACK_ELIM("stackelim", StepType.MAPPER, 1),
    STACK_KEEP("stackkeep", StepType.MAPPER, 1),
    TRIM_BELOW("trimbelow", StepType.MAPPER, 1),
    GROUP(StepType.MAPPER, 1),
    NO_OP("nop", StepType.MAPPER, 0),
    HELP(StepType.TERMINAL, 0),
    COUNT(StepType.TERMINAL, 0),
    LIST(StepType.TERMINAL, 0);
    static enum StepType {
        TERMINAL,
        FILTER,
        MAPPER
    }

    public static boolean stepValid(MJStep a) {
        StepProps pr = stepRepo.get(a.getStepName());
        return pr != null && a.stepArgs.size() == pr.argNum;
    }

    private String token;
    private StepType stepType;
    private int argNum;

    private static HashMap<String,StepProps> stepRepo = new HashMap<String, StepProps>();
    StepProps(String name, StepType type, int argNum) {
        this.token = name == null ? toString().toLowerCase() : name;
        this.stepType = type;
        this.argNum = argNum;
    }
    StepProps(StepType type, int argNum) {
        this(null, type, argNum);
    }
    public String getToken() { return token; }
    static {
        for (StepProps stepProps : EnumSet.allOf(StepProps.class)) {
            stepRepo.put(stepProps.token, stepProps);
        }
    }
}
