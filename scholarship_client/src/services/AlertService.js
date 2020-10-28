import {Notification} from 'element-ui';

let defaultDuration = 3000;
let AlertService = (function () {
  let disableAlert = false;
  function setDisabled(bool) {
    console.log("Disable Alert Service: " + bool);
    disableAlert = bool;
  }
  return {
    setDisabled: setDisabled,
    show(type, title, message, duration) {
      if (disableAlert) return;
      Notification({
        type: type,
        title: title,
        message: message,
        duration: duration ? duration : defaultDuration,
      });
    },
    success(message, duration) {
      if (disableAlert) return;
      Notification.success({
        message: message,
        duration: duration ? duration : defaultDuration,
      });
    },
    error(message, duration) {
      if (disableAlert) return;
      Notification.error({
        message: message,
        duration: duration ? duration : defaultDuration,
      });
    },
    warning(message, duration) {
      if (disableAlert) return;
      Notification.warning({
        message: message,
        duration: duration ? duration : defaultDuration,
      });
    },
    info(message, duration) {
      if (disableAlert) return;
      Notification.info({
        message: message,
        duration: duration ? duration : defaultDuration,
      });
    }
  };
})();
export default AlertService;
