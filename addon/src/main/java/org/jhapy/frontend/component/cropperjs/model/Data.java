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
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Data {

    /**
     * The offset left of the cropped area
     */
    private Float x;

    /**
     * The offset top of the cropped area
     */
    private Float y;

    /**
     * The width of the cropped area
     */
    private Float width;

    /**
     * The height of the cropped area
     */
    private Float height;

    /**
     * The rotated degrees of the image
     */
    private Float rotate;

    /**
     * The scaling factor to apply on the abscissa of the image
     */
    private Float scaleX;

    /**
     * The scaling factor to apply on the ordinate of the image
     */
    private Float scaleY;

    public String getJsonString() {
        List<String> parameters = new ArrayList<>();
        if (x != null) {
            parameters.add(String.format(Locale.ROOT, "\"x\": %.2f", x));
        }

        if (y != null) {
            parameters.add(String.format(Locale.ROOT, "\"y\": %.2f", y));
        }

        if (width != null) {
            parameters.add(String.format(Locale.ROOT, "\"width\": %.2f", width));
        }

        if (height != null) {
            parameters.add(String.format(Locale.ROOT, "\"height\": %.2f", height));
        }

        if (rotate != null) {
            parameters.add(String.format(Locale.ROOT, "\"rotate\": %.2f", rotate));
        }

        if (scaleX != null) {
            parameters.add(String.format(Locale.ROOT, "\"scaleX\": %.2f", scaleX));
        }

        if (scaleY != null) {
            parameters.add(String.format(Locale.ROOT, "\"fillColor\": %.2f", scaleY));
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
