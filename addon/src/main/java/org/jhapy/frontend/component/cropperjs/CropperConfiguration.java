package org.jhapy.frontend.component.cropperjs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.jhapy.frontend.component.cropperjs.model.Data;
import org.jhapy.frontend.component.cropperjs.model.DragMode;
import org.jhapy.frontend.component.cropperjs.model.ViewMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CropperConfiguration {

  /**
   * Define the fixed aspect ratio of the crop box. By default, the crop box is free ratio.
   * <p>
   * Default NaN
   */
  private Float aspectRatio;

  /**
   * Enable to crop the image automatically when initialized.
   * <p>
   * Default true
   */
    private Boolean autoCrop;

  /**
   * A number between 0 and 1. Define the automatic cropping area size (percentage).
   * <p>
   * Default 0.8 (80% of the image)
   */
    private Float autoCropArea;

  /**
   * Show the grid background of the container.
   * <p>
   * Default true
   */
    private Boolean background;

  /**
   * Show the center indicator above the crop box.
   * <p>
   * Default true
   */
    private Boolean center;

  /**
   * Check if the current image is a cross-origin image.
   *
   * If it is, when clone the image, a crossOrigin attribute will be added to the cloned image
   * element and a timestamp will be added to the src attribute to reload the source image to avoid
   * browser cache error.
   *
   * By adding crossOrigin attribute to image element will stop adding timestamp to image URL and
   * stop reload of image, but the request (XMLHttpRequest) to read the image data for orientation
   * checking will require a timestamp to bust cache to avoid browser cache error now, you can set
   * the checkOrientation option to false to cancel this request.
   *
   * If the value of the image's crossOrigin attribute is "use-credentials", then the
   * withCredentials attribute will set to true when read the image data by XMLHttpRequest.
   * <p>
   * Default true
   */
    private Boolean checkCrossOrigin;

  /**
   * Check the current image's Exif Orientation information. Note that only a JPEG image may
   * contains Exif Orientation information.
   *
   * More exactly, read the Orientation value for rotating or flipping the image, and then override
   * the Orientation value with 1 (the default value) to avoid some issues (1, 2) on iOS devices.
   *
   * Requires to set both the rotatable and scalable options to true at the same time.
   *
   * Note: Don't trust this all the time as some JPG images have incorrect (not standard)
   * Orientation values.
   * <p>
   * Default true
   */
    private Boolean checkOrientation;

  /**
   * Enable to move the crop box by dragging.
   * <p>
   * Default true
   */
    private Boolean cropBoxMovable;

  /**
   * Enable to resize the crop box by dragging.
   * <p>
   * Default true
   */
    private Boolean cropBoxResizable;

  /**
   * The previous cropped data if you had stored, will be passed to setData method automatically
   * when initialized. Only available when the autoCrop option is set to true.
   * <p>
   * Default null
   */
  private Data data;

  /**
   * Define the dragging mode of the cropper.
   * <p>
   * Default 'crop'
   */
    private DragMode dragMode;

  /**
   * Show the dashed lines above the crop box.
   * <p>
   * Default true
   */
    private Boolean guides;

  /**
   * Show the white modal above the crop box (highlight the crop box).
   * <p>
   * Default true
   */
    private Boolean highlight;

  /**
   * Define the initial aspect ratio of the crop box. By default, it is the same as the aspect ratio
   * of the canvas (image wrapper)
   * <p>
   * Default NaN
   */
  private Float initialAspectRatio;

  /**
   * The minimum height of the canvas (image wrapper).
   * <p>
   * Default 0
   */
    private Float minCanvasHeight;

  /**
   * The minimum width of the canvas (image wrapper).
   * <p>
   * Default 0
   */
    private Float minCanvasWidth;

  /**
   * The minimum height of the container.
   * <p>
   * Default 100
   */
    private Float minContainerHeight;

  /**
   * The minimum width of the container.
   * <p>
   * Default 200
   */
    private Float minContainerWidth;

  /**
   * The minimum height of the crop box.
   *
   * Note: This size is relative to the page, not the image.
   * <p>
   * Default 0
   */
    private Float minCropBoxHeight;

  /**
   * The minimum width of the crop box.
   *
   * Note: This size is relative to the page, not the image.
   * <p>
   * Default 0
   */
    private Float minCropBoxWidth;

  /**
   * Show the black modal above the image and under the crop box.
   * <p>
   * Default true
   */
    private Boolean modal;

  /**
   * Enable to move the image.
   * <p>
   * Default true
   */
    private Boolean movable;

  /**
   * Re-render the cropper when resizing the window.
   * <p>
   * Default true
   */
    private Boolean responsive;

  /**
   * Restore the cropped area after resizing the window.
   * <p>
   * Default true
   */
    private Boolean restore;

  /**
   * Enable to rotate the image.
   * <p>
   * Default true
   */
    private Boolean rotatable;

  /**
   * Enable to scale the image.
   * <p>
   * Default true
   */
    private Boolean scalable;

  /**
   * Enable to toggle drag mode between "crop" and "move" when clicking twice on the cropper.
   * <p>
   * Default true
   */
    private Boolean toggleDragModeOnDblclick;

  /**
   * Define the view mode of the cropper. If you set viewMode to 0, the crop box can extend outside
   * the canvas, while a value of 1, 2 or 3 will restrict the crop box to the size of the canvas. A
   * viewMode of 2 or 3 will additionally restrict the canvas to the container. Note that if the
   * proportions of the canvas and the container are the same, there is no difference between 2 and
   * 3.
   * <p>
   * Default 0 (No restrictions)
   */
    private ViewMode viewMode;

  /**
   * Define zoom ratio when zooming the image by wheeling mouse.
   * <p>
   * Default 0.1
   */
    private Float wheelZoomRatio;

  /**
   * Enable to zoom the image by dragging touch.
   * <p>
   * Default true
   */
    private Boolean zoomOnTouch;

  /**
   * Enable to zoom the image by wheeling mouse.
   * <p>
   * Default true
   */
    private Boolean zoomOnWheel;

  /**
   * Enable to zoom the image.
   * <p>
   * Default true
   */
    private Boolean zoomable;

    public String getJsonString() {
    List<String> parameters = new ArrayList<>();
    if (data != null) parameters.add(String.format(Locale.ROOT, "\"data\": %s", data.getJsonString()));

    if (aspectRatio != null) parameters.add(String.format(Locale.ROOT, "\"aspectRatio\": %.2f", aspectRatio));

    if ( autoCrop != null ) parameters.add(String.format(Locale.ROOT, "\"autoCrop\": %s", autoCrop));

    if ( autoCropArea != null ) parameters.add(String.format(Locale.ROOT, "\"autoCropArea\": %.2f", autoCropArea));

    if ( background != null ) parameters.add(String.format(Locale.ROOT, "\"background\": %s", background));

    if ( center != null ) parameters.add(String.format(Locale.ROOT, "\"center\": %s", center));

    if ( checkCrossOrigin != null )
      parameters.add(String.format(Locale.ROOT, "\"checkCrossOrigin\": %s", checkCrossOrigin));

    if ( checkOrientation != null )
      parameters.add(String.format(Locale.ROOT, "\"checkOrientation\": %s", checkOrientation));

    if ( cropBoxMovable != null ) parameters.add(String.format(Locale.ROOT, "\"cropBoxMovable\": %s", cropBoxMovable));

    if ( cropBoxResizable != null )
      parameters.add(String.format(Locale.ROOT, "\"cropBoxResizable\": %s", cropBoxResizable));

    if ( cropBoxMovable != null ) parameters.add(String.format(Locale.ROOT, "\"cropBoxMovable\": %s", cropBoxMovable));

    if ( dragMode != null ) parameters.add(String.format(Locale.ROOT, "\"dragMode\": \"%s\"", dragMode.getKey()));

    if ( guides != null ) parameters.add(String.format(Locale.ROOT, "\"guides\": %s", guides));

    if ( highlight != null ) parameters.add(String.format(Locale.ROOT, "\"highlight\": %s", highlight));

    if (initialAspectRatio != null)
      parameters.add(String.format(Locale.ROOT, "\"initialAspectRatio\": %.2f", initialAspectRatio));

    if ( minCanvasHeight != null )
      parameters.add(String.format(Locale.ROOT, "\"minCanvasHeight\": %.2f", minCanvasHeight));

    if ( minCanvasWidth != null )
      parameters.add(String.format(Locale.ROOT, "\"minCanvasWidth\": %.2f", minCanvasWidth));

    if ( minContainerHeight != null )
      parameters.add(String.format(Locale.ROOT, "\"minContainerHeight\": %.2f", minContainerHeight));

    if ( minContainerWidth != null )
      parameters.add(String.format(Locale.ROOT, "\"minContainerWidth\": %.2f", minContainerWidth));

    if ( minCropBoxHeight != null )
      parameters.add(String.format(Locale.ROOT, "\"minCropBoxHeight\": %.2f", minCropBoxHeight));

    if ( minCropBoxWidth != null )
      parameters.add(String.format(Locale.ROOT, "\"minCropBoxWidth\": %.2f", minCropBoxWidth));

    if ( modal != null ) parameters.add(String.format(Locale.ROOT, "\"modal\": %s", modal));

    if ( movable != null ) parameters.add(String.format(Locale.ROOT, "\"movable\": %s", movable));

    if ( responsive != null ) parameters.add(String.format(Locale.ROOT, "\"responsive\": %s", responsive));

    if ( restore != null ) parameters.add(String.format(Locale.ROOT, "\"restore\": %s", restore));

    if ( rotatable != null ) parameters.add(String.format(Locale.ROOT, "\"rotatable\": %s", rotatable));

    if ( scalable != null ) parameters.add(String.format(Locale.ROOT, "\"scalable\": %s", scalable));

    if ( toggleDragModeOnDblclick != null )
      parameters.add(String.format(Locale.ROOT, "\"toggleDragModeOnDblclick\": %s", toggleDragModeOnDblclick));

    if ( viewMode != null ) parameters.add(String.format(Locale.ROOT, "\"viewMode\": %d", viewMode.getKey()));

    if ( wheelZoomRatio != null )
      parameters.add(String.format(Locale.ROOT, "\"wheelZoomRatio\": %.2f", wheelZoomRatio));

    if ( zoomOnTouch != null ) parameters.add(String.format(Locale.ROOT, "\"zoomOnTouch\": %s", zoomOnTouch));

    if ( zoomOnWheel != null ) parameters.add(String.format(Locale.ROOT, "\"zoomOnWheel\": %s", zoomOnWheel));

    if ( zoomable != null ) parameters.add(String.format(Locale.ROOT, "\"zoomable\": %s", zoomable));

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
