import React, { useState, useEffect } from "react";
import styles from "./Alert.module.scss";

const Alert = ({ message }: { message: string }) => {
  const [visible, setVisible] = useState(true);

  useEffect(() => {
    const timer = setTimeout(() => {
      setVisible(false);
    }, 2000);

    return () => clearTimeout(timer);
  }, [message]);

  return visible ? (
    <div className={styles.alert}>
      <p>{message}</p>
    </div>
  ) : null;
};

export default Alert;
