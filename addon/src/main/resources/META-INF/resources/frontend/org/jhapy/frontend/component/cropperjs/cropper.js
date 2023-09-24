import {LitElement, css, html} from 'lit';

import Cropper from "cropperjs";

class CopperJs extends LitElement {
  static get styles() {
    return [
      css`
        .cropper-container {
          direction: ltr;
          font-size: 0;
          line-height: 0;
          position: relative;
          -ms-touch-action: none;
          touch-action: none;
          -webkit-user-select: none;
          -moz-user-select: none;
          -ms-user-select: none;
          user-select: none;
        }

        .cropper-container img {
          display: block;
          height: 100%;
          image-orientation: 0deg;
          max-height: none !important;
          max-width: none !important;
          min-height: 0 !important;
          min-width: 0 !important;
          width: 100%;
        }

        .cropper-wrap-box,
        .cropper-canvas,
        .cropper-drag-box,
        .cropper-crop-box,
        .cropper-modal {
          bottom: 0;
          left: 0;
          position: absolute;
          right: 0;
          top: 0;
        }

        .cropper-wrap-box,
        .cropper-canvas {
          overflow: hidden;
        }

        .cropper-drag-box {
          background-color: #fff;
          opacity: 0;
        }

        .cropper-modal {
          background-color: #000;
          opacity: 0.5;
        }

        .cropper-view-box {
          display: block;
          height: 100%;
          outline: 1px solid #39f;
          outline-color: rgba(51, 153, 255, 0.75);
          overflow: hidden;
          width: 100%;
        }

        .cropper-dashed {
          border: 0 dashed #eee;
          display: block;
          opacity: 0.5;
          position: absolute;
        }

        .cropper-dashed.dashed-h {
          border-bottom-width: 1px;
          border-top-width: 1px;
          height: calc(100% / 3);
          left: 0;
          top: calc(100% / 3);
          width: 100%;
        }

        .cropper-dashed.dashed-v {
          border-left-width: 1px;
          border-right-width: 1px;
          height: 100%;
          left: calc(100% / 3);
          top: 0;
          width: calc(100% / 3);
        }

        .cropper-center {
          display: block;
          height: 0;
          left: 50%;
          opacity: 0.75;
          position: absolute;
          top: 50%;
          width: 0;
        }

        .cropper-center::before,
        .cropper-center::after {
          background-color: #eee;
          content: " ";
          display: block;
          position: absolute;
        }

        .cropper-center::before {
          height: 1px;
          left: -3px;
          top: 0;
          width: 7px;
        }

        .cropper-center::after {
          height: 7px;
          left: 0;
          top: -3px;
          width: 1px;
        }

        .cropper-face,
        .cropper-line,
        .cropper-point {
          display: block;
          height: 100%;
          opacity: 0.1;
          position: absolute;
          width: 100%;
        }

        .cropper-face {
          background-color: #fff;
          left: 0;
          top: 0;
        }

        .cropper-line {
          background-color: #39f;
        }

        .cropper-line.line-e {
          cursor: ew-resize;
          right: -3px;
          top: 0;
          width: 5px;
        }

        .cropper-line.line-n {
          cursor: ns-resize;
          height: 5px;
          left: 0;
          top: -3px;
        }

        .cropper-line.line-w {
          cursor: ew-resize;
          left: -3px;
          top: 0;
          width: 5px;
        }

        .cropper-line.line-s {
          bottom: -3px;
          cursor: ns-resize;
          height: 5px;
          left: 0;
        }

        .cropper-point {
          background-color: #39f;
          height: 5px;
          opacity: 0.75;
          width: 5px;
        }

        .cropper-point.point-e {
          cursor: ew-resize;
          margin-top: -3px;
          right: -3px;
          top: 50%;
        }

        .cropper-point.point-n {
          cursor: ns-resize;
          left: 50%;
          margin-left: -3px;
          top: -3px;
        }

        .cropper-point.point-w {
          cursor: ew-resize;
          left: -3px;
          margin-top: -3px;
          top: 50%;
        }

        .cropper-point.point-s {
          bottom: -3px;
          cursor: s-resize;
          left: 50%;
          margin-left: -3px;
        }

        .cropper-point.point-ne {
          cursor: nesw-resize;
          right: -3px;
          top: -3px;
        }

        .cropper-point.point-nw {
          cursor: nwse-resize;
          left: -3px;
          top: -3px;
        }

        .cropper-point.point-sw {
          bottom: -3px;
          cursor: nesw-resize;
          left: -3px;
        }

        .cropper-point.point-se {
          bottom: -3px;
          cursor: nwse-resize;
          height: 20px;
          opacity: 1;
          right: -3px;
          width: 20px;
        }

        @media (min-width: 768px) {
          .cropper-point.point-se {
            height: 15px;
            width: 15px;
          }
        }

        @media (min-width: 992px) {
          .cropper-point.point-se {
            height: 10px;
            width: 10px;
          }
        }

        @media (min-width: 1200px) {
          .cropper-point.point-se {
            height: 5px;
            opacity: 0.75;
            width: 5px;
          }
        }

        .cropper-point.point-se::before {
          background-color: #39f;
          bottom: -50%;
          content: " ";
          display: block;
          height: 200%;
          opacity: 0;
          position: absolute;
          right: -50%;
          width: 200%;
        }

        .cropper-invisible {
          opacity: 0;
        }

        .cropper-bg {
          background-image: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQAQMAAAAlPW0iAAAAA3NCSVQICAjb4U/gAAAABlBMVEXMzMz////TjRV2AAAACXBIWXMAAArrAAAK6wGCiw1aAAAAHHRFWHRTb2Z0d2FyZQBBZG9iZSBGaXJld29ya3MgQ1M26LyyjAAAABFJREFUCJlj+M/AgBVhF/0PAH6/D/HkDxOGAAAAAElFTkSuQmCC");
        }

        .cropper-hide {
          display: block;
          height: 0;
          position: absolute;
          width: 0;
        }

        .cropper-hidden {
          display: none !important;
        }

        .cropper-move {
          cursor: move;
        }

        .cropper-crop {
          cursor: crosshair;
        }

        .cropper-disabled .cropper-drag-box,
        .cropper-disabled .cropper-face,
        .cropper-disabled .cropper-line,
        .cropper-disabled .cropper-point {
          cursor: not-allowed;
        }

        :host([theme~="round-crop"]) .cropper-view-box,
        :host([theme~="round-crop"]) .cropper-face {
          border-radius: 50%;
        }

        .vaadin-cropper-container img {
          display: block;
          /* This rule is very important, please don't ignore this */
          max-width: 100%;
        }
      `,
    ];
  }
  render() {
    return html`
      <div id="container" class="vaadin-cropper-container">
        <img id="image" .src="${this.src}" />
      </div>
    `;
  }


