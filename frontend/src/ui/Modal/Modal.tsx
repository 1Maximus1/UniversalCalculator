import React, { FC } from "react";
import styles from "./Modal.module.scss";
import IModal from "./IModal";
const Modal: FC<IModal> = ({ visible, children }) => {
  return (
    <div className={`${styles.modal} ${visible ? "" : styles.hide}`}>
      {children}
    </div>
  );
};

export default Modal;
