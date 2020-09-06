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

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCroppedCanvasOptions {

  /**
   * The destination width of the output canvas.
   */
  private Float width;
  /**
   * The destination height of the output canvas.
   */
  private Float height;

  /**
   * The minimum destination width of the output canvas, the default value is 0.
   */
  private Float minWidth;
  /**
   * The minimum destination height of the output canvas, the default value is 0.
   */
  private Float minHeight;

  /**
   * The maximum destination width of the output canvas, the default value is Infinity.
   */
  private Float maxWidth;

  /**
   * The maximum destination height of the output canvas, the default value is Infinity.
   */
  private Float maxHeight;

  /**
   * A color to fill any alpha values in the output canvas, the default value is transparent.
   */
  private String fillColor;

  /**
   * Set to change if images are smoothed (true, default) or not (false).
   */
  private Boolean imageSmoothingEnabled;

  /**
   * Set the quality of image smoothing, one of "low" (default), "medium", or "high".
   */
  private ImageSmoothingQuality imageSmoothingQuality;

  public String getJsonString() {
    List<String> parameters = new ArrayList<>();
    if (width != null)
      parameters.add(String.format("\"width\": %.2f", width));

    if (height != null)
      parameters.add(String.format("\"height\": %.2f", height));

    if ( minWidth != null )
      parameters.add(String.format("\"minWidth\": %.2f", minWidth));

    if ( minHeight != null )
      parameters.add(String.format("\"minHeight\": %.2f", minHeight));

    if ( maxWidth != null )
      parameters.add(String.format("\"maxWidth\": %.2f", maxWidth));

    if ( maxHeight != null )
      parameters.add(String.format("\"maxHeight\": %.2f", maxHeight));

    if ( fillColor != null )
      parameters.add(String.format("\"fillColor\": %s", fillColor));

    if ( imageSmoothingEnabled != null )
      parameters.add(String.format("\"imageSmoothingEnabled\": %s", imageSmoothingEnabled));

    if ( imageSmoothingQuality != null )
      parameters.add(String.format("\"imageSmoothingQuality\": %s", imageSmoothingQuality.getKey()));

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
