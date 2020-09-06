package org.jhapy.frontend.component.cropperjs;

import com.google.gson.Gson;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.io.IOException;
import org.jhapy.frontend.component.cropperjs.model.Data;
import org.jhapy.frontend.component.cropperjs.model.DragMode;
import org.jhapy.frontend.component.cropperjs.model.GetCroppedCanvasOptions;
import org.jhapy.frontend.component.cropperjs.model.SetCanvasDataOptions;
import org.jhapy.frontend.component.cropperjs.model.SetCropBoxDataOptions;
import org.jhapy.frontend.component.cropperjs.model.ViewMode;

/**
 * @author Alexandre Clavaud.
 * @version 1.0
 * @since 04/09/2020
 */
@Route
@PageTitle("Cropper-JS-Demo")
public class MainView extends VerticalLayout {
  public MainView() throws IOException {
    add(new H2("Vaadin-JS-Croppie Demo"));

    TextArea eventLog = new TextArea("Events", "", "no event's received");
    eventLog.setWidthFull();
    eventLog.addClassName("font-size-xs");
    eventLog.setHeight("250px");
    add(eventLog);

    CropperConfiguration cropperConfiguration = new CropperConfiguration();
    cropperConfiguration.setAspectRatio(1f);
    cropperConfiguration.setViewMode(ViewMode.RESTRICT_TO_CANVAS);

    CropperJs cropperJs = new CropperJs("frontend/picture.jpg");
    cropperJs.withConfig(cropperConfiguration);
    cropperJs.addCropListener(event -> {
      eventLog.setValue(event.toString() + ", isFromClient=" + event.isFromClient() + "\n" + eventLog.getValue());
    });
    cropperJs.addCropStartListener(event -> {
      eventLog.setValue(event.toString() + ", isFromClient=" + event.isFromClient() + "\n" + eventLog.getValue());
    });
    cropperJs.addCropMoveListener(event -> {
      eventLog.setValue(event.toString() + ", isFromClient=" + event.isFromClient() + "\n" + eventLog.getValue());
    });
    cropperJs.addCropEndListener(event -> {
      eventLog.setValue(event.toString() + ", isFromClient=" + event.isFromClient() + "\n" + eventLog.getValue());
    });
    cropperJs.addReadyListener(event -> {
      eventLog.setValue(event.toString() + ", isFromClient=" + event.isFromClient() + "\n" + eventLog.getValue());
    });
    cropperJs.addZoomListener(event -> {
      eventLog.setValue(event.toString() + ", isFromClient=" + event.isFromClient() + "\n" + eventLog.getValue());
    });
    add(cropperJs);

    HorizontalLayout buttonLayoutRow1 = new HorizontalLayout();
    buttonLayoutRow1.add( new Button("Move", event -> cropperJs.setDragMode(DragMode.MOVE) ));
    buttonLayoutRow1.add( new Button("Crop", event -> cropperJs.setDragMode(DragMode.CROP) ));

    buttonLayoutRow1.add( new Button("Zoom In", event -> cropperJs.zoom(0.1f) ));
    buttonLayoutRow1.add( new Button("Zoom Out", event -> cropperJs.zoom(-0.1f) ));

    buttonLayoutRow1.add( new Button("Move Left", event -> cropperJs.move(-10f, 0f) ));
    buttonLayoutRow1.add( new Button("Move Right", event -> cropperJs.move(10f, 0f) ));
    buttonLayoutRow1.add( new Button("Move Up", event -> cropperJs.move(0f, -10f) ));
    buttonLayoutRow1.add( new Button("Move Down", event -> cropperJs.move(0f, 10f) ));

    buttonLayoutRow1.add( new Button("Rotate Left", event -> cropperJs.rotate(-45) ));
    buttonLayoutRow1.add( new Button("Rotate Right", event -> cropperJs.rotate(45) ));

    HorizontalLayout buttonLayoutRow2 = new HorizontalLayout();
    buttonLayoutRow2.add( new Button("Flip Horizontal", event -> cropperJs.scaleX(-1) ));
    buttonLayoutRow2.add( new Button("Flip Vertical", event -> cropperJs.scaleY(-1) ));

    buttonLayoutRow2.add( new Button("Crop", event -> cropperJs.crop() ));
    buttonLayoutRow2.add( new Button("Clear", event -> cropperJs.clear() ));

    buttonLayoutRow2.add( new Button("Disable", event -> cropperJs.disable() ));
    buttonLayoutRow2.add( new Button("Enable", event -> cropperJs.enable() ));

    buttonLayoutRow2.add( new Button("Reset", event -> cropperJs.reset() ));
    buttonLayoutRow2.add( new Button("Destroy", event -> cropperJs.destroy() ));

    HorizontalLayout buttonLayoutRow4 = new HorizontalLayout();
    buttonLayoutRow4.add( new Button("Get Data", event -> cropperJs.getData(o -> eventLog.setValue((new Gson()).toJson(o)) )));
    buttonLayoutRow4.add( new Button("Set Data", event -> cropperJs.setData(new Gson().fromJson(eventLog.getValue(), Data.class))));
    buttonLayoutRow4.add( new Button("Get Container Data", event -> cropperJs.getContainerData(o -> eventLog.setValue((new Gson()).toJson(o)) )));
    buttonLayoutRow4.add( new Button("Get Image Data", event -> cropperJs.getImageData( o -> eventLog.setValue((new Gson()).toJson(o)) )));
    buttonLayoutRow4.add( new Button("Get Canvas Data", event -> cropperJs.getCanvasData( o -> eventLog.setValue((new Gson()).toJson(o)) )));
    buttonLayoutRow4.add( new Button("Set Canvas Data", event -> cropperJs.setCanvasData(new Gson().fromJson(eventLog.getValue(), SetCanvasDataOptions.class))));
    buttonLayoutRow4.add( new Button("Get Crop Box Data", event -> cropperJs.getCropBoxData(o -> eventLog.setValue((new Gson()).toJson(o)) )));
    buttonLayoutRow4.add( new Button("Set Crop Box Data", event -> cropperJs.setCropBoxData(new Gson().fromJson(eventLog.getValue(), SetCropBoxDataOptions.class))));

    HorizontalLayout buttonLayoutRow5 = new HorizontalLayout();
    buttonLayoutRow5.add( new Button("Move to [0,0]", event -> cropperJs.moveTo(0,0) ));
    buttonLayoutRow5.add( new Button("Zoom to 100%", event -> cropperJs.zoomTo(1)) );
    buttonLayoutRow5.add( new Button("Rotate 180°", event -> cropperJs.rotateTo(180)));
    buttonLayoutRow5.add( new Button("Scale (-2, -1)", event -> cropperJs.scale(-2, -1 )));


    add( buttonLayoutRow1, buttonLayoutRow2,buttonLayoutRow4,buttonLayoutRow5 );

  }
}
