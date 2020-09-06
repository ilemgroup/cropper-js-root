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
import org.jhapy.frontend.component.cropperjs.model.Data;

/**
 * This event fires when the canvas (image wrapper) or the crop box changed.
 *
 * Notes:
 * <ul>
 * <li>When the autoCrop option is set to true, a crop event will be triggered before the ready event.</li>
 * <li>When the data option is set, another crop event will be triggered before the ready event.</li>
 * </ul>
 */
@Getter
@Setter
@ToString
@DomEvent("cropperjs-crop")
@EqualsAndHashCode(callSuper = false)
public class CropEvent extends ComponentEvent<CropperJs> {

  private Data data;

  public CropEvent(CropperJs source, boolean fromClient, @EventData("event.detail.x") String x,
      @EventData("event.detail.y") String y, @EventData("event.detail.width") String width,
      @EventData("event.detail.height") String height,
      @EventData("event.detail.rotate") String rotate,
      @EventData("event.detail.scaleX") String scaleX,
      @EventData("event.detail.scaleY") String scaleY) {
    super(source, fromClient);
    this.data = new Data(Float.parseFloat(x), Float.parseFloat(y), Float.parseFloat(width), Float.parseFloat(height), Float.parseFloat(rotate), Float.parseFloat(scaleX), Float.parseFloat(scaleY));
  }
}