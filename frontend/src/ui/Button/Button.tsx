import React, { FC } from "react";
import IButton from "./IButton";
import styles from "./Button.module.scss";
const Button: FC<IButton> = ({ children, ...props }) => {
  return (
    <button className={styles.Button} {...props}>
      {children}
    </button>
  );
};

export default Button;
