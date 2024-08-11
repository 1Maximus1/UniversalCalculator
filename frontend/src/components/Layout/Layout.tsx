import React from "react";
import { Outlet } from "react-router-dom";
import Navigation from "../navigation/Navigation";
import styles from "./Layout.module.scss";
const Layout = () => {
  return (
    <div className={`${styles.layout} ${styles.container}`}>
      <Navigation />
      <div className={styles.centeredContent}>
        <div className={styles.maxWidthContainer}>
          <Outlet />
        </div>
      </div>
    </div>
  );
};

export default Layout;
