package com.systex.support.pebble;

import java.util.List;
import java.util.Map;
import com.systex.support.Base64Support;
import com.systex.support.Lists;
import io.pebbletemplates.pebble.error.PebbleException;
import io.pebbletemplates.pebble.extension.Filter;
import io.pebbletemplates.pebble.template.EvaluationContext;
import io.pebbletemplates.pebble.template.PebbleTemplate;

public class Base64DecodeFilter implements Filter {

    public static final String FILTER_NAME = "base64_decode";

    @Override
    public Object apply(Object input,
                        Map<String, Object> args,
                        PebbleTemplate self,
                        EvaluationContext context,
                        int lineNumber) throws PebbleException {
        if (input == null)
            return null;

        String base64Str = String.class.cast(input);
        if (Base64Support.check(base64Str)) {
            return Base64Support.decode(base64Str);
        } else {
            return base64Str;
        }

    }

    @Override
    public List<String> getArgumentNames() {
        return Lists.newArrayList();
    }

}
