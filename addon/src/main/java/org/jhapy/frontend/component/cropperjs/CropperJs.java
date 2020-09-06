package org.jhapy.frontend.component.cropperjs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.HasTheme;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.PropertyDescriptors;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.server.AbstractStreamResource;
import com.vaadin.flow.shared.Registration;
import elemental.json.JsonValue;
import java.util.Map;
import java.util.function.Consumer;
import lombok.Getter;
import org.jhapy.frontend.component.cropperjs.model.CanvasData;
import org.jhapy.frontend.component.cropperjs.model.ContainerData;
import org.jhapy.frontend.component.cropperjs.model.CropBoxData;
import org.jhapy.frontend.component.cropperjs.model.Data;
import org.jhapy.frontend.component.cropperjs.model.DragMode;
import org.jhapy.frontend.component.cropperjs.model.GetCroppedCanvasOptions;
import org.jhapy.frontend.component.cropperjs.model.ImageData;
import org.jhapy.frontend.component.cropperjs.model.Pivot;
import org.jhapy.frontend.component.cropperjs.model.SetCanvasDataOptions;
import org.jhapy.frontend.component.cropperjs.model.SetCropBoxDataOptions;
import org.jhapy.frontend.component.event.CropEndEvent;
import org.jhapy.frontend.component.event.CropEvent;
import org.jhapy.frontend.component.event.CropMoveEvent;
import org.jhapy.frontend.component.event.CropStartEvent;
import org.jhapy.frontend.component.event.ReadyEvent;
import org.jhapy.frontend.component.event.ZoomEvent;

/**
 * @author Alexandre Clavaud.
 * @version 1.0
 * @since 01/09/2020
 */
