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
 * This event fires when a cropper instance starts to zoom in or zoom out its canvas (image
 * wrapper).
 */
@Getter
@Setter
@ToString
@DomEvent("cropperjs-zoom")
@EqualsAndHashCode(callSuper = false)
public class ZoomEvent extends ComponentEvent<CropperJs> {

  /**
   * Options: wheel, touchmove.
   */
  private String originalEvent;

  /**
   * The old (current) ratio of the canvas
   */
  private float oldRatio;

  /**
   * The new (next) ratio of the canvas (canvasData.width / canvasData.naturalWidth)
   */
  private float ratio;

  public ZoomEvent(CropperJs source, boolean fromClient,
      @EventData("event.detail.originalEvent") String originalEvent,
      @EventData("event.detail.oldRatio") String oldRatio,
      @EventData("event.detail.ratio") String ratio) {
    super(source, fromClient);
    this.originalEvent = originalEvent;
    this.oldRatio = Float.parseFloat(oldRatio);
    this.ratio = Float.parseFloat(ratio);;
  }
}
