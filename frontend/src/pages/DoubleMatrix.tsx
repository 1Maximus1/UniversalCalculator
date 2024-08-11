import React, { useState } from "react";
import useFetch from "../hooks/useFetch/useFetch";
import Button from "../ui/Button/Button";
import Paint from "./helpers/Paint";
import styles from "./matrix.module.scss";

import { actionButtons as buttons } from "../configs/actions/multi.config";
import FullMatrix from "../components/FullMatrix/FullMatrix";
import Alert from "../components/Alert/Alert";

const DoubleMatrix = () => {
  const [matrix1, setMatrix1] = useState<number[][]>();
  const [matrix2, setMatrix2] = useState<number[][]>();
  const { fetchdata, value, loading, error } = useFetch<number | number[][]>();
  return (
    <div className={styles.canvas}>
      <div className={styles.equation}>
        <FullMatrix setMatrix={setMatrix1} />
        <FullMatrix setMatrix={setMatrix2} />
      </div>
      <div className={styles.options}>
        {matrix1 &&
          matrix2 &&
          buttons.map(button => (
            <Button
              key={button.link}
              onClick={() =>
                fetchdata(
                  button.link!,
                  button.method!,
                  JSON.stringify({ m1: matrix1!, m2: matrix2! })
                )
              }
            >
              {button.content}
            </Button>
          ))}
      </div>
      <div className={styles.result}>
        {loading && <h1>loading</h1>}
        {value && Paint(value, matrix1![0].length)}
        {error && <Alert message={error.message} />}
      </div>
    </div>
  );
};

export default DoubleMatrix;
