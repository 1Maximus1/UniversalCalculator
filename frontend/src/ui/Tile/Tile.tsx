import { FC } from "react";
import ITile from "./ITile";
import styles from "./tile.module.scss";
const Tile: FC<ITile> = ({ value, getValue, rowIndex, colIndex }) => {
  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    // Передайте новое значение и координаты в родительский компонент
    getValue(event.target.value, rowIndex, colIndex);
  };

  return (
    <div className={styles.Tile}>
      <input
        className={styles.input}
        type='text'
        value={value}
        onChange={handleChange}
      />
    </div>
  );
};

export default Tile;
