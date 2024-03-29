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

package org.jhapy.frontend.component.event;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jhapy.frontend.component.cropperjs.CropperJs;

/**
 * This event fires when the canvas (image wrapper) or the crop box stops to change.
 */
@Getter
@Setter
@ToString
@DomEvent("cropperjs-cropend")
@EqualsAndHashCode(callSuper = false)
public class CropEndEvent extends ComponentEvent<CropperJs> {

    private String originalEvent;
    private String action;

    public CropEndEvent(CropperJs source, boolean fromClient, @EventData("event.detail.originalEvent") String originalEvent, @EventData("event.detail.action") String action) {
        super(source, fromClient);
        this.originalEvent = originalEvent;
        this.action = action;

    }
}
