import { FC } from "react";
import IInput from "./IInput";
import styles from "./Input.module.scss";
const Input: FC<IInput> = props => {
  return (
    <input type='number' max={5} className={styles.IInput} {...props}></input>
  );
};

export default Input;