  static get properties() {
    return {
      src: {
        type: String,
        reflect: true,
      },
      croppieOptions: {
        type: String,
      },
    };
  }

  static get is() {
    return "vaadin-cropperjs";
  }

  constructor() {
    super();
    this.initTimerId = void 0;
  }

  clear() {
    this.croppie.clear();
  }

  crop() {
    this.croppie.crop();
  }

  destroy() {
    this.croppie.destroy();
  }

  disable() {
    this.croppie.disable();
  }

  enable() {
    this.croppie.enable();
  }

  getCanvasData() {
    return this.croppie.getCanvasData();
  }

  getContainerData() {
    return this.croppie.getContainerData();
  }

  getCropBoxData() {
    return this.croppie.getCropBoxData();
  }

  getCroppedCanvas(options) {
    return this.croppie.getCroppedCanvas(options);
  }

  getData(rounded) {
    return this.croppie.getData(rounded);
  }

  getImageData() {
    return this.croppie.getImageData();
  }

  move(offsetX, offsetY) {
    this.croppie.move(offsetX, offsetY);
  }

  moveTo(x, y) {
    this.croppie.moveTo(x, y);
  }

  replace(url) {
    this.croppie.replace(url);
  }

  replace(url, hasSameSize) {
    this.croppie.replace(url, hasSameSize);
  }

