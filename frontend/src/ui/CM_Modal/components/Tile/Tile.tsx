import React from "react";
import styles from "./Tile.module.scss";
const Tile = ({ checked }: { checked?: boolean }) => {
  return (
    <div
      className={`${styles.Tile} ${checked ? styles.Tile_Choosen : ""}`}
    ></div>
  );
};

export default Tile;