@Tag("vaadin-cropperjs")
@NpmPackage(value = "cropperjs", version = "1.5.7")
@JsModule("./org/jhapy/frontend/component/cropperjs/cropper.js")
public class CropperJs extends PolymerTemplate<CropperJsModel> implements HasSize, HasStyle,
    HasTheme {

  private static final PropertyDescriptor<String, String> srcDescriptor = PropertyDescriptors.attributeWithDefault("src", "");

  private CropperConfiguration config = new CropperConfiguration();
  @Getter
  private boolean attached = false;

  public CropperJs(AbstractStreamResource src) {
    withSrc(src);
  }

  public CropperJs(AbstractStreamResource src, boolean roundedCrop) {
    if (roundedCrop) {
      getElement().setAttribute( "theme","round-crop");
    }
    withSrc(src);
  }

  public CropperJs(String src) {
    withSrc(src);
  }

  public CropperJs withConfig(CropperConfiguration config) {
    this.config = config;
    return this;
  }

  public void setCropperFaceRound( boolean isRound ) {
    if ( isRound )
      addThemeName("round-crop");
    else
      removeThemeName("round-crop");
  }

  public CropperJs withSrc(AbstractStreamResource src) {
    getElement().setAttribute("src", src);
    return getAndUpdateConfig();
  }

  public Registration addCropListener(
      ComponentEventListener<CropEvent> listener) {
    return addListener(CropEvent.class, listener);
  }

  public Registration addCropStartListener(
      ComponentEventListener<CropStartEvent> listener) {
    return addListener(CropStartEvent.class, listener);
  }

  public Registration addCropEndListener(
      ComponentEventListener<CropEndEvent> listener) {
    return addListener(CropEndEvent.class, listener);
  }

  public Registration addCropMoveListener(
      ComponentEventListener<CropMoveEvent> listener) {
    return addListener(CropMoveEvent.class, listener);
  }

  public Registration addReadyListener(
      ComponentEventListener<ReadyEvent> listener) {
    return addListener(ReadyEvent.class, listener);
  }

  public Registration addZoomListener(
      ComponentEventListener<ZoomEvent> listener) {
    return addListener(ZoomEvent.class, listener);
  }

  protected CropperConfiguration getConfigInitialized() {
    if (this.config == null) {
      this.config = new CropperConfiguration();
    }
    return this.config;
  }

  public CropperJs withSrc(String src) {
    set(srcDescriptor, src);
    return getAndUpdateConfig();
  }

  protected CropperJs getAndUpdateConfig() {
    if (isAttached()) {
      rebuildImage();
    }
    return this;
  }

  protected void updateConfig() {
    getModel().setCroppieOptions(config.getJsonString());
  }

  public void rebuildImage() {
    getElement().callJsFunction("updateImage");
  }

  @Override
  protected void onAttach(AttachEvent attachEvent) {
    super.onAttach(attachEvent);
    attached = true;
    updateConfig();
  }

  @Override
  protected void onDetach(DetachEvent detachEvent) {
    super.onDetach(detachEvent);
    attached = false;
  }

  /**
   * Clear the crop box.
   */
  public void clear() {
    getElement().callJsFunction("clear");
  }

  /**
   * Show the crop box manually.
   */
  public void crop() {
    getElement().callJsFunction("crop");
  }

  /**
   * Destroy the cropper and remove the instance from the image.
   */
  public void destroy() {
    getElement().callJsFunction("destroy");
  }

  /**
   * Disable (freeze) the cropper.
   */
  public void disable() {
    getElement().callJsFunction("disable");
  }

  /**
   * Enable (unfreeze) the cropper.
   */
  public void enable() {
    getElement().callJsFunction("enable");
  }

  /**
   * Output the image position, size, and other related data.
   */
  public void getCanvasData(Consumer<CanvasData> callback) {
    getElement().callJsFunction("getCanvasData").then(JsonValue.class, result -> {
      callback.accept(new Gson().fromJson(result.toJson(), CanvasData.class));
    });
  }

  /**
   * Output the container size data.
   */
  public void getContainerData(Consumer<ContainerData> callback) {
    getElement().callJsFunction("getContainerData").then(JsonValue.class, result -> {
      callback.accept(new Gson().fromJson(result.toJson(), ContainerData.class));
    });
  }

  /**
   * Output the crop box position and size data.
   */
  public void getCropBoxData(Consumer<CropBoxData> callback) {
    getElement().callJsFunction("getCropBoxData").then(JsonValue.class, result -> {
      callback.accept(new Gson().fromJson(result.toJson(), CropBoxData.class));
    });
  }

  /**
   * Output the final cropped area position and size data (base on the natural size of the original
   * image).
   */
  public void getData(Consumer<Data> callback) {
    getElement().callJsFunction("getData").then(JsonValue.class, result -> {
      callback.accept(new Gson().fromJson(result.toJson(), Data.class));
    });
  }

  /**
   * Output the final cropped area position and size data (base on the natural size of the original
   * image).
   *
   * @param rounded Set true to get rounded values.
   */
  public void getData(Boolean rounded, Consumer<Data> callback) {
    getElement().callJsFunction("getData", rounded).then(JsonValue.class, result -> {
      callback.accept(new Gson().fromJson(result.toJson(), Data.class));
    });
  }

  public void getImageData(Consumer<ImageData> callback) {
    getElement().callJsFunction("getImageData").then(JsonValue.class, result -> {
      callback.accept(new Gson().fromJson(result.toJson(), ImageData.class));
    });
  }

  /**
   * Move the canvas (image wrapper) with relative offsets.
   *
   * @param offsetX Moving size (px) in the horizontal direction.
   * @param offsetY Moving size (px) in the vertical direction.
   */
  public void move(float offsetX, float offsetY) {
    getElement().callJsFunction("move", Float.toString(offsetX), Float.toString(offsetY));
  }

  /**
   * Move the canvas (image wrapper) to an absolute point.
   *
   * @param x The left value of the canvas
   * @param y The top value of the canvas
   */
  public void moveTo(float x, float y) {
    getElement().callJsFunction("moveTo", Float.toString(x), Float.toString(y));
  }

  /**
   * Replace the image's src and rebuild the cropper.
   *
   * @param url A new image url.
   */
  public void replace(String url) {
    getElement().callJsFunction("replace", url);
  }

  /**
   * Replace the image's src and rebuild the cropper.
   *
   * @param url A new image url.
   * @param hasSameSize If the new image has the same size as the old one, then it will not
   * rebuild the cropper and only update the URLs of all related images. This can be used for
   * applying filters.
   */
  public void replace(String url, boolean hasSameSize) {
    getElement().callJsFunction("replace", url, hasSameSize);
  }

  /**
   * Reset the image and crop box to their initial states.
   */
  public void reset() {
    getElement().callJsFunction("reset");
  }

  /**
   * Rotate the image with a relative degree.
   *
   * @param degree Rotate right: requires a positive number (degree > 0), Rotate left: requires a
   * negative number (degree < 0)
   */
  public void rotate(float degree) {
    getElement().callJsFunction("rotate", Float.toString(degree));
  }

  /**
   * Rotate the image to an absolute degree.
   *
   * @param degree Rotate right: requires a positive number (degree > 0), Rotate left: requires a
   * negative number (degree < 0)
   */
  public void rotateTo(float degree) {
    getElement().callJsFunction("rotateTo", Float.toString(degree));
  }

  /**
   * Scale the image.
   *
   * @param scaleX The scaling factor to apply to the abscissa of the image. When equal to 1 it does
   * nothing.
   * @param scaleY The scaling factor to apply on the ordinate of the image. When equal to 1 it does
   * nothing.
   */
  public void scale(float scaleX, float scaleY) {
    getElement().callJsFunction("scale", Float.toString(scaleX), Float.toString(scaleY));
  }

  /**
   * Scale the abscissa of the image.
   *
   * @param scaleX The scaling factor to apply to the abscissa of the image. When equal to 1 it does
   * nothing.
   */
  public void scaleX(float scaleX) {
    getElement().callJsFunction("scaleX", Float.toString(scaleX));
  }

  /**
   * Scale the ordinate of the image.
   *
   * @param scaleY The scaling factor to apply on the ordinate of the image. When equal to 1 it does
   * nothing.
   */
  public void scaleY(float scaleY) {
    getElement().callJsFunction("scaleY", Float.toString(scaleY));
  }

  /**
   * Change the aspect ratio of the crop box.
   *
   * @param aspectRatio Requires a positive number.
   */
  public void setAspectRatio(float aspectRatio) {
    getElement().callJsFunction("setAspectRatio", aspectRatio);
  }

  /**
   * Change the canvas (image wrapper) position and size with new data.
   *
   * @param data The new position and size of the canvas
   */
  public void setCanvasData(SetCanvasDataOptions data) {
    getElement().callJsFunction("setCanvasData", data.getJsonString());
  }

  public void setCanvasData(String jsonData) {
    getElement().callJsFunction("setCanvasData", jsonData);
  }

  /**
   * Change the crop box position and size with new data.
   *
   * @param data The new position and size of the crop box
   */
  public void setCropBoxData(SetCropBoxDataOptions data) {
    getElement().callJsFunction("setCropBoxData", data.getJsonString());
  }

  public void setCropBoxData(String jsonData) {
    getElement().callJsFunction("setCropBoxData", jsonData);
  }

  /**
   * Change the cropped area position and size with new data (base on the original image).
   *
   * This method only available when the value of the viewMode option is greater than or equal to
   * 1.
   */
  public void setData(Data data) {
    getElement().callJsFunction("setData", data.getJsonString());
  }

  public void setData(String jsonData) {
    getElement().callJsFunction("setData", jsonData);
  }

  /**
   * Change the drag mode.
   *
   * Tips: You can toggle the "crop" and "move" mode by double click on the cropper.
   *
   * @param dragMode Drag mode ('none', 'crop', 'move')
   */
  public void setDragMode(DragMode dragMode) {
    getElement().callJsFunction("setDragMode", dragMode.getKey());
  }

  /**
   * Zoom the canvas (image wrapper) with a relative ratio.
   *
   * @param ratio Zoom in: requires a positive number (ratio > 0), Zoom out: requires a negative
   * number (ratio < 0)
   */
  public void zoom(float ratio) {
    getElement().callJsFunction("zoom", Float.toString(ratio));
  }

  /**
   * Zoom the canvas (image wrapper) to an absolute ratio.
   *
   * @param ratio Requires a positive number (ratio > 0)
   */
  public void zoomTo(float ratio) {
    getElement().callJsFunction("zoomTo", Float.toString(ratio));
  }

  /**
   * Zoom the canvas (image wrapper) to an absolute ratio.
   *
   * @param ratio Requires a positive number (ratio > 0)
   * @param pivot The coordinate of the center point for zooming, base on the top left corner of the
   * cropper container.
   */
  public void zoomTo(float ratio, Pivot pivot) {
    getElement().callJsFunction("zoomTo", Float.toString(ratio), pivot.getJsonString());
  }

  public void resize(String height, String width) {
    int _height = Integer.parseInt(height.substring(0, height.indexOf("px")).trim());
    int _width = Integer.parseInt(width.substring(0, width.indexOf("px")).trim());

    getElement().callJsFunction("resizeImage", _height - 250, _width - 20);
  }

  public void resize(int height, int width) {
    getElement().callJsFunction("resizeImage", height - 250, width - 20);
  }
}
