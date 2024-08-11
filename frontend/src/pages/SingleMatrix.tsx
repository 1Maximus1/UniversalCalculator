import React, { useState } from "react";
import Button from "../ui/Button/Button";
import useFetch from "../hooks/useFetch/useFetch";
import styles from "./matrix.module.scss";
import { actionButtons as buttons } from "../configs/actions/single.config";
import Paint from "./helpers/Paint";
import FullMatrix from "../components/FullMatrix/FullMatrix";
import Alert from "../components/Alert/Alert";

const SingleMatrix = () => {
  const [matrix, setMatrix] = useState<number[][]>();
  const { fetchdata, value, loading, error } = useFetch<number | number[][]>();
  return (
    <div className={styles.canvas}>
      <div className={styles.equation}>
        <FullMatrix setMatrix={setMatrix} />
      </div>
      <div className={styles.options}>
        {matrix &&
          buttons.map(button => (
            <Button
              key={button.link}
              onClick={() =>
                fetchdata(
                  button.link!,
                  button.method!,
                  JSON.stringify({ matrix: matrix! })
                )
              }
            >
              {button.content}
            </Button>
          ))}
      </div>
      <div className={styles.result}>
        {loading && <h1>loading</h1>}
        {value && Paint(value, matrix![0].length)}
        {error && <Alert message={error.message} />}
      </div>
    </div>
  );
};

export default SingleMatrix;