  reset() {
    this.croppie.reset();
  }

  rotate(degree) {
    this.croppie.rotate(degree);
  }

  rotateTo(degree) {
    this.croppie.rotateTo(degree);
  }

  scale(scaleX, scaleY) {
    this.croppie.scale(scaleX, scaleY);
  }

  scaleX(scaleX) {
    this.croppie.scaleX(scaleX);
  }

  scaleY(scaleY) {
    this.croppie.scaleY(scaleY);
  }

  setAspectRatio(aspectRatio) {
    this.croppie.setAspectRatio(aspectRatio);
  }

  setCanvasData(data) {
    this.croppie.setCanvasData(JSON.parse(data));
  }

  setCropBoxData(data) {
    this.croppie.setCropBoxData(JSON.parse(data));
  }

  setData(data) {
    this.croppie.setData(JSON.parse(data));
  }

  setDragMode(dragMode) {
    this.croppie.setDragMode(dragMode);
  }

  zoom(ratio) {
    this.croppie.zoom(ratio);
  }

  zoomTo(ratio, pivot) {
    this.croppie.zoomTo(ratio, pivot);
  }

  resizeImage(height, width) {
    console.log("resizeImage() :: " + height + "px / " + width + "px");
    var ctx = this.shadowRoot.querySelector("#image");
    console.log("resizeImage() :: Image src = " + ctx.getAttribute("src"));
    ctx.setAttribute("width", width + "px");
    ctx.setAttribute("height", height + "px");
    this.croppie.destroy();
    this._initCroppie();
  }

  _croppieOptionsChanged(newValue) {
    console.log("_croppieOptionsChanged() :: -> " + newValue);
    var me = this;
    this.config = JSON.parse(newValue);
    this.config["crop"] = function (event) {
      var newEvent = new CustomEvent("cropperjs-crop", event);
      me.dispatchEvent(newEvent);
    };
    this.config["crop"] = function (event) {
      var newEvent = new CustomEvent("cropperjs-crop", event);
      me.dispatchEvent(newEvent);
    };
    this.config["cropstart"] = function (event) {
      var newEvent = new CustomEvent("cropperjs-cropstart", event);
      me.dispatchEvent(newEvent);
    };
    this.config["cropmove"] = function (event) {
      var newEvent = new CustomEvent("cropperjs-cropmove", event);
      me.dispatchEvent(newEvent);
    };
    this.config["cropend"] = function (event) {
      var newEvent = new CustomEvent("cropperjs-cropend", event);
      me.dispatchEvent(newEvent);
    };
    this.config["ready"] = function (event) {
      var newEvent = new CustomEvent("cropperjs-ready", event);
      me.dispatchEvent(newEvent);
    };
    this.config["zoom"] = function (event) {
      var newEvent = new CustomEvent("cropperjs-zoom", event);
      me.dispatchEvent(newEvent);
    };

    this._initCroppie();
  }

  updateImage() {
    console.log("updateImage() :: Start");
    var ctx = this.shadowRoot.querySelector("#image");

    console.log("updateImage() :: New src = " + ctx.getAttribute("src"));
    this.croppie.destroy();
    this._initCroppie();
    // this.croppie.replace(ctx.getAttribute("src"));
  }

  _initCroppie() {
    console.log("_initCroppie() :: Start");
    var ctx = this.shadowRoot.querySelector("#image");
    console.log("_initCroppie() :: Image src = " + ctx.getAttribute("src"));
    this.croppie = new Cropper(ctx, this.config);
    console.log("_initCroppie() :: End");
  }
  set croppieOptions(newValue) {
    this._croppieOptions = newValue;
  }

  async firstUpdated(changedProperties) {
    this._croppieOptionsChanged(this._croppieOptions);
    this.requestUpdate(
        "croppieOptions",
        this.croppieOptions,
        this.constructor.properties.croppieOptions
    );
  }

  get croppieOptions() {
    return this._croppieOptions;
  }
}

window.customElements.define(CopperJs.is, CopperJs);
