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

public enum ViewMode {

    /**
     * no restrictions
     */
    NO_RESTRICTION, /*
     * restrict the crop box to not exceed the size of the canvas.
     */
    RESTRICT_TO_CANVAS,
    /**
     * restrict the minimum canvas size to fit within the container. If the proportions of the canvas
     * and the container differ, the minimum canvas will be surrounded by extra space in one of the
     * dimensions.
     */
    RESTRICT_MIN_TO_FIT_WITHiN_CONTAINER,
    /**
     * restrict the minimum canvas size to fill fit the container. If the proportions of the canvas
     * and the container are different, the container will not be able to fit the whole canvas in one
     * of the dimensions.
     */
    RESTRICT_MIN_TO_FILL_FIT_CONTAINER;

    public static ViewMode fromInt(Integer key) {
        return key == null ? null : ViewMode.values()[key];
    }

    public Integer getKey() {
        return ordinal();
    }

}
