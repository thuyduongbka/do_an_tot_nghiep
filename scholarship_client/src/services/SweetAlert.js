const SweetAlert = {
  info(content){
    return Swal.fire({
      text: content
    });
  },
  infoHtml(html){
    return Swal.fire({
      html: html
    });
  },
  success(title, message, confirmBtnText, cancellable) {
    return Swal.fire({
      title: title,
      type: 'success',
      text: message,
      showCancelButton: cancellable ? cancellable : false,
      confirmButtonText: confirmBtnText ? confirmBtnText : 'OK'
    });
  },
  error(title, message, confirmBtnText, cancellable) {
    return Swal.fire({
      title: title,
      text: message,
      type: 'error',
      showCancelButton: cancellable ? cancellable : false,
      confirmButtonText: confirmBtnText ? confirmBtnText : 'OK'
    });
  },
  confirmDelete(title, message, preConfirm, ok, cancel) {
    /* callback is handle function when click confirm or cancel action */
    return Swal.fire({
      title: title ? title : 'Are you sure?',
      text: message ? message : "You won't be able to revert this!",
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      // cancelButtonColor: '#d33',
      confirmButtonText:ok ? ok : 'Confirm',
      cancelButtonText: cancel ? cancel : 'Cancel!',
      allowOutsideClick: false,
      showLoaderOnConfirm: true,
      preConfirm: preConfirm
    });
  },
  confirm(message, preConfirm) {
    return Swal.fire({
      title: "",
      text: message ? message : "You won't be able to revert this!",
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      // cancelButtonColor: '#d33',
      confirmButtonText: "common.confirmBtn",
      cancelButtonText: i18n.t('common.cancelBtn'),
      allowOutsideClick: false,
      showLoaderOnConfirm: true,
      preConfirm: preConfirm
    });
  },
  alertError(title, message, preConfirm) {
    return Swal.fire({
      title: title,
      text: message ? message : "Errors!",
      type: 'warning',
      showCancelButton: false,
      confirmButtonColor: '#3085d6',
      confirmButtonText: i18n.t('common.confirmBtn'),
      allowOutsideClick: false,
      showLoaderOnConfirm: true,
      preConfirm: preConfirm
    });
  },
  confirmApproval(title, message, preConfirm, ok) {
    return Swal.fire({
      title: title ? title : 'Are you sure?',
      text: message ? message : "You won't be able to revert this!",
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      confirmButtonText: ok ? ok : i18n.t('common.confirmBtn'),
      cancelButtonText: i18n.t('common.cancelBtn'),
      reverseButtons: true,
      allowOutsideClick: false,
      showLoaderOnConfirm: true,
      preConfirm: preConfirm
    });
  }
};

import Swal from "sweetalert2";

export default SweetAlert;
