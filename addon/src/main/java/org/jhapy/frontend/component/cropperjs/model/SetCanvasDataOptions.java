/*
 * Copyright 2020-2020 the original author or authors from the JHapy project.
 *
 * This file is part of the JHapy project, see https://www.jhapy.org/ for more information.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jhapy.frontend.component.cropperjs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetCanvasDataOptions {

    /**
     * The new offset left of the canvas
     */
    private Float left;
    /**
     * The new offset top of the canvas
     */
    private Float top;

    /**
     * The new width of the canvas
     */
    private Float width;
    /**
     * The new height of the canvas
     */
    private Float height;

    public String getJsonString() {
        List<String> parameters = new ArrayList<>();
        if (left != null) {
            parameters.add(String.format(Locale.ROOT, "\"left\": %.2f", left));
        }

        if (top != null) {
            parameters.add(String.format(Locale.ROOT, "\"top\": %.2f", top));
        }

        if (width != null) {
            parameters.add(String.format(Locale.ROOT, "\"width\": %.2f", width));
        }

        if (height != null) {
            parameters.add(String.format(Locale.ROOT, "\"height\": %.2f", height));
        }

        StringBuilder result = new StringBuilder("{");
        int paramSize = parameters.size();
        for (int x = 0; x < paramSize; x++) {
            result.append(parameters.get(x));
            if (x != paramSize - 1) {
                result.append(", ");
            }
        }
        result.append("}");
        return result.toString();
    }
}
