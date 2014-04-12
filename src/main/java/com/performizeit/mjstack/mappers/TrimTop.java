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

package com.performizeit.mjstack.mappers;

import com.performizeit.mjstack.api.JStackMapper;
import com.performizeit.mjstack.api.Plugin;
import com.performizeit.mjstack.parser.ThreadInfo;
import com.performizeit.mjstack.parser.StackTrace;

import java.util.Arrays;
import java.util.HashMap;

@Plugin(name="keepbot",paramTypes = {int.class},
        description = "Returns at most n bottom stack frames of the stack")
public class TrimTop implements  JStackMapper {
    private final int count;

    public TrimTop(int count) {
        this.count = count;
    }

    @Override
    public ThreadInfo map(ThreadInfo stck) {
        HashMap<String,Object> mtd = stck.cloneMetaData();
        StackTrace jss = (StackTrace) mtd.get("stack");
        String[] stackFrames = jss.getStackFrames();
        if (count < stackFrames.length)   {
            String[] partial = Arrays.copyOfRange(stackFrames,stackFrames.length-count , stackFrames.length);
            jss.setStackFrames(partial);
        }

        return      new ThreadInfo(mtd);
    }

}
